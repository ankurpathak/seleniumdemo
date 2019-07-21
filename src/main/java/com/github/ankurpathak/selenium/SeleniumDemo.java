package com.github.ankurpathak.selenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class SeleniumDemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", Paths.get("/home/ankur", "chromedriver").toString());


        String BASE_URL = "https://www.bankbazaar.com";
        String path = "ifsc-code";
        Document bankDoc = Jsoup.connect(String.format("%s/%s.html",BASE_URL,path)).get();
        Element bankSelect = bankDoc.getElementById("selectDropdownBank");
        List<Element> bankOptions = bankSelect.getElementsByTag("option");
        System.out.println("----------------------------------Bank------------------------------");
        for (Element bankOption: bankOptions) {
            System.out.println(bankOption.val() + bankOption.text());
            if(Objects.equals(bankOption.text(), "Select Bank"))
                continue;
            String statePath = bankOption.text().replace("& ", "").replace(" ", "-").toLowerCase();
            String stateLink = String.format("%s/%s/%s.html", BASE_URL, path, statePath);
            Document stateDoc= Jsoup.connect(stateLink).get();
            Element stateSelect = stateDoc.getElementById("selectDropdownState");
            List<Element> stateOptions = stateSelect.getElementsByTag("option");
            System.out.println("----------------------------------State------------------------------");
            for (Element stateOption: stateOptions) {
                System.out.println(stateOption.val() + stateOption.text());
                if(Objects.equals(stateOption.text(), "Select State"))
                    continue;
                String districtPath = stateOption.text().replace("& ", "").replace(" ", "-").toLowerCase();
                String districtLink = String.format("%s/%s/%s/%s.html", BASE_URL, path, statePath, districtPath);
                Document districtDoc= Jsoup.connect(districtLink).get();
                Element districtSelect = districtDoc.getElementById("selectDropdownDistrict");
                List<Element> districtOptions = districtSelect.getElementsByTag("option");
                System.out.println("----------------------------------District------------------------------");
                for (Element districtOption: districtOptions) {
                    System.out.println(districtOption.val() + districtOption.text());
                    String branchPath = districtOption.text().replace("& ", "").replace(" ", "-").toLowerCase();
                };
                System.out.println("----------------------------------District------------------------------");

            };
            System.out.println("----------------------------------State------------------------------");

        };
        System.out.println("----------------------------------Bank------------------------------");





    }
}
