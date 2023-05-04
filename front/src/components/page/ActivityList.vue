<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-lx-cascades"></i> 市场活动管理 </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.key" placeholder="请输入活动主题" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">查询</el-button>
                <el-button type="primary" @click="handleAdd" icon="el-icon-plus">添加</el-button>
            </div>
            <el-table :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="title" label="活动主题" align="center"></el-table-column>
                <el-table-column prop="content" label="活动内容" align="center"></el-table-column>
                <el-table-column prop="times" label="举办日期" align="center"></el-table-column>
                <el-table-column prop="status" label="状态" align="center">
                    <template slot-scope="scope">
                        <el-button type="success" size="mini" v-if="scope.row.status === '01'">未举办</el-button>
                        <el-button type="primary" size="mini" v-if="scope.row.status === '02'">计划举办</el-button>
                        <el-button type="danger" size="mini" v-if="scope.row.status === '03'">已举办</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
                <el-table-column label="操作" align="center" width="300">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type="danger" icon="el-icon-delete" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        <el-button type="info" icon="el-icon-more" @click="handleDetails(scope.$index, scope.row)">详情</el-button>
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
                    :total="totalCount"
                >
                </el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="市场活动编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="ruleForm" :model="eform" :rules="rules" label-width="90px">
                <el-form-item label="活动主题" prop="title">
                    <el-input v-model="eform.title"></el-input>
                </el-form-item>
                <el-form-item label="举办日期" prop="times">
                    <el-date-picker
                        v-model="eform.times"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
                    >
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="活动内容" prop="content">
                    <el-input type="textarea" rows="5" v-model="eform.content"></el-input>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-select v-model="eform.status" placeholder="请选择状态">
                        <el-option label="未举办" value="01"></el-option>
                        <el-option label="计划举办" value="02"></el-option>
                        <el-option label="已举办" value="03"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit('ruleForm')">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 市场活动详情框 -->
        <el-dialog title="添加市场活动详情" :visible.sync="detailsVisible" width="30%">
            <el-form :model="adsform" label-width="90px">
                <el-form-item label="活动主题" prop="title">
                    <el-input v-model="adsform.title"></el-input>
                </el-form-item>
                <el-form-item label="举办日期" prop="times">
                    <el-date-picker
                        v-model="adsform.times"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
                    >
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="参与人员" prop="uid">
                    <el-select v-model="adsform.uid" multiple placeholder="">
                        <!-- <el-option label="广告" value="advertise"></el-option>
                        <el-option label="线下举办" value="offline"></el-option>
                        <el-option label="研讨会" value="seminar"></el-option>
                        <el-option label="电子邮件" value="email"></el-option> -->
                        <el-option v-for="user in users" :key="user.id" :label="user.realname" :value="user.id"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="活动类型" prop="type">
                    <el-select v-model="adsform.type" placeholder="请选择类型">
                        <el-option label="广告" value="advertise"></el-option>
                        <el-option label="线下举办" value="offline"></el-option>
                        <el-option label="研讨会" value="seminar"></el-option>
                        <el-option label="电子邮件" value="email"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="活动预算" prop="money">
                    <el-input v-model="adsform.money"></el-input>
                </el-form-item>
                <el-form-item label="活动地址" prop="address">
                    <el-input v-model="adsform.address"></el-input>
                </el-form-item>
                <el-upload action="#" list-type="picture-card" :auto-upload="false">
                    <i slot="default" class="el-icon-plus"></i>
                    <div slot="file" slot-scope="{ file }">
                        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                        <span class="el-upload-list__item-actions">
                            <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
                                <i class="el-icon-zoom-in"></i>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleDownload(file)">
                                <i class="el-icon-download"></i>
                            </span>
                            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
                                <i class="el-icon-delete"></i>
                            </span>
                        </span>
                    </div>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="" />
                </el-dialog>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="detailsVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveDetails()">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 添加弹出框 -->
        <el-dialog title="市场活动添加" :visible.sync="addVisible" width="30%">
            <el-form ref="ruleForm" :model="aform" :rules="rules" label-width="90px">
                <el-form-item label="活动主题" prop="title">
                    <el-input v-model="aform.title"></el-input>
                </el-form-item>
                <el-form-item label="举办日期" prop="times">
                    <el-date-picker
                        v-model="aform.times"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
                    >
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="活动内容" prop="content">
                    <el-input type="textarea" rows="5" v-model="aform.content"></el-input>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-select v-model="aform.status" placeholder="请选择状态">
                        <el-option label="未举办" value="01"></el-option>
                        <el-option label="计划举办" value="02"></el-option>
                        <el-option label="已举办" value="03"></el-option>
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
            detailsVisible: false,
            // 添加
            eform: {},
            // 编辑
            aform: {},
            // 详情
            adsform: {},
            pagesize: 10,
            currentPage: 1,
            totalCount: 0,
            rules: {
                title: [{ required: true, message: '请输入活动主题', trigger: 'blur' }],
                content: [{ required: true, message: '请输入活动内容', trigger: 'blur' }],
                times: [{ required: true, message: '请输入举办日期', trigger: 'blur' }],
                status: [{ required: true, message: '请输入状态', trigger: 'blur' }]
            },
            dialogImageUrl: '',
            dialogVisible: false,
            disabled: false,
            users: []
        };
    },
    created() {
        this.getData();
    },
    methods: {
        //每页显示数据量变更
        handleSizeChange: function (val) {
            this.pagesize = val;
            this.getData();
        },
        //页码变更
        handleCurrentChange: function (val) {
            this.currentPage = val;
            this.getData();
        },
        //数据来源
        getData() {
            var param = {
                currentPage: this.currentPage, //页码
                pagesize: this.pagesize, //每页显示的记录数
                title: this.query.key //查询条件
            };
            this.$axios.post('/api/activity/selectAll', param).then((res) => {
                this.tableData = res.data.list;
                this.totalCount = res.data.total;
            });
        },
        //获取员工们
        getUsers() {
            this.$axios
                .post('/api/user/selectAll', {
                    currentPage: 1, //页码
                    pagesize: 10, //每页显示的记录数
                    realname: '' //查询条件
                })
                .then((res) => {
                    console.log(res);
                    this.users = res.data.list;
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
            })
                .then(() => {
                    this.$axios.get('/api/activity/deleteById?id=' + row.id).then((res) => {
                        this.$message.success('删除成功');
                        this.tableData.splice(index, 1);
                        this.getData();
                    });
                })
                .catch(() => {});
        },
        // 编辑操作
        handleEdit(index, row) {
            this.eform = JSON.parse(JSON.stringify(row));
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.editVisible = false;
                    this.$axios.post('/api/activity/edit', this.eform).then((res) => {
                        if (res.data == '200') {
                            this.$message.success('修改成功');
                            this.getData();
                        } else {
                            this.$message.warning('修改失败');
                        }
                    });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        saveDetails() {
            this.detailsVisible = false;
            console.log(this.adsform);
            this.$axios.post('/api/activityDetails/add', this.adsform).then((res) => {
                if (res.data == '200') {
                    this.$message.success('修改成功');
                    // this.getData();
                } else {
                    this.$message.warning('修改失败');
                }
            });
        },
        // 添加
        handleAdd() {
            if (this.$refs.ruleForm !== undefined) this.$refs.ruleForm.resetFields();
            this.addVisible = true;
            this.aform = {};
        },
        // 保存添加
        saveAdd(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$axios.post('/api/activity/add', this.aform).then((res) => {
                        if (res.data == '200') {
                            this.$message.success('添加成功');
                            this.addVisible = false;
                            this.getData();
                        } else {
                            this.$message.warning('添加失败');
                        }
                    });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleDetails(index, row) {
            this.adsform = JSON.parse(JSON.stringify(row));
            this.getUsers();
            this.detailsVisible = true;
        },
        // 图片上传
        handleRemove(file) {
            console.log(file);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        handleDownload(file) {
            console.log(file);
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
