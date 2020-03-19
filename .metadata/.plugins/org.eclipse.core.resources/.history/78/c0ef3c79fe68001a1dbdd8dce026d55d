package conn;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import conn.ActionAnnotation;
import conn.RequestMapping;
import conn.RequestMapping.RequestMethod;
import dao.InterestDao;
import dao.LogonDao;
import model.InterestDataBean;
import model.LogonDataBean;

public class MemberController extends ActionAnnotation {
	
	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
	}
	
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public String member_loginForm(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		return "/view/content/loginForm.jsp";
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
		}
		request.setAttribute("check", check);
		
		return "/view/member/loginPro.jsp";
	}
	
	@RequestMapping(value = "logoutPro", method = RequestMethod.GET)
	public String member_logoutPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		session.removeAttribute("name");

		return "/view/member/logoutForm.jsp";
	}
	
	@RequestMapping(value = "signUpForm", method = RequestMethod.GET)
	public String member_signUpForm(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		return "/view/member/signUpForm.jsp";
	}
	
	@RequestMapping(value = "signUpPro", method = RequestMethod.POST)
	public String member_signUpPro(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		
		LogonDataBean member = new LogonDataBean();
		LogonDao manager = LogonDao.getInstance();
		
		member.setGender(request.getParameter("gender")); // 성별
		member.setName(request.getParameter("username")); // 닉네임
		member.setEmail(request.getParameter("email")); // 이메일
		member.setPassword(request.getParameter("password")); // 비밀번호
		member.setPhone(request.getParameter("phoneNumber")); // 전화번호
		member.setBirthday(request.getParameter("birthday")); // 생일
		member.setAddress(request.getParameter("userAddr")); // 주소
		
		manager.insertMember(member);
		session.setAttribute("name", member.getName());
		
		InterestDao service = InterestDao.getInstance();
		List<InterestDataBean> interestList = service.allgetTit();
		// System.out.println(interestList.get(0));
		request.setAttribute("interestList", interestList);
		
		return "/view/content/memberLikeForm.jsp";
	}

	
}
