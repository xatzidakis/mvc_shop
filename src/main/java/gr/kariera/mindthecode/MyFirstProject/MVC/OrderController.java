package gr.kariera.mindthecode.MyFirstProject.MVC;

import gr.kariera.mindthecode.MyFirstProject.Entities.Product;
import gr.kariera.mindthecode.MyFirstProject.Services.OrderService;
import gr.kariera.mindthecode.MyFirstProject.Services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    private final ProductService productService;

    public OrderController(OrderService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping()
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "new-order";
    }


}
