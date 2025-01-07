package WebScrapper.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.*;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtils {
    String username, password; // Change based on your email provider

    public static String getIndeediqaLink() throws IOException {
        //qa link here
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/links.properties");
        prop.load(fis);
        String testingLink = prop.getProperty("indeed");
        String newtestingLink = testingLink.replace("filter", prop.getProperty("qafilter"));
        return newtestingLink;

    }

    //create another method for DS job link

    //method for indeed driver
    public static WebDriver getDriver(String Link) {
        WebDriver driver = new ChromeDriver();
        driver.get(Link);
        driver.manage().window().maximize();
        return driver;
    }
}
