package Physics.Play.drawableManagers;

import Physics.Play.views.MainGameView;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.ScreenBackground;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 9/23/14.
 */
public class ScreenBackgroundManager {

    private static ScreenBackgroundManager s = new ScreenBackgroundManager();

    private ScreenBackgroundManager(){}

    public static ScreenBackgroundManager getInstance( ) {
        return s;
    }

    public void createScreenBackground(int amount, List<ScreenBackground> screenBackgrounds, MainGameView g, float x, float y) {
        for(int i = 0; i < amount; i++) {
            screenBackgrounds.add(new ScreenBackground(g, x, y));
        }
    }

    public List<Drawable> setAsDrawable(List<ScreenBackground> screenBackground) {
        List<Drawable> converted = new ArrayList();
        for(int i = 0; i < screenBackground.size(); i++)
            converted.add(screenBackground.get(i));
        return converted;
    }
}
