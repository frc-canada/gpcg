package src.GPCG;
import java.util.ArrayList;


public class GPCGModel{


    //Define Variables
    private final int population = 1000000; //Set a population of 1000000
    private float difficulty = 1; // Set a difficulty
    private int rounds=5; // Set a number of rounds
    private int round = 0;
    private int numPlayers;
    private float averageFactor = 0.2f; //Not sure why this was used in the original game - the game was never commented!!!
    private float taxRate = 5;
    private float priceSum;
    private float priceAverage;
    private float adjustedSum;
    private float adjustedMinimum;
    private ArrayList<ArrayList<Float>> results = new ArrayList<ArrayList<Float>>();
    private ArrayList<String> companyNames = new ArrayList<String>();
    private ArrayList<Float> prices = new ArrayList<Float>(); //Declare a new empty array of prices
    private ArrayList<Float> priceAdjustment1 = new ArrayList<Float>();
    private ArrayList<Float> priceAdjustment2 = new ArrayList<Float>();
    private ArrayList<Integer> customers = new ArrayList<Integer>();
    private ArrayList<Float> revenue = new ArrayList<Float>();
    private ArrayList<Float> expenses = new ArrayList<Float>();
    private ArrayList<Float> profit = new ArrayList<Float>();
    private ArrayList<Float> total = new ArrayList<Float>();
    private Boolean botPlayers = false;
    private AutonomousPlay autoPlay =  new AutonomousPlay();
    
        
//Class Constructor
    public GPCGModel(){
	rounds = 5; //Set the number of rounds in the constructor
    numPlayers = 6; //Set the number of players
    System.out.println("Calling gpcg");
    if(companyNames.size()==0){
    companyNames.add("C1");
    companyNames.add("C2");
    companyNames.add("C3");
    companyNames.add("C4");
    companyNames.add("C5");
    companyNames.add("C6");

    for(int i = 0; i<6; i++){
        prices.add(50f);
        customers.add(0);
        revenue.add(0f);
        expenses.add(0f);
        profit.add(0f);
        total.add(0f);

        }
}
}

//One game turn:
    public boolean turn(ArrayList<Float> pricesInput){
    //Input the prices
    if(botPlayers){
        //Capture the game state to send to autoPlay
        GameDetails currentGameDetails = new GameDetails();
        currentGameDetails.setNames(companyNames);
        currentGameDetails.setPrices(prices);
        currentGameDetails.setCustomers(customers);
        currentGameDetails.setRevenue(revenue);
        currentGameDetails.setExpenses(expenses);
        currentGameDetails.setProfit(profit);
        currentGameDetails.setTotal(total);
        currentGameDetails.setRound(round);
        prices.clear();
        //Call the Autonomous Play class
        for(int i =0; i<numPlayers;i++){
            prices.add(autoPlay.strategy(i, currentGameDetails));
        }
    }
    else{
	prices = pricesInput;
	//Ensure that the following prices are of type float
    }
	//Validate the input
	if (!validate()){
	    //Check that the prices are valid
		return false;
		}
	//Calculate the price average
	priceAverage();
	//Calculate Adjustment1
	adjustment1();
	//Calculate Adjustment2
	adjustment2();
    //Determine the number of customers
    customers();
    //Determine the revenue
	revenue();
	//Deterimine the expenses
	expenses();
	//Determine the profit
	profit();
    //Calculate the running total cash positions of each company
    total();
    round++;
    System.out.println("Turn Successful");
    System.out.println(prices.size());
    return true;
	}        
    
    private boolean validate()  {
      //Input prices as a space-separated string of digits
        for(int priceEntry=0; priceEntry<numPlayers;priceEntry++){
            //If the price is too low or too high, return false
            if (prices.get(priceEntry) <= 0 || prices.get(priceEntry) >= 100){
                System.out.println("Please enter a price above 0 and less than  100");
                return false;
            }
         //If all prices are valid, return true
        }
        
        return true;
    }

