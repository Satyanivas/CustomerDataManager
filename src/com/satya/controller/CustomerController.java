package com.satya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.satya.entity.Customer;
import com.satya.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
   public String listCustomers(Model model) {
		List<Customer> thecustomers=customerService.getCustomers();
		model.addAttribute("customers",thecustomers);
	return "list-customers";   
   }
	
	@GetMapping("/showFormForAdd")
	   public String showFormForAdd(Model model) {
		Customer thecustomer=new Customer();
		model.addAttribute("customer",thecustomer);
		return "customer-form";   
	   }
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer thecustomer) {
		
		customerService.saveCustomer(thecustomer);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId")int Id,Model model) {
		Customer thecustomer=customerService.getCustomer(Id);
		model.addAttribute("customer", thecustomer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		customerService.deleteCustomer(id);
		 return "redirect:/customer/list";
		
	}
	
}
