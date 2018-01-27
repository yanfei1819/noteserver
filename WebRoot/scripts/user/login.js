$(function(){
	//给登录按钮绑定单击处理
	$("#login").click(function(){
	    //如果有上次信息清除
	    $("#name_error").html("");
	    $("#pwd_error").html("");
	    //获取用户名和密码
	    var name = $("#name").val().trim();
	    var password = $("#password").val().trim();
	    //对数据进行格式检查
	    var check = true;//表示通过检查
	    if(name == ""){
	        $("#name_error").html("用户名不能为空");
	        check = false;
	    }
	    if(password == ""){
	         $("#pwd_error").html("密码不为空");
	         check = false;
	    }
	    //通过数据格式检查,再发送Ajax请求
	  if(check){
		 //将用户名和密码拼成字符串
	    var msg = name+":"+password;
	    //对信息进行Base64处理
	    var base64_msg = Base64.encode(msg);
	    //将信息存入header发送请求
	    $.ajax({
	    	url:"http://localhost:8080/noteserver/user/login.form",
	    	type:"post",
	    	dataType:"json",
	    	beforeSend:function(xhr){
	    		xhr.setRequestHeader("Authorization","Basic "+base64_msg);
	    	},
	    	success:function(result){//result服务器返回的json结果
	              //根据结果的status处理
	              if(result.status=="0"){//成功
	                    //获取用户ID和令牌
	                    var token = result.data.token;
	                    var userId = result.data.userId;
	                    //存入Cookie
	                    addCookie("token",token,2);
	                    addCookie("userId",userId,2);
	                    window.location.href="edit.html";//进入edit.html
	              }else if(result.status=="1"){//用户名错
	                    $("#name_error").html(result.msg);
	              }else if(result.status=="2"){//密码错
	                    $("#pwd_error").html(result.msg);
	              }
	          }
	    });
	    }
	});
});