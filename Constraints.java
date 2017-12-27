/**
 * 
 * @author Caleb Crosby
 * 12/5/2017
 * Sets up the Constraint object to be used in the Course class for calculating conflicts and reducing conflicts
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Constraints {
	
	private int class1;
	private String constraint;
	private int class2;
	private int intClass1Value;
	private int class2IntValue = 0;
	private String[] constraints;
	private ArrayList<Constraints> constraintsList = new ArrayList<Constraints>();
	private Constraints con;
	
	/**
	 * First constructor for Constraint class
	 * 
	 * reads the constraint file when called in the ReadFile class
	 */
	public Constraints() {
		readConstraintsFile();
	}

	/**
	 * Reads the constraint file and filters each line by grabbing the Strings after each space
	 * Sets up a Constraint object and inserts it into an ArrayList
	 * Uses switch statement to convert class names to its integer representation
	 * 
	 * @return constraintsList containing the objects representing the constraints
	 */
	public ArrayList<Constraints> readConstraintsFile() {
		String constraintsFile = "constraints.txt";
		String line = null;
		try {
			FileReader fileReader = new FileReader(constraintsFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			constraints = new String[95];
			while ((line = bufferedReader.readLine()) != null) {
				
				String class1Value;
				String constraintValue;
				String class2Value;
				
				constraints = line.trim().split("\\s+");

				class1Value = constraints[0];
				constraintValue = constraints[1];
				class2Value = constraints[2];
				
				switch(class1Value){
				case "ICS140":
					intClass1Value = 140;
					break;
				case "ICS141":
					intClass1Value = 141;
					break;
				case "ICS232":
					intClass1Value = 232;
					break;
				case "ICS240":
					intClass1Value = 240;
					break;
				case "ICS311":
					intClass1Value = 311;
					break;
				case "ICS340":
					intClass1Value = 340;
					break;
				case "ICS365":
					intClass1Value = 365;
					break;
				case "ICS372":
					intClass1Value = 372;
					break;
				case "Math120":
					intClass1Value = 120;
					break;
				case "Math210":
					intClass1Value = 210;
					break;
				case "Math211":
					intClass1Value = 211;
					break;
				case "Math215":
					intClass1Value = 215;
					break;
				case "Writ131":
					intClass1Value = 131;
					break;
				case "Writ231":
					intClass1Value = 231;
					break;
				}
				
			switch(class2Value){
				case "ICS140":
					class2IntValue = 140;
					break;
				case "ICS141":
					class2IntValue = 141;
					break;
				case "ICS232":
					class2IntValue = 232;
					break;
				case "ICS240":
					class2IntValue = 240;
					break;
				case "ICS311":
					class2IntValue = 311;
					break;
				case "ICS340":
					class2IntValue = 340;
					break;
				case "ICS365":
					class2IntValue = 365;
					break;
				case "ICS372":
					class2IntValue = 372;
					break;
				case "ICS382":
					class2IntValue = 382;
					break;
				case "ICS440":
					class2IntValue = 440;
					break;
				case "ICS460":
					class2IntValue = 460;
					break;
				case "ICS462":
					class2IntValue = 462;
					break;
				case "ICS471":
					class2IntValue = 471;
					break;
				case "ICS499":
					class2IntValue = 499;
					break;
				case "Math210":
					class2IntValue = 210;
					break;
				case "Math211":
					class2IntValue = 211;
					break;
				case "Math215":
					class2IntValue = 215;
					break;
				case "Math310":
					class2IntValue = 310;
					break;
				case "Math320":
					class2IntValue = 320;
					break;
				case "Writ231":
					class2IntValue = 233;
					break;
				}
				
				//create Constraint object
				con = new Constraints(intClass1Value, constraintValue, class2IntValue);
				
				//add the Constraint object to the constraintsList
				constraintsList.add(con);
			}
	
			bufferedReader.close();
			
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + constraintsFile + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + constraintsFile + "'");
		}
		return constraintsList;
	}
	
	/**
	 * used in Course class to calculate conflicts
	 * @return constraintList
	 */
	public ArrayList<Constraints> getConstraintsList(){
		return constraintsList;
	}
	
	/**
	 * 
	 * Second constructor for Constraints class that sets up the constraint objects
	 */
	public Constraints(int class1, String constraint, int class2) {
		super();
		this.class1 = class1;
		this.constraint = constraint;
		this.class2 = class2;
	}
	
	//getters and setters 
	
	public int getClass1() {
		return class1;
	}

	public void setClass1(int class1) {
		this.class1 = class1;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public int getClass2() {
		return class2;
	}

	public void setClass2(int class2) {
		this.class2 = class2;
	}

	@Override
	public String toString() {
		return "Constraints [class1=" + class1 + ", constraint=" + constraint + ", class2=" + class2 + "]";
	}

}
