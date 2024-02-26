package check;

public class CheckIdBean {
	private String id;
	private String ckId;

	public void setCkId(String ckId) {
		this.ckId = ckId;
	}

	public String getCkId() {
		return ckId;
	}
// db가 연동된다면 selectOne으로 가져올 예정이고
//	현재는 teemo라는 값을 수동으로 적용시킬 예정이므로 필요없다
//	public void setCkId(String ckId) {
//		this.ckId = ckId;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean checkId() {
		if (id.equals(ckId)) {
			return true;
		}
		return false;
	}

}
