<template>
  <el-container class="manager-main">
    <el-header :height="elHeader">
      <el-dropdown style="float: right" @command="handleCommand">
        <span class="el-dropdown-link">{{userName}}<i class="el-icon-arrow-down el-icon--right"></i></span >
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>个人中心</el-dropdown-item>
          <el-dropdown-item>账号设置</el-dropdown-item>
          <el-dropdown-item command="logout">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    <el-container>
      <el-aside width="210px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-active="activeIndex" :router="true"
                 class="el-menu-vertical-demo"
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b">
          <el-menu-item index="/manager/aboutCompany">
            <i class="el-icon-menu"></i>
            <span slot="title">关于我们</span>
          </el-menu-item>
          <el-menu-item index="/manager/pictureHome">
            <i class="el-icon-picture"></i>
            <span slot="title">主页图片</span>
          </el-menu-item>
          <el-menu-item index="/manager/productDisplay">
            <i class="el-icon-document"></i>
            <span slot="title">产品展示</span>
          </el-menu-item>
          <el-menu-item index="/manager/industryInformation">
            <i class="el-icon-news"></i>
            <span slot="title">行业资讯</span>
          </el-menu-item>
          <el-menu-item index="/manager/contactInformation">
            <i class="el-icon-edit-outline"></i>
            <span slot="title">联系我们</span>
          </el-menu-item>
          <el-menu-item index="/manager/message">
            <i class="el-icon-message"></i>
            <span slot="title">来访信息</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view @routerPush="routerPush" />
        </el-main>
        <el-footer :height="elHeader">上海庆恒电子有限公司</el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: 'manager',
    data () {
      return{
        active:false,
        activeIndex: '',
        userName:window.sessionStorage.getItem('loginName'),
        elHeader:'60px',
      }
    },
    methods: {
      handleCommand(command) {
        if("logout"==command){
          this.$http.post(api+'/logout').then(function (response) {
            window.sessionStorage.removeItem("loginName");
            this.$router.push({path:response.data.code});
          });
        }
      },
      routerPush(url){
        this.activeIndex = url;
      }
    },
    mounted(){
      this.activeIndex = this.$route.path;
    }
  }
</script>
<style scoped>
  .manager-main {
    height:100%;
  }
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-menu{
    height: 100%;
  }
  a{
    text-decoration: none;
    color: #fff;
  }
</style>
