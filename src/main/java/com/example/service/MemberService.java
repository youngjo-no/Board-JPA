package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.domain.MemberDTO;
import com.example.entity.MemberEntity;
import com.example.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	//회원 목록
	public Page<MemberEntity> getMemberList(Pageable pageable){
		
		return memberRepository.findAllByOrderByNumberDesc(pageable);
		
	}
	
	
	//회원 등록
	public void enroll(MemberEntity memberEntity){
		
		memberRepository.save(memberEntity);
	}
	
	
	//회원 수정
	public void modify(MemberEntity memberEntity){
			
		memberRepository.save(memberEntity);
	}
	
	
	//회원 삭제
	public void delete(MemberEntity memberEntity){
				
		memberRepository.delete(memberEntity);
		
	}
	
	
	//회원 상세보기
	public MemberEntity getDetail(String number){
			
		return memberRepository.findByNumber(number);
			
	}
	
}
