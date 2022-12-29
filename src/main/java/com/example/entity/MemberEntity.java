package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity                   // 테이블마다 1개씩 생성
@Table(name="table1")     // 테이블 이름
public class MemberEntity{
	
	@Id               // primary key @Id로
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)        // auto increment
	private String number;
	
	@Column
	private String name;
	
	@Column
	private String tel;
	
	@Column
	private String age;
	

}
