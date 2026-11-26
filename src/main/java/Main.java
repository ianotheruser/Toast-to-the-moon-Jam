import zero.GameApp;
import zero.Color;
import zero.Key;


public class Main extends GameApp {
    private Player player;
    private Moon[] moons = new Moon[3];
    private int points;
    private double timeLeft = 60;
    
    @Override public void settings() {
        padding(20);
        size(640, 400);
    }

    @Override public void setup() {
        player = new Player(40, 180);
        moons[0] = new Moon(220,180);
        moons[1] = new Moon(340,200);
        moons[2] = new Moon(50,300);
    }

    @Override public void update(double seconds) {
        timeLeft -= seconds;
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
            }
        }
        if(timeLeft <= 0)
        {
            timeLeft = 0;
            return;
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
    }


    public static void main(String[] args) { launch(args); }
}