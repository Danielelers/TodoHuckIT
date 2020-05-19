package de.huckit.todo.cli;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class TodoMySQLRepository implements TodoRepository {  //implementiert die Methoden der repository, damit in der Main nur der Name nach repository geändert werden muss
    List<Todo> list;                                         // wodurch zwischen CLI und Datenbank gewechselt werden kann [INTERFACE]
    Connection conn;
    String url = "jdbc:mysql://localhost:3306/todo_db";
    String user = "root";
    String password = "";


    public TodoMySQLRepository() {
        this.conn = connect();              //erstellt eine Verbindung zur Datenbank her
        this.list = readDatabase();        // und liest direkt was alles in der Datenbank steht
    }

    private Connection connect() {

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Erfolgreich!");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to Database");
        }
    }


    public Todo create(Todo todo) {

        try {
            String insert = "INSERT INTO todos (Todo_Text) VALUES (?)";  //Statement
            PreparedStatement stmt = conn.prepareStatement(insert);     // Statement wird übertragen
            stmt.setString(1, todo.getText());                      // stmt.setString ([WelchesFragezeichen] , [WasInDasFragezeichenReinSoll]
            stmt.executeUpdate();                                     // führt das Statement aus
            stmt.close();
            return todo;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Todo");
        }

    }



    public List<Todo> getAll() {
        return list;          //Da in dem Konstruktor bereits die list erstellt wird, kann man direkt return list; machen
    }

    private List<Todo> readDatabase(){
        List <Todo> myTodos=new ArrayList<Todo>();
        try {
            String selectAll = "SELECT Todo_Id, Todo_Text, Todo_Tick FROM todos"; //Statement
            PreparedStatement stmt = conn.prepareStatement(selectAll);
            ResultSet rs = stmt.executeQuery(selectAll);
            while (rs.next()) {
                int i = rs.getInt("Todo_Id");
                String str = rs.getString("Todo_Text");
                boolean tick = rs.getBoolean("Todo_Tick");

                Todo todo = new Todo(str, tick, i);

                myTodos.add(todo);
            }

            return myTodos;


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create Todo");
        }

    }


    public Todo findById(int id) {
        for (Todo element : list) {
            if (id == element.getId()) {
                return element;
            }
        }
        throw new RuntimeException("Failed to find Todo");
    }


    public void delete(Todo todo) {

        try {
            String delete="DELETE FROM `todo_db`.`todos` WHERE  `Todo_Id`=?;";
            PreparedStatement stmt = conn.prepareStatement(delete);
            stmt.setInt(1, todo.getId());
            stmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete Todo");
        }

    }



    public void save(Todo todo) {

        try {
            String update=" UPDATE `todo_db`.`todos` SET `Todo_Tick`='1' WHERE  `Todo_Id`=?;";
            PreparedStatement stmt = conn.prepareStatement(update);
            stmt.setInt(1, todo.getId());
            stmt.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update Todo");
        }
    }

}

