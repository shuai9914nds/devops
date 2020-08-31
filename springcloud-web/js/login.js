$(function () {
    $("#submit").click(function () {
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            dataType: "json",
            contentType : 'application/json',
            //请求地址
            url: "http://localhost:8082/login/login",
            //数据，json字符串
            data: JSON.stringify(
                {
                    uname: $('#uname').val(),
                    password: $('#password').val()
                }
            ),
            //JSON.stringify(list),
            //请求成功
            success: function (result) {
                if (result.success) {
                    alert("登陆成功");
                } else {
                    alert("登录失败");
                }
            }
            ,
            //请求失败，包含具体的错误信息
            error: function (e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })
        ;

    })
})

