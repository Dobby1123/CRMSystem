<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">注册</div>
            <el-form :model="param" :rules="rules" ref="register" label-width="0px" class="ms-content" >
                <el-form-item prop="phone" label="手机号" label-width="90px">
                    <el-input v-model="param.phone" placeholder="手机号" >
                    </el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码" label-width="90px">
                    <el-input
                            type="password"
                            placeholder="密码"
                            v-model="param.password"
                            @keyup.enter.native="submitForm()" >
                    </el-input>
                </el-form-item>
                <el-form-item prop="repassword" label="确认密码" label-width="90px">
                    <el-input
                            type="password"
                            placeholder="确认密码"
                            v-model="param.repassword"
                            @keyup.enter.native="submitForm()" >
                    </el-input>
                </el-form-item>
                <el-form-item prop="did" label="部门" label-width="90px">
                  <el-select v-model="param.did" placeholder="请选择部门" clearable="true">
                    <el-option
                        v-for="item in departments"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">注册</el-button>
                </div>
            </el-form>

              <div class="login-bottom">
                <span style="float:right;margin: 0 15px 15px 0; font-size: 10px;"><a style="color: rgb(255 255 255);" href="/#/login">去登录</a></span>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data: function () {
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
            };
             var validatePass = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请输入密码"));
                } else {
                    if (this.param.repassword !== "") {
                        this.$refs.register.validateField("repassword");
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请再次输入密码"));
                } else if (value !== this.param.password) {
                    callback(new Error("两次输入密码不一致!"));
                } else {
                    callback();
                }
            };
            return {
                departments:[],
                param: {
                    phone: '',
                    password: '',
                    repassword: '',
                    role: '',
                    did:''
                },
                rules: {
                    phone: [ 
                            { required: true, message: '请输入手机号', trigger: 'blur' },
                            { validator: validatePhone, trigger: 'change'}
                        ],
                    password: [{ validator: validatePass, trigger: ["blur", "change"] }],
                    repassword: [{
                        validator: validatePass2,
                        trigger: ["blur", "change"]
                    }],
                    did: [ { required: true, message: '请选择部门', trigger: 'change' }],
                },
            };
        },
      created() {
          this.getDepartment();
      },
      methods: {
          //获取部门
          getDepartment() {
            this.$axios.post('/api/department/selectByCondition',{}).then(res => {
              this.departments = res.data;
            });
          },
            submitForm() {
                this.$refs.register.validate(valid => {
                    if (valid) {
                        this.$axios.post('/api/register', this.param).then(res => {
                            console.log(res.data.msg);
                            if (res.data.code === 200) {
                              this.$message.success(res.data.msg);
                              this.param = {};
                            } else{
                              this.$message.error(res.data.msg);
                            }
                            this.$router.push('/');
                        })
                    } else {
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