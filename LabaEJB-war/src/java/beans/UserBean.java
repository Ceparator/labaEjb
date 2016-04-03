/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Model.Task;
import Model.Polz;
import dao.JpaDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Ceparator
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private JpaDAO jpaDAO;

    private Polz user;
    private int editId;
    private int addId;

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    @PostConstruct
    private void initializeBean() {
        user = new Polz();
    }

    public List<Polz> selectUsers() {
        List<Polz> list = jpaDAO.listAllUsers();
        return list;
    }

    public String addUser() {
        jpaDAO.addUser(user);
        return "/index.xhtml";
    }

    public String toTheDelUser(int idUser) {
        this.editId = idUser;
        return "/delUser.xhtml";
    }

    public String delUser(int idUser) {
        jpaDAO.deleteUser(idUser);
        return "/index.xhtml";
    }

    public String toTheUserTasks(int idUser) {
        this.editId = idUser;
        return "/userInfo.xhtml";
    }

    public List<Task> selectUserTasks(int idUser) {
        List<Task> t = jpaDAO.getUserTasks(idUser);
        return t;
    }

    public String addTaskToUser(int idUser) {
        jpaDAO.addTaskToUser(idUser, addId);
        return "/userInfo.xhtml";
    }
    
    public String getUser(int idUser){
        return jpaDAO.getUserById(idUser).getUsername();
    }

    public Polz getUser() {
        return user;
    }

    public void setUser(Polz user) {
        this.user = user;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }
    
    

}
