package ca.sheridancollege.antonye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.antonye.dao.ProductDatabaseAccess;
import ca.sheridancollege.antonye.model.Product;

@Controller
public class Assignment3SecureWebApplicationController {

	@Autowired
	private ProductDatabaseAccess cda;
	

	@GetMapping("/index")
	public String index() {
	return "index";
    }
	
	@GetMapping("/login")
	public String securityLogin() {
	return "login";
	}
	
	@GetMapping("/admin/productDataInput")
	public String productDataInput(Model model) {
	model.addAttribute("product", new Product());
	return "/admin/productDataInput";
	}
	
	@PostMapping("/admin/productDataInput")
	public String addProducts(Model model, @ModelAttribute Product product) {
	String message;
	long numberOfRows=cda.addProduct(product);
	if (numberOfRows > 0)
	{message="Success! The product object successfully added to the database.";}
	else
	{message ="Failure The product object is not added to the database.";}
	model. addAttribute ("message", message) ;
	return "/admin/productAddOutcome";
	}
	
	@GetMapping ("/admin/editableListOfProducts")
	public String viewEditableListOfProducts(Model model) {
	List<Product> products = cda.selectProduct();
	model.addAttribute("products", products);
	return "/admin/editableListOfProducts"; }
	
	@GetMapping("/admin/editProductData/{productCode}")
	public String editCourseData(Model model, @PathVariable("productCode") String productCode) {
	Product productByProductCode = cda.selectProductByProductCode(productCode);
	model.addAttribute("product", productByProductCode);
	return "/admin/editProductData";
	}
	
	@PostMapping("/admin/updateProductData")
	public String updateProduct(Model model,@ModelAttribute Product product) {
	long rowsUpdated = cda.updateProductByProductData(product);
	return "index";
	}
	
	@GetMapping ("/admin/deletableListOfProduct")
	public String viewDeletableListOfProducts(Model model) {
	List<Product> products = cda.selectProduct();
	model.addAttribute("products", products);
	return "/admin/deletableListOfProduct"; }
	
	@GetMapping("/admin/deleteProductData/{productCode}")
	public String deleteCourseData(Model model, @PathVariable("productCode") String productCode) {
		String message;
	long rowsDeleted = cda.deleteProductByProductCode(productCode);
	model.addAttribute("product",rowsDeleted);
	if (rowsDeleted> 0)
	{message="Success! The product object successfully deleted from the database.";}
	else
	{message ="Failure The product object is cannot  deleted from database.";}
	model. addAttribute ("message", message) ;
		return "/admin/deleteProductData";
	}
	
	
	@GetMapping ("/customer/listOfProducts")
	public String viewListOfProducts (Model model) {
	List<Product> product = cda.selectProduct();
	model.addAttribute ("product",product) ;
	return "/customer/listOfProducts";
	}
	
	@GetMapping("/customer/productBrandBasedSearch")
	public String productBrandInput(Model model) {
	return "/customer/productBrandBasedSearch";
	}

	@PostMapping("/customer/productBrandBasedSearch")
	public String productSearch(Model model, @RequestParam String productBrand) {
	List<Product> product =cda.selectProductByBrand(productBrand);
	model.addAttribute("product", product) ;
	return "/customer/listOfProducts";
	}
	
	
	@GetMapping("/customer/searchProductsAboveGivenPrice")
	public String selectProductAboveGivenPrice(Model model) {
	return "/customer/searchProductsAboveGivenPrice";
	}

	@PostMapping("/customer/searchProductsAboveGivenPrice")
	public String productSearchAboveGivenUnitPrice(Model model, @RequestParam double unitPrice) {
	List<Product> product =cda.selectProductAboveUnitPrice(unitPrice);
	model.addAttribute("product", product) ;
	return "/customer/listOfProducts";
	}
	
	@GetMapping("/customer/productCodeBasedSearch")
	public String productCodeInput(Model model) {
	return "/customer/productCodeBasedSearch";
	}

	@PostMapping("/customer/productCodeBasedSearch")
	public String productSearchBasedCode(Model model, @RequestParam String productCode) {
	List<Product> product =cda.selectProductByCode(productCode);
	model.addAttribute("product", product) ;
	return "/customer/listOfProducts";
	}
	
}
