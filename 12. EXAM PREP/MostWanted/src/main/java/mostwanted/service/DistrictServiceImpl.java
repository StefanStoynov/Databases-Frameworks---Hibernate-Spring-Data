package mostwanted.service;


import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class DistrictServiceImpl implements DistrictService {

    private static final String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/districts.json";

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder districtResult = new StringBuilder();

        DistrictImportDto[] districtImportDtos = this.gson.fromJson(districtsFileContent, DistrictImportDto[].class);

        Arrays.stream(districtImportDtos).forEach(districtImportDto -> {
            District districtEntity = this.districtRepository.findByName(districtImportDto.getName()).orElse(null);
            if (districtEntity != null) {
                districtResult.append("Error: Duplicate Data!").append(System.lineSeparator());
                return;
            }
            Town town = this.townRepository.findByName(districtImportDto.getTownName()).orElse(null);
            if (!this.validationUtil.isValid(districtImportDto) || town == null) {
                districtResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                return;
            }

            districtEntity = this.modelMapper.map(districtImportDto, District.class);
            districtEntity.setTown(town);

            this.districtRepository.saveAndFlush(districtEntity);

            districtResult.append(String.format("Successfully imported %s – %s.", districtImportDto.getClass().getSimpleName(), districtImportDto.getName()));
            districtResult.append(System.lineSeparator());

        });

        return districtResult.toString().trim();
    }
}
