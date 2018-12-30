<template>
  <div style=" text-align:center;">
    <el-row>
      <el-col :span="24">
        <carousel></carousel>
      </el-col>
      <el-col :span="10">
        <span style="font-size: 0.8rem">首页&nbsp;&nbsp;>&nbsp;&nbsp;行业资讯&nbsp;&nbsp;>&nbsp;&nbsp;{{title}}</span>
      </el-col>
    </el-row>
    <el-row style="padding-top: 2rem">
      <el-col :span="16" :offset="4">
        <div >
          <p style="font-size: 1.4rem">{{title}}</p>
        </div>
        <div v-html="content"></div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import carousel from './components/carousel.vue';
  export default {
    name:"showProduct",
    data() {
      return{
        title:'',
        content:''
      }
    },
    methods:{
      getInformationById(){
        this.$http.get(api+'/getInformationById',{params:{id: this.$route.query.id}}).then(function(response){
          if(response.data.code='200'){
            this.title = response.data.title;
            this.content = response.data.content;
          }
        });
      }
    },
    components:{
      'carousel': carousel
    },
    mounted () {
      this.getInformationById();
    }
  }
</script>
