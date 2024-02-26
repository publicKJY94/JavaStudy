package check;

public class CheckIdBean2 {
	private String id;
	private String ckId;
	private String msg;
	public CheckIdBean2() {
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCkId() {
		return ckId;
	}
// db가 연동된다면 selectOne으로 가져올 예정이고
//	현재는 teemo라는 값을 수동으로 적용시킬 예정이므로 필요없다
	public void setCkId(String ckId) {
		this.ckId = ckId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void checkId() {
		if(id==null) {
			System.out.println("로그1");
			msg = "";
			return;
		}
		if (id.equals(ckId)) {
			msg = "teemo가 이미 있음";
		} else {
			msg = "사용가능한 ID입니다";
		}
	}

}
