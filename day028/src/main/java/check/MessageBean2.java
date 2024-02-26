package check;

public class MessageBean2 {
	private String msg;

	public MessageBean2() {
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String printCkId(boolean result) {
		if(result) {
			
		}
		if (result) {
			msg = "teemo가 이미 있음";
		} else {
			msg = "사용가능한 ID입니다";
		}
		return msg;
	}
}
