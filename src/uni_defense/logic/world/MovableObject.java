package uni_defense.logic.world;

public abstract class MovableObject {

    private float x;
    
    private float y;

    public MovableObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    public void move(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }
    
    /**
     * @param dtime The time since the last step in nanseconds.
     */
    public void step(long dtime) {
    }
    
}
