var basePath=$("#basePath").val();
//歌词处理
function lyric_ctrl(lrc_content) {
    //console.log(lrc_content);
    var lyricObj = lrc_content;
    var temp = lyricObj.split("[");
    var html = "";
    for (var i = 0; i < temp.length; i++) {
        var arr = temp[i].split("]");
        var text = (arr[1]);
        var time = arr[0].split(",");
        var temp2 = time[0].split(".");
        var ms = temp2[1];//毫秒
        var temp3 = temp2[0].split(":");
        var s = temp3[1];//秒
        var m = temp3[0];//分
        var s_sum = parseInt(m * 60) + parseInt(s);
        if (text) {
            html += "<li id='lrc" + s_sum + "' value='" + s_sum + "' class='lrc' style='list-style: none'>" + text + "</li>";
        }
    }
    lyric_txt.innerHTML = html;
}
$(function () {
    $("#ss").click(function () {
        var words = $("#wd").val();
        $.ajax({
            url: basePath+"/mic/search",
            type: 'POST', //GET
            async: true,    //或false,是否异步
            data: "words=" + words,
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                $("#list").empty();
                $("#count").text(data.length);
                for (var i = 0; i < data.length; i++) {
                    //console.log(data);
                    var hash=data[i].fileHash+"";
                    var hqHash=data[i].hqHash;
                    var sqHash=data[i].sqHash;
                    var songName=data[i].musicName+"-"+data[i].singer;
                    var id=$("#data").val();
                    //console.log(hash);
                    $("#list").append("<tr class='lb' fileHash='"+data[i].fileHash+"' hqHash='"+data[i].hqHash+"' sqHash='"+data[i].sqHash+"'><td>"+data[i].musicName+"</td><td>"+data[i].singer+"</td><td>"+data[i].album+"</td><td><button class='layui-icon' style='font-size: 30px; color: #1E9FFF;' onclick='add(\""+hash+"\",\""+songName+"\","+id+");'>&#xe61f;</button><button class='layui-icon' onclick='download(\""+hash+"\",\""+hqHash+"\",\""+sqHash+"\");' style='font-size: 30px; color: #1E9FFF;'>&#xe601;</button><button onclick='play(\""+hash+"\")' class='layui-icon' style='font-size: 30px; color: #1E9FFF;'>&#xe652;</button></td></tr>");
                }

            }
        })
    })

});

/**
 * 歌词解析滚动方法
 * @param audioFn
 */
function lrc(audioFn) {
    var arr = new Array();
    var i = 0;
    var $lrc=$(".lrc");
    var $this;
    //遍历li的标签
    setTimeout(function(){
        $lrc.each(function () {
            $this = $(this);
            arr.push($this.prop('value'));
        });
    },500);
    //歌词滚动
    audioFn.audio.ontimeupdate = function () {
        setInterval(function () {
            var mt=parseInt(audioFn.audio.currentTime);
            if (mt==arr[i]){
                //console.log(arr[i]+","+mt);
                $("#lrc"+arr[i]+"").css("color","#45B6F7");
                $("#lrc"+arr[i]+"").prev().css("color","silver");//设置当前行变色
                $(".lrc:first").css("color","silver");
                i++;
            }
        },100);
    };

}
//播放音乐
function play(hash) {
    //alert(hash);
    var song = [];
    var lrc_content="";
    $.ajax({
        url: basePath+'/mic/play',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        data: "hash=" + hash,
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            song.push({
                'cover': data.cover,
                'src': data.url,
                'title': data.audio_name
            });
            //console.log(song);
            var audioFn = audioPlay({
                song: song,
                autoPlay: true  //是否立即播放第一首，autoPlay为true且song为空，会alert文本提示并退出
            });
            $("#lrc_content").val(data.lrc);
            lrc_content = $("#lrc_content").val();
            $("#img").css("background-image","url('"+data.cover+"')");
            lyric_ctrl(lrc_content);
            lrc(audioFn);
        }
    })
}
//下载音乐
function download(fileHash,hqHash,sqHash) {
    var element = layui.element;
    element.init();
    var layer = layui.layer;
    $("input[name='downloadmusic']:eq(0)").val(fileHash);
    if (hqHash!="00000000000000000000000000000000"){
        $("input[name='downloadmusic']:eq(1)").val(hqHash);
    }else {
        $("input[name='downloadmusic']:eq(1)").attr("disabled","");
    }
    if (sqHash!="00000000000000000000000000000000"){
        $("input[name='downloadmusic']:eq(2)").val(sqHash);
    }else {
        $("input[name='downloadmusic']:eq(2)").attr("disabled","");
    }
        var index=layer.open({
            type:1,
            title:"下载",
            btn: ['确定', '取消'], //可以无限个按钮
            yes: function () {
                var hash=$("input[name='downloadmusic']:checked").val();
                $.ajax({
                    url: basePath+'/mic/getUrl',
                    type: 'POST', //GET
                    async: true,    //或false,是否异步
                    data: "hash="+hash,
                    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text

                    success: function (data) {
                        console.log(data);
                        layer.close(index);
                        downloadFile(data.url,data.audio_name);
                    }
                })

            },
            btn2:function () {

            },
            content:$("#download"),
        });
}
//添加到歌单
function add(hash,name,id) {
    var category=0;
    var categoryName='';
    var form = layui.form;
        form.on('select(interest)', function (data) {
            category = data.value;
            categoryName = data.elem[data.elem.selectedIndex].text;
            form.render('select');
        });


    layui.use('layer',function () {
        var layer = layui.layer;
        var index=layer.open({
            type:1,
            title:"选择要添加的列表",
            btn: ['确定', '取消'], //可以无限个按钮
            yes: function () {
                //alert(category);
                $.ajax({
                    url: basePath+'/mic/addSong',
                    type: 'POST', //GET
                    async: true,    //或false,是否异步
                    data: {"name":name,"hash":hash,"id":category},
                    dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                    success: function (data) {
                        layer.close(index);
                        layer.alert("添加成功");
                    }
                })

            },
            btn2:function () {
                
            },
            content:$("#box1"),
        });
    });

}
function xs(id,index_id) {
    //alert(id);
    var userId=$("#userId").val();
    //alert(userId+id);
    var divs = document.getElementsByName("b_content")
    for (var i = 0 ; i< divs.length ; i++){
        if (divs[i].id == "d"+(index_id) ){
            divs[i].style.display="";
            $.ajax({
                url: basePath+'/mic/s_list',
                type: 'POST', //GET
                async: true,    //或false,是否异步
                data: {"userId":userId,"listId":id},
                dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
                success: function (data) {
                    //console.log(data.length);
                    var lista="list"+index_id;
                    $("#"+lista+"").empty();
                    //console.log(lista);
                    for (var i=0;i<data.length;i++){
                        var hash=data[i][0].hash;
                        //console.log(data[i][0].name);
                        $("#"+lista+"").append("<tr class='lb' fileHash='"+data[i][0].hash+"'><td>"+data[i][0].name+"</td><td><button class='layui-icon' onclick='download(\""+hash+"\");' style='font-size: 30px; color: #1E9FFF;'>&#xe601;</button><button onclick='play(\""+hash+"\")' class='layui-icon' style='font-size: 30px; color: #1E9FFF;'>&#xe652;</button></td></tr>>");
                    }

                }
            })
        }else{
            divs[i].style.display="none"
        }
    }
}
function qk() {
    $("[name='b_content']").siblings().css("display","none");
    $("#ss_content").css("display","block");
}
function downloadFile(url,name,type){
    //alert(url);
    window.open(basePath+"/download?name="+name+"&url="+url);
}