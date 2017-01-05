package com.ccizer.webapps.todolist.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * <h1>Step</h1>
 *
 * Step POJO
 *
 * @author  Can Çizer
 * @version 1.0
 * @since   2017-01-04
 */
@Entity
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "checked", nullable = false)
    private boolean checked = false;

    @ManyToOne
    @JoinColumn(name = "toDoList_id")
    private ToDoList toDoList;

    public Step(){

    }

    public Step(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return name;
    }
}
