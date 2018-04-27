package uni_defense.logic.world;

public class NodeSearch {
	int cost, fcost;
	Point p;
	public NodeSearch(int cost, Point p, int fcost) {
		this.cost=cost;
		this.fcost=fcost;
		this.p=new Point(p.getX(),p.getY());
	}
	public int getFcost() {
		return fcost;
	}
	public void setFcost(int fcost) {
		this.fcost = fcost;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public Point getP() {
		return p;
	}

}
