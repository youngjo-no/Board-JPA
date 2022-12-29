package com.example.domain;
import lombok.Data;

@Data
public class MemberDTO extends DataTableDTO{
	
	private String number;
	private String name;
	private String tel;
	private String age;

}
