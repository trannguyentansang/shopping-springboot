package com.shelmark.demo.Controller;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shelmark.demo.Entity.Permission;
import com.shelmark.demo.Entity.User;
import com.shelmark.demo.Service.PermissionService;
import com.shelmark.demo.Service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService perService;
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpSession session, HttpServletRequest request) {
		if(request.getHeader("referer")!=null) {
			String referer = request.getHeader("referer");
			if (!referer.contains("login")&&!referer.contains("register")) {
				session.setAttribute("referer", referer);
			}
			else {
				session.setAttribute("referer", "/");
			}
		}
		else {
			session.setAttribute("referer", "/");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");
		return mv;
	}
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView createAcc(@RequestParam String phone, 
			@RequestParam String password,  
			@RequestParam String gender,
			@RequestParam String email,
			@RequestParam String fullname
		) {
		ModelAndView mv = new ModelAndView();
		User u = userService.findByUsername(phone);
		if (u!=null) {
			mv.setViewName("register");
			mv.addObject("message", "Phone number is used!");
			return mv;
		}
		User user = new User();
		password = passwordEncoder.encode(password);
		user.setPassword(password);
		user.setUsername(phone);
		user.setFullname(fullname);
		user.setEmail(email);
		user.setPhone(phone);
		user.setGender(gender);
		Date date= new Date();
		user.setDate(date.getTime());
		user.setImage("/resources/static/img/avatar/"+gender+".png");
		Set<Permission> pers = user.getPermissions();
		pers.add(perService.findByName("USER").get(0));
		user.setPermissions(pers);
		userService.save(user);
		mv.setViewName("login");
		return mv;
	}
}
