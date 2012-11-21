/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webFilter;

import dao.Dao;
import java.io.IOException;
import javax.servlet.*;
import org.hibernate.SessionFactory;

/**
 *
 * @author wellington
 */
@org.hibernate.annotations.Filter(name="hibernateFilter")
public class HibernateFilter implements Filter{
    
    private SessionFactory sessionFactory;
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { 
        sessionFactory = Dao.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        Dao.getSession();
        Dao.getSession().beginTransaction();
        chain.doFilter(request, response);
        
        try{
            Dao.getSession().getTransaction().commit();
        }catch(Exception ex){
            Dao.getSession().getTransaction().rollback();
        }
        finally{
            Dao.getSession().close();
        }
    } 
 
    @Override 
    public void destroy() {}
    
}
