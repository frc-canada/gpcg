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

/*



import javax.swing.table.TableColumn;
import javax.swing.text.TableView;
import javax.swing.text.html.ImageView;

import javafx.application.Application;
import javafx.event.ActionEvent;
//import javafx.scene.control.TableView;
//import javafx.scene.layout.VBox;
//import javafx.scene.layout.HBox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import jdk.internal.org.jline.terminal.MouseEvent.Button;
import jdk.tools.jlink.internal.Platform;

public class VBoxAndHBoxApp extends Application {
    GPCG game = new GPCG();  
    TableView<Company> tblCompanies;
    Label roundNumber;
    Label currentLeader;
    private ArrayList<Float> currentPrices = new ArrayList<Float>(); 
    private ArrayList<String> currentNames = new ArrayList<String>(); 
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        
        
    	
        
        
        for(float i: currentPrices){System.out.println(i);}
        
        Button btnPlay = new Button("Play");
        btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                currentPrices.clear();
                currentNames.clear();
                for(int i = 0; i<game.getPlayers();i++){
                    currentPrices.add(Float.parseFloat(tblCompanies.getColumns().get(1).getCellObservableValue(i).getValue().toString()));
                    currentNames.add((tblCompanies.getColumns().get(0).getCellObservableValue(i).getValue().toString()));
                }
                System.out.println("Length of Prices in main :");
                System.out.println(currentPrices.size());
                game.turn(currentPrices);
                System.out.println(currentPrices.size());
                tblCompanies.getItems().clear();
                loadTable(tblCompanies);
                
                System.out.println("Calling getPrice - handle");
                for(int i = 0; i<6;i++){
                    currentPrices.set(i,game.getPrice(i));
                }
                System.out.println("End of Handle:");
                System.out.println(currentPrices.size());
                for(int i=0;i<6;i++){
                    System.out.println(game.getPrice(i));
                }
               
            }
        });
        
            

        TableColumn<Company, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.<Company>forTableColumn());
        nameCol.setOnEditCommit(
            (TableColumn.CellEditEvent<Company, String> t) -> {
                ((Company) t.getTableView().getItems()
                .get(t.getTablePosition().getRow()))
                .setName(t.getNewValue());

            });

        TableColumn<Company, String> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceCol.setCellFactory(TextFieldTableCell.<Company>forTableColumn());
        priceCol.setOnEditCommit(
            (TableColumn.CellEditEvent<Company, String> t) -> {
                ((Company) t.getTableView().getItems()
                .get(t.getTablePosition().getRow()))
                .setPrice(t.getNewValue());

            });
        TableColumn<Company, String> customersCol = new TableColumn<>("Customers");
        customersCol.setCellValueFactory(new PropertyValueFactory<>("customers"));
      
        TableColumn<Company, String> revenueCol = new TableColumn<>("Revenue");
        revenueCol.setCellValueFactory(new PropertyValueFactory<>("revenue"));

        TableColumn<Company, String> expensesCol = new TableColumn<>("Expenses");
        expensesCol.setCellValueFactory(new PropertyValueFactory<>("expenses"));

        TableColumn<Company, String> profitCol = new TableColumn<>("Profit");
        profitCol.setCellValueFactory(new PropertyValueFactory<>("profit"));

        TableColumn<Company, String> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        tblCompanies.getColumns().addAll( nameCol, priceCol, customersCol, revenueCol, expensesCol, profitCol, totalCol);

        Separator sep = new Separator();

        HBox bottomControls = new HBox();
        bottomControls.setAlignment(Pos.BOTTOM_RIGHT );
        VBox.setMargin( bottomControls, new Insets(10.0d) );

        Button btnClose = new Button("Close");
        btnClose.setFont(labelFont);
        btnClose.setOnAction(e -> Platform.exit());

        bottomControls.getChildren().add( btnClose );

        vbox.getChildren().addAll(
                imageView,
                titleHBox,
		topControls,
                tblCompanies,
                sep,
                bottomControls
        );

        Scene scene = new Scene(vbox );
        scene.setFill(Color.web("#FFFFFF"));
        //scene.getStylesheets().add("css/stylesheet.css");
        File f = new File("css/stylesheet.css");
        scene.getStylesheets().clear();
        //scene.getStylesheets().add(getClass().getResource("/home/wneal/Lemma/FRC/Java/JavaFX/VBoxAndHBoxApp/css/stylesheet.css").toExternalForm());
        primaryStage.setScene( scene );
        primaryStage.setWidth( 800 );
        primaryStage.setHeight( 600 );
        primaryStage.setTitle("VBox and HBox App");
        primaryStage.setOnShown( (evt) -> loadTable(tblCompanies) );
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void loadTable(TableView<Company> tblCompanies) {
        System.out.println("Length of Current Prices - loadTable");
        System.out.println(currentPrices.size());
        System.out.println("Calling getPrice - loadTable");
        System.out.println(game.getPrice(1));
        roundNumber.setText(String.valueOf(game.getRound()));
        currentLeader.setText(game.getLeader());
        for(int i = 0; i<game.getPlayers(); i++){
            //System.out.println(game.getName(i));
            tblCompanies.getItems().add(new Company(currentNames.get(i), Float.toString(game.getPrice(i)),String.format("%.0f", game.getCustomers(i)), Float.toString(game.getRevenue(i)), Float.toString(game.getExpenses(i)), Float.toString(game.getProfit(i)),Float.toString(game.getTotal(i))));
        }
    }
}






*/