package uni_defense.logic.buildings;

public abstract class Building {

    /**
     * @param dtime The time since last step in milliseconds.
     */
    public abstract void step(double dtime, int x, int y);
    
    public abstract int getInfluenceRange();
    
    public abstract int getThreatLevel();
    
    public String getID() {
        return this.getClass().getSimpleName().toLowerCase();
    }
    
}
