/**
 * 
 * @author Caleb Crosby
 * 12/5/2017
 * sets up a randomCourse object to be used in the Course class in order to make a random course schedule
 */
public class randomCourse implements Comparable<randomCourse>{

	private int courseName;
	private int semester;
	private String fall;
	private String spring;
	private String summer;
	private int conflicts;
	
	/**
	 * 
	 * Sets up a random course to be added to a randomCourseList in the Course class
	 */
	public randomCourse(int courseName, int semester, String fall, String spring, String summer, int conflicts) {
		super();
		this.courseName = courseName;
		this.semester = semester;
		this.fall = fall;
		this.spring = spring;
		this.summer = summer;
		this.conflicts = conflicts;
	}

	//getters and setters
	public int getCourseName() {
		return courseName;
	}

	public void setCourseName(int courseName) {
		this.courseName = courseName;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getFall() {
		return fall;
	}

	public void setFall(String fall) {
		this.fall = fall;
	}

	public String getSpring() {
		return spring;
	}

	public void setSpring(String spring) {
		this.spring = spring;
	}

	public String getSummer() {
		return summer;
	}

	public void setSummer(String summer) {
		this.summer = summer;
	}
	
	public int getConflicts() {
		return conflicts;
	}

	public void setConflicts(int conflicts) {
		this.conflicts = conflicts;
	}

	public int compareTo(randomCourse d) {
		if (this.semester < d.semester) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return "randomCourse [courseName=" + courseName + ", semester=" + semester + ", fall=" + fall + ", spring="
				+ spring + ", summer=" + summer + ", conflicts=" + conflicts + "]";
	}



	
	
	
	
}
