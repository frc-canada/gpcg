package src.GPCG;
import java.util.ArrayList;
import java.util.Random;


public class AutonomousPlay {
    
    ArrayList<Float> highestPrices= new ArrayList<Float>();
    public AutonomousPlay(){

    }

    private float validate(float price){
        if (price >=100f){
            return 99.99f;
        }
        if (price <=0f){
            return 0.01f;
        }
        return price;
    }

    private float strategy_6854(GameDetails currentGameDetails) {
        ArrayList <Float> previousPrices = currentGameDetails.getPrices(); //previousPrices is now a local variable
		if (currentGameDetails.getRound() == 1) { // first round, start with 80 first;
			return 80;
		}
		// second to fifth round
		float cost_per_item; // cost per sweater
		float sold_default; // number sold at default price
		float sold_more_dec; // number more sold per 1$ decrease in price
		cost_per_item = currentGameDetails.getExpenses().get(currentGameDetails.getMyIndex())
						/ currentGameDetails.getCustomers().get(currentGameDetails.getMyIndex());
		int another_team = currentGameDetails.getMyIndex() + 1;
		if (another_team >= 6) {
			another_team = 0;
		}
		sold_more_dec = (currentGameDetails.getCustomers().get(currentGameDetails.getMyIndex())
						 - currentGameDetails.getCustomers().get(another_team))
						/ (currentGameDetails.getPrices().get(another_team)
						   - currentGameDetails.getPrices().get(currentGameDetails.getMyIndex()));
		sold_default
			= currentGameDetails.getCustomers().get(currentGameDetails.getMyIndex())
			  - (100 - currentGameDetails.getPrices().get(currentGameDetails.getMyIndex())) * sold_more_dec;
		float opt_price = (sold_default + 100 * sold_more_dec + sold_more_dec * cost_per_item)
						  / (2 * sold_more_dec); // set derivative to zero and solve for x
		// check endpoints
		if (opt_price <= 0
			|| opt_price >= 100) { // maxima of profit is out of domain, test endpoint
			float profit_at_zero
				= (0 - cost_per_item) * (sold_default + 100 * sold_more_dec - sold_default * 0);
			float profit_at_hundred
				= (100 - cost_per_item) * (sold_default + 100 * sold_more_dec - sold_default * 100);
			if (profit_at_zero > profit_at_hundred) {
				return (float)0.5;
			} else {
				return (float)99.5;
			}
		} else {
			return opt_price;
		}
	}


    private float strategy_6865(GameDetails currentGameDetails){

        int r = currentGameDetails.getRound(); //What is the current round? Set r to the current round
        int myIndex = currentGameDetails.getMyIndex(); //This is my player's index (0,1,2,3,4, or 5)
        int numPlayers = 6;
        ArrayList<Float> lastProfits = currentGameDetails.getProfit(); //Returns an ArrayList of the profits
        ArrayList<Float> lastRevenue = currentGameDetails.getRevenue(); //Returns an ArrayList of the revenue
        ArrayList<Float> lastExpenses = currentGameDetails.getExpenses(); //Returns an ArrayList of the expenses
         ArrayList<Float> lastPrices = currentGameDetails.getPrices(); //Returns an ArrayList
         ArrayList<Integer> lastCustomers = currentGameDetails.getCustomers(); //Returns an ArrayList of the number of customers
        float lowestPrice = 100f; //Set a price higher than the highest possible price... to begin
        int lowestPriceIndex; //Returns an the Index of the lowest price
        float myProfit = lastProfits.get(myIndex); //myProfit is my profit from the last round
        float costUnit = (lastRevenue.get(myIndex) - lastExpenses.get(myIndex))/ lastCustomers.get(myIndex);
        
       
        switch(r){
            case 1: //trying to set my price to 20
                return(20.00f);
           
            case 2: //trying to set my price to cost per unit but if it is over 100 return the next if
              
                 
                  if (costUnit+5>99.99f) {return 99.99f;}
                    else {return costUnit+5;}
                
                 
               

            case 3: //trying to return the lowest price -1 as lost as it is more than my price per unit
              
                for (int i = 0; i<numPlayers; i++)
                {if (lastPrices.get(i) < lowestPrice)
            
               lowestPriceIndex = i;  
              }
                if (lowestPrice < costUnit)
                  {return(costUnit + 2f);
                }
             
             
              else {return (costUnit - 1f);}
              

            case 4: //trying to return the lowest price -1 as lost as it is more than my price per unit
              

           
                    for (int i = 0; i<numPlayers; i++)
                    {if (lastPrices.get(i) < lowestPrice)
                   lowestPriceIndex = i;  

                  }
                    if (lowestPrice < costUnit)
                      {return(costUnit + 3f);
                    }
                 
                 
                  else {return (costUnit - 1f);}
           
            

            case 5: //trying to return the average of prices + 1
              float Total =0;
              for(int i = 0; i<numPlayers; i++){
                Total+=lastPrices.get(i);
              }
              float average = Total/numPlayers; //This is the average price
              float finalPrice = average+1f;
           
              if(finalPrice< 100 && finalPrice > 0){
                return finalPrice; //Be careful!!! It might be out of bounds!!!
              }
              else{

                if(costUnit < 100 && costUnit > 0)
                {
                  return (costUnit + 10f);
                }

                else {return (99.99F);}
              }
            
            }
            return 50f;
            
        }

