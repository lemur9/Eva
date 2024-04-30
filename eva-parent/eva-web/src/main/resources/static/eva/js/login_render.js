$(document).ready(function () {
    getcaptcha();
    init();
})

function init() {
    $(".captcha").off("click");
    $(".captcha").on("click", getcaptcha);

    $(".cho-eva-login").off("click")
    $(".cho-eva-login").on("click", gotoLogin);
}

function gotoLogin() {
    if (!validateForm()) {
        return;
    }
    login();
}


function getcaptcha() {
    //处理浏览器兼容性
    var windowUrl = window.URL || window.webkitURL;
    var xhr = new XMLHttpRequest();
    //验证码请求地址
    var url = "http://localhost:8088/eva-web/captcha?uuid=1&token=1";
    xhr.open("GET", url, true);
    xhr.responseType = "blob";
    xhr.onload = function () {
        if (this.status == 200) {
            var blob = this.response;
            $("#captchaImg").attr("src", windowUrl.createObjectURL(blob));
        }
    }
    xhr.send();
}

function login() {
    var username = $("#username").val();
    var password = $("#password").val();
    var captcha = $("#captcha").val();
    var reqData = {id: 1, username: username, password: password, captcha: captcha}

    $.ajax({
        //请求方式
        type: "POST",
        //请求内容编码类型
        contentType: "application/json;charset=UTF-8",
        //请求的服务器地址
        url: "http://127.0.0.1:8088/eva-web/login",
        //数据，json字符串
        data: JSON.stringify(reqData),
        //请求成功的回调函数
        success: function (result) {
            if (result.code == 200) {
                $.ajax({
                    //请求方式
                    type: "GET",
                    //请求内容编码类型
                    contentType: "application/json;charset=UTF-8",
                    //请求的服务器地址
                    url: "http://127.0.0.1:8088/eva-web/goto/hello?token=" + result.meta.token,
                    //数据，json字符串
                    // data: JSON.stringify(reqData),
                    //请求成功的回调函数
                    success: function (result) {
                        var protocol = location.protocol;
                        var host = location.host;
                        location.href = protocol + "//" + host + "/eva-web/index/" + result.meta;
                    },
                    //请求失败的回调函数，包含具体的错误信息
                    error: function (e) {
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });
            } else if (result.code == 10007) {
                getcaptcha();
                alert("验证码错误！")
            } else if (result.code == 10004) {
                getcaptcha();
                alert("账号或密码错误！")
            }
            console.log(result);
        },
        //请求失败的回调函数，包含具体的错误信息
        error: function (e) {
            getcaptcha();
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function validateForm() {
    var username = $("#username").val();
    var password = $("#password").val();
    var captcha = $("#captcha").val();
    if (username.trim() === "" || password.trim() === "" || captcha.trim() === "") {
        alert("Please enter both username and password.");
        return false;
    }
    return true;
}

