package model.DTO;

import java.util.ArrayList;
import java.util.Date;

public class OrderDTO {
    private int oId;	// 주문번호(PK)
    private String mId;	// 사용자id(FK)
    private Date oDate; // 주문일시
    
    private ArrayList<OrderContentDTO> ocdatas;	// 데이터 출력용 ArrayList


    public ArrayList<OrderContentDTO> getOcdatas() {
        return ocdatas;
    }
    public void setOcdatas(ArrayList<OrderContentDTO> ocdatas) {
        this.ocdatas = ocdatas;
    }
    public int getoId() {
        return oId;
    }
    public void setoId(int oId) {
        this.oId = oId;
    }
    public String getmId() {
        return mId;
    }
    public void setmId(String mId) {
        this.mId = mId;
    }
    public Date getoDate() {
        return oDate;
    }
    public void setoDate(Date oDate) {
        this.oDate = oDate;
    }
    @Override
    public String toString() {
        return "OrderDTO [oId=" + oId + ", mId=" + mId + ", oDate=" + oDate + "]";
    }

}