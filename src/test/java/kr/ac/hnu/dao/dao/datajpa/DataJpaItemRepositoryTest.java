package kr.ac.hnu.dao.dao.datajpa;

import jakarta.transaction.Transactional;
import kr.ac.hnu.dao.dao.jpa.DataJpaItemRepository;
import kr.ac.hnu.dao.global.entity.Items;
import kr.ac.hnu.dao.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static kr.ac.hnu.dao.util.TestUtil.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
public class DataJpaItemRepositoryTest {

    @Autowired
    DataJpaItemRepository repository;

    @Test
    @DisplayName("save test")
    void save_test() throws Exception{
        Items item = generateItem();
        Items saved = repository.save(item);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    @DisplayName("findById test")
    void find_by_id_test() throws Exception {

        Items item = generateItem();
        Items saved = repository.save(item);
        Optional<Items> itemOptional = repository.findById(saved.getId());
        assertThat(itemOptional.isPresent()).isTrue();

        assertThat(itemOptional.get()).isNotNull();
    }

    @Test
    @DisplayName("findByOrderCode test")
    void find_by_order_code_test() throws Exception {

        Items item = generateItem();
        Items saved = repository.save(item);

        Optional<Items> itemOptional = repository.findByItemCode(saved.getItemCode());
        assertThat(itemOptional.isPresent()).isTrue();

        Items findItem = itemOptional.get();
        assertThat(findItem.getItemCode()).isEqualTo(saved.getItemCode());
        assertThat(findItem.getItemCode()).isEqualTo(item.getItemCode());

    }
}
