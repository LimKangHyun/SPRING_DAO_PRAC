package kr.ac.hnu.dao.dao.jpa;

import kr.ac.hnu.dao.global.entity.OrderItems;
import kr.ac.hnu.dao.global.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataJpaOrderRepositoryCombine {

    private final DataJpaOrderRepository orderRepository;
    private final DataJpaOrderItemRepository orderItemRepository;

    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    public List<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    public OrderItems saveOrderItem(OrderItems orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
