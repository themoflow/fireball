package Physics.Play.views;

import Physics.Play.R;
import Physics.Play.activities.MainGameActivity;
import Physics.Play.bitmaps.*;
import Physics.Play.logic.CollisionDetector;
import Physics.Play.drawableManagers.*;
import Physics.Play.logic.MainThread;
import Physics.Play.model.CollisionDetails;
import Physics.Play.model.GameState;
import Physics.Play.drawables.*;
import Physics.Play.logic.Drawer;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

public class MainGameView extends SurfaceView implements SurfaceHolder.Callback {

    private boolean logEnabled = false;
    private MainThread thread;
    private MainGameActivity activity;
    private GameState gameState = null;
    private static float screenWidth, screenHeight;

     //Drawable managers.
    private RobotManager robotManager = RobotManager.getInstance();
    private RocketManager rocketManager = RocketManager.getInstance();
    private CityManager cityManager = CityManager.getInstance();
    private RocketExplosionManager rocketExplosionManager = RocketExplosionManager.getInstance();
    private FireballManager fireballManager = FireballManager.getInstance();
    private ScreenBackgroundManager screenBackgroundManager = ScreenBackgroundManager.getInstance();
    private GreenDotManager greenDotManager = GreenDotManager.getInstance();
    private BulletManager bulletManager = BulletManager.getInstance();
    private BulletExplosionManager bulletExplosionManager = BulletExplosionManager.getInstance();
    private SlingShotManager slingShotManager = SlingShotManager.getInstance();
    private ParachuteManager parachuteManager = ParachuteManager.getInstance();
    private RobotExplosionManager robotExplosionManager = RobotExplosionManager.getInstance();
    private AtomicExplosionManager atomicExplosionManager = AtomicExplosionManager.getInstance();
    private CityExplosionManager cityExplosionManager = CityExplosionManager.getInstance();
    private ShieldManager shieldManager = ShieldManager.getInstance();
    private RingExplosionManager ringExplosionManager = RingExplosionManager.getInstance();
    private Drawer draw = Drawer.getInstance();
    private CollisionDetector collisionDetector = CollisionDetector.getInstance();


    public MainGameView(Context context) {
        super(context);
        activity = (MainGameActivity)context;
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // make the GamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    public static float getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(float screenWidth) {
        MainGameView.screenWidth = screenWidth;
    }

    public static float getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(float screenHeight) {
        MainGameView.screenHeight = screenHeight;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenWidth = getWidth();
        screenHeight = getHeight();
        //Keeping all images in separate classes so that i do not have to deal with serializing bitmaps.
        initializeAllImagesOfTheGame();
        BulletExplosionBitmaps.initialize(this);
        if(gameState == null)
        {
            gameState = new GameState();
            float slingDistance = (screenHeight - BitmapFactory.decodeResource(getResources(), R.drawable.fireball1).getWidth() * 2) - (screenHeight - (screenHeight / 4));
            gameState.setLength(slingDistance / 10);

            //Create drawable lists.
            fireballManager.createFireballs(1, gameState.getFireballs(), this, screenWidth, screenHeight);
            Robot robot = robotManager.createRobot(gameState.getRobots(), this);
            Rocket rocket = rocketManager.createRocket(gameState.getRockets(), this, screenWidth);
            //Add the robots and the rockets to each other and get their movements coordinated..
            robotManager.addRobotToRocket(robot, rocket);
            cityManager.createCitys(1, gameState.getCitys(), this, (screenWidth / 2) - 170, screenHeight - 120);
            screenBackgroundManager.createScreenBackground(1, gameState.getScreenBackgrounds(), this, 0f, 0f);
            greenDotManager.createGreenDots(1, gameState.getGreenDots(), this, gameState.getFireballs());
            bulletManager.createBullets(0, gameState.getBullets(), this);
            shieldManager.createShields(this, gameState.getShields(), screenWidth, screenHeight);
            gameState.setSlingShot(new SlingShot(screenWidth / 2, screenWidth - 10, (screenHeight - (screenHeight / 4)) + (BitmapFactory.decodeResource(getResources(), R.drawable.fireball1).getHeight() / 2)));
            shieldManager.createShield(gameState.getShields(), this, screenWidth, screenHeight);
        }
        //Start main thread.
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        log("surfaceDestroyed() called");
        killThread();
    } // end surfaceDestroyed()

