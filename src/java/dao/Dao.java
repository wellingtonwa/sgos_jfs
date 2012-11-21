/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author wellington
 */
public class Dao {

    private static Session session;
    private static ThreadLocal threadlocal = new ThreadLocal();
    private static SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

    private Dao() {
        // TODO Auto-generated constructor stub
    }

    public static Session getSession() {
        session = (Session) threadlocal.get();
        if (session == null || !session.isOpen()) {
            session = sessionFactory.openSession();
            threadlocal.set(session);
        }

        return session;
    }

    public void begin() {
        getSession().beginTransaction();

    }

    public void commit() {
        getSession().getTransaction().commit();
    }

    public void rollback() {
        getSession().getTransaction().rollback();
    }

    public void close() {
        getSession().close();
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    //passamos ele para o Filter
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        Dao.sessionFactory = sessionFactory;
    }
}
