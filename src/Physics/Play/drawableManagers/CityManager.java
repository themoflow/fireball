package Physics.Play.drawableManagers;

import Physics.Play.drawables.City;
import Physics.Play.drawables.Drawable;
import Physics.Play.core.MainGamePanel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/22/14.
 */
public class CityManager {

    private static CityManager c = new CityManager();

    private CityManager(){}

    public static CityManager getInstance( ) {
        return c;
    }

    public List<City> createCitys(int amount, MainGamePanel g, float cityX, float cityY) {
        List<City> citys = new ArrayList();
        for(int i = 0; i < amount; i++) {
            citys.add(new City(g, cityX, cityY));
        }
        return citys;
    }

    public List<Drawable> setAsDrawable(List<City> citys) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < citys.size(); i++)
            converted.add(citys.get(i));
        return converted;
    }

    public void setDestroyedCityImage(List<City> citys) {
        for(int i = 0; i < citys.size(); i++)
            citys.get(0).switchImage();
    }
}
