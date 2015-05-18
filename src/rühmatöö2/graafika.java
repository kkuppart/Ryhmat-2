package rühmatöö2;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class graafika extends Application {

	@Override
	public void start(Stage peaLava) {
		 int ulatus=10;
	    Group juur = new Group(); // luuakse juur
	     
	   // for(int i=0;i<ulatus;i++){
	    //	for(int j=0;j<ulatus;j++) {
	    
	    //Rectangle ristkülik1 = new Rectangle(100+10*i-4,100+10*i+4,100+10*j-4,100+10*j-4);
	     
	     for(int i=0;i<ulatus;i++) {
	    	for(int j=0;j<ulatus;j++) {
	     
	     Circle ring = new Circle(100+15*i,100+15*j,5);
	     //Rectangle ristkülik2 = new Rectangle(1,34,34,65);
	    ring.setFill(Color.GREEN);
	     juur.getChildren().add(ring);  // ristkülik lisatakse juure alluvaks
	     //juur.getChildren().add(ristkülik2);
	     }
	    
	     }
	     Scene stseen1 = new Scene(juur, 535, 535, Color.SNOW);  // luuakse stseen  
	 
	    peaLava.setTitle("Must ruut");  // lava tiitelribale pannakse tekst
	 
	    peaLava.setScene(stseen1);  // lavale lisatakse stseen
	 
	    peaLava.show();  // lava tehakse nähtavaks
	    
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
