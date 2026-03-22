import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Practice {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(34, 57, 89, 23, 12, 90, 44, 68);

        //Day 1 (13/03/2026)
        //1.
        System.out.println(list.stream().filter(x -> x%2==0).toList());

        //2.
        System.out.println(list.stream().map(x -> x*x).toList());

        //3.
        System.out.println(list.stream().reduce((x, y) -> (x+y)).get());

        //4.
        System.out.println(list.stream().mapToInt(x -> x).max().getAsInt());

        //5.
        System.out.println(list.stream().count());

        List<String> list1 = Arrays.asList("Payal", "Kanha", "Sakshi");
        //6.
        list1.stream().forEach(x -> System.out.println(x.toUpperCase()));

        //Day 2 (16/03/2026)
        List<Integer> nums = Arrays.asList(10, 15, 8, 25, 35, 120, 98, 32, 10, 15);
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "eggplant");

        //7. Find distinct numbers in the given list
        System.out.println(nums.stream().distinct().toList());

        //8. Sort the list
        System.out.println(nums.stream().sorted().toList());

        //9. check iif the list contains any number greater than 100
        System.out.println(nums.stream().anyMatch(x -> x>100));

        //10. Find first element which is greater than 20
        System.out.println(nums.stream().filter(x -> x>20).findFirst().get());

        //11. words wali list ke har word ki length nikaalo aur un lengths ki ek nayi list banao (e.g., "apple" -> 5).
        System.out.println(words.stream().map(x -> x.length()).collect(Collectors.toList()));

        //12. Skip the first 2 numbers and print next 3 numbers
        System.out.println(nums.stream().skip(2).limit(3).toList());

        //Day 3 (17/03/2026)

        List<Integer> prices = Arrays.asList(150, 200, 350, 400, 90, 120, 180, 450);
        List<String> fruits = Arrays.asList("Mango", "Apple", "Kiwi", "Banana", "Pineapple");

        //13.Check karo kya list ke saare prices 50 se bade hain?
        System.out.println(prices.stream().allMatch(x -> x>50));

        //14. Check karo kya list mein koi bhi fruit "Z" se start hota hai?
        System.out.println(fruits.stream().noneMatch(x -> x.startsWith("Z")));

        //15. prices wali list mein se sabse sasta (minimum) price find karo.
        System.out.println(prices.stream().min((x, y) -> x-y).get());

        //16. Aise kitne fruits hain jinke naam ki length 5 se zyada hai?
        System.out.println(fruits.stream().filter(x -> x.length()>5).count());

        //17. fruits wali list ke saare naam ko ek single String mein join karo, beech mein ek space " " dekar.
        System.out.println(fruits.stream().collect(Collectors.joining(" ")));

        //18. List mein se koi bhi ek element nikaalo jo 200 se bada ho
        System.out.println(prices.stream().filter(x -> x>200).findAny().get());

        //below queries were applied on an Employee class contains name, dept and salary
        List<Employee> employees = Arrays.asList(
                new Employee("Payal", "IT", 50000),
                new Employee("Kanha", "HR", 60000),
                new Employee("Sakshi", "IT", 70000),
                new Employee("Rahul", "Finance", 55000),
                new Employee("Sonia", "HR", 45000),
                new Employee("Harsh", "Sales", 90000),
                new Employee("Purvi", "HR", 55000)
        );
        //19. Saare employees ko unke Department ke basis par group karo.Iska output ek Map<String, List<Employee>> hoga.

        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept)));

        //20. Sirf "IT" department ke employees ki total salary calculate karo.

        System.out.println("Query no. 20: "+employees.stream().filter(x -> x.getDept().equals("IT")).mapToDouble(x-> x.getSalary()).sum());

        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );

        //21. Tumhare paas list ki list hai.
        // Isse flat karke ek single List<Integer> banao: [1, 2, 3, 4, 5, 6].

        System.out.println(nestedList.stream().flatMap(x -> x.stream()).toList());

        //22. Puri company mein sabse zyada salary kiski hai? Wo Employee object find karo.
        Employee employee = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(employee.toString());

        //23. Saare employees ke names ki ek list banao jo 'S' se start hote hain aur unhe alphabetical order mein sort karo.

        System.out.println("23 : "+employees.stream().map(x->x.getName()).filter(x -> x.startsWith("S")).sorted().toList());

        //Day 4 (18/03/2026)

        //Level : Easy
        List<Integer> marks = Arrays.asList(45, 78, 88, 32, 56, 90, 25, 67, 88);
        List<String> cities = Arrays.asList("Bhopal", "Mumbai", "Delhi", "Bangalore", "Tiruvanathpuram", "Pune", "Indore");

        //1. marks list mein se un numbers ko filter karo jo 33 se zyada hain aur unhe sort karke ek list mein store karo.

        System.out.println(marks.stream().filter(x -> x>33).sorted().toList());

        //2. cities list ke har city ka sirf pehla character nikaalo aur un characters ki ek nayi list banao

        System.out.println(cities.stream().map(x -> x.substring(0, 1)).toList());

        //3. Kya list mein koi bhi mark 18 se kam hai?

        System.out.println(marks.stream().anyMatch(x -> x<18));

        //4. Puri list ka Sum calculate karo

        System.out.println(marks.stream().reduce(Integer::sum).get());

        //5. cities list mein se wo city find karo jiski length sabse zyada hai.

        System.out.println(cities.stream().max(Comparator.comparingInt(String::length)).get());

        //6. Check karo kya list mein "Delhi" present hai?
        System.out.println(cities.stream().anyMatch(x -> x.equals("Delhi")));

        //7. Sirf odd numbers ko filter karo, unka square nikaalo, aur result print karo.

        System.out.println(marks.stream().filter(x -> x%2!=0).map(x -> x*x).toList());

        //8. Saare marks ka Average nikaalo

        System.out.println(marks.stream().mapToInt(x -> x).average().getAsDouble());

        // Intermediate level

        //9. Saare employees ki list mein se unique department names ki ek list nikaalo.
        System.out.println(employees.stream().map(x -> x.getDept()).distinct().toList());

        //10. Aise kitne employees hain jinki salary 50,000 se zyada hai aur wo "HR" department mein hain?
        System.out.println(employees.stream().filter(x -> x.getSalary()>50000).filter(x -> x.getDept().equals("HR")).count());

        //11. IT department ke sabse pehle employee ka Naam (Name) nikaalo aur use Uppercase mein convert karke return karo.
        System.out.println(employees.stream().filter(x -> x.getDept().equals("IT")).findFirst().map(x -> x.getName().toUpperCase()).get());

        //Day 5 (19/03/2026)

