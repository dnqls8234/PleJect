package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import dao.InterestDao;
import dao.LogonDao;
import dao.RoomDao;
import dao.RoomDaoMybatis;
import model.InterestDataBean;
import model.LogonDataBean;
import model.RoomDataBean;


@Controller
@RequestMapping("/room/")
public class RoomController{

	private String email;
	private HttpSession session;
	@Autowired
	RoomDaoMybatis service;
	
	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		this.session = request.getSession();
		
		this.email = (String)session.getAttribute("memEmail");
		if(email==null){
			email="null";
		}
	}

	@RequestMapping(value = "roomWriteForm", method = RequestMethod.GET)
	public String Room_writeFrom(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		LogonDao service = LogonDao.getInstance();
		System.out.println((String)session.getAttribute("memEmail")+"@@@@@@@@@@@@");
		LogonDataBean member = service.getUser((String)session.getAttribute("memEmail"));
		InterestDao Inservice = InterestDao.getInstance();
		List<InterestDataBean> li = Inservice.allgetTit();
		
		request.setAttribute("member", member);
		request.setAttribute("li", li);
		
		return "content/room/roomWriteForm";
	}
	
	@RequestMapping(value = "roomWritePro", method = RequestMethod.POST)
	public String Room_writePro(RoomDataBean room){
		
		


			
		
		
		
		return "redirect:/room/roomList";
	}
	
	
	@RequestMapping(value = "roomList", method = RequestMethod.GET)
	public String Room_List(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		RoomDao service = RoomDao.getInstance();
		List<RoomDataBean> member = service.getRoomList();
//		InterestDao Inservice = InterestDao.getInstance();
//		List<InterestDataBean> li = Inservice.allgetTit();
		
		request.setAttribute("li", member);
		
		return "content/room/roomlist";
	}
	
	
	@RequestMapping(value = "roomcontent", method = RequestMethod.GET)
	public String Room_Content(int num,Model model) {
		
		RoomDataBean room = service.getRoom(num);
//		InterestDao Inservice = InterestDao.getInstance();
//		List<InterestDataBean> li = Inservice.allgetTit();
		
		int check = service.check(num,email);
		model.addAttribute("check", check);
		model.addAttribute("room", room);

		return "content/room/roomcontent";
	}
	
	@RequestMapping(value = "roomcontent", method = RequestMethod.POST)
	public String Room_Content1(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		RoomDao service = RoomDao.getInstance();
		RoomDataBean room = service.getRoom(Integer.parseInt(request.getParameter("num")));
//		InterestDao Inservice = InterestDao.getInstance();
//		List<InterestDataBean> li = Inservice.allgetTit();
		String email = (String)session.getAttribute("memEmail");
		
		int check = service.check(Integer.parseInt(request.getParameter("num")),email);
		
		request.setAttribute("room", room);
		request.setAttribute("check", check);
		
		return "content/room/roomcontent";
	}
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public String regi(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		RoomDataBean room = new RoomDataBean();
		System.out.println(email+"@@@@@email@@@@@@@@@@@@@");
		if(email==null || email.equals("null") || email=="null"){
			return "redirect:/member/loginForm";
		}
		int num = Integer.parseInt(request.getParameter("num"));
		room.setNum(Integer.parseInt(request.getParameter("num")));
		room.setLike_ca(Integer.parseInt(request.getParameter("like_ca")));
		room.setMembercnt(Integer.parseInt(request.getParameter("membercnt")));
		room.setMeet_title(request.getParameter("meet_title"));
		int check = Integer.parseInt(request.getParameter("check"));
		
		if(check==0){
		service.RoomJoin(room, email, 3);
		}else{
			service.RoomOut(email, num);
		}
		
		return "redirect:/room/roomcontent?num="+num;
	}
	
}
