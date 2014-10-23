package Physics.Play.drawableManagers;

import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import Physics.Play.drawables.GreenDot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/24/14.
 */
public class GreenDotManager {

    private static GreenDotManager g = new GreenDotManager();
    private FireballManager fireballManager = FireballManager.getInstance();

    private GreenDotManager() {

    }

    public static GreenDotManager getInstance() {
        return g;
    }

    public void move(float length, List<GreenDot> greenDots, List<Fireball> fireballs) {
        if(fireballManager.getSize(fireballs) > 0 && greenDots.size() > 0)
        {
            float [] centerCoordinates = getFireballsCenterCoordinates(fireballs);
            greenDots.get(0).setX(centerCoordinates[0]);
            greenDots.get(0).setY(centerCoordinates[1]);
            double horiDistance = fireballManager.getLastFireball(fireballs).getX() - fireballManager.getFirstFireball(fireballs).getOriginX();
            double vertDistance = fireballManager.getLastFireball(fireballs).getY() - fireballManager.getFirstFireball(fireballs).getOriginY();
            double angle = Math.atan2(vertDistance,horiDistance);
            float xScale = (float)Math.cos(angle);
            float yScale =  (float)Math.sin(angle);
            float dis = (float) Math.sqrt(Math.pow(fireballManager.getLastFireball(fireballs).getX() - fireballManager.getFirstFireball(fireballs).getOriginX(), 2) + Math.pow(fireballManager.getLastFireball(fireballs).getY() - fireballManager.getFirstFireball(fireballs).getOriginY(), 2));
            float speed = (dis / length);
            float angleX = (speed*xScale);
            float angleY = (speed*yScale);
            float aimX = fireballManager.getLastFireball(fireballs).getX();
            float aimY = fireballManager.getLastFireball(fireballs).getY();
            while(aimX < MainGameView.getScreenWidth() && aimX > 0 && aimY > 0)
            {
                aimX -= angleX;
                aimY -= angleY;
            }
            setAngle(aimX, aimY + (fireballs.get(0).getHeight() / 2), greenDots);
        }
    }

    public void createGreenDots(int amount, List<GreenDot> greenDots, MainGameView g, List<Fireball> fireballs) {
        float x = fireballManager.getLastFireball(fireballs).getX() + fireballManager.getLastFireball(fireballs).getWidth() / 2;
        float y = fireballManager.getLastFireball(fireballs).getY() + fireballManager.getLastFireball(fireballs).getHeight() / 2;
        for(int i = 0; i < amount; i++)
            greenDots.add(new GreenDot(g, x, y));
    }

    public List<Drawable> setAsDrawable(List<GreenDot> greenDots) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < greenDots.size(); i++)
            converted.add(greenDots.get(i));
        return converted;
    }

    public void reset(List<GreenDot> greenDots){
        greenDots.get(0).setX(greenDots.get(0).getOriginX());
        greenDots.get(0).setY(greenDots.get(0).getOriginY());
        greenDots.get(0).setAngleX(greenDots.get(0).getOriginX());
        greenDots.get(0).setAngleY(greenDots.get(0).getOriginY());
    }

    private void setAngle(float angleX, float angleY, List<GreenDot> greenDots) {
        for(int i = 0; i < greenDots.size(); i++) {
            greenDots.get(i).setAngleX(angleX);
            greenDots.get(i).setAngleY(angleY);
        }
    }

    private float[] getFireballsCenterCoordinates(List<Fireball> fireballs) {
        float[] centerCoordinates = new float[2];
        centerCoordinates[0] = fireballManager.getLastFireball(fireballs).getX() + (fireballManager.getLastFireball(fireballs).getWidth() / 2);
        centerCoordinates[1] = fireballManager.getLastFireball(fireballs).getY() + (fireballManager.getLastFireball(fireballs).getHeight() / 2);
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
