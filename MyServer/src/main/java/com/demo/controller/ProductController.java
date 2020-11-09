package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entites.Product;
import com.demo.services.ProductService;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="findall",
					method=RequestMethod.GET,
					produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
					headers = "Accept=application/json")
	public ResponseEntity<Iterable<Product>> findAll(){
		try {
			return new ResponseEntity<Iterable<Product>>(productService.findAll(),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Iterable<Product>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="find/{id}",
			method=RequestMethod.GET,
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
			headers = "Accept=application/json")
	public ResponseEntity<Product> find(@PathVariable("id") String id) {
		try {			
			return new ResponseEntity<Product>(productService.find(id),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="create",
			method=RequestMethod.POST,
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
			consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE},
			headers = "Accept=application/json")
	public ResponseEntity<Void> create(@RequestBody Product product) {
		try {
			productService.create(product);
			return new ResponseEntity<Void>( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="update",
			method=RequestMethod.POST,
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
			consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE},
			headers = "Accept=application/json")
	public ResponseEntity<Void> update(@RequestBody Product product) {
		try {
			productService.update(product);
			return new ResponseEntity<Void>( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="delete/{id}",
			method=RequestMethod.DELETE
			)
	public ResponseEntity<Void> delete(@PathVariable("id") String id) {
		try {
			productService.delete(id);
			return new ResponseEntity<Void>( HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
