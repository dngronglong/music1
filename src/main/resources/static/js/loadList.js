var basePath=$("#basePath").val();
var userId=$("#userId").val();
window.onload=function (ev) {
    $.ajax({
        url: basePath + '/mic/findAll',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        data: "id=" + userId,
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            for(var i=0;i<data.length;i++){
                $("#lb").append("<dd><a href='javascript:xs("+data[i].id+","+i+");'>"+data[i].name+"</a></dd>");
                $(".layui-body").append("<div id='d"+i+"' name='b_content' style='padding: 15px;display: none' >\n" +
                    "                <label>"+data[i].name+"</label>\n" +
                    "                <table class='layui-table' lay-skin='line'>\n" +
                    "                    <thead>\n" +
                    "                    <tr>\n" +
                    "                        <th>歌名</th>\n" +
                    "                        <th>操作</th>\n" +
                    "                    </tr>\n" +
                    "                    </thead>\n" +
                    "                    <tbody id=\"list"+i+"\">\n" +
                    "\n" +
                    "                    </tbody>\n" +
                    "                </table>\n" +
                    "            </div>");
            }
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.alert("1.优化了页面的加载速度及逻辑<br> 2.修复添加歌曲到列表");
            })
        }
    })
}