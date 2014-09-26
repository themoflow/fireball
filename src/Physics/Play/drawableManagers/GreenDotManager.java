package Physics.Play.drawableManagers;

import Physics.Play.core.MainGamePanel;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import Physics.Play.drawables.GreenDot;
import Physics.Play.drawables.Rocket;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/24/14.
 */
public class GreenDotManager {

    private static GreenDotManager g = new GreenDotManager();

    private GreenDotManager() {

    }

    public static GreenDotManager getInstance() {
        return g;
    }

    public void move(float length, List<GreenDot> greenDots, List<Fireball> fireballs) {
        if(fireballs.size() > 0)
        {
            float [] centerCoordinates = getFireballsCenterCoordinates(fireballs);
            greenDots.get(0).setX(centerCoordinates[0]);
            greenDots.get(0).setY(centerCoordinates[1]);
            double horiDistance = fireballs.get(fireballs.size()-1).getX() - Fireball.getOriginX();
            double vertDistance = fireballs.get(fireballs.size()-1).getY() - Fireball.getOriginY();
            double angle = Math.atan2(vertDistance,horiDistance);
            float xScale = (float)Math.cos(angle);
            float yScale =  (float)Math.sin(angle);
            float dis = (float) Math.sqrt(Math.pow(fireballs.get(fireballs.size()-1).getX() - Fireball.getOriginX(), 2) + Math.pow(fireballs.get(fireballs.size()-1).getY() - Fireball.getOriginY(), 2));
            float speed = (dis / length);
            float angleX = (speed*xScale);
            float angleY = (speed*yScale);
            float aimX = fireballs.get(fireballs.size()-1).getX(), aimY = fireballs.get(fireballs.size()-1).getY();
            while(aimX < MainGamePanel.getScreenWidth() && aimX > 0 && aimY > 0)
            {
                aimX -= angleX;
                aimY -= angleY;
            }
            setAngle(aimX, aimY + (Fireball.getHeight() / 2), greenDots);
        }
    }

    public List<GreenDot> createGreenDots(int amount, MainGamePanel g, List<Fireball> fireballs) {
        List<GreenDot> greenDots = new ArrayList();
        float x = fireballs.get(fireballs.size()-1).getX() + Fireball.getWidth() / 2;
        float y = fireballs.get(fireballs.size()-1).getY() + Fireball.getHeight() / 2;
        for(int i = 0; i < amount; i++)
            greenDots.add(new GreenDot(g, x, y));
        return greenDots;
    }

    public List<Drawable> setAsDrawable(List<GreenDot> greenDots) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < greenDots.size(); i++)
            converted.add(greenDots.get(i));
        return converted;
    }

    public void reset(List<GreenDot> greenDots){
        greenDots.get(0).setX(GreenDot.getOriginX());
        greenDots.get(0).setY(GreenDot.getOriginY());
        greenDots.get(0).setAngleX(GreenDot.getOriginX());
        greenDots.get(0).setAngleY(GreenDot.getOriginY());
    }

    private void setAngle(float angleX, float angleY, List<GreenDot> greenDots) {
        for(int i = 0; i < greenDots.size(); i++) {
            greenDots.get(i).setAngleX(angleX);
            greenDots.get(i).setAngleY(angleY);
        }
    }

    private float[] getFireballsCenterCoordinates(List<Fireball> fireballs) {
        float[] centerCoordinates = new float[2];
        centerCoordinates[0] = fireballs.get(fireballs.size()-1).getX() + (Fireball.getWidth() / 2);
        centerCoordinates[1] = fireballs.get(fireballs.size()-1).getY() + (Fireball.getHeight() / 2);
        return centerCoordinates;
    }

    public float[] getAimerCoordinates(List<GreenDot> greenDots) {
        float[] aimerCoordinates = new float[4];
        aimerCoordinates[0] = greenDots.get(0).getX();
        aimerCoordinates[1] = greenDots.get(0).getY();
        aimerCoordinates[2] = greenDots.get(0).getAngleX();
        aimerCoordinates[3] = greenDots.get(0).getAngleY();
        return aimerCoordinates;
    }
}