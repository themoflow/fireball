package Physics.Play.drawables;

import Physics.Play.bitmaps.ShieldBitmaps;
import Physics.Play.views.MainGameView;
import android.graphics.Bitmap;

/**
 * Created by morantornesella-brooks on 10/3/14.
 */
public class Shield extends Drawable {

    private int hits = 0;
    private int imageIndex = 0;

    public Shield(MainGameView g, float x, float y){
        super();
        setWidth(ShieldBitmaps.getImage(0).getWidth());
        setHeight(ShieldBitmaps.getImage(0).getHeight());
        setX(x);
        setY(y);
    }

    @Override
    public Bitmap getImage() {
        return ShieldBitmaps.getImage(imageIndex);
    }

    public void addHit(){
        hits++;
        if(hits > 20)
            switchImage();
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int h) {
         hits = h;
    }

    public void switchImage() {
        if(imageIndex < ShieldBitmaps.getSize()-1)
        {
            imageIndex++;
            hits = 0;
        }
        else
        {
            setIsActive(false);
        }

    }
}
