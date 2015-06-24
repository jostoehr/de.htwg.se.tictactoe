package de.htwg.tictactoe.aview.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author siegfried
 */
public class GameField extends Application {
    
    // create a grid with some sample data.
    GridPane grid = new GridPane();

    private Label l1;
    private Label l2;
    private Label l3;
    private Label l4;
    private Label l5;
    private Label l6;
    private Label l7;
    private Label l8;
    private Label l9;
    
    
    @Override
    public void init(){
        l1 = new Label("?");
        l1.setFont(new Font("Arial", 200));
        l2 = new Label("?");
        l2.setFont(new Font("Arial", 200));
        l3 = new Label("?");
        l3.setFont(new Font("Arial", 200));
        l4 = new Label("?");
        l4.setFont(new Font("Arial", 200));
        l5 = new Label("?");
        l5.setFont(new Font("Arial", 200));
        l6 = new Label("?");
        l6.setFont(new Font("Arial", 200));
        l7 = new Label("?");
        l7.setFont(new Font("Arial", 200));
        l8 = new Label("?");
        l8.setFont(new Font("Arial", 200));
        l9 = new Label("?");
        l9.setFont(new Font("Arial", 200));
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        BorderPane border = new BorderPane();
        HBox hbox = Menu();
        border.setTop(hbox);
        border.setCenter(mouse());
        border.setBottom(bottom());
        
        //  border.setCenter(gameField());
        root.getChildren().add(border);
        
        Scene scene = new Scene(root, 800, 800);
        stage.setTitle("Tictactoe");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setScene(scene);
        //stage.show();
        
        // create a grid with some sample data.
    
        // stage.setScene(new Scene(mouse(), 800, 800));
        stage.show();
        
    }
    
    public HBox Menu(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        // #17F26B
        hbox.setStyle("-fx-background-color:white;");
        // -- MenuBar menuBar
        MenuBar menuBar = new MenuBar();
        // -- Menu Datei
        Menu datei = new Menu("Datei");
        // -- MenuItem newGame
        MenuItem newGame = new MenuItem("Neues Spiel");
        newGame.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                
            }
        });
        // -- MenuItem exit
        MenuItem exit = new MenuItem("Beenden");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        
        // -- Menu Fragezeigen
        Menu fragezeichen = new Menu("?");
        
        
        datei.getItems().addAll(newGame, exit);
        menuBar.getMenus().addAll(datei, fragezeichen);
        hbox.getChildren().addAll(menuBar);
        return hbox;
    }
    
    /*Source: http://www.codedisqus.com/CyVkXejkXq/how-to-paint-the-selected-gridpane-range-with-mouse-in-javafx.html*/
    
    public StackPane mouse(){
        GridPane grid = new GridPane();

        final Image crossImage = new Image(getClass().getResourceAsStream("cross2.png"), 260, 240, false, false);
        final Image noughtImage = new Image(getClass().getResourceAsStream("nought.png"), 260, 240, false, false);
        
        
        
        l1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l1.setGraphic(new ImageView(crossImage));
                l1.setText("");
                l1.setStyle("-fx-background-color:white;");
            }
        });
        l2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l2.setGraphic(new ImageView(noughtImage));
                l2.setText("");
                l2.setStyle("-fx-background-color:white;");
            }
        });
        l3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l3.setGraphic(new ImageView(noughtImage));
                l3.setText("");
                l3.setStyle("-fx-background-color:white;");
            }
        });
        l4.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l4.setGraphic(new ImageView(crossImage));
                l4.setText("");
                l4.setStyle("-fx-background-color:white;");
            }
        });
        l5.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l5.setGraphic(new ImageView(noughtImage));
                l5.setText("");
                l5.setStyle("-fx-background-color:white;");
            }
        });
        l6.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l6.setStyle("-fx-background-color:white;");

            }
        });
        l7.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l7.setStyle("-fx-background-color:white;");
            }
        });
        l8.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l8.setStyle("-fx-background-color:white;");
            }
        });
        l9.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent arg0)
            {
                l9.setStyle("-fx-background-color:white;");
            }
        });

        grid.addRow(0, l1, l2, l3);
        grid.addRow(1, l4, l5, l6);
        grid.addRow(2, l7, l8, l9);

        for (Node n : grid.getChildren())
        {
            Control control = (Control) n;
            control.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            control.setStyle("-fx-background-color:white; -fx-alignment: center;");
        }

        grid.setStyle("-fx-background-color: black; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
        grid.setSnapToPixel(false);

        ColumnConstraints oneThird = new ColumnConstraints();
        oneThird.setPercentWidth(100 );
        oneThird.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().addAll(oneThird, oneThird, oneThird);
        RowConstraints oneHalf = new RowConstraints();
        RowConstraints one = new RowConstraints();
        one.setPercentHeight(100);
        oneHalf.setPercentHeight(100 );
        oneHalf.setValignment(VPos.CENTER);

        grid.getRowConstraints().addAll(oneHalf, oneHalf, oneHalf);

        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: white;");

        layout.getChildren().addAll(grid);
        return layout;
    }
    
    public HBox bottom(){
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(45);
        hbox.setStyle("-fx-background-color:white;");
        return hbox;
    }
    
}
