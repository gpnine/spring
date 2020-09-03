<%--
  Created by IntelliJ IDEA.
  User: zcl
  Date: 2020/6/4
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="margin: auto">
    手机号：<input type="text" name="phone"><br/>
    密码：<input type="password" name="password"><br/>
    验证码：<input type="text" name="vCode">
    <input type="submit" onclick="getCode()" value="获取验证码"><br/>
    <input id="login" type="submit" onclick="login()" value="登录">
</div>
</body>
<script src="js/jquery-3.4.0.min.js"></script>
<script type="text/javascript">
    function login() {
        var phone = $("[name='phone']").val();
        var vCode = $("[name='vCode']").val();
        var password = $("[name='password']").val();
        if (!phone || !vCode || !password) {
            alert("空")
            return;
        }
        $.ajax({
            url: "/user/login",
            method: 'post',
            data: {
                phone: phone,
                vCode: vCode,
                password: password
            },
            success: function (data) {
                alert(data);
            }

        })
    }

    function getCode() {
        var phone = $("[name='phone']").val();
        $.ajax({
            url: "/user/getCode",
            method: 'get',
            data: {
                phone: phone
            },
            success: function (data) {
                $("[name='vCode']").val(data);
                return data;
            }
        })
    }
</script>
</html>
