package uni_defense.logic.world;

public abstract class MovableObject {

    private double x;
    
    private double y;

    public MovableObject(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }
    
    /**
     * @param dtime The time since the last step in milliseconds.
     */
    public void step(double dtime) {
    }
    
    /**
     * The tile that this object is considered to be on.
     */
    public Point getCurrentTile() {
        return new Point((int) Math.round(x), (int) Math.round(y));
    }
    
    public String getID() {
    	return this.getClass().getSimpleName().toLowerCase();
    }
    
    /**
     * @return Size of the image of this object in pixels. 0 to use default size.
     */
    public int getSize() {
        return 0;
    }
    
}
