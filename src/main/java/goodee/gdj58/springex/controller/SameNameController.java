package goodee.gdj58.springex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import goodee.gdj58.springex.vo.Person;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SameNameController {
	@GetMapping("/checkbox")
	public String checkbox() {
		return "checkbox";
	}
	@PostMapping("/checkbox")
	public String checkbox(@RequestParam(value="hobby", required=false) String[] hobby) { //required=false 값이 넘어오지 않을 수도 있다
		// 요청페이지에서 hobby 체크박스가 체크되지 않으면 null
		if(hobby != null) {
			log.debug("\u001B[31m"+"hobby.length: "+hobby.length);
		} else {
			log.debug("\u001B[31m"+"hobby is null");
		}
		return "";
	}
	
	@PostMapping("/inputName")
	public String inputName(@RequestParam(value="nick") String[] nick) { //required=false 값이 넘어오지 않을 수도 있다
		for(String s : nick) {
			log.debug("\u001B[31m"+s);
		}
		return "";
	}
	
	@PostMapping("/command")
	public String command(Person person) {
		log.debug(person.toString()); // System.out.println(객체) -> 자동으로 toString method 분기코드가 있어서 자동으로 실행됨
		return "";
	}
}
