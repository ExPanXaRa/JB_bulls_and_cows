package JetBrains;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {

    @ParameterizedTest
    @MethodSource("provider")
    void shouldgrade(int bulls, int cows, String expect) {
        String actual = Util.grade(bulls, cows);
        assertEquals(expect, actual);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(0, 0, "Количество угаданных: Ни одного."),
                Arguments.of(1, 0, "Количество угаданных: 1 бык."),
                Arguments.of(0, 1, "Количество угаданных: 1 корова."),
                Arguments.of(3, 0, "Количество угаданных: 3 быка."),
                Arguments.of(0, 2, "Количество угаданных: 2 коровы."),
                Arguments.of(4, 5, "Количество угаданных: 4 быка и 5 коров."),
                Arguments.of(4, 1, "Количество угаданных: 4 быка и 1 корова."),
                Arguments.of(1, 3, "Количество угаданных: 1 бык и 3 коровы.")
        );
    }

}
