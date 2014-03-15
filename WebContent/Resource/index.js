var float_index = 0;

$(document).ready(function(){
	$("#submit").click(function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var code = $("#verifycode").val();
		$.post("./login.jsp",
		{
		    username:username,
		    password:password,
		    verifycode:code
		},
		function(data,status){
			var retmes = data.toString();
			if(retmes.search("success")!=-1 || retmes.search("admin") != -1)
				location.reload();
			if(retmes.search("wrongcode") != -1){
				alert("验证码错误");
			}
		});
	});
	$("#addTimenode").click(function(){
		var t_description = $("#new_time_description").text();
		var length = t_description.length;
		if(length <= 50)
			saveNewTimenode();
		else
			$(this).parent().append("超过50个数字，当前字数为：" + length);
	});
	$(".editorsavebutton").click(function(){
		type = $(this).val();
		if(type == "编辑")
		{
			editTimenode(this);
		}else if(type == '保存'){
			var tag = $(this).parent().attr("id");
			var type = $(this).val();
			var id = tag.split(".")[1];
			var content = "#td"+id;
			var t_description = $(content).text();
			var length = t_description.length;
			if(length <= 50)
				updateTimenode(this);
			else
				$(this).parent().append("超过50个数字，当前字数为：" + length);
			
		}
	});
	$(".deletebutton").click(function(){
		updateTimenode(this);
	});
	$(".timenodebox").mouseover(function(){
		var tag = $(this).attr("id");
		if(tag != 'tn'){
			refreshtimenode();
			$(this).css("background-color","gray");
			var id = tag.split(".")[1];
			if(id != float_index){
				float_index = id;
				getEventlist();
			}
		}
		
	});
	$("#addEventnode").click(function(){
		saveEventnode(this);
	});
});

function refreshtimenode(){
	var tnbs = $(".timenodebox");
	for(var i = 0 ;i < tnbs.length; i ++)
		$(tnbs[i]).css("background-color","white");
}

function changeimg(){
	var myimg = document.getElementById("code"); 
	now = new Date(); 
	myimg.src="./image.jsp?code="+now.getTime();
}

function addTimenode(){
	var num = $(".time_weight").length;
	var time_weight = "<lable id = 'time_weight_label'>时间权：</lable>" +
			"	<input type=‘text’ id=‘time_weight’ maxlength = '4' value = " + num + ">";
	var time_description ="<div id='time_description' contenteditable='true' >添加对该时间节点的描述</div>";
	$("#timelinelist").prepend(time_weight,time_description);
	$("#multi-option").val("保存此节点");
}

function saveNewTimenode(){
	var t_weight = $("#time_weight").val();
	var t_description = $("#new_time_description").text();
	$.post("./addtimenode.jsp",
	{
	   time_weight:t_weight,
	   time_description:t_description,
	},
	function(data,status){
		var retmes = data.toString();
		if(retmes.search("success")!=-1 || retmes.search("admin") != -1)
			location.reload();
		if(retmes.search("unknowningstatus") != -1){
			alert("操作错误");
		}
	});
}

function editTimenode(obj){
	var tag = $(obj).parent().attr("id");
	var type = tag.split(".")[0];
	var id = tag.split(".")[1];
	if(type == "tn")
	{
		var content = "#td"+id;
		var weight = "#tw"+id;
		var label = "#twl"+id;
		$(content).attr("contenteditable",true);
		$(weight).attr("hidden",false);
		$(label).attr("hidden",false);
		$(obj).val("保存");
	}
}

function editEventnode(obj){
	var tag = $(obj).parent().attr("id");
	var type = tag.split(".")[0];
	var id = tag.split(".")[1];
	var content = "#ed" + id;
	$(content).attr("contenteditable",true);
	$(obj).val("保存");
}

function updateTimenode(obj){
	var tag = $(obj).parent().attr("id");
	var type = $(obj).val();
	var id = tag.split(".")[1];
	var content = "#td"+id;
	var weight = "#tw"+id;
	var t_weight = $(weight).val();
	var t_description = $(content).text();
	$.post("./editordeletetimenode.jsp",
	{
		type:type,
		id:id,
		time_weight:t_weight,
		time_description:t_description,
	},
	function(data,status){
		var retmes = data.toString();
		if(retmes.search("success")!=-1 || retmes.search("admin") != -1)
			location.reload();
		if(retmes.search("unknowningstatus") != -1){
			alert("操作错误");
		}
	});
}

function updateEventnode(obj){
	var tag = $(obj).parent().attr("id");
	var type = $(obj).val();
	var id = tag.split(".")[1];
	var content = "#ed"+id;
	var t_description = $(content).text();
	var length = t_description.length;
	if(length > 140)
	{
		$(obj).parent().append("超过140个数字，当前字数为：" + length);
		return;
	}
	$.post("./editordeleteEventnode.jsp",
	{
		type:type,
		id:id,
		owner:float_index,
		time_description:t_description,
	},
	function(data,status){
		var retmes = data.toString();
		if(retmes.search("success")!=-1 || retmes.search("admin") != -1){
			getEventlist();
		}
		if(retmes.search("unknowningstatus") != -1){
			alert("操作错误");
		}
	});
}

function saveEventnode(obj){
	var e_description = $("#new_event_description").text();
	var length = e_description.length;
	if(length > 140)
	{
		$(obj).parent().append("超过140个数字，当前字数为：" + length);
		return;
	}
	if(float_index == 0)
		return;
	$.post("./addeventnode.jsp",
	{
	   owner:float_index,
	   event_description:e_description,
	},
	function(data,status){
		var retmes = data.toString();
		if(retmes.search("success")!=-1 || retmes.search("admin") != -1)
			getEventlist();
		if(retmes.search("unknowningstatus") != -1){
			alert("操作错误");
		}
	});
}

function getEventlist(){
	$.post("./Eventlist.jsp",
	{
		float_index:float_index,
	},
	function(data,status){
		var html = data.toString();;
		html = html.replace(/(^\s*)/g,"");
		clearEvents();
		$("#event_lists").append(html);
		$(".editorsaveEvent").click(function(){
			type = $(this).val();
			if(type == "编辑")
			{
				editEventnode(this);
			}else if(type == '保存'){
				updateEventnode(this);
			}
		});
		$(".deleteEvent").click(function(){
			updateEventnode(this);
		});
	});
}

function clearEvents(){
	var eventlist = $("#event_lists").children();
	for(var i = 0 ; i < eventlist.length ;i ++)
		eventlist[i].remove();
	var box_num = $("#pos"+float_index).val();
	var empty_box = "<div class = 'empty_box' '></div>";
	for(var i = 0; i < box_num ;i ++)
	{
		$("#event_lists").append(empty_box);
	}
}