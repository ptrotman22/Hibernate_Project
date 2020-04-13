package demo;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryEmployeeDemo {

    public static void main(String[] args) {

        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try{
            //start a transaction
            session.beginTransaction();

            //Query Student: All Students
            List<Employee> theEmployee = session.createQuery("from Employee").getResultList();
            //Display Students
            System.out.println("Displaying all the Students...");
            displayStudents(theEmployee);

            //Query Student: lastName='Skywalker'
            theEmployee = session.createQuery("from Employee s where s.lastName='Skywalker'").getResultList();
            //Display results
            System.out.println("\nDisplaying all the Employees with last name of Skywalker.");
            displayStudents(theEmployee);

            //Query Student: lastName='Skywalker' OR firstName='Ellie'
            theEmployee = session.createQuery("from Employee s where lastName='Skywalker' OR firstName='Ellie'").getResultList();
            //Display results
            System.out.println("\nDisplaying all the employee with last name Skywalker or first name Ellie...");
            displayStudents(theEmployee);

            //Query Student: where email LIKE '%too@gmail.com'
            theEmployee = session.createQuery("from Employee s where s.email LIKE '%too@gmail.com'").getResultList();
            //Display results
            System.out.println("\nDisplaying all the Employee with the everett@code.com email address...");
            displayStudents(theEmployee);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Employee> theEmployee) {
        for(Employee tempEmployee : theEmployee) {
            System.out.println(tempEmployee);
        }
    }
}
