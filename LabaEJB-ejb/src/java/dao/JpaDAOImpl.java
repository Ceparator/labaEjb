/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Task;
import Model.Polz;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ceparator
 */
@Stateful
public class JpaDAOImpl implements JpaDAO {

    @PersistenceContext(unitName = "LabaEJB-ejbPU")
    private EntityManager em;

    @Override
    public void deleteUser(int idUser) {
        em.remove(em.getReference(Polz.class, idUser));
    }

    @Override
    public void addUser(Polz user) {
        em.persist(user);
    }

    @Override
    public List<Polz> listAllUsers() {
        Query query = em.createQuery("SELECT u FROM Polz u", Polz.class);
        return query.getResultList();
    }

    @Override
    public void editUser(Polz user) {
        em.merge(user);
    }

    @Override
    public List<Task> getUserTasks(int idUser) {
        Polz user = em.getReference(Polz.class, idUser);
        return user.getTasks();
    }

    @Override
    public void addTaskToUser(int idUser, int idTask) {
        Polz user = em.getReference(Polz.class, idUser);
        user.getTasks().add(em.getReference(Task.class, idTask));
        Task task = em.getReference(Task.class, idTask);
        task.getPolzs().add(em.getReference(Polz.class, idUser));
    }

    @Override
    public Polz getUserById(int idUser) {
        return em.find(Polz.class, idUser);
    }

    @Override
    public List<Polz> listUsersForTask(int idTask) {
        Query query = em.createQuery("SELECT u FROM Polz u", Polz.class);
        List<Polz> fullList = query.getResultList();
        Task task = em.getReference(Task.class, idTask);
        List<Polz> newList = task.getPolzs();
        fullList.removeAll(newList);
        return fullList;
    }

    @Override
    public void deleteTaskFromUser(int idUser, int idTask) {
        Task task = em.getReference(Task.class, idTask);
        Polz polz = em.getReference(Polz.class, idUser);
        polz.getTasks().remove(task);
    }
}
