import zero.GameApp;
import zero.Color;
import zero.Key;


public class Main extends GameApp {
    private Player player;
    private Moon[] moons = new Moon[5];
    private int points;
    private double breadTimer = 0;
    
    @Override public void settings() {
        padding(20);
        size(640, 400);
    }

    @Override public void setup() {
        player = new Player(40, 180);
        moons[0] = new Moon(220,180);
        moons[1] = new Moon(340,200);
        moons[2] = new Moon(50,300);
        moons[3] = new Moon(270,120);
        moons[4] = new Moon(70,160);
    }

    @Override public void update(double seconds) {
        breadTimer -= seconds;
        int horizontal = 0;
        int vertical = 0;
        if (keyDown(Key.LEFT)) horizontal--;
        if (keyDown(Key.RIGHT)) horizontal++;
        if (keyDown(Key.UP)) vertical--;
        if (keyDown(Key.DOWN)) vertical++;
        player.update(horizontal, vertical, seconds, width(), height());
        for(int i = 0; i < moons.length; i++){
            if (!moons[i].isCollected() && moons[i].touches(player)) {
                moons[i].collect();
                points++;
                breadTimer = 2;
            }
        }
    }

    @Override public void draw() {
        background(Color.rgb(240, 248, 255));
        fill(Color.rgb(47, 79, 79));
        textSize(20);
        text("Click the canvas, then hold the arrow keys.", 20, 30);
        text("Score: " + points, 20, 60);
        for (int i = 0; i < moons.length; i++){
            moons[i].draw(this);
        }
        player.draw(this);
        if(breadTimer > 0)
        {
            text("Bread!", 20, 90);
        }
    }


    public static void main(String[] args) { launch(args); }
}