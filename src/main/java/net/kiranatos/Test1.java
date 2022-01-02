package net.kiranatos;

import net.kiranatos.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            Employee emp = new Employee("John", "Smith", "IT", 500);
            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit(); // close session        
            System.out.println(emp);
            
            session = factory.getCurrentSession();
            session.beginTransaction();
            emp = session.get(Employee.class, (Long)1L);
            session.getTransaction().commit();
            System.out.println(emp);
        } finally {
            factory.close();
        }
    }
}
