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

import dao.MypageDao;
import model.MyPageDataBean;


@Controller
@RequestMapping("/mypage/")
public class MypageController{

	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "mypage", method = RequestMethod.GET)
	public String Room_myPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		MypageDao service = MypageDao.getInstance();
		String email = (String) session.getAttribute("memEmail");
		List<MyPageDataBean> li = service.getMyRoomList(email);
		List<Integer> nowcnt = service.getCount(email);
		request.setAttribute("nowcnt", nowcnt);
		request.setAttribute("li", li);
		
		return "content/mypage/mypage";
	}
	

}
