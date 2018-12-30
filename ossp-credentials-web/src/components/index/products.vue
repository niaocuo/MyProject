<template>
  <div style=" text-align:center;">
    <el-row>
      <el-col :span="24">
        <carousel></carousel>
      </el-col>
    </el-row>

    <div style="padding-bottom: 2rem">
      <div style="padding-top: 2rem;padding-bottom: 2rem">
        <span style="font-size: 1.5rem;color:black">产品展示</span>
        <div style="margin-top: 0.8rem">
          <span style="font-size: 1.2rem;color:black;">Products</span>
        </div>
      </div>
      <el-row>
        <el-col :span="15" :offset="4">
          <el-row   style="padding-top: 1rem" >
            <el-col :span="8" v-for="(img,index)  in imgList" :key="index">
              <div class="imgList"  style="margin-right: 1rem;margin-left: 1rem;" @click="showProduct(img.id)">
                <img width="100%" :src="root+img.imageUrl"/>
              </div>
              <div style="background-color: white;margin-right: 1rem;margin-left: 1rem;padding-top: 0.5rem;padding-bottom: 0.1rem">
                <p style="margin-top: 0.5rem">{{img.title}}</p>
              </div>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <el-row v-if="showButton">
        <el-col :span="15" :offset="4">
          <div class="demo-block-control"  @click="viewMore" style="left: 0px;">
            <button type="button" class="el-button control-button el-tooltip el-button--text el-button--small">
          <span>
          查看更多
          </span>
            </button>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  import carousel from './components/carousel.vue';
  export default {
    name:"aboutUs",
    data() {
      return{
        root:api,
        pageSize:3,
        showButton:true,
        imgList:[]
      }
    },
    methods:{
      getProduct(){
        this.$http.post(api+'/getProductPage',
          {
            currentPage:1,
            pageSize:this.pageSize,
            queryCondition:{imageId:'is not null'}
          }).then(function(response){
          if(response.data.code='200'){
            this.imgList =response.data.tableData;
          }
        });
      },
      viewMore(){
          this.showButton=false;
          this.pageSize=12;
          this.getProduct();
      },
      showProduct(id){
        this.$router.push({path:'/index/showProduct', query:{id:id}});
      }
    },
    components:{
      'carousel': carousel
    },
    mounted () {
      this.getProduct();
    }
  }
</script>
<style scoped>
  .imgList{
    overflow: hidden;
  }
  .imgList img{
    cursor: pointer;
    transition: all 0.6s;
  }
  .imgList:hover img{
    transform: scale(1.3);
  }

  .demo-block-control  {
    text-align:center;
    border:1px solid #a1a1a1;
    padding:10px 40px;
    border-radius:20px;
    -moz-border-radius:20px;
  }
</style>
