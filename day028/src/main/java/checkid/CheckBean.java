package checkid;

import java.util.ArrayList;

public class CheckBean {
   private String mid;
   private String msg;
   
   public void check() {
      if(mid==null) {
         msg="";
         return;
      }
      
      ArrayList<String> datas=new ArrayList<String>();
      datas.add("teemo");
      
      boolean flag=true;
      for(String data:datas) {
         if(mid.equals(data)) {
            flag=false;
            break;
         }
      }
      
      if(flag) {
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