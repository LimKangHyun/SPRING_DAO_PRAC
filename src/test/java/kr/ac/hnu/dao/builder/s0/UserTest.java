package kr.ac.hnu.dao.builder.s0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("User 생성 테스트 #C1")
    public void user_create_c1() {

        String name = "user1";
        User user = new User(name);

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(name);
    }
}
