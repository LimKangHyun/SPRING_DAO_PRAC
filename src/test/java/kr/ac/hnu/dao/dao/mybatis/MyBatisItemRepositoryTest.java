package kr.ac.hnu.dao.dao.mybatis;

import kr.ac.hnu.dao.global.entity.Items;
import kr.ac.hnu.dao.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
// JUnit 5에서 Mockito를 사용할 때 필요한 확장 기능을 추가
@ExtendWith(MockitoExtension.class)
public class MyBatisItemRepositoryTest {

    @Mock
    ItemMapper mapper;
    MyBatisItemRepository repository;

    @BeforeEach
    void init() {
        // 각 테스트가 실행되기 전에 매번 새로운 리포지토리 객체를 초기화하여 독립적인 테스트 환경 보장
        repository = new MyBatisItemRepository(mapper);
    }

    @Test
    @DisplayName("상품 등록 서비스")
    void item_save_test() {
        Items item = Items.builder()
                .name(TestUtil.genRandomItemCode())
                .itemCode(TestUtil.genRandomItemCode())
                .price(TestUtil.genRandomPrice())
                .build();

        doNothing().when(mapper).save(item);
        Items saved = repository.save(item);

        assertThat(saved.getItemCode()).isEqualTo(item.getItemCode());

        verify(mapper, times(1)).save(item);
    }

    @Test
    @DisplayName("예외 처리 테스트")
    void raise_exception_test() {

        Items item = Items.builder()
                .itemCode(TestUtil.genRandomItemCode())
                .price(TestUtil.genRandomPrice())
                .build();
        doThrow(RuntimeException.class).when(mapper).save(item);
        assertThatThrownBy(() -> repository.save(item))
                .isInstanceOf(RuntimeException.class);
        verify(mapper, times(1)).save(item);
    }

    @Test
    @DisplayName("예외 처리 테스트")
    void find_by_item_code_test() {
        String VALID_ITEM_CODE = "validItemCode";
        String INVALID_ITEM_CODE = "INVALID_ITEM_CODE";

        Items validItem = Items.builder()
                .name("testItem")
                .itemCode(VALID_ITEM_CODE)
                .price(1000)
                .build();
        // 유효한 itemCode에 대해 값을 반환하도록 설정
        when(mapper.findByItemCode(VALID_ITEM_CODE)).thenReturn(Optional.of(validItem));
        // 유효하지 않은 itemCode에 대해 빈 값을 반환하도록 설정
        when(mapper.findByItemCode(INVALID_ITEM_CODE)).thenReturn(Optional.empty());

        // 유효한 itemCode로 조회했을 때, 아이템이 존재해야 함
        Optional<Items> validItemOptional = repository.findByItemCode(VALID_ITEM_CODE);
        assertThat(validItemOptional.isPresent()).isTrue();
        assertThat(validItemOptional.get()).isEqualTo(validItem);

        // 유효하지 않은 itemCode로 조회했을 때, 예외가 발생해야 함
        Optional<Items> invalidItemOptional = repository.findByItemCode(INVALID_ITEM_CODE);
        assertThat(invalidItemOptional.isPresent()).isFalse();
        assertThatThrownBy(
                () -> {
                    invalidItemOptional.get(); // 예외가 발생해야 한다
                }
        ).isInstanceOf(NoSuchElementException.class); // 예외 타입 확인

    }

    @Test
    @DisplayName("정확한 item code와 변경하고자 하는 가격이 주어지면 가격이 변경되는지 테스트")
    void it_will_change() throws Exception {

        // 테스트용 유효한 itemCode와 변경할 가격 설정
        final String VALID_ITEM_CODE = "validItemCode";
        final String INVALID_ITEM_CODE = "INVALID_ITEM_CODE";
        final int TARGET_PRICE = 10000;

        Items updated = Items.builder()
                .name(VALID_ITEM_CODE)
                .itemCode(VALID_ITEM_CODE)
                .price(TARGET_PRICE)
                .build();
        // mapper의 update 메서드가 호출될 때 1을 반환하도록 설정 (성공적인 업데이트)
        when(mapper.update(any(String.class), any(Integer.class))).thenReturn(1);
        // mapper의 findByItemCode 메서드가 유효한 itemCode에 대해 아이템을 반환하도록 설정
        when(mapper.findByItemCode(VALID_ITEM_CODE)).thenReturn(Optional.of(updated));

        repository.updatePrice(VALID_ITEM_CODE, TARGET_PRICE);

        // 업데이트된 아이템이 정상적으로 반환되는지 확인
        Optional<Items> itemOptional = repository.findByItemCode(VALID_ITEM_CODE);
        assertThat(itemOptional.isPresent()).isTrue();
        assertThat(itemOptional.get()).isEqualTo(updated);
        assertThat(itemOptional.get().getPrice()).isEqualTo(TARGET_PRICE);

        // 유효하지 않은 itemCode에 대해 조회 시 빈 값을 반환하도록 설정
        when(mapper.findByItemCode(INVALID_ITEM_CODE)).thenReturn(Optional.empty());

        // 유효하지 않은 itemCode로 조회 시 예외가 발생해야 함
        Optional<Items> invalidItemOptional = repository.findByItemCode(INVALID_ITEM_CODE);
        assertThat(invalidItemOptional.isPresent()).isFalse();
        assertThatThrownBy(
                () -> {
                    invalidItemOptional.get(); // 예외가 발생해야 한다
                }
        ).isInstanceOf(NoSuchElementException.class); // 예외 타입 확인
    }
}