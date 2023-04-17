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
            String URL = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105";
            Document doc = Jsoup.connect(URL).get();

            Elements elements = doc.select(".cluster_text_headline");
            //헤드라인 제목 추출
            for (Element element : elements) {
                System.out.println(element.text());
            }

            //헤드라인 a 태그 추출
            elements = doc.select(".cluster_text a");

            for (Element element : elements) {
                System.out.println(element.getElementsByAttribute("href").attr("href"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
