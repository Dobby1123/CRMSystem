<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 部门权限管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.key" placeholder="请输入部门名称" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">查询</el-button>
                <el-button type="primary" @click="handleAdd" icon="el-icon-plus" >添加</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="dname" label="部门" align="center"></el-table-column>
                <el-table-column prop="mname" label="菜单" align="center"></el-table-column>
                <el-table-column prop="createTime" label="发布时间" align="center"></el-table-column>
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
        <el-dialog title="部门权限编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="ruleForm" :model="eform" :rules="rules" label-width="70px">
                <el-form-item label="部门" prop="sex">
                  <el-select v-model="eform.did" disabled="true" placeholder="请选择部门" clearable="true">
                    <el-option
                        v-for="item in departments"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="菜单" prop="mid">
                  <el-select v-model="mids" multiple placeholder="">
                    <el-option
                        v-for="item in menus"
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
        <el-dialog title="部门权限添加" :visible.sync="addVisible" width="30%">
            <el-form ref="ruleForm" :model="aform" :rules="rules" label-width="70px">
                <el-form-item label="部门" prop="sex">
                  <el-select v-model="aform.did" placeholder="请选择部门" clearable="true">
                    <el-option
                        v-for="item in departments"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="菜单" prop="mid">
                  <el-select v-model="aform.mid" multiple placeholder="">
                    <el-option
                        v-for="item in menus"
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
                    did: [
                        { required: true, message: '请输入部门', trigger: 'blur' }
                    ],
                    mid: [
                        { required: true, message: '请输入菜单', trigger: 'blur' }
                    ],
                },
                menus:[],
                mids:[],
                departments:[],
            };
        },
        created() {
            this.getData();
        },
        methods: {
          //获取部门
          getDepartment() {
            this.$axios.post('/api/department/selectByCondition',{}).then(res => {
              this.departments = res.data;
            });
          },
          //获取菜单
          getMenus() {
            this.$axios.post('/api/menus/selectByCondition',{}).then(res => {
              this.menus = res.data;
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
                    dname: this.query.key  //查询条件
                };
                this.$axios.post('/api/authority/selectAll',param).then(res => {
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
                        this.$axios.get('/api/authority/deleteById?id=' + row.id).then(res => {
                            this.$message.success('删除成功');
                            this.tableData.splice(index, 1);
                            this.getData();
                        });
                    }).catch(() => {
                });
            },
            // 编辑操作
            handleEdit(index, row) {
                this.getDepartment();
                this.mids = [];
                this.$axios.post('/api/menus/selectByCondition',{}).then(res => {
                //   debugger
                  this.menus = res.data;
                  this.eform = JSON.parse(JSON.stringify(row));
                  this.eform.did = Number(this.eform.did);
                  let arryList = this.eform.mid.split(',');
                  arryList.forEach((ele,index)=>{
                    this.mids.push(Number(ele));
                  })
                  this.editVisible = true;
                });
            },
            // 保存编辑
            saveEdit(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.editVisible = false;
                        this.eform.mid =  this.mids.join(",")
                        this.$axios.post('/api/authority/edit', this.eform).then(res => {
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
              if (this.$refs.ruleForm !== undefined) this.$refs.ruleForm.resetFields();
                this.getMenus();
                this.getDepartment();
                this.addVisible = true;
                this.aform = {};
            },
            // 保存添加
            saveAdd(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.aform.mid =  this.aform.mid.join(",")
                        this.$axios.post('/api/authority/add', this.aform).then(res => {
                            if(res.data == '200') {
                              this.$message.success("添加成功");
                              this.addVisible = false;
                              this.getData();
                            }else if(res.data == '202'){
                                this.$message.warning("部门权限已添加，请勿重复添加！");
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
