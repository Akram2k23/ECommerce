package com.ecom.services.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.controller.model.Model_AddToCartRequest;
import com.ecom.controller.model.Model_CreateCategoryRequest;
import com.ecom.controller.model.Model_CreateProductRequest;
import com.ecom.controller.model.Model_LoginRequest;
import com.ecom.controller.model.Model_SaveUserRequest;
import com.ecom.entities.Ent_Cart;
import com.ecom.entities.Ent_CartItem;
import com.ecom.entities.Ent_Category;
import com.ecom.entities.Ent_Product;
import com.ecom.entities.Ent_Role;
import com.ecom.entities.Ent_User;
import com.ecom.repo.CartItemRepository;
import com.ecom.repo.CartRepository;
import com.ecom.repo.CategoryRepository;
import com.ecom.repo.ProductRepository;
import com.ecom.repo.RoleRepository;
import com.ecom.repo.UserRepository;
import com.ecom.services.MyService_Inf;
import com.ecom.userdetailsservice.UserDetailsServiceImpl;

@Component
public class MyService implements MyService_Inf {
	
//	@Value("${baseDir}")
	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Value("${server.port}")
    private String serverPort;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	public void saveRole(Ent_Role role) {
		System.out.println("Post-Role-Service");
		roleRepository.save(role);
	}

	public void saveAdmin(Model_SaveUserRequest modelAdmin) throws ParseException {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(date);
//		System.out.println(formattedDate);

		List<Ent_Role> roles = roleRepository.findAll();
		String encodedPassword = passwordEncoder.encode(modelAdmin.getPassword());

		// saving to db
		Ent_User saveAdmin = new Ent_User();
		saveAdmin.setUsername(modelAdmin.getUsername());
		saveAdmin.setPassword(encodedPassword);
		saveAdmin.setFirstName(modelAdmin.getFirstName());
		saveAdmin.setLastName(modelAdmin.getLastName());
		saveAdmin.setMobile(modelAdmin.getMobile());
		saveAdmin.setEmail(modelAdmin.getEmail());
		saveAdmin.setAddress(modelAdmin.getAddress());
		saveAdmin.setCreatedOn(formatter.parse(formattedDate));
		saveAdmin.setUpdatedOn(formatter.parse(formattedDate));

		Set<Ent_Role> assinedRoles = new HashSet<>();
		for (Ent_Role role : roles) {
			if (role.getRoleName().equals("Role_User") || role.getRoleName().equals("Role_Admin")) {
				assinedRoles.add(role);
//				user.setRoles(role.getRoleName());
			}
		}
		saveAdmin.setRoles(assinedRoles);
		userRepository.save(saveAdmin);
	}

	public void saveUser(Model_SaveUserRequest modelUser) throws ParseException {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(date);
//		System.out.println(formattedDate);

		List<Ent_Role> roles = roleRepository.findAll();
		String encodedPassword = passwordEncoder.encode(modelUser.getPassword());
//		user.setPassword(encodedPassword);

		Ent_User saveUser = new Ent_User();
		saveUser.setUsername(modelUser.getUsername());
		saveUser.setPassword(encodedPassword);
		saveUser.setFirstName(modelUser.getFirstName());
		saveUser.setLastName(modelUser.getLastName());
		saveUser.setMobile(modelUser.getMobile());
		saveUser.setEmail(modelUser.getEmail());
		saveUser.setAddress(modelUser.getAddress());
		saveUser.setCreatedOn(formatter.parse(formattedDate));
		saveUser.setUpdatedOn(formatter.parse(formattedDate));

		Set<Ent_Role> assinedRoles = new HashSet<>();
		for (Ent_Role role : roles) {
			if (role.getRoleName().equals("Role_User")) {
				assinedRoles.add(role);
//				user.setRoles(role.getRoleName());
			}
		}
		saveUser.setRoles(assinedRoles);
		userRepository.save(saveUser);
	}

	@Override
	public Optional<Ent_User> getUserById(long id) { // ============================================
		System.out.println("Service-Get-Done");
		Optional<Ent_User> user = userRepository.findById(id);
		System.out.println("Exception-Handled");
		return user;
	}

	public void updateUser() {
		System.out.println("Update - User - Service");
	}

	public void deleteUser(long id) {
		System.out.println("Delete - User - Service");
		userRepository.deleteById(id);
	}

//	@Override
//	public Entity_User loginAdmin(long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Ent_User loginAdmin(Model_LoginRequest modelLogin) {

