/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.DelTaskDAO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import Model.Task;
import Model.Polz;
import dao.JpaDAO;
import dao.TaskDAO;
import java.util.List;

/**
 *
 * @author Ceparator
 */
@Named(value = "addTaskBean")
@SessionScoped
public class AddTaskBean implements Serializable {

    @EJB
    private DelTaskDAO dao;
    @EJB
    private TaskDAO taskDAO;
    @EJB
    private JpaDAO jpaDAO;

    @PostConstruct
    private void initializeBean() {
        System.out.println("===== initialization!");
        c = 0;
        task = new Task();
    }

    private Task task;
    private int c;
    private int editId;
    private int addId;

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public Task getTask() {
        System.out.println("=========== getTask = " + this);
        if (task == null) {
            System.out.println("Task is null");
        }
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String adTask() {
        System.out.println("=========== adTask = " + this);
        java.sql.Date newDate = new java.sql.Date(task.getDueDate().getTime());
        task.setDueDate(newDate);
        this.c = dao.addTask(task);
        return "/index.xhtml";
    }

    public String toTheDelTask(int idTask) {
        this.editId = idTask;
        return "/delTask.xhtml";
    }

    public String delTask(int idTask) {
        this.c = dao.deleteTask(idTask);
        return "/index.xhtml";
    }

    public String toTheTaskUsers(int idTask) {
        this.editId = idTask;
        return "/taskInfo.xhtml";
    }

    public List<Polz> selectTaskUsers(int idTask) {
        List<Polz> u = taskDAO.getUsers(idTask);
        return u;
    }

    public String addUserToTask(int idTask) {
        taskDAO.addUserToTask(idTask, addId);
        return "/taskInfo.xhtml";
    }
    
    public String getTask(int idTask){
        return taskDAO.getTaskById(idTask).getName();
    }
    
    public List<Polz> selectUsersForTask(int idTask) {
        List<Polz> list = jpaDAO.listUsersForTask(idTask);
        return list;
    }
    
    public String deleteUserFromTask(int idTask, int idUser) {
        taskDAO.deleteUserFromTask(idTask, idUser);
        return "/taskInfo.xhtml";
    }
}
