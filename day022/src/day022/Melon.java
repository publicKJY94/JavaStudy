package day022;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Melon {
	public static ArrayList<MusicDTO> sample() {
		ArrayList<MusicDTO> datas=new ArrayList<MusicDTO>();
		final String url = "https://www.melon.com/chart/index.htm";

		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Elements elems = doc.getElementsByClass("lst50");
		Iterator<Element> itr = elems.iterator();
		while (itr.hasNext()) {
			Element el = itr.next();
			System.out.println(el.getElementsByClass("rank").text());
			System.out.println(el.select("div.rank01").get(0).getElementsByTag("a").text());// 제목명
			System.out.println(el.select("div.rank02").get(0).getElementsByTag("a").get(0).text());// 가수명
			System.out.println(el.select("div.rank03").text());// 앨범명
			MusicDTO mDTO = new MusicDTO();
			mDTO.setRank(Integer.parseInt(el.getElementsByClass("rank").text()));
			mDTO.setTitle(el.select("div.rank01").get(0).getElementsByTag("a").text());
			mDTO.setSinger(el.select("div.rank02").get(0).getElementsByTag("a").get(0).text());
			mDTO.setAlbum(el.select("div.rank03").text());
			datas.add(mDTO);
		}
		return datas;
	}

}
