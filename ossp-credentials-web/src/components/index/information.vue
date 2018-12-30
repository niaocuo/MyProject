<template>
  <div style=" text-align:center;">
    <el-row>
      <el-col :span="24">
        <carousel></carousel>
      </el-col>
    </el-row>
    <div style="padding-bottom: 2rem">
      <div style="padding-top: 2rem;padding-bottom: 2rem">
        <span style="font-size: 1.5rem;color:black">行业资讯</span>
        <div style="margin-top: 0.8rem">
          <span style="font-size: 1.2rem;color:black;">Industry information</span>
        </div>
      </div>
      <el-row>
        <el-col :span="15" :offset="4">
          <el-row   style="padding-top: 1rem" >
            <el-col :span="6" v-for="(information,index)  in informationList" :key="index" >
              <div class="imgList"  style="position:relative;margin-right: 0.3rem;margin-left: 0.3rem;cursor:pointer" @click="showInformation(information.id)">
                <img style="width: 100%;" :src="root+information.imageUrl"/>
                <div class="information" >
                  <div style="top: 30%;position:relative;">
                    <div style="overflow: hidden;white-space: nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis;width:98%;">
                      <span style="font-size: 1rem">{{information.title}}</span>
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
      <el-row style="margin-top: 2rem">
        <el-pagination
          background
          layout="prev, pager, next"
          :page-size="page.pageSize"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :total="total">
        </el-pagination>
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
        page:{
          currentPage: 1,
          pageSize:4
        },
        total:0,
        informationList:[]
      }
    },
    watch:{
      page:{
        handler(val, oldVal){
          this.getInformation();
        }, deep:true
      }
    },
    methods:{
      handleSizeChange(val) {
        this.page.pageSize=val;
      },
      handleCurrentChange(val) {
        this.page.currentPage=val;
      },
      getInformation(){
        this.$http.post(api+'/getInformationPage',
          {
            currentPage:this.page.currentPage,
            pageSize:this.page.pageSize,
            queryCondition:{imageId:'is not null'}
          }).then(function(response){
          if(response.data.code='200'){
            this.informationList=response.data.tableData;
            this.total=response.data.total;
          }
        });
      },
      showInformation(id){
        this.$router.push({path:'/index/showInformation', query:{id:id}});
      }
    },
    components:{
      'carousel': carousel
    },
    mounted () {
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
