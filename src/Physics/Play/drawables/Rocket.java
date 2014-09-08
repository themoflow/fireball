package Physics.Play.drawables;

import Physics.Play.main.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rocket extends Drawable {

    private Paint paint = new Paint();
    private static float width, height, scrWidth, scrHeight;
    private int add = 4;
    private float cityX, cityY;
    private Bitmap rocketB;
    private Random rand = new Random();
    private List<Rocket> rockets = new ArrayList<Rocket>();
    private MainGamePanel gamePanel;

    public Rocket(MainGamePanel g) {
        this.gamePanel = g;
    }

    public Rocket(MainGamePanel g, float y) {
        rocketB = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket);
        width = rocketB.getWidth();
        height = rocketB.getHeight();
        scrWidth = g.getScrWidth();
        scrHeight = g.getScrHeight();
        setx(generateRandomNumber());
        sety(y);
        add = 1;
    }

    public Rocket(MainGamePanel g, float x, float y) {
        rocketB = BitmapFactory.decodeResource(g.getResources(), R.drawable.rocket);
        width = rocketB.getWidth();
        height = rocketB.getHeight();
        scrWidth = g.getScrWidth();
        scrHeight = g.getScrHeight();
        setx(x);
        sety(y);
        add = 1;
        rand = new Random();
    }

    private float generateRandomNumber() {
        return (float)rand.nextInt((int)(scrWidth-getWidth()));
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }

    public void move2() {
        float y = gety();
        y += add;
        sety(y);
    }

    public void draw2(Canvas c) {
        c.drawBitmap(rocketB, getx(), gety(), null);
    }

    public int getSpeed() {
        return add;
    }

    public void speedUp() {
        add++;
    }

    public void setSpeed(int num) {
        add += num;
    }

    public float getY() {
        return gety();
    }

    public float getX() {
        return getx();
    }

    public Bitmap getImage() {
        return rocketB;
    }

    public void createRockets() {
        for(int i = 0; i < 70; i++)
        {
            rockets.add(new Rocket(gamePanel, (i*2)*(-Rocket.getHeight())));
        }
    }

    public void move() {
        for(int i = 0; i < rockets.size(); i++)
        {
            rockets.get(i).move2();
        }
    }

    public void draw(Canvas c) {
        for(int i = 0; i < rockets.size(); i++)
        {
            rockets.get(i).draw2(c);
        }
    }

    public int getSize() {
        return rockets.size();
    }

    public int checkRocketAmount() {
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

    public void checkForSpeed(int num) {
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
                    //gamePanel.addNewSpaceship();
                }
                break;
        }
    }

    public List<Rocket> getArray() {
        return rockets;
    }


}
