package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class RacerServiceImpl implements RacerService {

    private static final String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;


    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, FileUtil fileUtil, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder racersResult = new StringBuilder();

        RacerImportDto[] racerImportDtos = this.gson.fromJson(racersFileContent,RacerImportDto[].class);

        Arrays.stream(racerImportDtos).forEach(racerImportDto -> {
            Racer racerEntity = this.racerRepository.findByName(racerImportDto.getName()).orElse(null);
            if (racerEntity != null){
                racersResult.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                return;
            }
            Town townEntity = this.townRepository.findByName(racerImportDto.getHomeTown()).orElse(null);
            if (!this.validationUtil.isValid(racerImportDto)|| townEntity == null){
                racersResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                return;
            }

            racerEntity = this.modelMapper.map(racerImportDto, Racer.class);
            racerEntity.setHomeTown(townEntity);

            this.racerRepository.saveAndFlush(racerEntity);

            racersResult.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, racerEntity.getClass().getSimpleName(), racerEntity.getName()));
            racersResult.append(System.lineSeparator());
        });

        return racersResult.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        return null;
    }
}
