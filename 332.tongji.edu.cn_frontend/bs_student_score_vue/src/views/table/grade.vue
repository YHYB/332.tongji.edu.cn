<template>
  <div>
    <br>
    <el-form :inline="true">
      <el-form-item label="年级名称">
        <el-input placeholder="年级名称" size="small" v-model="searchMap.grade_name"></el-input>
      </el-form-item>

      <el-button @click="fetchData()" icon="el-icon-search" size="small" type="primary">查询</el-button>
      <el-button @click="handleEdit('')" icon="el-icon-plus" size="small" type="primary">新增</el-button>
      <el-button @click="exportExcel()" icon="el-icon-download" size="small" type="primary">导出表格</el-button>
      <el-button @click="importExcel()" icon="el-icon-upload2" size="small" type="primary">批量导入</el-button>
    </el-form>
    <el-table
      :data="list"
      border
      size="medium"
      style="width: 100%">
      <el-table-column label="序列号" prop="id"></el-table-column>
      <el-table-column label="年级名称" prop="grade_name"></el-table-column>
      <el-table-column label="年级主任" prop="director"></el-table-column>
      <el-table-column label="联系方式" prop="contact_infor"></el-table-column>

      <el-table-column
        fixed="right"
        label="操作"
        width="200"
      >
        <template slot-scope="scope">
          <el-button @click="handleEdit(scope.row.id)" icon="el-icon-edit" size="small" type="primary">编辑</el-button>
          <el-button @click="handleDelete(scope.row.id)" icon="el-icon-delete" size="small" type="danger">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
    <br/>
    <el-pagination
      :current-page.sync="currentPage"
      :page-size="10"
      :page-sizes="[5,10,20]"
      :total="total"
      @current-change="fetchData"
      @size-change="fetchData"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :visible.sync="dialogFormVisible" title="编辑" width="40%">
      <el-form label-width="100px" style="margin-right: 30px">
        <el-form-item label="年级名称">
          <el-input size="medium" v-model="pojo.grade_name"></el-input>
        </el-form-item>
        <el-form-item label="年级主任">
          <el-input size="medium" v-model="pojo.director"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input size="medium" v-model="pojo.contact_infor"></el-input>
        </el-form-item>

      </el-form>
      <span class="dialog-footer" slot="footer">
      <el-button @click="dialogFormVisible = false" size="medium">关闭</el-button>
      <el-button @click="handleSave()" size="medium" type="primary">保存</el-button>
    </span>
    </el-dialog>
    <el-dialog :visible.sync="dialogUploadVisible" title="批量导入" width="40%">
      <el-upload
        :auto-upload="false"
        :file-list="fileList"
        :on-success="handleSuccess"
        action="http://localhost:9999/demo/grade/importExcel/"
        class="upload-demo"
        ref="upload">
        <el-button size="small" slot="trigger" type="primary">选取文件</el-button>
        <div class="el-upload__tip" slot="tip">只能上传.xls文件，且不超过10MB</div>
      </el-upload>
      <span class="dialog-footer" slot="footer">
    <el-button @click="dialogUploadVisible = false" size="medium">关闭</el-button>
    <el-button @click="submitUpload" size="small" style="margin-left: 10px;" type="success">开始导入</el-button>
    </span>
    </el-dialog>
  </div>
</template>
<script>
  import gradeApi from '@/api/grade'

  export default {
    data() {
      return {
        list: [],
        total: 0, // 总记录数
        currentPage: 1, // 当前页
        pageSize: 10, // 每页大小
        searchMap: {}, // 查询条件
        dialogFormVisible: false, // 编辑窗口是否可见
        dialogUploadVisible: false,
        fileList: [],
        pojo: {}, // 编辑表单绑定的实体对象
        cityList: [], // 城市列表
        id: '' // 当前用户修改的ID
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        gradeApi.search(this.currentPage, this.pageSize, this.searchMap).then(response => {
          this.list = response.data.rows;
          this.total = response.data.total
        })
      },
      handleSave() {
        gradeApi.update(this.id, this.pojo).then(response => {
          this.$message({
            message: response.message,
            type: (response.flag ? 'success' : 'error')
          });
          if (response.flag) { // 如果成功
            this.fetchData() // 刷新列表
          }
        });
        this.dialogFormVisible = false // 关闭窗口
      },
      handleEdit(id) {
        this.id = id;
        this.dialogFormVisible = true; // 打开窗口
        if (id !== '') { // 修改
          gradeApi.findById(id).then(response => {
            if (response.flag) {
              this.pojo = response.data
            }
          })
        } else {
          this.pojo = {} // 清空数据
        }
      },
      handleDelete(id) {
        this.$confirm('确定要删除此纪录吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          gradeApi.deleteById(id).then(response => {
            this.$message({message: response.message, type: (response.flag ? 'success' : 'error')});
            if (response.flag) {
              this.fetchData() // 刷新数据
            }
          })
        })
      },
      importExcel() {
        this.dialogUploadVisible = true
      },
      submitUpload() {
        this.$refs.upload.submit();
      },
      handleSuccess(response, file, fileList) {
        this.dialogUploadVisible = false;
        this.fileList = [];
        this.$message({
          message: response.message,
          type: (response.flag ? 'success' : 'error')
        });
        if (response.flag) {
          this.fetchData() // 刷新数据
        }
      },
      exportExcel() {
        gradeApi.exportExcel();
      }
    }
  }
</script>
