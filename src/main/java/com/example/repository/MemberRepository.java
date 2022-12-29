package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String>{

	// 회원목록 불러오기
	Page<MemberEntity> findAllByOrderByNumberDesc(Pageable pageable);
	
	// 회원 상세보기
	MemberEntity findByNumber(String number);
	
}
