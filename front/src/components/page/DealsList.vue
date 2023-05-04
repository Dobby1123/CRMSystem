<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-lx-cascades"></i> 交易管理 </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.key" placeholder="请输入标题" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">查询</el-button>
                <el-button type="primary" @click="handleAdd" icon="el-icon-plus">添加</el-button>
                <el-button type="primary" @click="handleExport" icon="el-icon-download">导出</el-button>
            </div>
            <el-table id="data-table" :data="tableData" border class="table" ref="multipleTable" header-cell-class-name="table-header" @selection-change="updateSelectedRows">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="title" label="标题" align="center"></el-table-column>
                <el-table-column prop="content" label="交易内容" align="center"></el-table-column>
                <el-table-column prop="dealtime" label="交易时间" align="center"></el-table-column>
                <el-table-column prop="cname" label="所属客户" align="center"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
                <el-table-column label="操作" align="center" width="200">
                    <template slot-scope="scope">
                        <el-button type="primary" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
                    :total="totalCount"
                >
                </el-pagination>
            </div>
            <!-- 确认导出的提示框 -->
            <el-dialog title="确认导出" :visible.sync="showConfirmDialog">
                <p v-if="selectedRowKeys.length === 0">确定要导出全部数据吗？</p>
                <p v-if="selectedRowKeys.length != 0">确定要导出 {{ selectedRowKeys.length }} 条数据吗？</p>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="showConfirmDialog = false">取消</el-button>
                    <el-button type="primary" @click="exportSelectedData">确定</el-button>
                </span>
            </el-dialog>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="交易编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="ruleForm" :model="eform" :rules="rules" label-width="90px">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="eform.title"></el-input>
                </el-form-item>
                <el-form-item label="交易时间" prop="dealtime">
                    <el-date-picker
                        v-model="eform.dealtime"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
                    >
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="所属客户" prop="cid">
                    <el-select v-model="eform.cid" placeholder="请选择所属客户" clearable="true">
                        <el-option v-for="item in customers" :key="item.id" :label="item.name" :value="item.id"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="交易内容" prop="content">
                    <el-input type="textarea" rows="5" v-model="eform.content"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit('ruleForm')">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 添加弹出框 -->
        <el-dialog title="交易添加" :visible.sync="addVisible" width="30%">
            <el-form ref="ruleForm" :model="aform" :rules="rules" label-width="90px">
                <el-form-item label="标题" prop="title">
                    <el-input v-model="aform.title"></el-input>
                </el-form-item>
                <el-form-item label="交易时间" prop="dealtime">
                    <el-date-picker
                        v-model="aform.dealtime"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="yyyy-MM-dd"
                    >
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="所属客户" prop="cid">
                    <el-select v-model="aform.cid" placeholder="请选择所属客户" clearable="true">
                        <el-option v-for="item in customers" :key="item.id" :label="item.name" :value="item.id"> </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="交易内容" prop="content">
                    <el-input type="textarea" rows="5" v-model="aform.content"></el-input>
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
import * as XLSX from 'xlsx';
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
                title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
                content: [{ required: true, message: '请输入交易内容', trigger: 'blur' }],
                dealtime: [{ required: true, message: '请输入交易时间', trigger: 'blur' }],
                cid: [{ required: true, message: '请输入所属客户', trigger: 'blur' }]
            },
            customers: [],
            selectedRowKeys: [], // 已选择的行的key
            showConfirmDialog: false // 控制是否展示确认导出的弹窗
        };
    },
    created() {
        this.getData();
    },
    methods: {
        //获取活动
        getCustomer() {
            this.$axios.post('/api/customer/selectByCondition', {}).then((res) => {
                this.customers = res.data;
            });
        },
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
            this.$axios.post('/api/deals/selectAll', param).then((res) => {
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
            })
                .then(() => {
                    this.$axios.get('/api/deals/deleteById?id=' + row.id).then((res) => {
                        this.$message.success('删除成功');
                        this.tableData.splice(index, 1);
                        this.getData();
                    });
                })
                .catch(() => {});
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
                    this.$axios.post('/api/deals/edit', this.eform).then((res) => {
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
                    this.$axios.post('/api/deals/add', this.aform).then((res) => {
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
        // 更新已选择的行的key
        updateSelectedRows(selection) {
            this.selectedRowKeys = selection.map((item) => item.id);
        },

        // 处理导出数据的操作
        handleExport() {
            // 否则需要用户确认，展示确认对话框
            this.showConfirmDialog = true;
        },

        // 导出已经选择的行
        exportSelectedData() {
            // 如果当前没有选择任何行，则默认导出全部数据
            if (this.selectedRowKeys.length === 0) {
                this.exportData(this.tableData);
            } else {
                const exportData = this.tableData.filter((item) => this.selectedRowKeys.includes(item.id));
                this.exportData(exportData);
            }
            this.showConfirmDialog = false; // 关闭确认对话框
        },

        // 导出所有数据
        exportData(data) {
            const sheetName = 'sheet1';
            const workbook = XLSX.utils.book_new();
            const worksheet = XLSX.utils.json_to_sheet(data);
            XLSX.utils.book_append_sheet(workbook, worksheet, sheetName);
            const currentDate = new Date().toLocaleDateString().replace(/\//g, '-');
            const fileName = `data-${currentDate}.xlsx`;
            XLSX.writeFile(workbook, fileName);
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
