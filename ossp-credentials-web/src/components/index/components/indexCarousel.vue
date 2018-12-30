<template>
  <el-carousel trigger="click"  height="36rem">
    <el-carousel-item v-for="(item,index)  in items":key="item.index">
      <div  v-bind:style="{backgroundImage:'url('+root+item.url + ')', backgroundPosition:'50% 50%',backgroundRepeat:'cover',backgroundSize:'cover',opacity:'1'}">
        <el-row >
          <el-col :span="15" :offset="6">
            <div  style="height: 36rem;margin-top: 10rem">
              <div v-if="item.title!=null&&item.title!='' " style="height: 12rem ; width: 45rem;  z-index:2; background-color: #000000;filter:alpha(Opacity=60); -moz-opacity:0.6;opacity: 0.6;">
                <div>
                  <span style="line-height:1.5;">
                    <span style="color:#ffffff">
                      <span style="font-size:4rem">
                        <span style="font-family:Microsoft JhengHei">
                          {{item.title}}
                        </span>
                      </span>
                    </span>
                  </span>
                  <br/>
                  <span>
                    <span style="font-size:2rem"><span style="color:#ffffff">
                      {{item.detail}}
                      <span style="font-family:Microsoft JhengHei"></span>
                    </span>
                    </span>
                  </span>
                </div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-carousel-item>
  </el-carousel>

</template>
<script>
  export default {
    name: 'indexCarousel',
    data() {
      return {
        items:[],
        root:api
      };
    },
    mounted () {
      this.$http.get(api+'/getHomeList').then(function(response){
        if(response.data.code='200'){
          this.items = response.data;
        }
      });
    }
  }
</script>

