package JetBrains;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game game;
        int secretLength;
        int symbolsRange;

        System.out.println("Введите длину секретного кода: ");
        try {
            secretLength = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Ошибка: неверный ввод.");
            return;
        }

        System.out.println("Введите число уникальных символов в коде: ");
        try {
            symbolsRange = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Ошибка: неверный ввод.");
            return;
        }

        try {
            game = Game.create(secretLength, symbolsRange);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println(game.prepare());
        System.out.println("Начнем игру!");

        int round = 1;
        while (true) {

            System.out.println("Ход " + round + ":");
            String input;

            try {
                input = scanner.next();
            } catch (Exception e) {
                System.out.println("Ошибка: неверный ввод.");
                continue;
            }

            if (input.length() != secretLength) {
                System.out.println("Ошибка: неверная длинна.");
                continue;
            }

            if (!game.validateChars(input)) {
                System.out.println("Ошибка: неверный формат");
                continue;
            }

            int bulls = (int) game.bulls(input.toCharArray());
            int cows = (int) game.cows(input.toCharArray());
            System.out.println(Util.grade(bulls, cows));

            if (bulls == secretLength) {
                System.out.println("Поздравляю! Вы разгадали код.");
                break;
            }

            round++;
        }
    }

}
