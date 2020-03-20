package controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import dao.MybatisInterestDaoOracle;
import dao.MybatisLogonDaoOracle;
import dao.RoomDaoMybatis;
import model.InterestDataBean;
import model.LogonDataBean;
import model.RoomDataBeanaaa;
import model.RoomDataBean;

@Controller
@RequestMapping("/room/")
public class RoomController {

	private String email;
	private HttpSession session;
	@Autowired
	RoomDaoMybatis service;
	@Autowired
	MybatisInterestDaoOracle Interservice;
	@Autowired
	MybatisLogonDaoOracle Logonservice;

	@ModelAttribute
	public void initProcess(HttpServletRequest request) {
		this.session = request.getSession();

		this.email = (String) session.getAttribute("memEmail");
		if (email == null) {
			email = "null";
		}
	}

	@RequestMapping(value = "roomWriteForm", method = RequestMethod.GET)
	public String Room_writeFrom(Model m, @ModelAttribute("rm") RoomDataBean rm) {

		LogonDataBean member = Logonservice.getUser(email);
		List<InterestDataBean> li = Interservice.allgetTit();

		m.addAttribute("member", member);
		m.addAttribute("li", li);
		m.addAttribute("size",li.size());
		return "content/room/roomWriteForm";
	}

	@RequestMapping(value = "roomWritePro", method = RequestMethod.POST)
	public String Room_writePro(MultipartHttpServletRequest mutipart, RoomDataBeanaaa room) throws Exception {
		
		System.out.println(room);
		MultipartFile multi = mutipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();

		if (filename != null && !filename.equals("")) {
			String uploadPath = mutipart.getRealPath("/") + "/uploadFile";
			System.out.println(uploadPath);

			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadPath + "/" + multi.getOriginalFilename()));

			// room.setPhoto(filename);

		} else {
			// room.setPhoto("");
		}

		// service.RoomInsert(room);

		return "redirect:/room/roomList";
	}

	@RequestMapping(value = "roomList", method = RequestMethod.GET)
	public String Room_List(Model m) {

		List<RoomDataBean> member = service.getRoomList();
		// InterestDao Inservice = InterestDao.getInstance();
		// List<InterestDataBean> li = Inservice.allgetTit();

		m.addAttribute("li", member);

		return "content/room/roomlist";
	}

	@RequestMapping(value = "roomcontent", method = RequestMethod.GET)
	public String Room_Content(int num, Model model) {

		RoomDataBean room = service.getRoom(num);
		// InterestDao Inservice = InterestDao.getInstance();
		// List<InterestDataBean> li = Inservice.allgetTit();

		int check = service.check(num, email);
		model.addAttribute("check", check);
		model.addAttribute("room", room);

		return "content/room/roomcontent";
	}

//	@RequestMapping(value = "roomcontent", method = RequestMethod.POST)
//	public String Room_Content1(int num, Model m) {
//
//		RoomDataBean room = service.getRoom(num);
//		// InterestDao Inservice = InterestDao.getInstance();
//		// List<InterestDataBean> li = Inservice.allgetTit();
//
//		int check = service.check(num, email);
//
//		m.addAttribute("room", room);
//		m.addAttribute("check", check);
//
//		return "content/room/roomcontent";
//	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String regi(HttpServletRequest request) {
		HttpSession session = request.getSession();

		RoomDataBean room = new RoomDataBean();
		System.out.println(email + "@@@@@email@@@@@@@@@@@@@");
		if (email == null || email.equals("null") || email == "null") {
			return "redirect:/memberAction/loginForm";
		}
		int num = Integer.parseInt(request.getParameter("num"));
		room.setNum(Integer.parseInt(request.getParameter("num")));
		room.setLike_ca(Integer.parseInt(request.getParameter("like_ca")));
		room.setMembercnt(Integer.parseInt(request.getParameter("membercnt")));
		room.setMeet_title(request.getParameter("meet_title"));
		int check = Integer.parseInt(request.getParameter("check"));

		if (check == 0) {
			service.RoomJoin(room, email, 3);
		} else {
			service.RoomOut(email, num);
		}

		return "redirect:/room/roomcontent?num=" + num;
	}

}
