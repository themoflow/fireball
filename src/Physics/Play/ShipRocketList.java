package Physics.Play;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;


public class ShipRocketList {

    private List<Rocket> rockets = new ArrayList<Rocket>();
    private MainGamePanel gamePanel;


    public ShipRocketList(MainGamePanel g){

        gamePanel = g;

    }

    public void createRockets(){

        for(int i = 0; i < 70; i++)
        {
            rockets.add(new Rocket(gamePanel, (i*2)*(-Rocket.getHeight())));

        }
    }

    public void speedUp(){
        for(int i = 0; i < rockets.size(); i++)
            rockets.get(i).speedUp();
    }

    public void setSpeed(int num){
        for(int i = 0; i < rockets.size(); i++)
            rockets.get(i).setSpeed(num);

    }

    public void move(){

        for(int i = 0; i < rockets.size(); i++)
        {
            rockets.get(i).move();
        }
    }

    public void draw(Canvas c){

        for(int i = 0; i < rockets.size(); i++)
        {
            rockets.get(i).draw(c);
        }
    }

    public int getSize(){
        return rockets.size();
    }

    public int checkRocketAmount(){

        switch(rockets.size())
        {
            case 60:
                return 60;
            case 40:
                return 40;
            case 20:
                return 20;
            case 0:
                return 0;
        }
        return 0;
    }

    public void checkForSpeed(int num){

        switch(num)
        {
            case 60:
                for (int i = 0; i < rockets.size(); i++)
                {
                    if(rockets.get(i).getSpeed() != 2 && !(rockets.get(i).getY() > 0))
                        rockets.get(i).speedUp();
                }
                break;
            case 40:
                for (int i = 0; i < rockets.size(); i++)
                {
                    if(rockets.get(i).getSpeed() != 3 && !(rockets.get(i).getY() > 0))
                        rockets.get(i).speedUp();
                }
                break;
            case 20:
                for (int i = 0; i < rockets.size(); i++)
                {
                    if(rockets.get(i).getSpeed() != 4 && !(rockets.get(i).getY() > 0))
                        rockets.get(i).speedUp();
                }
                break;
            case 0:
                gamePanel.setLevel(2);
                for(int i = 0; i < 10; i++)
                {
                    gamePanel.addNewSpaceship();
                }
                break;
        }

    }

    public List<Rocket> getArray(){
        return rockets;
    }

    public void add(float x, float y){
        rockets.add(new Rocket(gamePanel,x,y));
    }
}
