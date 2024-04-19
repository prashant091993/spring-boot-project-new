package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.model.CategoryMstObject;
import com.service.CategoryMstService;

@RestController
@RequestMapping("/category")
public class CategoryMstController 
{
	@Autowired
	private CategoryMstService categoryService;
	
		
	@RequestMapping(value="/{strVar1}/{strVar2}/{strVar3}/{strMode}/{strWsMode}", method=RequestMethod.GET)
	@CrossOrigin(origins="*")
	public List<CategoryMstObject> getProduct(@PathVariable(value = "strVar1") String strVar1, @PathVariable(value = "strVar2") String strVar2,@PathVariable(value = "strVar3") String strVar3, @PathVariable(value = "strMode") String strMode, @PathVariable(value = "strWsMode") String strWsMode) 
	{
		System.out.println("strVar1---"+strVar1);
	    System.out.println("strVar2---"+strVar2);
	    System.out.println("strVar3---"+strVar3);
	    System.out.println("strWsMode---"+strWsMode);
	
		//finds all the products
		List<CategoryMstObject> products = categoryService.findAll();
		//returns the product list
		return products;
	}
	
}
