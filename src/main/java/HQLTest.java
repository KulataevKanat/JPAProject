import entities.Employee;
import entities.EmployeeCountry;
import entities.EmployeeDepartments;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class HQLTest {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
/*for(Employee employee : a18(18,"A%")){
           System.out.println(employee.getName() + " " + employee.getAge());
       }

        session.close();*/

        Employee e1 = new Employee(1, "Alisher", 52, null, null, null);
        Employee e2 = new Employee(2, "Alex", 42, null, null, null);
        Employee e3 = new Employee(3, "Kanat", 17, null, null, null);
        Employee e4 = new Employee(4, "Bektur", 24, null, null, null);


        EmployeeDepartments d1 = new EmployeeDepartments(1, "IT");
        EmployeeDepartments d2 = new EmployeeDepartments(2, "Marketing");

        EmployeeCountry c1 = new EmployeeCountry(1, "KG");
        EmployeeCountry c2 = new EmployeeCountry(2, "KZ");
        EmployeeCountry c3 = new EmployeeCountry(3, "US");

        session.beginTransaction();

        session.saveOrUpdate(e1);
        session.saveOrUpdate(e2);
        session.saveOrUpdate(e3);
        session.saveOrUpdate(e4);

        session.saveOrUpdate(d1);
        session.saveOrUpdate(d2);

        session.saveOrUpdate(c1);
        session.saveOrUpdate(c2);
        session.saveOrUpdate(c3);

        session.getTransaction().commit();

        /*EmployeeCountry employeeCountry = (EmployeeCountry)*/
        session.createQuery("select e from Employee e " +
                "where e.employeeDepartments.name = :dep and e.employeeCountry.name = :cnt ")
                .setParameter("dep", "IT")
                .setParameter("cnt", "KG")
                .list();

        session.close();
    }

    public static List<Employee> a18(int age, String firstLetter) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> employees = session.createQuery("select e from Employee e " +
                "where age >= :p_age and name like :p_first ")
                .setParameter("p_age", age)
                .setParameter("p_first", firstLetter).list();

        session.close();

        return employees;

    }

    public static void getLoad() {
        Session session =
                HibernateUtil.getSessionFactory().openSession();


        Employee e = new Employee(1, "One", 52, null, null, null);
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();

        System.out.println("before load");
        Employee employee =
                (Employee) session.load(Employee.class, 1);
        System.out.println("after load");
        System.out.println(employee.getAge() + " " + employee.getName());

        System.out.println("before get");
        Employee employeeG =
                (Employee) session.get(Employee.class, 1);
        System.out.println("after get");
        System.out.println(employeeG.getAge() + " " + employeeG.getName());

        session.close();
        System.out.println("Успешно создан " + e.toString());
    }


}
