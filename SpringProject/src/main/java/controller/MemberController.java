package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.InterestDao;
import dao.LogonDao;
import model.InterestDataBean;
import model.LogonDataBean;


@Controller
@RequestMapping("/member/")
public class MemberController{
	
	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
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
		HttpSession session = request.getSession();
		return "member/signUpForm";
	}
	
//	@RequestMapping(value = "signUpPro", method = RequestMethod.POST)
//	public String member_signUpPro(HttpServletRequest request, HttpServletResponse res) throws Exception {
//		HttpSession session = request.getSession();
//		
//		LogonDataBean member = new LogonDataBean();
//		LogonDao manager = LogonDao.getInstance();
//		
//		String realFolder = "";
//		String saveFolder = "uploadFile";
//		String encType = "UTF-8";
//		int maxSize = 10*1024*1024;
//		ServletContext context = getServletContext();
//		realFolder = context.getRealPath(saveFolder);
//		try {
//			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
//			Enumeration files = multi.getFileNames();
//			if(files.hasMoreElements()) {
//				System.out.println("──────────────1");
//				String name = (String)files.nextElement();
//				System.out.println(name + " ──────────────2");
//				File file = multi.getFile(name);
//				System.out.println(file + " ──────────────3");
//			if (file != null) {
//				member.setProfileImg(file.getName());
//			} else {
//				member.setProfileImg("");
//			}
//			}
//			member.setGender(multi.getParameter("gender"));
//			member.setName(multi.getParameter("username"));
//			member.setEmail(multi.getParameter("email"));
//			member.setPassword(multi.getParameter("password"));
//			member.setPhone(multi.getParameter("phoneNumber"));
//			member.setBirthday(multi.getParameter("birthday"));
//			member.setAddress(multi.getParameter("userAddr"));
//		
//			manager.insertMember(member);
//				
//		} catch (Exception e ) {
//			e.printStackTrace();
//		}
//		
//		session.setAttribute("name", member.getName());
//		
//	
//		return "redirect:/memberAction/memberLikeForm";
//	}

}
