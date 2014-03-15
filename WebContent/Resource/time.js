var prenode_id,fathernode_id;

$(document).ready(function(){
	var worldid = $("#worldid").val();
	$(".btn-primary").click(function(){
		var id = $(this).attr("id");
		var prenode_id = id.split(".")[1];
		var timenode = "<form method='post' role='form' action='../RequestHandler' class='well' role='form'>" +
							"<input name='type' type='hidden' value='addtimenode'>" +
							"<input name='worldid' type='hidden' value='"+ worldid +"'>" +
							"<input name='prenodeid' type='hidden' value='"+ prenode_id + "'>" +
							"<div class='form-group'>" +
								"<label for='exampleInputEmail1'>时间节点的名称</label>" +
								"<input name='time_description' class='form-control' maxlength='20' placeholder='Enter Description'>" +
							"</div>" +
							"<button type='submit' class='btn btn-default'>Submit</button>" +
						"</form>";
		clearForm();
		$(this).parent().prepend(timenode);
		clearButton()
	});
	$(".btn-danger").click(function(){
		var id = $(this).attr("id");
		prenode_id = id.split(".")[1];
		fathernode_id = id.split(".")[2];
		$('#myModal').modal('show');
	});
	$(".btn-warning").click(function(){
		$.post("../RequestHandler",
		{
		    type:'deletetimenode',
		    fathernodeid:fathernode_id,
		    prenodeid:prenode_id
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
	$(".btn-info").click(function(){
		var id = $(this).attr("id");
		var editnode_id = id.split(".")[1];
		var description = $("#"+editnode_id).html();
		$("#"+editnode_id).remove();
		var timenode = "<form method='post' role='form' action='../RequestHandler' class='well' role='form'>" +
							"<input name='type' type='hidden' value='edittimenode'>" +
							"<input name='worldid' type='hidden' value='"+ worldid +"'>" +
							"<input name='edtnodeid' type='hidden' value='"+ editnode_id + "'>" +
							"<div class='form-group'>" +
								"<label for='exampleInputEmail1'>时间节点的名称</label>" +
									"<input name='time_description' class='form-control' maxlength='20' value='"+ description + "'>" +
							"</div>" +
								"<button type='submit' class='btn btn-default'>Submit</button>" +
						"</form>";
		clearForm();
		$(this).parent().prepend(timenode);
		clearButton();
	});
	$(".btn-success").click(function(){
		var id = $(this).attr("id");
		var editnode_id = id.split(".")[1];
		var description_pannel = "#e_description"+editnode_id;
		var event_content = $(description_pannel).html();
		$(description_pannel).html("");
		var description = $("#"+editnode_id).html();
		var timenode = "<form method='post' role='form' action='../RequestHandler'  style='margin: 5px;' class='well' role='form'>" +
							"<input name='type' type='hidden' value='editeventnode'>" +
							"<input name='edtnodeid' type='hidden' value='"+ editnode_id + "'>" +
							"<div class='form-group'>" +
								"<div class='alert alert-success'>不要超过1000字</div>" +
								"<label for='exampleInputEmail1'>编辑关于此时间节点事件的描述</label>" +
									"<textarea rows='3' name='event_description' class='form-control' " +
									"onkeydown='if(value.length>1000) value=value.substr(0,1000)'" +
											">" + event_content +
							"</textarea></div>" +
								"<button type='submit' class='btn btn-default'>Submit</button>" +
						"</form>";
						
		clearForm();
		$(this).parent().prepend(timenode);
		clearButton();
	});
});

function clearForm(){
	$(".well").remove();
}

function clearButton(){
	$(".btn-primary").remove();
	$(".btn-danger").remove();
	$(".btn-info").remove();
	$(".btn-success").remove();
}