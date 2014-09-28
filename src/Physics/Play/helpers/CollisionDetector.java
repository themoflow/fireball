package Physics.Play.helpers;

import java.util.List;

import Physics.Play.drawables.*;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Color;
import android.util.Log;


public class CollisionDetector {

    private static CollisionDetector d = new CollisionDetector();

    private CollisionDetector() {
    }

    public static CollisionDetector getInstance() {
        return d;
    }

    public float[] fireBallColidedWithRockets(List<Fireball> fireballs, List<Rocket> rockets, List<Robot> robots){

        float[] coords = new float[2];
        for(int i = 0; i < fireballs.size(); i++)
        {
              Fireball f = fireballs.get(i);
              Rect fireballArea = new Rect( (int)f.getX(), (int)f.getY(), (int)(f.getX()+f.getWidth()), (int)(f.getY() + f.getHeight()));

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
                                  if(rockets.get(j).hasRobot())
                                      robots.remove(rockets.get(j).getRobot());
                                  fireballs.remove(i);
                                  rockets.remove(j);
                                  return coords ;
                              }
                          }
                      }
                  }
              }

        }
        return null;

    }  //end fireBallColidedWithRockets.

    public float[] fireBallColidedWithRobots(List<Fireball> fireballs, List<Robot> robots, List<Rocket> rockets){

        float[] coords = new float[2];
        for(int i = 0; i < fireballs.size(); i++)
        {
            Fireball fireball = fireballs.get(i);
            Rect fireballRec = new Rect( (int)fireball.getX(), (int)fireball.getY(), (int)(fireball.getX()+fireball.getWidth()), (int)(fireball.getY() + fireball.getHeight()));

            for(int j = 0; j < robots.size(); j++)
            {
                Robot robot = robots.get(j);
                Rect robotRec = new Rect((int)robot.getX(), (int)robot.getY(), (int)(robot.getX() + robot.getCurrentWidth()), (int)(robot.getY() + robot.getCurrentHeight()) );

                if(Rect.intersects(fireballRec, robotRec) )
                {
                    Rect collisionBounds = getCollisionBounds(fireballRec, robotRec);
                    for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                    {
                        for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                        {
                            int fireballPixel = getBitmapPixel(fireball, k, L);
                            int robotPixel = getBitmapPixel(robot, k, L);

                            if(isFilled(fireballPixel) && isFilled(robotPixel))
                            {
                                coords[0] = robots.get(j).getX();
                                coords[1] = robots.get(j).getY();
                                fireballs.remove(i);
                                if(robots.get(j).isOnRocket())
                                    rockets.remove(robots.get(j).getRocket());
                                robots.remove(j);
                                return coords ;
                            }
                        }
                    }
                }
            }

        }
        return null;

    }  //end fireBallColidedWithRobots

    public float[] rocketCollidedWithCity(List<Rocket> rockets, List<City> citys, List<Robot> robots){

        float[] coords = new float[2];
        int y = (int)citys.get(0).getY();
        int x = (int)citys.get(0).getX();
        Rect city = new Rect(x, y, (int)(x + City.getWidth()), (int)(y + City.getHeight()));
        Bitmap cityImg = citys.get(0).getImage();

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

                        int cityPixel = getBitmapPixel(cityImg, k, L, x, y);
                        int squarePixel = getBitmapPixel(s, k, L);

                        if(isFilled(cityPixel) && isFilled(squarePixel))
                        {
                            coords[0] = rockets.get(i).getX() + (s.getWidth() / 2);
                            coords[1] = rockets.get(i).getY() + s.getHeight();
                            if(rockets.get(i).hasRobot())
                                robots.remove(rockets.get(i).getRobot());
                            rockets.remove(i);
                            return coords ;
                        }
                    }
                }
           }
        }
        return null;
    }

    public float[] bulletsCollidedWithCity(List<Bullet> bullets, List<City> citys){

        float[] coords = new float[2];
        int y = (int)citys.get(0).getY();
        int x = (int)citys.get(0).getX();
        Rect city = new Rect(x, y, (int)(x + City.getWidth()), (int)(y + City.getHeight()));
        Bitmap cityImg = citys.get(0).getImage();

        for(int i = 0; i < bullets.size(); i++)
        {
            Bullet bullet = bullets.get(i);
            Rect square = new Rect( (int)bullet.getX(), (int)bullet.getY(), (int)(bullet.getX()+Bullet.getWidth()), (int)(bullet.getY() + Bullet.getHeight()));

            if( Rect.intersects(city, square) )
            {
                Rect collisionBounds = getCollisionBounds(city, square);
                for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                {
                    for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                    {

                        int cityPixel = getBitmapPixel(cityImg, k, L, x, y);
                        int squarePixel = getBitmapPixel(bullet, k, L);

                        if(isFilled(cityPixel) && isFilled(squarePixel))
                        {
                            coords[0] = bullets.get(i).getX();
                            coords[1] = bullets.get(i).getY();
                            bullets.remove(i);
                            return coords ;
                        }
                    }
                }
            }
        }
        return null;
    }

    public float[] fireBallColidedWithBullets(List<Fireball> fireballs, List<Bullet> bullets){

        float[] coords = new float[2];
        for(int i = 0; i < fireballs.size(); i++)
        {
            Fireball fireball = fireballs.get(i);
            Rect fireballRec = new Rect( (int)fireball.getX(), (int)fireball.getY(), (int)(fireball.getX()+fireball.getWidth()), (int)(fireball.getY() + fireball.getHeight()));

            for(int j = 0; j < bullets.size(); j++)
            {
                Bullet bullet = bullets.get(j);
                Rect bulletRec = new Rect((int)bullet.getX(), (int)bullet.getY(), (int)(bullet.getX() + Bullet.getWidth()), (int)(bullet.getY() + Bullet.getHeight()));

                if(Rect.intersects(fireballRec, bulletRec) )
                {
                    Rect collisionBounds = getCollisionBounds(fireballRec, bulletRec);
                    for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                    {
                        for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                        {
                            int fireballPixel = getBitmapPixel(fireball, k, L);
                            int robotPixel = getBitmapPixel(bullet, k, L);

                            if(isFilled(fireballPixel) && isFilled(robotPixel))
                            {
                                coords[0] = bullets.get(j).getX();
                                coords[1] = bullets.get(j).getY();
                                bullets.remove(j);
                                return coords ;
                            }
                        }
                    }
                }
            }

        }
        return null;

    }  //end fireBallColided

    private int getBitmapPixel(Bitmap b, int i, int j, int x, int y) {
        return b.getPixel((i - x), (j - y));
    }

    private int getBitmapPixel(Fireball f, int i, int j) {
        return f.getImage().getPixel(i - (int)f.getX(), j - (int)f.getY());
    }

    private int getBitmapPixel(Rocket r, int i, int j) {
        return r.getImage().getPixel(i-(int)r.getX(), j - (int)r.getY());
    }

    private int getBitmapPixel(Robot robot, int i, int j) {
        return robot.getImage().getPixel(i-(int)robot.getX(), j - (int)robot.getY());
    }

    private int getBitmapPixel(Bullet bullet, int i, int j) {

        return bullet.getImage().getPixel(i-(int)bullet.getX(), j - (int)bullet.getY());
    }

     private static Rect getCollisionBounds(Rect rect1, Rect rect2) {
        int left = Math.max(rect1.left, rect2.left);
        int top = Math.max(rect1.top, rect2.top);
        int right = Math.min(rect1.right, rect2.right);
        int bottom = Math.min(rect1.bottom, rect2.bottom);
        return new Rect(left, top, right, bottom);
    }

    private static boolean isFilled(int pixel) {
         return pixel != Color.TRANSPARENT;
    }



}
