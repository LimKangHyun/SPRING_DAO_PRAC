package kr.ac.hnu.dao.builder.s1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {

    private static final Logger log = LoggerFactory.getLogger(UserTest.class);

    @Test
    @DisplayName("빌더 테스트")
    void builder_test() {
        String name = "user";
        int age = 20;
        String email = "user@email.com";

        User user = User.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();
        log.info("user.getName() = {}", user.getName());
        log.info("user.getAge() = {}", user.getAge());
        log.info("user.getEmail() = {}", user.getEmail());
    }
}
