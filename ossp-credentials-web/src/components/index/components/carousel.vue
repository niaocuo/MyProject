<template>
  <el-carousel trigger="click"  height="25rem">
    <el-carousel-item v-for="(item,index)  in items":key="item.index">
      <div  v-bind:style="{backgroundImage:'url(' +root+item.url +  ')', backgroundPosition:'50% 50%',backgroundRepeat:'cover',backgroundSize:'cover',opacity:'1', height: '24.3rem'}">
      </div>
    </el-carousel-item>
  </el-carousel>
</template>
  <script>
    export default {
      name: 'carousel',
      data() {
        return {
          root:api,
          items:[],
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
