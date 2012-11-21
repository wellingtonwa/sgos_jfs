/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UsuarioDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Grupo;
import model.GrupoPermissaoTela;
import model.Usuario;

/**
 *
 * @author wellington
 */
@ManagedBean(name="UsuarioAuthController")
@SessionScoped
public class UsuarioAuthController {
    
    public Usuario current;
    public String loginEmail;
    public String senha;
    public boolean logado;
    
    
    public UsuarioAuthController() {

    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
    public void verificarLogin(){
        Usuario usuario = null;
        
        if(loginEmail.indexOf("@")!=-1){
            usuario = new UsuarioDao().getUsuarioByEmailAndPassword(loginEmail, senha);
        }
        else{
            usuario = new UsuarioDao().getUsuarioByLoginAndPassword(loginEmail, senha);
        }
        
        if(usuario != null){
            current = usuario;
            logado = true;
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/sgos_jfs/index.jsf");
            } catch (IOException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void logout(){
        current = new Usuario();
        logado = false;
        
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/sgos_jfs/index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean temPermissaoTela(String nomeTela){
        if(current.getIsAdmin())
            return true;
        
        if(current.getGrupos() != null && current.getGrupos().size()>0){
            for(Grupo grupo : current.getGrupos()){
                if(grupo.getPermissoesGrupo() != null && grupo.getPermissoesGrupo().size()>0){
                    for(GrupoPermissaoTela grupoPermissaoTela : grupo.getPermissoesGrupo()){
                        if(nomeTela.equals(grupoPermissaoTela.getId().getTela().getNome())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean temPermissaoTelaAcao(String nomeTela, String permissao){
        
        if(current.getIsAdmin())
            return true;
        
        if(current.getGrupos().size()>0){
            for(Grupo grupo : current.getGrupos()){
                if(grupo.getPermissoesGrupo().size()>0){
                    for(GrupoPermissaoTela grupoPermissaoTela : grupo.getPermissoesGrupo()){
//                        System.out.println(nomeTela+" =="+grupoPermissaoTela.getId().getTela().getNome());
//                        System.out.println(permissao+" =="+grupoPermissaoTela.getId().getPermissao().getPermissao()+"/n/n");
                        if(nomeTela.equals(grupoPermissaoTela.getId().getTela().getNome())
                                && permissao.equals(grupoPermissaoTela.getId().getPermissao().getPermissao())){
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
       
}
