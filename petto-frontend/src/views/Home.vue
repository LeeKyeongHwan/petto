<template>
    <div class="home">
        <div id="header">
            <!-- <petto-home/> -->
            <main-page/>
        </div>
    </div>
</template>

<script>
// import PettoHome from './PettoHome.vue'
import MainPage from '../components/mainHome/MainPage.vue'

  export default {
    name: 'Home',

    components: {
        // PettoHome,
        MainPage
    },
  }
</script>
