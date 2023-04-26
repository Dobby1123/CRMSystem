<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 联系人管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.key" placeholder="请输入姓名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">查询</el-button>
                <el-button type="primary" @click="handleAdd" icon="el-icon-plus" >添加</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="name" label="姓名" align="center"></el-table-column>
                <el-table-column prop="phone" label="手机号" align="center"></el-table-column>
                <el-table-column prop="position" label="职务" align="center"></el-table-column>
                <el-table-column prop="depart" label="部门" align="center"></el-table-column>
                <el-table-column prop="wx" label="微信号" align="center"></el-table-column>
                <el-table-column prop="qq" label="QQ号" align="center"></el-table-column>
                <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
                <el-table-column prop="ctname" label="所属客户" align="center"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="160" align="center"></el-table-column>
                <el-table-column label="操作" align="center" width="200" >
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
        <el-dialog title="联系人编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="ruleForm" :model="eform" :rules="rules" label-width="90px">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="eform.name"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="eform.phone"></el-input>
                </el-form-item>
                <el-form-item label="职务" prop="position">
                    <el-input v-model="eform.position"></el-input>
                </el-form-item>
                <el-form-item label="部门" prop="depart">
                    <el-input v-model="eform.depart"></el-input>
                </el-form-item>
                <el-form-item label="微信号">
                    <el-input v-model="eform.wx"></el-input>
                </el-form-item>
                <el-form-item label="QQ号">
                    <el-input v-model="eform.qq"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" >
                    <el-input v-model="eform.email"></el-input>
                </el-form-item>
                <el-form-item label="所属客户" prop="cid">
                    <el-select v-model="eform.cid" placeholder="请选择所属客户" clearable="true">
                      <el-option
                          v-for="item in customers"
                          :key="item.id"
                          :label="item.name"
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
        <el-dialog title="联系人添加" :visible.sync="addVisible" width="30%">
            <el-form ref="ruleForm" :model="aform" :rules="rules" label-width="90px">
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="aform.name"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="aform.phone"></el-input>
                </el-form-item>
                <el-form-item label="职务" prop="position">
                    <el-input v-model="aform.position"></el-input>
                </el-form-item>
                <el-form-item label="部门" prop="depart">
                    <el-input v-model="aform.depart"></el-input>
                </el-form-item>
                <el-form-item label="微信号" >
                    <el-input v-model="aform.wx"></el-input>
                </el-form-item>
                <el-form-item label="QQ号" >
                    <el-input v-model="aform.qq"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" >
                    <el-input v-model="aform.email"></el-input>
                </el-form-item>
                <el-form-item label="所属客户" prop="cid">
                    <el-select v-model="aform.cid" placeholder="请选择所属客户" clearable="true">
                      <el-option
                          v-for="item in customers"
                          :key="item.id"
                          :label="item.name"
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
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号', trigger: 'blur' },
                        { validator: validatePhone, trigger: 'change'}
                    ],
                    position: [
                        { required: true, message: '请输入职务', trigger: 'blur' }
                    ],
                    depart: [
                        { required: true, message: '请输入部门', trigger: 'blur' }
                    ],
                    wx: [
                        { required: true, message: '请输入微信号', trigger: 'blur' }
                    ],
                    qq: [
                        { required: true, message: '请输入QQ号', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' }
                    ],
                    cid: [
                        { required: true, message: '请输入所属客户', trigger: 'blur' }
                    ],
                },
              customers:[],
            };
        },
        created() {
            this.getData();
        },
        methods: {
          //获取活动
          getCustomer() {
            this.$axios.post('/api/customer/selectByCondition',{}).then(res => {
              this.customers = res.data;
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
                    currentPage: this.currentPage, //页码
                    pagesize: this.pagesize,  //每页显示的记录数
                    name: this.query.key  //查询条件
                };
                this.$axios.post('/api/contacter/selectAll',param).then(res => {
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
                        this.$axios.get('/api/contacter/deleteById?id=' + row.id).then(res => {
                            this.$message.success('删除成功');
                            this.tableData.splice(index, 1);
                            this.getData();
                        });
                    }).catch(() => {
                });
            },
            // 编辑操作
            handleEdit(index, row) {
                this.getCustomer();
                this.eform = JSON.parse(JSON.stringify(row));
                this.eform.cid = Number(this.eform.cid);
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.editVisible = false;
                        this.$axios.post('/api/contacter/edit', this.eform).then(res => {
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
              this.getCustomer();
              if (this.$refs.ruleForm !== undefined) this.$refs.ruleForm.resetFields();
                this.addVisible = true;
                this.aform = {};
            },
            // 保存添加
            saveAdd(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios.post('/api/contacter/add', this.aform).then(res => {
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
            }
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