        private float strategy_2324(GameDetails currentGameDetails){
            int r = currentGameDetails.getRound(); //What is the current round? Set r to the current round
            int myIndex = currentGameDetails.getMyIndex(); //This is my player's index (0,1,2,3,4, or 5)
            ArrayList<Float> lastProfits = currentGameDetails.getProfit(); //Returns an ArrayList of the profits
            float myProfit = lastProfits.get(myIndex); //myProfit is my profit from the last round
            ArrayList<Float> lastPrices = currentGameDetails.getPrices(); //Returns an ArrayList
            float myPrice = lastPrices.get(myIndex);
            
            float returnPrice;
     
            switch (r) {
                case 1:
                    returnPrice = 45.99f;
                    return (returnPrice);
                case 2:
                    float maxNum = 0;
                    int i = 0;
                    int proIndex = 0;
     
                    while (i < 6){
                        float proNum = lastProfits.get(i);
                       if (maxNum < proNum){
                           proIndex = i;
                           maxNum = proNum;
                       }
                       i ++;
                    }
                   
                    highestPrices.add(lastPrices.get(proIndex));
                    float averagePrice = (highestPrices.get(0) + myPrice)/2;
                    returnPrice = Math.abs(averagePrice - 10);
     
                    return (returnPrice);
                case 3:
                    maxNum = 0;
                    i = 0;
                    proIndex = 0;
     
                        while (i < 6){
                            float proNum = lastProfits.get(i);
                            if (maxNum < proNum){
                                proIndex = i;
                                maxNum = proNum;
                            }
                            i ++;
                        }
               
                    highestPrices.add(lastPrices.get(proIndex));
                    averagePrice = (highestPrices.get(0) + highestPrices.get(1)+ myPrice)/3;
                    returnPrice = Math.abs(averagePrice - 10);
               
                    return (returnPrice);
                case 4:
                    maxNum = 0;
                    i = 0;
                    proIndex = 0;
     
                        while (i < 6){
                            float proNum = lastProfits.get(i);
                            if (maxNum < proNum){
                                proIndex = i;
                                maxNum = proNum;
                            }
                            i ++;
                        }
               
                    highestPrices.add(lastPrices.get(proIndex));
                    averagePrice = (highestPrices.get(0)+highestPrices.get(1) +highestPrices.get(2) + myPrice)/4;
                    returnPrice = Math.abs(averagePrice - 10);
               
                    return (returnPrice);
                case 5:
                    maxNum = 0;
                    i = 0;
                    proIndex = 0;
     
                        while (i < 6){
                            float proNum = lastProfits.get(i);
                            if (maxNum < proNum){
                                proIndex = i;
                                maxNum = proNum;
                            }
                            i ++;
                        }
                   
                    highestPrices.add(lastPrices.get(proIndex));
                    averagePrice = (highestPrices.get(0)+ highestPrices.get(1) + highestPrices.get(2)+ highestPrices.get(3)+ myPrice)/5;
                    returnPrice = Math.abs(averagePrice - 10);
               
                    return (returnPrice);
                default:
                    return 45.99f;
            }
        }
    

    private float strategy_1234(GameDetails currentGameDetails){

        int r = currentGameDetails.getRound(); //What is the current round? Set r to the current round
        int myIndex = currentGameDetails.getMyIndex(); //This is my player's index (0,1,2,3,4, or 5)
        ArrayList<Float> lastProfits = currentGameDetails.getProfit(); //Returns an ArrayList of the profits
        float myProfit = lastProfits.get(myIndex); //myProfit is my profit from the last round
        ArrayList<Float> lastPrices = currentGameDetails.getPrices(); //Returns an ArrayList
        switch(r){
            case 1:
                return(35.40f);
            case 2:
                ArrayList<Float> p = currentGameDetails.getProfit(); //A list of 6 profit values
                
                if(p.get(4)>0){ 
                    //Check the 5th one (starts counting at 0)
                    System.out.println("Size of lastPrices:");
                    System.out.println(lastPrices.size());
                    return lastPrices.get(5);
                }
                else{
                    return 10.12f;
                }
            case 3:
                //What is the average of all of the prices?
                float Total =0;
                int numPlayers = 6;
                System.out.println("Printing prices: ");
                System.out.println(lastPrices.size());
                for(int i = 0; i<numPlayers; i++){
                    System.out.println(lastPrices.get(i));
                    Total+=lastPrices.get(i);
                }
                float average = Total/numPlayers; //This is the average price
                float finalPrice = average+5f;
                if(finalPrice< 100 && finalPrice > 0){
                    return finalPrice; //Be careful!!! It might be out of bounds!!!
                }
                else{
                    return 50f;
                }

            case 4:
                return 90f;

            case 5:
                return 70f;

            default:
                return 50f;

        }

    }

    private float strategy_random(GameDetails currentGameDetails){
        float min = 0.0f;
        float max = 100.0f;
        Random r = new Random();
        ArrayList<String> n = new ArrayList<String>();
        n = currentGameDetails.getNames();
        System.out.println(n.get(2));
        float randomNumber = min + r.nextFloat() * (max - min);

        return randomNumber;

        }
    public float strategy(int i, GameDetails currentGameDetails){
        switch(i){
            case 0:
                return validate(strategy_6865(currentGameDetails));
            case 1:
                return validate(strategy_2324(currentGameDetails));
            case 2:
                return validate(strategy_6854(currentGameDetails));
            case 3:
                return validate(strategy_random(currentGameDetails));
            case 4:
                return validate(strategy_random(currentGameDetails));
            case 5:
                return validate(strategy_random(currentGameDetails));
            default:
                return validate(strategy_random(currentGameDetails));
        }

    }


}
