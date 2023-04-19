package com.example.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingApplication {

    public static void main(String[] args) {
        try {
            String URL = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=조일+알미늄";
            Document doc = Jsoup.connect(URL).get();

//            Elements elements = doc.select(".cluster_text_headline");
            //헤드라인 제목 추출
//            for (Element element : elements) {
//                System.out.println(element.text());
//            }

            //헤드라인 a 태그 추출
//            elements = doc.select(".cluster_text a");

//            for (Element element : elements) {
//                System.out.println(element.getElementsByAttribute("href").attr("href"));
//            }

            Elements elements = doc.select(".spt_con strong");
            int money = 751 * Integer.parseInt(elements.get(0).text().replace(",", ""));
            System.out.println("현재 보유중인 자산 : " + money + "원");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
