package com.github.npospolita;

public class Printer {

    // имя метода с маленькой буквы
    // можно использовать этот класс как "утильный", методы статические - класс через конструктор не создаём.
    public void DotsCooking ()

    {
        // есть цикл for. Код ниже можно параметризировать 1 - задержкой в мс, 2 - кол-вом точек.
        System.out.print(".");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        System.out.print(".");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        System.out.print(".");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        System.out.print(".");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        System.out.println(".");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
    }
}
