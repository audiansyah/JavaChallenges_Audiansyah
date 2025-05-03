package com.codeid.eshopper_schema_oe.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codeid.eshopper_schema_oe.entities.Shipper;
import com.codeid.eshopper_schema_oe.service.ShipperService;
import com.codeid.eshopper_schema_oe.service.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/shipper")
public class ShipperController {
    private final ShipperService shipperService;
    private final CategoryService categoryService;

    public ShipperController(ShipperService shipperService, CategoryService categoryService){
        this.shipperService = shipperService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String findAllShipper(Model model) {
        model.addAttribute("shipper", shipperService.findAllShipper());
        model.addAttribute("categories", categoryService.findAllCategory());
        return "/modules/shipper/shipper.html";
    }

    @GetMapping("/add")
    public String addShipper(Model model) {
        model.addAttribute("shipper", new Shipper());
        model.addAttribute("action", "Add Shipper");
        return "/modules/shipper/addEdit.html";
    }

    @PostMapping("/add")
    public String postShipper(@Valid Shipper shipper,BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()){
            return "/modules/shipper/addEdit.html";
        }

        shipperService.addShipper(shipper);

        redirectAttrs.addFlashAttribute("message", "Shipper "+shipper.getCompanyName()+" created!");
		return "redirect:/shipper/";
    }

    @GetMapping("edit/{id}")
	public String editShipperById(@PathVariable("id") long shipperId,Model model) {

		Optional<Shipper> shipper = shipperService.findShipperById(shipperId);

		model.addAttribute("action", "Edit shipper");
		model.addAttribute("shipper", shipper.get());
		return "modules/shipper/addEdit.html";

	}


    @GetMapping("delete/{id}")
	public String deleteShipper(@PathVariable(name = "id") Long shipperId,RedirectAttributes redirectAttrs) {
		shipperService.deleteShipperById(shipperId);
		redirectAttrs.addFlashAttribute("message", "ShipperId "+shipperId+" has been delete!");
		return "redirect:/shipper/";
	}
    
    
}
