<template>
  <div >
    <el-form :inline="true"  :model="formData" class="demo-form-inline" >
      <el-row style="margin-top: 1rem">
        <el-col style="text-align: right">
          <el-form-item >
            <el-input  placeholder="请输入留言标题" v-model="formData.title"><i slot="prefix" class="el-input__icon el-icon-search"></i></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="getTableData">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div style="margin-top: 1rem">
      <el-table :data="tableData"  border style="width: 100%">
        <el-table-column prop="title" label="留言标题"  width="250"></el-table-column>
        <el-table-column prop="content" label="留言内容" ></el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="250"></el-table-column>
        <el-table-column label="操作" width="60">
          <template slot-scope="scope" >
            <el-button @click="deleteClick(scope.row.id)" type="text" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="float: right">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="page.currentPage"
          :page-sizes="[5, 10, 20, 50]"
          :page-size="page.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>


<script>
  export default {
    name: 'productDisplay',
    inject: ['reload'],
    data () {
      return {
        formData: {
          title: ''
        },
        page:{
          currentPage: 1,
          pageSize:5
        },
        total:0,
        tableData: []
      }
    },
    watch:{
      page:{
        handler(val, oldVal){
          this.getTableData();
        }, deep:true
      }
    },
    methods: {
      getTableData(){
        this.$http.post(api+'/manager/getMessagePage',
          {
            currentPage:this.page.currentPage,
            pageSize:this.page.pageSize,
            queryCondition:{title:this.formData.title}
          }).then(function(response){
          if(response.data.code='200'){
            this.tableData=response.data.tableData;
            this.total=response.data.total;
          }
        });
      },
      handleSizeChange(val) {
        this.page.pageSize=val;
      },
      handleCurrentChange(val) {
        this.page.currentPage=val;
      },
      deleteClick(id){
        this.$confirm('确认删除此信息?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.post(api+'/manager/deleteMessage', id).then(function(response){
            if(response.data.code='200'){
              this.reload();
              this.$emit('routerPush','/index/aboutUs');
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
      }
    },
    mounted () {
      this.getTableData();//初始化列表
    }
  }
</script>
