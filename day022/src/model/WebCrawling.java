package model;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import model.DTO.ProductDTO;

public class WebCrawling {
	public static ArrayList<ProductDTO> webCrawling() { // 웹크롤링하여 제품추가
				
				String url = "https://www.bang-olufsen.com/ko/kr/headphones"; // B&O제품차트 url -> 제품차트에 있는 영화들의 URL주소를 얻어오기위해 사용됨
				String url2 = "https://www.bang-olufsen.com"; // 제품상세페이지로 넘어가기위해 사용됨 -> url2+제품상세주소
				Document doc = null; // B&O제품차트 주소를 연결하는데 사용됨
				Document doc2 = null; // 제품상세페이지의 모든정보를 저장할 예정
				
				ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>(); // 반환할 제품리스트 객체 선언
				try {
					doc = Jsoup.connect(url).get(); // 
					Elements productUrl = doc.select("div.product-card-wrap > div > a"); // 제품차트 속 각각의 제품상세페이지 url
					
						
					for (int i = 0; i < productUrl.size(); i++) { // 무비차트에 있는 영화개수만큼 반복문 수행
							doc2 = Jsoup.connect(url2+productUrl.get(i).select("a").attr("href")).get(); // 각각의 영화상세페이지 url 연결
							
							String productName = null; // 제품명
							String price_str=null; // 가격pro
							int price=0; // 가격
							String info=null; // 제품설명
							String productImgUrl = null; // 제품 이미지 URL

							// 가격 String -> Integer 가공
							price_str = doc2.select("span.product-price").text();
							price_str = price_str.substring(1).replace(",", "");
							if(price_str.contains(" 기존 가격")) {
								price_str=price_str.replace(" 기존 가격", "").trim();
							}
							
							productName = doc2.select("span.h5").text();
							info = doc2.select("div>p").get(1).text();
							price = Integer.parseInt(price_str);
							productImgUrl = doc2.select("div.slick-list img").attr("src");
							
							ProductDTO pDTO = new ProductDTO();
							pDTO.setpName(productName);
							pDTO.setpPrice(price);
							pDTO.setpInfo(info);
							pDTO.setpImgUrl(productImgUrl);
							datas.add(pDTO);
							
							System.out.println(productName);
							System.out.println(price);
							System.out.println(info);
							System.out.println(productImgUrl);
							
					} // for(i++)
				} catch (IOException e) {
					e.printStackTrace();
				}

				return datas;
		
		
	}
}
