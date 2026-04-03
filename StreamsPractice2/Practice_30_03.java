import java.util.*;
import java.util.stream.Collectors;

public class Practice_30_03 {
    public static void main(String[] args) {
        // Day 9: 30/03/2026

        List<Employee> employees = Arrays.asList(
                new Employee("Payal Ojha", "IT", 50000),
                new Employee("Kanha Namdev", "HR", 60000),
                new Employee("Sakshi Soni", "IT", 70000),
                new Employee("Sonia Gandhi", "HR", 45000),
                new Employee("Rahul Gandhi", "Finance", 55000),
                new Employee("Harsh Ojha", "Sales", 90000),
                new Employee("Purvi", "HR", 60000),
                new Employee("Shivam", "IT", 70000),
                new Employee("Aman", "IT", 30000)
        );

        //1. Puri company mein wo top 3 employees nikaalo jinki salary sabse zyada hai.
        List<Employee> list = employees.stream().sorted((x, y) -> (int) (y.getSalary() - x.getSalary())).limit(3).toList();
        System.out.println(list);

        //2. Ek Set<String> nikaalo jisme sirf saare unique department names hon.
        Set<String> collect = employees.stream().collect(Collectors.groupingBy(Employee::getDept)).keySet();
        System.out.println(collect);

        //3."IT" department ke saare employees ke naam ek saath join karo, beech mein " & " hona chahiye.
        String it = employees.stream().filter(x -> x.getDept().equals("IT")).map(Employee::getName).collect(Collectors.joining(" & "));
        System.out.println(it);

        //4. Ek hi stream mein puri company ki Max, Min, Sum, aur Average salary nikaalo.
        DoubleSummaryStatistics doubleSummaryStatistics = employees.stream().mapToDouble(Employee::getSalary).summaryStatistics();
        System.out.println("Average = " + doubleSummaryStatistics.getAverage() +
                ", Max = " + doubleSummaryStatistics.getMax() +
                ", Min = " + doubleSummaryStatistics.getMin() +
                ", Sum = " + doubleSummaryStatistics.getSum() );

        //5. Un employees ki list nikaalo jinka naam kisi Vowel (A, E, I, O, U) se start hota ho.
        List<Employee> list1 = employees.stream().filter(x -> "AEIOUaeiou".contains(x.getName().substring(0, 1))).toList();
        System.out.println(list1);

        //6. Check karo kya list mein Finance department ka koi employee hai?
        boolean finance = employees.stream().anyMatch(x -> x.getDept().equals("Finance"));
        System.out.println(finance);

    }
}
