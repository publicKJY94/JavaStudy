package day022;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// 웹 크롤링
// 스크래핑
// 데이터를 많이 모으는 작업
// 크롤링
// 데이터를 정제하고 원하는 형태로 가공하는 작업
// >데이터를 많이 모아서 원하는 output으로 정제▪가공하는 작업
// >빅데이터에서 활용되는 기술
// : 웹으로부터 데이터를 많이 모아서 가공할 예정
public class Test01 {

	public static void main(String[] args) {
		// 크롤링할 타겟 웹 페이지를 알아야한다
		// "주소"를 알아야 한다
		// "URL"
		final String url = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
		// 말 그대로 해당 url은 페이지로 구성되어 있음
		// 개발자가 페이지를 코드로 작성하면
		// 웹 브라우저라는 sw가 코드를 해석해서 보여주는 것
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get(); // document 객체가 반환됨
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(doc);
		// 우리가 사용할 웹 페이지 정보타입은 java에서 기본제공하지 않는다
		// 웹으로 부터 Document를 지원해 줄 수 있는 jar파일을 추가해야함
		// 내가 확인하고 싶은 영화 제목은
		// <strong class = "title">로 되어있다
		Elements elems = doc.getElementsByClass("title");
		Iterator<Element> itr = elems.iterator();
		
//		ResultSet처럼 1개씩 보여줄 수 없어서
//		1개씩 뵤여주기 위해 itr을 사용함!
		while(itr.hasNext()) {
			String str = itr.next().text();
			System.out.println(str);
		}
		
	}

}
