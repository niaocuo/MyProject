<template>
  <div style=" text-align:center;">
    <el-row>
      <el-col :span="24">
        <carousel></carousel>
      </el-col>
    </el-row>
    <div style="margin-bottom: 2rem">
      <div style="padding-top: 2rem;padding-bottom: 2rem">
        <span style="font-size: 1.5rem;color:black">联系我们</span>
        <div style="margin-top: 0.8rem">
          <span style="font-size: 1.2rem;color:black;">Contact us</span>
        </div>
      </div>
      <el-row style="margin-top:2rem" >
        <el-col :span="4" :offset="4">
          <span style="font-size: 1.2rem;color:black">电话</span>
          <div style="margin-top: 0.5rem">
            <span >{{contactUs.phone}}</span>
          </div>
        </el-col>
        <el-col :span="4" >
          <span style="font-size: 1.2rem;color:black">邮箱</span>
          <div style="margin-top: 0.5rem">
            <span >{{contactUs.email}}</span>
          </div>
        </el-col>
        <el-col :span="4" >
          <span style="font-size: 1.2rem;color:black">传真</span>
          <div style="margin-top: 0.5rem">
            <span >{{contactUs.fax}}</span>
          </div>
        </el-col>
        <el-col :span="4" >
          <span style="font-size: 1.2rem;color:black">地址</span>
          <div style="margin-top: 0.5rem">
            <span >{{contactUs.address}}</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-row style="margin-bottom: 2rem">
      <el-col :span="8" :offset="4">
        <div id="allmap" style="height: 24rem;width: 100%"></div>
      </el-col>
      <el-col :span="8" >
        <el-form ref="form"  :model="form" label-width="100px" >
          <el-form-item>
            <el-input style="line-height: 1rem" placeholder="留言标题" v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input  type="textarea" :rows="6" placeholder="留言内容" v-model="form.content" ></el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="form.email" placeholder="联系邮箱"></el-input>
          </el-form-item>
          <el-form-item >
            <el-col :span="16">
            <el-input v-model="form.code"  placeholder="验证码" ></el-input>
            </el-col>
            <el-col :span="7" :offset="1">
              <img width="100%" height="40px"  :src="imageUrl" @click="getCaptcha"/>
            </el-col>
          </el-form-item>
          <el-form-item style="text-align: right;">
            <el-button type="primary" @click="onSubmit" >提交</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import carousel from './components/carousel.vue'

  export default {
    name:"aboutUs",
    data() {
      return{
        //标注点数组
        markerArr : [{title:"上海庆恒电子有限公司",content:"",point:"121.472476|31.232847",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}],
        map: {},
        form:{
          title:'',
          content:'',
          email:'',
          code:''
        },
        contactUs:{
          address:'',
          email:'',
          fax:'',
          phone:'',
        },
        imageUrl:''
      }
    },
    methods:{
      getContactUs(){
        this.$http.get(api + '/getContactUs').then(function (response) {
          if (response.status = '200') {
            this.contactUs = response.data;
            if (response.data.imageUrl != null) {
              this.imageUrl = response.data.imageUrl
            }
          }
        });
      },
      //创建地图函数：
      createMap(){
        this.map = new window.BMap.Map('allmap');
        var point = new window.BMap.Point(121.468667,31.224571);
        this.map.centerAndZoom(point, 14);
      },
      //地图事件设置函数：
      setMapEvent(){
        this.map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        this.map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        this.map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        this.map.enableKeyboard();//启用键盘上下左右键移动地图
      },
      //地图控件添加函数：
      addMapControl(){
        var ctrl_nav = new window.BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
        this.map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
        var ctrl_ove = new window.BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
        this.map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
        var ctrl_sca = new window.BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
        this.map.addControl(ctrl_sca);
      },
      //创建marker
      addMarker(){
        for(var i=0;i<this.markerArr.length;i++){
          var json = this.markerArr[i];
          var p0 = json.point.split("|")[0];
          var p1 = json.point.split("|")[1];
          var point = new window.BMap.Point(p0,p1);
          var iconImg = this.createIcon(json.icon);

          var myIcon = new window.BMap.Icon("markers.png", new BMap.Size(23, 25), {
            // 指定定位位置。
            // 当标注显示在地图上时，其所指向的地理位置距离图标左上
            // 角各偏移10像素和25像素。您可以看到在本例中该位置即是
            // 图标中央下端的尖角位置。
            anchor: new BMap.Size(10, 25),
            // 设置图片偏移。
            // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您
            // 需要指定大图的偏移位置，此做法与css sprites技术类似。
            imageOffset: new window.BMap.Size(0, 0 - 1 * 25)   // 设置图片偏移
          });

          var marker = new window.BMap.Marker(point,{icon:iconImg});
          var iw = this.createInfoWindow(i);
          var label = new window.BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
          marker.setLabel(label);
          this.map.addOverlay(marker);
          label.setStyle({
            borderColor:"#808080",
            color:"#333",
            cursor:"pointer"
          });
          var $this=this;
          (function(){
            var index = i;
            var _iw = $this.createInfoWindow(i);
            var _marker = marker;
            _marker.addEventListener("click",function(){
              this.openInfoWindow(_iw);
            });
            _iw.addEventListener("open",function(){
              _marker.getLabel().hide();
            })
            _iw.addEventListener("close",function(){
              _marker.getLabel().show();
            })
            label.addEventListener("click",function(){
              _marker.openInfoWindow(_iw);
            })
            if(!!json.isOpen){
              label.hide();
              _marker.openInfoWindow(_iw);
            }
          })()
        }
      },
      //创建InfoWindow
      createInfoWindow(i){
        var json = this.markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
      },
      //创建一个Icon
      createIcon(json){
        var icon = new BMap.Icon("http://app.baidu.com/map/images/us_mk_icon.png", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
      },
      getCaptcha(){
        this.$http.get(api+'/captcha', {
            params:{ran: Math.random()},
            responseType: 'arraybuffer'
          }).then(response => {
          return 'data:image/png;base64,' + btoa(new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), ''))
          }).then(data => {
              this.imageUrl=data;
          })
      },
      onSubmit(){
        this.$http.post(api+'/saveMessage',this.form).then(function(response){
        if(response.data.code=='200'){
          this.$message({
            message: '提交成功！感谢您的支持',
            type: 'success'
          });
        }else{
          this.$message({
            message: response.data.message,
            type: 'warning'
          });
        }
        });
      }
    },
    components:{
      'carousel': carousel
    },
    mounted () {
      this.getContactUs();
      this.createMap();//创建地图
      this.setMapEvent();//设置地图事件
      this.addMapControl();//向地图添加控件
      this.addMarker();//向地图中添加marker
      this.getCaptcha();
    },
  }
</script>
