package com.ecom.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.controller.model.Model_AddToCartRequest;
import com.ecom.controller.model.Model_CreateCategoryRequest;
import com.ecom.controller.model.Model_CreateProductRequest;
import com.ecom.controller.model.Model_LoginRequest;
import com.ecom.controller.model.Model_SaveUserRequest;
import com.ecom.entities.Ent_Category;
import com.ecom.entities.Ent_Product;
import com.ecom.entities.Ent_Role;
import com.ecom.entities.Ent_User;
import com.ecom.services.impl.MyService;

//@CrossOrigin(origins = "http://16.170.206.92")
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = {"http://16.170.206.92", "http://localhost:3000"})
//@CrossOrigin(origins = "*")

@CrossOrigin
@RestController
@RequestMapping("/ss")
public class MyController {
	
	@Autowired
	private MyService myService;

	@PostMapping("/saverole")
	public void saveRole(@RequestBody Ent_Role role) {
		System.out.println("Post-Role-Controller");
		myService.saveRole(role);
	}
	
	@PostMapping("/saveadmin")
	public void saveAdmin(@RequestBody Model_SaveUserRequest modelAdmin) throws ParseException {
		System.out.println("Post-Admin-Done");
		myService.saveAdmin(modelAdmin);
	}
	
	@PostMapping("/saveuser")
	public void saveUser(@RequestBody Model_SaveUserRequest modelUser) throws ParseException {
		System.out.println("Post-User-Done");
		myService.saveUser(modelUser);
	}
	
	@PreAuthorize("hasRole('Role_User')")
	@GetMapping("/getuser/{id}")
	public Optional<Ent_User> getUserById(@PathVariable long id) {
		System.out.println("Post-Get-Done");
		return myService.getUserById(id);
	}
	
	@PreAuthorize("hasRole('Role_Admin')")
	@PutMapping("/updateuser/{id}")
	public void updateUser(@RequestBody Ent_User user, long id) {
		System.out.println("Update - User - Controller");
		myService.updateUser();
	}
	
	@PreAuthorize("hasRole('Role_Admin')")
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUser(@PathVariable long id) {
		System.out.println("Delete - User - Controller");
		myService.deleteUser(id);
	}
	
//	@PreAuthorize("hasRole('Role_Admin')")
	@PostMapping("/loginadmin")
	public Ent_User loginAdmin(@RequestBody Model_LoginRequest modelLogin) {
		System.out.println("Post-Get-Done-Admin-login");
		return myService.loginAdmin(modelLogin);
	}
	
	@PostMapping("/loginuser")
	public Ent_User loginUser(@RequestBody Model_LoginRequest modelLogin) {
		System.out.println("Post-Get-Done-Admin-login");
		return myService.loginUser(modelLogin);
	}
	
	@PreAuthorize("hasRole('Role_Admin')")
	@PostMapping("/createCategory")
	public String createCategory(@RequestBody Model_CreateCategoryRequest modelCategory) throws ParseException {
		System.out.println("Post-Get-Done-Admin-login");
		myService.createCategory(modelCategory);
		return "Category Saved";
	}
	
	@PreAuthorize("hasRole('Role_Admin')")
	@PostMapping("/createProduct")
	public String createProduct(@ModelAttribute Model_CreateProductRequest modelProduct) throws ParseException {
		System.out.println("sdkjofiejoijrfijor");
		System.out.println("Post-Create-Product-Controller");
		System.out.println(modelProduct.getImage());
		
		System.out.println("This is image size : "+modelProduct.getImage());
		
		myService.createProduct(modelProduct);
		return "Product Saved";
	}
	
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<Map<String, Object>> getAllCategory() {
		System.out.println("Get-Done-controller");
		Map<String, Object> obj = myService.getAllCategory();
		System.out.println("=======================");
		System.out.println(obj);
		System.out.println("API - Hit");
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<Map<String, List<Map<String, Object>>>> getAllProducts() {
		System.out.println("Get-Done-controller");
		Map<String, List<Map<String, Object>>> obj = myService.getAllProducts();
		System.out.println("API - Hit");
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	@GetMapping("/getProductById/{id}")
	public ResponseEntity<Map<String, Object>> getProductById(@PathVariable long id) {
		System.out.println("Get-Done-controller");
		Map<String, Object> obj = myService.getProductById(id);
		System.out.println("API - Hit");
		return ResponseEntity.status(HttpStatus.OK).body(obj);
	}
	
	
//	@PreAuthorize("hasRole('Role_Admin')")
	@PostMapping("/addToCart")
	public String addToCart(@RequestBody Model_AddToCartRequest addToCartRequest) throws ParseException {
		System.out.println("Post-Create-Product-Controller");
				
		myService.addToCart(addToCartRequest);
		return "Product Added To Cart";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Autowired
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
//        String username = loginForm.getUsername();
//        String password = loginForm.getPassword();
//
//        // Authenticate the user
//        Authentication authentication = authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(username, password));
//
//        // Set the authentication object in the SecurityContext
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Authentication is successful if no exception is thrown
//        return ResponseEntity.ok("Login successful");
//    }
	
	
//	@GetMapping("/profile")
//	public ResponseEntity<String> getProfile() {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    String username = authentication.getName(); // Get the username of the authenticated user
//	    return ResponseEntity.ok("Hello, " + username);
//	}

}