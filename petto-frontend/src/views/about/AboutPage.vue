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
  background-image: url("../../assets/about/about02.jpg");
}

.item-3 { 
  background-image: url("../../assets/about/about03.jpg");
}

.item-4 { 
  background-image: url('https://images.unsplash.com/photo-1510425463958-dcced28da480?auto=format&fit=crop&w=1352&q=80');
}

.item-5 { 
  background-image: url('https://images.unsplash.com/photo-1503602642458-232111445657?auto=format&fit=crop&w=1234&q=80');
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