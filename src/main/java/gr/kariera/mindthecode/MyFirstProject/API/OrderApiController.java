package gr.kariera.mindthecode.MyFirstProject.API;

import gr.kariera.mindthecode.MyFirstProject.Entities.Order;
import gr.kariera.mindthecode.MyFirstProject.Services.OrderService;
import gr.kariera.mindthecode.MyFirstProject.Services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
    private final OrderService orderService;
    private final ProductService productService;


    public OrderApiController(OrderService service, ProductService productService) {
        this.orderService = service;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Order one(@PathVariable Integer id) {
        return orderService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/orders/{id}")
    public Order update(@PathVariable Integer id, @RequestBody Order order) throws Exception {
        try {
            return orderService.createOrUpdateOrder(id, order);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

//    @PostMapping
//    @Transactional
//    public Order newOrder(@RequestBody NewOrderDto newOrder) throws Exception {
//        Order order = new Order();
//        order.setAddress(newOrder.getAddress());
//        order.setDiscountPercentage(newOrder.getDiscountPercentage());
//        order = orderService.createOrUpdateOrder(order.getId(),order);
//
//        final Order finalOrder = order;
//        newOrder.getProducts()
//                .stream()
//                .forEach(nop -> {
//
//                    Product p = productRepo
//                            .findById(nop.getProductId())
//                            .orElseThrow();
//
//                    OrderProduct op = new OrderProduct();
//                    OrderProductPK opPK = new OrderProductPK();
//                    opPK.setOrderId(finalOrder.getId());
//                    opPK.setProductId(p.getId());
//                    op.setId(opPK);
//                    op.setOrder(finalOrder);
//                    op.setProduct(p);
//                    op.setQuantity(nop.getQuantity());
//                    finalOrder.getOrderProducts().add(op);
//                    finalOrder.setOrderProducts(finalOrder.getOrderProducts());
//
//                });
//    }
}
