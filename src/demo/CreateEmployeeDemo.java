package demo;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateEmployeeDemo {

    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create a session
        Session session = factory.getCurrentSession();

        try{
            //create 3 student objects
            System.out.println("Creating 1 employee objects...");
            Employee tempStudent = new Employee("Vanessa", "Dixon", "Pyramid Inc.");

            //start a transaction
            session.beginTransaction();
            System.out.println("Beginning transaction...");

            //save the student object
            session.save(tempStudent);
            System.out.println("Saving the new employees...");

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
