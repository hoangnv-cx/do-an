package com.hoang.travel.service.impl;

import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.repository.UserRepository;
import com.hoang.travel.service.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements IReportService {

    @Autowired
    private UserRepository userRepository;

    public byte[] getReport() throws IOException, JRException {
        // tìm kiếm file report
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("report/userreport.jrxml").getInputStream());
        // đây là list data của mình, thông thường list này sẽ đc query dưới db
        List<UserEntity> user = (List<UserEntity>) userRepository.findAll();
        // khởi tạo data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(user);
        // khai báo các parameter
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Giang");
        // compile file report cùng các tham số đã khai báo
        return JasperExportManager.exportReportToPdf(JasperFillManager.fillReport(jasperReport, parameters, dataSource));
    }
}
