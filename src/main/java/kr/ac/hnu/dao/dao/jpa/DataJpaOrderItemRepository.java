package kr.ac.hnu.dao.dao.jpa;

import kr.ac.hnu.dao.global.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataJpaOrderItemRepository extends JpaRepository<OrderItems, Long> {

    @Query("select oi from OrderItems oi where oi.orders.orderCode = :orderCode")
    List<OrderItems> findAllByOrderCode(String orderCode);
}
