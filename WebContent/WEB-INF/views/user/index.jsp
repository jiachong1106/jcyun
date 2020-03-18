<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="GB18030">
  <head>
    <meta charset="GB18030">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/static/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/static/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i> 张三 <span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="index.html"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
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
				<ul style="padding-left:0px;" class="list-group">
					<li class="list-group-item tree-closed" >
						<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a> 
					</li>
					<li class="list-group-item">
						<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;">
							<li style="height:30px;">
								<a href="user.html" style="color:red;"><i class="glyphicon glyphicon-user"></i> 用户维护</a> 
							</li>
							<li style="height:30px;">
								<a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a> 
							</li>
							<li style="height:30px;">
								<a href="permission.html"><i class="glyphicon glyphicon-lock"></i> 许可维护</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon-ok"></i> 业务审核 <span class="badge" style="float:right">3</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="auth_cert.html"><i class="glyphicon glyphicon-check"></i> 实名认证审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_adv.html"><i class="glyphicon glyphicon-check"></i> 广告审核</a> 
							</li>
							<li style="height:30px;">
								<a href="auth_project.html"><i class="glyphicon glyphicon-check"></i> 项目审核</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed">
						<span><i class="glyphicon glyphicon-th-large"></i> 业务管理 <span class="badge" style="float:right">7</span></span> 
						<ul style="margin-top:10px;display:none;">
							<li style="height:30px;">
								<a href="cert.html"><i class="glyphicon glyphicon-picture"></i> 资质维护</a> 
							</li>
							<li style="height:30px;">
								<a href="type.html"><i class="glyphicon glyphicon-equalizer"></i> 分类管理</a> 
							</li>
							<li style="height:30px;">
								<a href="process.html"><i class="glyphicon glyphicon-random"></i> 流程管理</a> 
							</li>
							<li style="height:30px;">
								<a href="advertisement.html"><i class="glyphicon glyphicon-hdd"></i> 广告管理</a> 
							</li>
							<li style="height:30px;">
								<a href="message.html"><i class="glyphicon glyphicon-comment"></i> 消息模板</a> 
							</li>
							<li style="height:30px;">
								<a href="project_type.html"><i class="glyphicon glyphicon-list"></i> 项目分类</a> 
							</li>
							<li style="height:30px;">
								<a href="tag.html"><i class="glyphicon glyphicon-tags"></i> 项目标签</a> 
							</li>
						</ul>
					</li>
					<li class="list-group-item tree-closed" >
						<a href="param.html"><i class="glyphicon glyphicon-list-alt"></i> 参数管理</a> 
					</li>
				</ul>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered" id="users_table">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox"></th>
                  <th>账号</th>
                  <th>邮箱地址</th>
                  <th>创建时间</th>
                  <th width="120">操作</th>
                </tr>
              </thead>
              <tbody >
                
              </tbody>
			  <tfoot >
				<tr>	  	
			        <td colspan="6" align="center" id="page_nav_area">
			        </td>
		     	 </tr>
			  </tfoot>
			  
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH }/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/static/script/docs.min.js"></script>
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
			queryPageUser(1);
            });
            $("tbody .btn-success").click(function(){
                window.location.href = "assignRole.html";
            });
            $("tbody .btn-primary").click(function(){
                window.location.href = "edit.html";
            });
            //查询用户
           	function queryPageUser(pageno){
            	$.ajax({
            		type : "POST",
            		data : "pageno="+pageno,
            		url : "${APP_PATH}/user/doIndex.do",
            		beforeSend : function(){
            			return true ;
            		},
            		success : function(result){
            			if(result.success){
            				//1.解析并显示用户数据
            				 build_users_table(result);
            				//2.解析并显示分页数据
                            // build_page_info(result);
                             //3.解析显示分页条数据
                             build_page_nav(result)
            			}else{
            				alert("查询失败");
            			}
            		},
            		error : function(){
            			alert("查询错误");
            		}
            	});
            }
            //1.解析并显示用户数据
            function build_users_table(result){
            	//清空table表格
            	$("#users_table tbody").empty();
            	//解析Json获取员工列表
            	var users = result.data.pageInfo.list;
            	$.each(users,function(index,item){
            		//把用户数据添加到单元格中
            		 var idTd = $("<td></td>").append(item.id);
                     var loginacctTd = $("<td></td>").append(item.loginacct);
                     var boxTd=$("<td><input type='checkbox'></td>");
                     var emailTd = $("<td></td>").append(item.email);
                     var ctimeTd = $("<td></td>").append(item.createtime);
                     var saveBtn = $("<button></button>").addClass("btn btn-success btn-xs")
                     				.append($("<span></span>").addClass("glyphicon glyphicon-check"));
                     var editBtn = $("<button></button>").addClass("btn btn-primary btn-xs")
                     				.append($("<span></span>").addClass("glyphicon glyphicon-pencil"));
		           	var delBtn = $("<button></button>").addClass("btn btn-danger btn-xs")
		           					.append($("<span></span>").addClass("glyphicon glyphicon-remove"));
			         var btnTd = $("<td></td>").append(saveBtn).append(" ").append(editBtn).append(" ").append(delBtn);
			       //append方法执行完后还是返回原来的元素
	                   $("<tr></tr>").append(idTd).append(boxTd).append(loginacctTd).append(
	                		   emailTd).append(ctimeTd).append(btnTd)
	                		   .appendTo("#users_table tbody");
            	})
            }
          //3.解析显示分页条数据
            function build_page_nav(result){
            	$("#page_nav_area").empty();
            	//创建ul class="pagination"元素
                var ul = $("<ul></ul>").addClass("pagination");
                //创建首页，前页，下一页，末页元素
                var firstPage = $("<li></li>").append($("<a></a>").append("首页"));
                var prePage = $("<li></li>").append($("<a></a>").append("&laquo;"));
                if (result.data.pageInfo.hasPreviousPage == false) {
                     //不能点击
                     firstPage.addClass("disabled");
                     prePage.addClass("disabled");
                } else {
                     firstPage.click(function() {
                    	 queryPageUser(1);
                     });
                     prePage.click(function() {
                    	 queryPageUser(result.data.pageInfo.pageNum - 1);
                     });
                }
                var nextPage = $("<li></li>").append($("<a></a>").append("&raquo;"));
                var lastPage = $("<li></li>").append($("<a></a>").append("末页"));
                if (result.data.pageInfo.hasNextPage == false) {
                     //不能点击
                     lastPage.addClass("disabled");
                     nextPage.addClass("disabled");
                } else {
                     lastPage.click(function() {
                    	 queryPageUser(result.data.pageInfo.pages);
                     });
                     nextPage.click(function() {
                    	 queryPageUser(result.data.pageInfo.pageNum + 1);
                     });
                }
                //将首页，前页li元素添加到ul元素中
                ul.append(firstPage).append(prePage);
                //获取要显示的所有页码参数
                var nums = result.data.pageInfo.navigatepageNums;
                $.each(nums, function(index, item) {
                     var num = $("<li></li>").append($("<a></a>").append(item));
                     if (result.data.pageInfo.pageNum == item)
                          //添加活动标识
                          num.addClass("active");
                     num.click(function() {
                    	 queryPageUser(item);
                     });
                     ul.append(num);
                });
                //添加下一页和末页提示
                ul.append(nextPage).append(lastPage);
                $("<nav></nav>").append(ul).appendTo("#page_nav_area");
            
          }
        </script>
  </body>
</html>
