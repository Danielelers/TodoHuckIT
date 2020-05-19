package de.huckit.todo.cli;


public class Todo {

    private String text;
    private boolean isDone;
    private int id;

    public Todo(String text, boolean isDone, int id) {
        this.text = text;
        this.isDone = isDone;
        this.id = id;
    }

    public Todo() {

    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
}