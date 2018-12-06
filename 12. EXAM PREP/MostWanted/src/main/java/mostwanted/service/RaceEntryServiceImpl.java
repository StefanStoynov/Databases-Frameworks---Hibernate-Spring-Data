package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.raceentries.RaceEntrieImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private static final String RACE_ENTRY_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() > 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRY_XML_FILE_PATH);
    }

    @Override
    public String importRaceEntries() throws JAXBException, FileNotFoundException {
        StringBuilder raceEntriesResult = new StringBuilder();

        RaceEntrieImportRootDto raceEntrieImportRootDtos = this.xmlParser
                .parseXml(RaceEntrieImportRootDto.class, RACE_ENTRY_XML_FILE_PATH);

        Arrays.stream(raceEntrieImportRootDtos.getRaceEntrieImportDtos()).forEach(raceEntrieImportDto -> {

            Car carEntity = this.carRepository.findById(raceEntrieImportDto.getCarId()).orElse(null);
            Racer racerEntity = this.racerRepository.findByName(raceEntrieImportDto.getRacer()).orElse(null);

            if (carEntity == null || racerEntity == null) {
                raceEntriesResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                return;
            }

            RaceEntry raceEntryEntity = this.modelMapper.map(raceEntrieImportDto, RaceEntry.class);
            raceEntryEntity.setRacer(racerEntity);
            raceEntryEntity.setCar(carEntity);
            raceEntryEntity.setRace(null);
            raceEntryEntity.setId(null);

            this.raceEntryRepository.saveAndFlush(raceEntryEntity);

            raceEntriesResult
                    .append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE
                            , raceEntryEntity.getClass().getSimpleName()
                            , raceEntryEntity.getId()
                    ))
                    .append(System.lineSeparator());

        });

        return raceEntriesResult.toString().trim();
    }
}
