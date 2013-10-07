/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dicegui;
import dicegui.Die;
import dicegui.Die.LoadedDie;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sean
 */
public class DiceGUI extends Application {


@Override    
public void start(Stage primaryStage) {

//Read in user input about the game die
Scanner scanner = new Scanner(System.in);
System.out.println("How many sides would you like the die to have?");
final int sides = scanner.nextInt();
System.out.println("Would you like the die to be loaded (Enter 1) or fair (Enter 2)?");
int loaded_fair = scanner.nextInt();

final Die temp = new Die(); //need Die Object to create instance of LoadedDie
final LoadedDie GameDie = temp.new LoadedDie(sides); //LoadedDie is fair by default

if(loaded_fair == 1){
    System.out.println("Choose a loaded side for your die");
    int load_side = scanner.nextInt();
    System.out.println("Choose a load percentage for your die (integer value)");
    int load_percent = scanner.nextInt();
    GameDie.LoadPercentage = load_percent;
    GameDie.LoadedSide = load_side;
}



//Create Label to show Point Value in GUI
int Init_Point = 0;

//congifure grid
primaryStage.setTitle("A Simple Game of Dice");
final GridPane grid = new GridPane();
grid.setPrefSize(400, 400);
grid.setAlignment(Pos.CENTER);
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(25,25,25,25));
Text scenetitle = new Text("Welcome");
scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
grid.add(scenetitle, 0, 0, 2, 1);

//create and add roll button
Button btn = new Button("Roll");
HBox hbBtn = new HBox(10);
hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
hbBtn.getChildren().add(btn);

btn.setOnAction(new EventHandler(){
            @Override
            public void handle(Event event) {
                
                int newvalue = GameDie.roll();
                GameDie.value = newvalue;
                
                //Create Text to label the game die in GUI
                String Str_DieValue = Integer.toString(GameDie.value);
                Text DieValue = new Text(Str_DieValue);
                
                //Create Die Visual
                StackPane stack = new StackPane();
                Rectangle Die_visual = new Rectangle(30,30);
                Die_visual.setFill(Color.WHITE);
                Die_visual.setStroke(Color.BLACK);
                stack.getChildren().addAll(Die_visual, DieValue);
                grid.add(stack, 4, 4, 2, 2);
            }
        });

grid.add(hbBtn, 10, 10);
       
Scene scene = new Scene(grid, 500, 400);
primaryStage.setScene(scene);
        primaryStage.show();
    }
;
    
    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
