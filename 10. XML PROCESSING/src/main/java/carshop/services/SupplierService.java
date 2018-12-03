package carshop.services;

import carshop.domain.dtos.supplier.SupplierImportRootDto;

public interface SupplierService {
    void seedSuppliers(SupplierImportRootDto importRootDtos);
}
