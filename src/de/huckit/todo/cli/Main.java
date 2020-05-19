package de.huckit.todo.cli;

import java.io.IOException;


public class Main {


    public static void main(String[] args) throws IOException {
        final var repository = new TodoMySQLRepository();  // das macht das un das
        final var service = new TodoService(repository); //damit Todoservice die Klasse erhält um repository Methoden zu nutzen
        final var controller = new TodoController(service); // selbe Prinzip


        switch (args[0]) {
            case "-help": {

                controller.help();
                break;
            }
            case "create": {

                controller.create(args);
                break;
            }
            case "list": {

                controller.print();
                break;
            }
            case "tick": {

                controller.tick(Integer.parseInt(args[1]));
                break;
            }
            case "delete": {

                controller.delete(Integer.parseInt(args[1]));
                break;
            }

            default: {

                System.out.println("Eingabe ungueltig. Bitte erneut eingeben.");
                break;
            }

        }
    }
}

