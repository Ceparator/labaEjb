/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

/**
 *
 * @author Ceparator
 */
@Stateful
public class StatefulDAOImpl implements StatefulDAO, Serializable {

    List<String> bookShelf;

    @PostConstruct
    private void init() {
        bookShelf = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        bookShelf.add(book.getName());
    }

    @Override
    public List<String> getBooks() {
        return bookShelf;
    }

}
