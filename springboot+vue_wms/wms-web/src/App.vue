<template>
  <div id="app">
<!--    <Index></Index>-->
<!--      <nav>-->
<!--          <router-link to="/">login</router-link>-->
<!--      </nav>-->
    <router-view/>
  </div>
</template>

<script>

// import Index from "@/components/Index";
export default {
    name: 'App',
    components: {
    // Index
    },
    data(){
        return{
            user:JSON.parse(sessionStorage.getItem('CurUser')),
        }
    },
    watch:{
      '$store.state.menu':{
          handler(val, old){
              // console.log(val, old) // val为menu表中的数据
              if(!old && this.user && this.user.no){
                  this.$store.commit("setRouter", val)
              }
          },
          immediate: true
      }
    }
}
</script>

<style>
#app {
  height: 100%;
  /*font-family: Avenir, Helvetica, Arial, sans-serif;*/
  /*-webkit-font-smoothing: antialiased;*/
  /*-moz-osx-font-smoothing: grayscale;*/
  /*text-align: center;*/
  /*color: #2c3e50;*/
  /*margin-top: 60px;*/
}
</style>
