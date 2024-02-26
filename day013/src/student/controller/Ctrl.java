package student.controller;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import student.model.StudentDAO;
import student.model.StudentDTO;
import student.view.View;

public class Ctrl {
	private View view;
	private StudentDAO model;

//	private StudentDTO studentDTO; 스프링에서 바인딩 할때 메소드마다 다시 매핑해주기 때문에 습관을 들이기 위함
	public Ctrl() {
		this.view = new View();
		this.model = new StudentDAO();
	}

	public void startApp() {
		int PK = 1003;
		while (true) {
			view.printMenu();
			int action = view.inputAction();
			if (action == 0) {
				break;
			} else if (action == 1) {
				String name = view.inputName();
				int score = view.inputScore();
				StudentDTO studentDTO = new StudentDTO(PK, score, name);
				studentDTO = new StudentDTO(PK++, score, name);
				boolean flag = model.insert(studentDTO);
				if (flag) {
					view.printTrue();
				} else {
					view.printFalse();
				}
			} else if (action == 2) {
				StudentDTO studentDTO = new StudentDTO(0, 0, null);
				studentDTO.setSearchCondition("전체검색");
				ArrayList<StudentDTO> datas = model.selectAll(studentDTO);
				view.printDatas(datas);
			} else if (action == 3) {
				int num = view.inputNum();
				StudentDTO studentDTO = new StudentDTO(num, 0, null);
				StudentDTO data = model.selectOne(studentDTO);
//				데이터가 많아질 경우 상당히 메모리적인 측면으로 비효율적이 되어버린다
//				for(StudentDTO s : model.selectAll(studentDTO)) {
//					if(s.getNum()==num) {
//						view.printData(s);
//					}
//				}
				if (data == null) {
					view.printFalse();
					continue;
				}
				view.printData(data);
			} else if (action == 4) {
				view.printUpdateMenu();
				action = view.inputAction();
				boolean flag;
				int num = view.inputNum();
				if (action == 1) {
					int score = view.inputScore();
					StudentDTO studentDTO = new StudentDTO(num, score, null);
					flag = model.update(studentDTO);
				} else {
					String name = view.inputName();
					StudentDTO studentDTO = new StudentDTO(num, 0, name);
					flag = model.update(studentDTO);
				}
				if (flag) {
					view.printTrue();
				} else {
					view.printFalse();
				}
			} else if (action == 5) {
				int num = view.inputNum();
				StudentDTO studentDTO = new StudentDTO(num, 0, null);
				boolean flag = model.delete(studentDTO);
				if (flag) {
					view.printTrue();
				} else {
					view.printFalse();
				}
			} else if (action == 6) {
				StudentDTO studentDTO = new StudentDTO(0, 0, null);
				studentDTO.setSearchCondition("점수검색");
				ArrayList<StudentDTO> datas = model.selectAll(studentDTO);
				view.printDatas(datas);
			} else if (action == 7) {
				StudentDTO studentDTO = new StudentDTO(0, 0, null);
				studentDTO.setSearchName(view.inputName());
				studentDTO.setSearchCondition("이름검색");
				ArrayList<StudentDTO> datas = model.selectAll(studentDTO);
				view.printDatas(datas);
			}
		}
	}
}