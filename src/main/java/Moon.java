import zero.Color;
import zero.GameApp;
public class Moon {
    private final double x;
    private final double y;
    private final double size = 24;
    private boolean collected;

    public Moon(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean isCollected() { return collected; }
    public boolean touches(Player player) { return player.touches(x, y, size); }
    public void collect() { collected = true; }

    public void draw(GameApp app) {
        if (collected) return;
        app.fill(Color.rgb(200, 195, 191));
        app.rect(x, y, size, size);
    }
}

