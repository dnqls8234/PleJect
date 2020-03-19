package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.InterestDao;
import dao.RoomDaoMybatis;
import model.InterestDataBean;


@Controller
@RequestMapping("/content/")
public class MainController{
	
	
	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		
		String name = (String)session.getAttribute("name");
		System.out.println(name);
	}
	
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String content_main(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		return "content/main";
	}
	

}
