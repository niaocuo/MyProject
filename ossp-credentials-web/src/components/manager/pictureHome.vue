<template>
  <div style="text-align:center;">
    <el-row>
      <el-col :span="7" :offset="8">
        <el-alert
          title="上传后点击图片大图编辑标题哦！"
          type="success"
          show-icon>
        </el-alert>
      </el-col>
    </el-row>
    <el-form ref="form" :model="form" label-width="80px" style="margin-top: 5rem">
      <el-row>
      <el-col :span="18" :offset="3">
        <imge-list style="float:left;" :files="oldFiles" :handlePreview="handlePictureCardPreview" @remove="removeOldFile" />
        <el-upload style="text-align:left;" ref="uploadPicture"
                   :action="upload"
                   list-type="picture-card"
                   :before-upload="beforeAvatarUpload"
                   :on-preview="handlePictureCardPreview"
                   :on-success="handleSuccess"
                   :with-credentials="true">
          <i class="el-icon-plus" ></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" v-if="showFile.id!=null" :src="root+showFile.url" >
          <img width="100%" v-else :src="showFile.url" >
          <div style="margin-top: 1rem">
            <el-form-item label="标题：">
              <el-input v-model="showFile.title" placeholder="请输入标题"></el-input>
            </el-form-item>
            <el-form-item label="描述：">
              <el-input v-model="showFile.detail" placeholder="请输入描述"></el-input>
            </el-form-item>
          </div>
        </el-dialog>
      </el-col>
      </el-row>
      <el-row>
        <el-button type="primary" @click="onSubmit">确定</el-button>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import imgeList from './components/imgeList.vue';
  export default {
    components: {
      imgeList,
    },
    inject: ['reload'],
    data () {
      return {
        form:{},
        showFile:{},
        dialogVisible: false,
        upload:api + '/manager/uploadPicture',
        root:api,
        oldFiles:[],
        newFiles:[]
      }
    },
    methods: {
      init(){
        this.$http.get(api+'/manager/getHomeAll').then(function(response){
            if(response.data.code='200'){
                this.oldFiles = response.data;
            }
        });
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
        if (isJPG||isPNG) {
          return true;
        }else{
          this.$message.error('上传头像图片是 JPG、PNG 格式!');
          return false;
        }
      },
      handlePictureCardPreview(file) {
        this.showFile=file;
        this.dialogVisible = true;
      },
      handleSuccess(response, file, fileList){
        if(response.code!='200'){
          this.$message.error('上传失败');
          this.$refs.uploadPicture.handleRemove(file, fileList);
        }else{
          file.realUrl=response.data;
          this.newFiles.push(file)
        }
      },
      removeOldFile(file) {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post(api+'/manager/deleteHomeById',
            { id:file.id},
            {emulateJSON:true}).then(function(response){
            if(response.data.code='200'){
              this.oldFiles.splice(this.oldFiles.indexOf(file), 1);
            }
          });
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      onSubmit(){
        let newFileList=[].concat(this.oldFiles);
        for(let file of this.newFiles) {
          let item={url:file.realUrl};
          if(file.hasOwnProperty('title') ){
            item.title = file.title
          }
          if(file.hasOwnProperty('detail') ){
            item.detail = file.detail
          }
          newFileList.push(item);
        }

        this.$http.post(api+'/manager/saveHome',
          { homeDate:JSON.stringify(newFileList)},
          {emulateJSON:true}).then(function(response){
          if(response.data.code='200'){
            this.reload();
          }
        });
      }
    },
    mounted () {
      this.init();//创建地图
    }
  }
</script>
