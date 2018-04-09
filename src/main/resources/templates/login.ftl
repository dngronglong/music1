<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="${basePath}/css/reset.css" />
    <link rel="stylesheet" href="${basePath}/css/login.css" />
    <script type="text/javascript" src="${basePath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${basePath}/js/login.js"></script>
</head>
<body>
<div class="page">
    <div class="loginwarrp">
        <div class="logo">用户登陆</div>
        <div class="login_form">
            <form id="Login" name="Login" method="post" onsubmit="" action="${basePath}/login">
                <li class="login-item">
                    <span>用户名：</span>
                    <input type="text" id="username" name="userName" class="login_input" >
                    <span id="count-msg" class="error"></span>
                </li>
                <li class="login-item">
                    <span>密　码：</span>
                    <input type="password" id="password" name="password" class="login_input" >
                    <span id="password-msg" class="error"></span>
                </li>
                <li class="login-item verify">
                    <span>验证码：</span>
                    <input type="text" name="CheckCode" class="login_input verify_input">
                </li>
                <img src="${basePath}/code" border="0" class="verifyimg" onclick="changeImg()" />
                <div class="clearfix"></div>
                <li class="login-sub">
                    <input type="submit" name="Submit" value="登录" />
                    <input type="reset" name="Reset" value="重置" /><br>

                </li>

            </form>

        </div>
        <a href="${basePath}/registe">没有账号？</a>
    </div>
</div>
<script type="text/javascript">
    function changeImg() {
        //alert(11);
        var imgSrc = $(".verifyimg");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }

    // 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(src) {
        var timestamp = (new Date()).valueOf();
        src = src.substring(0, 30);
        if ((src.indexOf("&") >= 0)) {
            src = src + "×tamp=" + timestamp;
        } else {
            src = src + "?timestamp=" + timestamp;
        }
        return src;
    }
    window.onload = function() {
        var config = {
            vx : 4,
            vy : 4,
            height : 2,
            width : 2,
            count : 100,
            color : "121, 162, 185",
            stroke : "100, 200, 180",
            dist : 6000,
            e_dist : 20000,
            max_conn : 10
        }
        CanvasParticle(config);
    }
</script>
<script type="text/javascript" src="${basePath}/js/canvas-particle.js"></script>
</body>
</html>