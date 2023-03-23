package gr.kariera.mindthecode.MyFirstProject.Services;

import gr.kariera.mindthecode.MyFirstProject.Entities.Order;
import gr.kariera.mindthecode.MyFirstProject.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }


    @Override
    public Order createOrUpdateOrder(Integer id, Order order) throws Exception {
        if (id != null) {
            if (!id.equals(order.getId())) {
                throw new Exception("id in path does not patch id in body");
            }
        }
        return repository.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        Order match = repository.findById(id)
                .orElseThrow();
        repository.delete(match);
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return repository.findById(id)
                .orElseThrow();
    }
}
