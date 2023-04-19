package com.example.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingApplication {
    final static int seedMoney = 3000000;

    public static void main(String[] args) {
        try {
            long beforeTime = System.currentTimeMillis();


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
            while (true) {
                System.out.println("\n-------------------------------------\n");
                String URL = "https://finance.naver.com/item/main.nhn?code=018470";
                Document doc = Jsoup.connect(URL).get();
                Elements elements = doc.select(".no_up span");
                int money = 751 * Integer.parseInt(elements.get(0).text().replace(",", ""));
                System.out.println("현재 시세 : " + elements.get(0).text().replace(",", "") + "원");
                System.out.println("현재 보유중인 자산 : " + money + "원");
                long afterTime = System.currentTimeMillis();
                long secDiffTime = (afterTime - beforeTime) / 1000;
                float percent = 0;
                System.out.println("실행시간(m) : " + secDiffTime);
                if (money > seedMoney) {
                    System.out.println(money - seedMoney + "원 흑자");
                    percent = Float.parseFloat(String.valueOf(money - seedMoney)) / seedMoney;
                    System.out.println("+" + percent * 100 + "%");
                } else {
                    System.out.println(seedMoney - money + "원 적자");
                    percent = Float.parseFloat(String.valueOf(seedMoney - money)) / seedMoney;
                    System.out.println("-" + percent * 100 + "%");
                }
                System.out.println("\n-------------------------------------\n");
                Thread.sleep(10000);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
