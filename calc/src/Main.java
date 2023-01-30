import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи пример для вычистения");
        String input = scanner.nextLine();  //Данные, которые ввел пользователь
        calc(input);}


    public static String calc (String input) throws Exceptions {
        String[] arab = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}; //массив арабских символов
        String[] rim = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; //массив римских символов
        String[] operatorsArr = new String[]{"+", "-", "*", "/"}; //массив операторов
        int result = 0; //переменная результата
        int resultRim = 0; //вспомогательная переменная для конвертации в римские
        int num1 = 0; //переменная 1го числа
        int num2 = 0; //переменная 2го числа
        String [] calcValueArr = input.split(" "); //Делим на 3 слогаемых
        if (calcValueArr.length != 3) {
            throw new Exceptions("Строка не соответствует типу a + b, a - b, a * b или a / b ");}
        //проверка на правильность написания и количество символов//
        boolean isArab = Arrays.stream(arab).anyMatch(x -> x.equals(calcValueArr[0])) && Arrays.stream(arab).anyMatch(x -> x.equals(calcValueArr[2]));
        //проверка на арабские цифры
        boolean isOperator = Arrays.stream(operatorsArr).anyMatch(x -> x.equals(calcValueArr[1]));
        //проверка на оператор
        boolean isRim = Arrays.stream(rim).anyMatch(x -> x.equals(calcValueArr[0].toUpperCase())) && Arrays.stream(rim).anyMatch(x -> x.equals(calcValueArr[2].toUpperCase()));
        //проверка на римские цифры
        //присваивание арабского числа
        if (isArab) {
            num1 = Integer.parseInt(calcValueArr[0]); //Конвертируем 1 число из строки в цифру
            num2 = Integer.parseInt(calcValueArr[2]); //Конвертируем 2 число из строки в цифру
        }

        //присваиваивание римского числа
        if (isRim) {
            Rimsk rNum1 = Rimsk.valueOf(calcValueArr[0].toUpperCase()); //приводим значение1 к верхнему регистру
            Rimsk rNum2 = Rimsk.valueOf(calcValueArr[2].toUpperCase()); //приводим значение2 к верхнему регистру
            num1 = rNum1.getRimNum(); //получаем число1 из Энама
            num2 = rNum2.getRimNum(); //получаем число2 из Энама
        }

        //проверка соответсвия систем, целочисленности и диапазона
        if (!isArab && !isRim) {
            throw new Exceptions("Числа введены в разных системах исчисления или не соответствуют диапазону 1-10");
        }

        //проверка на оператор и длину (кол-во) слогаемых
        if ((isArab || isRim) && isOperator) {

            //конвертация символа в оператор вычисления
            switch (calcValueArr[1]) {
                case "+" :
                    result = num1 + num2;
                    break;
                case "-" :
                    result = num1 - num2;
                    break;
                case "*" :
                    result = num1 * num2;
                    break;
                case "/" :
                    result = num1 / num2;
                    break;
            }

            if(isArab) {System.out.println(result);} //вывод арабского результата
            if(isRim) {
                resultRim = result;
                int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};// массив больших римских чисел на арабском
                String[] romanLetters = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; // обозначения на римском
                StringBuilder roman = new StringBuilder();
                for (int i = 0; i < values.length; i++) {
                    while (resultRim >= values[i]) {
                        resultRim = resultRim - values[i];
                        roman.append(romanLetters[i]);
                    }
                }
                if (result > 0) {
                    System.out.println(roman);
                } else {
                    throw new Exceptions("В римской системе нет отрицательных чисел");
                }
            }
        } else {throw new Exceptions("В строке недопустимый оператор");
        } return "";}
}
