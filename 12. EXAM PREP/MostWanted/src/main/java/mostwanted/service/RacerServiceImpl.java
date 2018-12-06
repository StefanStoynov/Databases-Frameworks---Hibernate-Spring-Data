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

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    /**
     *
     * Export all racers which have any cars:
     *  •	Export the racer’s name, age (but ONLY if it is NOT NULL), list of cars.
     *          o	In case the racer’s age property is NULL, do NOT include it.
     *  •	The cars should be strings in the following format: “{brand} {model} {yearOfProduction}”.
     *  •	Order them descending, by count of cars they have, and then by racer name alphabetically.
     */
    @Override
    public String exportRacingCars() {
        StringBuilder racingCarsResult = new StringBuilder();

        List<Racer> racers = this.racerRepository.racingCars();
        racers.stream().forEach(racer -> {

            racingCarsResult.append("Name: ").append(racer.getName()).append(System.lineSeparator());

            if (racer.getAge()!=null){
                racingCarsResult.append("Age: ").append(racer.getAge()).append(System.lineSeparator());
            }

            racingCarsResult.append("Cars:").append(System.lineSeparator());

            racer.getCars().stream().forEach(car -> {
                racingCarsResult
                        .append(String.format("\t%s %s %s",
                                car.getBrand(),
                                car.getModel(),
                                car.getYearOfProduction()
                        ))
                        .append(System.lineSeparator());
            });
            racingCarsResult.append(System.lineSeparator());
        });

        return racingCarsResult.toString().trim();
    }
}
