package com.codeid.eshopper.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.codeid.eshopper.entities.Region;
import com.codeid.eshopper.service.RegionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@Controller
@RequestMapping("/regions")
public class RegionController {
    private final RegionService service;

    public RegionController(RegionService service) {
        this.service = service;
    }

    // get
    @GetMapping("/")
    public String findAllRegion(Model model) {
        model.addAttribute("regions",service.findAllCategory());
        model.addAttribute("region", new Region());
        return "region";
    }

    //post
    @PostMapping("/create")
    public String createRegion(@ModelAttribute("region") @Valid Region region, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("regions", service.findAllCategory());
            return "region";
        }

        System.out.println("Submitting Region ID: " + region.getRegionId());

        service.addRegion(region);
        return "redirect:/regions/";
    }

    //put
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Region region = service.findById(id);
        model.addAttribute("region", region);
        model.addAttribute("regions", service.findAllCategory());
        return "region";
    }

    @PostMapping("/update/{id}")
    public String updateRegion(@PathVariable("id") Long id,
    @ModelAttribute("region") @Valid Region region,BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("regions", service.findAllCategory());
            return "region";
        }
        service.updateRegion(id, region);
        return "redirect:/regions/";
    }

    //delete
    @GetMapping("/delete/{id}")
    public String deleteRegion(@PathVariable("id") Long id) {
        service.deleteRegion(id);
        return "redirect:/regions/";
    }

}
