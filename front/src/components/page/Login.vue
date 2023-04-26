<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">客户关系管理系统</div>
            <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content" >
                <el-form-item prop="username" label="登录名" label-width="70px">
                    <el-input v-model="param.username" placeholder="账号" >
                       <!-- <el-button slot="prepend" icon="el-icon-lx-people"></el-button>-->
                    </el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码" label-width="70px">
                    <el-input
                            type="password"
                            placeholder="密码"
                            v-model="param.password"
                            @keyup.enter.native="submitForm()" >
                        <!--<el-button slot="prepend" icon="el-icon-lx-lock"></el-button>-->
                    </el-input>
                </el-form-item>
                 <el-form-item label="人员类型" label-width="70px">
                     <el-select v-model="param.role" placeholder="请选择人员类型">
                        <el-option label="管理员" value="01"></el-option>
                        <el-option label="用户" value="02"></el-option>
                    </el-select>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
            </el-form>
              <div class="login-bottom">
                <span style="float:right;margin: 0 15px 15px 15px; font-size: 10px;"><a style="color: rgb(255 255 255);" href="/#/register">立即注册</a></span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
            return {
                param: {
                    username: '',
                    password: '',
                    role: '01'
                },
                rules: {
                    username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}],
                },
            };
        },
        methods: {
            submitForm() {
                this.$refs.login.validate(valid => {
                    if (valid) {
                        this.$axios.post('/api/adminLogin', this.param).then(res => {
                            console.log(res);
                            if (res.data.code == 200) {
                                this.$message.success('登录成功');
                                this.common.set('adminInfo',JSON.stringify(res.data.data));
                                this.common.set('type',this.param.role);
                                this.$router.push('/admin/Index');
                            } else{
                              this.$message.error('登陆失败');
                            }
                        })
                    } else {
                        this.$message.error('请输入账号和密码');
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
        },
    };
</script>

<style scoped>
    .login-wrap {
        position: relative;
        width: 100%;
        height: 100%;
        background-image: url(../../assets/img/1.jpg);
        background-size: 100%;
    }
    ::v-deep .el-form-item__label{
        color: #f4f4f5;
    }
    .ms-title {
        width: 100%;
        line-height: 50px;
        text-align: center;
        font-size: 20px;
        color: #fff;
        border-bottom: 1px solid #ddd;
    }

    .ms-login {
        position: absolute;
        left: 50%;
        top: 50%;
        width: 350px;
        margin: -190px 0 0 -175px;
        border-radius: 5px;
        background: rgba(255, 255, 255, 0.3);
        overflow: hidden;
    }

    .ms-content {
        padding: 30px 30px;
    }

    .login-btn {
        text-align: right;
    }

    .login-btn button {
        width: 76%;
        height: 36px;
        margin-bottom: 10px;
    }

    .login-tips {
        font-size: 12px;
        line-height: 30px;
        color: #fff;
    }
</style>