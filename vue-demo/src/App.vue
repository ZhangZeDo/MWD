<template>
  <div id="app">
    <Login/>
  </div>
</template>

<script>
import Login from './views/login/index.vue'

export default {
  name: 'app',
  components: {
      Login
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

}
</style>
