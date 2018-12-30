<template>
  <div >
    <ue v-if="showUe" ref="uEditor" :value="content":config="config"></ue>
    <el-upload
      class="upload-demo"
      ref="uploadPicture"
      :action="upload"
      :before-upload="beforeAvatarUpload"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :file-list="fileList">
      <el-button size="small" type="primary">封面图片上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件!</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible">
      <img width="100%" :src="root+showFile.url" >
    </el-dialog>
    <div style="text-align: center;">
      <el-button type="primary" @click="onSubmit">确定</el-button>
    </div>
  </div>
</template>
<script>
  import ue from './components/UE.vue';
  export default {
    name:"aboutCompany",
    inject: ['reload'],
    data() {
      return{
        showUe:false,
        content:'',
        root:api,
        fileList: [],
        showFile:{},
        upload:api + '/manager/uploadPicture',
        dialogVisible: false,
        config:{
          serverUrl:api+"/uEditor/upload",
          initialFrameHeight: 380,
        }
      }
    },
    methods: {
      onSubmit(){
        var aboutUs={
          content: this.$refs.uEditor.getUEContent()
        };
        if(this.fileList.length>0){
          aboutUs.imageName=this.fileList[0].name;
          aboutUs.imageUrl=this.fileList[0].url;
        }
        this.$http.post(api+'/manager/saveAboutUs', {
            aboutUs:JSON.stringify(aboutUs),
          },
          {emulateJSON:true}).then(function(response){
          if(response.data.code='200'){
            this.reload();
          }
        });
      },
      beforeAvatarUpload(file) {
        if(this.fileList.length>0){
          this.$message.error('封面图片只能有一张');
          return false;
        }
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
        if (isJPG||isPNG) {
          return true;
        }else{
          this.$message.error('上传头像图片是 JPG、PNG 格式!');
          return false;
        }
      },
      handleRemove(file) {
        if(this.fileList.indexOf(file)>-1){
          this.fileList.splice(this.fileList.indexOf(file), 1);
        }
      },
      handlePreview(file) {
        this.showFile=file;
        this.dialogVisible = true;
      },
      handleSuccess(response, file, fileList){
        if(response.code!='200'){
          this.$message.error('上传失败');
          this.$refs.uploadPicture.handleRemove(file, fileList);
        }else{
          file.url=response.data;
          this.fileList.push(file)
        }
      }
    },
    components:{
      ue,
    },
    mounted () {
      this.$http.get(api+'/manager/getAboutUs').then(function(response){
        if(response.status='200'){
          this.content = response.data.content;
          if (response.data.imageUrl!=null) {
            var image = {
              name: response.data.imageName,
              url: response.data.imageUrl
            };
            this.fileList.push(image);
          }
        }
        this.showUe=true;
      });
    }
  }
</script>
