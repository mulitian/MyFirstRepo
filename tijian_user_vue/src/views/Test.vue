<template>
    <!-- 总容器 -->
    <div class="wrapper">
        <header>
            <i class="fa fa-angle-left" onclick="history.go(-1)"></i>
            <p>请您选择体检机构</p>
            <div></div>
        </header>
        <div class="top-ban"></div>

        <ul class="hospital">
            <li>
                <h3 onclick="location.href='setmeal.html'">
                    沈阳熙康云医院-和平院区
                    <i class="fa fa-angle-right"></i>
                </h3>
                <div class="hospita-info">
                    <img src="../assets/img/hospita1.png">
                    <table>
                        <tr>
                            <td>营业时间</td>
                            <td>周一至周五 7:30-15:30 （周六截止12:00）</td>
                        </tr>
                        <tr>
                            <td>采血截止</td>
                            <td>采血截止时间 10:30</td>
                        </tr>
                        <tr>
                            <td>电话</td>
                            <td>52433456</td>
                        </tr>
                        <tr>
                            <td>地址</td>
                            <td>文体路7号世贸大厦商都（五里河茶城）四楼西区</td>
                        </tr>
                    </table>
                </div>
                <div class="about">
                    <p>
                        <i class="fa fa-phone"></i>
                        联系我们
                    </p>
                    <p>
                        <i class="fa fa-map-marker"></i>
                        查找位置
                    </p>
                </div>
            </li>
            <li>
                <h3 onclick="location.href='setmeal.html'">
                    沈阳熙康云医院-浑南院区
                    <i class="fa fa-angle-right"></i>
                </h3>
                <div class="hospita-info">
                    <img src="../assets/img/hospita2.png">
                    <table>
                        <tr>
                            <td>营业时间</td>
                            <td>周一至周六 7:30-11:30 </td>
                        </tr>
                        <tr>
                            <td>采血截止</td>
                            <td>10:30</td>
                        </tr>
                        <tr>
                            <td>电话</td>
                            <td>52713658</td>
                        </tr>
                        <tr>
                            <td>地址</td>
                            <td>创新路175号（与智慧大厦交汇处）</td>
                        </tr>
                    </table>
                </div>
                <div class="about">
                    <p>
                        <i class="fa fa-phone"></i>
                        联系我们
                    </p>
                    <p>
                        <i class="fa fa-map-marker"></i>
                        查找位置
                    </p>
                </div>
            </li>
        </ul>

        <div class="bottom-ban"></div>
        <footer>
            <ul>
                <li onclick="location.href='index.html'">
                    <i class="fa fa-home"></i>
                    <p>云医院</p>
                </li>
                <li>
                    <i class="fa fa-opencart"></i>
                    <p>商城</p>
                </li>
                <li>
                    <i class="fa fa-compass"></i>
                    <p>发现</p>
                </li>
                <li onclick="location.href='personal.html'">
                    <i class="fa fa-user"></i>
                    <p>我</p>
                </li>
            </ul>
        </footer>
    </div>
</template>

<script setup>

</script>

<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
    width: 100%;
    height: 100%;
    background-color: #F9F9F9;
}

/*********************** header ***********************/
header {
    width: 100%;
    height: 15.7vw;
    background-color: #FFF;

    position: fixed;
    left: 0;
    top: 0;

    display: flex;
    align-items: center;
    justify-content: space-between;

    box-sizing: border-box;
    padding: 0 3.6vw;
}

header .fa {
    font-size: 8vw;
}

/*********************** footer ***********************/
footer {
    width: 100%;
    height: 14.2vw;
    box-sizing: border-box;
    border-top: solid 1px #E9E9E9;
    background-color: #FFF;

    position: fixed;
    left: 0;
    bottom: 0;
}

footer ul {
    width: 100%;
    height: 14.2vw;
    display: flex;
    align-items: center;
    justify-content: space-around;
}

footer ul li {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    font-size: 3vw;
    color: #999;

    user-select: none;
    cursor: pointer;
}

footer ul li .fa {
    font-size: 5vw;
}

/*********************** common样式 ***********************/
.top-ban {
    width: 100%;
    height: 15.7vw;
}

.bottom-ban {
    width: 100%;
    height: 14.2vw;
}

/*********************** hospital ***********************/
.hospital {
    width: 100%;
    margin-top: 3.6vw;
}

.hospital li {
    width: 92.8vw;
    margin: 0 auto;
    border: solid 1px #EEE;
    border-radius: 1vw;
    box-shadow: 2px 2px 5px rgba(0, 0, 0, .08);
    background-color: #FFF;
    margin-bottom: 3.6vw;

    box-sizing: border-box;
    padding: 4vw;
}

.hospital li h3 {
    box-sizing: border-box;
    padding-left: 2.4vw;
    border-left: solid 3px #157999;
    font-size: 4.3vw;
    display: flex;
    justify-content: space-between;

    user-select: none;
    cursor: pointer;
}

.hospital li h3 i {
    font-size: 5vw;
}

.hospital li .hospita-info {
    width: 100%;
    margin-top: 3vw;
    display: flex;
    justify-content: space-between;
}

.hospital li .hospita-info img {
    width: 22vw;
    height: 22vw;
}

.hospital li .hospita-info table {
    width: 59vw;
    font-size: 3.5vw;
    color: #666;
}

.hospital li .hospita-info table tr {
    height: 6vw;
}

.hospital li .hospita-info table tr td {
    vertical-align: top;
}

.hospital li .hospita-info table tr td:first-child {
    width: 15vw;
}

.hospital li .about {
    display: flex;
    justify-content: flex-end;
    margin-top: 2vw;
}

.hospital li .about p {
    width: 30vw;
    height: 8vw;
    border: solid 1px #157999;
    border-radius: 2vw;

    text-align: center;
    line-height: 8vw;
    margin-left: 2vw;

    font-size: 4vw;
    color: #157999;

    user-select: none;
    cursor: pointer;
}

.hospital li .about p i {
    color: #555;
    margin-right: 1vw;
}
</style>