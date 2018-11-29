package app.ccb.services;

import app.ccb.domain.dtos.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {
    private final static String BRANCH_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\11. WORKSHOP – MVC PROJECT SPRING MVC + SPRING DATA 1\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\branches.json";

    private final BranchRepository branchRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, Gson gson, ValidationUtil validationUtill, ModelMapper modelMapper, FileUtil fileUtil) {
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.validationUtil = validationUtill;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        String readBranchFile = this.fileUtil.readFile(BRANCH_FILE_PATH);

        return readBranchFile;
    }

    @Override
    public String importBranches(String branchesJson) {
        StringBuilder sb = new StringBuilder();

        BranchImportDto[] branchImportDtos = this.gson.fromJson(branchesJson, BranchImportDto[].class);

        for (BranchImportDto dto : branchImportDtos) {
            if (!this.validationUtil.isValid(dto)) {
                sb.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Branch branchEntity = this.modelMapper.map(dto, Branch.class);
            this.branchRepository.saveAndFlush(branchEntity);

            sb.append(String.format("Successfully imported Branch - %s.%n", branchEntity.getName()));
        }
        return sb.toString().trim();
    }
}
