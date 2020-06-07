package com.shelmark.demo.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shelmark.demo.Entity.Category;
import com.shelmark.demo.Entity.Product;
import com.shelmark.demo.Service.CategoryService;
import com.shelmark.demo.Service.ImageService;
import com.shelmark.demo.Service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	@Autowired 
	private ProductService proService;
	@Autowired 
	private CategoryService catService;
	
	@Autowired 
	private ImageService imgService;
	
	String uploadRootPath = System.getProperty("user.dir") + "/src/main/webapp/resources/static/img";
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView getProduct(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer limit) {
		if (page == null) {
			page = 1;
		}
		if (limit == null) {
			limit = 10;
		}
		ModelAndView mv = new ModelAndView();
		int start = (page - 1) * limit;
		List<Product> pros = proService.getProductsByPage(start, limit);
		mv.setViewName("productList");
		mv.addObject("pros", pros);
		return mv;
	}
	
	@RequestMapping(value = "/deletePro", method = RequestMethod.POST)
	public String deleteProduct(@RequestParam Long proId) {
		Product pro = proService.findById(proId);
		proService.delete(pro);
		return "redirect:/admin/product";
	}
	
	@RequestMapping(value = "/editPro", method = RequestMethod.GET)
	public ModelAndView editProduct(@RequestParam Long proId) {
		Product pro = proService.findById(proId);
		List<Category> cats = catService.getAllCategory();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("editPro");
		mv.addObject("pro", pro);
		mv.addObject("cats", cats);
		return mv;
	}
	
	@RequestMapping(value = "/editPro", method = RequestMethod.POST)
	public String editPro(	@RequestParam Long proId, 
							@RequestParam String proName,
							@RequestParam String proDescription,
							@RequestParam Long proPrice,
							@RequestParam Long proQuantity,
							@RequestParam Long catId,
							@RequestParam MultipartFile file
	){
		Product pro = proService.findById(proId);
		pro.setName(proName);
		pro.setDescription(proDescription);
		pro.setPrice(proPrice);
		pro.setQuantity(proQuantity);
		pro.setCat(catService.getCatById(catId));
		Date date = new Date();
		Long milis = date.getTime();
		pro.setDate(milis);
		if (!file.isEmpty()) {
			String img = imgService.uploadFile(uploadRootPath + "/product", file);
			pro.setImage("/resources/static/img/category/"+img);
		}
		proService.save(pro);
		return "redirect:/admin/product";
	}
}