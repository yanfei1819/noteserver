

function showAddNoteWindow(){
    	//载入alert/alert_note.html
    	$("#can").load("alert/alert_note.html");
    	$("#can").show();//显示弹出框div
    	$(".opacity_bg").show();//显示灰背景色
};

//创建笔记按钮的操作
function sureAddNote(){
    	//获取客户端需要传递的数据
    	var userId = getCookie("userId");
    	var bookId = $("#book_list a[class='checked']").parent().data("bookId");
    	var noteName = $("#input_note").val().trim();
    	//TODO 检测名称格式
    	//发送ajax请求
    	$.ajax({
    		url:"http://localhost:8080/noteserver/note/add.form",
    		type:"post",
    		data:{"cn_notebook_id":bookId,
    				"cn_user_id":userId,
    				"cn_note_title":noteName},
    		dataType:"json",
    		success:function(result){//回调函数处理
    			if(result.status=="0"){//正确
    			    closePopWindow();//关闭窗口
    			    //获取笔记的ID
    			    var noteId = result.data;
    			    //将创建的笔记本添加到列表中
    			    var li = "";
				    li+="<li class='online'>";
					li+="<a>";
					li+="	<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					li+="</a>";
					li+="<div class='note_menu' tabindex='-1'>";
					li+="	<dl>";
					li+="		<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
					li+="		<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>";
					li+="		<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>";
					li+="	</dl>";
					li+="</div>";
				    li+="</li>";
					$li = $(li);//将li字符串转成jQuery对象
					$li.data("noteId",noteId);
					$("#note_list").append($li);//将li添加到笔记的ul中
    				//将当前li设置成选中
    				$("#note_list a").removeClass("checked");
    				$("#note_list li:last a").addClass("checked");
    				//将笔记标题设置到编辑窗口
    				$("#input_note_title").val(noteName);
    				//将编辑区的内容清空
    				um.setContent("");
    			}
    		}
    	});
    };

    //创建笔记操作
function saveAddNote(){
  	    //获取请求需要提交的数据
  	    var noteName = $("#input_note_title").val().trim();
  	    var noteId = $("#note_list a[class='checked']").parent().data("noteId");
  	    var noteBody = um.getContent();
  	    //TODO 格式检查（是否为空）
  		//发送Ajax请求
  		$.ajax({
  			url:"http://localhost:8080/noteserver/note/update.form",
  			type:"post",
  			data:{"cn_note_id":noteId,
  					"cn_note_title":noteName,
  					"cn_note_body":noteBody},
  			dataType:"json",
  			success:function(result){//回调处理
  				if(result.status=="0"){
  					//判断标题是否发生改变
  					var li_noteName = $("#note_list a[class='checked']").text().trim();
  					if(!(noteName==li_noteName)){
  					   //将li中的笔记标题改变
  					   var s = "	<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
  					   $("#note_list a[class='checked']").html(s);
  					}
  					//提示保存成功
  					alert(result.msg);
  				    //var msg = "<font color='red'>"+result.msg+"</font>";
  					//$("#input_note_title_msg").html(msg).hide(5000);//提示
  				}
  			}
  		});
  	};
  
  //笔记列表中li的单击处理
 function clickNote(){
  		//将点击的li设置成选中状态
  		$("#note_list li a").removeClass("checked");
  		$(this).find("a").addClass("checked");
  		//获取笔记的ID
  		var noteId = $(this).data("noteId");
  		//发送ajax请求,根据笔记的ID提取笔记信息
  		$.ajax({
  			url:"http://localhost:8080/noteserver/note/loadnote.form",
  			type:"post",
  			data:{"noteId":noteId},
  			dataType:"json",
  			success:function(result){
  				//获取返回的笔记信息显示到编辑区
  				if(result.status=="0"){
  					//获取标题
  					var noteName = result.data.cn_note_title;
  					$("#input_note_title").val(noteName);
  					//获取内容
  					var noteBody = result.data.cn_note_body;
  					um.setContent(noteBody);
  				}
  			}
  		});
  	};
    
  //笔记删除操作
function deleteNote(){
  		//获取当前选中的笔记li
  		var $li = $("#note_list a[class='checked']").parent();
  		var noteId = $li.data("noteId");//获取笔记ID
  		//发送ajax请求
  		$.ajax({
  			url:"http://localhost:8080/noteserver/note/delete.form",
  			type:"post",
  			data:{"noteId":noteId},
  			dataType:"json",
  			success:function(result){
  				if(result.status=="0"){//删除成功
  				   //将笔记li删除
  				   $li.remove();
  				   //清空编辑区显示的笔记信息
  				   $("#input_note_title").val("");
  				   um.setContent("");
  				   alert("删除成功");
  				}
  			}
  		});
  	};
 //分享笔记操作
 function shareNote(){
		//获取笔记ID
		var $li = $("#note_list a[class='checked']").parent();
  		var noteId = $li.data("noteId");//获取笔记ID
  		//发送Ajax请求
  		$.ajax({
  			url:"http://localhost:8080/noteserver/note/share.form",
  			type:"post",
  			data:{"noteId":noteId},
  			dataType:"json",
  			success:function(result){
  				if(result.status=="0"){
  				   alert("分享成功");
  				}else{
  					alert("分享失败");
  				}
  			}
  		});
	};
//搜索笔记
//为了支持火狐浏览器必须加event参数,再使用
function searchNote(event){
		var code = event.keyCode;
		if(code==13){//回车事件
			//获取要传递参数
			var title = $("#search_note").val().trim();
			//发送ajax
			$.ajax({
				url:"http://localhost:8080/noteserver/note/search.form",
				type:"post",
				data:{"title":title},
				dataType:"json",
				success:function(result){
					if(result.status=="0"){
					    $("#pc_part_2").hide();//隐藏笔记列表
						$("#pc_part_6").show();//显示搜索列表
						//清除笔记列表内容
						$("#share_list").empty();
						//循环生成笔记列表
						for(i=0;i<result.data.length;i++){
							var share = result.data[i];
							var noteId = share.cn_note_id;//获取笔记ID
							var noteName = share.cn_note_title;//获取笔记标题
							var li = "";
						    li+="<li class='online'>";
							li+="<a>";
							li+="	<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
							li+="</a>";
							li+="<div class='note_menu' tabindex='-1'>";
							li+="	<dl>";
							//li+="		<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
							li+="	</dl>";
							li+="</div>";
						    li+="</li>";
							$li = $(li);//将li字符串转成jQuery对象
							$li.data("noteId",noteId);
							$("#share_list").append($li);//将li添加到笔记的ul中
						}
					}
				}
			});
		}
	};
	
	
    