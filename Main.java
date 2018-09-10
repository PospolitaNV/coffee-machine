package com.github.npospolita;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Названия всех переменных некорректны
        CoffeeMaker CM = new CoffeeMaker(); // переменные именуются с маленькой буквы
        Printer Pr = new Printer(); // не используется в коде
        Scanner scanner = new Scanner(System.in); // название переменной не отражает суть
        int i = 0;

        CM.startMashine();
        CM.statusMashine();
        CM.SetActiveReceipts();
        CM.InquiryAvaliableReceipt();

        do {
/*
            try {
                i = num.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Некорректный ввод !");
                return;
            }
*/


            while (true)
                // обработка ввода не делается на исключениях. Есть методы сканнера:
                // scanner.hasNextInt(); или scanner.hasNext("паттерн");
                // К тому же у тебя разный текст обработки ошибок при вводе строки и при вводе большого числа
                try {
                    i = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    //Нет разделения ответственности. У тебя же есть класс Printer
                    System.out.println("Некорректный ввод !");
                    System.out.println("Введите рецепт из списка ниже или завершите работу с системой набрав <8>.");
                }


            int button = i; // почему бы не избавиться от "i", она выглядит бесполезной

            switch (button) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    if (CM.TestAvaliabilityIngredients(button)) {
                        CM.MakeCoffee(button);
                        CM.statusMashine();
                        CM.SetActiveReceipts();
                        CM.OutListAllReceipts();
                    }
                    else
                        CM.DontMakeCoffee();
                        CM.InquiryAvaliableReceipt();
                    break;
                // как только добавится 8ой вид кофе - код здесь придётся переписать. и ВЕЗДЕ, где ты пишешь "<8>"
                case 8:
                        System.out.println("Всего доброго, надеюсь Вам понравился мой кофе ...");
                    break;
                case 777:
                        System.out.println("Вход в сервисный режим ...");
                    break;
                default: {
                    //Нет разделения ответственности. У тебя же есть класс Printer
                    System.out.println("Некорректный ввод !");
                    System.out.println("Введите рецепт из списка ниже или завершите работу с системой.");
                    System.out.println();

                    CM.InquiryAvaliableReceipt();
                }
            }





        } while (i != 8);
    }
}

