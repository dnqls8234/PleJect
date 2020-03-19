package conn;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import conn.RequestMapping.RequestMethod;
import dao.InterestDao;
import dao.LogonDao;
import dao.RoomDao;
import model.InterestDataBean;
import model.LogonDataBean;
import model.RoomDataBean;
import conn.RequestMapping;

public class RoomController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse reponse) {
		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "roomWriteForm", method = RequestMethod.GET)
	public String Room_writeFrom(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		LogonDao service = LogonDao.getInstance();
		System.out.println((String)session.getAttribute("memEmail")+"@@@@@@@@@@@@");
		LogonDataBean member = service.getUser((String)session.getAttribute("memEmail"));
		InterestDao Inservice = InterestDao.getInstance();
		List<InterestDataBean> li = Inservice.allgetTit();
		
		request.setAttribute("member", member);
		request.setAttribute("li", li);
		
		return "/view/content/room/roomWriteForm.jsp";
	}
	
	@RequestMapping(value = "roomWritePro", method = RequestMethod.POST)
	public String Room_writePro(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String realFolder="";
		String saveFolder="uploadFile";
		String encType="UTF-8";
		int maxSize = 10*1024*1024;
		ServletContext context = getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		try{
			MultipartRequest multi = new MultipartRequest(request, realFolder,maxSize,encType, new DefaultFileRenamePolicy());
			RoomDataBean Room = new RoomDataBean();
			Enumeration files = multi.getFileNames();
			
			if(files.hasMoreElements()){
				String name = (String)files.nextElement();
				File file = multi.getFile(name);
				if(file !=null){
					Room.setPhoto(file.getName());
				}else{
					Room.setPhoto("");
				}
				
			}
			
			
			
			Room.setContent(multi.getParameter("content"));
			Room.setHost(multi.getParameter("host"));
			Room.setLike_ca(Integer.parseInt(multi.getParameter("like_ca")));
			Room.setLike_sub(Integer.parseInt(multi.getParameter("like_sub")));
			Room.setLocation(multi.getParameter("location"));
			Room.setMeet_title(multi.getParameter("meet_title"));
			Room.setMembercnt(Integer.parseInt(multi.getParameter("membercnt")));
			RoomDao service = RoomDao.getInstance();
			System.out.println(multi.getParameter("email")+"email 뭐임?");
			service.RoomJoin(Room, multi.getParameter("email"), 1);
			service.RoomInsert(Room);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return "redirect:/room/roomList";
	}
	
	
	@RequestMapping(value = "roomList", method = RequestMethod.GET)
	public String Room_List(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		RoomDao service = RoomDao.getInstance();
		List<RoomDataBean> member = service.getRoomList();
//		InterestDao Inservice = InterestDao.getInstance();
//		List<InterestDataBean> li = Inservice.allgetTit();
		
		request.setAttribute("li", member);
		
		return "/view/content/room/roomlist.jsp";
	}
	
	
	@RequestMapping(value = "roomcontent", method = RequestMethod.GET)
	public String Room_Content(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		RoomDao service = RoomDao.getInstance();
		RoomDataBean room = service.getRoom(Integer.parseInt(request.getParameter("num")));
//		InterestDao Inservice = InterestDao.getInstance();
//		List<InterestDataBean> li = Inservice.allgetTit();
		String email = (String)session.getAttribute("memEmail");
		int check = service.check(Integer.parseInt(request.getParameter("num")),email);
		request.setAttribute("check", check);
		request.setAttribute("room", room);

		return "/view/content/room/roomcontent.jsp";
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
		
		return "/view/content/room/roomcontent.jsp";
	}
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public String regi(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
		RoomDataBean room = new RoomDataBean();
		String email = (String)session.getAttribute("memEmail");
		System.out.println(email+"이거 값이 뭐에요 ?");
		if(email==null || email.equals("")){
			return "redirect:/member/loginForm";
		}
		int num = Integer.parseInt(request.getParameter("num"));
		room.setNum(Integer.parseInt(request.getParameter("num")));
		room.setLike_ca(Integer.parseInt(request.getParameter("like_ca")));
		room.setMembercnt(Integer.parseInt(request.getParameter("membercnt")));
		room.setMeet_title(request.getParameter("meet_title"));
		RoomDao service = RoomDao.getInstance();
		int check = Integer.parseInt(request.getParameter("check"));
		
		if(check==0){
		service.RoomJoin(room, email, 3);
		}else{
			service.RoomOut(email, num);
		}
		
		return "redirect:/room/roomcontent?num="+num;
	}
	
}
