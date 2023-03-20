package gr.kariera.mindthecode.MyFirstProject.Services;

import gr.kariera.mindthecode.MyFirstProject.Entities.Order;

import java.util.List;

public interface OrderService{
    public abstract Order createOrUpdateOrder(Integer id, Order order) throws Exception;
    public  abstract void deleteOrder(Integer id);

    // public abstract Page<Order> getOrders();

    public abstract List<Order> getAllOrders();
    public abstract Order getById(Integer id);

}
