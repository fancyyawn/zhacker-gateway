<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>登录界面</title>
    <link rel="stylesheet" href="${re.contextPath}/css/reset.css"/>
    <link rel="stylesheet" href="${re.contextPath}/css/common.css"/>
    <link rel="stylesheet" href="${re.contextPath}/css/font-awesome.min.css"/>
</head>
<body>
<div class="wrap login_wrap">
    <div class="content">
        <div class="logo"></div>
        <div class="login_box">

            <div class="login_form">
                <div class="login_title">
                    授权
                </div>
                <div class="form_message_title">
                    <i>${authorizationRequest.clientId}</i>将访问以下域:<i>${authorizationRequest.scope?join(", ")}</i>
                </div>
                <form id="confirmationForm" name="confirmationForm"
                      action="${re.contextPath}/oauth/authorize" method="post">
                    <input name="user_oauth_approval" value="true" type="hidden" />
                    <#--<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div class="form_btn">
                        <button type="submit">Approve</button>
                    </div>
                </form>
                <form id="denyForm" name="confirmationForm"
                      action="${re.contextPath}/oauth/authorize" method="post">
                    <input name="user_oauth_approval" value="false" type="hidden" />
                    <#--<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
                    <div class="form_btn">
                        <button type="submit">Deny</button>
                    </div>
                </form>
                <div class="other_login">
                </div>
            <#--</form>-->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${re.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${re.contextPath}/js/common.js"></script>
<script>
    <#--$(function () {-->
        <#--$("#login").click(function () {-->
            <#--//判断是否勾选记住我-->
            <#--var isCheck = $("input[name='remember-me']").is(":checked");-->
            <#--$.ajax({-->
                <#--url: "${re.contextPath}/authentication/form",-->
                <#--type: "post",-->
                <#--async: true,-->
                <#--data: {-->
                    <#--"username": $("input[name='username']").val(),-->
                    <#--"password": $("input[name='password']").val(),-->
                    <#--"imageCode": $("input[name='imageCode']").val(),-->
                    <#--"remember-me": isCheck-->
                <#--},-->
                <#--dataType: "json",-->
                <#--contentType: "application/x-www-form-urlencoded",-->
                <#--success: function (data) {-->
                    <#--if (data.code === -2 || data.code === 103) {-->
                        <#--$("#span_msg").text(data.msg);-->
                    <#--} else {-->
                        <#--window.location.href = "${re.contextPath}/";-->
                    <#--}-->
                <#--},-->
                <#--error: function (XMLHttpRequest, textStatus, errorThrown) {-->
                    <#--alert(XMLHttpRequest.status);-->
                    <#--alert(XMLHttpRequest.readyState);-->
                    <#--alert(textStatus); // paser error;-->
                <#--}-->

            <#--})-->
            <#--;-->
        <#--});-->

    <#--});-->

    // function keyDown() {
    //     if (event.keyCode == 13) {
    //         event.returnValue = false;
    //         event.cancel = true;
    //         $("#login").click();
    //     }
    // }
</script>
</body>
</html>
