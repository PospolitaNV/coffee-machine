package com.github.npospolita;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Coffeemaker CM = new Coffeemaker();
        Printer Pr = new Printer();
        Scanner num = new Scanner(System.in);
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
                try {
                    i = Integer.parseInt(num.nextLine());
                    break;
                } catch (NumberFormatException nfe) {
                    System.out.println("Некорректный ввод !");
                    System.out.println("Введите рецепт из списка ниже или завершите работу с системой набрав <8>.");
                }


            int button = i;

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
                case 8:
                        System.out.println("Всего доброго, надеюсь Вам понравился мой кофе ...");
                    break;
                case 777:
                        System.out.println("Вход в сервисный режим ...");
                    break;
                default: {
                    System.out.println("Некорректный ввод !");
                    System.out.println("Введите рецепт из списка ниже или завершите работу с системой.");
                    System.out.println();
                    CM.InquiryAvaliableReceipt();
                }
            }





        } while (i!= 8);


    }
}

