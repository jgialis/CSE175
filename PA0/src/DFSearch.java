////JG
import java.io.*;
import java.util.*;

public class DFSearch {

	//I will declare the necessary
	//data members specified in the 
	//PA0 specification document.
	public int depthLimit;
	public String startLoc;
	public String endLoc;
	public Map mapObj;
	public int expansionCount;
	public SearchDisplay display;
	
	
	//Specified Constructor with appropriate parameterizations 
	public DFSearch (Map mapObj, String startLoc, String endLoc, int depthLimit, SearchDisplay display) {
		
		this.mapObj = mapObj;					//initialize mapObj
		this.startLoc = startLoc; 		//initialize startLoc point
		this.endLoc = endLoc;			//initialize endLoc point
		this.depthLimit = depthLimit;	//initialize depth depthLimit
		this.display = display;			//Toggle display option, won't be using since not necessary 

	}
	
	
	public Node search(boolean RSC) {
		
		//This variable will help us keep track of
		//how many different expansions were done
		//in order to reach a solution or the depth depthLimit.
		//This will help us to compare our algorithm when
		//using RSC or not using RSC.
		expansionCount = 0;
	
		
		//No repeated state checking
		if(RSC == false){
			
			//Creating first node corresponding 
			//to a specified location (startLocLoc) on mapObjObj.
			Node nodeObj = new Node(mapObj.findLocation(startLoc));
			
			//Creating an initially empty
			//frontier that will contain leaf nodes. 
			//We will implement it as a stack.
			Frontier stack = new Frontier();
			
			//Adding the initial node to our Frontier Stack.
//			System.out.println("Initial adding " + nodeObj.loc.name);
			stack.addToTop(nodeObj);
	
			//While loop to begin DFS with no RSC.
			while(nodeObj.depth <= depthLimit && !stack.isEmpty()) 
			{
//				System.out.print("\nStack Frontier = ");
//				for(int i = 0; i < stack.fringe.size(); i++) {
//				
//				if(nodeObj.depth == depthLimit || stack.isEmpty()) {
//					return null;
//				}
				
				//Chooses the most shallow node in frontier.
//						System.out.println("removing " + nodeObj.loc.name);
						nodeObj = stack.removeTop();
					
					if(nodeObj.isDestination(endLoc)){
							return nodeObj;
					} 
						nodeObj.expand();
						stack.addToTop(nodeObj.children);
//						System.out.println("expanding " + nodeObj.loc.name);
						
//						System.out.print("adding : "); 
//						for(int i = 0; i < nodeObj.children.size(); i++) {
//						System.out.print(nodeObj.children.get(i).loc.name + ", ");}
//						System.out.println();
						expansionCount++;
				
			}
			return (null);
		}
		

		//Repeated state checking active
		else
		{
			//Repeat same process as above, except this time, we will 
			//check for any repeated states we come across to optimize
			//our efficiency. We will do this by this time declaring a 
			//LocationSet object which we will store all of our visited states in.
			Node nodeObj = new Node(mapObj.findLocation(startLoc));
			Frontier stack = new Frontier();
			LocationSet exploredSet = new LocationSet();
			
			stack.addToTop(nodeObj);

			while(true) 
				{
//				System.out.print("\nStack Frontier = ");
				
					//If the frontier is empty return failure
					if(stack.isEmpty()){
						return null; 
					} 
					
					//Choose a leaf node and remove it from the frontier.
					else{
						nodeObj = stack.removeTop();
						
						//If the node contains a goal state, return that solution.
						if(nodeObj.isDestination(endLoc)){
							return nodeObj;
						}
						
						nodeObj.expand();
						exploredSet.add(nodeObj.loc.name);
						expansionCount = expansionCount+1;
					}
					
					//Following the pseudocode provided by Dr. Noelle David, we will iterate through 
					//the children of our expanded node to verify that the conditions of not being in
					//the explored set or stack are met.	Only then will we add that child to the stack for expansion.
					for(Node iter: nodeObj.children){
					if(!exploredSet.contains(iter.loc.name) && !stack.contains(iter.loc.name)){
						stack.addToTop(iter);
					}
				}
			}
		}	
	}	
}
