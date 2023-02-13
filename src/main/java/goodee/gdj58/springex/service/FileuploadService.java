package goodee.gdj58.springex.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import goodee.gdj58.springex.vo.MemberForm;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional // db세팅이 되어야지만 쓸 수 있는 애노테이션
@Slf4j
public class FileuploadService {
	public void addMember(MemberForm memberForm) {
		// 1+2+3+4 -> 트랜잭션 처리
		
		// Member테이블(1) : (N)pic테이블
		
		// 1. member mapper 호출(insert -> key 반환)
		int memberNo = 0; // insert후 key 반환값
		
		// 2. 파일업로드
		List<MultipartFile> pic = memberForm.getPic();
		log.debug("in for...");
		if(pic != null) { // 하나이상의 업로드할 파일이 있다
			//UUID uuid = UUID.randomUUID(); // 랜덤 문자열 생성시 많이 사용하는 API
			//String newName = uuid.toString(); 
			for(MultipartFile mf : pic) {
				// 3. 파일 저장
				String originName = mf.getOriginalFilename(); // 확장자 포함된 이름
				log.debug(originName);
				String newName = UUID.randomUUID().toString().replace("-", ""); // 확장자 미포함
				String ext = originName.substring(originName.lastIndexOf(".")+1); // 마지막에 있는 . 위치를 구함
				String newFullName = memberForm.getPath()+newName+"."+ext;
				log.debug(newFullName);
				File f = new File(newFullName); // 풀네임으로 빈 파일을 생성
				// 빈 파일에 mf안의 업로드 된 파일을 복사
				try {
					mf.transferTo(f); // 내부적으로 관리해야함 catch쓰면 안됨
				} catch(Exception e) {
					e.printStackTrace();
					// try... catch절이 필요로 하지 않는 runtimeException을 발생시켜서 ]
					// 애노테이션 Transactinal이 감지하여 tollback할 수 있도록..
					throw new RuntimeException(); // 코드에는 감지되지 않는 예외 생성 RuntimeException try/catch절을 필요로 하지 않는다 그래서 이 페이지의 트랜잭션이 실행됨
				}
				// 4. 파일정보를 pic테이블에 저장
			}
		}
		// 2. mapper 호출
	}
}
