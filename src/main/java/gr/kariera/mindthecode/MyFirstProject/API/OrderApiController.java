package gr.kariera.mindthecode.MyFirstProject.API;

import gr.kariera.mindthecode.MyFirstProject.DTOs.NewOrderDto;
import gr.kariera.mindthecode.MyFirstProject.Entities.Order;
import gr.kariera.mindthecode.MyFirstProject.Entities.OrderProduct;
import gr.kariera.mindthecode.MyFirstProject.Entities.OrderProductPK;
import gr.kariera.mindthecode.MyFirstProject.Entities.Product;
import gr.kariera.mindthecode.MyFirstProject.Services.OrderService;
import gr.kariera.mindthecode.MyFirstProject.Services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
    private final OrderService orderService;
    private final ProductService productService;


    public OrderApiController(OrderService service, ProductService productService) {
        this.orderService = service;
        this.productService = productService;
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order one(@PathVariable Integer id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return "Deleted";
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Integer id,
                        @RequestBody NewOrderDto newOrderDto ) throws Exception {
        Order updatedOrder = updateOrder(id,newOrderDto);
        return updatedOrder;
    }

    @PostMapping
    @Transactional
    public Order newOrder(@RequestBody NewOrderDto newOrder) throws Exception {
        Order order = new Order();
        order.setAddress(newOrder.getAddress());
        order.setDiscountPercentage(newOrder.getDiscountPercentage());
        order = orderService.createOrUpdateOrder(order.getId(),order);

        final Order finalOrder = order;
        newOrder.getProducts()
                .stream()
                .forEach(nop -> {

                    Product p = productService.getById(nop.getProductId());

                    OrderProduct op = new OrderProduct();
                    OrderProductPK opPK = new OrderProductPK();
                    opPK.setOrderId(finalOrder.getId());
                    opPK.setProductId(p.getId());
                    op.setId(opPK);
                    op.setOrder(finalOrder);
                    op.setProduct(p);
                    op.setQuantity(nop.getQuantity());
                    finalOrder.getOrderProducts().add(op);
                    finalOrder.setOrderProducts(finalOrder.getOrderProducts());

                });

        Order resultOrder = orderService.createOrUpdateOrder(finalOrder.getId(),finalOrder);
        return resultOrder;

    }
}