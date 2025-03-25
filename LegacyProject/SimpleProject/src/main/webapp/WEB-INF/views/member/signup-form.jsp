<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    <style> 
        .content { 
            background-color:rgb(247, 245, 245);
            width:80%; 
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }
    </style>
</head>
<body>
    
    <!-- 메뉴바 -->
    <jsp:include page="../include/header.jsp" />
    
    	<!-- 
    		 사용자가 아이디를 입력하는 input요소에 무언가 값을 입력할 때 마다
    		 아이디가 중복인지 검사해서 출력해주기
    	-->
    	
    	
    <script>
    	window.onload = function (){
    		
    		//console.log([] == '');
    		
    		// JavaScript
    		
    		
    		
	    	const inputEl = document.querySelector('#signup-form > #userId');
	    	//console.log(inputEl);
	    	
	    	inputEl.addEventListener('keyup', () => {
	    		
				const inputValue = inputEl.value;
				// console.log(inputValue);
				
				if(inputValue.length >= 5){
					
					$.ajax({
						url : `id-check?memberId=\${inputValue}`,
						type : 'GET',
						success : function(result){
							
							//console.log(result);
							
							// NNNNN / NNNNY
							// 없을 때 / 있을 때
							const responseData = result.substr(4);
							//console.log(responseData);
							
							if(responseData === 'Y'){
								
								$('#check-result').show()
												  .css('color', 'crimson')
												  .text('사용할 수 없는 아이디입니다.');
							} else{
								
								$('#check-result').show()
												  .css('color', 'lightgreen')
												  .text('정말 멋진 아이디네요!!');
							}
							
						}						
					});
					
				} else{
					$('#check-result').hide();
				}
	    		
	    	});
	    	
    	}
    
    </script>

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="signup" method="post">
                <div class="form-group" id="signup-form">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="memberId" required> <br>
                    <div id="check-result" style="font-size:0.7em; display:none;"></div>
                    <br/>

                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="Please Enter Password" name="memberPw" required> <br>

                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" placeholder="Please Enter Name" name="memberName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" placeholder="Please Enter Email" name="email"> <br>

                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary">회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>

    </div>

    <!-- 푸터바 -->
    <jsp:include page="../include/footer.jsp" />

</body>
</html>