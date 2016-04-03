/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Task;
import Model.Polz;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Ceparator
 */
@Local
public interface JpaDAO {

    void deleteUser(int idUser);

    void addUser(Polz user);
    
    List<Polz> listAllUsers();

    void editUser(Polz user);
    
    List<Task> getUserTasks(int idUser);
    
    void addTaskToUser(int idUser, int idTask);
    
    Polz getUserById(int idUser);
}
