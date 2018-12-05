package mostwanted.service;

import mostwanted.repository.RaceEntryRepository;
import mostwanted.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private static final String RACE_ENTRY_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final FileUtil fileUtil;

    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, FileUtil fileUtil) {
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
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
    public String importRaceEntries() {
        return null;
    }
}
