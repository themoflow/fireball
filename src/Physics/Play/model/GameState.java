package Physics.Play.model;

import Physics.Play.drawables.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by morantornesella-brooks on 10/9/14.
 */
public class GameState implements Serializable {

    /*public static float screenWidth;
    public static float screenHeight;*/
    private float length;
    private boolean dragged = false, down = false, gameOverSequenceStarted = false;
    public boolean gameOver;
    public boolean timeToRestart = false;

    //Drawable lists.
    private List<Robot> robots;
    private List<Rocket> rockets;
    private List<City> citys;
    private List<RocketExplosion> rocketExplosions;
    private List<Fireball> fireballs;
    private List<ScreenBackground> screenBackgrounds;
    private List<GreenDot> greenDots;
    private List<Bullet> bullets;
    private List<BulletExplosion> bulletExplosions;
    private List<Parachute> parachutes;
    private List<RobotExplosion> robotExplosions;
    private List<AtomicExplosion> atomicExplosions;
    private List<CityExplosion> cityExplosions;
    private List<Shield> shields;
    private List<RingExplosion> ringExplosions;
    private SlingShot slingShot;

    /*//Drawable managers.
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
    private Drawer draw = Drawer.getInstance();*/

    public GameState() {
        //Initialize Drawable lists.
        robots = new ArrayList();
        rockets = new ArrayList();;
        citys = new ArrayList();
        rocketExplosions = new ArrayList();
        fireballs = new ArrayList();
        screenBackgrounds = new ArrayList<ScreenBackground>();
        greenDots = new ArrayList<GreenDot>();
        bullets = new ArrayList();
        bulletExplosions = new ArrayList();
        parachutes = new ArrayList();
        robotExplosions = new ArrayList();
        atomicExplosions = new ArrayList();
        cityExplosions = new ArrayList();
        shields = new ArrayList();
        ringExplosions = new ArrayList();
    }

   /* public static float getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(float screenWidth) {
        GameState.screenWidth = screenWidth;
    }

    public static float getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(float screenHeight) {
        GameState.screenHeight = screenHeight;
    }*/

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public boolean isTimeToRestart() {
        return timeToRestart;
    }

    public void setTimeToRestart(boolean t) {
        this.timeToRestart = t;
    }

    public boolean isDragged() {
        return dragged;
    }

    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isGameOverSequenceStarted() {
        return gameOverSequenceStarted;
    }

