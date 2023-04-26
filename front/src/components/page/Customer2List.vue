<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i> 客户管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input v-model="query.key" placeholder="请输入客户名称" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="handleSearch">查询</el-button>
      </div>
      <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="name" label="客户名称" align="center"></el-table-column>
        <el-table-column prop="phone" label="联系电话" align="center"></el-table-column>
        <el-table-column prop="address" label="详细地址" align="center"></el-table-column>
        <el-table-column prop="industry" label="所属行业" align="center"></el-table-column>
        <el-table-column prop="type" label="客户类型" align="center"></el-table-column>
        <el-table-column prop="atitle" label="市场活动" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
        <el-table-column label="操作" align="center" width="300" >
          <template slot-scope="scope">
            <el-button  type="primary" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div align="center">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pagesize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalCount">
        </el-pagination>
      </div>
    </div>

    <!-- 编辑弹出框 -->
    <el-dialog title="客户编辑" :visible.sync="editVisible" width="30%">
      <el-form ref="ruleForm" :model="eform" :rules="rules" label-width="90px">
        <el-form-item label="客户名称" prop="name">
          <el-input v-model="eform.name"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="eform.phone"></el-input>
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="eform.address"></el-input>
        </el-form-item>
        <el-form-item label="所属行业" prop="industry">
          <el-select v-model="eform.industry" placeholder="请选择所属行业">
            <el-option label="金融" value="金融"></el-option>
            <el-option label="IT" value="IT"></el-option>
            <el-option label="服饰" value="服饰"></el-option>
            <el-option label="建筑" value="建筑"></el-option>
            <el-option label="能源" value="能源"></el-option>
            <el-option label="化工" value="化工"></el-option>
            <el-option label="其他" value="其他"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户类型" prop="type">
          <el-select v-model="eform.type" placeholder="请选择客户类型">
            <el-option label="客户" value="客户"></el-option>
            <el-option label="合作伙伴" value="合作伙伴"></el-option>
            <el-option label="投资人" value="投资人"></el-option>
            <el-option label="竞争对手" value="竞争对手"></el-option>
            <el-option label="经销商" value="经销商"></el-option>
            <el-option label="供应商" value="供应商"></el-option>
            <el-option label="其他" value="其他"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="市场活动" prop="aid">
          <el-select v-model="eform.aid" placeholder="请选择市场活动" clearable="true">
            <el-option
                v-for="item in activitys"
                :key="item.id"
                :label="item.title"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit('ruleForm')">确 定</el-button>
            </span>
    </el-dialog>


    <!-- 添加弹出框 -->
    <el-dialog title="客户添加" :visible.sync="addVisible" width="30%">
      <el-form ref="ruleForm" :model="aform" :rules="rules" label-width="90px">
        <el-form-item label="客户名称" prop="name">
          <el-input v-model="aform.name"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="aform.phone"></el-input>
        </el-form-item>
        <el-form-item label="详细地址" prop="address">
          <el-input v-model="aform.address"></el-input>
        </el-form-item>
        <el-form-item label="所属行业" prop="industry">
          <el-select v-model="aform.industry" placeholder="请选择所属行业">
            <el-option label="金融" value="金融"></el-option>
            <el-option label="IT" value="IT"></el-option>
            <el-option label="服饰" value="服饰"></el-option>
            <el-option label="建筑" value="建筑"></el-option>
            <el-option label="能源" value="能源"></el-option>
            <el-option label="化工" value="化工"></el-option>
            <el-option label="其他" value="其他"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户类型" prop="type">
          <el-select v-model="aform.type" placeholder="请选择客户类型">
            <el-option label="客户" value="客户"></el-option>
            <el-option label="合作伙伴" value="合作伙伴"></el-option>
            <el-option label="投资人" value="投资人"></el-option>
            <el-option label="竞争对手" value="竞争对手"></el-option>
            <el-option label="经销商" value="经销商"></el-option>
            <el-option label="供应商" value="供应商"></el-option>
            <el-option label="其他" value="其他"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="市场活动" prop="aid">
          <el-select v-model="aform.aid" placeholder="请选择市场活动" clearable="true">
            <el-option
                v-for="item in activitys"
                :key="item.id"
                :label="item.title"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd('ruleForm')">确 定</el-button>
            </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data() {
    var validatePhone = (rule, value, callback) => {
      if (value && value != "") {
        if (
            !/^1[0-9]{10}$/.test(
                value
            )
        ) {
          callback(new Error("请输入正确的手机号码"));
        } else {
          callback();
        }
      } else {
        callback();
      }
    }
    return {
      query: {
        key: ''
      },
      tableData: [],
      multipleSelection: [],
      delList: [],
      editVisible: false,
      addVisible: false,
      eform: {},
      aform: {},
      pagesize: 10,
      currentPage: 1,
      totalCount: 0,
      rules: {
        name: [
          { required: true, message: '请输入客户名称', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { validator: validatePhone, trigger: 'change'}
        ],
        address: [
          { required: true, message: '请输入详细地址', trigger: 'blur' }
        ],
        industry: [
          { required: true, message: '请输入所属行业', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请输入客户类型', trigger: 'change' }
        ],
        aid: [
          { required: true, message: '请输入市场活动', trigger: 'change' }
        ],
      },
      activitys:[],
    };
  },
  created() {
    this.getData();
  },
  methods: {
    //获取活动
    getActivity() {
      this.$axios.post('/api/activity/selectByCondition',{}).then(res => {
        this.activitys = res.data;
      });
    },
    //每页显示数据量变更
    handleSizeChange: function(val) {
      this.pagesize = val;
      this.getData();
    },
    //页码变更
    handleCurrentChange: function(val) {
      this.currentPage = val;
      this.getData();
    },
    //数据来源
    getData() {
      var param = {
        status:'02',
        currentPage: this.currentPage, //页码
        pagesize: this.pagesize,  //每页显示的记录数
        name: this.query.key  //查询条件
      };
      this.$axios.post('/api/customer/selectAll',param).then(res => {
        this.tableData = res.data.list;
        this.totalCount = res.data.total;
      });
    },
    // 触发搜索按钮
    handleSearch() {
      this.getData();
    },
    // 删除操作
    handleDelete(index, row) {
      this.$confirm('确定要删除吗？', '提示', {
        type: 'warning'
      }).then(() => {
        this.$axios.get('/api/customer/deleteById?id=' + row.id).then(res => {
          this.$message.success('删除成功');
          this.tableData.splice(index, 1);
          this.getData();
        });
      }).catch(() => {
      });
    },
    // 编辑操作
    handleEdit(index, row) {
      this.getActivity();
      this.eform = JSON.parse(JSON.stringify(row));
      this.eform.aid = Number(this.eform.aid);
      this.editVisible = true;
    },
    // 保存编辑
    saveEdit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.editVisible = false;
          this.$axios.post('/api/customer/edit', this.eform).then(res => {
            if(res.data == '200'){
              this.$message.success("修改成功");
              this.getData();
            }else{
              this.$message.warning("修改失败");
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // 添加
    handleAdd() {
      this.getActivity();
      if (this.$refs.ruleForm !== undefined) this.$refs.ruleForm.resetFields();
      this.addVisible = true;
      this.aform = {};
    },
    // 保存添加
    saveAdd(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.aform.status = '02';
          this.$axios.post('/api/customer/add', this.aform).then(res => {
            if(res.data == '200'){
              this.$message.success("添加成功");
              this.addVisible = false;
              this.getData();
            }else{
              this.$message.warning("添加失败");
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    //转为客户
    shenpi(id){
      let vm = this;
      this.$confirm('确定要转为客户吗？', '提示', {
        type: 'warning'
      }).then(() => {
        this.$axios.post('/api/customer/edit', {"id":id,"status":"02"}).then(res => {
          if(res.data === 200){
            vm.getData();
            vm.$message.success("操作成功");
          }else{
            vm.$message.warning("操作失败");
          }
        });
      }).catch(() => {
      });
    },


  }
};
</script>

<style scoped>
.handle-box {
  margin-bottom: 20px;
}
.handle-input {
  width: 300px;
  display: inline-block;
}
.table {
  width: 100%;
  font-size: 14px;
}
.mr10 {
  margin-right: 10px;
}
.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}
</style>
