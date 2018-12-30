<template>
  <el-container>
    <el-header>网站登陆</el-header>
    <el-main>
      <el-form ref="form" :model="form" action="/login" label-width="80px">
        <el-row >
          <el-col :span="2" :offset="8"><div class="grid-content bg-purple">用户名:</div></el-col>
          <el-col :span="6" ><div class="grid-content bg-purple-light"> <el-input v-model="form.userName" placeholder="请输入用户名" clearable></el-input></div></el-col>
        </el-row>
        <el-row>
          <el-col :span="2" :offset="8"><div class="grid-content bg-purple">密码:</div></el-col>
          <el-col :span="6"><div class="grid-content bg-purple-light"><el-input v-model="form.password" type="password" placeholder="请输入密码" clearable></el-input></div></el-col>
        </el-row>
        <el-row>
          <el-col style="margin-top: 2rem" >
            <el-button type="primary" @click="login">登陆</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-main>
    <el-footer>上海庆恒电子有限公司</el-footer>
  </el-container>
</template>
<script>
  export default {
    name: 'login',
    data () {
      return {
        form:{
          userName:'',
          password:''
        }
      }
    },

    methods:{
      login:function(){
        this.$http.post(api+'/login',this.form).then(function(response){
          console.log(response);
          if(response.data.code=="200"){
            window.sessionStorage.setItem("loginName",response.data.name);
            this.$router.push({path:response.data.path});
          }else{
            this.$message.error(response.data.err);
          }
        });
      }
    }
  }
</script>
<style scoped>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    padding-top: 120px;
    text-align: center;
    line-height: 50px;
    min-height: 478px;
  }
  .el-container {
    height:100%;
  }
</style>
