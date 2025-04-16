package kr.ac.hnu.dao.util;

import kr.ac.hnu.dao.global.entity.Items;
import kr.ac.hnu.dao.global.entity.OrderItems;
import kr.ac.hnu.dao.global.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static final String ITEM = "ITEM_";

    public static String genRandomItemCode() {
        return ITEM + genNumStr();
    }

    public static Integer genRandomOrderCode() {
        int num = (int) (Math.random() * 100_000);
        return num * 10_000;
    }

    public static Integer genRandomQuantity() {
        return (int) (Math.random() * 100);
    }

    public static Integer genRandomPrice() {
        int num = (int) (Math.random() * 100_000);
        return num * 10_000;
    }

    private static String genNumStr() {
        int num = (int) (Math.random() * 100_000);
        return Integer.toString(num);
    }

    public static Items generateItem() {
        return Items.builder()
                .name(genRandomItemCode())
                .itemCode(genRandomItemCode())
                .price(genRandomPrice())
                .build();
    }

    public static List<Items> generateItems(int amount) {
        List<Items> items = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            items.add(generateItem());
        }
        return items;
    }

    // 주문과 상품 리스트로 OrderItems 자동 생성하는 유틸 메서드
    public static List<OrderItems> genOrderItems(Orders order, List<Items> items, int amount) {
        return items.stream()
                .map(i -> OrderItems.builder()
                        .orders(order)
                        .quantity(amount)
                        .items(i)
                        .build()
                ).toList();
    }
}
