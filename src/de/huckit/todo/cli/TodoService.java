package de.huckit.todo.cli;

import java.io.IOException;
import java.util.List;

public class TodoService {
    final private TodoRepository repository;  //erstellt Objekt von der TodoRepository um Methoden zu nutzen

    public TodoService(TodoRepository repository) {  //Erstellt Service objekt, welches repository Eigenschaften annimmt
        this.repository = repository;               //Dieses Objekt sei das Objekt was in der Main bereits erstellt wurde
    }


    public void createTodo(String text) {

        Todo todo = new Todo(text, false, 0); //gibt id 0 erst mal um nachher ID von der aktuellen ID zu setzen
        repository.create(todo);
    }


    public List<Todo> bringlist() throws IOException {
        return repository.getAll();
    }

    public void tick(int id) {
        final var todo = repository.findById(id);
        if (todo == null) throw new RuntimeException("TODO doesn't exist!");
        todo.setDone(true);
        repository.save(todo);
    }

    public void delete(int id) {
        final var todo = repository.findById(id);
        if (todo == null) throw new RuntimeException("TODO doesn't exist!");
        repository.delete(todo);
    }
}
