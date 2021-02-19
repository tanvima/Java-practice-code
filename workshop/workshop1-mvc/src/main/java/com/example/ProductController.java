package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

@Autowired
ProductService pservice;
	
	@RequestMapping
	public ModelAndView getProducts() {
		ModelAndView mv = new ModelAndView("index");
		List<Product> prods = pservice.getAllProduct();
		mv.addObject("product",prods);
		return mv;
	}
	
	@RequestMapping("/add")
	public String showAdd(Model m) {
		Product product = new Product();
		m.addAttribute("prod",product);
		return "add-product";
	}
	
	@PostMapping(path="/submit-form")
	public ModelAndView submitForm(@ModelAttribute("prod")Product p) {
		System.out.println(p);
		pservice.addProduct(p);
		return new ModelAndView("redirect:/products");
		
	}
	
	@GetMapping(path="/delete")
	public ModelAndView delProduct(@RequestParam int id) {
		pservice.delProduct(id);
		return new ModelAndView("redirect:/products");
		
	}
	
	@GetMapping(path="/update/{id}")
	public ModelAndView updateProduct(@PathVariable int id) {
		Optional<Product> p = pservice.getProduct(id);
		ModelAndView mv = new ModelAndView("update-product");
		mv.addObject("prod",p);
		return mv;
	}
	@PostMapping(path="/update-form")
	public ModelAndView updateForm(@ModelAttribute("prod")Product p) {
		pservice.addProduct(p);
		return new ModelAndView("redirect:/products");
	}
	
	
}