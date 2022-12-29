//package com.example.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class PagingEntity {
//	
//	@Builder.Default
//    private int draw = 1;//이 개체가 응답하는 그리기 카운터-데이터 요청의 일부로 전송 된 그리기 매개 변수
//    @Builder.Default
//    private int start = 0; //페이징 첫 번째 레코드 표시기. 이것은 현재 데이터 세트의 시작점
//    @Builder.Default
//    private int length = 10; //테이블이 현재 그리기에서 표시 할 수있는 레코드 수
//    @Builder.Default
//    private int page = 0; //페이징수
//
//    public int getPage(){
//        page = (start/length);
//        return page;
//    }
//
//
//}
