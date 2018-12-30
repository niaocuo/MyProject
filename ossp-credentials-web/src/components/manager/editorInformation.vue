<template>
  <div >
    <el-row>
      <el-col :span="9" :offset="8">
        <el-input placeholder="请输入名称" v-model="title" clearable></el-input>
      </el-col>
    </el-row>
    <div style="margin-top: 1rem;margin-bottom: 1rem">
      <ue v-if="showUe" ref="uEditor" :value="content"></ue>
    </div>
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
      <img width="100%"  :src="root+showFile.url" >
    </el-dialog>
    <el-row>
      <el-col :span="2" :offset="11">
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import ue from './components/UE.vue';
  export default {
    name: 'editorProduct',
    data () {
      return{
        showUe:false,
        id:'',
        title:'',
        root:api,
        upload:api + '/manager/uploadPicture',
        fileList: [],
        showFile:{},
        dialogVisible: false,
        content:''
      }
    },
    methods: {
      onSubmit(){
        var image={};
        if(this.fileList.length>0){
          image={
            name : this.fileList[0].name,
            url : this.fileList[0].url
          };
          if(this.fileList[0].hasOwnProperty('id')){
            image.id=this.fileList[0].id;
          }
        }
        var information={
          id:this.id,
          title:this.title,
          content:this.$refs.uEditor.getUEContent()
        };
        var url =api+'/manager/updateInformation';
        if(this.id==""){
          url =api+'/manager/addInformation';
        }
        this.$http.post(url,
          { image:JSON.stringify(image),
            information:JSON.stringify(information)
          },
          {emulateJSON:true}).then(function(response){
          if(response.data.code='200'){
            this.$router.push({path:'/manager/industryInformation'});
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
      },
    },
    components: {
      ue,
    },
    mounted () {
      if(Object.keys(this.$route.query).length!=0) {
        this.$http.get(api+'/manager/getInformationById',{params:{id: this.$route.query.id}}).then(function(response){
          if(response.data.code='200'){
            this.id = response.data.id;
            this.title = response.data.title;
            this.content = response.data.content;
            if (response.data.imageId!=null) {
              var image = {
                id: response.data.imageId,
                name: response.data.imageName,
                url: response.data.imageUrl
              };
              this.fileList.push(image);
            }
            this.showUe=true;
          }
        });
      }else{
        this.showUe=true;
      }
    }
  }
</script>
