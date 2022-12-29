package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.DataTableDTO;
import com.example.domain.MemberDTO;
import com.example.entity.MemberEntity;
import com.example.service.MemberService;

@Controller
public class BoardController {
	
	@Autowired
	private MemberService memberService;
	
	//메인화면
	@RequestMapping("/board/view")
	public String view() {
		
		return "board/list";
		
	}
	
	//회원 목록 출력
	@RequestMapping("/board/list")
	public ResponseEntity<Object> list(@RequestParam(value="draw", required = false, defaultValue = "1")int draw, Pageable pageable) {
																				//	Ajax ResponseEntity로 받고 페이징을 위한 파라미터 받기
		Page<MemberEntity> pages = memberService.getMemberList(pageable);      // 회원 목록 받기
		DataTableDTO<MemberEntity> dataPage = new DataTableDTO<>();            // DataTableDTO 형식의 dataPage 생성
		List<MemberEntity> content = pages.getContent();                       // pages의 정보를 가져오는 List 형식의 content 생성
		
		// DTO에 담아서 출력하기 위해
		dataPage.setData(content);                                        // dataPage에 content 저장
		dataPage.setDraw(draw);                                          // dataPage에 draw 저장
		dataPage.setRecordsTotal(pages.getTotalElements());          // dataPage에 총 레코드 수 저장
		dataPage.setRecordsFiltered(pages.getTotalElements());          // dataPage에 필터링 후의 총 레코드 수 저장
		
		return new ResponseEntity<>(dataPage, HttpStatus.OK);         // dataPge 반환
		
	}
	
	//회원 등록
	@RequestMapping("/board/enroll")
	public ResponseEntity<String> enroll(MemberEntity memberEntity) {
			
		memberService.enroll(memberEntity);
		
		return ResponseEntity.ok("등록완료");
			
	}
	
	
	//회원 수정
	@RequestMapping("/board/modify")
	public ResponseEntity<String> modify(MemberEntity memberEntity) {
				
		memberService.modify(memberEntity);
			
		return ResponseEntity.ok("수정완료");
				
	}
	
	
	//회원 삭제
	@RequestMapping("/board/delete")
	public ResponseEntity<String> delete(MemberEntity memberEntity) {
					
		memberService.delete(memberEntity);
				
		return ResponseEntity.ok("삭제완료");
					
	}
	
	
	//회원 상세보기
	@RequestMapping("/board/detail")
	public ResponseEntity<Map<String, Object>> detail(String number) {
				
		Map<String, Object> result = new HashMap<>();
		result.put("detail", memberService.getDetail(number));
		
		return ResponseEntity.ok(result);
		
	}
	
}
