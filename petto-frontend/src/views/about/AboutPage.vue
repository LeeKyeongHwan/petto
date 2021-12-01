<template>
 <div class="gallery">
  <div class="gallery-wrap">
    <div class="item item-1"></div>
    <div class="item item-2"></div>
    <div class="item item-3"></div>
    <div class="item item-4"></div>
    <div class="item item-5"></div>
</div>
</div>
</template>

<script>

</script>

<style lang="scss">
html,body{
  width: 100%;
  height: 100%;
}

.gallery{
  align-items: center;
  box-sizing: border-box;
  max-width: 100%;
  height: auto;
}


.gallery-wrap {
  display: flex;
  flex-direction: row;
  width: 100%;
  height: 70vh;
}

.item {
  flex: 1;
  height: 100%;
  background-position: center;
  background-size: cover;
  background-repeat: none;
  transition: flex 0.8s ease;
  
  &:hover{
    flex: 7;
  }
}

.item-1 { 
  background-image: url("../../assets/about/about03.jpg");
}

.item-2 { 
  background-image: url("../../assets/about/about01.jpg");
}

.item-3 { 
  background-image: url("../../assets/about/about02.jpg");
}

.item-4 { 
  background-image: url("../../assets/about/about04.jpg");
}

.item-5 { 
  background-image: url("../../assets/about/about05.jpg");
}

.social{
  position: absolute;
  right: 35px;
  bottom: 0;
  
  img{
    display: block;
    width: 32px;
  }
}
</style>