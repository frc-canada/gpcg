
package src.GPCG;
//import javax.swing.table.TableColumn;
//import javax.swing.text.TableView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.TextFieldTableCell;
//import javax.swing.JOptionPane;
//import javafx.application.Platform;
import javafx.event.ActionEvent;
//import javafx.event.EventType;
import javafx.fxml.FXML;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
import java.util.ArrayList;

public class GPCGController{
    private GPCGModel game;
    private ArrayList<Float> currentPrices = new ArrayList<Float>(); 
    private ArrayList<String> currentNames = new ArrayList<String>(); 
    private int round;
    @FXML Label actiontarget;
    @FXML  Label roundNumber;
    @FXML  Label currentLeader;
    @FXML  TableView<Company> tblCompanies;
    @FXML TableColumn<Company,String> companyColumn;
    @FXML TableColumn<Company,String> priceColumn;
    @FXML TableColumn<Company,String> customersColumn;
    @FXML TableColumn<Company,String> revenueColumn;
    @FXML TableColumn<Company,String> expensesColumn;
    @FXML TableColumn<Company,String> profitColumn;
    @FXML TableColumn<Company,String> totalColumn;
    @FXML  void handleSubmitButtonAction(ActionEvent event) {
        currentPrices.clear();
        currentNames.clear();
        System.out.println("Getting Names and Prices");
        for(int i = 0; i<6;i++){
          
            System.out.println(i);
            currentPrices.add(Float.parseFloat(tblCompanies.getColumns().get(1).getCellObservableValue(i).getValue().toString()));
            currentNames.add((tblCompanies.getColumns().get(0).getCellObservableValue(i).getValue().toString()));
                }
            System.out.println("Length of Prices in main :");
            System.out.println(currentPrices.size());
        game.turn(currentPrices);
        updateTable();
        }

    public void updateTable(){
        //Drop old data from the Table
        tblCompanies.getItems().clear();
        
        //Get New Data for the Table
        System.out.println("Length of Current Prices - loadTable");
        System.out.println(currentPrices.size());
        System.out.println("Calling getPrice - loadTable");
        System.out.println(game.getPrice(1));
        System.out.println(game.getRound());
        round = game.getRound();
        currentLeader.setText(game.getLeader());
        
        //Update the table
        for(int i = 0; i<game.getPlayers(); i++){
            //System.out.println(game.getName(i));
            tblCompanies.getItems().add(new Company(currentNames.get(i), Float.toString(game.getPrice(i)),String.format("%.0f", game.getCustomers(i)), Float.toString(game.getRevenue(i)), Float.toString(game.getExpenses(i)), Float.toString(game.getProfit(i)),Float.toString(game.getTotal(i))));
        
        }
    }

    public GPCGController(){
        game = new GPCGModel();
            }
    
    @FXML public void initialize(){
        System.out.println("GameController initializing");
        roundNumber.setText(String.valueOf(game.getRound()));
        currentLeader.setText(game.getLeader());
        //tblCompanies.getItems().clear();
        tblCompanies.setEditable(true);
        //nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //priceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        companyColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyColumn.setCellFactory(TextFieldTableCell.<Company>forTableColumn());
        companyColumn.setOnEditCommit(
            (TableColumn.CellEditEvent<Company, String> t) -> {
                ((Company) t.getTableView().getItems()
                .get(t.getTablePosition().getRow()))
                .setName(t.getNewValue());
            });
        companyColumn.setOnEditCommit(event -> {
                final String cname = event.getNewValue() != null ? event.getNewValue() :
                        event.getOldValue();
                    ((Company) event.getTableView().getItems()
                        .get(event.getTablePosition().getRow()))
                    .setName(cname);
                    tblCompanies.refresh();
                });

            
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setCellFactory(TextFieldTableCell.<Company>forTableColumn());
        priceColumn.setOnEditCommit(
            (TableColumn.CellEditEvent<Company, String> t) -> {
                ((Company) t.getTableView().getItems()
                .get(t.getTablePosition().getRow()))
                .setPrice(t.getNewValue());
            });
        priceColumn.setOnEditCommit(event -> {
                final String price = event.getNewValue() != null ? event.getNewValue() :
                        event.getOldValue();
                    ((Company) event.getTableView().getItems()
                        .get(event.getTablePosition().getRow()))
                    .setPrice(price);
                    tblCompanies.refresh();
                });
        customersColumn.setCellValueFactory(new PropertyValueFactory<>("customers"));
        revenueColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        expensesColumn.setCellValueFactory(new PropertyValueFactory<>("expenses"));
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        currentNames.clear();
        for(int i = 0; i<6;i++){
            currentNames.add(game.getName(i));
        }
        
        for(int i = 0; i<6; i++){
            
            
            System.out.println(currentNames.get(i));
            tblCompanies.getItems().add(new Company(currentNames.get(i), Float.toString(game.getPrice(i)),String.format("%.0f", game.getCustomers(i)), Float.toString(game.getRevenue(i)), Float.toString(game.getExpenses(i)), Float.toString(game.getProfit(i)),Float.toString(game.getTotal(i))));
        }

    }

   // public void play(){
    //    System.out.println("The play button has been pressed.");
    //}
}

/*
    int round;
    private ArrayList<Float> currentPrices = new ArrayList<Float>(); 
    private ArrayList<String> currentNames = new ArrayList<String>();
  
    game.gpcg();
    game.setRounds(5);
    game.setPlayers(6);
    round = game.getRound();
    currentPrices.clear();
    currentNames.clear();
    for(int i=0;i<game.getPlayers();i++){
        currentPrices.add(game.getPrice(i));
        currentNames.add("C");
    }

    roundNumber.setText("123");
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setOnShown( (evt) -> loadTable(tblCompanies) );
        primaryStage.show();


        FXML private void loadTable(TableView<Company> tblCompanies) {
        
            System.out.println("Length of Current Prices - loadTable");
            System.out.println(currentPrices.size());
            System.out.println("Calling getPrice - loadTable");
            System.out.println(game.getPrice(1));
            System.out.println(game.getRound());
            round = game.getRound();
            
            
            if (currentLeader!=null){
                currentLeader.setText(game.getLeader());
            }
            if (tblCompanies!=null){
            for(int i = 0; i<game.getPlayers(); i++){
                //System.out.println(game.getName(i));
                tblCompanies.getItems().add(new Company(currentNames.get(i), Float.toString(game.getPrice(i)),String.format("%.0f", game.getCustomers(i)), Float.toString(game.getRevenue(i)), Float.toString(game.getExpenses(i)), Float.toString(game.getProfit(i)),Float.toString(game.getTotal(i))));
            }
        }
        }
}
*/