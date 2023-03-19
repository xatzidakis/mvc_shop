package gr.kariera.mindthecode.MyFirstProject.Services;

import gr.kariera.mindthecode.MyFirstProject.Entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public abstract Product createOrUpdateProduct(Integer id, Product product) throws Exception;
    public  abstract void deleteProduct(Integer id);
    public abstract Page<Product> getProducts(String description, int page, int size, String sort);
    public abstract List<Product> getAllProducts();
    public abstract Product getById(Integer id);
}
