package com.demo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Arrays;

public class BaseClass {

   protected WebDriver driver;

    @BeforeTest
    public void beforeMethod() {

        getChromeDriver();
    }

    private void getChromeDriver() {

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-gpu");
        option.addArguments("--disable-dev-shm-usage");
        option.addArguments("--disable-infobars");
        option.addArguments("--no-sandbox");
        option.addArguments("--disable-default-apps");
        option.addArguments("--allow-insecure-localhost");
        option.setAcceptInsecureCerts(true);
        option.setAcceptInsecureCerts(true);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors," +
                "--web-security=false,--ssl-protocol=any,--ignore-ssl-errors=true"));

        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(option);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}
