package Physics.Play.Logic;

import java.util.List;

import Physics.Play.drawables.City;
import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import Physics.Play.drawables.Rocket;
import Physics.Play.main.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Color;
import android.util.Log;


public class Collision {

    private MainGamePanel gamePanel;
    private float cityX, cityY;
    private final float cityWidth = 340, cityHeight = 147;
    private boolean logEnabled = false;

    public Collision(MainGamePanel g){
        gamePanel = g;
        this.cityX = gamePanel.getCityX();
        this.cityY = gamePanel.getCityY();
    }

    public float[] fireBallColided(List<Fireball> FBL, List<Rocket> rockets){

        float[] coords = new float[2];
        List<Fireball> fireballs = FBL.get(0).getArray();

        for(int i = 0; i < fireballs.size(); i++)
        {
              Fireball f = fireballs.get(i);
              Rect fireballArea = new Rect( (int)f.getx(), (int)f.gety(), (int)(f.getx()+f.getWidth()), (int)(f.gety() + f.getHeight()));

              for(int j = 0; j < rockets.size(); j++)
              {
                  Rocket s = rockets.get(j);
                  Rect rocketArea = new Rect( (int)s.getX(), (int)s.getY(), (int)(s.getX()+s.getWidth()), (int)(s.getY()+s.getHeight()) );

                  if(Rect.intersects(fireballArea, rocketArea) )
                  {
                      Rect collisionBounds = getCollisionBounds(fireballArea, rocketArea);
                      for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                      {
                          for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                          {
                              int fireballPixel = getBitmapPixel(f, k, L);
                              int rocketPixel = getBitmapPixel(s, k, L);

                              if(isFilled(fireballPixel) && isFilled(rocketPixel))
                              {
                                  coords[0] = rockets.get(j).getX();
                                  coords[1] = rockets.get(j).getY();
                                  fireballs.remove(i);
                                  Fireball.updateFireballAmount();
                                  rockets.remove(j);
                                  return coords ;
                              }
                          }
                      }
                  }
              }

        }
        return null;

    }  //end fireBallColided

    public float[] rocketCollidedWithCity(List<Rocket> rockets, List<City> citys){

        float[] coords = new float[2];
        Rect city = new Rect( (int)cityX,(int)cityY, (int)(cityX+cityWidth), (int)(cityY +cityHeight));
        Bitmap cityImg = citys.get(0).getImg();

        for(int i = 0; i < rockets.size(); i++)
        {
            Rocket s = rockets.get(i);
            Rect square = new Rect( (int)s.getX(), (int)s.getY(), (int)(s.getX()+s.getWidth()), (int)(s.getY() + s.getHeight()) );

            if( Rect.intersects(city, square) )
            {
                Rect collisionBounds = getCollisionBounds(city, square);
                for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                {
                    for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                    {

                        int cityPixel = getBitmapPixel(cityImg, k, L);
                        int squarePixel = getBitmapPixel(s, k, L);

                        if( isFilled(cityPixel) && isFilled(squarePixel))
                        {
                            coords[0] = rockets.get(i).getX()+(s.getWidth()/2);
                            coords[1] = rockets.get(i).getY()+s.getHeight();
                            rockets.remove(i);

                            return coords ;
                        }
                    }
                }
           }
        }
        return null;
    }

    private int getBitmapPixel(Bitmap b, int i, int j) {
        return b.getPixel(i - (int)cityX, j - (int)cityY);
    }

    private int getBitmapPixel(Fireball f, int i, int j) {
        if(logEnabled)
            Log.i("Collision - getBitmapPixel","x = " + (i - (int)f.getx()) + ", width = " + f.getImage().getWidth());
        return f.getImage().getPixel(i - (int)f.getx(), j - (int)f.gety());
    }

    private static int getBitmapPixel(Rocket r, int i, int j) {
        return r.getImage().getPixel(i-(int)r.getX(), j - (int)r.getY());
    }

     private static Rect getCollisionBounds(Rect rect1, Rect rect2) {

          int left = (int) Math.max(rect1.left, rect2.left);
          int top = (int) Math.max(rect1.top, rect2.top);
          int right = (int) Math.min(rect1.right, rect2.right);
          int bottom = (int) Math.min(rect1.bottom, rect2.bottom);
          return new Rect(left, top, right, bottom);
    }

    private static boolean isFilled(int pixel) {
         return pixel != Color.TRANSPARENT;
    }



}
