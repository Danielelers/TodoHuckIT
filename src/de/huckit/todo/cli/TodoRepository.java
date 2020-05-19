package de.huckit.todo.cli;

import java.util.List;

public interface TodoRepository {
    List<Todo> getAll();

    Todo findById(int id);

    void delete(Todo todo);

    Todo create(Todo todo);

    void save(Todo todo);

}
