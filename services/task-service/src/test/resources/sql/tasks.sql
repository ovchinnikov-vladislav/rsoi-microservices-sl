INSERT INTO task (id, name_task, short_description, text_task, template_code, image, complexity, created_at, updated_at, status, id_user)
VALUES (random_uuid(), 'Программа сложения двух чисел',
        'Написать программу, на вход которой подается два числа и происходит их сложение.',
        'Напишите программу, которая складывает два числа, т.е.:\n' ||
         '1. в стандартный поток ввода поступают два числа (a, b).\n' ||
          '2. а в выходной поток поступает их сумма.\n' ||
           'Тестовый пример:\n' ||
            '2 + 3 = 5\n' ||
             '32 + 46 = 78\n',
        'public class App {\n' ||
         '\tpublic static void main(String... args) {\n' ||
          '\t}\n' ||
           '}\n',
           '', 1, CURRENT_DATE(), CURRENT_DATE(), 0, random_uuid()),
           (random_uuid(), 'Программа создания и объявления полей класса',
           'Написать класс, с определенными полями, которые даны по условию.',
           'Напишите класс со следующими полями:\n' ||
            '1. Поле name строкового типа.\n' ||
             '2. Поле age целочисленного типа.\n',
             'public class User {\n' ||
              '}', '', 1, CURRENT_DATE(), CURRENT_DATE(), 0, random_uuid());