package com.hsn.properties;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;


@RestController
@PropertySource(value = "classpath:custom_props.properties")
@ConfigurationProperties(prefix = "mycustom")
@Data
public class TestController {
    @Autowired
    PropertyReader propertyReader;

    @Value("${mesage.welcome}")
    private String welcomeMessage;

    private String applicationName; //mycustom.applicationName wil be injected
    private Map<String,String> developer; //mycustom.developer will be injected
    private List<String> projects; //mycustom.projects will be injected

    @GetMapping("/")
    public String getMethodName() {
        System.out.println(welcomeMessage);
        
        System.out.println("Property(message.hello) from application.properties:"+propertyReader.getProperty("message.hello"));
        System.out.println("Property(message.bye) from application.properties:"+propertyReader.getProperty("message.bye"));
        System.out.println("PATH from environment:"+propertyReader.getProperty("PATH"));
        System.out.println("=======================Custom properties from custom_props.properties file=========================");
        System.out.println("Application Name:"+applicationName);
        System.out.println("Developer Name:"+developer.get("name"));
        System.out.println("Developer Experience:"+developer.get("experience"));
        int i = 0;
        for(String project:projects) {
            System.out.println("Project["+i+"]:"+project);
            i++;
        }
        return new String("Properties are printed in console");
    }

}
