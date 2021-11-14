package src.GPCG;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javafx.scene.Scene;

/*
Main application

This application reads the FXML file, GPCGFXML.fxml and creates a main window based on that file.
The FXML file calls  on the following:
    (a) the GPCController class which, in turn, calls the CPCGModel (game data) and Company.java 
    (details about each player's 'company'
    (b) fxmlstyle.css - the CSS style sheet for the application

*/
public class GPCGApp extends Application{
    public static final String UI_FORM = "src/GPCG/GPCGFXML.fxml";
    
    
    public static void main(String[] args) {
        launch(args);
            }

    @Override
    public void start(Stage stage){ 
        //Call an FXML form; check for errors
        Parent parent = null;
        URL form = this.getClass().getClassLoader().getResource(UI_FORM);
        System.out.println(form);
		if (form == null) {
			Logger.getLogger("FXMLExample").log(Level.SEVERE, "Couldn't file FXML form " + UI_FORM);
			return;
			}
			try {
				parent = FXMLLoader.load(form);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
            }
               
        //Initialize the application
        Scene scene = new Scene(parent);
        stage.setTitle("GPC Game");
        stage.setScene(scene);
        stage.show();
        
        }  
    
  
    
}

