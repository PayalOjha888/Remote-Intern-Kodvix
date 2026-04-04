import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Practice_04_04 {
    public static void main(String[] args) {
        //Day 14: 04/04/2026

        List<Students> students = Arrays.asList(
                new Students("Payal", "Salesforce", 50000, Arrays.asList("Java", "SQL")),
                new Students("Sakshi", "IT", 40000, Arrays.asList("Java", "SQL", "Spring Boot")),
                new Students("Kanha", "Salesforce", 59500, Arrays.asList("Development", "SQL", "Python")),
                new Students("Abhinav", "Finance", 55000, Arrays.asList("Excel")),
                new Students("Harsh", "Finance", 57000, Arrays.asList("Accounting", "Excel")),
                new Students("Muskan", "HR", 40000, Arrays.asList("Recruitment", "Communication"))
        );

        //1. Har department mein jiski salary sabse zyada hai, uska sirf naam nikaalo.
        Map<String, String> collect = students.stream().collect(Collectors.toMap(Students::getDept,
                Students::getName,
                (oldName, newName) -> {
                    double s1 = students.stream().filter(x -> x.getName().equals(oldName)).findFirst().get().getSalary();
                    double s2 = students.stream().filter(x -> x.getName().equals(newName)).findFirst().get().getSalary();
                    return s1>s2 ? oldName : newName;
                }));
        System.out.println(collect);

        //2. Ek bahut badi list (LongStream.rangeClosed(1, 1000000)) ka sum nikaalo.
        // Pehle normal stream se aur phir parallelStream() se.
        // Dono ka Time Difference (System.currentTimeMillis()) check karke batao.
        long t1 = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, 1000000000).sum();
        long t2 = System.currentTimeMillis();
        long sum1 = LongStream.rangeClosed(1, 100000000).parallel().sum();
        long t3 = System.currentTimeMillis();
        System.out.println("time of stream op : "+(t2-t1)+" time of parallel stream op: "+(t3-t2));

        //3. Saare students ki saari skills ko ek single string mein badlo,
        // par dhyan rahe ki skills unique hon aur unke beech mein hyphen (-) ho.

        String collect1 = students.stream().flatMap(s -> s.getSkills().stream()).distinct().collect(Collectors.joining("-"));
        System.out.println(collect1);

        //4. Kya koi aisi salary amount hai jo ek se zyada employees ko mil rahi hai? Agar haan, toh wo amounts ki list nikaalo.

        List<Double> list = students.stream().collect(Collectors.groupingBy(Students::getSalary, Collectors.counting())).entrySet().stream().map(x -> x.getValue() > 1 ? x.getKey() : -1).filter(x -> x != -1).toList();
        System.out.println(list);

        //5. Sirf un students ki Average Salary nikaalo jinke paas "SQL" skill hai.
        // (Agar koi na ho toh 0.0 return karein).

        double sql = students.stream().filter(x -> x.getSkills().contains("SQL")).mapToDouble(x -> x.getSalary()).average().orElse(0.0);
        System.out.println(sql);

        //6. Ek Map<String, Integer> banao jahan key Department ho aur
        // value us dept ke saare employees ki Total Number of Skills (Sum) ho.

        Map<String, Integer> collect2 = students.stream().collect(Collectors.groupingBy(Students::getDept, Collectors.summingInt(s -> s.getSkills().size())));
        System.out.println(collect2);

        //7. Ek nayi class StudentDTO (sirf name aur dept) ki list banao purani Students list se,
        // lekin sirf unki jinki salary 55,000 se upar hai.

        List<StudentDTO> list1 = students.stream().filter(x -> x.getSalary() > 55000).map(s -> new StudentDTO(s.getName(), s.getDept())).toList();
        list1.forEach(x -> System.out.println(x.toString()));
        
    }
}
