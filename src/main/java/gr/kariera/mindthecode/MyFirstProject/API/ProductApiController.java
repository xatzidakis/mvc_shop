package gr.kariera.mindthecode.MyFirstProject.API;

import gr.kariera.mindthecode.MyFirstProject.Entities.Product;
import gr.kariera.mindthecode.MyFirstProject.Services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    private final ProductService service;

    public ProductApiController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC") String sort
    ) {
        return service.getProducts(description, page, size, sort);
    }


    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        try {
            return new ResponseEntity <>(service.createOrUpdateProduct(id, product), HttpStatus.OK);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

    @PostMapping
    public Product newProduct(@RequestBody Product product) {
        try {
            return service.createOrUpdateProduct(product.getId(), product);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteProduct(id);
    }
}
