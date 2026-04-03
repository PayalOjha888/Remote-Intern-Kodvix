import java.util.List;

public class Students {
    private String name;
    private String dept;
    private double salary;
    List<String> skills;

    public Students(String name, String dept, double salary, List<String> skills) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", skills=" + skills +
                '}';
    }
}
