$(function () {
    $("#submit").click(function () {
        var param = {
            uname: $('#uname').val(),
            password: $('#password').val()
        }
        //请求参数
        var paramJson = JSON.stringify(param);
        //
        $.ajax({
            //请求方式
            type: "POST",
            //请求的媒体类型
            dataType: "json",
            //请求地址
            url: "http://localhost:8082/login/login",
            //数据，json字符串
            data: {"param": paramJson},

            //JSON.stringify(list),
            //请求成功
            success: function (result) {
                if (result.success) {
                    alert("登陆成功")
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

