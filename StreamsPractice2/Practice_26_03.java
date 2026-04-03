import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Practice_26_03 {
    public static void main(String[] args) {
        //26/03/2026

        List<Employee> employees = Arrays.asList(
                new Employee("Payal", "IT", 50000),
                new Employee("Kanha Namdev", "HR", 60000),
                new Employee("Sakshi", "IT", 70000),
                new Employee("Sonia", "HR", 45000),
                new Employee("Rahul", "Finance", 55000),
                new Employee("Harsh", "Sales", 90000),
                new Employee("Purvi", "HR", 60000),
                new Employee("Shivam", "IT", 70000),
                new Employee("Aman", "IT", 30000)
        );

        //1. IT aur HR department ke un employees ki list nikaalo jinki salary 55,000 se zyada hai

        List<Employee> list = employees.stream().filter(x -> Objects.equals(x.getDept(), "HR") || Objects.equals(x.getDept(), "IT")).filter(x -> x.getSalary() > 55000).toList();
        System.out.println(list);

        //2. Employees ko pehle Salary ke basis par (Descending) sort karo, aur agar salary same ho,
        // toh unhe Name ke basis par (Alphabetical) sort karo.

        List<Employee> list1 = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed().thenComparing(Employee::getName)).toList();
        System.out.println(list1);

        //3. IT department ka wo employee nikaalo jiska naam 'S' se start hota ho.
        // Agar koi na mile, toh ek "Default Employee" return karo.

        Employee employee = employees.stream().filter(x -> x.getDept().equals("IT") && x.getName().startsWith("S")).findFirst().orElse(new Employee("Default Employee", "None", 0.0));
        System.out.println(employee);

        //4. Saare "HR" department ke employees ki salary 10% badha kar unki
        // updated salaries ki ek nayi list nikaalo.

        List<Double> hr = employees.stream().filter(x -> x.getDept().equals("HR")).map(x -> (x.getSalary() / 10) + x.getSalary()).toList();
        System.out.println(hr);

        //5. Puri list mein jis employee ka naam sabse lamba (lengthiest) hai, uska sirf Naam print karo.

        String employee1 = employees.stream().max(Comparator.comparingInt(x -> x.getName().length())).get().getName();
        System.out.println(employee1);


    }
}
