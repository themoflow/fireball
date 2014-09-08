package Physics.Play.Logic;

import java.util.List;

import Physics.Play.drawables.Drawable;
import Physics.Play.drawables.Fireball;
import Physics.Play.drawables.Rocket;
import Physics.Play.main.MainGamePanel;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Color;


public class Collision {

    private MainGamePanel gamePanel;
    private float cityX, cityY;
    private final float cityWidth = 340, cityHeight = 147;
    private List<Drawable> drawables;


    public Collision(List<Drawable> drawables, MainGamePanel g){
        this.drawables = drawables;
        gamePanel = g;
        this.cityX = gamePanel.getCityX();
        this.cityY = gamePanel.getCityY();
    }

    public float[] fireBallColided(){

        float[] coords = new float[2];
        List<Fireball> fireballs = getFireballs();
        List<Rocket> rockets = getRockets();

        for(int i = 0; i < fireballs.size(); i++)
        {
              Fireball f = fireballs.get(i);
              Rect fireball = new Rect( (int)f.getx(), (int)f.gety(), (int)(f.getx()+f.getWidth()), (int)(f.gety() + f.getHeight()));

              for(int j = 0; j < rockets.size(); j++)
              {
                  Rocket s = rockets.get(j);
                  Rect square = new Rect( (int)s.getX(), (int)s.getY(), (int)(s.getX()+s.getWidth()), (int)(s.getY()+s.getHeight()) );

                  if(Rect.intersects(fireball, square) )
                  {
                      Rect collisionBounds = getCollisionBounds(fireball, square);
                      for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                      {
                          for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                          {

                              int fireballPixel = getBitmapPixel(f, k, L);
                              int squarePixel = getBitmapPixel(s, k, L);

                              if( isFilled(fireballPixel) && isFilled(squarePixel))
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

    private List<Fireball> getFireballs(){
        List<Fireball> fireballList = null;
        Fireball fireball = null;
        for(int i = 0; i < drawables.size(); i++)
        {
            if(drawables.get(i).getClass() == Fireball.class)
            {
                fireball = (Fireball) drawables.get(i);
                fireballList = fireball.getArray();
            }
        }
        return fireballList;
    }

    private List<Rocket> getRockets(){
        List<Rocket> rocketList = null;
        Rocket rocket = null;
        for(int i = 0; i < drawables.size(); i++)
        {
            if(drawables.get(i).getClass() == Rocket.class)
            {
                rocket = (Rocket) drawables.get(i);
                rocketList = rocket.getArray();
            }
        }
        return rocketList;
    }


    public float[] rocketCollidedWithCity(){

        float[] coords = new float[2];
        Rect city = new Rect( (int)cityX,(int)cityY, (int)(cityX+cityWidth), (int)(cityY +cityHeight));
        Bitmap cityImg = gamePanel.getCity().getImg();
        List<Rocket> rockets = getRockets();

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

    private int getBitmapPixel(Bitmap b, int i, int j){
        return b.getPixel(i - (int)cityX, j - (int)cityY);
    }

    private static int getBitmapPixel(Fireball f, int i, int j) {
        return f.getImage().getPixel(i - (int)f.getx(), j - (int)f.gety());
    }

    private static int getBitmapPixel(Rocket s, int i, int j) {
        return s.getImage().getPixel(i-(int)s.getX(), j - (int)s.getY());
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
