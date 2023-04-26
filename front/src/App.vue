<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<style>
    @import "./assets/css/main.css";
    @import "./assets/css/color-dark.css"; /*深色主题*/
    /*@import "./assets/css/theme-green/color-green.css";   !*浅绿色主题*!*/
    .el-button--primary {
        color: #FFF;
        background-color: #1fa2ff !important;
        border-color: #1fa2ff !important;
    }
    .el-pager li.active{
        color: #1fa2ff !important;
    }
</style>