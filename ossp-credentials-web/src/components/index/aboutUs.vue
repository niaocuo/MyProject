<template>
  <div style=" text-align:center;">
    <el-row>
      <el-col :span="24">
        <carousel></carousel>
      </el-col>
    </el-row>
    <el-row style="padding-top: 2rem">
      <el-col :span="16" :offset="4">
        <div v-html="aboutContent"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import carousel from './components/carousel.vue';
  export default {
    name:"aboutUs",
    data() {
      return{
        root:api,
        aboutContent:'',
        imageUrl:''
      }
    },
    methods: {
      getAboutUs(){
        this.$http.get(api + '/getAboutUs').then(function (response) {
          if (response.status = '200') {
            this.aboutContent = response.data.content;
            if (response.data.imageUrl != null) {
              this.imageUrl = response.data.imageUrl
            }
          }
        });
      }
    },
    components:{
      'carousel': carousel
    },
    mounted () {
      this.getAboutUs();
    }
  }
  </script>
