package app;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	int size=800;
    	Board b= new Board(20,size-20,20,size-20);
        primaryStage.setTitle("Owl Square");
        Group root = new Group();
        Scene scene = new Scene(root, size, size, Color.WHITE);
             
        for (Owl current_owl : b.get_owl()) {
			root.getChildren().add(new OwlCircle(current_owl));
		}
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

