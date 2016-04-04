/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Task;
import Model.Polz;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.persistence.Query;

/**
 *
 * @author Ceparator
 */
@Stateless
public class TaskDAOImpl implements TaskDAO {

    @PersistenceContext(unitName = "LabaEJB-ejbPU")
    private EntityManager em;

    @Resource(lookup = "jdbc/laba")
    private DataSource dataSource;

    @Override
    public List<Task> listAllTasks() {
        Query query = em.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

    @Override
    public Task getTaskById(Integer taskId) {
        return em.find(Task.class, taskId);
    }

    @Override
    public boolean editTask(Task task) {
        try {
            em.merge(task);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Polz> getUsers(int idTask) {
        Task task = em.getReference(Task.class, idTask);
        return task.getPolzs();
    }

    @Override
    public void addUserToTask(int idTask, int idUser) {
        Task task = em.getReference(Task.class, idTask);
        task.getPolzs().add(em.getReference(Polz.class, idUser));
        Polz user = em.getReference(Polz.class, idUser);
        user.getTasks().add(em.getReference(Task.class, idTask));
    }

    @Override
    public List<Task> listTasksForUser(int idPolz) {
        Query query = em.createQuery("SELECT t FROM Task t", Task.class);
        List<Task> fullList = query.getResultList();
        Polz user = em.getReference(Polz.class, idPolz);
        List<Task> newList = user.getTasks();
        fullList.removeAll(newList);
        return fullList;
    }

    @Override
    public void deleteUserFromTask(int idTask, int idUser) {
        Task task = em.getReference(Task.class, idTask);
        Polz polz = em.getReference(Polz.class, idUser);
        task.getPolzs().remove(polz);
    }
}
