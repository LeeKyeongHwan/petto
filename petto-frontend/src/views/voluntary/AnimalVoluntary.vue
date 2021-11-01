<template>

  <div class="Voluntary" >  
    <h1> 봉사봉사 심봉사</h1>    
    <div class = "banner1">
    <image-shadow :width="240"  :src="this.src1" shadow-hover></image-shadow>
    </div>
    <div class = "banner2">
    <image-shadow :width="240" :src="this.src2" shadow-hover></image-shadow>
    </div>
    <div class = "banner3">
    <image-shadow :width="240" :src="this.src3" shadow-hover></image-shadow>
    </div>
    <div class = "banner4">
    <image-shadow :width="240" :src="this.src4" shadow-hover></image-shadow>
    </div>
    <div class = "banner5">
    <image-shadow :width="240" :src="this.src5" shadow-hover></image-shadow>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {  
    /* 각 객체의 profileUrl에 저장이 되어 있는 것은 단순 String 값이기 때문이다.
     이를 해결하기 위해서는 해당 경로를 import한다는 의미로 require를 사용*/
      src1: require("../../assets/voluntary/care.jpg"),
      src2: require("../../assets/voluntary/Home.jpg"),
      src3: require("../../assets/voluntary/move.jpg"),
      src4: require("../../assets/voluntary/Walk.jpg"),
      src5: require("../../assets/voluntary/Walk.jpg"),

    }
  }
}
</script>

<style>
.Voluntary{
    text-align: center;
    margin: 0 auto;
}

.banner1 {
    display:inline-block;zoom:1;
    margin: 0% 3% 2% 3%;
    align-items: center;
    box-sizing: border-box;
     max-width: 100%;
     height: auto;
}

.banner2{
    display:inline-block;zoom:1;
    margin: 0% 3% 2% 3%;
    align-items: center;
    box-sizing: border-box;
     max-width: 100%;
     height: auto;
}

.banner3{
    display:inline-block;zoom:1;
    margin: 0% 3% 2% 3%;
    align-items: center;
     max-width: 100%;
     height: auto;
}
.banner4{
    display:inline-block;zoom:1;
    margin: 0% 3% 2% 3%;
    align-items: center;
    box-sizing: border-box;
     max-width: 100%;
     height: auto;
}
.banner5{
    display:inline-block;zoom:1;
    margin: 0% 3% 2% 3%;
    align-items: center;
    box-sizing: border-box;
     max-width: 100%;
     height: auto;
}
</style>