package com.aha.tom.controller;

import com.aha.tom.entity.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private HttpSession httpSession;

    /**
     * 普通的变量映射
     *
     * @param reportName
     * @param response
     * @throws Exception
     */
    @RequestMapping("/userToPdf")
    public void userToPdf(@RequestParam("reportName") final String reportName,
                          HttpServletResponse response) throws Exception {

        //第二个参数就是用来填充模板中的parameters
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小泽");
        map.put("address", "ze@qq.com");
//        URL url = this.getClass().getClassLoader().getResource("jasper/" + reportName + ".jrxml");
//        JasperCompileManager.compileReportToFile(url.getPath());
        //获取文件流
        ClassPathResource resource = new ClassPathResource("jasper" + File.separator + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, new JREmptyDataSource());
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);


    }

    @RequestMapping("/javaBean")
    public void javaBeanToPdf(@RequestParam("reportName") final String reportName,
                              HttpServletResponse response) throws Exception{

        //获取文件流
        ClassPathResource resource = new ClassPathResource("jasper" + File.separator + reportName + ".jasper");
        InputStream jasperStream = resource.getInputStream();

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        //组织javabean数据
        List<User> javabeanList = new ArrayList<>();
        javabeanList = getUserList();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(javabeanList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrDataSource);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

    }

    public List<User> getUserList() {
        List<User> javabeanList = new ArrayList<>();
        for(int i=0; i<5; i++){
            javabeanList.add(new User("Tom"+i, 20+i, 23.3+i, "上海"+i));
        }
        return javabeanList;
    }
}