package com.example.crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlingApplication {
    final static int seedMoney = 3037560;

    public static void main(String[] args) {
        try {
            long beforeTime = System.currentTimeMillis();
            while (true) {
                System.out.println("\n-------------------------------------\n");

                String URL = "https://finance.naver.com/item/main.nhn?code=093370";
                Document doc = Jsoup.connect(URL).get();
                Elements elements = doc.select(".no_today span");
                long afterTime = System.currentTimeMillis();
                long secDiffTime = (afterTime - beforeTime) / 1000;
                float percent;
                int money = 204 * Integer.parseInt(elements.get(0).text().replace(",", ""));

                System.out.println("현재 시세 : " + elements.get(0).text().replace(",", "") + "원");
                System.out.println("현재 보유중인 자산 : " + money + "원");

                if (money > seedMoney) {
                    System.out.println(money - seedMoney + "원 흑자");
                    percent = Float.parseFloat(String.valueOf(money - seedMoney)) / seedMoney;
                    System.out.println("+" + percent * 100 + "%");
                } else {
                    System.out.println(seedMoney - money + "원 적자");
                    percent = Float.parseFloat(String.valueOf(seedMoney - money)) / seedMoney;
                    System.out.println("-" + percent * 100 + "%");
                }
                long minute = secDiffTime / 60;
                long second = secDiffTime % 60;
                if (secDiffTime > 60) {
                    System.out.println("실행시간 : " + minute + "분 " + second + "초");
                } else {
                    System.out.println("실행시간 : " + secDiffTime + "초");
                }
                System.out.println("\n-------------------------------------\n");
                Thread.sleep(10000);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
