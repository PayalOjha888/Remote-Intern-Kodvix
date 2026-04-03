import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Practice_02_04 {
    public static void main(String[] args) {

        //Day 12: 02/04/2026

        List<Students> students = Arrays.asList(
                new Students("Payal", "Salesforce", 50000, Arrays.asList("Java", "SQL")),
                new Students("Payal", "Salesforce", 50000, Arrays.asList("Java", "SQL")),
                new Students("Sakshi", "IT", 70000, Arrays.asList("Java", "Spring Boot")),
                new Students("Suhani", "IT", 70000, Arrays.asList("Java", "Spring Boot")),
                new Students("Kanha", "Salesforce", 59500, Arrays.asList("Development", "Python")),
                new Students("Kanhaaa", "Salesforce", 59500, Arrays.asList("Development", "Python")),
                new Students("Abhinav", "Finance", 57000, Arrays.asList("Accounting", "Excel")),
                new Students("Harsh", "Finance", 55000, Arrays.asList("Accounting", "Excel")),
                new Students("Muskan", "HR", 40000, Arrays.asList("Recruitment", "Communication"))
        );

        //1. Check karo ki poori company mein aise kitne unique employees hain jinke paas "Java" skill hai?
        long count = students.stream().filter(x -> x.getSkills().contains("Java")).distinct().count();
        System.out.println(count);

        //2. Saare employees ki saari skills mein se wo ek skill nikaalo jiski length sabse zyada hai
        String string = students.stream().flatMap(x -> x.getSkills().stream()).max(Comparator.comparingInt(String::length)).get();
        System.out.println(string);

        //3.Ek Map<Character, List<String>> banao jahan key naam ka Pehla Akshar (Initial) ho
        // aur value un names ki list. (e.g., 'P' -> ["Payal", "Pankaj"]).

        Map<Character, List<String>> collect = students.stream().collect(Collectors.groupingBy(x -> x.getName().charAt(0), Collectors.mapping(Students::getName, Collectors.toList())));
        System.out.println(collect);

        //4. Employees ko 3 categories mein baanto:
        //"Low" (Salary < 50k)
        //"Medium" (50k - 70k)
        //"High" (Salary > 70k)

        Map<String, List<Students>> collect1 = students.stream().collect(Collectors.groupingBy(x ->
                {
                    if (x.getSalary() < 50000) return "low";
                    if (x.getSalary() > 70000) return "High";
                    return "Medium";
                }
        ));

        System.out.println(collect1);

        //5. Wo Department Name nikaalo jisme sabse zyada employees kaam karte hain.

        String key = students.stream().collect(Collectors.groupingBy(Students::getDept, Collectors.counting())).entrySet().stream().max((x, y) -> (int) (x.getValue() - y.getValue())).get().getKey();
        System.out.println(key);

        //6. "HR" department ko chhod kar, baaki saari company ki Average Salary kya hai?
        Double hr = students.stream().filter(x -> !x.getDept().equals("HR")).map(Students::getSalary).collect(Collectors.averagingDouble(x -> x));
        System.out.println(hr);

        //7. Check karo kya har department mein kam se kam 2 employees hain?

        boolean b = students.stream().collect(Collectors.groupingBy(Students::getDept, Collectors.counting())).entrySet().stream().map(x -> x.getValue()).allMatch(x -> x >= 2);
        System.out.println(b);
    }
}
