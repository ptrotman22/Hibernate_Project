
import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadEmployeeDemo {

    public static void main(String[] args) {

        //create session factory this is for hibernate
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        //create a session this is for hibernate
        Session session = factory.getCurrentSession();

        try{
            //create a new student object
            System.out.println("Creating new student object...");
            Employee tempEmployee = new Employee("DarthVader", "Skywalker", "darth@skywalker.com");

            //start a transaction
            session.beginTransaction();
            System.out.println("Beginning transaction...");

            //save the student object
            System.out.println("Saving the new students...");
            System.out.println(tempEmployee);
            session.save(tempEmployee);

            //commit the transaction
            session.getTransaction().commit();

            //***********************************************************************************
            //***Code for Reading the object***
            //find out the student's id: primary key
            System.out.println("Saved employee. Generated id: " + tempEmployee.getId());

            //Get a new session and start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //Retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + tempEmployee.getId());
            Employee myStudent = session.get(Employee.class, tempEmployee.getId());
            System.out.println("Get Completed: " + myStudent);

            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
