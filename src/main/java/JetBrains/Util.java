package JetBrains;

public class Util {

    public static String grade(int bulls, int cows) {
        String s;
        if (bulls >= 1 && cows >= 1) {
            s = bulls + " " + (bulls % 10 == 1 ? "бык" : bulls % 10 > 4 ? "быков" : "быка") +
                    " и " + cows + " " + (cows % 10 == 1 ? "корова" : cows % 10 > 4 ? "коров" : "коровы");
        } else if (bulls >= 1) {
            s = bulls + " " + (bulls % 10 == 1 ? "бык" : bulls % 10 > 4 ? "быков" : "быка");
        } else if (cows >= 1) {
            s = cows + " " + (cows % 10 == 1 ? "корова" : cows % 10 > 4 ? "коров" : "коровы");
        } else {
            s = "Ноль";
        }
        return "Количество угаданных: " + s + ".";
    }

}
