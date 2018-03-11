package app;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	int size=800;
    	int border=1;
        primaryStage.setTitle("Owl Square");
        Group root = new Group();
        Scene scene = new Scene(root, size, size, Color.LIGHTBLUE);
        
        Board b= new Board(border,size-border,border,size-border, root);
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){//on ajoute de la nourriture
 	            public void handle(MouseEvent me){
 	                System.out.println(me.getSceneX() + " " + me.getSceneY());
 	                b.addFood(new Vector2((float)me.getSceneX(),(float)me.getSceneY()), root);
 	                //root.getChildren().add(new FoodCircle(b.get_food().get(b.get_food().size() - 1)));	            
 	                }
 	        }); 

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

