package Physics.Play.Managers;

import Physics.Play.drawables.City;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import Physics.Play.main.MainGamePanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/22/14.
 */
public class FireballManager {

    private static FireballManager f = new FireballManager();

    private FireballManager(){}

    public static FireballManager getInstance( ) {
        return f;
    }

    public List<Fireball> createFireballs(int amount, MainGamePanel g, float origX, float origY) {
        List<Fireball> fireballs = new ArrayList();
        for(int i = 0; i < amount; i++) {
            fireballs.add(new Fireball(g, origX, origY));
        }
        return fireballs;
    }

    public List<Drawable> setAsDrawable(List<City> citys) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < citys.size(); i++)
            converted.add(citys.get(i));
        return converted;
    }
}
