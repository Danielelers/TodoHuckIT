package de.huckit.todo.cli;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TodoFileRepository implements TodoRepository {
    private SaveList list;  //Objekt der Savelist wird erstellt dieses Objekt beinhaltet eine Liste, welche die Todos einspeichern soll
    private TodoMySQLRepository mysql;

    public TodoFileRepository() {
        this.list = readAllData();  //Das Objekt wird gelesen und ermöglich den Zugriff auf die Liste
    }


    private void writeAllData(SaveList liste) {
        ObjectMapper mapper = new ObjectMapper();

        try {              //überschreibt den JSON.File mit dem aktuellen Inhalt des Objekts "list"
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("MyTodos.json"), liste);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SaveList readAllData() {
        SaveList savelist = null;

        try {
            savelist = new ObjectMapper().readValue(  //todoArray erhält das gelesene der Datei "Mytodos.json" worin das Objekt "list" drin steht
                    new File("MyTodos.json"),
                    SaveList.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return savelist;
    }

    public List<Todo> getAll() {
        return list.getTodos();  //Objekt hat die Methode um die Todos zu geben, da die liste im Objekt ist
    }


    public Todo findById(int id) {
        for (Todo element : list.getTodos()) {
            if (id == element.getId()) {
                return element;
            }
        }
        throw new RuntimeException("Failed to find Todo");
    }

    public void delete(Todo todo) {
        list.getTodos().remove(todo);
        writeAllData(list);
    }

    public Todo create(Todo todo) {

        todo.setId(list.getCurrentId());   //nimmt die aktuelle ID aus dem Objekt "list"
        list.setCurrentId(todo.getId() + 1); //setzt die aktuelle ID um 1 höher
        list.getTodos().add(todo);
        writeAllData(list);  //speichert die veränderte list

        return todo;

    }

    public void save(Todo todo) {
        for (Todo element : list.getTodos()) {
            if (element.getId() == todo.getId()) {   //überschreibt den bereits Vorhandenen Todo
                element = todo;
                break;
            }
        }
        writeAllData(list);
    }


}
