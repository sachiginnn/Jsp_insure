$(function(){
    $("#updateuserinfo").click(function(){
        var userPass = $("#userPass").val();
        var userPhone = $("#userPhone").val();
        var charge = $("#charge").val();
        var bo = false;

        //手机号正则
        var regP = /^1[3,4,5,6,7,8,9][0-9]{9}$/;
        //密码正则：6‑16位纯数字
        var regPa=/^(\w){6,16}$/;
        //充值：正数或者空
        var regC = /^([1-9]\d*(\.\d+)?|)$/;


        //1.校验充值
        if(!regC.test(charge)){
            alert("チャージ金額は0より大きい数値を入力してください！");
            return; //直接终止，不再往下执行
        }

        //2.校验手机号：不为空才校验格式
        if(userPhone !== "" && !regP.test(userPhone)){
            alert("入力した携帯番号に問題がありますよ！");
            return;
        }

        //3.校验密码：不为空才校验格式
        if(userPass !== "" && !regPa.test(userPass)){
            alert("パスワードには6桁以上15桁未満の数字が必要です！");
            return;
        }

        //全部校验通过
        bo = true;

        if(bo){
            $.ajax({
                url:"/Jsp_insure/UserServlet?opt=update",
                type:"post",
                data:{"userPhone":userPhone,"userPass":userPass,"charge":charge},
                success:function(date){
                    if(date == 1){
                        alert("更新完了しました！");
                        location.reload();
                    }else{
                        alert("更新に失敗しました。もう一度お試しください。");
                    }
                }
            });
        }
    });
});