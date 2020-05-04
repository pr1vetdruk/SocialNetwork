package ru.privetdruk.socialnetwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {
    @GetMapping
    public String test(Model model) {
        Map<Object, Object> data = new HashMap<>();
        data.put("ownerId", 1);
        model.addAttribute("frontendData", data);
        model.addAttribute("ownerId", 322);
        return "test";
    }
}
