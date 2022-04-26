package com.andreagreco.Caching;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class App 
{
    public static void main( String[] args )
    {
    	Person p = new Person();
    	
//    	p.setPid(101);
//    	PersonName pName = new PersonName();
//    	pName.setFname("Jean");
//    	pName.setMname("R.");
//    	pName.setLname("Seberg");
//    	p.setName(pName);
//    	p.setOccupation("Actress");
//		...
    	
        Configuration con = new Configuration().configure()
        		.addAnnotatedClass(Person.class);
        ServiceRegistry reg = new ServiceRegistryBuilder()
        		.applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
//        session.save(p);
        
        p = (Person) session.get(Person.class, 101);
        System.out.println(p.getName().toString() + ", "
        		+ p.getOccupation());        
        p = (Person) session.get(Person.class, 102);
        System.out.println(p.getName().toString() + ", "
        		+ p.getOccupation());        
        p = (Person) session.get(Person.class, 103);
        System.out.println(p.getName().toString() + ", "
        		+ p.getOccupation());     
        p = (Person) session.get(Person.class, 104);
        System.out.println(p.getName().toString() + ", "
        		+ p.getOccupation());
        
        //First level caching works in the same session
        p = (Person) session.get(Person.class, 104);
        System.out.println(p.getName().toString() + ", "
        		+ p.getOccupation());
        
        tx.commit();
        session.close();
        
        /*
         * For second level caching:
         * 1. Add Ehcache dependency 
         * 2. Add Hibernate-ehcache dependency
         * 3. Add appropriate properties in Hibernate configuration file
         * 4. Add @Cacheable annotation to class
         * 5. Define cache strategy for class
         */
        Session s2 = sf.openSession();
        s2.beginTransaction();
        p = (Person) s2.get(Person.class, 104);
        System.out.println(p.getName().toString() + ", "
        		+ p.getOccupation());
        s2.getTransaction().commit();
        s2.close();
    }
}
