/**
 * 
 * @author Caleb Crosby
 * 12/5/2017
 * Sets up a course object and performs the local search on a random schedule
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Course extends Constraints {

	private String courseName;
	private String fallSemester;
	private String springSemester;
	private String summerSemester;
	public String[] courseSchedule;
	public String[] daysFall = new String[6];
	public String[] daysSpring = new String[6];
	public String[] daysSummer = new String[6];
	public String[] season = new String[3];
	public int[] daysOffered = new int[6];
	public int intClassName;
	public int classOne;
	public int classTwo;
	public int classZero;
	public int semesterConflict;
	private ArrayList<String> randomDaysFall = new ArrayList<String>();
	private ArrayList<String> randomDaysSpring = new ArrayList<String>();
	private ArrayList<String> randomDaysSummer = new ArrayList<String>();
	private ArrayList<randomCourse> randomCoursesList = new ArrayList<randomCourse>();
	private ArrayList<Course> coursesList = new ArrayList<Course>();
	private ArrayList<Integer> semesterTracker = new ArrayList<Integer>();
	private randomCourse rC;
	private randomCourse rCc;
	private randomCourse rCc2;
	private randomCourse maxCourse;
	private Course co;
	public int iterationCount = 0;
	
	/**
	 * First constructor for Course class
	 * 
	 * reads the course file when called in the ReadFile class
	 */
	public Course() {
		readCourseFile();
	}
	
	/**
	 * Reads the course file and filters each line by grabbing the Strings after each space
	 * Sets up a Course object and inserts it into an ArrayList
	 * Uses switch statement to convert class names to its integer representation
	 */
	public void readCourseFile() {
		String courseFile = "classes.txt";
		String line = null;

		try {

			FileReader fileReader = new FileReader(courseFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			courseSchedule = new String[30];

			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {

				if (row != 0) {
					String courseName;
					String daysAvailableFall;
					String daysAvailableSpring;
					String daysAvailableSummer;

					courseSchedule = line.trim().split("\\s+");

					courseName = courseSchedule[0];
					daysAvailableFall = courseSchedule[1];
					daysAvailableSpring = courseSchedule[2];
					daysAvailableSummer = courseSchedule[3];

					switch (courseName) {
					case "ICS140":
						intClassName = 140;
						break;
					case "ICS141":
						intClassName = 141;
						break;
					case "NSci204":
						intClassName = 204;
						break;
					case "ICS231":
						intClassName = 231;
						break;
					case "ICS232":
						intClassName = 232;
						break;
					case "ICS240":
						intClassName = 240;
						break;
					case "RelS304":
						intClassName = 304;
						break;
					case "Math310":
						intClassName = 310;
						break;
					case "ICS311":
						intClassName = 311;
						break;
					case "Math320":
						intClassName = 320;
						break;
					case "Comm320":
						intClassName = 321;
						break;
					case "ICS340":
						intClassName = 340;
						break;
					case "ICS365":
						intClassName = 365;
						break;
					case "ICS372":
						intClassName = 372;
						break;
					case "ICS382":
						intClassName = 382;
						break;
					case "ICS440":
						intClassName = 440;
						break;
					case "ICS460":
						intClassName = 460;
						break;
					case "ICS462":
						intClassName = 462;
						break;
					case "ICS471":
						intClassName = 471;
						break;
					case "ICS499":
						intClassName = 499;
						break;
					case "EthS100":
						intClassName = 100;
						break;
					case "Lit100":
						intClassName = 101;
						break;
					case "Psyc100":
						intClassName = 103;
						break;
					case "Chem105":
						intClassName = 105;
						break;
					case "Phys110":
						intClassName = 110;
						break;
					case "Math120":
						intClassName = 120;
						break;
					case "Math210":
						intClassName = 210;
						break;
					case "Math211":
						intClassName = 211;
						break;
					case "Math215":
						intClassName = 215;
						break;
					case "Writ131":
						intClassName = 131;
						break;
					case "Writ231":
						intClassName = 233;
						break;

					}

					co = new Course(intClassName, daysAvailableFall, daysAvailableSpring, daysAvailableSummer);

					coursesList.add(co);
					row++;
				} else {
					row++;
				}
			}
			
			//create the random schedule
			createRandomSchedule();
			
			//sort the list according to semester value
			Collections.sort(randomCoursesList);
			for (int a = 0; a < randomCoursesList.size(); a++) {
				System.out.println(randomCoursesList.get(a).toString());
			}

			//perform Local Search
			localSearch();
			bufferedReader.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + courseFile + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + courseFile + "'");
		}
	}
	/**
	 * creates ArrayList of semesters to be randomly added to a course
	 */
	public void createSemesterTracker() {
		semesterTracker.add(1); // 3 classes for Fall2018
		semesterTracker.add(1);
		semesterTracker.add(1);
		semesterTracker.add(2); // 3 classes for Spring2019
		semesterTracker.add(2);
		semesterTracker.add(2);
		semesterTracker.add(3); // 2 classes for Summer2019
		semesterTracker.add(3);
		semesterTracker.add(4); // 3 classes for Fall2019
		semesterTracker.add(4);
		semesterTracker.add(4);
		semesterTracker.add(5); // 3 classes for Spring2020
		semesterTracker.add(5);
		semesterTracker.add(5);
		semesterTracker.add(6); // 2 classes for Summer2020
		semesterTracker.add(6);
		semesterTracker.add(7); // 3 classes for Fall2020
		semesterTracker.add(7);
		semesterTracker.add(7);
		semesterTracker.add(8); // 3 classes for Spring2021
		semesterTracker.add(8);
		semesterTracker.add(8);
		semesterTracker.add(9); // 2 classes for Summer2021
		semesterTracker.add(9);
		semesterTracker.add(10); // 3 classes for Fall2021
		semesterTracker.add(10);
		semesterTracker.add(10);
		semesterTracker.add(11); // 3 classes for Spring2022
		semesterTracker.add(11);
		semesterTracker.add(11);
	}
	
	/**
	 * Creates a random course object which gets added to an ArrayList which represents the random course schedule
	 * 
	 * Iterates until every class name, semester, fall, spring, and summer day has been randomly grabbed to set up a random schedule
	 */
	public void createRandomSchedule() {
		createSemesterTracker();
		ArrayList<Course> courseList = new ArrayList<Course>();
		ArrayList<Integer> semesterMap = new ArrayList<Integer>();
		courseList = coursesList;
		semesterMap = semesterTracker;

		for (int i = 0; i < 30; i++) {
			
			//remove a random semester
			int randomKey = semesterMap.remove((int) (Math.random() * (semesterMap.size())));
			//remove a random course
			Course randomClass = courseList.remove((int) (Math.random() * (courseList.size())));
			
			//remove a random class name
			int randomClassName = randomClass.getIntClassName();
			
			//set up an array of the days available in Fall for the course removed from the courseList
			daysFall = randomClass.getFallSemester().split("(?!^)");
			for (int j = 0; j < daysFall.length; j++) {
				randomDaysFall.add(daysFall[j]);
			}
			
			//removes a random day from the days available in Fall for the course removed from the courseList
			String fall = randomDaysFall.remove((int) (Math.random() * (randomDaysFall.size())));
			
			//set up an array of the days available in Spring for the course removed from the courseList
			daysSpring = randomClass.getSpringSemester().split("(?!^)");
			for (int k = 0; k < daysSpring.length; k++) {
				randomDaysSpring.add(daysSpring[k]);
			}

			//removes a random day from the days available in Spring for the course removed from the courseList
			String spring = randomDaysSpring.remove((int) (Math.random() * (randomDaysSpring.size())));

			//set up an array of the days available in Summer for the course removed from the courseList
			daysSummer = randomClass.getSummerSemester().split("(?!^)");
			for (int l = 0; l < daysSummer.length; l++) {
				randomDaysSummer.add(daysSummer[l]);
			}

			//removes a random day from the days available in Summer for the course removed from the courseList
			String summer = randomDaysSummer.remove((int) (Math.random() * (randomDaysSummer.size())));
			
			//set the correct semester to a "-" if it is not the correct day for the semester
			if (randomKey == 1 || randomKey == 4 || randomKey == 7 || randomKey == 10) {
				spring = "-";
				summer = "-";
			}

			else if (randomKey == 2 || randomKey == 5 || randomKey == 8 || randomKey == 11) {
				fall = "-";
				summer = "-";
			} else {
				fall = "-";
				spring = "-";
			}
			
			//create random course object
			rC = new randomCourse(randomClassName, randomKey, fall, spring, summer, 0);
			
			//add random course object to an arrayList
			randomCoursesList.add(rC);
			
			//clear the ArrayLists holding the days available for Fall, Spring, and Summer semesters
			randomDaysFall.clear();
			randomDaysSpring.clear();
			randomDaysSummer.clear();

		}

	}
	/**
	 * Calculates the constraints for each class in the random course list given to it if the constraint is less than
	 * 
	 * first loop(goes for the size of the random course list) - grab a randomCourse(not randomly grabbed) from the random course list
	 * second loop(goes for the size of the constraints list) - if the constraint equals less then and if the class on the left side of
	 * the constraint is equal to the randomCourse grabbed in the first loop, grab the class on the other side of the constraint. 
	 * third loop(goes for the size of the random course list) - if the class name on the right side of the constraint is equal to a class name
	 * in the random course list, grab the randomCourse course object. if the semester of that course is less than or equal to the class grabbed
	 * in the first loop, set a conflict for the randomCourse grabbed in the first loop and a conflict for the randomCourse associated with
	 * the class on the right side of the constraint. 
	 * @param inList - randomCourseList
	 */
	public void calculateConstraints(ArrayList<randomCourse> inList) {
		ArrayList<Constraints> constraints = co.getConstraintsList();
		for (int i = 0; i < 30; i++) {
			rCc = inList.get(i);
			for (int j = 0; j < constraints.size(); j++) {
				if (constraints.get(j).getConstraint().equals("<")) {
					if (constraints.get(j).getClass1() == rCc.getCourseName()) {
						classTwo = constraints.get(j).getClass2();
						for (int k = 0; k < 30; k++) {
							if (classTwo == inList.get(k).getCourseName()) {
								randomCourse course = inList.get(k);
								int semester = course.getSemester();
								if (semester <= rCc.getSemester()) {
									rCc.setConflicts(rCc.getConflicts() + 1);
									course.setConflicts(course.getConflicts() + 1);
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Calculates the constraints for each class in the random course list given to it if the constraint is less or equal to
	 * 
	 * first loop(goes for the size of the random course list) - grab a randomCourse(not randomly grabbed) from the random course list
	 * second loop(goes for the size of the constraints list) - if the constraint equals less then and if the class on the left side of
	 * the constraint is equal to the randomCourse grabbed in the first loop, grab the class on the other side of the constraint. 
	 * third loop(goes for the size of the random course list) - if the class name on the right side of the constraint is equal to a class name
	 * in the random course list, grab the randomCourse course object. if the semester of that course is less than the class grabbed
	 * in the first loop, set a conflict for the randomCourse grabbed in the first loop and a conflict for the randomCourse associated with
	 * the class on the right side of the constraint. If their semesters are equal, add zero conflicts.
	 * @param inList - randomCourseList
	 */
	public void calculateConstraintsCorequisite(ArrayList<randomCourse> inList) {
		ArrayList<Constraints> constraints = co.getConstraintsList();
		for (int i = 0; i < 30; i++) {
			rCc2 = inList.get(i);
			for (int j = 0; j < constraints.size(); j++) {
				if (constraints.get(j).getConstraint().equals("<=")) {
					if (constraints.get(j).getClass1() == rCc2.getCourseName()) {
						classTwo = constraints.get(j).getClass2();
						for (int k = 0; k < 30; k++) {
							if (classTwo == inList.get(k).getCourseName()) {
								randomCourse course = inList.get(k);
								int semester = course.getSemester();
								if (semester < rCc2.getSemester()) {
									rCc2.setConflicts(rCc2.getConflicts() + 1);
									course.setConflicts(course.getConflicts() + 1);
								}
								if (semester == rCc2.getSemester()) {
									rCc.setConflicts(rCc2.getConflicts() + 0);
									course.setConflicts(course.getConflicts() + 0);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * calculates the constraints for the case of a class being on the same day during the same semester(manually)
	 */
	private void calculateConstraintsSameDay() {

		randomCourse course0 = randomCoursesList.get(0);
		randomCourse course1 = randomCoursesList.get(1);
		randomCourse course2 = randomCoursesList.get(2);

		if (course1.getFall().equals(course0.getFall()) && !course1.getFall().equals("O")
				&& !course1.getFall().equals("O")) {
			course1.setConflicts(course1.getConflicts() + 1);
			course0.setConflicts(course0.getConflicts() + 1);
		}
		if (course1.getFall().equals(course2.getFall()) && !course1.getFall().equals("O")
				&& !course2.getFall().equals("O")) {
			course1.setConflicts(course1.getConflicts() + 1);
			course2.setConflicts(course2.getConflicts() + 1);
		}
		if (course0.getFall().equals(course2.getFall()) && !course0.getFall().equals("O")
				&& !course2.getFall().equals("O")) {
			course0.setConflicts(course0.getConflicts() + 1);
			course2.setConflicts(course2.getConflicts() + 1);
		}

		randomCourse course3 = randomCoursesList.get(3);
		randomCourse course4 = randomCoursesList.get(4);
		randomCourse course5 = randomCoursesList.get(5);

		if (course4.getSpring().equals(course3.getSpring()) && !course4.getSpring().equals("O")
				&& !course3.getSpring().equals("O")) {
			course4.setConflicts(course4.getConflicts() + 1);
			course3.setConflicts(course3.getConflicts() + 1);
		}
		if (course4.getSpring().equals(course5.getSpring()) && !course4.getSpring().equals("O")
				&& !course5.getSpring().equals("O")) {
			course4.setConflicts(course4.getConflicts() + 1);
			course5.setConflicts(course5.getConflicts() + 1);
		}
		if (course3.getSpring().equals(course5.getSpring()) && !course3.getSpring().equals("O")
				&& !course5.getSpring().equals("O")) {
			course3.setConflicts(course3.getConflicts() + 1);
			course5.setConflicts(course5.getConflicts() + 1);
		}

		randomCourse course6 = randomCoursesList.get(6);
		randomCourse course7 = randomCoursesList.get(7);

		if ((course6.getSummer().equals(course7.getSummer()) || course7.getSummer().equals(course6.getSummer()))
				&& !course6.getSummer().equals("O") && !course7.getSummer().equals("O")) {
			course6.setConflicts(course6.getConflicts() + 1);
			course7.setConflicts(course7.getConflicts() + 1);
		}

		randomCourse course8 = randomCoursesList.get(8);
		randomCourse course9 = randomCoursesList.get(9);
		randomCourse course10 = randomCoursesList.get(10);

		if (course9.getFall().equals(course8.getFall()) && !course9.getFall().equals("O")
				&& !course8.getFall().equals("O")) {
			course9.setConflicts(course9.getConflicts() + 1);
			course8.setConflicts(course8.getConflicts() + 1);
		}
		if (course9.getFall().equals(course10.getFall()) && !course9.getFall().equals("O")
				&& !course10.getFall().equals("O")) {
			course9.setConflicts(course9.getConflicts() + 1);
			course10.setConflicts(course10.getConflicts() + 1);
		}
		if (course8.getFall().equals(course10.getFall()) && !course8.getFall().equals("O")
				&& !course10.getFall().equals("O")) {
			course8.setConflicts(course8.getConflicts() + 1);
			course10.setConflicts(course10.getConflicts() + 1);
		}

		randomCourse course11 = randomCoursesList.get(11);
		randomCourse course12 = randomCoursesList.get(12);
		randomCourse course13 = randomCoursesList.get(13);

		if (course12.getSpring().equals(course11.getSpring()) && !course12.getSpring().equals("O")
				&& !course11.getSpring().equals("O")) {
			course12.setConflicts(course12.getConflicts() + 1);
			course11.setConflicts(course11.getConflicts() + 1);
		}
		if (course12.getSpring().equals(course13.getSpring()) && !course12.getSpring().equals("O")
				&& !course13.getSpring().equals("O")) {
			course12.setConflicts(course12.getConflicts() + 1);
			course13.setConflicts(course13.getConflicts() + 1);
		}
		if (course11.getSpring().equals(course13.getSpring()) && !course11.getSpring().equals("O")
				&& !course13.getSpring().equals("O")) {
			course11.setConflicts(course11.getConflicts() + 1);
			course13.setConflicts(course13.getConflicts() + 1);
		}

		randomCourse course14 = randomCoursesList.get(14);
		randomCourse course15 = randomCoursesList.get(15);

		if ((course14.getSummer().equals(course15.getSummer()) || course14.getSummer().equals(course15.getSummer())
				&& !course14.getSummer().equals("O") && !course15.getSummer().equals("O"))) {
			course14.setConflicts(course14.getConflicts() + 1);
			course15.setConflicts(course15.getConflicts() + 1);
		}

		randomCourse course16 = randomCoursesList.get(16);
		randomCourse course17 = randomCoursesList.get(17);
		randomCourse course18 = randomCoursesList.get(18);

		if (course17.getFall().equals(course16.getFall()) && !course17.getFall().equals("O")
				&& !course16.getFall().equals("O")) {
			course17.setConflicts(course17.getConflicts() + 1);
			course16.setConflicts(course16.getConflicts() + 1);
		}
		if (course17.getFall().equals(course18.getFall()) && !course17.getFall().equals("O")
				&& !course18.getFall().equals("O")) {
			course17.setConflicts(course17.getConflicts() + 1);
			course18.setConflicts(course18.getConflicts() + 1);
		}
		if (course16.getFall().equals(course18.getFall()) && !course16.getFall().equals("O")
				&& !course18.getFall().equals("O")) {
			course16.setConflicts(course16.getConflicts() + 1);
			course18.setConflicts(course18.getConflicts() + 1);
		}

		randomCourse course19 = randomCoursesList.get(19);
		randomCourse course20 = randomCoursesList.get(20);
		randomCourse course21 = randomCoursesList.get(21);

		if (course20.getSpring().equals(course19.getSpring()) && !course20.getSpring().equals("O")
				&& !course19.getSpring().equals("O")) {
			course20.setConflicts(course20.getConflicts() + 1);
			course19.setConflicts(course19.getConflicts() + 1);
		}
		if (course20.getSpring().equals(course21.getSpring()) && !course20.getSpring().equals("O")
				&& !course21.getSpring().equals("O")) {
			course20.setConflicts(course20.getConflicts() + 1);
			course21.setConflicts(course21.getConflicts() + 1);
		}
		if (course19.getSpring().equals(course21.getSpring()) && !course19.getSpring().equals("O")
				&& !course21.getSpring().equals("O")) {
			course19.setConflicts(course19.getConflicts() + 1);
			course21.setConflicts(course21.getConflicts() + 1);
		}

		randomCourse course22 = randomCoursesList.get(22);
		randomCourse course23 = randomCoursesList.get(23);

		if ((course22.getSummer().equals(course23.getSummer()) || course23.getSummer().equals(course22.getSummer()))
				&& !course22.getSummer().equals("O") && !course23.getSummer().equals("O")) {
			course22.setConflicts(course22.getConflicts() + 1);
			course23.setConflicts(course23.getConflicts() + 1);
		}

		randomCourse course24 = randomCoursesList.get(24);
		randomCourse course25 = randomCoursesList.get(25);
		randomCourse course26 = randomCoursesList.get(26);

		if (course25.getFall().equals(course24.getFall()) && !course25.getFall().equals("O")
				&& !course24.getFall().equals("O")) {
			course25.setConflicts(course25.getConflicts() + 1);
			course24.setConflicts(course24.getConflicts() + 1);
		}
		if (course25.getFall().equals(course26.getFall()) && !course25.getFall().equals("O")
				&& !course26.getFall().equals("O")) {
			course25.setConflicts(course25.getConflicts() + 1);
			course26.setConflicts(course26.getConflicts() + 1);
		}
		if (course24.getFall().equals(course26.getFall()) && !course24.getFall().equals("O")
				&& !course26.getFall().equals("O")) {
			course24.setConflicts(course24.getConflicts() + 1);
			course26.setConflicts(course26.getConflicts() + 1);
		}

		randomCourse course27 = randomCoursesList.get(27);
		randomCourse course28 = randomCoursesList.get(28);
		randomCourse course29 = randomCoursesList.get(29);

		if (course28.getSpring().equals(course27.getSpring()) && !course28.getSpring().equals("O")
				&& !course27.getSpring().equals("O")) {
			course28.setConflicts(course28.getConflicts() + 1);
			course27.setConflicts(course27.getConflicts() + 1);
		}
		if (course28.getSpring().equals(course29.getSpring()) && !course28.getSpring().equals("O")
				&& !course29.getSpring().equals("O")) {
			course28.setConflicts(course28.getConflicts() + 1);
			course29.setConflicts(course29.getConflicts() + 1);
		}
		if (course27.getSpring().equals(course29.getSpring()) && !course27.getSpring().equals("O")
				&& !course29.getSpring().equals("O")) {
			course27.setConflicts(course27.getConflicts() + 1);
			course29.setConflicts(course29.getConflicts() + 1);
		}

	}
	/**
	 * Performs an Iterative Best First Local Search on the randomCoursesList after calculating conflicts
	 */
	public void localSearch() {

		int count = 0;
		//calculate constraints if the constraint is less than
		calculateConstraints(randomCoursesList);
		
		//while there are still conflicts, keep minimizing
		while ((getTotalConflicts(randomCoursesList) != 0)) {
			//print result after 1000 iterations
			if(count == 1000){
				System.out.println("RANDOM SCHEDULE1");
				Collections.sort(randomCoursesList);
				for (int a = 0; a < randomCoursesList.size(); a++) {
					System.out.println(randomCoursesList.get(a).toString());
				}
			}
			
			//minimize conflicts on the course with the most conflicts
			randomCoursesList = minimizeConflicts(randomCoursesList, getMaxConflict(randomCoursesList));
			
			//keep track of number of iterations
	        count++;
	        }

		
		
		if (getTotalConflicts(randomCoursesList) == 0) {
			//calculate constraints again to make sure they are zero
			calculateConstraints(randomCoursesList);
			System.out.println("FINAL COURSE SCHEDULE WITH ZERO CONFLICTS");
			
			//sort the list according to semester value
			Collections.sort(randomCoursesList);

			//print the course schedule with zero conflicts
			for (int a = 0; a < randomCoursesList.size(); a++) {
				System.out.println(randomCoursesList.get(a).toString());
			}
			//print the number of iterations it took
			System.out.println(count);
		}

	}

	/**
	 * Minimizes conflicts by grabbing a random semester value and assigning it to a randomCousrse in the random course list acting as the 
	 * course schedule. 
	 * first loop(goes for the size of the conflictCourseList list) - if the conflicts for the course with the most conflicts isnt zero ad its
	 * in the conflictCourseList, set the semester of the course with the most conflicts to a randomly grabbed semester.
	 * second loop(goes for the size of the conflictCourseList) - set the conflicts for all the courses to zero
	 * after the second loop is done the conflicts are calculated again, but this time with a course that might have a different semester, then
	 * if the total conflicts didnt get any better, try grabbing a different random semester. 
	 * 
	 * @param courseList - randomCourseList
	 * @param tempMaxCourse - randomCourse with most conflicts
	 * @return the randomCourseList with conflicts minimized
	 */
	public ArrayList<randomCourse> minimizeConflicts(ArrayList<randomCourse> courseList, randomCourse tempMaxCourse) {
		ArrayList<Constraints> constraints = co.getConstraintsList();
		ArrayList<randomCourse> conflictCourseList = (ArrayList<randomCourse>) courseList.clone();
		
		maxCourse = tempMaxCourse;
		int totalConflicts = getTotalConflicts(conflictCourseList);
		createSemesterTracker();
		for(int i = 0; i < conflictCourseList.size(); i++){
			if(maxCourse.getConflicts() != 0 && maxCourse.getCourseName() == conflictCourseList.get(i).getCourseName()){
				conflictCourseList.get(i).setSemester(semesterTracker.remove((int) (Math.random() * (semesterTracker.size()))));
				for(int a = 0; a < conflictCourseList.size(); a++){
					conflictCourseList.get(a).setConflicts(0);
				}
				calculateConstraints(conflictCourseList);
				int afterMin = getTotalConflicts(conflictCourseList);
				if(afterMin >= totalConflicts){
					conflictCourseList.get(i).setSemester(semesterTracker.remove((int) (Math.random() * (semesterTracker.size()))));
				}
			}
		}
			//set the conflicts to zero
			for(int a = 0; a < conflictCourseList.size(); a++){
				conflictCourseList.get(a).setConflicts(0);
			}

			//calculate the constraints on the list with a newly assigned semester
			calculateConstraints(conflictCourseList);
			
			int afterMinimization = getTotalConflicts(conflictCourseList);
			
			//if the total conflicts didnt get any better, grab a different random semester
			if(afterMinimization >= totalConflicts){
				for(int s = 0; s < conflictCourseList.size(); s++){
					if(maxCourse.getCourseName() == conflictCourseList.get(s).getCourseName()){
						conflictCourseList.get(s).setSemester(semesterTracker.remove((int) (Math.random() * (semesterTracker.size()))));
						for(int a = 0; a < conflictCourseList.size(); a++){
							conflictCourseList.get(a).setConflicts(0);
						}
					}
				}
			}

			calculateConstraints(conflictCourseList);

		return conflictCourseList;
	}

	/**
	 * 
	 * @param inList - randomCourseList
	 * @return the course with the max conflicts
	 */
	public randomCourse getMaxConflict(ArrayList<randomCourse> inList) {

		int maxConflicts = 0;
		randomCourse maxConflictsCourse = null;

		for (int i = 0; i < inList.size(); i++) {

			if (inList.get(i).getConflicts() > maxConflicts) {
				maxConflicts = inList.get(i).getConflicts();
				maxConflictsCourse = inList.get(i);
				if (i == 29) {
					return maxConflictsCourse;
				}
			}

		}

		return maxConflictsCourse;

	}

	/**
	 * 
	 * @param inList - randomCourseList
	 * @return the total conflicts for the ranodmCourses in the randomCourseList representing the course schedule
	 */
	public int getTotalConflicts(ArrayList<randomCourse> inList) {

		int totalConflicts = 0;
		// Print new Class
		for (int i = 0; i < inList.size(); i++) {
			if (inList.get(i) != null) {
				totalConflicts += inList.get(i).getConflicts();
			}
		}
		return totalConflicts;
	}

	/**
	 * 
	 * Second Constructor for setting up a course object
	 */
	public Course(int intClassName, String fallSemester, String springSemester, String summerSemester) {
		super();
		this.intClassName = intClassName;
		this.fallSemester = fallSemester;
		this.springSemester = springSemester;
		this.summerSemester = summerSemester;
	}
	
	//getters and setters
	public String getFallSemester() {
		return fallSemester;
	}

	public void setFallSemester(String fallSemester) {
		this.fallSemester = fallSemester;
	}

	public String getSpringSemester() {
		return springSemester;
	}

	public void setSpringSemester(String springSemester) {
		this.springSemester = springSemester;
	}

	public String getSummerSemester() {
		return summerSemester;
	}

	public void setSummerSemester(String summerSemester) {
		this.summerSemester = summerSemester;
	}

	public int getIntClassName() {
		return intClassName;
	}

	public void setIntClassName(int intClassName) {
		this.intClassName = intClassName;
	}

	@Override
	public String toString() {

		return "Course [courseName=" + courseName + ", fallSemester=" + fallSemester + ", springSemester="
				+ springSemester + ", summerSemester=" + summerSemester + "]";
	}

}
