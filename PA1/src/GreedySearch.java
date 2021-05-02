//Josue Gialis Final
public class GreedySearch {

	RoadMap map;
	String startLoc;
	String endLoc;
	int depthLimit;
	SearchDisplay display;
	public int expansionCount;
	
	//Default Constructor with no parameterization
	public GreedySearch() {
		map = new RoadMap();
		startLoc = "";
		endLoc = "";
		depthLimit = 0;
		display = new SearchDisplay();
	}

	//Specified constructor 
	public GreedySearch(RoadMap map, String startLoc, String endLoc, int depthLimit, SearchDisplay display) {
		this.map = map;
		this.startLoc = startLoc;
		this.endLoc = endLoc;
		this.depthLimit = depthLimit;
		this.display = display;
	}


	public Node search(boolean RSC) {
		
		//Initializing expansion count to zero.
		expansionCount = 0;
		
		//No repeated State checking
		if (RSC == false) {
			//Creating initial node with startLoc
			Node node = new Node(map.findLocation(startLoc));
			//Creating our GoodHeuristic object 
			GoodHeuristic hVal = new GoodHeuristic(map.findLocation(endLoc));
			//Initializing a frontier with state of the problem, sorting by the f(n) cost
			SortedFrontier queue = new SortedFrontier(SortBy.h);
			queue.addSorted(node);

			//Beginning iteration of search with condition
			//that the SortedFrontier can never be empty.
			while(queue.isEmpty() == false) {
				//Selecint a leaf node from the frontier.
				node = queue.removeTop();
				
				

				//If depth limit is reached, terminate search to avoid infinite loops.
				if(node.depth == depthLimit) {
					return (null);
				}
				
				//If node contains a goal state, return solution
				if(node.isDestination(endLoc)) {
					return (node);
				}
				
				//Assigning heuristic value to given node (AT THE MOMENT ZERO)
				node.heuristicValue = hVal.heuristicValue(node);
				//Expanding the node to obtain its children 
				//and adding them to the frontier.
				node.expand(hVal);
				queue.addSorted(node.children);
				//Increment expansion count by 1.
				expansionCount++;	
			}	
		 }	
		
		//Performing search with repeated state checking
		else if (RSC == true) {
			//Creating initial node with startLoc
			Node node = new Node(map.findLocation(startLoc));
			//Creating our GoodHeuristic object 
			GoodHeuristic hVal = new GoodHeuristic(map.findLocation(endLoc));
			//Initializing a frontier with state of the problem
			SortedFrontier queue = new SortedFrontier(SortBy.h);
			queue.addSorted(node);
			
			//Initializing an empty hash set.
			LocationSet exploredSet = new LocationSet();
			
		
			//Beginning iteration of search with condition
			//that the SortedFrontier can never be empty.
			while(queue.isEmpty() == false) {
				//Selecing a leaf node from the frontier.
				node = queue.removeTop();
				//display.updateDisplay(node, queue, exploredSet, expansionCount);

				//If depth limit is reached, terminate search to avoid infinite loops.
				if(node.depth == depthLimit) {
					return (null);
				}
				
				//If node contains a goal state, return solution
				if(node.isDestination(endLoc)) {
					return (node);
				}
				
				//Adding nodes location name to the explored set.
				exploredSet.add(node.loc.name);
				//Expanding the node to obtain its children.
				node.expand(hVal);
			
				
				for(Node iter: node.children) {
					//If the child is not in the frontier or in the 
					//explored set, then add the child to the frontier.
					if(!queue.contains(iter.loc.name) && !exploredSet.contains(iter.loc.name)){
 						queue.addSorted(iter);
					}
				}	
				//Increment expansion count by 1.
				expansionCount++;
			}
		}
		//If our while loop terminates due to an 
		//empty frontier, return no solution (null).
		return (null);
	}
}
