package Physics.Play.helpers.backups;


public class BACK_UP_CollisionDetector {

    /*private static CollisionDetector d = new CollisionDetector();

    private CollisionDetector() {
    }

    public float[] detectCollision(List<Drawable> drawables1, List<Drawable> drawables2){

        float[] coordinates = new float[4];
        for(int i = 0; i < drawables1.size(); i++)
        {
            Drawable d1 = drawables1.get(i);
            Rect rec1 = new Rect((int)d1.getX(), (int)d1.getY(), (int)(d1.getX() + d1.getWidth()), (int)(d1.getY() + d1.getHeight()));

            for(int j = 0; j < drawables2.size(); j++)
            {
                Drawable d2 = drawables2.get(j);
                Rect rec2 = new Rect( (int)d2.getX(), (int)d2.getY(), (int)(d2.getX() + d2.getWidth()), (int)(d2.getY() + d2.getHeight()));

                if(Rect.intersects(rec1, rec2) )
                {
                    Rect collisionBounds = getCollisionBounds(rec1, rec2);
                    for (int k = collisionBounds.left; k < collisionBounds.right; k++)
                    {
                        for (int L = collisionBounds.top; L < collisionBounds.bottom; L++)
                        {
                            int d1_pixel = getBitmapPixel(d1, k, L);
                            int d2_pixel = getBitmapPixel(d2, k, L);

                            if(isFilled(d1_pixel) && isFilled(d2_pixel))
                            {
                                coordinates[0] = drawables1.get(j).getX();
                                coordinates[1] = drawables1.get(j).getY();
                                coordinates[2] = drawables2.get(j).getX();
                                coordinates[3] = drawables2.get(j).getY();
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

    public float[] fireBallColidedWithRobots(List<Fireball> fireballs, List<Robot> robots, List<Rocket> rockets,  List<Parachute> parachutes){

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
                                parachutes.remove(robots.get(j).getParachute());
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
        Rect cityRec = new Rect(x, y, (int)(x + City.getWidth()), (int)(y + City.getHeight()));
        Bitmap cityImg = citys.get(0).getImage();

        for(int i = 0; i < bullets.size(); i++)
        {
            Bullet bullet = bullets.get(i);
            Rect bulletRec = new Rect( (int)bullet.getX(), (int)bullet.getY(), (int)(bullet.getX()+Bullet.getWidth()), (int)(bullet.getY() + Bullet.getHeight()));
            if(Rect.intersects(cityRec, bulletRec))
            {
                Rect collisionBounds = getCollisionBounds(cityRec, bulletRec);
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

    }  //end fireBallCollided.

    public float[] rocketsCollidedWithShield(List<Shield> shields, List<Rocket> rockets, List<Robot> robots){

        float[] coords = new float[2];
        if(Shield.getHits() < 15) {
            for (int i = 0; i < shields.size(); i++) {
                Shield shield = shields.get(i);
                Rect shieldRec = new Rect((int) shield.getX(), (int) shield.getY(), (int) (shield.getX() + shield.getWidth()), (int) (shield.getY() + shield.getHeight()));

                for (int j = 0; j < rockets.size(); j++) {
                    Rocket rocket = rockets.get(j);
                    Rect rocketRec = new Rect((int) rocket.getX(), (int) rocket.getY(), (int) (rocket.getX() + Rocket.getWidth()), (int) (rocket.getY() + Rocket.getHeight()));

                    if (Rect.intersects(shieldRec, rocketRec)) {
                        Rect collisionBounds = getCollisionBounds(shieldRec, rocketRec);
                        for (int k = collisionBounds.left; k < collisionBounds.right; k++) {
                            for (int L = collisionBounds.top; L < collisionBounds.bottom; L++) {
                                int shieldPixel = getBitmapPixel(shield, k, L);
                                int rocketPixel = getBitmapPixel(rocket, k, L);

                                if (isFilled(shieldPixel) && isFilled(rocketPixel)) {
                                    coords[0] = rockets.get(j).getX();
                                    coords[1] = rockets.get(j).getY();
                                    if (rockets.get(j).hasRobot())
                                        robots.remove(rockets.get(j).getRobot());
                                    rockets.remove(j);
                                    Shield.addHit();
                                    return coords;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;

    }  //end fireBallCollided.

    public float[] bulletsCollidedWithAtomicExplosion(List<AtomicExplosion> atomicExplosions, List<Bullet> bullets){

        float[] coords = new float[2];
            for (int i = 0; i < atomicExplosions.size(); i++) {
                AtomicExplosion atomicExplosion = atomicExplosions.get(i);
                Rect shieldRec = new Rect((int) atomicExplosion.getX(), (int) atomicExplosion.getY(), (int) (atomicExplosion.getX() + atomicExplosion.getWidth()), (int) (atomicExplosion.getY() + atomicExplosion.getHeight()));

                for (int j = 0; j < bullets.size(); j++) {
                    Bullet bullet = bullets.get(j);
                    Rect bulletRec = new Rect((int) bullet.getX(), (int) bullet.getY(), (int) (bullet.getX() + bullet.getWidth()), (int) (bullet.getY() + bullet.getHeight()));

                    if (Rect.intersects(shieldRec, bulletRec)) {
                        Rect collisionBounds = getCollisionBounds(shieldRec, bulletRec);
                        for (int k = collisionBounds.left; k < collisionBounds.right; k++) {
                            for (int L = collisionBounds.top; L < collisionBounds.bottom; L++) {
                                int atomicExplosionPixel = getBitmapPixel(atomicExplosion, k, L);
                                int bulletPixel = getBitmapPixel(bullet, k, L);

                                if (isFilled(atomicExplosionPixel) && isFilled(bulletPixel)) {
                                    coords[0] = bullets.get(j).getX();
                                    coords[1] = bullets.get(j).getY();
                                    bullets.remove(j);
                                    Shield.addHit();
                                    return coords;
                                }
                            }
                        }
                    }
                }
            }
        return null;

    }  //end fireBallCollided.

    public float[] bulletsCollidedWithCityExplosion(List<CityExplosion> cityExplosions, List<Bullet> bullets){

        float[] coords = new float[2];
        for (int i = 0; i < cityExplosions.size(); i++) {
            CityExplosion cityExplosion = cityExplosions.get(i);
            Rect shieldRec = new Rect((int) cityExplosion.getX(), (int) cityExplosion.getY(), (int) (cityExplosion.getX() + cityExplosion.getWidth()), (int) (cityExplosion.getY() + cityExplosion.getHeight()));

            for (int j = 0; j < bullets.size(); j++) {
                Bullet bullet = bullets.get(j);
                Rect bulletRec = new Rect((int) bullet.getX(), (int) bullet.getY(), (int) (bullet.getX() + bullet.getWidth()), (int) (bullet.getY() + bullet.getHeight()));

                if (Rect.intersects(shieldRec, bulletRec)) {
                    Rect collisionBounds = getCollisionBounds(shieldRec, bulletRec);
                    for (int k = collisionBounds.left; k < collisionBounds.right; k++) {
                        for (int L = collisionBounds.top; L < collisionBounds.bottom; L++) {
                            int cityExplosionPixel = getBitmapPixel(cityExplosion, k, L);
                            int bulletPixel = getBitmapPixel(bullet, k, L);

                            if (isFilled(cityExplosionPixel) && isFilled(bulletPixel)) {
                                coords[0] = bullets.get(j).getX();
                                coords[1] = bullets.get(j).getY();
                                bullets.remove(j);
                                Shield.addHit();
                                return coords;
                            }
                        }
                    }
                }
            }
        }
        return null;

    }  //end fireBallCollided.


    public boolean willRobotCollideWithAnotherRobot(List<Robot> robots, MainGamePanel g) {
        for (int A = 0; A < robots.size(); A++) {
            if (robots.get(A).isOnRocket() && robots.get(A).getJumpCoordinates() != null) {
                List<Coordinate> CC = robots.get(A).getJumpCoordinates();
                Coordinate coordinate = CC.get(CC.size() - 1);
                Rect robotRec = new Rect((int) coordinate.getX(), (int) coordinate.getY(), (int) (coordinate.getX() + Robot.getStandingWidth()), (int) (coordinate.getY() + Robot.getStandingHeight()));
                for (int B = 0; B < robots.size(); B++) {
                    Robot robot2 = robots.get(B);
                    if (robot2.getY() > 0 && A != B) {
                        Rect robot2Rec = new Rect((int) robot2.getX(), (int) robot2.getY(), (int) (robot2.getX() + robot2.getCurrentWidth()), (int) (robot2.getY() + robot2.getCurrentHeight()));
                        if (Rect.intersects(robotRec, robot2Rec)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

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
        return bullet.getImage().getPixel(i - (int)bullet.getX(), j - (int)bullet.getY());
    }

    private int getBitmapPixel(Shield shield, int i, int j) {
        return shield.getImage().getPixel(i - (int)shield.getX(), j - (int)shield.getY());
    }

    private int getBitmapPixel(AtomicExplosion atomicExplosion, int i, int j) {
        return atomicExplosion.getImage().getPixel(i - (int)atomicExplosion.getX(), j - (int)atomicExplosion.getY());
    }

    private int getBitmapPixel(CityExplosion cityExplosion, int i, int j) {
        return cityExplosion.getImage().getPixel(i - (int)cityExplosion.getX(), j - (int)cityExplosion.getY());
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

    private void log(String print) {
        Log.i("CollisionDetector - ",print);
    }
*/
}
