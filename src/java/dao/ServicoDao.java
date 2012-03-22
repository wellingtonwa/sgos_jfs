/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;
import model.Servico;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author wellington
 */
public class ServicoDao {
    
    public void save(Servico servico){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            if(servico.getId() == 0)
                Dao.getSession().save(servico);
            else
                Dao.getSession().update(servico);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public void delete(Servico servico){
        Transaction t = Dao.getSession().beginTransaction();
        
        try {
            Dao.getSession().delete(servico);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
        }
    }
    
    public Servico getRegistro(long id){
        return (Servico) Dao.getSession().load(Servico.class, id);
    }
    
    public List<Servico> getRegistros(){
        return Dao.getSession().createQuery("from Servico").list();
    }
    
    public List<Servico> getServicoByTipoEquipamento(long idTipoEquipamento){
        Query query = Dao.getSession().createQuery("SELECT te.servicos FROM TipoEquipamento te WHERE te.id = :idTipoEquipamento");
        query.setLong("idTipoEquipamento", idTipoEquipamento);
        return query.list();
    }
}
