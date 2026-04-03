import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Practice_27_03 {
    public static void main(String[] args) {
        //Date: 27/03/2026
        //Level: Intermediate

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

        //1. Ek Map<String, Double> nikaalo jo bataye ki har department ki total salary kitni hai.
        Map<String, Double> collect = employees.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.summingDouble(Employee::getSalary)));
        System.out.println(collect);

        //2. Sirf IT department ke employees ki average salary nikaalo.
        Double it = employees.stream().filter(x -> x.getDept().equals("IT")).mapToDouble(Employee::getSalary).average().getAsDouble();
        System.out.println(it);

        //3. Check karo kya list mein koi aisa employee hai jiski salary exactly 60,000 hai?
        // Agar hai, toh uska naam print karo, warna "Not Found".

        String string = employees.stream().filter(x -> x.getSalary() == 60000).findAny().map(Employee::getName).orElse("Not found");
        System.out.println(string);

        //4. Aise kitne employees hain jinke poore naam mein space hai (jaise "Kanha Namdev")?
        long count = employees.stream().filter(x -> x.getName().contains(" ")).count();
        System.out.println(count);

        //5. Employees ko pehle Department ke basis par (Alphabetical) sort karo, aur agar department same ho,
        // toh unhe Salary ke basis par Descending (bade se chota) sort karo.

        List<Employee> list = employees.stream().sorted(Comparator.comparing(Employee::getDept).thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed())).toList();
        System.out.println(list);

        //6. Kya har department mein kam se kam ek aisa employee hai jiski salary 40,000 se zyada hai?

        System.out.println(employees.stream().collect(Collectors.groupingBy(Employee::getDept)).values().stream().allMatch(x -> x.stream().anyMatch(y -> y.getSalary() > 40000)));

        Map<Integer, Integer> map = new HashMap<>(20);

        map.put(1, 2);
        map.put(2, 8);
        map.put(3, 9);
        map.put(4, 7);




        Collection c = new ArrayList(10);
        List<Integer> listt = new LinkedList<>();

        List<Integer> st = new Stack<>();

        List<Integer> v = new Vector<Integer>(2);
        v.add(12);
        v.add(12);
        v.add(12);
        System.out.println();
        System.out.println(v);

        Deque<Integer> dq = new ArrayDeque<>();
        Queue<Integer> pq = new PriorityQueue<>(12);

        Hashtable<Integer, Integer> ht = new Hashtable<>();
        CopyOnWriteArrayList<Integer> arrlist = new CopyOnWriteArrayList<>();
        Iterator<Integer> iterator = listt.iterator();

    }

}
