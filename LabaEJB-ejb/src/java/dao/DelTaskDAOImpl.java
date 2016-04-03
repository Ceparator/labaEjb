/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import Model.Task;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

/**
 *
 * @author Ceparator
 */
@Stateful
@ConversationScoped
public class DelTaskDAOImpl implements DelTaskDAO, Serializable {

    @Resource(lookup = "jdbc/laba")
    private DataSource dataSource;

    @PersistenceContext(unitName = "LabaEJB-ejbPU")
    private EntityManager em;

    @Inject
    Conversation conversation;
    private int count;

    @PostConstruct
    public void init() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int deleteTask(int idTask) {
        em.remove(em.getReference(Task.class, idTask));
        endConversation();
        return count;
    }

    @Override
    public int addTask(Task task) {
        em.persist(task);
        if (conversation.isTransient()) {
            conversation.begin();
        }
        count++;
        return count;
    }

    public void endConversation() {
        count = 0;
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
}
