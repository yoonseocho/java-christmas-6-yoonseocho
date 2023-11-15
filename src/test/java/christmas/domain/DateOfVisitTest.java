package christmas.domain;

import christmas.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class DateOfVisitTest {
    @DisplayName("날짜 입력값 검증 후 반환하기.")
    @ValueSource(ints = {0, 32, 78})
    @ParameterizedTest
    void validateDate(int dates) {
        assertThatThrownBy(() -> new DateOfVisit(dates))
                .isInstanceOf(InvalidInputException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
