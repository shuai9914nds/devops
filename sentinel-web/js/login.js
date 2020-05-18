$(function () {
    $("#submit").click(function () {

        //请求参数
        var list = {};
        //
        $.ajax({
            //请求方式
            type : "POST",
            //请求的媒体类型
            contentType: "application/json;charset=UTF-8",
            //请求地址
            url : "http://localhost:8082/login/save",
            //数据，json字符串
            data : {
               param:"ssdfsdfsdfds"
            },

                //JSON.stringify(list),
            //请求成功
            success : function(result) {
                console.log(result);
            },
            //请求失败，包含具体的错误信息
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });

    })
})

