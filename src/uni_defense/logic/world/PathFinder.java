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
	
	private void reset() {
		this.costMap= new int[world.getWidth()][world.getHeight()];
		for (int i=0;i<world.getWidth();i++) for (int j=0;j<world.getHeight();j++) this.costMap[i][j]=INFINITEE;
		this.dirMap= new int[world.getWidth()][world.getHeight()];
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
		//start from the end
		int auxx, auxy;
		int xa=xg;
		int ya=yg;
		int modx [] = {-1,+1,0,0};
		int mody [] = {0,0,-1,+1};
		
		while(this.dirMap[xa][ya]!=START){
			closed.add(0, new Point(xa,ya));
			auxx=xa - modx[this.dirMap[xa][ya]-1];
			auxy=ya - mody[this.dirMap[xa][ya]-1];
			xa=auxx;
			ya=auxy;
		}
		closed.add(0, new Point(xa,ya));
		return closed;
	}
	
	public List<Point> findPath(int x1, int y1, int x2, int y2) {
		 //First point on this list is the start
		this.reset();
		NodeSearch actual;
		this.costMap[x1][y1]=0;
		this.dirMap[x1][y1]=START;
		this.dirMap[x2][y2]=GOAL;
		Queue<NodeSearch> prio = new PriorityQueue<>(new Comparator<NodeSearch>() {

			@Override
			public int compare(NodeSearch o1, NodeSearch o2) {
				return Integer.compare(o1.getFcost(), o2.getFcost());
			}
		});//use actual distance and Manhattan as priority
		
		
		//iteration rules over 4 neighbors
		int modx [] = {-1,+1,0,0};
		int mody [] = {0,0,-1,+1};
		int dirV [] = {RIGHT,LEFT,DOWN,UP}; // this is the direction the new node will come from. int={1,2,3,4}
		
		prio.add(new NodeSearch(0,new Point(x1,y1),manhattanD(x1, y1, x2, y2))); //add start node
		//while there is stuff to check! CHECK DAT STUFF!
//		System.out.println("before while");
//		System.out.println(x2);
//		System.out.println(y2);
		while (!prio.isEmpty())
		{
//			System.out.println("enter while");
			//load actual position data
			actual = prio.poll(); //get priority node
			int actx = actual.getP().getX();
			int acty = actual.getP().getY();
			int currentD = this.costMap[actx][acty];
			
			//if this is the goal
//			System.out.println(actx);
//			System.out.println(acty);
			
			if (actx==x2&&acty==y2) {
				//end create the list and return
//				System.out.println("get here");
				return this.getPath(x2, y2);
			}//if end
			
			//iterate over four directions
			for (int i=0;i<4;i++) { 
				//check boundaries
				if((actx+modx[i]>=0 	&&  acty+mody[i]>=0)	&&
				(  actx+modx[i]<this.world.getWidth() 	&& acty+mody[i]<this.world.getHeight()) ){
				
					//Get positions for new candidate
					int visitx=actx+modx[i];
					int visity=acty+mody[i];
					//check if candidate is accessible
					if (world.isWalkable(visitx, visity)){
						//check if it's visited, or else add it to the priority list
						if(this.dirMap[visitx][visity]==NOTVISITED||this.dirMap[visitx][visity]==GOAL)prio.add(new NodeSearch(currentD+1,
																					  new Point(visitx,visity),
																					  currentD+1+this.manhattanD(visitx, visity, x2, y2)));
						//check if this path is better, then update your costs.
						int tentativeScore = currentD+1;
						if (tentativeScore < this.costMap[visitx][visity]) {
							this.costMap[visitx][visity] = tentativeScore;
							this.dirMap [visitx][visity] = dirV[i];
						}//if (score) end
					
					}//if (walkable) end
				}//if (boundaries) end
			}// for end
		
		}//while end
		//if the destination has no available path...
		return null;
	}// function end
	
	
}

