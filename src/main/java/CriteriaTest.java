import CriteriaAPI.entities.Country;
import CriteriaAPI.entities.Department;
import CriteriaAPI.entities.Employee;
import com.sun.org.apache.bcel.internal.generic.Select;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class CriteriaTest {
    public static void main(String[] args) {
//        multipleSelectJPQL();
//        innerJoin();
//        fetchJoin();
//        criteriaTask();
//        criterialJoin();
//        criterialGroupBy();
    }

    public static void multipleSelectJPQL() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select name, country.name, age " + "from Employee ");
        List<Object[]> objects = query.getResultList();
        for (Object[] o : objects) {
            System.out.println(o[0] + " " + o[1] + " " + o[2]);
        }
        session.close();
        HibernateUtil.shutdown();
    }

    public static void innerJoin() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(
                "select e from Employee e join e.department");
        List<Employee> employeeList = query.getResultList();
        for (Employee d : employeeList) {
            System.out.println(d.getId() + " " + d.getName() + " " + d.getDepartment().getName());
        }

        session.close();
        HibernateUtil.shutdown();
    }

    public static void fetchJoin() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select e from Employee e join fetch e.department");
        List<Employee> employeeList = query.getResultList();
        for (Employee d : employeeList) {
            System.out.println(d.getId() + " " + d.getName() + " " + d.getDepartment().getName());
        }
        session.close();
        HibernateUtil.shutdown();
    }

    public static void criteriaTask() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
        Root<Employee> rootEmployee = query.from(Employee.class);

        //query.select(rootEmployee).where(cb.like(rootEmployee.get("country").get("name"),"%K%"));
        //query.select(rootEmployee).where(cb.greaterThan(rootEmployee.get("age"),18));
        //query.select(rootEmployee).where(cb.greaterThanOrEqualTo(rootEmployee.get("age"),20));

//        query.select(rootEmployee).where(
//                cb.and(cb.between(rootEmployee.get("age"), 20, 60),
//                        cb.like(rootEmployee.get("country").get("name"), "%KG%")));

        query.select(rootEmployee).where(
                cb.or(cb.equal(rootEmployee.get("department").get("name"), "IT"),
                        cb.equal(rootEmployee.get("country").get("name"), "US")));

        TypedQuery<Employee> employeeTypedQuery = session.createQuery(query);
        List<Employee> employeeList = employeeTypedQuery.getResultList();

        for (Employee employee : employeeList) {
            System.out.println(employee.getName() + " " + employee.getCountry().getName() + " " + employee.getAge());
        }
        session.close();
        HibernateUtil.shutdown();
    }

    public static void criterialJoin() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Employee> rootEmployee = query.from(Employee.class);

        Join<Employee, Country> country = rootEmployee.join("country");
        Join<Employee, Department> department = rootEmployee.join("department");

        query.multiselect(rootEmployee.get("name"), rootEmployee.get("age"),
                country.get("name"), department.get("name"));

        Query qry = session.createQuery(query);
        List<Object[]> fieldsList = qry.getResultList();

        for (Object[] object : fieldsList) {
            System.out.println(object[0] + " " + object[1] + " " + object[2] + " " + object[3]);
        }

        session.close();
        HibernateUtil.shutdown();


    }

    public static void criterialGroupBy() {

    }
}

