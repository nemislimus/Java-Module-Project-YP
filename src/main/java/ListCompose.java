import java.util.Scanner;

public class ListCompose {
    int persons = 0;
    double totalPrice = 0;

    public void countPesrsons() {
        Scanner input = new Scanner(System.in);
        while (true) {
            boolean personsIsDigit = input.hasNextInt();
            this.persons = personsIsDigit ? input.nextInt() : -1;
            if (persons == 0) {
                System.out.println("Количество персон - 0\nСмысл имеет число больше еденицы, попробуйте снова: ");
                input.nextLine();
            } else if (persons == 1) {
                System.out.println("Количество персон - 1\nСмысл имеет число больше еденицы, попробуйте снова: ");
                input.nextLine();
            } else if (persons > 1) {
                System.out.println("Принято! Количество персон: " + persons);
                input.nextLine();
                break;
            } else {
                System.out.println("Неверно! Напишите цифру больше 1: ");
                input.nextLine();
            }
        }
    }

    public void listOfItems() {
        String rubPersonal;
        String rubTotal;
        String checkList = "Добавленные позиции: ";
        double personPrice = 0;
        while (true) {
            Scanner input = new Scanner(System.in);
            String inputUnit = input.nextLine(); //считываем название позиции
            String proverka = "";
            if (!inputUnit.trim().equalsIgnoreCase("завершить")) {
                ItemList checkItem = new ItemList(inputUnit, 0); // создаём объект позиции и вносим в него введнные значения
                System.out.println("Укажите стоимость позиции в формате - рубли.копейки (0.00): ");
                while (true) {
                    String itemPrice = input.nextLine(); //считываем стоимость позиции
                    //Далее первая ступень проверки, есть ли в введённой строке цифры и спец символы
                    for (int i = 0; i < itemPrice.length(); i++) {
                        if (Character.isDigit(itemPrice.charAt(i)) || itemPrice.charAt(i) == ',' || itemPrice.charAt(i) == '.') {
                            char ch = itemPrice.charAt(i);
                            proverka += ch;
                        }
                    }
                    //тут вторая ступень проверки на наличие цифр на вводе
                    if (proverka.equals(",") || proverka.equals(".") || proverka.isEmpty()) {
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
                rubTotal = wordVariation(totalPrice);  // (totalPrice <2f) ? "рубль" : (totalPrice>=5f ? "рублей" : "рубля");
                rubPersonal = wordVariation(personPrice);    //(personPrice <2f) ? "рубль" : (personPrice>=5f ? "рублей" : "рубля");
                System.out.println(String.format("\n\nОбщая сумма чека: %.2f %s.\n\nДелим на %d-х. Сумма с одного человека: %.2f %s.", totalPrice, rubTotal, persons, personPrice, rubPersonal));
                break;
            }
        }
    }


    public String wordVariation(double cost) {
        String variant = "";
        int sokr = (int) (Math.floor(cost)) % 100;
        int sokrDecade = sokr % 10;

        if (sokrDecade == 0 || sokr >= 5 && sokr <= 20) {
            variant = "рублей";
        } else if (sokr == 1) {
            variant = "рубль";
        } else if (sokr > 1 && sokr < 5) {
            variant = "рубля";
        } else if (sokr > 20 && sokrDecade == 1) {
            variant = "рубль";
        } else if (sokr > 20 && sokrDecade > 1 && sokrDecade < 5) {
            variant = "рубля";
        } else if (sokr > 20 && sokrDecade >= 5) {
            variant = "рублей";
        }


        return variant;
    }

}