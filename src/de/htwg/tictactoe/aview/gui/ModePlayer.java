package de.htwg.tictactoe.aview.gui;

import de.htwg.tictactoe.controller.impl.MasterController;
import de.htwg.tictactoe.util.Value;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author siegfried
 */
public class ModePlayer extends Application {
    
    /* Controller Instance Variable */
    private MasterController master;
    
    /* Mode player instance Variables */
    private RadioButton intelligence;
    private RadioButton player;
    private Label LblmodeTitle;
    private Label LblplayerTitle1;
    private Label LblplayerTitle2;
    private Label Lblplayer1;
    private Label Lblplayer2;
    private TextField txtBoxplayer1;
    private TextField txtBoxplayer2;
    private final ToggleGroup group = new ToggleGroup();
    private Button reset;
    private Button apply;
    
    @Override
    public void init(){
        intelligence = new RadioButton("Gegen KI spielen");
        player = new RadioButton("Gegen Spieler spielen");
        Lblplayer1 = new Label("Spielername 1: ");
        Lblplayer2 = new Label("Spielername 2: ");
        LblmodeTitle = new Label("Wählen Sie einen Modus: ");
        LblplayerTitle1 = new Label("Geben Sie Ihren Namen ein: ");
        LblplayerTitle2 = new Label("Geben Sie Ihren Namen ein: ");
        txtBoxplayer1 = new TextField();
        txtBoxplayer2 = new TextField();
        reset = new Button("Zurücksetzen");
        apply = new Button("Übernehmen");
        
        intelligence();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        StackPane root = new StackPane();
        BorderPane border = new BorderPane();
        
        border.setTop(Top());
        border.setCenter(addIntelligencePlayer());
        border.setLeft(addRadio());
        border.setBottom(Bottom());
        root.getChildren().add(border);
        
        Scene scene = new Scene(root, 600, 220);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        stage.setScene(scene);
        stage.setTitle("Bitte auswählen!");
        stage.show();
        
    }
    
    public GridPane addRadio(){
        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        intelligence.setUserData("intelligence");
        intelligence.setToggleGroup(group);
        intelligence.setSelected(true);
        player.setUserData("player");
        player.setToggleGroup(group);
        
        grid.add(LblmodeTitle, 0, 0);
        grid.add(intelligence, 0, 1);
        grid.add(player, 0, 2);
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
        public void changed(ObservableValue<? extends Toggle> ov,
            Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle() != null) {
                    if(group.getSelectedToggle().getUserData().toString().equals("intelligence")){
                        intelligence();
                    } else {
                        player();
                    }
                }                
            }
        });
        
        return grid;
    }
    
    public ModePlayer(MasterController master){
        this.master = master;
        
    }
    
    
    public void intelligence(){
        LblplayerTitle2.setVisible(false);
        Lblplayer2.setVisible(false);
        txtBoxplayer2.setVisible(false);
    }
    
    public void player(){
        LblplayerTitle2.setVisible(true);
        Lblplayer2.setVisible(true);
        txtBoxplayer2.setVisible(true);
    }
    
    public GridPane addIntelligencePlayer(){
        GridPane grid = new GridPane();
        grid.setHgap(2);
        grid.setVgap(2);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.add(LblplayerTitle1, 1, 0);
        grid.add(Lblplayer1, 1, 1);
        grid.add(txtBoxplayer1, 2, 1);
        grid.add(LblplayerTitle2, 1, 2);
        grid.add(Lblplayer2, 1, 3);
        grid.add(txtBoxplayer2, 2, 3);
        return grid;
    }
    
    public HBox Top() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 100, 15, 200));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");

        Label label = new Label("Auswahl treffen:");
        label.setFont(new Font("Arial", 22));
        hbox.getChildren().addAll(label);
        return hbox;
    }
    
    public HBox Bottom() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #336699;");
        
        hbox.getChildren().addAll(reset, apply);
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                group.selectToggle(intelligence);
                txtBoxplayer1.clear();
                txtBoxplayer2.clear();
            }
        });
        
        apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //txtBoxplayer1.toString(), Value.CROSS);
                //txtBoxplayer2.clear();
                master.setPlayer1(txtBoxplayer1.getText(), Value.CROSS);
                if(!txtBoxplayer2.getText().isEmpty()){
                    master.setPlayer2(txtBoxplayer2.getText(), Value.NOUGHT);
                
                }
                System.out.println(master.getPlayer1());
                Platform.exit();
                
            }
        });
        
        return hbox;
    }
    
}
