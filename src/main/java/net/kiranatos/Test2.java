package net.kiranatos;

import java.util.List;
import net.kiranatos.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            
            List<Employee> emps = session.createQuery("from Employee")
                    .getResultList();
            for (Employee e : emps) {
                System.out.println(e);
            }
            
            emps = session.createQuery("from Employee" // Employee - 
                    + "where name = 'Aleks'")
                    .getResultList();
            for (Employee e : emps) {
                System.out.println(e);
            }            
            
            session.getTransaction().commit();            
        } finally {
            factory.close();
        }
    }
}
