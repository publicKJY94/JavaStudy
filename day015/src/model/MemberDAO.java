package model;

import java.util.ArrayList;

public class MemberDAO {
	private ArrayList<MemberDTO> mdatas;

	public MemberDAO() {
		mdatas = new ArrayList<MemberDTO>();
	}

	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) {
		return null;
	}

	public MemberDTO selectOne(MemberDTO mDTO) {
		if (mDTO.getSearchCondition().equals("회원가입")) {
			for (MemberDTO data : mdatas) {
				if (data.getMid().equals(mDTO.getMid())) {
					return mDTO;
				}
			}
		} else if (mDTO.getSearchCondition().equals("로그인")) {
			int i;
			for (i = 0; i < mdatas.size(); i++) {
				if (mdatas.get(i).getMid().equals(mDTO.getMid()) && mdatas.get(i).getMpw().equals(mDTO.getMpw())) {
					return mdatas.get(i);
				}
			}
		}
		return null;
	}

	public boolean insert(MemberDTO mDTO) {
		if (mDTO != null) {
			mdatas.add(mDTO);
			return true;
		}
		return false;
	}

	public boolean update(MemberDTO mDTO) {
		return false;
	}

	public boolean delete(MemberDTO mDTO) {
		for(int i = 0; i < mdatas.size(); i++) {
			if(mdatas.get(i).getMid().equals(mDTO.getMid())) {
				mdatas.remove(i);
				return true;
			}
		}
		return false;
	}
}
