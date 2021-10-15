<template>
    <div class="home">
        <div id="header">
            <router-link :to="{ name: 'Login' }"
                    class="nav-link"
                    active-class="active">
                login
            </router-link>
        </div>
    </div>
</template>

<script>

  export default {
    name: 'Home'
  }

</script>