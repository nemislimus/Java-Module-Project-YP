import java.util.Locale;


public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        String Label = "D-Bill"; //just for fun (^_^)

        System.out.println("Вас приветствует приложение для уставших тусовщиков " + "\033[0;1m" + Label + "\033[0m" + " (divide bill).");
        System.out.println("Укажите число (на сколько персон будем дробить счёт):");

        ListCompose list = new ListCompose();

        list.countPesrsons(); // определяем количесто персон

        System.out.println("\nВыбирите дальнейшее действие:\n1. Напишите любое название позиции для формирования чека. \n2. Закрыть чек и совершить расчёт (напишите \"Завершить\").");

        list.listOfItems(); // Основная логика (формируем и выводим список, и цены по счёту)

    }
}

