package carshop.web.controlers;

import carshop.domain.dtos.supplier.SupplierImportRootDto;
import carshop.services.SupplierService;
import carshop.util.FileUtil;
import carshop.util.XmlParcer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Controller
public class CarDealerController implements CommandLineRunner {
    private static final String SUPPLIER_FILE_PATH = "C:\\Users\\sstoy\\Desktop\\SoftUni\\Git Hub\\Databases-Frameworks---Hibernate-Spring-Data\\10. XML PROCESSING\\src\\main\\resources\\files\\suppliers.xml";

    private final FileUtil fileUtil;
    private final SupplierService supplierService;
    private final XmlParcer xmlParcer;

    public CarDealerController(FileUtil fileUtil, SupplierService supplierService, XmlParcer xmlParcer) {
        this.fileUtil = fileUtil;
        this.supplierService = supplierService;
        this.xmlParcer = xmlParcer;
    }


    @Override
    public void run(String... args) throws Exception {
        this.seedSuppliers();
    }

    private void seedSuppliers() throws JAXBException, FileNotFoundException {
        SupplierImportRootDto supplierImportRootDto = this.xmlParcer
                .parseXml(SupplierImportRootDto.class, SUPPLIER_FILE_PATH);

        this.supplierService.seedSuppliers(supplierImportRootDto);

    }
}
