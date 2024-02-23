package JetBrains;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void shouldGetBulls() {
        Game game = Game.create(5, 15);
        String code = Arrays.stream(game.getCode())
                .mapToObj(c -> "" + (char) c)
                .collect(Collectors.joining());
        long actual = game.bulls(code.toCharArray());
        assertEquals(5, actual);
    }

    @Test
    void shouldGetCows() {
        Game game = Game.create(5, 15);
        String code = Arrays.stream(game.getCode())
                .mapToObj(c -> "" + (char) c)
                .collect(Collectors.joining());
        long actual = game.cows(code.toCharArray());
        assertEquals(actual, 0);
    }

    @Test
    void shouldPrepareMessage() {
        Game game = Game.create(5, 15);
        String actual = game.prepare();
        String expect = "Секретный код подготовлен: ***** (0-9, a-e).";
        assertEquals(expect, actual);
    }

    @Test
    void shouldValidateCharsTrue() {
        Game game = Game.create(5, 15);
        boolean actual = game.validateChars("0e123");
        assertTrue(actual);
    }

    @Test
    void shouldValidateCharsFalse() {
        Game game = Game.create(5, 15);
        boolean actual = game.validateChars("0e12f");
        assertFalse(actual);
    }

    @Test
    void shouldThrowException1() {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> Game.create(10, 9)
        );
        String expect = "Ошибка: невозможно сгенерировать код длинной 10 с 9 уникальными символами.";
        assertEquals(expect, exc.getMessage());
    }

    @Test
    void shouldThrowException2() {
        Exception exc = assertThrows(
                IllegalArgumentException.class,
                () -> Game.create(40, 40)
        );
        String expect = "Ошибка: максимальное количество возможных символов 36 (0-9, a-z).";
        assertEquals(expect, exc.getMessage());
    }
}
