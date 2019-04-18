import entities.Employee;
import entities.EmployeeAddress;
import entities.EmployeeDepartments;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class Application {
    public static void main(String[] args) {
//        deleteAll();
//
//        Employee employee1 = new Employee(1, "A", 20);
//        Employee employee2 = new Employee(2, "B", 30);
//        Employee employee3 = new Employee(3, "C", 25);
//
//        create(employee1);
//        create(employee2);
//        create(employee3);
//
//        List<Employee> employeeList = read();
//        employeeList.stream().filter(x -> x.getName().contains("A")).forEach(System.out::println);
//
//        Employee employee4 = new Employee(4,"",35);
//        create(employee4);
//
//        delete(1);

//        EmployeeAddress ea = new EmployeeAddress(1,"Кое-где 15");
//
//        EmployeeDepartments ed = new EmployeeDepartments(1,"ed");
//
//        Employee e1 = new Employee(1,"one",56,ea,ed);
//
//        createEmployeeAddress(ea);
//        createEmployeeDepartments(ed);
//
//        create(e1);

    }

    public static Integer create(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Успешно создан" + e.toString());
        return e.getId();
    }

    public static Integer createEmployeeAddress(EmployeeAddress e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Успешно создан" + e.toString());
        return e.getId();
    }


    public static Integer createEmployeeDepartments(EmployeeDepartments e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("Успешно создан" + e.toString());
        return e.getId();
    }

    public static List<Employee> read() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        @SuppressWarnings("uncheked")
        List<Employee> employees = session.createQuery("FROM Employee").list();
        session.close();
        System.out.println("Найдено " + employees.size() + " сотрудников");
        return employees;

    }

    public static void update(Employee e) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee em = (Employee) session.load(Employee.class, e.getId());
        em.setName(e.getName());
        em.setAge(e.getAge());
        session.getTransaction().commit();
        session.close();
        System.out.println("Успешно изменено " + e.toString());

    }

    public static void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employee e = findByID(id);
        session.delete(e);
        session.getTransaction().commit();
        session.close();
        System.out.println("успешно удалено " + e.toString());
    }

    public static Employee findByID(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee e = (Employee) session.load(Employee.class, id);
        session.close();
        return e;
    }

    public static void deleteAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Employee ");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        System.out.println("Успешно удалены все записи в Employee.");
    }




}
