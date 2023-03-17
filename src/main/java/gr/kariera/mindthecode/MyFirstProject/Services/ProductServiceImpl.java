package gr.kariera.mindthecode.MyFirstProject.Services;

import gr.kariera.mindthecode.MyFirstProject.Entities.Product;
import gr.kariera.mindthecode.MyFirstProject.Repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createOrUpdateProduct(Integer id, Product product) throws Exception {
        if(id != null) {
            if(!id.equals(product.getId())) {
                throw new Exception("IDs does not match...");
            }
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getProducts(String description, int page, int size, String sort) {
        PageRequest paging = PageRequest.of(page, size)
                .withSort(sort.equalsIgnoreCase("ASC") ?
                Sort.by("description").ascending() :
                Sort.by("description").descending());
        Page<Product> res;
        if(description == null) {
            res = productRepository.findAll(paging);
        } else {
            res = productRepository.findByDescriptionContainingIgnoreCase(description, paging);
        }
        return res;
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }
}
