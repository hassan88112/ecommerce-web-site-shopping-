package com.example.SpringBootProject.Controller;

import com.example.SpringBootProject.DTO.DateRange;
import com.example.SpringBootProject.Entity.ExcelTest;
import com.example.SpringBootProject.Service.ExcelService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/showExcel")
    public String show(Model model){
        model.addAttribute("excel",new ExcelTest());
        model.addAttribute("Excels",excelService.findAllExcelTest());  // get All customers from Excel test
        return "uploadFile";

    }
    @PostMapping("/uploadFile")
    public String uploadExcel(@ModelAttribute ("excel") ExcelTest test , RedirectAttributes redirectAttributes){

        boolean isFlag=excelService.saveDateFromUploadFlie(test.getFile());
        return "redirect:/showExcel";

    }


    @GetMapping("/excel")
    public String AllExcel(Model model){

        return "redirect:/test";
    }



















    /*
    @GetMapping("/")
    public String index(Model model){

        return "index";
    }



    @PostMapping("/import")
    public String readExcel(@RequestParam ("file")MultipartFile file) throws IOException {

        List<ExcelTest> temp=new ArrayList<ExcelTest>();
        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet=workbook.getSheetAt(0);
        for (int i=1;i<sheet.getPhysicalNumberOfRows() ;i++){

            ExcelTest test=new ExcelTest();
            XSSFRow row=sheet.getRow(i);
            System.out.println(+row.getLastCellNum());
            test.setId((long) row.getCell(0).getNumericCellValue());
            test.setFirstName(row.getCell(1).getStringCellValue());
            test.setSalary(row.getCell(2).getStringCellValue());
            temp.add(test);

        }
        return "index";
    }


*/

}
