package src.GPCG;
import java.util.ArrayList;

public class GameDetails {
    private ArrayList<String> companyNames = new ArrayList<String>();
    private ArrayList<Float> prices = new ArrayList<Float>(); 
    private ArrayList<Integer> customers = new ArrayList<Integer>();
    private ArrayList<Float> revenue = new ArrayList<Float>();
    private ArrayList<Float> expenses = new ArrayList<Float>();
    private ArrayList<Float> profit = new ArrayList<Float>();
    private ArrayList<Float> total = new ArrayList<Float>();
    public GameDetails(){
        
    }

    public ArrayList<String> getNames(){
        return companyNames;
    }

    public ArrayList<Integer> getCustomers(){
        return customers;
    }

    public ArrayList<Float> getPrices(){
        return prices;
    }
            
    public ArrayList<Float> getRevenue(){
        return revenue;
    }

    public ArrayList<Float> getExpenses(){
        return expenses;
    }

    public ArrayList<Float> getTotals(){
        return total;
    }

    public ArrayList<Float> getProfit(){
        return profit;
    }

    public void setNames(ArrayList<String> n){
        companyNames = n;
    }

    public void setPrices(ArrayList<Float> p){
        prices = p;
    }

    public void setCustomers(ArrayList<Integer> c){
        customers = c;
    }

    public void setRevenue(ArrayList<Float> r){
        revenue = r;
    }

    public void setExpenses(ArrayList<Float> e){
        expenses = e;
    }

    public void setProfit(ArrayList<Float> p){
        profit = p;
    }

    public void setTotal(ArrayList<Float> t){
        total = t;
    }
}
