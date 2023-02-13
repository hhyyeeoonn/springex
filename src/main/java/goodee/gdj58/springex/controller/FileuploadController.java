package goodee.gdj58.springex.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import goodee.gdj58.springex.service.FileuploadService;
import goodee.gdj58.springex.vo.MemberForm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileuploadController {
	@Autowired FileuploadService fileuploadService;
	
	@GetMapping("/fileupload")
	public String fileupload() {
		return "fileupload";
	}
	
	@PostMapping("/singleFileupload")
	public String singleFileupload(@RequestParam(value="name") String name
								, MultipartFile pic) {
		/*
		 	싱글파일업로드
		 	커맨드 VO객체를 이용
		 	public class Member {
		 		private String name;
		 		private MultipartFile pic;
		 	}
		 */
		
		log.debug(name);
		log.debug(pic.getOriginalFilename()+pic.getSize()); // 업로드할 때 내가 쓴 이름
		return "";
	}
	
	
	/*
	 	멀티파일업로드 : 커맨드 VO객체를 이용  서비스레이어 구현
	 	커맨드 VO객체를 이용
	 	public class Member {
	 		private String name;
	 		private List<MultipartFile> pic;
	 	}
	 */

	@PostMapping("/multiFileupload")
	public String multiFileupload(HttpServletRequest request
								, MemberForm memberForm) {
		String path = request.getServletContext().getRealPath("/upload/");
		memberForm.setPath(path);
		return "";
	}
}
