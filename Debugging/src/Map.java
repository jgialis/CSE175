import java.io.*;

public class Map {

	Location loc;
	
	public Map() {
		loc = new Location("Bonifacio");
	}
	
	public Location findLocation(String name) {
		if (loc.start.equals(name)) {
		return (loc);
	}
		return (null);
	}
	
	public void printRandom() {
		System.out.println("HELLO BITCH");
	}
}
