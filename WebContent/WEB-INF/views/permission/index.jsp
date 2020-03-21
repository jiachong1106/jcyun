<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${APP_PATH}/static/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/static/css/main.css">
<link rel="stylesheet" href="${APP_PATH}/static/css/doc.min.css">
<link rel="stylesheet" href="${APP_PATH}/static/ztree/zTreeStyle.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}
</style>
</head>

<body>
	
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 许可维护</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<%@include file="/WEB-INF/views/common/top.jsp"%>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="tree">
					<ul style="padding-left: 0px;" class="list-group">
						<%@include file="/WEB-INF/views/common/menu.jsp"%>
					</ul>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<div class="panel panel-default">
				<h1><a href="#" target="_blank">12311111</a></h1>
					<div class="panel-heading">
						<i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${APP_PATH}/static/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/static/script/docs.min.js"></script>
	<script src="${APP_PATH}/static/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/jquery/layer/layer.js"></script>
	<script type="text/javascript" >
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    loadData();
            })
            
              var setting = {
   			    view : {
   			    		addDiyDom: function(treeId, treeNode){
   				    	var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
   			    		if ( treeNode.icon ) {
   			    			icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background","");
   			    		}
   			    	},
					addHoverDom: function(treeId, treeNode){   //设置自定义按钮组,在节点后面悬停显示增删改按钮组.
						var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
						aObj.attr("href","javascript:;","target",""); // 取消当前链接事件.
						if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
						var s = '<span id="btnGroup'+treeNode.tId+'">';
						if ( treeNode.level == 0 ) { //根节点
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#"  onclick="window.location.href=\'${APP_PATH}/permission/add.htm?id='+treeNode.id+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
						} else if ( treeNode.level == 1 ) { //分支节点
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#"  onclick="window.location.href=\'${APP_PATH}/permission/update.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
							if (treeNode.children.length == 0) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#"  onclick="deletePermission('+treeNode.id+','+treeNode.name+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#"  onclick="window.location.href=\'${APP_PATH}/permission/add.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
						} else if ( treeNode.level == 2 ) { //叶子节点
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#"   onclick="window.location.href=\'${APP_PATH}/permission/update.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
							s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#"   onclick="deletePermission('+treeNode.id+','+"'"+treeNode.name+"'"+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
						}
		
						s += '</span>';
						aObj.after(s);	
					},
					removeHoverDom: function(treeId, treeNode){
						$("#btnGroup"+treeNode.tId).remove();
					}

   			   }
    		};
            
            function loadData(){
           		$.ajax({
           		 	url:"${APP_PATH}/permission/loadPermission.do",
	           		type:"post",
	           		success:function(result){
	           			if(result.success){
	           				console.log(result);
	           				var zNodes = result.data.data ;
	           			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			           	}else{
        	    			alert("加载数据失败...");
        	    		}
		            }
         		})
            }
            
            function deletePermission(id,name){
            	layer.confirm("确定要删除["+name+"]许可吗?",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    $.ajax({
            	    	url:"${APP_PATH}/permission/doDelete.do",
            	    	data : {
            	    		"id" : id
            	    	},
            	    	type:"post",
            	    	success:function(result){
            	    		if(result.success){
            	    			loadData();
            	    		}else{
            	    			alert("删除许可数据失败...");
            	    		}
            	    	}
            	    	
            	    });
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
        </script>
</body>
</html>
