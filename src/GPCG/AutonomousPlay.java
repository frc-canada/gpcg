package src.GPCG;
import java.util.ArrayList;
import java.util.Random;


public class AutonomousPlay {
    
    
    public AutonomousPlay(){

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
                return strategy1(currentGameDetails);
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
