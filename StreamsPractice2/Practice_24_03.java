import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Practice_24_03 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("amit", "bob", "alok", null, "aman", "  ", "ankita");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //Easy Level

        //1. names list mein se null values aur "sirf spaces" (blank strings) wali values ko filter out karo.
        System.out.println(names.stream().filter(Objects::nonNull).filter(x -> !x.trim().isEmpty()).toList());

        //2. nums list ke pehle 5 elements ka sum nikaalo.
        System.out.println(nums.stream().limit(5).mapToInt(x -> x).sum());

        //3. names list (nulls hata kar) mein se check karo kya koi naam "it" par khatam hota hai?
        System.out.println(names.stream().filter(Objects::nonNull).filter(x -> !x.isEmpty()).anyMatch(x -> x.endsWith("it")));

        //4. names list ke har naam ka pehla letter capital karo (e.g., amit -> Amit).
        System.out.println(names.stream().filter(Objects::nonNull).filter(x -> !x.trim().isEmpty()).map(x -> x.substring(0, 1).toUpperCase() + x.substring(1)).toList());

        //5. nums list ke 4th element se lekar 7th element tak ki ek nayi list banao.
        System.out.println(nums.stream().skip(3).limit(4).toList());

        //6. nums list mein se koi bhi ek even number nikaalo jo 5 se bada ho.
        System.out.println(nums.stream().filter(x -> x%2==0 && x>5).findAny().get());

        //7. names list (nulls hata kar) mein total kitni baar 'a' letter aaya hai?
        System.out.println("Output 7 : "+ names.stream().filter(Objects::nonNull).filter(x -> !x.trim().isEmpty()).mapToInt(s -> (int)s.chars().filter(c -> c == 'a').count()).sum());

        //Intermediate level

        List<Employee> employees = Arrays.asList(
                new Employee("Payal", "IT", 50000),
                new Employee("Kanha", "HR", 60000),
                new Employee("Sakshi", "IT", 70000),
                new Employee("Rahul", "Finance", 55000),
                new Employee("Sonia", "HR", 45000),
                new Employee("Harsh", "Sales", 90000),
                new Employee("Purvi", "HR", 55000)
        );

        //8. Employees ko salary buckets mein group karo:
        // "Low" ( < 50k), "Medium" (50k-70k), "High" ( > 70k).

        Map<String, List<Employee>> collect = employees.stream().collect(Collectors.groupingBy(x -> {
            if (x.getSalary() < 50000) return "low";
            if (x.getSalary() <= 70000) return "medium";
            return "high";
        }));

        System.out.println(collect);

        //9. Puri company mein second-highest salary kitni hai?
        System.out.println(employees.stream().sorted((x, y) -> (int) (y.getSalary() - x.getSalary())).skip(1).toList().get(0).getSalary());

        //10. Har department ke employees ke names ki ek List<String>
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.toList()))));

        //11. Check karo kya IT department ke saare employees ki salary 40,000 se zyada hai?
        System.out.println(employees.stream().filter(x -> x.getDept().equals("IT")).allMatch(x -> x.getSalary()>40000));

        //12. Har department ke employees ke names ki average length kya hai?
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingInt(x -> x.getName().length()))));
    }
}

