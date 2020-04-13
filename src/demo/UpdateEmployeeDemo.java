package demo;

import entity.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateEmployeeDemo {

    public static void main(String[] args) {
        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create a session this is for hibernate
        Session session = factory.getCurrentSession();

        try{
            int employeeId = 32;
            //Get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve student based on the id: primary key
            System.out.println("\nGetting employee with id: " + employeeId);
            Employee myEmployee = session.get(Employee.class, employeeId);

            //Updating the student first Name at primary key 1
            System.out.println("Updating Employee...");
            myEmployee.setFirstName("Adam");

            //commit the transaction
            session.getTransaction().commit();

            //*********************************************************//
            //Create new factory session to do a bulk update
            session = factory.getCurrentSession();
            session.beginTransaction();

            //Update company for all students
            System.out.println("Updating company for all the employee...");
            session.createQuery("update Employee set company='Pyramid'").executeUpdate();
            //or you can use a where clause to be more precise
            session.createQuery("update Employee set company='Pyramid' where company='Accenture'").executeUpdate();

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
