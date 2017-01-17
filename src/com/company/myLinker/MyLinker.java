package com.company.myLinker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by olko1016 on 01/11/2017.
 */
public class MyLinker extends Application {
    private int windowWidth = 600;
    private int windowHeight = 600;
    private String server = "https://devapp254.netcracker.com:7301/";


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simple Application");
        primaryStage.show();

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("ПРОСМОТР РАБОТЫ ПРАВ", "tools/showGrants.jsp");
        stringMap.put("Обновление прав", "tools/resetGrants.jsp");
        stringMap.put("Экспорт атрибутов", "platform/attrexport/attrexport.jsp");
        stringMap.put("Обновление кешей SQL", "tools/queryFinderCache.jsp");
        stringMap.put("Профайлер", "profiler/");
        stringMap.put("ПРОСМОТР МЕТОДОВ И ПОЛЕЙ КЛАССА", "tools/check.jsp");

        BorderPane root = new BorderPane();

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #333366;");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));

        TextField textField = new TextField(server);
        grid.getChildren().add(textField);

        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-background-color: #336699;");

        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            Button button = new Button();
            button.setText(entry.getKey());
            String s = server + entry.getValue();
            button.setOnMousePressed(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    try {
                        openWebpage(new URI(s));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    StringSelection ss = new StringSelection(s);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                }
            });
            vBox.getChildren().add(button);
        }

        root.setCenter(grid);
        root.setRight(vBox);

        Scene scene = new Scene(root, windowWidth, windowHeight);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void openWebpage(URL url) {
        try {
            openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
