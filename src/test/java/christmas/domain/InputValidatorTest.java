package christmas.domain;

import christmas.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static christmas.util.ParseUtil.parseKeyValuePairs;
import static org.assertj.core.api.AssertionsForClassTypes.*;

public class InputValidatorTest {
    DateOfVisit dateOfVisit;
    MenuOrdered menuOrdered;

    @BeforeEach
    void setUp() {
        dateOfVisit = new DateOfVisit();
        menuOrdered = new MenuOrdered();
    }

    @DisplayName("날짜 입력값 검증 후 반환하기.")
    @Test
    void returnDate() {
        int dateNumber = dateOfVisit.validateDate(1);
        assertThat(dateNumber).isEqualTo(1);
    }

    @DisplayName("날짜 입력값 범위 확인하기.")
    @ValueSource(ints = {0, 32, 78})
    @ParameterizedTest
    void validateRangeOfDate(int dates) {
        assertThatThrownBy(() -> dateOfVisit.validateRangeOfDate(dates))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴와 개수 검증 후 반환하기.")
    @Test
    void returnMenu() {
        Map<String, String> orderMenu = this.menuOrdered.validateMenu(Map.of("티본스테이크", "1", "샴페인", "1"));
        assertThat(orderMenu).isEqualTo(Map.of("티본스테이크", "1", "샴페인", "1"));
    }

    @DisplayName("고객이 메뉴판에 없는 메뉴를 입력하는 경우 예외처리")
    @Test
    void validateMenuExist() {
        assertThatThrownBy(() -> menuOrdered.validateMenuExist(Map.of("골드키위샐러드", "1","티본스테이끼","2","시저샐러드","1")))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴의 개수는 1 이상의 숫자만 입력되도록 해주세요. 이외의 입력값은 예외처리")
    @Test
    void validateNumberOfEachMenu() {
        assertThatThrownBy(() -> menuOrdered.validateNumberOfEachMenu(Map.of("시저샐러드", "0")))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴 형식이 예시와 다른 경우 예외처리")
    @ValueSource(strings = {"", ",레드와인-1", "해산물파스타2,레드와인_1,초코케이크_1", "해산물파스타-,레드와인-1,초코케이크-1", "해산물파스타-1.레드와인-1, 초코케이크-1"})
    @ParameterizedTest
    void validateFormOfMenu(String input) {
        assertThatThrownBy(() -> parseKeyValuePairs(input))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복 메뉴는 hashMap이 애초에 걸러주는 거 확인하기")
    @Test
    void validateDuplicatedMenu() {
        Map<String, String> orderMenu = parseKeyValuePairs("시저샐러드-1,시저샐러드-1");
        assertThat(orderMenu.size()).isEqualTo(1);
    }

    @DisplayName("음료만 주문 시 예외처리")
    @Test
    void validateOnlyDrink() {
        assertThatThrownBy(() -> menuOrdered.validateOnlyDrink(Map.of("제로콜라", "1", "샴페인", "1")))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴를 한 번에 20개 초과로 시키면 예외처리")
    @Test
    void validateTotalNumberOfMenu() {
        assertThatThrownBy(() -> menuOrdered.validateTotalNumberOfMenu(Map.of("시저샐러드", "1", "티본스테이크", "7", "크리스마스파스타", "6", "제로콜라", "5", "아이스크림", "5")))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
