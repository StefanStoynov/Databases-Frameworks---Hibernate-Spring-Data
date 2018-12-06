package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.race.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {
    private static final String RACES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/races.xml";

    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository, FileUtil fileUtil, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() > 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_XML_FILE_PATH);
    }

    @Override
    public String importRaces() throws JAXBException, FileNotFoundException {
        StringBuilder raceResult = new StringBuilder();

        RaceImportRootDto raceImportRootDto = this.xmlParser.parseXml(RaceImportRootDto.class, RACES_XML_FILE_PATH);

        Arrays.stream(raceImportRootDto.getRaceImportDtos()).forEach(raceImportDto -> {
            District districtEntity = this.districtRepository.findByName(raceImportDto.getDistrictName()).orElse(null);

            if (!this.validationUtil.isValid(raceImportDto) || districtEntity == null){
                raceResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                return;
            }

            Race raceEntity = this.modelMapper.map(raceImportDto, Race.class);
            raceEntity.setDistrict(districtEntity);

            List<RaceEntry>raceEntries = new ArrayList<>();

            Arrays.stream(raceImportDto.getEntries().getEntries()).forEach(entryImportDto -> {
                RaceEntry raceEntryEntity = this.raceEntryRepository.findById(entryImportDto.getId()).orElse(null);
                if (raceEntryEntity == null){
                    return;
                }

                raceEntryEntity.setRace(raceEntity);
                raceEntries.add(raceEntryEntity);

            });

            this.raceRepository.saveAndFlush(raceEntity);
            this.raceEntryRepository.saveAll(raceEntries);

            raceResult
                    .append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,
                            raceEntity.getClass().getSimpleName(),
                            raceEntity.getId()
                    ))
                    .append(System.lineSeparator());

        });

        return raceResult.toString().trim();
    }
}
