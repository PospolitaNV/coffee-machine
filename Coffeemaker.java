package com.github.npospolita;

import java.util.Collections;

public class CoffeeMaker { // имя класса в CamelCase
    Printer printer = new Printer(); // переменная с маленькой буквы, обычно без сокращений

        /*
        Вносим данные о рецептах кофе в формате - кофе/вода/молоко/ в массив, где
        первый параметр - iD рецепта
        второй - кофе, в мг.
        третий - вода, в мл.
        четвертый - молоко в мл.
        пятый - резервируется для статуса, определяющего кол-во чашек кофе, ктороый
            возможно приготовить из имеющихся на данный момент аргументов.
        */

    /**
     * Весь код ниже - Receipts, NameOfCoffee - это прямые кандидаты на новый класс. Т.к. сейчас для добавления нового кофе придётся менять код.
     * В CoffeeMaker-е можно сделать конструктор с передачей возможных рецептов. и их параметрами.
     * Таким образом можно сделать инициализацию при запуске, а не переписывая код каждый раз.
     *
     * Поля - вода, кофе, молоко, имя рецепта. Класс Receipt
     *
     * Вместо arr[][] млжно использовать HashMap<Integer, Receipt> или HashMap<String, Receipt>
     */
    int Receipts[][] = {
            {0, 10, 50, 0, 0}, //0 - Эспрессо    10/50
            {1, 20, 100, 0, 0}, //1 - Доппио      20/100
            {2, 5, 50, 0, 0}, //2 - Лунго 	    5/50
            {3, 20, 50, 0, 0}, //3 - Ристретто	20/50
            {4, 10, 200, 0, 0}, //4 - Американо	10/200
            {5, 10, 50, 150, 0}, //5 - Флэт Уайт	10/50/150
            {6, 20, 100, 50, 0}, //6 - Макиато	    20/100/50
            {7, 10, 50, 100, 0}, //7 - Капучино	10/50/100
    };

    String NameOfCoffee[] = {
            "Эспрессо ",    //0 - Эспрессо      10/50
            "Доппио   ",    //1 - Доппио        20/100
            "Лунго    ",    //2 - Лунго 	    5/50
            "Ристретто",    //3 - Ристретто	    20/50
            "Американо",    //4 - Американо	    10/200
            "Флэт Уайт",    //5 - Флэт Уайт	    10/50/150
            "Макиато  ",    //6 - Макиато	    20/100/50
            "Капучино ",    //7 - Капучино	    10/50/100
    };

    // Создаем массив с данными об остатках компонентов в резервуарах
    // Возможно стоит сделать внутренний класс или отдельный с использованием композиции.
    int Reservuar[] = {
            200,  //кофе, в граммах
            3000,  //вода, в мл
            500,  //молоко, в мл
    };

    //Инициализация работы и загрузка резервуаров по полной до 255 мл/мг
    public void startMashine() {
        System.out.println("Инициализция...");
        System.out.println("Кофе машина загружена");
        System.out.println();

    }

    // Выдача состояния резервуаров
    public void statusMashine() {
        System.out.println();
        System.out.println("Содержимое кофемашины :");
        System.out.println("        Кофе   : " + Reservuar[0]);
        System.out.println("        Вода   : " + Reservuar[1]);
        System.out.println("        Молоко : " + Reservuar[2]);
        System.out.println();
    }

    //Выводит в консоль все доступные к приготовлению рецепты
    public void OutListAllAvaliableReceipts() { // не используется
        boolean j = false;// не используется
        int ActRcpt = 0;
        System.out.println("Доступность рецептов :");
        for (int i = 0; i <= 7; i++) {
            if (Receipts[i][4] != 0) {
                System.out.print("        " + NameOfCoffee[i] + " - доступно чашек - ");
                System.out.println(Receipts[i][4]);
                ActRcpt++;
            }
        }
        System.out.println();
        if (ActRcpt == 0) {
            System.out.println("Нет доступных рецептов, заправьте ингридиенты в резервуар!");
            System.out.println();
        }
    }


    //Выводит в консоль данные обо всех рецептах
    public void OutListAllReceipts() {
        System.out.println("Доступность рецептов :");
        for (int i = 0; i <= 7; i++) {
            System.out.print("        " + NameOfCoffee[i] + " - доступно чашек - ");
            System.out.println(Receipts[i][4]);
        }
        System.out.println();

    }

