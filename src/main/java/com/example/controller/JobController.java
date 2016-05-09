package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.App;
import com.example.domain.ApplicationForm;
import com.example.domain.JobAddForm;
import com.example.service.ApplicationService;
import com.example.service.JobService;
import org.springframework.web.bind.WebDataBinder;


@Controller
public class JobController {
    private final JobService jobService;
    private final ApplicationService applicationService;

    @Autowired
    public JobController(JobService jobService,ApplicationService applicationService) {
    		this.jobService = jobService;
    		this.applicationService = applicationService;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value="/job/add",method=RequestMethod.GET)
    public ModelAndView jobAddPage() {
        return new ModelAndView("addjob", "jobForm", new JobAddForm());
    }

    @RequestMapping(value = "/job/add", method = RequestMethod.POST)
    public String handleJobAdd(@Valid @ModelAttribute("jobForm") JobAddForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            {return "addjob";}

        jobService.addJob(form);
        return "redirect:/job";
    }
    
    @RequestMapping("/job")
    public ModelAndView getJobsPage() {
        return new ModelAndView("jobs", "jobs", jobService.getJobs());
    }
 
    @RequestMapping(value = "/job/{id}", method = RequestMethod.POST)
    public ModelAndView handleApplicationForm(@PathVariable Long id,@Valid @ModelAttribute 
    		ApplicationForm form, BindingResult bindingResult,
    		@RequestParam("name") String name,@RequestParam("file") MultipartFile file,
			   RedirectAttributes redirectAttributes) {
    	 if (bindingResult.hasErrors()) {
    		 return new ModelAndView("redirect:/job/{id}");
         }
    	if(!file.getOriginalFilename().contains(".pdf")){
			redirectAttributes.addFlashAttribute("message", "PDF");
			return new ModelAndView("redirect:/");
    	}
    	if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Folder separators not allowed");
	        return new ModelAndView("redirect:/");
		}
		if (name.contains("/")) {
			redirectAttributes.addFlashAttribute("message", "Relative pathnames not allowed");
	        return new ModelAndView("redirect:/");
		}

		if (!file.isEmpty()) {
			try {
				String newfilename = form.getName()+"_" +id.toString();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(App.ROOT + "/" + newfilename )));
                FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				redirectAttributes.addFlashAttribute("message",
						"You successfully uploaded " + name + "!");
			} 
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"You failed to upload " + name + " => " + e.getMessage());
			}
		}
		else {
			redirectAttributes.addFlashAttribute("message",
					"You failed to upload " + name + " because the file was empty");
		}

      
        applicationService.basvur(form, id);
        return new ModelAndView("redirect:/job");
    }
    
@RequestMapping(value = "/job/{id}", method = RequestMethod.DELETE)
public String handleJobDelete(@PathVariable Long id) {
	jobService.deleteJobById(id);
    return "redirect:/job";
}

@RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
public String getJobPage(@PathVariable Long id,Model model){
	model.addAttribute("job",jobService.getJobById(id));
	model.addAttribute("form",new ApplicationForm());
	File rootFolder = new File(App.ROOT);
	List<String> fileNames = Arrays.stream(rootFolder.listFiles()).map(f -> f.getName()).collect(Collectors.toList());
	model.addAttribute("files",
			Arrays.stream(rootFolder.listFiles())
					.sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
					.map(f -> f.getName())
					.collect(Collectors.toList())
		);
	return "jobdetail";

}

}
