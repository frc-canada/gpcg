package src.GPCG;
import java.util.ArrayList;
import java.util.Random;


public class AutonomousPlay {
    
    
    public AutonomousPlay(){

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
                    return lastPrices.get(5);
                }
                else{
                    return 10.12f;
                }
            case 3:
                //What is the average of all of the prices?
                float Total =0;
                int numPlayers = 6;
                for(int i = 0; i<numPlayers; i++){
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

    private float strategy1(GameDetails currentGameDetails){
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
                return strategy1(currentGameDetails);
            case 1:
                return strategy_1234(currentGameDetails);
            case 2:
                return strategy1(currentGameDetails);
            case 3:
                return strategy1(currentGameDetails);
            case 4:
                return strategy1(currentGameDetails);
            case 5:
                return strategy1(currentGameDetails);
            default:
                return strategy1(currentGameDetails);
        }

    }


}
