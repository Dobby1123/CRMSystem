<template>
    <div>
        <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css" />

        <div>
            <el-select v-model="value" placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                    <i :class="item.icon" /> 
                </el-option>
            </el-select>
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                <i :class="item.icon" />
            </el-option>
        </div>
    </div>
</template>

<script src="https://unpkg.com/vue/dist/vue.js"></script>
        <script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
export default {
    data() {
        return {
            value: '',
            options: [
                {
                    value: 'zhinan',
                    label: '指南',
                    icon: 'el-icon-info'
                },
                {
                    value: 'shejiyuanze',
                    label: '设计原则',
                    icon: 'el-icon-document'
                },
                {
                    value: 'yizhi',
                    label: '一致',
                    icon: 'el-icon-star-on'
                },
                {
                    value: 'fankui',
                    label: '反馈',
                    icon: 'el-icon-message'
                }
            ]
        };
    }
};
</script>

