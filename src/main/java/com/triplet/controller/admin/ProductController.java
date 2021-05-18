package com.triplet.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.bean.ProductInfo;
import com.triplet.bean.SearchProduct;
import com.triplet.model.Category;
import com.triplet.model.Product;
import com.triplet.model.Rate;
import com.triplet.utils.ImageFileUtils;

@PropertySource("classpath:messages.properties")
@Controller(value = "productControllerOfadmin")
@RequestMapping("/admin/products")
public class ProductController extends BaseController {

	Logger logger = Logger.getLogger(ProductController.class);

	@Value("${msg_error_save}")
	private String msg_error_save;

	@Value("${msg_success_save}")
	private String msg_success_save;

	private List<Product> products;
	private List<Category> categories;

	@GetMapping(value = { "", "/" })
	public String index(Model model) {

		this.categories = categoryService.getLeafNodes();

		// Search form
		SearchProduct searchProduct = new SearchProduct();

		model.addAttribute("categories", this.categories);
		model.addAttribute("searchProduct", searchProduct);
		return "views/admin/products/products";
	}

	@PostMapping("/search")
	public String search(@ModelAttribute("searchProduct") SearchProduct searchProduct, Model model) {

		this.products = productService.search(searchProduct);
		List<ProductInfo> productInfos = generateProductInfos(products);

		model.addAttribute("products", productInfos);
		model.addAttribute("categories", this.categories);
		model.addAttribute("searchProduct", searchProduct);
		return "views/admin/products/products";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		List<Rate> rates = rateService.loadRatings(product.getId());
		product.setRates(rates);
		ProductInfo productInfo = new ProductInfo(product);
		model.addAttribute("productInfo", productInfo);
		return "views/admin/products/product";
	}

	@GetMapping("/new")
	public String createForm(Model model) {
		ProductInfo productInfo = new ProductInfo();

		model.addAttribute("categories", this.categories);
		model.addAttribute("productInfo", productInfo);
		model.addAttribute("formStt", "add");
		return "views/admin/products/product-form";
	}

	@GetMapping("/{id}/edit")
	public String updateForm(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		List<Rate> rates = rateService.loadRatings(product.getId());
		product.setRates(rates);
		ProductInfo productInfo = new ProductInfo(product);

		model.addAttribute("productInfo", productInfo);
		model.addAttribute("categories", this.categories);
		model.addAttribute("formStt", "update");
		return "views/admin/products/product-form";
	}

	@PostMapping("/save-or-update")
	public String saveOrUpdate(@RequestParam("image") MultipartFile multipartFile,
			@ModelAttribute ProductInfo productInfo, BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest request) {

		String typeCss = "error";
		String message = msg_error_save;

		// Save image to folder
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = "/assets/images";
		String realPathtoUpload = request.getServletContext().getRealPath(uploadDir);
		try {
			ImageFileUtils.saveFile(realPathtoUpload, fileName, multipartFile);
		} catch (IOException e) {
			logger.info("Error in save image"+e);
		}

		// Save product
		int id = 0;
		Product product = new Product();
		boolean isCreate = true;
		if (productInfo.getId() != 0) {
			product = productService.findById(productInfo.getId());
			id = productInfo.getId();
			isCreate = false;
		}
		
		product =  prepareProduct(product, productInfo);

		List<String> images = productInfo.getImages();
		images.add(fileName);
		productInfo.setImages(images);
		if (product.getImage() != null) {
			product.setImage(product.getImage() + fileName + "/");
		}
		else {
			product.setImage(fileName + "/");
		}
			
		Product savedProduct = productService.saveOrUpdate(product);

		if (savedProduct != null) {
			typeCss = "success";
			message = msg_success_save;
			id = savedProduct.getId();
			return handleRedirect(redirectAttributes, typeCss, message, "/admin/products/" + id);
		}
		// fail
		if (isCreate) {
			return handleRedirect(redirectAttributes, typeCss, message, "/admin/products/new");
		}	
		return handleRedirect(redirectAttributes, typeCss, message, "/admin/products/" + id + "/edit");
	}

	@GetMapping("/{id}/toggle-status")
	public String toggleStatus(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
		String typeCss = "error";
		String message = msg_error_save;

		Product product = productService.findById(id);
		if (product.getDelete_time() == null) {
			Date date = new Date();
			product.setDelete_time(date);
		} else {
			product.setDelete_time(null);
		}

		if (productService.saveOrUpdate(product) != null) {
			typeCss = "success";
			message = msg_success_save;
		}
		return handleRedirect(redirectAttributes, typeCss, message, "/admin/products/" + id);
	}
	
	private Product prepareProduct(Product product, ProductInfo productInfo) {
		product.setCategory(categoryService.findById(productInfo.getCategoryId()));
		product.setName(productInfo.getName());
		product.setPrice(productInfo.getPrice());
		product.setQuantity(productInfo.getQuantity());
		product.setDescription(productInfo.getDescription());
		return product;
	}
}