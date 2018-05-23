<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>音乐小站</title>
    <link rel="stylesheet" href="${basePath}/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/audio.css">
    <#--<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css">-->
</head>
<input id="userId" type="hidden" value="${user.id}">
<body class="layui-layout-body">
<div id="download" style="display:none;overflow:hidden;height: 30%" >
    <form class="layui-form">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="radio" name="downloadmusic" value="m4a" title="流畅品质(m4a)">
                <input type="radio" name="downloadmusic" value="mp3_l" title="标准品质(mp3)" checked="">
                <input type="radio" name="downloadmusic" value="mp3_h" title="HQ高品质(mp3)">
                <input type="radio" name="downloadmusic" value="ape" title="无损品质(ape)">
                <input type="radio" name="downloadmusic" value="flac" title="无损品质(flac)">
            </div>
        </div>
    </form>
</div>
<div id="box1" style="display: none;overflow:hidden;height: 500px;width: 400px">
    <form class="layui-form" action="">
    <div class="layui-inline">
        <label class="layui-form-label">搜索选择框</label>
        <div class="layui-input-inline">
            <select name="modules" lay-verify="required" lay-search="" id="songList">
                <option value="">选择列表</option>
            </select>
        </div>
    </div>
    </form>
</div>
<div id="zhe">
    <!--//遮罩层-->
    <div id="img" style="float: left;width: 110%;height: 110%;z-index: 1"></div>
    <div id="ct" style="float: left;position: absolute;width: 100%;height: 100%;z-index: 9">
    <i class="layui-icon" id="yinCang" style="font-size: 30px; color: #1E9FFF;">&#xe61a;</i>
    <div id="lyric_txt" class="test test-1"
         style="height: 500px;width:500px;text-align: center;margin: 0 auto;color: silver">
        <div class="scrollbar"></div>
    </div>
    <textarea id="lrc_content" name="textfield" cols="70" rows="10" style="height:200px;display:none;z-index: 9;"></textarea>
    </div>
</div>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">MyMusic</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                ${user.loginName}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                    <dd><a href="${basePath}/exit">退出</a></dd>
                </dl>
            </li>

        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <input type="hidden" id="data" name="data" value="${user.id}">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">我的云歌单</a>
                    <dl class="layui-nav-child" id="lb">
                        <#--<#list user.list as list>-->
                            <#--<dd><a href="javascript:xs('${list.id}','${list_index}');">${list.name}</a></dd>-->
                        <#--</#list>-->
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:qk();">曲库</a></li>
                <li class="layui-nav-item" id="addList"><a href="javascript:;">添加列表</a></li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <div id="ss_content" name="b_content" style="margin-top: 20px;">
        <input class="layui-input" type="text" id="wd" style="width: 200px;float: left">
        <button class="layui-btn" type="button" id="ss" style="margin-left: 20px">搜索</button>
            <hr class="layui-bg-gray">
        <div>
            <table id="musicList" lay-filter="music"></table>
        </div>
            <#--<hr class="layui-bg-gray">-->
    </div>
    </div>
    <div class="layui-footer" style="background: #0C0C0C;height: 70px;z-index: 10">
        <div class="audio-box">
            <div class="audio-container">
                <div class="audio-cover" id="layui-btn" data-method="notice" class="layui-btn"></div>
                <div class="audio-view">
                    <li class="audio-title" style="list-style: none">未知歌曲</li>
                    <div class="audio-body">
                        <div class="audio-backs">
                            <div class="audio-this-time">00:00</div>
                            <div class="audio-count-time">00:00</div>
                            <div class="audio-setbacks">
                                <i class="audio-this-setbacks">
                                    <span class="audio-backs-btn"></span>
                                </i>
                                <span class="audio-cache-setbacks">
							</span>
                            </div>
                        </div>
                    </div>
                    <div class="audio-btn">
                        <div class="audio-select">
                            <div class="audio-prev"></div>
                            <div class="audio-play"></div>
                            <div class="audio-next"></div>
                            <div class="audio-menu"></div>
                            <div class="audio-volume"></div>
                        </div>
                        <div class="audio-set-volume">
                            <div class="volume-box">
                                <i><span></span></i>
                            </div>
                        </div>
                        <div class="audio-list">
                            <div class="audio-list-head">
                                <p>☺随心听</p>
                                <span class="menu-close">关闭</span>
                            </div>
                            <ul class="audio-inline" id="songList">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" value="${basePath}" id="basePath">

