import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static String staffFile = "Task_15-5_GetStream\\data\\staff.txt";
    public static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();
        //==============================================================================================
        // Фильтрует сотрудников с зарплатой более 100.000 и распечатывает.
        staff.stream().filter(e -> e.getSalary() > 100_000).forEach(System.out::println);
        //==============================================================================================
        // Создание простого потока данных из чисел, фильтрация по четности и печать.
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        numbers.filter(n -> n % 2 == 0).forEach(System.out::println);
        //==============================================================================================
        // Создание массива чисел, получение потока данных из массива, фильтрация по
        // четности и печать.
        Integer[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Arrays.stream(numbers2).filter(n -> n % 2 == 0).forEach(System.out::println);
        //==============================================================================================
        // Бесконечные стримы потоки данных. Seed инициализатор, а лямбда шаг условие
        Stream.iterate(-1000000, n -> n + 1).forEach(System.out::println);
        // Бесконечные стримы потоки данных.
        // using Stream.generate() method
        // to generate 5 random Integer values
        Stream.generate(new Random()::nextInt).limit(5).forEach(System.out::println);
        //==============================================================================================
        // из строки получаем поток символов, фильтруем на наличие символа "a" и печатаем
        "another text the java is the best program language"
                .chars().filter(c -> c == 'a').forEach(System.out::println);
        //==============================================================================================
    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("    ");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line + " " + fragments.length);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }
}
