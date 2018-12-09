package alararestaurant.service;

import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ItemServiceImpl implements ItemService {

    private static final String ITEM_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/items.json";

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    private final FileUtil fileUtil;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }


    @Override
    public Boolean itemsAreImported() {
       return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {

        return this.fileUtil.readFile(ITEM_FILE_PATH);
    }

    @Override
    public String importItems(String items) {
        StringBuilder itemsResult = new StringBuilder();

        ItemImportDto[] itemImportDtos = this.gson.fromJson(items, ItemImportDto[].class);

        Arrays.stream(itemImportDtos).forEach(itemImportDto -> {

            if (!this.validationUtil.isValid(itemImportDto)){
                itemsResult.append("Invalid data format.").append(System.lineSeparator());
                return;
            }
            Category category =this.categoryRepository.findByName(itemImportDto.getCategory()).orElse(null);

            if (category == null){
                category = new Category();
                category.setName(itemImportDto.getCategory());

                if (!this.validationUtil.isValid(category)){
                    itemsResult.append("Invalid data format.").append(System.lineSeparator());
                    return;
                }

                this.categoryRepository.saveAndFlush(category);
            }

            if (this.itemRepository.findByName(itemImportDto.getName()).orElse(null) != null){

                itemsResult.append("Invalid data format.").append(System.lineSeparator());
                return;
            }

            Item item = this.modelMapper.map(itemImportDto, Item.class);
            item.setCategory(category);
            this.itemRepository.saveAndFlush(item);

            itemsResult
                    .append(String.format("Record %s successfully imported.",
                            item.getName()))
                    .append(System.lineSeparator());
        });

        return itemsResult.toString().trim();
    }
}
