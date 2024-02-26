package student.model;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentDAO {
	private ArrayList<StudentDTO> datas; // DB의 역할

	public StudentDAO() {
		this.datas = new ArrayList<StudentDTO>();
		this.datas.add(new StudentDTO(1001, 50, "티모"));
		this.datas.add(new StudentDTO(1002, 89, "아리"));
	}

	// R : 데이터 반환이 목표
	public ArrayList<StudentDTO> selectAll(StudentDTO studentDTO) {
		ArrayList<StudentDTO> datas = new ArrayList<StudentDTO>(); // 반환할 목록
		if (studentDTO.getSearchCondition().equals("점수검색")) {
			for (int i = 0; i < this.datas.size(); i++) {
				if (this.datas.get(i).getScore() >= studentDTO.getScore()) {
					datas.add(this.datas.get(i));
				}
			}
			return datas;
		} else if (studentDTO.getSearchCondition().equals("이름검색")) {
			for (int i = 0; i < this.datas.size(); i++) {
				if (this.datas.get(i).getName().contains(studentDTO.getSearchName())) {
					datas.add(this.datas.get(i));
				}
			}
			return datas;
		} else {
			return this.datas; // 전체 목록==DB==학생부 그 자체
		}

	}

	public StudentDTO selectOne(StudentDTO studentDTO) {
		boolean flag = false;
		int i;
		for (i = 0; i < datas.size(); i++) {
			if (datas.get(i).getNum() == studentDTO.getNum()) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return null;
		}
		return datas.get(i);
	}

	public boolean insert(StudentDTO studentDTO) {
		this.datas.add(new StudentDTO(studentDTO.getNum(), studentDTO.getScore(), studentDTO.getName()));
		return true;
	}

	public boolean update(StudentDTO studentDTO) {
		boolean flag = false;
		int i;
		for (i = 0; i < datas.size(); i++) {
			if (datas.get(i).getNum() == studentDTO.getNum()) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return false;
		}

		if (studentDTO.getName() != null) { // name 인자에 값을 부여했다면, 변경을 하고싶었던것!
			datas.get(i).setName(studentDTO.getName());
			System.out.println("[로그] 이름변경");
		} else {
			datas.get(i).setScore(studentDTO.getScore());
			System.out.println("[로그] 점수변경");
		}

		return true;
	}

	public boolean delete(StudentDTO studentDTO) {
		boolean flag = false;
		int i;
		for (i = 0; i < datas.size(); i++) {
			if (datas.get(i).getNum() == studentDTO.getNum()) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return false;
		}
		datas.remove(i);
		return true;
	}
}