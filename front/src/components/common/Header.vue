<template>
    <div class="header">
        <!-- 折叠按钮 -->
        <div class="collapse-btn" @click="collapseChage">
        </div>
        <div class="logo">客户关系管理系统</div>
        <div class="header-right">

            <div class="header-user-con">
                <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{this.userType}}：{{username}}
                        <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item divided command="loginout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>
<script>
    import common from "../../utils/common";

    export default {
        data() {
            return {
                collapse: false,
                fullscreen: false,
                name: '暂未登录',
                userType:'',
                message: 2
            };
        },
        computed: {
            username() {
                const user = this.common.getUserInfo('adminInfo');
                if(user==null){
                    this.$message.error('登录失效，请重新登录！');
                    return this.name;
                }else{
                    const type = common.get('type');
                    if(type==='01'){
                        this.userType = '管理员';
                         return user.username;
                    }else{
                      this.userType = '用户';
                      return user.realname;
                    }
                }
            }
        },
        methods: {
            goIndex(){
                this.$router.push('/user/helloHome');
            },
            // 用户名下拉菜单选择事件
            handleCommand(command) {
                if (command == 'loginout') {
                    localStorage.clear();
                    this.$router.push('/');
                }
            },
            // 侧边栏折叠
            collapseChage() {
                this.collapse = !this.collapse;
                bus.$emit('collapse', this.collapse);
            },
        },
        mounted() {
            if (document.body.clientWidth < 1500) {
                this.collapseChage();
            }
        }
    };
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 50px;
        font-size: 22px;
        color: #ffffff;
        background-color: #1fa2ff;
    }

    .collapse-btn {
        float: left;
        padding: 0 8px;
        cursor: pointer;
        line-height: 50px;
    }

    .header .logo {
        float: left;
        width: 300px;
        line-height: 50px;
        font-size: 17px;
        margin-left: 20px;
    }

    .header-right {
        float: right;
        padding-right: 50px;
    }

    .header-user-con {
        display: flex;
        height: 50px;
        align-items: center;
    }

    .btn-fullscreen {
        transform: rotate(45deg);
        margin-right: 5px;
        font-size: 24px;
    }

    .btn-bell,
    .btn-fullscreen {
        position: relative;
        width: 30px;
        height: 30px;
        text-align: center;
        border-radius: 15px;
        cursor: pointer;
    }

    .btn-bell-badge {
        position: absolute;
        right: 0;
        top: -2px;
        width: 8px;
        height: 8px;
        border-radius: 4px;
        background: #f56c6c;
        color: #fff;
    }

    .btn-bell .el-icon-bell {
        color: #fff;
    }

    .user-name {
        margin-left: 10px;
    }

    .user-avator {
        margin-left: 20px;
    }

    .user-avator img {
        display: block;
        width: 40px;
        height: 40px;
        border-radius: 50%;
    }

    .el-dropdown-link {
        color: #fff;
        cursor: pointer;
    }

    .el-dropdown-menu__item {
        text-align: center;
    }
</style>
