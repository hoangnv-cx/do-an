package com.hoang.travel.utils;

import com.hoang.travel.entity.RoleEntity;
import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.service.IRoleService;
import com.hoang.travel.service.IUserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class ImportFileExcelUser {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    public void importFileExcel(MultipartFile file) {
        try {
            FileInputStream excelFile = (FileInputStream) file.getInputStream();
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
      //      System.out.println(firstCell.getStringCellValue());
            List<UserEntity> userEntities = new ArrayList<>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                UserEntity user = new UserEntity();
                user.setId(Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0))));
                user.setFullName(currentRow.getCell(1).getStringCellValue());
                user.setUserName(currentRow.getCell(2).getStringCellValue());
                user.setPassWord(currentRow.getCell(3).getStringCellValue());
                user.setEmail(currentRow.getCell(4).getStringCellValue());
                user.setPhone(currentRow.getCell(5).getStringCellValue());
                user.setAddress(currentRow.getCell(6).getStringCellValue());

                if(currentRow.getCell(7) != null ){
                    user.setAbout(currentRow.getCell(7).getStringCellValue());
                }
                List<RoleEntity> roleEntities=new ArrayList<>();
                for(int i = 0; i < 10; i++){
                    if(currentRow.getCell(8+i) == null){
                        break;
                    }
                    RoleEntity roleEntity =roleService.findByName(currentRow.getCell(8+i).getStringCellValue());
                    roleEntities.add(roleEntity);
                }
                user.setRoles(roleEntities);
                userEntities.add(user);
            }
            for (UserEntity userEntity : userEntities) {
                System.out.println(userEntity);
                userService.addAndUpdateUser(userEntity);

            }
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
