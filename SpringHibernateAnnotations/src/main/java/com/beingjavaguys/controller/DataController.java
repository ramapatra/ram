package com.beingjavaguys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.beingjavaguys.domain.Employee;
import com.beingjavaguys.services.DataService;

@Controller
public class DataController {
	
	@Autowired
	DataService dataService;
	
	
	
	@Autowired
	PasswordValidator pv;
	
	

	@RequestMapping("form")
	public ModelAndView getForm(@ModelAttribute Employee employee) {
		return new ModelAndView("form");
	}
	
	@RequestMapping("register")
	public ModelAndView registerUser(@ModelAttribute Employee employee,BindingResult br) {
		pv.validate(employee,br);
		if(br.hasErrors())
			return new ModelAndView("form");
		
		dataService.insertRow(employee);
		return new ModelAndView("redirect:list");
	}
	
	@RequestMapping("list")
	public ModelAndView getList() {
		List employeeList = dataService.getList();
		return new ModelAndView("list","employeeList",employeeList);
	}
	
	@RequestMapping("delete")
	public ModelAndView deleteUser(@RequestParam int id) {
		dataService.deleteRow(id);
		return new ModelAndView("redirect:list");
	}
	
	@RequestMapping("edit")
	public ModelAndView editUser(@RequestParam int id,@ModelAttribute Employee employee) {
		Employee employeeObject = dataService.getRowById(id);
		return new ModelAndView("edit","employeeObject",employeeObject);
	}
	
	@RequestMapping("update")
	public ModelAndView updateUser(@ModelAttribute Employee employee) {
		dataService.updateRow(employee);
		return new ModelAndView("redirect:list");
	}

}
