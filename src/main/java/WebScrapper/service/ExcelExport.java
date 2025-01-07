package WebScrapper.service;

import WebScrapper.models.Job;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExport {
    public static File createJobSheet(List<Job> jobsList) throws IOException {
        Workbook jobWorkbook = new XSSFWorkbook();
        Sheet sheet = jobWorkbook.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Job Title");
        row.createCell(1).setCellValue("Company Name");
        row.createCell(2).setCellValue("Job Link");

        int rowNum = 1;
        for(Job job : jobsList){
            Row newRow = sheet.createRow(rowNum++);
            newRow.createCell(0).setCellValue(job.getJobTitle());
            newRow.createCell(1).setCellValue(job.getCompanyName());
            newRow.createCell(2).setCellValue(job.getJobLink());
        }

        File file = new File("jobs.xlsx");
        FileOutputStream fileOut = new FileOutputStream(file);
        jobWorkbook.write(fileOut);

        return file;

    }



}