		System.out.println(modelLogin.getUsername());
		System.out.println(modelLogin.getPassword());

		// Authenticate the user
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(modelLogin.getUsername(), modelLogin.getPassword()));

		// Set the authentication object in the SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Load the user details from the authentication object
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		// Retrieve the user from the database
		Ent_User userAdmin = userRepository.findByUsername(userDetails.getUsername());
		if (userAdmin == null) {
			throw new UsernameNotFoundException("User not found with username: " + modelLogin.getUsername());
		}

		if (userAdmin.getRoles().stream().anyMatch(e -> e.getRoleName().equals("Role_Admin"))) {
			System.out.println("Admin return");
			return userAdmin;
		} else {
			System.out.println("Admin Error return");
			throw new UsernameNotFoundException("This User is not Admin: " + modelLogin.getUsername());
		}

		// Authentication is successful if no exception is thrown
//        return ResponseEntity.ok("Login successful");
	}

	@Override
	public Ent_User loginUser(Model_LoginRequest modelLogin) {
		System.out.println(modelLogin.getUsername());
		System.out.println(modelLogin.getPassword());

		// Authenticate the user
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(modelLogin.getUsername(), modelLogin.getPassword()));

		// Set the authentication object in the SecurityContext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Load the user details from the authentication object
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		// Retrieve the user from the database
		Ent_User user = userRepository.findByUsername(userDetails.getUsername());
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + modelLogin.getUsername());
		}

		if (user.getRoles().stream().anyMatch(e -> e.getRoleName().equals("Role_User"))) {
			System.out.println("User return");
			return user;
		} else {
			System.out.println("User Error return");
			throw new UsernameNotFoundException("This User is not Admin: " + modelLogin.getUsername());
		}
	}

	@Override
	public String createCategory(Model_CreateCategoryRequest modelCategory) throws ParseException {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(date);

		Ent_Category saveCategory = new Ent_Category();

		saveCategory.setCatName(modelCategory.getCatName());
		saveCategory.setCatDescription(modelCategory.getCatDescription());
		saveCategory.setAddedBy(modelCategory.getAddedBy());
		saveCategory.setCreatedOn(formatter.parse(formattedDate));
		saveCategory.setUpdatedOn(formatter.parse(formattedDate));

		categoryRepository.save(saveCategory);

		return "Category Saved";
	}

	@Override
	public String createProduct(Model_CreateProductRequest modelProduct) throws ParseException {
		
		System.out.println("This is image : "+modelProduct);
		
		MultipartFile imagee = modelProduct.getImage();
		String filePath = handleImage(imagee);
		
		System.out.println("File Path : "+filePath);
		System.out.println("==========");
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(date);

		Ent_Product saveProduct = new Ent_Product();

		saveProduct.setName(modelProduct.getName());
		saveProduct.setPrice(modelProduct.getPrice());
		saveProduct.setDescription(modelProduct.getDescription());
		saveProduct.setAddedBy(modelProduct.getAddedBy());
		saveProduct.setImage(modelProduct.getImage().getOriginalFilename());
		saveProduct.setCategory(modelProduct.getCategory());
		saveProduct.setCreatedOn(formatter.parse(formattedDate));
		saveProduct.setUpdatedOn(formatter.parse(formattedDate));

		productRepository.save(saveProduct);

		return "Product Saved";
	}

	@Override
	public Map<String, Object> getAllCategory() {

//		JSONObject catList = new JSONObject();
		Map<String, Object> map = new HashMap<>();

		List<Ent_Category> categoryListAll = categoryRepository.findAll();
		categoryListAll.forEach(e -> System.out.println(e));

		map.put("CategoryList", categoryListAll);

//		List<JSONObject> catList = new ArrayList<>();

		System.out.println(map);
		return map;
	}

