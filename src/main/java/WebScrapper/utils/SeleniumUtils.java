package WebScrapper.utils;
import WebScrapper.service.ExcelExport;

import WebScrapper.models.Job;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static WebScrapper.service.EmailService.sendEmail;


public class SeleniumUtils {
    private static String Joblink,jobTitle,companyName;
    static List<Job> jobListdata = new ArrayList<Job>();
    public static void getIndeedJobs() throws IOException {
        String indeedQAJobLink = ConfigUtils.getIndeediqaLink();
        WebDriver driver = ConfigUtils.getDriver(indeedQAJobLink);

        //extracting jobs here
        List<WebElement> joblist = driver.findElements(By.xpath("//*[@id='mosaic-provider-jobcards']/ul/li"));
        System.out.println(joblist.size());
            for (int i =1; i<joblist.size(); i++) {
                try {
                    WebElement jobElement = joblist.get(i);

                    // Try to find the job title element
                    List<WebElement> jobTitleElements = jobElement.findElements(By.className("jobTitle"));
                    if (jobTitleElements.isEmpty()) {
                        System.out.println("Job title not found for index: " + i + " - Skipping...");
                        continue;
                    }
                    WebElement job = jobTitleElements.get(0);
                    // Extract job details
                    String jobLink = job.findElement(By.tagName("a")).getAttribute("href");
                    String jobTitle = job.findElement(By.tagName("span")).getText();
                    String companyName = jobElement.findElement(By.xpath("//*[@data-testid = 'company-name']")).getText();

                    // Print and add to the job list
                    System.out.println("Job Title: " + jobTitle + " - Job Link: " + jobLink + " - Company Name: " + companyName);
                    jobListdata.add(new Job(companyName, jobTitle, jobLink));
                } catch (NoSuchElementException e) {
                    // Handle the exception and continue the loop
                    System.out.println("Element not found for index: " + i + " - Skipping...");
                }
            }

        File attachment =  ExcelExport.createJobSheet(jobListdata);

        // Get that excel and call mailing function in EmailService
        sendEmail(attachment);
    }
    //call function in excel export to add data to excel and create excel

}
