package com.hoang.travel.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hoang.travel.entity.RoleEntity;
import com.hoang.travel.service.IRoleService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class importFileExcel {
    @Autowired
    private IRoleService roleService;
    public void importFileExcel(MultipartFile file){
        try {
            FileInputStream excelFile = (FileInputStream) file.getInputStream();
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            System.out.println(firstCell.getStringCellValue());
            List<RoleEntity> listOfCustomer = new ArrayList<>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                RoleEntity customer = new RoleEntity();
                customer.setId(Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0))));
                customer.setRoleCode(currentRow.getCell(1).getStringCellValue());
                customer.setRoleName(currentRow.getCell(2).getStringCellValue());
                listOfCustomer.add(customer);
            }
            for (RoleEntity customer : listOfCustomer) {
                System.out.println(customer);
                roleService.addAndUpdateUser(customer);

            }
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
