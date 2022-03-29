package com.hoang.travel.utils;

import com.hoang.travel.entity.UserEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelFileExporter {
    public static ByteArrayInputStream contactListToExcelFile(List<UserEntity> users) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header
            Cell cell = row.createCell(0);
            cell.setCellValue("Id");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Full Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("User Name");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Password");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Email");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Phone");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Address");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("About");
            cell.setCellStyle(headerCellStyle);





            // Creating data rows for each customer
            for (int i = 0; i < users.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                for(int j  = 0; j < users.get(i).getRoles().size(); j++){
                    cell = row.createCell(8+j);
                    cell.setCellValue("Role "+j);
                    cell.setCellStyle(headerCellStyle);
                    dataRow.createCell(8+j).setCellValue(users.get(i).getRoles().get(j).getRoleName());
                    sheet.autoSizeColumn(8+j);

                }
                dataRow.createCell(0).setCellValue(users.get(i).getId());
                dataRow.createCell(1).setCellValue(users.get(i).getFullName());
                dataRow.createCell(2).setCellValue(users.get(i).getUserName());
                dataRow.createCell(3).setCellValue(users.get(i).getPassWord());
                dataRow.createCell(4).setCellValue(users.get(i).getEmail());
                dataRow.createCell(5).setCellValue(users.get(i).getPhone());
                dataRow.createCell(6).setCellValue(users.get(i).getAddress());
                dataRow.createCell(7).setCellValue(users.get(i).getAbout());

            }

            // Making size of column auto resize to fit with data
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);



            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
