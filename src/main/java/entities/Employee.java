package entities;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @OneToOne
    @JoinColumn(name = "id_address")
    private EmployeeAddress employeeAddress;

    @ManyToOne
    @JoinColumn(name = "id_departments")
    private EmployeeDepartments employeeDepartments;

    @ManyToOne
    @JoinColumn(name = "id_country")
    private EmployeeCountry employeeCountry;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer age, EmployeeAddress employeeAddress, EmployeeDepartments employeeDepartments, EmployeeCountry employeeCountry) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.employeeAddress = employeeAddress;
        this.employeeDepartments = employeeDepartments;
        this.employeeCountry = employeeCountry;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public EmployeeDepartments getEmployeeDepartments() {
        return employeeDepartments;
    }

    public void setEmployeeDepartments(EmployeeDepartments employeeDepartments) {
        this.employeeDepartments = employeeDepartments;
    }

    public EmployeeCountry getEmployeeCountry() {
        return employeeCountry;
    }

    public void setEmployeeCountry(EmployeeCountry employeeCountry) {
        this.employeeCountry = employeeCountry;
    }

    @Override
    public String toString() {
        return "Сотрудник: " + this.id + ", " + this.name + ", " + this.age;
    }

}
