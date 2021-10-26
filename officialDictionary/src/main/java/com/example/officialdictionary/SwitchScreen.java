package com.example.officialdictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class SwitchScreen {
    public static void Switch(String fxmlFile, ActionEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root = FXMLLoader.load(Objects.requireNonNull(SwitchScreen.class.getResource(fxmlFile)));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        AtomicReference<Double> x = new AtomicReference<>((double) 0);
        AtomicReference<Double> y = new AtomicReference<>((double) 0);
        root.setOnMousePressed(evt -> {
            x.set(evt.getSceneX());
            y.set(evt.getSceneY());
        });
        root.setOnMouseDragged(evt -> {
            stage.setX(evt.getScreenX() - x.get());
            stage.setY(evt.getScreenY() - y.get());
        });
        stage.show();
    }
}
