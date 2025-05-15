package com.example.crudaluno;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.crudaluno.view.TelaAluno;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        new TelaAluno().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
