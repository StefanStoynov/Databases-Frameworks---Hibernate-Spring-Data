package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.orm.hibernate5.SpringFlushSynchronization;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    private static final String TOWNS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/towns.json";

    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder resultTowns = new StringBuilder();

        TownImportDto[] townImportDtos = gson.fromJson(townsFileContent, TownImportDto[].class);

        Arrays.stream(townImportDtos).forEach(townImportDto -> {
            Town town = this.townRepository.findByName(townImportDto.getName()).orElse(null);
            if (town != null) {
                resultTowns.append("Error: Duplicate Data!").append(System.lineSeparator());
                return;
            }

            if (!this.validationUtil.isValid(townImportDto)) {
                resultTowns.append("Error: Incorrect Data!").append(System.lineSeparator());
                return;
            }


            town = this.modelMapper.map(townImportDto, Town.class);

            this.townRepository.saveAndFlush(town);
            resultTowns.append(String.format("Successfully imported %s – %s.", townImportDtos.getClass().getSimpleName(), townImportDto.getName()));
            resultTowns.append(System.lineSeparator());
        });

        return resultTowns.toString().trim();
    }

    @Override
    public String exportRacingTowns() {
        StringBuilder racingTownsResult = new StringBuilder();

        List<Town> towns = this.townRepository.racingTowns();
        towns.stream().forEach(town -> {
            racingTownsResult.append("Name: ");
            racingTownsResult.append(town.getName()).append(System.lineSeparator());
            racingTownsResult.append("Racers: ").append(town.getRacer().size()).append(System.lineSeparator());
            racingTownsResult.append(System.lineSeparator());
        });
        return racingTownsResult.toString().trim();
    }
}
