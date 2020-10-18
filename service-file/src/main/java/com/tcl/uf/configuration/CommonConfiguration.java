package com.tcl.uf.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Configuration
public class CommonConfiguration {

    @Value("${upload.locationTemp}")
    private String locationTemp;

	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // upgrade (hepeng)
        factory.setMaxFileSize(DataSize.ofMegabytes(100));
//        factory.setMaxFileSize("100MB");
//        factory.setMaxRequestSize("100MB");
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));
        File tmpFile = new File(locationTemp);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(locationTemp);
        return factory.createMultipartConfig();
    }
}