//        List<Employee> employees = Arrays.asList(
//                new Employee("Payal", "IT", 50000),
//                new Employee("Kanha", "HR", 60000),
//                new Employee("Sakshi", "IT", 70000),
//                new Employee("Rahul", "Finance", 55000),
//                new Employee("Sonia", "HR", 45000),
//                new Employee("Harsh", "Sales", 90000),
//                new Employee("Purvi", "HR", 55000)
//        );

        //1. Ek Map<String, Long> nikaalo jo bataye ki har department mein kitne employees hain.

        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting())));

        //2. Employees ko do groups mein baanto: ek jinki salary 60,000 se zyada hai aur ek jinki kam hai.
        System.out.println(employees.stream().collect(Collectors.partitioningBy(x -> x.getSalary()>60000)));

        //3. Har department ki average salary nikaalo

        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.averagingInt(x -> (int)x.getSalary()))));

        //4. Har department mein sabse zyada salary wala employee kaun hai?
        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.maxBy((x, y)->(int)(x.getSalary()-y.getSalary())))));

        //5. Ek Map<String, String> nikaalo jahan key "Department" ho aur value us dept ke saare employees ke names hon, comma (,) se separated.

        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName, Collectors.joining(",")))));

        //6. Har department ki total salary ka map nikaalo

        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.summingDouble(Employee::getSalary))));

        //7. Har department mein sabse zyada salary wale ka naam kya hai?

        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), e -> e.map(Employee::getName).orElse("N/A")))));

//        String s1 = "Java8";
//        String s2 = "Java";
//        String s3 = s2 + 8;
//        System.out.println(s1 == s3);
//
//        Thread t1 = new Thread();
//
//        System.out.println(t1.getState());
//
//        String s4 = new String("78");
//        Employee e = new Employee("Anita", "IT", 34000);
//        System.out.println(e.hashCode());
//        Employee clone = e.clone();
//        Employee e1 = clone;
//        System.out.println(clone);

        //Day 6 (20/03/2026)
        List<String> items = Arrays.asList("Pen", "Notebook", "Eraser", "Notebook", "Pen", "Marker", "Sharpener");
        List<Integer> ages = Arrays.asList(12, 18, 25, 40, 15, 60, 18, 22);
        List<String> codes = Arrays.asList("java8", "python3", "js", "cpp11", "go");

        //1.items list mein kitne unique elements hain?
        System.out.println(items.stream().distinct().count());

        //2. ages list mein se check karo kya saare log 10 se bade hain?
        System.out.println(ages.stream().allMatch(x -> x>10));

        //3. codes list mein se wo elements nikaalo jinki length 3 se kam ya barabar (<= 3) hai.
        System.out.println(codes.stream().filter(x -> x.length()<=3).toList());

        //4. ages list ko Descending Order (bade se chota) mein sort karke print karo.
        System.out.println(ages.stream().sorted((x, y) -> (y-x)).toList());

        //5. ages list ke pehle 3 elements ko uthao aur unka product (multiplication) nikaalo.
        System.out.println(ages.stream().limit(3).reduce((x, y) -> (x*y)).get());

        //6. codes list ko ek Map<String, Integer> mein badlo jahan key "Code" ho aur value uski "Length".
        System.out.println(codes.stream().collect(Collectors.toMap(x->x, x->x.length())));

        //7. items list ka last element find karo.
        System.out.println(items.stream().skip(items.size()-1).toList().get(0));

        //8. codes list mein se un strings ko filter karo jo vowel (a, e, i, o, u) se start hoti hain.
        System.out.println(codes.stream().filter(x -> x.startsWith("a") || x.startsWith("e") || x.startsWith("i") || x.startsWith("o") || x.startsWith("u")).toList());

        //9. ages list mein se even numbers ka sum aur odd numbers ka sum nikaalo
        System.out.println(ages.stream().collect(Collectors.partitioningBy(x -> x%2==0, Collectors.summingInt(x -> x))));

        //10. items list mein har item kitni baar repeat ho raha hai?
        System.out.println(items.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting())));

        //11. codes list ke har element ko check karo, agar wo "java8" hai toh use "Java 17" se replace karo, warna waisa hi rehne do.
        List<String> ans = codes.stream().map(x ->
        {
            if (x.equals("java8")) {
                return x.replaceAll("java8", "Java17");
            } else {
                return x;
            }
        }).toList();
        System.out.println(ans);

        //12. codes list ke saare elements ko join karo comma se, lekin starting mein [ aur end mein ] hona chahiye.
        System.out.println(codes.stream().collect(Collectors.joining(",", "[", "]")));
    }
}
