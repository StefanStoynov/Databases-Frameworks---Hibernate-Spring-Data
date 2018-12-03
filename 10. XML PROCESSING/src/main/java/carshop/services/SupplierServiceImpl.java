package carshop.services;

import carshop.domain.dtos.supplier.SupplierImportDto;
import carshop.domain.dtos.supplier.SupplierImportRootDto;
import carshop.domain.entities.Supplier;
import carshop.repositories.SupplierRepository;
import carshop.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, ValidatorUtil validatorUtil) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void seedSuppliers(SupplierImportRootDto importRootDtos) {
        for (SupplierImportDto dto : importRootDtos.getSupplierImportDtos()) {
            if (!validatorUtil.isValid(dto)){
                this.validatorUtil.violations(dto).forEach( v-> System.out.println(v.getMessage()));
                continue;
            }

            Supplier supplierEntity = this.modelMapper.map(dto, Supplier.class);

            this.supplierRepository.saveAndFlush(supplierEntity);
        }

    }
}
