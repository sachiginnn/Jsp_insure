<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>健康保険のホームページ</title>
		<link rel="stylesheet" type="text/css" href="css/index.css" />
		<link rel="stylesheet" href="https://at.alicdn.com/t/c/font_3881267_wfv3iyzbijg.css">
		<link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
		
		<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="js/my_info.js"></script>
	</head>
	<body>
		<div align="center" id="div_all">
			<div id="top1">
				<div id="top1_text">
					<div id="top1_text1">こんにちは、健康保険モールへようこそ</div>
					<div id="top1_text2">
						<span id="span1">最新情報：</span>
						<marquee id="lb" direction="left">
							ログインしてサインするとプレゼントがあります
						</marquee>
						<c:if test="${empty user}">
					    <%-- 用户未登录：显示登录注册按钮 --%>
					    <a href="login.jsp">
					        <button type="button" id="userlogin">login/regist</button>
					    </a >
						</c:if>
						<c:if test="${not empty user}">
					    <%-- 用户已登录：显示用户名，点击跳转到个人信息页面，把personal.jsp改成你自己的个人信息页面名 --%>
					    <a href="my_info.jsp">
					        <button type="button" id="userlogin">${user.user_name}-先生，こんにちは！</button>
					    </a >
					</c:if>
						
					</div>
				</div>
			</div>
			<div id="top2">
				<div id="top2_text">
					<ul id="top2_text_ul">
						<li><b>すべての保険</b></li>
						<li><b>健康保険</b></li>
						<li><b>意外保険</b></li>
						<li><b>企業保険</b></li>
						<li><b>旅行保険</b></li>
						<li><b>家計保険</b></li>
					</ul>
				</div>
			</div>
			<div id="context_all2">
				<div id="banner_img">
					<div id="banner_img_text">
					    <img src="images/my_img.jpg" width="100%" />
					</div>
				</div>
				<div id="context" class="context" style="height: auto;">
					<div id="context_text_1" style="height: 70px;">
						<a href="my_info.jsp"><div id="myinfo" style="background-color:#FF7F50;color: white;"><b>マイメッセージ</b></div></a>
						<a href="/Jsp_insure/OrderServlet?opt=queryOrder"><div id="myorder"><b>私の注文</b></div></a>
					</div>
					<div id="context_text_2">
						<div id="context_text_2_1">
							<b>${user.user_name}さん、あなたの情報は次の通りです。</b>
							<table id="table_myinfo">
								<tr>
									<th>メールアドレス：${user.user_email}</th>
									<td></td>
								</tr>
								<tr>
									<th>パスワード：${user.user_password}</th>
									<td><input type="password" name="userPass" id="userPass"/></td>
								</tr>
								<tr>
									<th>あなたの誕生日：${user.user_birthday}</th>
									<td></td>
								</tr>
								<tr>
									<th>携帯番号：${user.user_phone}</th>
									<td>
										<input type="text" maxlength="5" name="userPhone" id="userPhone"  />
									</td>
								</tr>
								<tr>
									<th>現在の残高：${user.user_money}</th>
									<td>
										チャージ金額の入力
										<input type="text" id="charge" name="charge">
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<a href="#"><div id="updateuserinfo">変更</div></a>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
				<div id="foot">
					<h5 class="h51">カスタマーサービスホットライン：888-888-8888 （9:00~21:00）</h5>
					<h5 class="h52">○○健康保険株式会社 027-666666 &nbsp;&nbsp; ICP認証：UP9999999 &nbsp;&nbsp; Copyright © 2023 All Rights Reserved</h5>
				</div>
			</div>
		</div>
	</body>
</html>