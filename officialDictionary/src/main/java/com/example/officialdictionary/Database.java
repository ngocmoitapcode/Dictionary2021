package com.example.officialdictionary;

import javafx.scene.control.Alert;
import java.sql.*;
import java.util.HashMap;

public class Database {
    public static PreparedStatement preparedStatement;
    public static Connection connection;
    public static HashMap<String, String> listWord= new HashMap<String, String>();
    static {
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/edict";
            String username = "root";
            String password = "123456";
            connection = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("connect successfully!");
        } catch (Exception e) {
            System.out.println("Connect failure!");
            e.printStackTrace();
        }
    }
    static {
        try {
            // crate statement
            Statement stmt = connection.createStatement();
            // get data from table 'tbl_edict'
            ResultSet rs = stmt.executeQuery("select * from tbl_edict");
            // show data
            while (rs.next()) {
                listWord.put(rs.getString("word"), rs.getString("detail"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*public void HashmapToDatabase() throws SQLException {
        String SqlDetelteAll = "DELETE FROM tbl_edict";
        preparedStatement = connection.prepareStatement(SqlDetelteAll);
        preparedStatement.executeUpdate();
        Set<String> keySet = listWord.keySet();
        for (String key : keySet) {
            String sqlAdd = "INSERT INTO tbl_edict (word, detail) VALUES (?, ?);\n";
            preparedStatement = connection.prepareStatement(sqlAdd);
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, listWord.get(key));
            preparedStatement.executeUpdate();
        }
        System.out.println("Update successfully!");
    }*/


    public String searchWord(String target) throws SQLException {
        if(listWord.containsKey(target) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification!");
            alert.setContentText("This word isn't available!");
            alert.show();
            return null;
        }
        else {
            return listWord.get(target);
        }
    }

    public void deleteWord(String target) throws SQLException {
        if(listWord.containsKey(target) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification!");
            alert.setContentText("This word isn't available!");
            alert.show();
        }
        else {
            listWord.remove(target);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setContentText("Delete successfully!");
            alert.show();

        }

    }

    public void editWord(String target,String explain) throws SQLException {
        if(listWord.containsKey(target) == false) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification!");
            alert.setContentText("This word isn't available!");
            alert.show();
        }
        else {
            listWord.put(target, explain);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification!");
            alert.setContentText("Edit successfully!");
            alert.show();

        }

    }

    public void addWord(String target, String explain) throws SQLException {
        if(listWord.containsKey(target) == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification!");
            alert.setContentText("This word is available!");
            alert.show();
        }
        else {
            listWord.put(target, explain);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification!");
            alert.setContentText("Add successfully!");
            alert.show();
        }

    }

   /* public String searchWord(String target) throws SQLException {
        String sqlsearch = "Select detail from tbl_edict where word=?";
        preparedStatement = connection.prepareStatement(sqlsearch);
        preparedStatement.setString(1, target);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This word isn't available!");
            alert.show();
            return null;
        }
        return resultSet.getString("detail");
    }

    public void deleteWord(String target) throws SQLException {
        String sqlremove = "Delete from tbl_edict where word=?";
        preparedStatement = connection.prepareStatement(sqlremove);
        preparedStatement.setString(1, target);
        int check = preparedStatement.executeUpdate();
        if(check == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This word isn't available!");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Delete successfully!");
            alert.show();
        }
    }

    public void editWord(String target,String explain) throws SQLException {
        String sqledit = "UPDATE tbl_edict SET detail = ? WHERE word = ?";
        preparedStatement = connection.prepareStatement((sqledit));
        preparedStatement.setString(1, explain);
        preparedStatement.setString(2, target);
        int check = preparedStatement.executeUpdate();
        if(check == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This word isn't available!");
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Edit successfully!");
            alert.show();
        }

    }

    public void addWord(String target, String explain) throws SQLException {
        String sqlsearch = "Select detail from tbl_edict where word=?";
        preparedStatement = connection.prepareStatement(sqlsearch);
        preparedStatement.setString(1, target);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()) {
            String sqlAdd = "INSERT INTO tbl_edict (word, detail) VALUES (?, ?);\n";
            preparedStatement = connection.prepareStatement(sqlAdd);
            preparedStatement.setString(1, target);
            preparedStatement.setString(2, explain);
            int check = preparedStatement.executeUpdate();
            if(check != 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Add successfully!");
                alert.show();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("This word is available!");
            alert.show();
        }

    } */

}
