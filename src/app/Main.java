package app;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
 	                b.addFood(new Vector2((float)me.getSceneX(),(float)me.getSceneY()), root);
 	                root.getChildren().add(b.get_food().get(b.get_food().size()-1));	            
 	                }
 	        }); 

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	System.exit(0);
            }
        });
        
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	checkOwlPosition(b); 
            	checkFood(b);  	
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
    
	public void checkOwlPosition(Board b) {
    	for (Owl owl : b.get_owl()) {
    		b.get_owls_circles().get(b.get_owl().indexOf(owl)).get_owlBody().setCenterX(owl.get_pos().get_x());
    		b.get_owls_circles().get(b.get_owl().indexOf(owl)).get_owlBody().setCenterY(owl.get_pos().get_y());
    	} 
    }
	
	public synchronized void checkFood(Board b) {
		int i=0;
    	for (i=0;i<b.get_food().size();i++) {
    		b.get_food().get(i).getting_older();
    		if(b.get_food().get(i).isFresh()) {
    			FillTransition ft = new FillTransition(Duration.millis(1000), b.get_food().get(i).get_body(), Color.GREEN, Color.BROWN);
    			ft.setAutoReverse(true);
    			ft.play();
    		}
    		if (b.get_food().get(i).get_freshness()<-50.0) {
    			b.get_food().get(i).removeFoodCircle();
    			b.get_food().remove(i);
    		}
    	}
    	for (i=0;i<b.get_expired().size();i++) {
    		b.get_expired().get(i).getting_older();
    		if (b.get_expired().get(i).get_freshness()<-50.0) {
    			b.get_expired().get(i).removeFoodCircle();
    			b.get_expired().remove(i);
    		}
    	} 
    }
}

