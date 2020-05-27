package com.itgarden.restweb.controller;

import com.itgarden.restweb.model.Employee;
import com.itgarden.restweb.service.BaseService;
import com.itgarden.restweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeService")
    private BaseService baseService;

    @GetMapping("/display") // http://localost:8080/api/employee/display?name=suresh
    public List<Employee> displayEmployee(@RequestParam("name") String name) {
      List<Employee> result = baseService.displayByName(name);
       return result;
    }
}
