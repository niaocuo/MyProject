<template>
  <div style=" text-align:center;">
    <el-row>
      <el-col :span="24">
        <index-carousel></index-carousel>
      </el-col>
    </el-row>
    <el-row style="padding-top: 5rem">
      <el-col :span="6" :offset="4"><img :src="root+introduce" style="height: 15rem" /></el-col>
      <el-col :span="8" :offset="1" >
        <div style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis; height: 12rem" v-html="aboutContent"></div>
        <div style="float: left;">
          <el-button @click="goAboutUs">了解更多</el-button>
        </div>
      </el-col>
    </el-row>
    <div style="margin-top: 3rem;padding-bottom:2rem; background-color: #444444">
      <div style="padding-top: 2rem;padding-bottom: 1rem">
        <span style="font-size: 1.5rem;color: white">产品展示</span>
        <div style="margin-top: 0.8rem">
          <span style="font-size: 1.2rem;color: white;">Products</span>
        </div>
      </div>
      <el-row>
        <el-col :span="16" :offset="4">
          <el-row  style="padding-top: 1rem" >
            <el-col :span="8"  v-for="(img,index)  in imgList" :key="index" style="margin-bottom:2rem">
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
    </div>
    <div style="margin-bottom: 2rem">
      <div style="margin-top: 2rem;margin-bottom: 2rem">
        <span style="font-size: 1.5rem;color:black">行业资讯</span>
        <div style="margin-top: 0.8rem">
          <span style="font-size: 1.2rem;color:black;">Industry information</span>
        </div>
      </div>
      <el-row>
        <el-col :span="16" :offset="4">
          <el-row   style="padding-top: 1rem" >
            <el-col :span="6" v-for="(information,index)  in informationList" :key="index" >
              <div class="imgList"  style="position:relative;margin-right: 0.3rem;margin-left: 0.3rem;cursor:pointer" @click="showInformation(information.id)">
                <img style="width: 100%;" :src="root+information.imageUrl"/>
                <div class="information" >
                  <div style="top: 30%;position:relative;">
                    <div style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis;font-size: 1rem;width:99%;">
                      {{information.title}}
                    </div>
                    <div style="padding-top: 0.2rem;line-height:1.4rem;font-size: 0.8rem;overflow: hidden;height: 2.5rem;">
                      <div v-html="information.content"></div>
                    </div>
                  </div>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  import indexCarousel from './components/indexCarousel.vue';
  export default {
    name: 'home',
    data(){
        return {
          root:api,
          introduce: '',
          aboutContent:'',
          imgList:[],
          informationList: []
        }
    },
    methods:{
      getAboutUs(){
        this.$http.get(api+'/getAboutUs').then(function(response){
          if(response.status='200'){
            this.aboutContent = response.data.content;
            if (response.data.imageUrl!=null) {
              this.introduce=response.data.imageUrl
            }
          }
        });
      },
      getProduct(){
        this.$http.post(api+'/getProductPage',
          {
            currentPage:1,
            pageSize:6,
            queryCondition:{imageId:'is not null'}
          }).then(function(response){
          if(response.data.code='200'){
              this.imgList =response.data.tableData;
          }
        });
      },
      getInformation(){
        this.$http.post(api+'/getInformationPage',
          {
            currentPage:1,
            pageSize:4,
            queryCondition:{imageId:'is not null'}
          }).then(function(response){
          if(response.data.code='200'){
           this.informationList=response.data.tableData;
          }
        });
      },
      goAboutUs(){
        this.$emit('routerPush','/index/aboutUs');
        this.$router.push({path:'/index/aboutUs'});
      },
      showInformation(id){
        this.$router.push({path:'/index/showInformation', query:{id:id}});
      },
      showProduct(id){
        this.$router.push({path:'/index/showProduct', query:{id:id}});
      }
    },
    components:{
      'index-carousel': indexCarousel
    },
    mounted () {
      this.getAboutUs();
      this.getProduct();
      this.getInformation();
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

  .imgList:hover .information{
    height: 100%;
  }
  .information{
    position:absolute;
    z-indent:2;
    left:0;
    bottom: 2px;
    background-color: black;
    width: 100%;
    height: 2rem;
    -moz-opacity:0.6;
    opacity: 0.6;
    color: white
  }
</style>
