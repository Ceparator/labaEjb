/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Task;
import Model.Polz;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ceparator
 */
@Remote
public interface TaskDAO {

    List<Task> listAllTasks();

    List<Task> listTasksForUser(int idPolz);

    Task getTaskById(Integer taskId);

    List<Polz> getUsers(int idTask);

    boolean editTask(Task task);

    void addUserToTask(int idTask, int idUser);

    void deleteUserFromTask(int idTask, int idUser);

}
