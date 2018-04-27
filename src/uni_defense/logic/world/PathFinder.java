package uni_defense.logic.world;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder {
	
	private static final int UP         = 4;
	private static final int DOWN       = 3;
	private static final int LEFT       = 2;
	private static final int RIGHT      = 1;
	private static final int START      = 5;
	private static final int GOAL       = 6;
	private static final int NOTVISITED = 0;
	private static final int FINAL      = 6; 
	private static final int INFINITEE  = Integer.MAX_VALUE;
	
	private World world;
	private int[][] costMap;
	private int[][] dirMap; //1-up 2-down 3-left 4-right 5-START
	public PathFinder(World world) {
		this.world=world;
		this.costMap= new int[world.getWidth()][world.getHeight()];
		for (int i=0;i<world.getWidth();i++) for (int j=0;j<world.getHeight();j++) this.costMap[i][j]=INFINITEE;
		this.dirMap= new int[world.getWidth()][world.getHeight()];
	}
	
	private int absoluteV(int x)
	{
		if (x<0) return -x;
		return x;
	}
	
	private int manhattanD(int x1, int y1, int x2, int y2)
	{
		int xd = this.absoluteV(x1-x2);
		int yd = this.absoluteV(y1-y2);
		return xd+yd;
	}
	
	private List<Point> getPath(int xg, int yg)
	{
		List<Point> closed = new LinkedList<>();
		int xa=xg;
		int ya=yg;
		int modx [] = {-1,+1,0,0};
		int mody [] = {0,0,-1,+1};
		int dirV [] = {RIGHT,LEFT,DOWN,UP};
		while(this.dirMap[xa][ya]!=START){
			closed.add(0, new Point(xa,ya));
			xa=xa - modx[this.dirMap[xa][ya]-1];
			ya=ya - mody[this.dirMap[xa][ya]-1];
		}
		closed.add(0, new Point(xa,ya));
		return closed;
	}
	
	public List<Point> findPath(int x1, int y1, int x2, int y2) {
		 //First point on this list is the start
		
		NodeSearch actual;
		this.costMap[x1][y1]=0;
		
		this.dirMap[x1][y1]=START;
		this.dirMap[x2][y2]=GOAL;
		Queue<NodeSearch> prio = new PriorityQueue<>(new Comparator<NodeSearch>() {

			@Override
			public int compare(NodeSearch o1, NodeSearch o2) {
				return Integer.compare(o1.getFcost(), o2.getFcost());
			}
		});
		
		
		//iteration rules over 4 neighbors
		int modx [] = {-1,+1,0,0};
		int mody [] = {0,0,-1,+1};
		int dirV [] = {RIGHT,LEFT,DOWN,UP};
		
		prio.add(new NodeSearch(0,new Point(x1,y1),manhattanD(x1, y1, x2, y2))); //add start node
		while (!prio.isEmpty())
		{
			actual = prio.poll(); //get priority node
			
			int actx = actual.getP().getX();
			int acty = actual.getP().getY();
			int currentD = this.costMap[actx][acty];
			
			if (actx==x2&&acty==y2) {
				//end create the list and return			
				return this.getPath(x2, y2);
			}
			
			for (int i=0;i<4;i++) { //iterate over four directions
				//check boundaries
				if((actx+modx[i]>=0 	&&  acty+mody[i]>=0)	&&
				(  actx+modx[i]<this.world.getWidth() 	&& acty+mody[i]<this.world.getHeight()) ){
				
					int visitx=actx+modx[i];
					int visity=acty+mody[i];
					if (world.isWalkable(visitx, visity)){
						if(this.dirMap[visitx][visity]==NOTVISITED)prio.add(new NodeSearch(currentD+1,
																					  new Point(visitx,visity),
																					  currentD+1+this.manhattanD(visitx, visity, x2, y2)));
						int tentativeScore = currentD+1;
						if (tentativeScore < this.costMap[visitx][visity]) {
							this.costMap[visitx][visity] = tentativeScore;
							this.dirMap [visitx][visity] = dirV[i];
						}
					
					}
				}
			}
		
		}
		return null;
	}
	
	
}