    // Запрос выбора доступного рецепта
    public void InquiryAvaliableReceipt() {
        boolean j = false; // не используется
        int ActRcpt = 0;
        System.out.println("Доступные рецепты :");
        for (int i = 0; i <= 7; i++) {
            if (Receipts[i][4] != 0) {
                System.out.println("   <" + Receipts[i][0] + ">  " + NameOfCoffee[i]);
                ActRcpt++;
            }
        }
        if (ActRcpt != 0) {
            // в Printer
            System.out.println();
            System.out.println("Выберите напиток,введите его номер, и нажмите <Enter> !");
            System.out.println("Нажмите <8> для завершения сеанса работы !"); // завязка на цифру 8 делает код каменным, если ты убираешь или добавляешь кофе
            System.out.println();
            System.out.print(">>");
        } else {
            System.out.println("Нет доступных рецептов, заправьте ингридиенты в резервуар!");
            System.out.println("...");
        }
    }


    // Метод определяет доступность рецепта к изготовлению в данный момент и
    // заносит в массив рецептов в последнюю переменную "статус" - сколько чашек можно приготовить сейчас
    // название метода с маленькой буквы
    public void SetActiveReceipts() {
        int k, w, m = 0; // не говорящие переменные
        for (int i = 0; i <= 7; i++) { // завязка на цифру 7 - "магическое число". нужна переменная
            if (Receipts[i][1] != 0)
                k = (Reservuar[0] / Receipts[i][1]);
            else
                k = 1000; // волшебное число. лучше Integer.MAX_VALUE

            if (Receipts[i][2] != 0)
                w = (Reservuar[1] / Receipts[i][2]);
            else
                w = 1000;

            if (Receipts[i][3] != 0)
                m = (Reservuar[2] / Receipts[i][3]);
            else
                m = 1000;

            if ((k >= 0) & (w >= 0) & (m >= 0)) { // 1 - это условие всегда верное. 2 - "&" побитовая операция, надо использовать && (хоть это и работает)
                // Определяем какого ингредиента меньше всего для рецепта,
                // следовательно, получаем минимальное кол-во готовых чашек
                // Receipts[i][4] = Math.min(Math.min(k,w), m)
                if (k < w)
                    if (k < m)
                        Receipts[i][4] = k; //Заносим минимальное количество готовых чашек в статус рецепта
                    else
                        Receipts[i][4] = m;//Заносим минимальное количество готовых чашек в статус рецепта
                else if (w < m)
                    Receipts[i][4] = w;//Заносим минимальное количество готовых чашек в статус рецепта
                else
                    Receipts[i][4] = m;//Заносим минимальное количество готовых чашек в статус рецепта


            }
        }
    }

    //Приготовление кофе - ДРАФТ!!!!!
    // название метода с маленькой буквы
    public void MakeCoffee(int Receipt_N) {
        Reservuar[0] = Reservuar[0] - Receipts[Receipt_N][1];
        Reservuar[1] = Reservuar[1] - Receipts[Receipt_N][2];
        Reservuar[2] = Reservuar[2] - Receipts[Receipt_N][3];

        System.out.println("");
        System.out.print("Идет приготовление кофе - " + NameOfCoffee[Receipt_N] + " ");
        printer.DotsCooking();
        System.out.println();
        System.out.println("Ваш " + NameOfCoffee[Receipt_N] + " готов!");
        System.out.println();
    }

    //Статус остатков
    // название метода с маленькой буквы
    public boolean TestAvaliabilityIngredients(int Receipt_N) {
//            boolean TestAvaliabilityIngredients = false;
        if (Receipts[Receipt_N][4] != 0)
            return true;
        else
            return false;

        // просто return Receipts[Receipt_N][4] != 0;
    }

    // Не делать кофе , не из чего
    // название метода с маленькой буквы
    // есть класс Printer
    public void DontMakeCoffee() {
        System.out.println();
        System.out.print("ВНИМАНИЕ !");
        System.out.println(" Этот рецепт недоступен, недостаточно ингредиентов.");
        System.out.println("Введите доступный рецепт, или попросите заправить машину.");
        System.out.println();
    }


    //Заправить Кофемашину

    //История состояний
}