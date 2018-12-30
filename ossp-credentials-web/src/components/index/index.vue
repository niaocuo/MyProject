<template>
  <el-container>
    <el-header :height="height">
        <el-row >
          <el-col :span="2" :offset="4"><img title="logo_17" id="img_smv_tem_90_13" style="width: 8rem; height: 3rem; padding-top: 1rem" alt="logo_17" :src="logo" ></el-col>
          <el-col :span="16" :offset="2">
            <el-menu :default-active="activeIndex" :router="true"
                     class="el-menu-demo"
                     mode="horizontal"
                     background-color="#000000"
                     text-color="#ffffff"
                     active-text-color="#ffd04b" style=" padding-top: 0.6rem"  >
            <el-menu-item style="height: 65px;margin: 0 2rem 0 0" index="/index/home"><span style="font-size:18px" >首页</span></el-menu-item>
            <el-menu-item style="height: 65px;margin: 0 2rem 0 1rem" index="/index/aboutUs"><span style="font-size:18px" >关于我们</span></el-menu-item>
            <el-menu-item style="height: 65px;margin: 0 2rem 0 1rem" index="/index/products"><span style="font-size:18px" >产品展示</span></el-menu-item>
            <el-menu-item style="height: 65px;margin: 0 2rem 0 1rem" index="/index/information"><span style="font-size:18px" >行业资讯</span></el-menu-item>
            <el-menu-item style="height: 65px;margin: 0 0 0 1rem" index="/index/contactUs"><span style="font-size:18px" >联系我们</span></el-menu-item>
          </el-menu></el-col>
        </el-row>
    </el-header >
    <el-main>
      <router-view @routerPush="routerPush"/>
    </el-main>
    <el-footer :height="footHeight"><span>版权所有： 上海庆恒电子有限公司 备案号： 京ICP备2018xxxx号</span> </el-footer>
  </el-container>
</template>
<script>
  import Vue from 'vue'
  import indexCarousel from './components/indexCarousel.vue';
  export default {
    name: 'index',
    inject: ['reload'],
    data () {
      return {
        height:"75px",
        footHeight:"60px",
        activeIndex: '',
        logo: require("../../assets/logo.png"),
      }
    },
    methods:{
      routerPush(url){
        this.activeIndex=url;
        this.reload();
      }
    },
    mounted(){
      this.activeIndex = this.$route.path;
    }
  }
</script>
<style scoped>
  .el-header{
    background-color: #000000;
    color:rgba(255, 255, 255, 1);
    text-align: center;
    line-height:80px;
  }
  .el-footer {
    background-color: #000000;
    color:rgba(255, 255, 255, 1);
    text-align: center;
    line-height:60px;
  }
  .el-main {
    background-color: white;
    color: #333;
    text-align: center;
    padding: 0px;
  }
  .el-container {
    height:100%;
  }
</style>
