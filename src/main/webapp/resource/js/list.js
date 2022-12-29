/**
 * 
 */
$(function(){
   $("#list-div").show();   // 먼저 실행시켜줌으로서 회원 등록 가리고 회원 목록만 띄움
   $("#enroll").hide();
   $("#enrollTitle").hide();
   $("#detail").hide();
   $("#modifypage").hide();
   
   //등록버튼 실행시
   $('#enrollBtn').click(function() {   // 회원 목록 가리고 회원 등록 띄움
	    $("#listTitle").hide();
	    $("#enrollBtn").hide();
	    $("#list-div").hide();
	    $("#enroll").show();
	    $("#enrollTitle").show();
	});
	
	//상세보기에서 수정버튼 실행시
   $('#modifyPageBtn').click(function() { 
		$("#detail").hide();
	    $("#modifypage").show();
	});
	
	//datatable
	$('#list').DataTable().destroy();   
	$('#list').DataTable({
      paging : true,                // paging 사용여부
      searching : false,            // 검색 기능 사용여부
      info : true,                  // 정보 표시
      autoWidth : false,            // 가로 자동
      responsive : false,            // 웹사이트 길이에 따라 컬럼이 자동적으로 숨겨지지만 숨겨진 컬럼을 펼쳐서 볼 수 있음
      lengthChange : false,    		// 페이지 조회 시 row를 변경할 것인지..
      pageLength : 5,        		// 처음에 띄울 row의 수
//      lengthMenu : [5, 10, 15],	// lengthChange true라면 고를 수 있는 row의 수들
      ordering : false,              // 게시물의 정렬 기능 사용 여부
	  destroy : true,               // 테이블 재생성을 위해
	  columns:[                              // 테이블에 매핑할 리턴 파라미터명 순서
			{title : 'name', data : 'name'},
			{title : 'tel', data : 'tel'}, 
			{title : 'age', data : 'age'}
		],
	  processing : true,           // 서버와 통신 시 응답을 받기 전이라는 ui를 띄울 것인지의 여부
	  serverSide : true,           // serverside 사용 여부
	  ajax: {
	   url: "/board/list",
	   type : "get",
	   data: function(data){
		
		console.log('test1-->' + data.start);
		console.log('test2-->' + data.legnth);
		console.log('test3-->' + parseInt(data.start / data.length));
		
		return{
			"page" : parseInt(data.start / data.length),
			"size" : data.length,
			"draw" : data.draw,
		};
		},
	  },	
	});
	
	
	// 데이터 테이블 tr 클릭 이벤트
	$("#list").on('click', 'tbody tr', function () {

	    var row = $("#list").DataTable().row($(this)).data();
	    
	    $("#list-div").hide();
	    $("#listTitle").hide();
	    $("#detail").show();
	    
	    $.ajax({
	           type: "post",
	           url: "/board/detail",
	           data : row,
	           success : function(data){
					console.log("data >> " , data.detail);
					let detail = data.detail;
					$('#detail-name').text(detail.name); // 화면에 값 출력
					$('#detail-tel').text(detail.tel);
					$('#detail-age').text(detail.age);
					$('#modifyName').val(detail.name);  // value 값으로 뜨게
					$('#modifyTel').val(detail.tel);
					$('#modifyAge').val(detail.age);
					$('#modifyNum').val(detail.number);
					
				},
				error : function(error){
					console.log("error >> " , error);
				}
	       });
	 });

	
	// 등록 버튼 ajax
	$('#enBtn').on("click",function () {  // buttom id

       var form = $("#enroll").serialize();   // form id

       console.log('form--->' + form);
       $.ajax({
           type: "post",
           url: "/board/enroll",    // submit 방식에서 action
           data: form,
           dataType: 'text',        // Stirng으로 받아서 json 대신 text 사용
           success: function (data) {
               alert("등록완료");
               console.log(data);
               location.href = '/board/view' // 리스트 페이지로 돌아감
           },
           error: function (request, status, error) {
               console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

           }
       });
    });
   
   
   // 수정 버튼 ajax
	$('#modifyBtn').on("click",function () {  // buttom id

       var form = $("#modifypage").serialize();   // form id

       $.ajax({
           type: "post",
           url: "/board/modify",    // submit 방식에서 action
           data: form,
           dataType: 'text',        // Stirng으로 받아서 json 대신 text 사용
           success: function (data) {
               alert("수정 완료");
               console.log(data);
               location.href = '/board/view' // 리스트 페이지로 돌아감
           },
           error: function (request, status, error) {
               console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

           }
        });
    });
   
   // 삭제 버튼 ajax
	$('#delBtn').on("click",function () {  // buttom id
	
	   var form = $("#modifypage").serialize();
	   
       $.ajax({
           type: "post",
           url: "/board/delete",    // submit 방식에서 action
           data: form,
           success: function (data) {
               alert("삭제 완료");
               console.log(data);
               location.href = '/board/view' // 리스트 페이지로 돌아감
           },
           error: function (request, status, error) {
               console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

           }
        });
    });
   
   
})

