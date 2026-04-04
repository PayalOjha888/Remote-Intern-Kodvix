import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice_03_04 {
    public static void main(String[] args) {
        //Day 13 (03/04/2026)

        List<Students> students = Arrays.asList(
                new Students("Payal", "Salesforce", 50000, Arrays.asList("Java", "SQL")),
                new Students("Sakshi", "IT", 70000, Arrays.asList("Java", "Spring Boot")),
                new Students("Kanha", "Salesforce", 59500, Arrays.asList("Development", "Python")),
                new Students("Abhinav", "Finance", 57000, Arrays.asList("Excel")),
                new Students("Harsh", "Finance", 55000, Arrays.asList("Accounting", "Excel")),
                new Students("Muskan", "HR", 40000, Arrays.asList("Recruitment", "Communication"))
        );

        //1. Har department mein jiski salary sabse zyada hai, uska sirf naam nikaalo.
        Map<String, String> collect = students.stream().collect(Collectors.toMap(x -> x.getDept(), y -> y.getName(), (x, y) -> x));
        System.out.println(collect);

        List<Integer> list = Arrays.asList(3, 4, 2, 1, 7, 8, 45, 56, 89, 66, 32, 77, 33, 90, 12, 35, 20, 56, 34, 23, 67, 89, 86, 50);

        //2. Stream.iterate ka use karke pehle 10 even numbers ka square nikaalo aur list mein daalo.
        List<Integer> list1 = list.stream().filter(x -> x % 2 == 0).limit(10).map(x -> x * x).toList();
        System.out.println(list1);

        List<Integer> list2 = Stream.iterate(2, x -> x+2).limit(10).map(x -> x*x).toList();
        System.out.println(list2);

        List<Students> studentsList = Arrays.asList(
                new Students("Payal", "Salesforce", 50000, Arrays.asList("Java", "SQL")),
                new Students("Payal", "Salesforce", 50000, Arrays.asList("Java", "SQL")),
                new Students("Sakshi", "IT", 70000, Arrays.asList("Java", "Spring Boot")),
                new Students("Suhani", "IT", 70000, Arrays.asList("Java", "Spring Boot")),
                new Students("Kanha", "Salesforce", 59500, Arrays.asList("Development", "Python")),
                new Students("Kanhaaa", "Salesforce", 59500, Arrays.asList("Development", "Python")),
                new Students("Abhinav", "Finance", 57000, Arrays.asList("Accounting", "Excel")),
                new Students("Harsh", "Finance", 55000, Arrays.asList("Accounting", "Excel")),
                new Students("Muskan", "HR", 40000, Arrays.asList("Recruitment", "Communication")),
                new Students("Muskan", "HR", 40000, Arrays.asList("Recruitment", "Communication"))
        );

        //3. Puri list mein se un names ki list nikaalo jo ek se zyada baar aaye hain.
        Map<String, Long> collect1 = studentsList.stream().collect(Collectors.groupingBy(Students::getName, Collectors.counting()));
        System.out.println(collect1.entrySet().stream().filter(x -> x.getValue()>1).map(Map.Entry::getKey).toList());

        //4. Saare students ki saari skills ko ek single Comma-separated String mein badlo jo Alphabetically sorted ho.
        String collect2 = students.stream().flatMap(x -> x.getSkills().stream()).distinct().sorted().collect(Collectors.joining(","));
        System.out.println(collect2);

        //5. Ek nayi list banao jisme "IT" walo ki salary 10% badh jaye aur baaki sabki 5%, lekin original list change nahi honi chahiye.
        List<Students> it = students.stream().map(x -> {
            double rate = x.getDept().equals("IT") ? 1.10 : 1.05;
            return new Students(x.getName(), x.getDept(), x.getSalary() * rate, x.getSkills());
        }).toList();

        System.out.println(it);

        //6. Poori company mein wo kaunsi skill hai jo sabse zyada employees ke paas hai?
        String string = studentsList.stream().flatMap(x -> x.getSkills().stream()).collect(Collectors.groupingBy(x -> x, Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("No skills");
        System.out.println(string);

        //7. Students ko do groups mein baanto: Wo jinke paas 2 ya 2 se zyada skills hain vs wo jinke paas kam hain.
        Map<Boolean, List<Students>> collect3 = students.stream().collect(Collectors.partitioningBy(x -> x.getSkills().size() >= 2));
        System.out.println(collect3);
    }
}
