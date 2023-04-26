<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-arrow-right"></i> 我的信息
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div  class="container">
            <el-form ref="form" :model="form" label-width="80px" style="width: 600px;">
                <el-form-item label="部门" v-if="type==='02'">
                  <el-input v-model="form.dname" style="width: 214px;" disabled=""></el-input>
                </el-form-item>
                <el-form-item label="用户名" v-if="type==='01'">
                    <el-input v-model="form.username" :disabled="true" style="width: 214px;"></el-input>
                </el-form-item>
                <el-form-item label="手机号" v-if="type==='02'">
                  <el-input v-model="form.phone" style="width: 214px;"></el-input>
                </el-form-item>
                <el-form-item label="姓名" v-if="type==='02'">
                    <el-input v-model="form.realname" style="width: 214px;"></el-input>
                </el-form-item>
                <el-form-item label="年龄" v-if="type==='02'">
                  <el-input v-model="form.age" style="width: 214px;"></el-input>
                </el-form-item>
                <el-form-item label="性别" v-if="type==='02'">
                    <el-select v-model="form.sex"  placeholder="请选择性别" style="width: 214px;">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="原始密码" >
                    <el-input type="password" v-model="form.password" style="width: 214px;"></el-input>
                </el-form-item>
                <el-form-item label="修改密码" >
                    <el-input type="password" v-model="form.repassword" style="width: 214px;"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">立即修改</el-button>
                </el-form-item>
            </el-form>

        </div>
    </div>

</template>
<script>
    export default {
        data() {
            return {
                form: {
                    id:'',
                    username:'',
                    phone:'',
                    realname:'',
                    sex:'',
                    age:'',
                    password:'',
                    repassword:'',
                },
                type:'',
                password:'',
                userId:'',
            }
        },
        created() {
            this.getInfo();
        },
        methods: {
            onSubmit() {
                if(this.password !== this.form.password){
                    this.$message.warning("原始密码错误，请重试！");
                    return;
                }
                if(this.form.repassword!==null && this.form.repassword!==''){
                    this.form.password = this.form.repassword;
                }
                let url = '';
                if(this.type === '01'){
                    url = '/api/admin/edit';
                }else{
                    url = '/api/user/edit'
                }
                this.$axios.post(url, this.form).then(res => {
                    if(res.data === 200){
                        this.$message.success("修改成功");
                        this.updateInfo();
                    }else{
                        this.$message.warning("修改失败");
                    }
                })
            },
            updateInfo(){
                if(this.type === '01'){
                    let url = '/api/admin/selectOne?id='+this.userId;
                    this.$axios.get(url).then(res => {
                        const userInfo = res.data;
                        localStorage.removeItem("adminInfo");
                        this.common.set('adminInfo',JSON.stringify(userInfo));
                        this.password = userInfo.password;
                        this.form.password = '';
                        this.form.repassword = '';
                    });
                }else{
                    let url = '/api/user/selectOne?id='+this.userId;
                    this.$axios.get(url).then(res => {
                        const userInfo = res.data;
                        localStorage.removeItem("adminInfo");
                        this.common.set('adminInfo',JSON.stringify(userInfo));
                        this.password = userInfo.password;
                        this.form.password = '';
                        this.form.repassword = '';
                    });
                }
                
            },
            getInfo(){
                let user = this.common.getUserInfo('adminInfo');
                this.type = this.common.get("type");
                this.form = user;
                this.userId = user.id;
                this.password = this.form.password;
                this.form.password = '';
            }
        }
    }
</script>
