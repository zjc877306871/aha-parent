package com.aha.tom.controller;

import com.aha.tom.entity.Conut;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
public class ImageAndCountController {

    @RequestMapping("/image")
    public void image(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam String reportName) throws Exception{

        Map<String,Object> paramterMap = new HashMap<>();
        String imagePath = this.getClass().getClassLoader().getResource("").getPath()+"imag/image1.jpg";
        paramterMap.put("imagelocal",imagePath);

        Map<String,Object> contentMap = new HashMap<>();
        contentMap.put("conut1",12.0);
        contentMap.put("count2",13.00);
        contentMap.put("count3",14);
        Conut conut = new Conut(12.0,13.33,13.4);

        List<Conut> list = new ArrayList<>();
        list.add(conut);
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);

        //获取文件流
        ClassPathResource resource = new ClassPathResource("jasper" + File.separator + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramterMap, jrDataSource);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);


    }

}