    public void setGameOverSequenceStarted(boolean gameOverSequenceStarted) {
        this.gameOverSequenceStarted = gameOverSequenceStarted;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

   /* public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }*/

    public List<Robot> getRobots() {
        return robots;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public void setRockets(List<Rocket> rockets) {
        this.rockets = rockets;
    }

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    public List<RocketExplosion> getRocketExplosions() {
        return rocketExplosions;
    }

    public void setRocketExplosions(List<RocketExplosion> rocketExplosions) {
        this.rocketExplosions = rocketExplosions;
    }

    public List<Fireball> getFireballs() {
        return fireballs;
    }

    public void setFireballs(List<Fireball> fireballs) {
        this.fireballs = fireballs;
    }

    public List<ScreenBackground> getScreenBackgrounds() {
        return screenBackgrounds;
    }

    public void setScreenBackgrounds(List<ScreenBackground> screenBackgrounds) {
        this.screenBackgrounds = screenBackgrounds;
    }

    public List<GreenDot> getGreenDots() {
        return greenDots;
    }

    public void setGreenDots(List<GreenDot> greenDots) {
        this.greenDots = greenDots;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<BulletExplosion> getBulletExplosions() {
        return bulletExplosions;
    }

    public void setBulletExplosions(List<BulletExplosion> bulletExplosions) {
        this.bulletExplosions = bulletExplosions;
    }

    public List<Parachute> getParachutes() {
        return parachutes;
    }

    public void setParachutes(List<Parachute> parachutes) {
        this.parachutes = parachutes;
    }

    public List<RobotExplosion> getRobotExplosions() {
        return robotExplosions;
    }

    public void setRobotExplosions(List<RobotExplosion> robotExplosions) {
        this.robotExplosions = robotExplosions;
    }

    public List<AtomicExplosion> getAtomicExplosions() {
        return atomicExplosions;
    }

    public void setAtomicExplosions(List<AtomicExplosion> atomicExplosions) {
        this.atomicExplosions = atomicExplosions;
    }

    public List<CityExplosion> getCityExplosions() {
        return cityExplosions;
    }

    public void setCityExplosions(List<CityExplosion> cityExplosions) {
        this.cityExplosions = cityExplosions;
    }

    public List<Shield> getShields() {
        return shields;
    }

    public void setShields(List<Shield> shields) {
        this.shields = shields;
    }

    public List<RingExplosion> getRingExplosions() {
        return ringExplosions;
    }

    public void setRingExplosions(List<RingExplosion> ringExplosions) {
        this.ringExplosions = ringExplosions;
    }

    /*public RobotManager getRobotManager() {
        return robotManager;
    }

    public void setRobotManager(RobotManager robotManager) {
        this.robotManager = robotManager;
    }

    public RocketManager getRocketManager() {
        return rocketManager;
    }

    public void setRocketManager(RocketManager rocketManager) {
        this.rocketManager = rocketManager;
    }

    public CityManager getCityManager() {
        return cityManager;
    }

    public void setCityManager(CityManager cityManager) {
        this.cityManager = cityManager;
    }

    public RocketExplosionManager getRocketExplosionManager() {
        return rocketExplosionManager;
    }

    public void setRocketExplosionManager(RocketExplosionManager rocketExplosionManager) {
        this.rocketExplosionManager = rocketExplosionManager;
    }

    public FireballManager getFireballManager() {
        return fireballManager;
    }

    public void setFireballManager(FireballManager fireballManager) {
        this.fireballManager = fireballManager;
    }

    public ScreenBackgroundManager getScreenBackgroundManager() {
        return screenBackgroundManager;
    }

    public void setScreenBackgroundManager(ScreenBackgroundManager screenBackgroundManager) {
        this.screenBackgroundManager = screenBackgroundManager;
    }

    public GreenDotManager getGreenDotManager() {
        return greenDotManager;
    }

    public void setGreenDotManager(GreenDotManager greenDotManager) {
        this.greenDotManager = greenDotManager;
    }

    public BulletManager getBulletManager() {
        return bulletManager;
    }

    public void setBulletManager(BulletManager bulletManager) {
        this.bulletManager = bulletManager;
    }

    public BulletExplosionManager getBulletExplosionManager() {
        return bulletExplosionManager;
    }

    public void setBulletExplosionManager(BulletExplosionManager bulletExplosionManager) {
        this.bulletExplosionManager = bulletExplosionManager;
    }

    public SlingShotManager getSlingShotManager() {
        return slingShotManager;
    }

    public void setSlingShotManager(SlingShotManager slingShotManager) {
        this.slingShotManager = slingShotManager;
    }

    public ParachuteManager getParachuteManager() {
        return parachuteManager;
    }

    public void setParachuteManager(ParachuteManager parachuteManager) {
        this.parachuteManager = parachuteManager;
    }

    public RobotExplosionManager getRobotExplosionManager() {
        return robotExplosionManager;
    }

    public void setRobotExplosionManager(RobotExplosionManager robotExplosionManager) {
        this.robotExplosionManager = robotExplosionManager;
    }

    public AtomicExplosionManager getAtomicExplosionManager() {
        return atomicExplosionManager;
    }

    public void setAtomicExplosionManager(AtomicExplosionManager atomicExplosionManager) {
        this.atomicExplosionManager = atomicExplosionManager;
    }

    public CityExplosionManager getCityExplosionManager() {
        return cityExplosionManager;
    }

    public void setCityExplosionManager(CityExplosionManager cityExplosionManager) {
        this.cityExplosionManager = cityExplosionManager;
    }

    public ShieldManager getShieldManager() {
        return shieldManager;
    }

    public void setShieldManager(ShieldManager shieldManager) {
        this.shieldManager = shieldManager;
    }

    public RingExplosionManager getRingExplosionManager() {
        return ringExplosionManager;
    }

    public void setRingExplosionManager(RingExplosionManager ringExplosionManager) {
        this.ringExplosionManager = ringExplosionManager;
    }

    public Drawer getDraw() {
        return draw;
    }

    public void setDraw(Drawer draw) {
        this.draw = draw;
    }*/

    public SlingShot getSlingShot() {
        return slingShot;
    }

    public void setSlingShot(SlingShot slingShot) {
        this.slingShot = slingShot;
    }





}
