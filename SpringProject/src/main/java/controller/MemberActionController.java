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
import dao.MybatisInterestDaoOracle;
import model.InterestDataBean;

@Controller
@RequestMapping("/memberAction/")
public class MemberActionController{
		

	@Autowired
	MybatisInterestDaoOracle service;
	
	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String name = (String)session.getAttribute("name");
		System.out.println(name);
	}
	
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String memberAction_loginForm(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		return "content/memberAction/loginForm";
	}
	
	@RequestMapping(value = "memberLikeForm", method = RequestMethod.GET)
	public String memberAction_memberLikeForm(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		InterestDao service = InterestDao.getInstance();
		List<InterestDataBean> interestList = service.allgetTit();
		// System.out.println(interestList.get(0));
		request.setAttribute("interestList", interestList);
		
		return "content/memberAction/memberLikeForm";
	}
	
	@RequestMapping(value = "memberLikeSubForm", method = RequestMethod.POST)
	public String memberAction_memberLikeSubForm(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		String[] chkbox = request.getParameterValues("likes");
		System.out.println(chkbox + "!!!!!!!!!!!!!!");
		List li = new ArrayList();
		List lii = new ArrayList();
		for( int i = 0; i < chkbox.length; i++) {
			System.out.println(chkbox[i]);
			String checklist = chkbox[i];
			List<InterestDataBean> lia = service.getLikeSub(checklist);
			lii.add(checklist);
			li.add(lia);
			System.out.println(lia.size() + " ##########");
		}
		System.out.println(li + " $$$$$$$$$$$$$$$$$$$$$");
		
		request.setAttribute("checkList", lii);
		request.setAttribute("li", li);
		
		return "content/memberAction/memberLikeSubForm";
	}
	
	@RequestMapping(value = "memberLikeSubPro", method = RequestMethod.POST)
	public String memberAction_memberLikeSubPro(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		InterestDao service = InterestDao.getInstance();
		String[] chkbox = request.getParameterValues("likes");
		String name = (String)session.getAttribute("name");
		List li = new ArrayList();
		for(int i = 0; i<chkbox.length; i++) {
			System.out.println(chkbox[i]);
			String checklist = chkbox[i];
			service.insertMemberLike(checklist, name);
		}
		
		return "content/main";
	}
	
	@RequestMapping(value = "myPage", method = RequestMethod.GET)
	public String memberAction_myPage(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
					
		return "content/memberAction/myPage";
	}

}
