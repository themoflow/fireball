package Physics.Play.drawables;

import Physics.Play.main.MainGamePanel;
import Physics.Play.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rocket extends Drawable {

    private static float width, height, scrWidth, scrHeight;
    private int add = 50;
    private Bitmap rocketB;
    private Random rand = new Random();
    private List<Rocket> rockets = new ArrayList<Rocket>();
    private MainGamePanel gamePanel;
    private Rocket rocket;

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

    public float getDistanceIncrement() {
        return add;
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







}
