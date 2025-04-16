package kr.ac.hnu.dao.util;

import kr.ac.hnu.dao.global.entity.Items;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static final String ITEM = "ITEM_";

    public static String genRandomItemCode() {
        return ITEM + genNumStr();
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
}
