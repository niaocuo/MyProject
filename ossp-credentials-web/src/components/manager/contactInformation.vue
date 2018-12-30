<template>
  <div style="text-align:center;margin-top: 2rem">
    <el-row>
      <el-col :span="10" :offset="6">
        <el-form ref="form"  :model="form" label-width="100px">
          <el-form-item prop="phone" label="电话">
            <el-input v-model="form.phone"></el-input>
          </el-form-item>

          <el-form-item  prop="email" label="邮箱" :rules="[{ required: true, message: '请输入邮箱地址', trigger: 'blur' },{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]">
            <el-input v-model="form.email" ></el-input>
          </el-form-item>

          <el-form-item label="传真">
            <el-input v-model="form.fax"></el-input>
          </el-form-item>

          <el-form-item label="地址">
            <el-input v-model="form.address"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="onSubmit">保存</el-button>
            <el-button>取消</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name:"contactInformation",
    inject: ['reload'],
    data() {
      return {
        form: {
          phone: '',
          email: '',
          fax: '',
          address: '',
        }
      }
    },
    methods: {
      onSubmit() {
        this.$http.post(api+'/manager/saveContactUs',this.form).then(function(response){
          if(response.data.code='200'){
            this.reload();
          }
        });
      }
    },
    mounted () {
      this.$http.get(api+'/manager/getContactUs').then(function(response){
        if(response.status='200'){
            this.form = response.data;
        }
      });
    }
  }
</script>