    private void priceAverage(){
            priceSum = 0;
            for(float i : prices){
            priceSum+=i;
        }
        priceAverage = priceSum/numPlayers;
    
    }
    private void adjustment1(){
        priceAdjustment1.clear();
        for(float i : prices){
            priceAdjustment1.add(priceAverage-i);
            }

	}
    private void adjustment2(){
    priceAdjustment2.clear();
    adjustedMinimum = priceAdjustment1.get(0);
    System.out.println("Size of Price Adjustment");
    System.out.println(priceAdjustment1.size());
        for (float i : priceAdjustment1){
            adjustedMinimum = adjustedMinimum < i ? adjustedMinimum : i;
        }
        System.out.println("Adjustment 2 calculations");
        for(float i : priceAdjustment1){
        priceAdjustment2.add(i-adjustedMinimum+averageFactor*priceAverage);
        System.out.println(adjustedMinimum+averageFactor*priceAverage);
        }
	}
	
    private void customers(){
        //Add the second adjustments
        customers.clear();
        adjustedSum = 0;
        for(float i : priceAdjustment2){
            adjustedSum += i;
            System.out.println("Adjusted sum:");
            System.out.println(adjustedSum);
            }
        //Calculate the number of customers for each company as a proportion of the entire population:
        for (float i : priceAdjustment2){
            System.out.println("Customers calculation");
            System.out.println((int)(population*i/adjustedSum));
            customers.add((int)(population*i/adjustedSum));
            }
        
        System.out.println(customers.get(1));
	    }
	
    private void revenue(){
        //Calculate the Revenue for each company:
        revenue.clear();
        for(int i=0;i<numPlayers;i++){
            revenue.add(customers.get(i)*prices.get(i));
        }
	}
	
    private void expenses(){
        expenses.clear();
        for(int i=0;i<numPlayers;i++){
            expenses.add(taxRate*population + customers.get(i)*difficulty);
        }

	}
	
    private void profit(){
        profit.clear();
        for(int i=0;i<numPlayers;i++){
            profit.add(revenue.get(i) - expenses.get(i));
        }
        results.add(profit);       
    }
    
    private void total(){
        for(int i=0;i<numPlayers;i++){
            float previousTotal = total.get(i);
            previousTotal += (revenue.get(i) - expenses.get(i)); 
            total.set(i, previousTotal);
        }
    }

    //Public getters
    public String getName(int i){
        return companyNames.get(i);
        }

    public float getPrice(int i){
        return prices.get(i);
        }

    public float getCustomers(int i){
        return customers.get(i);
        }

    public float getRevenue(int i){
        return revenue.get(i);
        }
    
    public float getExpenses(int i){
        return expenses.get(i);
        }

    public float getProfit(int i){
        return profit.get(i);
        }
    
    public float getTotal(int i){
        return total.get(i);
        }

    public int getRound(){
        return round;
        }
        
    public ArrayList<Float> getResults(int i){
        return results.get(i);
    }
    public int getPlayers(){
            return numPlayers;
            }
    
    public String getLeader(){
        float greatestTotal = total.get(0);
        int greatestIndex = 0;
        for(int i=1; i<numPlayers; i++)
            if (total.get(i)>greatestTotal){
                greatestTotal = total.get(i);
                greatestIndex = i;
            }
        return companyNames.get(greatestIndex);
        }
    //Public setters

    public void newName(String newName){
        companyNames.add(newName);
        }

    public void setName(int i, String newName){
        companyNames.set(i, newName);
    }
    
    public void setPrice(int i, float newPrice){
        prices.set(i, newPrice);
    }

    public void setCustomers(int i, int newCustomers){
        customers.set(i, newCustomers);
    }

    public void setRevenue(int i, float newRevenue){
        revenue.set(i, newRevenue);
    }

    public void setExpenses(int i, float newExpenses){
        expenses.set(i, newExpenses);
    }

    public void setProfit(int i, float newProfit){
        profit.set(i, newProfit);
    }

    public void setResults(int i, ArrayList<Float> newResults){
        results.add(newResults);
    }
    public void setRounds(int r){
        rounds = r;
        }
    public void setPlayers(int n){
        numPlayers = n;
        }
}



    

   

