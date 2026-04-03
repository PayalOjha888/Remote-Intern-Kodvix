import java.util.*;
import java.util.stream.Collectors;

public class Practice_31_03 {
    public static void main(String[] args) {
        //Day 10: 31/03/2026

        List<Students> students = Arrays.asList(
                new Students("Payal", "IT", 50000, Arrays.asList("Java", "SQL")),
                new Students("Sakshi", "IT", 70000, Arrays.asList("Java", "Spring Boot")),
                new Students("Kanha", "HR", 60000, Arrays.asList("Recruitment", "Communication")),
                new Students("Rahul", "Finance", 57000, Arrays.asList("Accounting", "Excel")),
                new Students("Rahul", "Finance", 55000, Arrays.asList("Accounting", "Excel"))
        );

        //1. Saare students ki unique skills ki list nikalo
        List<String> list = students.stream().flatMap(x -> x.getSkills().stream()).distinct().toList();
        System.out.println(list);

        //2. Kaunse length ke kitne naam hain?
        Map<Integer, Long> collect = students.stream().collect(Collectors.groupingBy(x -> x.getName().length(), Collectors.counting()));
        System.out.println(collect);

        //3. 50k se upar aur niche walo ke Sirf Names ka partition karo.
        Map<Boolean, List<String>> collect1 = students.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 50000, Collectors.mapping(Students::getName, Collectors.toList())));
        System.out.println(collect1);

        //4. Sabse chote naam wala Employee object.
        Students students1 = students.stream().min(Comparator.comparingInt(x -> x.getName().length())).get();
        System.out.println(students1); //my Output: Payal student object

        //5. Un logo ko filter karo jinke paas "Java" skill hai.
        List<Students> java = students.stream().filter(x -> x.skills.contains("Java")).toList();
        System.out.println(java);

        //6. Ek Map banao Name -> Salary, agar name duplicate ho toh purani salary rakho
        Map<String, Double> collect2 = students.stream().collect(Collectors.toMap(
                Students::getName,
                Students::getSalary,
                (x, y) -> x
        ));
        System.out.println(collect2);

        List<Department> depts = Arrays.asList(
                new Department("IT", Arrays.asList("Cloud App", "Security Tool", "AI Chatbot")),
                new Department("HR", Arrays.asList("Payroll System", "Recruitment Portal")),
                new Department("Finance", Arrays.asList("Tax Calculator", "Payroll System")) // Duplicate Project!
        );

        Company myCompany = new Company("TechCorp", depts);

        /*
        Aapko myCompany object ka use karke ek aisi Stream query likhni hai jo:

        Saare Departments ki list nikaale (getDepartments().stream()).

        flatMap ka use karke unke saare Projects ko ek single stream mein le aaye.

        Saare project names ko Uppercase mein badal de.

        Duplicates hata de (jaise "Payroll System" do baar hai).

        Unhe Alphabetically sort kare.
        */

        List<String> list1 = depts.stream().flatMap(x -> x.getProjects().stream()).map(x -> x.toUpperCase()).distinct().sorted().toList();
        System.out.println(list1);
    }
}
