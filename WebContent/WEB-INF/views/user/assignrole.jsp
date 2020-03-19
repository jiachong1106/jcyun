<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/static/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/static/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/static/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
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
						<%@include file="/WEB-INF/views/common/menu.jsp" %>
					</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline">
				  <div class="form-group">
					<label for="exampleInputPassword1">未分配角色列表</label><br>
					<select id="leftRoleList" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                        <c:forEach items="${leftRoleList }" var="role">
	                        <option value="${role.id }">${role.name }</option>
                        </c:forEach>                      
                    </select>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li id="leftToRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                            <br/>
                            <li id="rightToLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                        </ul>
				  </div>
				  <div class="form-group" style="margin-left:40px;">
					<label for="exampleInputPassword1">已分配角色列表</label><br>
					<select id="rightRoleList" class="form-control" multiple size="10" style="width:250px;overflow-y:auto;">
                        <c:forEach items="${rightRoleList }" var="role">
	                        <option value="${role.id }">${role.name }</option>
                        </c:forEach>  
                    </select>
				  </div>
				  <br/>
				  <div style="margin-left:215px;margin-top:25px;">
					  <button id="saveBtn"type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 保存</button>
					  <button id="resetBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
					  <button id="cancleBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> 取消</button>
				  </div>
				  
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
		</div>
	  </div>
	</div>
    <script src="${APP_PATH }/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/static/script/docs.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/static/jquery/layer/layer.js"></script>
        <script type="text/javascript">
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
            });
          //分配角色
            $("#leftToRightBtn").click(function(){            	
            	var selectedOptions = $("#leftRoleList option:selected");
            	
            	$(selectedOptions).each(function(){
            		$(this).remove();
            		$(this).appendTo($("#rightRoleList"));
            		})
           })
            //取消角色
			$("#rightToLeftBtn").click(function(){
				var selectedOptions = $("#rightRoleList option:selected");
				$(selectedOptions).each(function(){
            		$(this).remove();
            		$(this).appendTo($("#leftRoleList"));
            	})
           })
           //单击“保存”,执行分配
           $("#saveBtn").click(function(){
        	   //判断数据不为空，防止传给控制器null值
        	   if($("#rightRoleList").children("option").length==0){
        		   alert("至少分配一个权限");
        		   return false;        
        	    }
        	   //用户现有的权限
        	   var haveRoles = $("#rightRoleList option");
        	   //取出当前用户的id
        	   var userId=${param.id};
        	   //取出所有的id值
        	   var ids="userId="+userId+"&ids=";
        	   $(haveRoles).each(function(){
            		ids+=$(this).attr("value");
            		ids+="&ids=";
            	})
            	ids = ids.substring(0, ids.length-4);
        	   $.ajax({
           		type : "POST",
           		data : ids,
           		//url?userId=1&ids=1&ids=2..
           		url : "${APP_PATH}/user/doAssignRole.do",
           		beforeSend : function(){      
           			index = layer.load(2, {time: 10*1000});
           			return true ;
           		},
           		success : function(result){
           			layer.close(index);
           			if(result.success){
           				alert("操作成功");
           				 window.location.href="${APP_PATH}/user/index.htm";
           			}else{
           				layer.msg(result.message, {time:1000, icon:5, shift:6});
           			}
           		},
           		error : function(){
           			layer.msg("分配错误", {time:1000, icon:5, shift:6});
           		}
           		
           	});
           })
             $("#cancleBtn").click(function(){
            	 window.location.href="${APP_PATH}/user/index.htm";
             })
/*             //分配角色
            $("#leftToRightBtn").click(function(){            	
            	var selectedOptions = $("#leftRoleList option:selected");

            	var jsonObj = {
        			userid : "${param.id}"
        		};
            	
            	$.each(selectedOptions,function(i,n){
            		jsonObj["ids["+i+"]"] = this.value ;            		
            	});
            	
            	var index = -1 ;
            	$.ajax({
            		type : "POST",
            		data : jsonObj,
            		url : "${APP_PATH}/user/doAssignRole.do",
            		beforeSend : function(){      
            			index = layer.load(2, {time: 10*1000});
            			return true ;
            		},
            		success : function(result){
            			layer.close(index);
            			if(result.success){
            				$("#rightRoleList").append(selectedOptions.clone());
                        	selectedOptions.remove();
            			}else{
            				layer.msg(result.message, {time:1000, icon:5, shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("操作失败!", {time:1000, icon:5, shift:6});
            		}
            		
            	});
            	
            	
            });
            
            
            //取消角色
			$("#rightToLeftBtn").click(function(){
            	
				var selectedOptions = $("#rightRoleList option:selected");

            	var jsonObj = {
           			userid : "${param.id}"
           		};
               	
               	$.each(selectedOptions,function(i,n){
               		jsonObj["ids["+i+"]"] = this.value ;            		
               	});
               	
               	var index = -1 ;
               	$.ajax({
               		type : "POST",
               		data : jsonObj,
               		url : "${APP_PATH}/user/doUnAssignRole.do",
               		beforeSend : function(){      
               			index = layer.load(2, {time: 10*1000});
               			return true ;
               		},
               		success : function(result){
               			layer.close(index);
               			if(result.success){
               				$("#leftRoleList").append(selectedOptions.clone());
                           	selectedOptions.remove();
               			}else{
               				layer.msg(result.message, {time:1000, icon:5, shift:6});
               			}
               		},
               		error : function(){
               			layer.msg("操作失败!", {time:1000, icon:5, shift:6});
               		}
               	});
            }); */
            
            
        </script>
  </body>
</html>
    