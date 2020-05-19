package de.huckit.todo.cli;

import java.util.List;

public class SaveList {

    private int currentId;
    private List<Todo> todos;

    public SaveList(int currentId, List<Todo> todos) {
        this.currentId = currentId;
        this.todos = todos;
    }

    public SaveList() {
    }

    public int getCurrentId() {
        return currentId;
    }

    public void setCurrentId(int currentId) {
        this.currentId = currentId;
    }

    public List<Todo> getTodos() {
        return todos;
    }
}
