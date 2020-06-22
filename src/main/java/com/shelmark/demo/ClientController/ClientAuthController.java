package com.shelmark.demo.ClientController;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shelmark.demo.SetupSession;
import com.shelmark.demo.Entity.Order;
import com.shelmark.demo.Entity.Order_Detail;
import com.shelmark.demo.Entity.Product;
import com.shelmark.demo.Entity.ShoppingCart;
import com.shelmark.demo.Entity.User;
import com.shelmark.demo.Repository.OrderDetailRepository;
import com.shelmark.demo.Repository.ShoppingCartRepository;
import com.shelmark.demo.Service.OrderService;
import com.shelmark.demo.Service.ProductService;
import com.shelmark.demo.Service.ShoppingCartService;
import com.shelmark.demo.Service.UserService;

@Controller
@RequestMapping("/auth")
public class ClientAuthController {
	@Autowired
	private ProductService proService;

	@Autowired
	private ShoppingCartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCart(@RequestParam Long quantity, @RequestParam Long proId, HttpServletRequest request) {
		Product pro = proService.findById(proId);
		ShoppingCart cart = new ShoppingCart();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = userService.findByUsername(u.getUsername());
		String referer = request.getHeader("Referer");
		for (ShoppingCart c : user.getCartItems()) {
			if (c.getProduct().equals(pro)) {
				c.setQuantity(c.getQuantity() + quantity);
				cart.setDate();
				cartService.save(c);
				return "redirect:" + referer;
			}
		}
		cart.setUser(user);
		cart.setDate();
		cart.setQuantity(quantity);
		cart.setProduct(pro);
		cart.setTotal();
		cartService.save(cart);
		SetupSession.setHeader(user.getCartItems().size() + 1, user.getProLiked().size(), request);
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public ModelAndView viewCart(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = userService.findByUsername(u.getUsername());
		mv.addObject("user", user);
		mv.setViewName("cart");
		return mv;
	}

	@RequestMapping(value = "/deleteCartItem", method = RequestMethod.GET)
	public String deleteCartItem(@RequestParam Long itemId, HttpServletRequest request) {
		ShoppingCart cart = cartService.findById(itemId);
		cartService.delete(cart);
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = userService.findByUsername(u.getUsername());
		SetupSession.setHeader(user.getCartItems().size() - 1, user.getProLiked().size(), request);
		return "redirect:/auth/viewCart";
	}

	@RequestMapping(value = "/autoSaveCart", method = RequestMethod.POST)
	public String saveCart(@RequestParam List<Long> cart, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = userService.findByUsername(u.getUsername());
		int i = 0;
		for (ShoppingCart item : user.getCartItems()) {
			item.setQuantity(cart.get(i));
			item.setTotal();
			cartService.save(item);
			i++;
		}
		return "redirect:/auth/viewCart";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public ModelAndView checkout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = userService.findByUsername(u.getUsername());
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("checkout");
		return mv;
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String submitOrder(@RequestParam String fullname, 
			@RequestParam String city, 
			@RequestParam String district,
			@RequestParam String address, 
			@RequestParam String phone, 
			@RequestParam List<Long> proId,
			@RequestParam List<Long> quantity,
			@RequestParam String payment,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		User user = userService.findByUsername(u.getUsername());
		System.out.print(payment);
		//set Order
		Order order = new Order();
		order.setStatus(0);
		order.setFullname(fullname);
		order.setAddress(city + " " + district + " " + address);
		order.setPhone(phone);
		order.setUser(user);
		order.setDate();
		order.setShipping((long) 30);
		order.setDiscount((long) 0);
		order.setAdditionalFees((long) 0);
		orderService.save(order);
		List <Product> pros = proService.getListByListId(proId);
		Long subTotal=(long) 0;
		//set Order_Detail
		for (int index = 0; index<pros.size(); index++) {
			Order_Detail orderDetail = new Order_Detail();
			orderDetail.setOrder(order);
			orderDetail.setProduct(pros.get(index));
			orderDetail.setQuantity(quantity.get(index));
			orderDetail.setSubTotal();
			subTotal+=orderDetail.getQuantity()*orderDetail.getProduct().getPrice();
			orderDetailRepo.save(orderDetail);
		}
		order.setSubTotal(subTotal);
		order.setGrandTotal();
		orderService.save(order);
		Set<ShoppingCart> cartItems = user.getCartItems();
		for(Product p : pros) {
			for (ShoppingCart cartItem: cartItems) {
				if (cartItem.getProduct().equals(p)) {
					cartService.delete(cartItem);
					cartItems.remove(cartItem);
					break;
				}
			}
		}
		SetupSession.setHeader(cartItems.size(), user.getProLiked().size(), request);
		return "redirect:/";
	}
}
