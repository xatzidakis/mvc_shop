package gr.kariera.mindthecode.MyFirstProject.MVC;
import gr.kariera.mindthecode.MyFirstProject.Entities.Order;
import gr.kariera.mindthecode.MyFirstProject.Services.OrderService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }


    @GetMapping("/index")
    public String index() {
        return "index-orders";
    }


    @GetMapping()
    public String showOrders(Model model) {
        List<Order> orders = service.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/addOrder")
    public String addOrder(Model model) {
        model.addAttribute("order", new Order());
        return "add-order-form";
    }

    @PostMapping("/addNewOrder")
    public String saveOrder(@ModelAttribute("order") Order order) {
        try {
            service.createOrUpdateOrder(null, order);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
        return "redirect:/orders/addOrder";
    }



}
