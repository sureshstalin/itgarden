package com.itgarden.itgardenwebapp.controller;

import com.itgarden.itgardenwebapp.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class WebAppController {

    @GetMapping("/post") // http://localhost:8080/post
    public String courseSubmit(Model model) {
        System.out.println("Displaying course form");
        model.addAttribute("course",new Course());
        return "post";
    }

    @PostMapping("/display")
    public String displayCourse(@ModelAttribute("data") Course course) {
        System.out.println("This is displaying the course details");
        System.out.println("Course Name " + course.getCourseName());
        System.out.println("Course Duration " + course.getDuration());
        System.out.println("Place " + course.getPlace());
        return "display";
    }
}