</div>

<script src="${basePath}/js/layui.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/js/audio.js"></script>
<script type="text/javascript" src="${basePath}/js/MyMusic.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery.blurr.js"></script>
<script type="text/javascript" src="${basePath}/js/loadList.js"></script>
<script id="music" type="text/html">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="play">播放</a>
    <a class="layui-btn layui-btn-xs" lay-event="add">添加</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="download">下载</a>
</script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //监听工具条
        table.on('tool(music)', function(obj){
            var data = obj.data;
            if(obj.event === 'play'){
                layer.msg('播放：'+ data.musicName+" - "+data.singer);
                // play(data.fileHash);//播放歌曲
                qqPlay(data.mid,data.musicName+" - "+data.singer);
            } else if(obj.event === 'add'){
                add(data.mid,data.musicName);
            } else if(obj.event === 'download'){
                var mid=data.mid;
                layer.msg(mid);
                download(mid,data.musicName+" - "+data.singer);
            }
        });
//        $('.demoTable .layui-btn').on('click', function(){
//            var type = $(this).data('type');
//            active[type] ? active[type].call(this) : '';
//        });
    });
</script>


<script>
    var id =${user.id};
    $("#addList").click(function () {
        var element = layui.element;
        layui.use('layer', function () {
            var layer = layui.layer;
            layer.prompt({
                formType: 2,
                value: '',
                title: '请输入要添加的列表'
            }, function (value, index, elem) {
                layer.close(index);
                var lbId=$("#lb").children.length;
                $.ajax({
                    type: "post",
                    url: "${basePath}/mic/addList",
                    data: {"listName": value, "id": id},
                    async: true,
                    success: function () {
                        layer.alert('添加成功');
                        $("#lb").empty();
                        $.ajax({
                            type: "post",
                            url: "${basePath}/mic/findAll",
                            data: "id="+id,
                            async: true,
                            dataType: "json",
                            success: function (data) {
                                $(".layui-body").append("<div id='d"+(data.length-1)+"' name='b_content' style='padding: 15px;display: none' >\n" +
                                        "                <label>"+data[data.length-1].name+"</label>\n" +
                                        "                <table class='layui-table' lay-skin='line'>\n" +
                                        "                    <thead>\n" +
                                        "                    <tr>\n" +
                                        "                        <th>歌名</th>\n" +
                                        "                        <th>操作</th>\n" +
                                        "                    </tr>\n" +
                                        "                    </thead>\n" +
                                        "                    <tbody id=\"list"+(data.length-1)+"\">\n" +
                                        "\n" +
                                        "                    </tbody>\n" +
                                        "                </table>\n" +
                                        "            </div>");
                                for (var i = 0; i < data.length; i++) {
                                     console.log(data);
                                    $("#lb").append("<dd><a href='javascript:xs(\""+data[i].id+"\",\""+i+"\");'>" + data[i].name + "</a></dd>");

                                }

                            }
                        });
                        element.init();
                    }
                });
            });
        });
    });


    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
    var form;
    layui.use('form', function () {
        form = layui.form;
        form.render();
    });
    $(function () {
        $("#layui-btn").click(function () {
            $("#zhe").css("display", "block");
        });
        $("#yinCang").click(function () {
            $("#zhe").css("display", "none");
        });
    })

</script>
<script type="text/javascript">
    //data
    var lyric = [{
        'name': "告白气球-周杰伦",
        'img': 'img/photo1.jpg',
        'audio_src': 'music/gaobaiqiqiu.mp3',
        'content': lrc_content
    }]
    var songIndex = 0;
    var lyric_txt = document.getElementById("lyric_txt");



    $(function () {
        //var div=$("#zhe");
        /* 暂停播放 */
        //audioFn.stopAudio();

        /* 开启播放 */
        //audioFn.playAudio();

        /* 选择歌单中索引为3的曲目(索引是从0开始的)，第二个参数true立即播放该曲目，false则不播放 */
        //audioFn.selectMenu(3,true);

        /* 查看歌单中的曲目 */
        //console.log(audioFn.song);

        /* 当前播放曲目的对象 */
        //console.log(audioFn.audio);
    });
</script>
<script>

</script>
<script type="text/javascript" src="${basePath}/js/MyMusic.js"></script>
</body>

</html>