package homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Напишите программу, которая использует Stream API для обработки списка чисел.
//Программа должна вывести на экран среднее значение всех четных чисел в списке.
public class Average {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = new ArrayList<>(Arrays.asList(1, 2, 4, 5 ,6, 8));
        System.out.println(findAverage(listOfNumbers));
    }

    public static double findAverage(List<Integer> numbers){
        return numbers.stream()
                .filter(number -> number % 2 ==0)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
    }
}
