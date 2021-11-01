<template>  
    <div id="main">
        <div id="header">
            <a href="/pettohome" class="logo"><h1>petto</h1></a>
            <div class="header-top">
                <v-btn plain router :to="{name: 'MemberLoginPage'}">LOGIN</v-btn>
                <v-btn plain router :to="{name: 'SignupPage'}">JOIN US</v-btn>
            </div>
            <div>
                <ul>
                    <li><a href="#">소개</a></li>
                    <li><a href="/abandonedAnimal">유기동물</a></li>
                    <li><a href="#">제보</a></li>
                    <li><a href="/animalVoluntary">자원봉사</a></li>
                    <li><a href="#">Q&A</a></li>
                </ul>
            </div>
        </div>
    </div>
</template>


<style lang="scss" scoped>
@font-face {
    font-family: 'Manse';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Manse.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

@font-face {
    font-family: 'GowunDodum-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

*
{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

#main {
    position: relative;
	padding: 5rem 2rem;
	color: white;
    background-color:#f5cc81b6; // 메뉴바 라인 확인용
}

#header{
    position: absolute;
    top:0;
    left: 0;
    width: 100%;
    padding: 15px 60px;
    justify-content: space-between;
    align-items: center;
}

.logo{
    color: #000;
    letter-spacing: 3px;
}

#header ul{
    display: flex;
    justify-content: center;
    align-items: center;
}

#header ul li {
    list-style: none;
    margin-left: 15px;
}

#header ul li a {
    text-decoration: none;
    padding: 6px 12px;
    color:#000;
    position: relative;
    font-family: 'GowunDodum-Regular';
    font-size: 21px;
    margin-left: 30px;
    margin-right: 30px;
}

#header ul li a::after{
    content: "";
    position: absolute;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    width: 0;
    height: 4px;
    background:#f7b43e;
    transition: all .5s ease-out;
}

#header ul li a:hover::after {
    width:100%;
}

h1 {
    font-family: 'Manse';
    font-size: 80px;
    text-shadow: 1px 1px 1px gray;
}

.header-top{
    position: absolute;
    top: 1em;
    list-style: none;
    right:2em;
}

</style>