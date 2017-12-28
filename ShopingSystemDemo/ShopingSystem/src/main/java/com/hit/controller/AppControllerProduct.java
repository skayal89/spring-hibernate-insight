package com.hit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hit.model.Category;
import com.hit.model.Product;
import com.hit.service.ProductService;

@Controller
@RequestMapping("/product/")
@ComponentScan("com.hit")
public class AppControllerProduct {

	@Autowired
	ProductService productService;
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping(value={"/list"}, method=RequestMethod.GET)
	public ModelAndView listProducts()
	{
		List<Product> list=productService.getAllProducts();
		System.out.println(list);
		List<Category> categories=productService.getAllCategories();
		ModelAndView modelAndView=new ModelAndView("ProductList");
		modelAndView.addObject("productList", list); // to show all products
		modelAndView.addObject("categoryList", categories); // to show all categories
		return modelAndView;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView addProduct(){
		Product product=new Product();
		Category category=new Category();
		product.setCategory(category);
		List<Category> categories=productService.getAllCategories();
		ModelAndView mView=new ModelAndView("AddProduct");
		mView.addObject("edit", false);
		mView.addObject("product2", product);
		mView.addObject("categoryList", categories);
		// need to add student object if we use 'path' 
		// which binds to a variable and the object received 
		// by 'commandName' or 'modelAttribute' tag
		return mView;
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result){
		System.out.println("saveProduct executing...");
		if(result.hasErrors())
		{
			System.out.println("saveProduct/hasErrors");
			//return addEmployee().getViewName();
		}
		
		/*Category c=productService.getCategory(product.getCategory().getCategoryId());
		product.getCategory().setCategoryName(c.getCategoryName());*/
		System.out.println("Category Id:"+product.getCategory());
		productService.add(product);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/edit/{prodid}", method=RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable("prodid") int id){
		Product product=productService.getProduct(id);
		ModelAndView mView=new ModelAndView("AddProduct");
		mView.addObject("edit", true);
		mView.addObject("product2", product); // need to add student object if we use 'path' 
		// which binds to a variable and the object received by 'commandName' or 'modelAttribute' tag
		return mView;
	}
	
	@RequestMapping(value="/edit/{prodid}", method=RequestMethod.POST)
	public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result){
		if(result.hasErrors())
		{
			return editProduct(product.getProductId()).getViewName();
		}
		
		productService.update(product);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/delete/{prodid}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable("prodid") int pid){
		productService.delete(pid);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/cat/new", method=RequestMethod.GET)
	public ModelAndView addCategory(){
		Category category=new Category();
		ModelAndView mView=new ModelAndView("AddCategory");
		mView.addObject("edit", false);
		mView.addObject("category2", category); 
		// need to add student object if we use 'path' 
		// which binds to a variable and the object received 
		// by 'commandName' or 'modelAttribute' tag
		return mView;
	}
	
	@RequestMapping(value="/cat/new", method=RequestMethod.POST)
	public String saveCategory(@Valid @ModelAttribute("product") Category category, BindingResult result){
		System.out.println("saveCategory executing...");
		if(result.hasErrors())
		{
			System.out.println("saveCategory/hasErrors");
			//return addEmployee().getViewName();
		}
		
		productService.addCategory(category);;
		return "redirect:/product/list";
	}
	
	@RequestMapping(value="/cat/delete/{catid}", method=RequestMethod.GET)
	public String deleteCategory(@PathVariable("catid") int cid){
		productService.deleteCategory(cid);
		return "redirect:/product/list";
	}
}
