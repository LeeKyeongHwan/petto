<template>
  <v-app>
    <category v-if="($route.name !== 'PettoHome') && ($route.name !== 'Home')"/>
      <v-main>
        <router-view :key="$route.fullPath"/>
      </v-main>
    <petto-footer/>
  </v-app>
</template>

<script>
import Category from '@/views/Category.vue'
import PettoFooter from '@/views/PettoFooter.vue'

export default {
  name: 'App',
  components: {
    Category,
    PettoFooter
  }
}
</script>

<style>
/*
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
*/
</style>