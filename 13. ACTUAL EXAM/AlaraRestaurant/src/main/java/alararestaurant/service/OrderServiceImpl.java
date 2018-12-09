package alararestaurant.service;

import alararestaurant.domain.dtos.orders.OrderImputRootDto;
import alararestaurant.domain.entities.*;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final String ORDER_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/orders.xml";

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemRepository itemRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() throws IOException {

        return this.fileUtil.readFile(ORDER_FILE_PATH);
    }

    @Override
    public String importOrders() throws JAXBException, FileNotFoundException {
        StringBuilder ordersResult = new StringBuilder();

        OrderImputRootDto orderImputRootDto = this.xmlParser.parseXml(OrderImputRootDto.class, ORDER_FILE_PATH);

        Arrays.stream(orderImputRootDto.getOrderImputDtos()).forEach(orderImputDto -> {

            Employee employee = this.employeeRepository.findByName(orderImputDto.getEmployee()).orElse(null);

            if (employee == null) {

                ordersResult.append("Invalid data format.").append(System.lineSeparator());
                return;

            }
            Order order = this.modelMapper.map(orderImputDto, Order.class);
            order.setEmployee(employee);

            List<OrderItem> orderItems = new ArrayList<>();

            Arrays.stream(orderImputDto.getItems().getItemImportDtos()).forEach(itemImportDto -> {

                Item itemEntity = this.itemRepository.findByName(itemImportDto.getName()).orElse(null);

                if (itemEntity == null || !this.validationUtil.isValid(itemImportDto)) {

                    ordersResult.append("Invalid data format.").append(System.lineSeparator());

                    return;

                }

                OrderItem orderItem = this.orderItemRepository.findByItem(itemEntity).orElse(null);
                if (orderItem == null){
                    orderItem = this.modelMapper.map(itemImportDto, OrderItem.class);
                    orderItem.setItem(itemEntity);
                    orderItem.setOrder(order);
                    orderItems.add(orderItem);
                }

                System.out.println();

            });


            String date = orderImputDto.getDateTime();
            DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime localDate = LocalDateTime.parse(date, formattedDate);
            order.setType(OrderType.valueOf(orderImputDto.getType()));
            order.setDateTime(localDate);
            order.setEmployee(employee);
            order.setOrderItems(orderItems);

            this.orderRepository.saveAndFlush(order);

            this.orderItemRepository.saveAll(orderItems);


            ordersResult
                    .append(String.format("Order for %s on %s added"
                            ,order.getCustomer()
                            ,orderImputDto.getDateTime()
                    ))
                    .append(System.lineSeparator());


        });

        return ordersResult.toString().trim();

    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
       StringBuilder stringBuilder = new StringBuilder();

       List<Order>orders = this.orderRepository.burgerFlippers();

       orders.stream().forEach(order -> {

           stringBuilder.append(String.format("Name: %s", order.getEmployee().getName())).append(System.lineSeparator());
           stringBuilder.append("Orders:").append(System.lineSeparator());
           stringBuilder.append(String.format("   Customer: %s",order.getCustomer())).append(System.lineSeparator());
           stringBuilder.append("   Items:").append(System.lineSeparator());

           order.getOrderItems().forEach(orderItem -> {

               stringBuilder.append(String.format("      Name: %s",orderItem.getItem().getName())).append(System.lineSeparator());
               stringBuilder.append(String.format("      Price: %.2f",orderItem.getItem().getPrice())).append(System.lineSeparator());
               stringBuilder.append(String.format("      Quantity: %d",orderItem.getQuantity())).append(System.lineSeparator());
               stringBuilder.append(System.lineSeparator());
           });

       });

        return stringBuilder.toString().trim();
    }
}