//	@Override
//	public Map<String, List<Map<String, Object>>> getAllProducts() {
//
//		int count = 0;
//
//		Map<String, List<Map<String, Object>>> map = new HashMap<>();
//
//		Map<String, Object> map1 = new HashMap<>();
//		
//		List<Map<String, Object>> productList = new ArrayList<>();
//
//		List<Ent_Product> categoryListAll = productRepository.findAll();
//		categoryListAll.forEach(e -> System.out.println(e));
//
//		for (Ent_Product prod : categoryListAll) {
//
//			map1.put("name", prod.getName());
//			map1.put("price", prod.getPrice());
//			map1.put("description", prod.getDescription());
//			map1.put("image", prod.getImage());
//			count++;
//			productList.add(map1);
//
//		}
//		System.out.println("coutn = " + count);
//		map.put("ProductList", productList);
//		
//		System.out.println(map);
//
//		return map;
//	}
	
	
	@Override
	public Map<String, List<Map<String, Object>>> getAllProducts() {

		int count = 0;

		Map<String, List<Map<String, Object>>> map = new HashMap<>();
		
		List<Map<String, Object>> productList = new ArrayList<>(); 

		List<Ent_Product> categoryListAll = productRepository.findAll();
		categoryListAll.forEach(e -> System.out.println(e));
		
		if(!productList.equals(null)) {
			
			for (Ent_Product prod : categoryListAll) {
				
				String imageUrl = "http://localhost:"+serverPort+"/uploads/"+prod.getImage();
				
				Map<String, Object> map1 = new HashMap<>();
				map1.put("id", prod.getProId());
				map1.put("name", prod.getName());
				map1.put("price", prod.getPrice());
				map1.put("description", prod.getDescription());
				map1.put("image", imageUrl);
				count++;
				productList.add(map1);

			}
			System.out.println("coutn = " + count);
			map.put("ProductList", productList);
		}else {
			return null;
		}
		
		return map;
	}
	
	String handleImage(MultipartFile image){
		String fileName = null;
		
		if(image != null && !image.isEmpty()) {
			try {
				
				fileName = image.getOriginalFilename();
				Path filePath = Paths.get(uploadDir + "/" + fileName);
				
				// Ensure the directory exists
				Files.createDirectories(filePath.getParent());
				
				// Save the file to the server
				Files.write(filePath, image.getBytes());
				return fileName;
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return fileName;
	}

	@Override
	public Map<String, Object> getProductById(long id) {
		
		Optional<Ent_Product> productObj = productRepository.findById(id);
		Map<String, Object> mapp = new HashMap<>();
		
		if(productObj.isPresent()) {
			Ent_Product prod = productObj.get();
			String imageUrl = "http://localhost:"+serverPort+"/uploads/"+prod.getImage();
			
			Map<String, Object> map = new HashMap<>();
			map.put("proId", prod.getProId());
			map.put("name", prod.getName());
			map.put("price", prod.getPrice());
			map.put("description", prod.getDescription());
			map.put("image", imageUrl);
			mapp.put("product", map);
			
		}else {
			return null;
		}
		
		return mapp;
	}

	@Override
	public String addToCart(Model_AddToCartRequest addToCartRequest) throws ParseException {
		
		Ent_Cart cart = cartRepository.findByUserId(addToCartRequest.getUserId())
				.orElseGet(() -> {
					try {
						return createNewCartForUser(addToCartRequest.getUserId());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				});
		
		addProductToCart(addToCartRequest, cart);
		
		return "ok";
	}
	
	Ent_Cart createNewCartForUser(Long userId) throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(date);
		
		Ent_Cart cart = new Ent_Cart();
		cart.setUserId(userId);
		cart.setCreatedOn(formatter.parse(formattedDate));
		
		return cartRepository.save(cart);
	}
	
	void addProductToCart(Model_AddToCartRequest addToCartRequest, Ent_Cart cart) throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(date);
		
		Ent_CartItem cartItems = cartItemRepository.findByProductProIdAndCartId(addToCartRequest.getProduct().getProId(), cart.getId());
		
		
		if(cartItems == null || cart.getId() != cartItems.getCart().getId()) {
			Ent_CartItem cartItem = new Ent_CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(addToCartRequest.getProduct());
			cartItem.setQuantity(1);
			cartItem.setCreatedOn(formatter.parse(formattedDate));
			cartItem.setUpdatedOn(formatter.parse(formattedDate));
			cartItemRepository.save(cartItem);
		}else if(cart.getId() == cartItems.getCart().getId()) {
			
			cartItems.setQuantity(cartItems.getQuantity() + 1);
			cartItems.setUpdatedOn(formatter.parse(formattedDate));
			cartItemRepository.save(cartItems);
		}
		
		
		
		
		
	}
	
	
	
	

}