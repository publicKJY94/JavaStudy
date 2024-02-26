package student.model;
public class StudentDTO {
	private int num, score;
	private String name,searchCondition,searchName;

	public StudentDTO(int num, int score, String name) {
		this.num = num;
		this.score = score;
		this.name = name;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [" + num + "번 , 점수 : " + score + ", 이름 : " + name + "]";
	}
}
