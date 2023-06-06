import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static final String STAFF_TXT = "AdvancedOOPFeatures_1\\data\\staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        sortBySalaryAndAlphabet(staff);
        staff.forEach(System.out::println);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        //TODO Метод должен отсортировать сотрудников по заработной плате и алфавиту.
        //=============================================================================================
        staff.sort((e1, e2) -> {
            int comparison =
                    e1.getSalary().compareTo(e2.getSalary());
            if (comparison == 0) {                                              // MIDDLE
                return e1.getName().compareTo(e2.getName());
            }
            return comparison;
        });
        //=============================================================================================
        Collections.sort(staff, Comparator.comparing(Employee::getName));       // SHORT
        Collections.sort(staff, Comparator.comparing(Employee::getSalary));
        //=============================================================================================
    }
}