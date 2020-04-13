package demo;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteEmployeeDemo {

    public static void main(String[] args) {
        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create a session this is for hibernate
        Session session = factory.getCurrentSession();

        try{
            int employeeId = 34;
            //Get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve student based on the id: primary key
            System.out.println("\nGetting employee with id: " + employeeId);
            Employee myEmployee = session.get(Employee.class, employeeId);

            //Delete the employee
            //System.out.println("Deleting student: " + myEmployee);
            //session.delete(myStudent);

            //Delete student where id=15 this allows you to delete on the fly instead of having to retrieve the object.
            System.out.println("Deleting employee where id=15");
            session.createQuery("delete from Employee where id=33").executeUpdate();

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
