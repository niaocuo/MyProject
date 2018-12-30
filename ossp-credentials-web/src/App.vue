<template>
  <div id="app">
    <router-view v-if="isRouterAlive"/>
  </div>
</template>

<script>
  export default {
    name: 'App',
    provide(){
      return{
        reload:this.reload
      }
    },
    data(){
      return{
        isRouterAlive:true,
      }
    },
    methods: {
      reload(){
        this.isRouterAlive = false;
        this.$nextTick(function () {
          this.isRouterAlive = true;
        })
      }
    }
  }
</script>

<style>
  body {
    margin: 0;
    padding: 0;
  }
  #app {
    position: absolute;
    top: 0;
    bottom: 0;
    width: 100%;
  }

</style>
