package de.huckit.todo.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    void help() {
        System.out.println("----------- COMMANDS -----------\n " +
                "Todo erstellen = create (Ihr todo)\n" +
                "Todos auflisten = todolist\n"
                + "Todo abhaken = ticktodo (Id Ihrer Todo)\n" +
                "Todo loeschen = delete (Id Ihrer Todo)\n");
    }

    void create(String[] args) {          // (Array das Kopiert wird / wo er anfängt [] / wie lange er kopieren soll);
        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
        String text = String.join(" ", newArgs); //fügt ein Array zsm als 1 String und gibt an was zwischen jedem String sein soll
        service.createTodo(text);
        System.out.println("Ihr Todo wurde erfolgreich erstellt");
    }

    void print() throws IOException {
        List<Todo> liste = service.bringlist();
        for (Todo element : liste) {
            String haken = "Id: " + element.getId() + ". " + element.getText();
            if (element.isDone()) {
                System.out.print(haken += "[ERLEDIGT]\n");
                continue;
            }
            System.out.print(haken + "\n");
        }
    }

    void tick(int id) {
        service.tick(id);
        System.out.print("Todo wurde erfolgreich abgehakt! Gut gemacht!");
    }

    void delete(int id) {
        service.delete(id);
        System.out.print("Todo wurde erfolgreich geloescht!");
    }
}
