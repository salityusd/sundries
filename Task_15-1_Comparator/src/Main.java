import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static String staffFile = "Task_15-1_Comparator\\data\\staff.txt";
    public static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();
        //==============================================================================================
        Collections.sort(staff, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSalary().compareTo(o2.getSalary());            // LONG
            }
        });
        //==============================================================================================
        Collections.sort(staff, ((o1, o2) -> o1.getSalary().compareTo(o2.getSalary())));    // MIDDLE
        //==============================================================================================
        Collections.sort(staff, Comparator.comparing(Employee::getSalary));     // SHORT
        //==============================================================================================

        staff.forEach(System.out::println);
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
