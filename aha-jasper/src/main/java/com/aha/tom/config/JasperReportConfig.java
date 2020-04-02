package com.aha.tom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//public class JasperReportConfig extends WebMvcConfigurerAdapter {
//    @Bean
//    public JasperReportsViewResolver getJasperReportsViewResolver(){
//        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
//        resolver.setPrefix("classpath:jasperreport/"); //src/main/resources/jasperreport，将jrxml放入该目录
//        resolver.setSuffix(".jrxml");
//        resolver.setReportDataKey("datasource");
//        resolver.setViewClass(JasperReportsMultiFormatView.class);
//        resolver.setOrder(0);
//        return resolver;
//    }
//
//}