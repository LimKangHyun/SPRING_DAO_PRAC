package kr.ac.hnu.dao.dao.datajpa;

import kr.ac.hnu.dao.dao.jpa.DataJpaOrderRepository;
import kr.ac.hnu.dao.global.entity.Orders;
import kr.ac.hnu.dao.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Slf4j
@DataJpaTest
public class DataJpaOrderRepositoryTest {

    @Autowired
    DataJpaOrderRepository repository;

    @Test
    @DisplayName("save test")
    void save_test() throws Exception {

        String orderCode = TestUtil.genRandomOrderCode();
        Orders order = Orders.builder().orderCode(orderCode).build();

        Orders saved = repository.save(order);

        Assertions.assertThat(saved.getId()).isNotNull();

    }
}
