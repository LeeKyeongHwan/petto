<template>
  <div>

    <div id="main">
      <div>
       <video src="@/assets/img/mains.mp4" muted loop autoplay class="video-main"/>
      </div>
       
      <div id="header">
        <a href="/pettohome" class="logo"><h1 style="color:white;">petto</h1></a>
        <div class="header-top">
        </div>
        <section>
            <div class="main-text">
                <div class="fade-in-box">
                    <span><h4>Dogs are not our whole life,</h4></span>
                    <span><h4>but they make our lives whole.</h4></span>
                    <span>
                      <button class="center-circle-hover"><a href="/pettohome" style="color:white;">
                        <h5>Go Petto</h5></a>
                      </button></span>
                </div>
            </div>
          </section>
          
      </div>
      
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      layers: false,
      isLogin: false,
      nav_drawer: false,
      group: false,
      auth: '',
    }
  }
};
</script>

<style lang="scss" scoped>


@font-face {
  font-family: "Manse";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Manse.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
@font-face {
  font-family: "GowunDodum-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

.video-main{ 
position: relative;
display: flex;
width: 110%;
height: 100vh;
object-fit: cover;

// background-size: cover;
}

// .overlay{
//    position: absolute;
//    top: 0;
//    left:0;
//    height: 100%;
//    width: 100%; 
//   //  background-size: cover;
//   //  background: {
//   //   image: linear-gradient(
//   //       to bottom,
//   //       rgba(172, 172, 172, 0.014) 0%,
//   //       rgba(0, 0, 0, 0.3) 100%
//   //   );
//   //  }
// }

.menu-btn{
    position: absolute;
    top:1em;
    right:15em;
    
}

#main {
  position: relative;
  min-height: 100%;
  display: flex;
  color: white;
  background: {
    image: linear-gradient(
        to bottom,
        rgba(172, 172, 172, 0.014) 0%,
        rgba(0, 0, 0, 0.6) 100%
      );
    size: cover;
  }
}


#header {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  padding: 15px 60px;
  justify-content: space-between;
  align-items: center;
}
.logo {
  color: #fff;
  letter-spacing: 3px;
}

h1 {
//   font-family: "Manse";
font-family: 'WandohopeB';
  font-size: 6vw;
  text-shadow: 2px 2px 2px gray;
}
.header-top {
  position: absolute;
  top: 1em;
  list-style: none;
  right: 2em;
}


h3{
    font-size: 2vw;
    color: black;
    font-family: "GowunDodum-Regular";
    margin: 0% 0% 1% 1%;
}

@font-face {
    font-family: 'WandohopeB';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/WandohopeB.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

// .main-text{
//     position: absolute;
//     right: 0%;
//     top:10%;
//     width: 100%;
//     min-height: 100vh;
//     padding: 4vw 4vw 0vw 4vw;
//     display: flex;
// }

h4{
  font-size:4.5vw;
  font-family: 'WandohopeB';
}
//
.fade-in-box {
  margin-top:25%;
  display: inline-block;
  color:white;
  // padding: 10px;
  animation: fadein 10s;
  -moz-animation: fadein 10s; /* Firefox */
  -webkit-animation: fadein 10s; /* Safari and Chrome */
  -o-animation: fadein 10s; 
}
@keyframes fadein {
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-moz-keyframes fadein { /* Firefox */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-webkit-keyframes fadein { /* Safari and Chrome */
    from {
        opacity:0;
    }
    to {
        opacity:1;
    }
}
@-o-keyframes fadein { /* Opera */
    from {
        opacity:0;
    }
    to {
        opacity: 1;
    }
}
//
//
//


button {
  appearance: none;
  -moz-appearance: none;
  -webkit-appearance: none;
  
  display: inline-block;
  position: relative;
  border: 5px solid white;
  width: 25vw;
  vertical-align: middle;
  color: white;
  z-index: 1;
  overflow: hidden;
  margin-top: 3%;
}

h5{
  font-size:2vw;
  font-family: 'WandohopeB';
  text-align: center;
  text-transform: uppercase;
  text-decoration: none;
  line-height: 4.5vw;
}

/**
* CENTER-CIRCLE-HOVER
**/
.center-circle-hover {
  -webkit-transition: color 0.4s ease-in-out;
  -moz-transition: color 0.4s ease-in-out;
  -ms-transition: color 0.4s ease-in-out;
  -o-transition: color 0.4s ease-in-out;
  transition: color 0.4s ease-in-out;
}

.center-circle-hover:before { /* transitions på :before og :after er ikke støttet i opera og safari ennå */
  content: '';
  display: block;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 0;
  padding-top: 0;
  z-index: -1;
  
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  
  
  border-radius: 100%;
  background: rgba(56, 56, 56, 0.5);
  
  -webkit-transition: all 0.4s ease-in-out;
  -moz-transition: all 0.4s ease-in-out;
  -ms-transition: all 0.4s ease-in-out;
  -o-transition: all 0.4s ease-in-out;
  transition: all 0.4s ease-in-out;
}

.center-circle-hover:hover {
  color: white;
}

.center-circle-hover:hover:before {
  width: 105%;
  padding-top: 105%;
}

</style>
