package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BadgeOfDecemberTest {
    BadgeOfDecember badgeOfDecember;
    @BeforeEach
    void setUp(){
        badgeOfDecember = new BadgeOfDecember(-21000);
    }
    @DisplayName("총혜택 금액에 따라 이벤트 배지의 이름 반환하기")
    @Test
    void getBadgeByTotalDiscountAmount(){
        String badge = badgeOfDecember.getBadgeByTotalDiscountAmount();
        assertThat(badge).isEqualTo("산타");
    }
}
