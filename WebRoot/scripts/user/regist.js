	//追加注册按钮事件处理
    $(function(){
       $("#regist_button").click(function(){
            //获取数据
            var username = $("#regist_username").val();
            var nickname = $("#nickname").val();
            var password = $("#regist_password").val();
            //TODO 检查数据格式
            //发送ajax请求
            $.ajax({
               url:"http://localhost:8080/noteserver/user/regist.form",
               data:{"cn_user_name":username,
               			"cn_user_desc":nickname,
               			"cn_user_password":password},
               	dataType:"json",
               	type:"post",
               	success:function(result){
               		if(result.status=="1"){//用户名被占用
               		     $("#warning_1").html(result.msg).show();
               		}else if(result.status=="0"){//成功
               		     alert("恭喜您，注册成功");
               		     $("#back").click();//触发返回按钮的单击事件
               		}
               	},
               	error:function(){
               	   alert("很遗憾,注册失败");
               	}
            
            });
       });
    });