INSERT INTO task (name_task, short_description, text_task, template_code, image, complexity, create_date, id_user)
VALUES ('Программа сложения двух чисел',
        'Написать программу, на вход которой подается два числа и происходит их сложение.',
        'Напишите программу, которая складывает два числа, т.е.:
1. в стандартный поток ввода System.in поступают два числа (a, b).
2. в выходной поток ввода System.out поступает их сумма.

Тестовый пример:
\ввод:
2
3
\вывод:
5',
        'public class App {

    public static void main(String... args) {

    }

}',
           '', 1, CURRENT_DATE(), 1),
           ('Программа создания и объявления полей класса',
           'Написать класс, с определенными полями, которые даны по условию.',
           'Напишите класс со следующими полями:
1. Поле name строкового типа.
2. Поле age целочисленного типа.',
             'public class User {

}', '', 1, CURRENT_DATE(), 1);