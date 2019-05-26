<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<meta name="description" content="spring mvc, spring security, mybatis 最新版整合测试">
  	<meta name="keywords" content="spring mvc spring security mybatis">
  	<meta name="author" content="skyrocks">
    <security:csrfMetaTags/>

  	<%-- <base href="<%= basePath %>"> --%>

    <!-- Le styles -->
 	<link href="../resources/vendor/bootstrap/3.3.6/css/bootstrap.min.css"
	    rel="stylesheet">
		
	<link rel="stylesheet" href="../resources/vendor/font-awesome/4.2.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="../resources/vendor/fonts/fonts.googleapis.com.css" type="text/css"/>
	<link rel="stylesheet" href="../resources/vendor/css/ace.min.css" type="text/css"/>
	<link rel="stylesheet" href="../resources/vendor/css/ace-rtl.min.css" type="text/css"/>

    <!--[if lt IE 9]>
    <script src="http://libs.baidu.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <script src="http://libs.baidu.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style type="text/css">
      /* body {
        background: url(../resources/img/login-bg.png) no-repeat;
        background-size: 100%;
      } */

      .box-shadow{  
        position: absolute;
        top: 50%;
        left: 50%;
        padding: 30px 30px;
        margin-left: -150px;
        margin-top: -180px;
        width: 300px;
        height: 360px;
        background: url(../resources/img/1.png) repeat;
        border-radius: 10px;
      } 
      .title{
        border-bottom:1px solid #c7c7c7;margin-bottom:8px; padding-right:10px; font-size:20px;font-weight:bold;
      }
      .error{
        color:red;margin-bottom:8px;height:15px;
      }
    </style>
  </head>

	<body class="login-layout" >
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">智能化</span>
									<span class="white" id="id-text2">管理系统</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; 浙江锦马</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请输入您的信息
											</h4>

											<div class="space-6"></div>

											<form name='f' action='/ii/login' method='POST'>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Username" name="username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Password" name="password"/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl" name="_spring_security_rember_me"> 记住</span>
														</label>

														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary" name="submit" value="Login">
															<!-- <i class="ace-icon fa fa-key"></i> -->
															<span class="bigger-110">登录</span>
														</button>
														<!-- <input name="_csrf" type="hidden" value="3032d80d-8101-4d9f-a0a5-92253bf12d5f" /> -->
														<security:csrfInput/>  
														<!-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> -->
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											
										</div><!-- /.widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													<i class="ace-icon fa fa-arrow-left"></i>
													忘记密码
												</a>
											</div>

											<div>
												<a href="#" data-target="#signup-box" class="user-signup-link">
													注册
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												找回密码
											</h4>

											<div class="space-6"></div>
											<p>
												输入您的电子邮件和接收指令
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon fa fa-lightbulb-o"></i>
															<span class="bigger-110">发送邮件</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												返回登录
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												用户注册
											</h4>

											<div class="space-6"></div>
											<p>填写信息: </p>

											<form>
												<fieldset>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="用户名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="确认密码" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="邮箱" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="phone" class="form-control" placeholder="手机号" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															接受
															<a href="#">用户协议</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">重置</span>
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success">
															<span class="bigger-110">注册</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												<i class="ace-icon fa fa-arrow-left"></i>
												返回登录
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->

							
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<script type="text/javascript"
		src="../resources/vendor/jquery/1.12.4/jquery.min.js"></script>		
        <script type="text/javascript"
		src="../resources/vendor/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
		</script>
		<div style="text-align:center;">
<p>欢迎使用浙江锦马智能化管理系统</p>
</div>
	</body>
</html>
