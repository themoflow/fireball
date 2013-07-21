package Physics.Play;


import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SpaceshipList {

    private List<Spaceship> spaceships = new ArrayList<Spaceship>();
    private MainGamePanel gamePanel;
    private float scrW, scrH;

    public SpaceshipList(MainGamePanel g){
        gamePanel = g;
        scrW = gamePanel.getScrWidth();
        scrH = gamePanel.getScrHeight();

    }

    public void add(ShipRocketList srl){
        spaceships.add(new Spaceship(gamePanel, scrW, scrH, srl));
    }

    public void move(){

        if(spaceships.size() > 0)
        {
            spaceships.get(0).move();
        }

    }

    public void draw(Canvas c){

        if(spaceships.size() > 0)
        {
            spaceships.get(0).draw(c);
        }

    }
}
