package de.htwg.tictactoe.aview.gui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

/**
 *
 * @author siegfried
 */
public class HelloWorld extends Application {
    
    @Override public void init(){
        
    }
    
    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("Tictactoe");
        Scene scene = new Scene(new VBox(), 400, 350);
        scene.setFill(Color.AQUA);
        MenuBar menu = new MenuBar();
        Menu datei = new Menu("Datei");
        final VBox vbox = new VBox();
        MenuItem neuesSpiel = new MenuItem("Neues Spiel");
        neuesSpiel.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.out.println("hallo welt");
                vbox.setVisible(true);
            }
        });
        
        
        MenuItem beenden = new MenuItem("Spiel beenden");
        beenden.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t) {
                Runtime.getRuntime().exit(0);
            }
        });
        
        
        datei.getItems().addAll(neuesSpiel, beenden);
        
        
        Menu fragezeichen = new Menu("?");
        menu.getMenus().addAll(datei, fragezeichen);
        
        
        Button btn = new Button();
    btn.setText("Open Dialog");
    btn.setOnAction(
        new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(stage);
                VBox dialogVbox = new VBox(20);
                
                dialogVbox.getChildren().add(new Text("This is a Dialog"));
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            }
         });
        
        
        
        ((VBox)scene.getRoot()).getChildren().addAll(menu, btn);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public GridPane SpielFeld() {
        
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(0);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        
        
        
        return null;
        
    }
    
}
