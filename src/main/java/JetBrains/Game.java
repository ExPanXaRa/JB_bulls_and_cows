package JetBrains;

import JetBrains.range.ASCIIRange;
import JetBrains.range.ASCIIRangeExtended;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Game {

    private final int[] range;
    private final int[] code;
    private final String rangeString;

    private Game(int length, int[] range, String rangeString) {
        this.range = range;
        this.code = generateCodeWithoutRepeat(length, range);
        this.rangeString = rangeString;
    }

    public long bulls(char[] input) {
        return IntStream.range(0, Math.min(input.length, code.length))
                .filter(i -> input[i] == code[i])
                .count();
    }

    public long cows(char[] input) {
        int size = Math.min(input.length, code.length);
        return IntStream.range(0, size)
                .filter(i -> IntStream.range(0, size).anyMatch(j -> input[i] == code[j] && i != j))
                .count();
    }

    public boolean validateChars(String input) {
        return input.chars().allMatch(c -> Arrays.stream(range).anyMatch(r -> r == c));
    }

    public String prepare() {
        return "Секретный код подготовлен: " + "*".repeat(code.length) + " " + rangeString + ".";
    }

    public int[] getCode() {
        return code;
    }

    public int[] getRange() {
        return range;
    }

    private int[] generateCodeWithoutRepeat(int length, int[] range) {
        if (length > range.length) {
            throw new IllegalArgumentException("Длинна больше чем количество возможных символов.");
        }
        Random random = new Random();
        int[] code = new int[length];
        for (int i = 0; i < length; i++) {
            while (true) {
                final int rand = range[random.nextInt(range.length)];
                if (Arrays.stream(code).noneMatch(c -> c == rand)) {
                    code[i] = rand;
                    break;
                }
            }
        }
        return code;
    }

    public static Game create(int secretLength, int symbolsRange) {

        ASCIIRangeExtended ascii = new ASCIIRangeExtended(List.of(
                new ASCIIRange('0', '9'), new ASCIIRange('a', 'z')
        ));
        String rangeString = ascii.joinedRange(symbolsRange);
        String totalRangeString = ascii.joinedRange();
        int maxRange = ascii.get().length;

        if (symbolsRange > maxRange) {
            throw new IllegalArgumentException(String.format(
                    "Ошибка: максимальное количество возможных символов %d %s.",
                    maxRange,
                    totalRangeString
            ));
        }

        if (secretLength > symbolsRange || secretLength <= 0) {
            throw new IllegalArgumentException(String.format(
                    "Ошибка: невозможно сгенерировать код длинной %d с %d уникальными символами.",
                    secretLength,
                    symbolsRange
            ));
        }

        return new Game(secretLength, ascii.get(symbolsRange), rangeString);
    }

}
