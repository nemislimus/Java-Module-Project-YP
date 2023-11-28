import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        String Label= "D-Bill"; //just for fun (^_^)
        System.out.println("Вас приветствует приложение для уставших тусовщиков " + "\033[0;1m" + Label + "\033[0m" + " (divide bill).");
        System.out.println("Укажите число (на сколько персон будем дробить счёт):");
        Scanner input = new Scanner(System.in);
        int persons;
        double totalPrice = 0;
        double personPrice=0;
        String rubPersonal;
        String rubTotal;
        String checkList = "Добавленные позиции: ";

        //проверяем, что ввели именно число персон, а не белеберду или нелогичное значение...
        while (true){
            boolean personsIsDigit = input.hasNextInt();
            persons = personsIsDigit ? input.nextInt() : -1;
            if(persons == 0){
                System.out.println("Количество персон - 0\nСмысл имеет число больше еденицы, попробуйте снова: ");
                input.nextLine();
            } else if(persons == 1){
                System.out.println("Количество персон - 1\nСмысл имеет число больше еденицы, попробуйте снова: ");
                input.nextLine();
            } else if(persons > 1){
                System.out.println("Принято! Количество персон: " + persons);
                input.nextLine();
                break;
            } else {
                System.out.println("Неверно! Напишите цифру больше 1: ");
                input.nextLine();
            }
        }
        System.out.println("\nВыбирите дальнейшее действие:\n1. Напишите любое название позиции для формирования чека. \n2. Закрыть чек и совершить расчёт (напишите \"Завершить\").");
        // тут формируем список позиций в чек
        while(true){
            String inputUnit = input.nextLine(); //считываем название позиции
            String proverka="";
            if(!inputUnit.equalsIgnoreCase("завершить")){
                ItemList checkItem = new ItemList(inputUnit, 0); // создаём объект позиции и вносим в него введнные значения
                System.out.println("Укажите стоимость позиции в формате - рубли.копейки (0.00): ");
                while(true) {
                    String itemPrice = input.nextLine(); //считываем стоимость позиции
                    //Далее первая ступень проверки, есть ли в введённой строке цифры и спец символы
                    for (int i = 0; i < itemPrice.length(); i++) {
                        if (Character.isDigit(itemPrice.charAt(i)) || itemPrice.charAt(i) == ',' || itemPrice.charAt(i) == '.') {
                            char ch = itemPrice.charAt(i);
                            proverka += ch;
                        }
                    }
                    //тут вторая ступень проверки на наличие цифр в воде
                    if(proverka.equals(",") || proverka.equals(".") || proverka.isEmpty()){
                        System.out.println("Вы не ввели ни одной цифры, повторите ввод в формате - рубли.копейки (0.00):");
                    } else {
                        double itemPriceDigit = Double.parseDouble(proverka.replace(",", "."));
                        totalPrice += itemPriceDigit;  //накидываем тотал по чеку
                        checkItem.itemCost = itemPriceDigit; //фиксируем стоимость позиции
                        personPrice = checkItem.calculatePersonCash(totalPrice, persons); // расчитываем затраты на персону
                        checkList += String.format("\n%s  %.2f", checkItem.itemName, checkItem.itemCost); // формируем строку списка в чеке
                        break;
                    }
                }
                System.out.println("Название следующей позиции или \"Завершить\": ");

            } else {
                System.out.println(checkList);
                rubTotal = (totalPrice <2f) ? "рубль" : (totalPrice>=5f ? "рублей" : "рубля");
                rubPersonal = (personPrice <2f) ? "рубль" : (personPrice>=5f ? "рублей" : "рубля");
                System.out.println(String.format("\n\nОбщая сумма чека: %.2f %s.\n\nДелим на %d-х. Сумма с одного человека: %.2f %s.",totalPrice, rubTotal, persons,personPrice, rubPersonal));
                break;
            }
        }
    }
}

 class ItemList{
    String itemName;
    double itemCost;
    public double calculatePersonCash(double summaryPrice, int persCount){
        return summaryPrice/(double)persCount;
     }
    ItemList(String name, double cost){
        itemName = name;
        itemCost = cost;
    }
}

