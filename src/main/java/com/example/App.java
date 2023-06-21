package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.cert.TrustAnchor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;

public class App {
    public static void main(String[] args) {
        String url = "https://study4.com/tests/2029/ets-toeic-2022-test-1/start/#_=_";
        System.setProperty("webdriver.chrome.driver", "Resources/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Open web
        driver.get("https://toeic-testpro.com/study/test/test-5-62b6cf2fbbc57b27fe110747/");
        // find button
        try {
            Thread.sleep(5000);
            WebElement button = driver.findElement(By.className("game-button-play"));
            button.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Wait for the web to be loaded
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Document doc = requestDoc(url);
        Document doc = Jsoup.parse(driver.getPageSource());
        if (doc != null) {
            Elements elements = doc.select(".game-object-question.tablet.quiz-game-object-question");
            // Elements elements = getelementbyclass("question-text").text()
            System.out.println(elements.size());

            for (int i = 0; i < elements.size(); i++) {
                System.out.println(i);
                System.out.println(elements.get(i).text());
                System.out.println('\n');

            }
            // System.out.println(elements.size());

        } else {
            System.out.println("doc is null");
        }
        // close web driver
        driver.quit();
    }

    private static Document requestDoc(String url) {
        try {
            Connection conn = Jsoup.connect(url);
            Document doc = conn.get();
            if (conn.response().statusCode() == 200) {
                System.out.println("Crawl: ");
                System.out.println(doc.title());
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
