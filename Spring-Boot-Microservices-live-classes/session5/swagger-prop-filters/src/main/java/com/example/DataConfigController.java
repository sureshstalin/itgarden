package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DataConfigController {

    @Autowired
    private DataFileConfig dataFileConfig;

    @GetMapping("/dataconfig")
    public String getYamlProp() {
        return dataFileConfig.getFilePath() + " , " + dataFileConfig.getAccessType() + " ," +
                dataFileConfig.getPassword() + ", " + dataFileConfig.getUsername();
    }
}
