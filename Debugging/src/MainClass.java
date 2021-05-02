import java.io.*;
import java.util.*;


public class CreateTables{
    public class CreateTable {
        public String create = "CREATE TABLE" ;
        public String tableName = "";
        public String openParanthesis = " ( ";
        public String closeParanthesis = " );";
        public String comma = ", ";
        public ArrayList<String> attributes = new ArrayList<String>();
        public String [] type = {"char(255)", "decimal(8,2"};

        public CreateTable(String tableName) {
            this.tableName = tableName;

        }
       
        public void populateAttributes(ArrayList<String> attributeNames) {
            //some sht
        }
    }
}

public class MainClass {
	
	public static void main(String[] args) {
	
		double  l  = Math.sqrt(Math.abs((Math.pow(4, 2) -  Math.pow(3, 2))));
		
		System.out.println("HI");
		
		
	}
	
		
		
}
