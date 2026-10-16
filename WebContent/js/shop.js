/**
 * shop.js文件
 */
//设置js方法来操作界面下拉框，动态设置值
function js(obj,v){
	var sum = parseFloat(obj.value) * 10;
	sessionStorage.setItem(v,sum + "");
	var sum_value = 0;
	var bo = true;
	if(getData() != ""){
		var $value = getData().split(",");
		for(var i = 0; i < $value.length; i++){
			if(v == $value[i]){
				bo = false;
				break;
			}
		}
	}
	if(bo){
		sessionStorage.setItem("dataValue",getData() + v);
	}
	if(getData() != ""){
		var $value = getData().split(",");
		for(var i = 0; i < $value.length -1; i++){
			sum_value = sum_value + parseFloat(sessionStorage.getItem($value[i]));
		}
	}
	sum_value = sum_value + parseFloat(document.getElementById("price_value").value);
	document.getElementById("price_view").innerHTML = sum_value;
}

//获取内容
function getData(){
	var dataValue = sessionStorage.getItem("dataValue");
	if(dataValue != null){
		return dataValue + ","
	}
	return "";
}

//设置当前时间
function setDate(){
	var date = new Date();
	document.getElementById("staDateTime").value = date.getFullYear();
}

//每次打开此网页进行清空sessionStorage避免出现金额增多情况
window.onload = function(){
	sessionStorage.clear();
	//运行
	setDate();
}
$(function(){
$("#buyCommodity").click(function(){
	
	var insuredName = $("#userName2").val().trim();
	var insuredCity = $("#userCity2").val().trim();
	var insuredPhone = $("#userPhone2").val().trim();
	var commodityId = $("#commodity_id").val();

	if(insuredName=="" || insuredCity=="" || insuredPhone==""){
		alert("被保険者情報の必須項目を全部入力してください");
		return;
	}
	
	$.post("OrderServlet",{
		opt:"checkInsured",
		insuredName:insuredName,
		insuredCity:insuredCity,
		insuredPhone:insuredPhone,
		commodityId:commodityId
	},function(resp){  
		
		if(resp === "notReg"){
			alert("該当の被保険者は未登録、または入力情報に誤りがあります！");
			return;
		}
		if(resp === "ageFail"){
			alert("被保険者の年齢が本保険の適用範囲外、購入不可");
			return;
		}
		if(resp !== "ok"){
			alert("検証処理に異常が発生しました");
			return;
		}

		//校验ok之后，弹出确认购买框！（你原来漏掉confirm确认框！！）
		var isConfirm = confirm("ご購入を確定しますか？");
		if(!isConfirm){
			return;
		}

		//总价格,转换成小数
		var totalFee = parseFloat($("#price_view").text());
		//取出选项数据
		var allSelectData = sessionStorage.getItem("dataValue");
		//保险生效年份
		var startTime = document.getElementById("staDateTime").value;

		$.post("OrderServlet",{
			opt:"buy",
			//保险id
			commodityId:commodityId,
			//总价
			totalFee:totalFee,
			//选项
			selectData:allSelectData,
			//开始年份
			startTime:startTime,
			//被保人
			insuredName:insuredName,
			//城市
			insuredCity:insuredCity,
			//被保人电话
			insuredPhone:insuredPhone
		},function(data){
			if(data == "moneyNo"){
				alert("残高が不足しています。チャージしてください。");
			}else if(data == "success"){
				alert("購入成功、注文詳細ページへジャンプします。");
				sessionStorage.removeItem("dataValue");
				location.href="my_order.jsp";
			}else if(data == "nologin"){
				alert("ログインしてください！");
				location.href="login.jsp";
			}else{
				alert("購入に失敗しました。");
			}
		});
	});
});
});