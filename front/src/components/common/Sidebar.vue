<template>
    <div class="sidebar">
        <el-menu
            class="sidebar-el-menu"
            :default-active="onRoutes"
            :collapse="collapse"
            background-color="#324157"
            text-color="#bfcbd9"
            active-text-color="#20a0ff"
            unique-opened
            router
        >
            <template v-for="item in items">
                    <template v-if="item.subs">
                        <el-submenu :index="item.index" :key="item.index">
                            <template slot="title">
                                <i :class="item.icon"></i>
                                <span slot="title">{{ item.title }}</span>
                            </template>
                            <template v-for="subItem in item.subs">
                                <el-submenu
                                        v-if="subItem.subs"
                                        :index="subItem.index"
                                        :key="subItem.index"
                                >
                                    <template slot="title">{{ subItem.title }}</template>
                                    <el-menu-item
                                            v-for="(threeItem,i) in subItem.subs"
                                            :key="i"
                                            :index="threeItem.index"
                                    >{{ threeItem.title }}</el-menu-item>
                                </el-submenu>
                                <el-menu-item
                                        v-else
                                        :index="subItem.index"
                                        :key="subItem.index"
                                >{{ subItem.title }}</el-menu-item>
                            </template>
                        </el-submenu>
                    </template>
                    <template v-else>
                        <el-menu-item :index="item.index" :key="item.index">
                            <i :class="item.icon"></i>
                            <span slot="title">{{ item.title }}</span>
                        </el-menu-item>
                    </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
import bus from './bus';
import common from "../../utils/common";
export default {
    data() {
        return {
            collapse: false,
            type:'',
            userInfo:{},
            items: []
        };
    },
    computed: {
        onRoutes() {
            return this.$route.path.replace('/', '');
        }
    },
    created() {
        // 通过 Event Bus 进行组件间通信，来折叠侧边栏
        bus.$on('collapse', msg => {
            this.collapse = msg;
            bus.$emit('collapse-content', msg);
        });
        this.getInfo();
    },
    methods: {
        getInfo(){
            this.type = this.common.get("type");
            this.userInfo = this.common.getUserInfo('adminInfo');
            if(this.userInfo==null || this.type==null){
              this.$router.push('/');
            }else{
              this.items = [];
              //每个人都有信息
              this.items.push({
                icon: 'el-icon-s-home',
                index: 'Index',
                title: '首页',
              });
              var param = {
                did: this.type==='02'?this.userInfo.did:''
              };
              this.$axios.post('/api/authority/getMenuByDid',param).then(res => {
                for(let i=0;i<res.data.length;i++){
                  let temp = {
                    icon: res.data[i].icons,
                    index: res.data[i].url,
                    title: res.data[i].name,
                  };
                  this.items.push(temp);
                }
                //每个人都有个人信息
                this.items.push({
                  icon: 'el-icon-document',
                  index: 'MyInfo',
                  title: '我的信息',
                });
              });
            }
        }
    }
};
</script>

<style scoped>
.sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 50px;
    bottom: 0;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
    width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
}
.sidebar > ul {
    height: 100%;
}
</style>
