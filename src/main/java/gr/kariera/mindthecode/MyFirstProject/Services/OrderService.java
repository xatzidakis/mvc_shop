package gr.kariera.mindthecode.MyFirstProject.Services;

import gr.kariera.mindthecode.MyFirstProject.Entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService{
    public abstract Order createOrUpdateOrder(Integer id, Order order) throws Exception;
    public  abstract void deleteOrder(Integer id);

    // public abstract Page<Order> getOrders();

    public abstract List<Order> getAllOrders();
    public abstract Order getById(Integer id);

}