    private void killThread() {
        thread.setRunning(false);
        /*boolean retry = true;
        while (retry)
        {
            try
            {
                thread.setRunning(false);
                thread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {
                System.out.println("InteruptedException error was caught in surfaceDestroyed method");
            }
        }*/
    }

    @Override
    synchronized public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            gameState.setDown(fireballManager.checkForFireballTouch(event.getX(), event.getY(), gameState.getFireballs()));
        }

        if(event.getAction() == MotionEvent.ACTION_UP)
        {
            if(gameState.isDragged())
            {
                gameState.setDragged(false);
                gameState.setDown(false);
                fireballManager.getAndSetVelocity(event.getX(), event.getY(), gameState.getLength(), gameState.getFireballs());
                greenDotManager.reset(gameState.getGreenDots());
            }

        }

        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            if(gameState.isDown())
            {
                gameState.setDragged(true);
                fireballManager.onDrag(event.getX(), event.getY(), gameState.getFireballs(), this);
                greenDotManager.move(gameState.getLength(), gameState.getGreenDots(), gameState.getFireballs());
                slingShotManager.move(gameState.getFireballs(), gameState.getSlingShot());
            }
        }
        return true;

    } //end onTouchEvent()

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(canvas != null)
        {
            if(gameState.isGameOver())
            {
                if(!gameState.isGameOverSequenceStarted())
                {
                    gameState.setGameOverSequenceStarted(true);
                    rocketManager.removeRockets(gameState.getRockets());
                    robotManager.removeRobots(gameState.getRobots());
                    fireballManager.removeFireballs(gameState.getFireballs());
                    cityExplosionManager.createExplosion(this, gameState.getCityExplosions(), screenWidth, screenHeight, "left");
                    cityExplosionManager.createExplosion(this, gameState.getCityExplosions(), screenWidth, screenHeight, "right");
                    atomicExplosionManager.createExplosion(this, gameState.getAtomicExplosions(), screenWidth, screenHeight);
                    ringExplosionManager.createExplosion(this, gameState.getRingExplosions(), screenWidth, screenHeight);
                    cityManager.setDestroyedCityImage(gameState.getCitys());
                }
                else
                {
                    atomicExplosionManager.checkForRemoval(gameState.getAtomicExplosions());
                    cityExplosionManager.checkForRemoval(gameState.getCityExplosions());
                    ringExplosionManager.checkForRemoval(gameState.getRingExplosions());
                    if(everythingHasExploded())
                    {
                        City.HITS = 0;
                        shieldManager.setHits(gameState.getShields(), 0);
                        activity.displayGameOverMessage("GAME OVER\nYou let the city get destroyed\nTry Again!");
                        killThread();
                        return;
                    }
                }
            }
            if(!gameState.isGameOver()) {
                fireballManager.isAtleastOneFireball(gameState.getFireballs(), this, screenWidth, screenHeight);
                fireballManager.checkForFireBallRemoval(gameState.getFireballs(), this);
                fireballManager.checkForFireballCreation(gameState.isDown(), gameState.getFireballs(), this, screenWidth, screenHeight);
                fireballManager.addVelocity(gameState.getFireballs());

                if (rocketManager.timeToCreateRocket(gameState.getRockets())) {
                    Rocket rocket = rocketManager.createRocket(gameState.getRockets(), this, screenWidth);
                    Robot robot = robotManager.createRobot(gameState.getRobots(), this);
                    robotManager.addRobotToRocket(robot, rocket);
                }
            }
            rocketManager.move(gameState.getRockets());
            robotManager.move(gameState.getRobots(), gameState.getParachutes(), collisionDetector, this);
            parachuteManager.move(gameState.getParachutes());
            bulletManager.createBullets(gameState.getRobots(), gameState.getBullets(), this);
            bulletManager.move(gameState.getBullets());
            slingShotManager.move(gameState.getFireballs(), gameState.getSlingShot());

            rocketExplosionManager.checkForRemoval(gameState.getRocketExplosions());
            bulletExplosionManager.checkForRemoval(gameState.getBulletExplosions());
            robotExplosionManager.checkForRemoval(gameState.getRobotExplosions());
            fireballManager.checkForRemoval(gameState.getFireballs());
            bulletManager.checkForRemoval(gameState.getBullets());
            rocketManager.checkForRemoval(gameState.getRockets());
            robotManager.checkForRemoval(gameState.getRobots());
            parachuteManager.checkForRemoval(gameState.getParachutes());
            shieldManager.checkForRemoval(gameState.getShields());

            //Fireball collision details.
            CollisionDetails fireballRocketCollisionDetails = collisionDetector.detectCollision(fireballManager.setAsDrawable(gameState.getFireballs()), rocketManager.setAsDrawable(gameState.getRockets()));
            CollisionDetails fireballRobotCollisionDetails = collisionDetector.detectCollision(fireballManager.setAsDrawable(gameState.getFireballs()), robotManager.setAsDrawable(gameState.getRobots()));
            CollisionDetails fireballBulletCollisionDetails = collisionDetector.detectCollision(fireballManager.setAsDrawable(gameState.getFireballs()), bulletManager.setAsDrawable(gameState.getBullets()));
            //Rocket collision details.
            CollisionDetails rocketCityCollisionDetails = collisionDetector.detectCollision(cityManager.setAsDrawable(gameState.getCitys()), rocketManager.setAsDrawable(gameState.getRockets()));
            CollisionDetails rocketShieldCollisionDetails = collisionDetector.detectCollision(shieldManager.setAsDrawable(gameState.getShields()), rocketManager.setAsDrawable(gameState.getRockets()));
            CollisionDetails rocketCityExplosionCollisionDetails = collisionDetector.detectCollision(cityExplosionManager.setAsDrawable(gameState.getCityExplosions()), rocketManager.setAsDrawable(gameState.getRockets()));
            CollisionDetails rocketAtomicExplosionCollisionDetails = collisionDetector.detectCollision(atomicExplosionManager.setAsDrawable(gameState.getAtomicExplosions()), rocketManager.setAsDrawable(gameState.getRockets()));
            CollisionDetails rocketRingExplosionCollisionDetails = collisionDetector.detectCollision(ringExplosionManager.setAsDrawable(gameState.getRingExplosions()), rocketManager.setAsDrawable(gameState.getRockets()));
            //Robot collision details.
            CollisionDetails robotCityCollisionDetails = collisionDetector.detectCollision(cityManager.setAsDrawable(gameState.getCitys()), robotManager.setAsDrawable(gameState.getRobots()));
            CollisionDetails robotShieldCollisionDetails = collisionDetector.detectCollision(shieldManager.setAsDrawable(gameState.getShields()), robotManager.setAsDrawable(gameState.getRobots()));
            CollisionDetails robotCityExplosionCollisionDetails = collisionDetector.detectCollision(cityExplosionManager.setAsDrawable(gameState.getCityExplosions()), robotManager.setAsDrawable(gameState.getRobots()));
            CollisionDetails robotAtomicExplosionCollisionDetails = collisionDetector.detectCollision(atomicExplosionManager.setAsDrawable(gameState.getAtomicExplosions()), robotManager.setAsDrawable(gameState.getRobots()));
            CollisionDetails robotRingExplosionCollisionDetails = collisionDetector.detectCollision(ringExplosionManager.setAsDrawable(gameState.getRingExplosions()), robotManager.setAsDrawable(gameState.getRobots()));
            //Bullet collision details.
            CollisionDetails bulletCityCollisionDetails = collisionDetector.detectCollision(cityManager.setAsDrawable(gameState.getCitys()), bulletManager.setAsDrawable(gameState.getBullets()));
            CollisionDetails bulletShieldCollisionDetails = collisionDetector.detectCollision(shieldManager.setAsDrawable(gameState.getShields()), bulletManager.setAsDrawable(gameState.getBullets()));
            CollisionDetails bulletAtomicExplosionDetails = collisionDetector.detectCollision(atomicExplosionManager.setAsDrawable(gameState.getAtomicExplosions()), bulletManager.setAsDrawable(gameState.getBullets()));
            CollisionDetails bulletCityExplosionDetails = collisionDetector.detectCollision(cityExplosionManager.setAsDrawable(gameState.getCityExplosions()), bulletManager.setAsDrawable(gameState.getBullets()));
            CollisionDetails bulletRingExplosionDetails = collisionDetector.detectCollision(ringExplosionManager.setAsDrawable(gameState.getRingExplosions()), bulletManager.setAsDrawable(gameState.getBullets()));

            if(fireballRocketCollisionDetails != null) {
                rocketExplosionManager.createExplosion(this, gameState.getRocketExplosions(), fireballRocketCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.rocket_explosion_1).getWidth() / 2), fireballRocketCollisionDetails.getY());
                //Remove the Robot that is on the Rocket.
                if(fireballRocketCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRobots().remove(fireballRocketCollisionDetails.getDrawable2().getDrawables().get(0));
                }
                gameState.getFireballs().remove(fireballRocketCollisionDetails.getDrawable1());
                gameState.getRockets().remove(fireballRocketCollisionDetails.getDrawable2());
            }

            if(rocketCityCollisionDetails != null)
            {
                if (City.HITS++ >= 5 && gameState.isGameOver() == false)
                    gameState.setGameOver(true);
                rocketExplosionManager.createExplosion(this, gameState.getRocketExplosions(), rocketCityCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.rocket_explosion_1).getWidth() / 2), rocketCityCollisionDetails.getY());
                if(rocketCityCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRobots().remove(rocketCityCollisionDetails.getDrawable2().getDrawables().get(0));
                }
                gameState.getRockets().remove(rocketCityCollisionDetails.getDrawable2());
            }

            if(fireballRobotCollisionDetails != null) {
                robotExplosionManager.createRobotExplosion(this, gameState.getRobotExplosions(), fireballRobotCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.rocket_explosion_1).getWidth() / 2), fireballRobotCollisionDetails.getY());
                if(fireballRobotCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getParachutes().remove(fireballRobotCollisionDetails.getDrawable2().getDrawable(Parachute.class));
                    gameState.getRockets().remove(fireballRobotCollisionDetails.getDrawable2().getDrawable(Rocket.class));
                }
                gameState.getFireballs().remove(fireballRobotCollisionDetails.getDrawable1());
                gameState.getRobots().remove(fireballRobotCollisionDetails.getDrawable2());
            }

            if(bulletCityCollisionDetails != null) {
                bulletExplosionManager.createBulletExplosion(this, gameState.getBulletExplosions(), bulletCityCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), bulletCityCollisionDetails.getY());
                gameState.getBullets().remove(bulletCityCollisionDetails.getDrawable2());
            }
            if(fireballBulletCollisionDetails != null) {
                bulletExplosionManager.createBulletExplosion(this, gameState.getBulletExplosions(), fireballBulletCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), fireballBulletCollisionDetails.getY());
                gameState.getBullets().remove(fireballBulletCollisionDetails.getDrawable2());
            }
            if(rocketShieldCollisionDetails != null) {
                shieldManager.addHit(gameState.getShields());
                rocketExplosionManager.createExplosion(this, gameState.getRocketExplosions(), rocketShieldCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.rocket).getWidth() / 2), rocketShieldCollisionDetails.getY());
                if(rocketShieldCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRobots().remove(rocketShieldCollisionDetails.getDrawable2().getDrawable(Robot.class));
                }
                gameState.getRockets().remove(rocketShieldCollisionDetails.getDrawable2());
            }
            if(bulletAtomicExplosionDetails != null) {
                bulletExplosionManager.createBulletExplosion(this, gameState.getBulletExplosions(), bulletAtomicExplosionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), bulletAtomicExplosionDetails.getY());
                gameState.getBullets().remove(bulletAtomicExplosionDetails.getDrawable2());
            }
            if(bulletCityExplosionDetails != null) {
                bulletExplosionManager.createBulletExplosion(this, gameState.getBulletExplosions(), bulletCityExplosionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), bulletCityExplosionDetails.getY());
                gameState.getBullets().remove(bulletCityExplosionDetails.getDrawable2());
            }
            if(robotCityCollisionDetails != null) {
                if (City.HITS++ >= 5 && gameState.isGameOver() == false)
                    gameState.setGameOver(true);
                robotExplosionManager.createRobotExplosion(this, gameState.getRobotExplosions(), robotCityCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.robot_explosion_1).getWidth() / 2), robotCityCollisionDetails.getY());
                if(robotCityCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getParachutes().remove(robotCityCollisionDetails.getDrawable2().getDrawable(Parachute.class));
                }
                gameState.getRobots().remove(robotCityCollisionDetails.getDrawable2());
            }
            if(robotShieldCollisionDetails != null) {
                shieldManager.addHit(gameState.getShields());
                if(robotShieldCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getParachutes().remove(robotShieldCollisionDetails.getDrawable2().getDrawable(Parachute.class));
                }

                robotExplosionManager.createRobotExplosion(this, gameState.getRobotExplosions(), robotShieldCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.robot_explosion_1).getWidth() / 2), robotShieldCollisionDetails.getY());
                gameState.getRobots().remove(robotShieldCollisionDetails.getDrawable2());
            }

            if(bulletShieldCollisionDetails != null) {
                shieldManager.addHit(gameState.getShields());
                bulletExplosionManager.createBulletExplosion(this, gameState.getBulletExplosions(), bulletShieldCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), bulletShieldCollisionDetails.getY());
                gameState.getBullets().remove(bulletShieldCollisionDetails.getDrawable2());
            }

            if(rocketCityExplosionCollisionDetails != null) {
                rocketExplosionManager.createExplosion(this, gameState.getRocketExplosions(), rocketCityExplosionCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), rocketCityExplosionCollisionDetails.getY());
                if(rocketCityExplosionCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRobots().remove(rocketCityExplosionCollisionDetails.getDrawable2().getDrawable(Robot.class));
                }
                gameState.getRockets().remove(rocketCityExplosionCollisionDetails.getDrawable2());
            }
            if(rocketAtomicExplosionCollisionDetails != null) {
                rocketExplosionManager.createExplosion(this, gameState.getRocketExplosions(), rocketAtomicExplosionCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), rocketAtomicExplosionCollisionDetails.getY());
                if(rocketAtomicExplosionCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRobots().remove(rocketAtomicExplosionCollisionDetails.getDrawable2().getDrawable(Robot.class));
                }
                gameState.getRockets().remove(rocketAtomicExplosionCollisionDetails.getDrawable2());
            }

            if(robotCityExplosionCollisionDetails != null) {
                robotExplosionManager.createRobotExplosion(this, gameState.getRobotExplosions(), robotCityExplosionCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), robotCityExplosionCollisionDetails.getY());
                if(robotCityExplosionCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRockets().remove(robotCityExplosionCollisionDetails.getDrawable2().getDrawable(Rocket.class));
                    gameState.getParachutes().remove(robotCityExplosionCollisionDetails.getDrawable2().getDrawable(Parachute.class));
                }
                gameState.getRobots().remove(robotCityExplosionCollisionDetails.getDrawable2());
            }
            if(robotAtomicExplosionCollisionDetails != null) {
                robotExplosionManager.createRobotExplosion(this, gameState.getRobotExplosions(), robotAtomicExplosionCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), robotAtomicExplosionCollisionDetails.getY());
                if(robotAtomicExplosionCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRockets().remove(robotAtomicExplosionCollisionDetails.getDrawable2().getDrawable(Rocket.class));
                    gameState.getParachutes().remove(robotAtomicExplosionCollisionDetails.getDrawable2().getDrawable(Parachute.class));
                }
                gameState.getRobots().remove(robotAtomicExplosionCollisionDetails.getDrawable2());
            }
            if(bulletRingExplosionDetails != null) {
                bulletExplosionManager.createBulletExplosion(this, gameState.getBulletExplosions(), bulletRingExplosionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.bullet_explosion_1).getWidth() / 2), bulletRingExplosionDetails.getY());
                gameState.getBullets().remove(bulletRingExplosionDetails.getDrawable2());
            }
            if(robotRingExplosionCollisionDetails != null) {
                robotExplosionManager.createRobotExplosion(this, gameState.getRobotExplosions(), robotRingExplosionCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.robot_explosion_1).getWidth() / 2), robotRingExplosionCollisionDetails.getY());
                if(robotRingExplosionCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRockets().remove(robotRingExplosionCollisionDetails.getDrawable2().getDrawable(Rocket.class));
                    gameState.getParachutes().remove(robotRingExplosionCollisionDetails.getDrawable2().getDrawable(Parachute.class));
                }
                gameState.getRobots().remove(robotRingExplosionCollisionDetails.getDrawable2());
            }
            if(rocketRingExplosionCollisionDetails != null) {
                rocketExplosionManager.createExplosion(this, gameState.getRocketExplosions(), rocketRingExplosionCollisionDetails.getX() - (BitmapFactory.decodeResource(getResources(), R.drawable.rocket).getWidth() / 2), rocketRingExplosionCollisionDetails.getY());
                if(rocketRingExplosionCollisionDetails.getDrawable2().getDrawables().size() > 0)
                {
                    gameState.getRobots().remove(rocketRingExplosionCollisionDetails.getDrawable2().getDrawable(Robot.class));
                }
                gameState.getRockets().remove(rocketRingExplosionCollisionDetails.getDrawable2());
            }

            float[] aimerCoordinates = greenDotManager.getAimerCoordinates(gameState.getGreenDots());

            //Redraw the whole canvas.
            draw.backgroundColor(Color.BLACK, canvas);
            draw.drawToCanvas(screenBackgroundManager.setAsDrawable(gameState.getScreenBackgrounds()), canvas);
            draw.drawToCanvas(cityManager.setAsDrawable(gameState.getCitys()), canvas);
            draw.drawToCanvas(fireballManager.setAsDrawable(gameState.getFireballs()), canvas);
            draw.drawToCanvas(rocketManager.setAsDrawable(gameState.getRockets()), canvas);
            draw.drawToCanvas(parachuteManager.setAsDrawable(gameState.getParachutes()), canvas);
            draw.drawToCanvas(robotManager.setAsDrawable(gameState.getRobots()), canvas);
            draw.drawToCanvas(bulletManager.setAsDrawable(gameState.getBullets()), canvas);
            draw.drawToCanvas(shieldManager.setAsDrawable(gameState.getShields()), canvas);
            if(!gameState.isGameOver()) {
                draw.drawLine(gameState.getSlingShot().getLeftX(), gameState.getSlingShot().getTopY(), gameState.getSlingShot().getMidX(), gameState.getSlingShot().getBottomY(), canvas);
                draw.drawLine(gameState.getSlingShot().getMidX(), gameState.getSlingShot().getBottomY(), gameState.getSlingShot().getRightX(), gameState.getSlingShot().getTopY(), canvas);
            }
            draw.drawLine(aimerCoordinates[0], aimerCoordinates[1], aimerCoordinates[2], aimerCoordinates[3], canvas);
            draw.drawToCanvas(ringExplosionManager.setAsDrawable(gameState.getRingExplosions()), canvas);
            draw.drawToCanvas(cityExplosionManager.setAsDrawable(gameState.getCityExplosions()), canvas);
            draw.drawToCanvas(atomicExplosionManager.setAsDrawable(gameState.getAtomicExplosions()), canvas);
            draw.drawToCanvas(robotExplosionManager.setAsDrawable(gameState.getRobotExplosions()), canvas);
            draw.drawToCanvas(bulletExplosionManager.setAsDrawable(gameState.getBulletExplosions()), canvas);
            if(gameState.getRocketExplosions().size() > 0)
                log("rocketExplsions.size() = " + gameState.getRocketExplosions().size());
            draw.drawToCanvas(rocketExplosionManager.setAsDrawable(gameState.getRocketExplosions()), canvas);
             //Testing, for the path of the jumping gameState.getRobots().
             /*List<GreenDot> plottedCurve = new ArrayList();
             for(int i = 0; i < gameState.getRobots().size(); i ++)
             {
                 List<Coordinate> c = gameState.getRobots().get(i).getJumpCoordinates();
                 if(c != null) {
                     for (int j = 0; j < c.size(); j++)
                         plottedCurve.add(new GreenDot(this, c.get(j).getX(), c.get(j).getY()));
                     draw.drawToCanvas(greenDotManager.setAsDrawable(plottedCurve), canvas);
                 }
             }*/
             //Testing end.


     }//end null canvas check.

    }//end onDraw

    private void initializeAllImagesOfTheGame() {
        AtomicExplosionBitmaps.initialize(this);
        BulletBitmaps.initialize(this);
        BulletExplosionBitmaps.initialize(this);
        CityBitmaps.initialize(this);
        CityExplosionBitmaps.initialize(this);
        FireballBitmaps.initialize(this);
        ParachuteBitmaps.initialize(this);
        RingExplosionBitmaps.initialize(this);
        RobotBitmaps.initialize(this);
        RobotExplosionBitmaps.initialize(this);
        RocketBitmaps.initialize(this);
        RocketExplosionBitmaps.initialize(this);
        ScreenBackgroundBitmaps.initialize(this);
        ShieldBitmaps.initialize(this);
    }


    private boolean everythingHasExploded() {
        if(gameState.getAtomicExplosions().size() == 0 &&
           gameState.getCityExplosions().size() == 0 &&
           gameState.getRingExplosions().size() == 0 &&
           gameState.getBulletExplosions().size() == 0 &&
           gameState.getRocketExplosions().size() == 0 &&
           gameState.getRobotExplosions().size() == 0 &&
           gameState.getBullets().size() == 0 &&
           gameState.getRockets().size() == 0 &&
           gameState.getRobots().size() == 0)
        {
            log("everythingHasExploded() = " + true);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void log(String string) {
        if(logEnabled)
            Log.i("MainGameView.java :::: ", string + " ::::");
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gs) {
        gameState = gs;
        restartTimers(gameState);
    }

    private void restartTimers(GameState gameState) {
        if(gameState.getAtomicExplosions().size() > 0)
        {
            List<AtomicExplosion> atomicExplosions = gameState.getAtomicExplosions();
            for(int i = 0; i < atomicExplosions.size(); i++)
            {
                atomicExplosions.get(i).startAnimation();
            }
        }
        if(gameState.getBulletExplosions().size() > 0)
        {
            List<BulletExplosion> bulletExplosions = gameState.getBulletExplosions();
            for(int i = 0; i < bulletExplosions.size(); i++)
            {
                bulletExplosions.get(i).startAnimation();
            }
        }
        if(gameState.getCityExplosions().size() > 0)
        {
            List<CityExplosion> cityExplosions = gameState.getCityExplosions();
            for(int i = 0; i < cityExplosions.size(); i++)
            {
                cityExplosions.get(i).startAnimation();
            }
        }
        if(gameState.getRingExplosions().size() > 0)
        {
            List<RingExplosion> ringExplosions = gameState.getRingExplosions();
            for(int i = 0; i < ringExplosions.size(); i++)
            {
                ringExplosions.get(i).startAnimation();
            }
        }
        if(gameState.getRobotExplosions().size() > 0)
        {
            List<RobotExplosion> robotExplosions = gameState.getRobotExplosions();
            for(int i = 0; i < robotExplosions.size(); i++)
            {
                robotExplosions.get(i).startAnimation();
            }
        }
        if(gameState.getRocketExplosions().size() > 0)
        {
            List<RocketExplosion> rocketExplosions = gameState.getRocketExplosions();
            for(int i = 0; i < rocketExplosions.size(); i++)
            {
                rocketExplosions.get(i).startAnimation();
            }
        }
        if(gameState.getRocketExplosions().size() > 0)
        {
            List<RocketExplosion> rocketExplosions = gameState.getRocketExplosions();
            for(int i = 0; i < rocketExplosions.size(); i++)
            {
                rocketExplosions.get(i).startAnimation();
            }
        }
        if(gameState.getFireballs().size() > 0)
        {
           List<Fireball> fireballs = gameState.getFireballs();
           for(int i = 0; i < fireballs.size(); i++)
           {
               fireballs.get(i).startAnimation();
           }
        }
        if(gameState.getRockets().size() > 0)
        {
            List<Parachute> parachute = gameState.getParachutes();
            for(int i = 0; i < parachute.size(); i++)
            {
                parachute.get(i).startAnimation();
            }
        }
    }

}
