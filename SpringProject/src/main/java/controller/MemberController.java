package controller;

import java.io.UnsupportedEncodingException;
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
import dao.LogonDao;
import dao.MybatisLogonDaoOracle;
import model.InterestDataBean;
import model.LogonDataBean;


@Controller
@RequestMapping("/member/")
public class MemberController{
	
	
	
	@Autowired
	MybatisLogonDaoOracle service;
	
	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		String asdasdsad;
	}
	
	@RequestMapping(value = "loginPro", method = RequestMethod.POST)
	public String member_loginPro(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		LogonDao manager = LogonDao.getInstance();
		
		String memEmail = request.getParameter("email");
			int idx = memEmail.indexOf("@");
		String memEmail2 = memEmail.substring(0, idx);
		System.out.println(memEmail2);
		String memPassword = request.getParameter("password");
		System.out.println(memEmail);
		System.out.println(memPassword);
		int check = manager.userCheck(memEmail, memPassword);
		if (check == 1) {
			String name = manager.findName(memEmail);
			session.setAttribute("name", name);
			session.setAttribute("memEmail", memEmail);
		}
		request.setAttribute("check", check);
		
		return "member/loginPro";
	}
	
	
	@RequestMapping(value = "logoutPro", method = RequestMethod.GET)
	public String member_logoutPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		session.invalidate();

		return "member/logoutForm";
	}
	
	@RequestMapping(value = "signUpForm", method = RequestMethod.GET)
	public String member_signUpForm(HttpServletRequest request, HttpServletResponse res) throws Exception {
		
		
		return "member/signUpForm";
	}
	
	@RequestMapping(value = "signUpPro", method = RequestMethod.POST)
	public String member_signUpPro(LogonDataBean logon){
		
		System.out.println(logon);
		logon.setProfileImg("null");
		service.insertMember(logon);
		
		
		return "redirect:/memberAction/memberLikeForm";
	}

}
