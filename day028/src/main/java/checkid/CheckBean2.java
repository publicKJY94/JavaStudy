package checkid;

public class CheckBean2 {
	private String mid;
	private String msg;
	
	public void check() {
		if(mid==null) {
			msg="";
			return;
		}
		
		MemberDAO mDAO=new MemberDAO();
		
		MemberDTO mDTO=new MemberDTO();
		mDTO.setMid(mid);
		mDTO=mDAO.selectOne(mDTO);
				
		if(mDTO==null) {
			msg="<font color='green'>해당 아이디는 사용가능합니다! :D</font>";
		}
		else {
			msg="<font color='red'>중복이라 사용불가능한 아이디입니다...</font>";
		}
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
