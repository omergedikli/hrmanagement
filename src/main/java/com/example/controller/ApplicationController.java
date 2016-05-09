package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.service.ApplicationService;

@Controller
public class ApplicationController {
   private final ApplicationService applicationService;

   @Autowired
   public ApplicationController(ApplicationService applicationService) {
   		this.applicationService = applicationService;
   }

   @RequestMapping("/application")
   public ModelAndView getApplicationListPage() {
       return new ModelAndView("applications", "applications", applicationService.getApplications());
   }

   @RequestMapping(value = "/application/{id}", method = RequestMethod.GET)
   public ModelAndView getAppPage(@PathVariable Long id) {
	   Map<String, Object> model = new HashMap<String, Object>();
	   model.put("app", applicationService.getAppById(id));
	   return new ModelAndView("applicationdetail", model);
   }
}
