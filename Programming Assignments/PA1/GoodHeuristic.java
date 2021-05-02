//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method. The provided "good"
// heuristic function is admissible.
//
// Josue Gialis -- 10/16/2020
//


// IMPORT ANY PACKAGES THAT YOU NEED.
// import java.util.*;
//Josue Gialis Final

public class GoodHeuristic extends Heuristic {

	double hLongitude;
	double hLatitude;
	Location loc;
        // YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
        // ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.
	public GoodHeuristic() {

		hLongitude = 0;
		hLatitude = 0;
	}
	
	public GoodHeuristic(Location loc) {
		super(loc);
		this.loc = loc;
	}
	
	
	
	// heuristicValue -- Return the appropriate heuristic values for the
	// given search tree node. Note that the given Node should not be
	// modified within the body of this function.
	public double heuristicValue(Node thisNode) {
		
		double hVal = 0.0;
		// COMPUTE A REASONABLE HEURISTIC VALUE HERE
		double nodeX = Math.pow(thisNode.loc.longitude, 2);
		double nodeY = Math.pow(thisNode.loc.latitude, 2);
		
		
		double goalX = Math.pow(this.loc.longitude, 2);
		double goalY = Math.pow(this.loc.latitude, 2);
		
		hVal = Math.sqrt(Math.abs(goalX - nodeX) + (goalY - nodeY));

		return (hVal);
	}

}
