<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-arrow-right"></i> 首页 </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-container>
            <el-header>
                <div class="caidan" style="height: 50px; display: flex; width: 100%;font-size:18px;">
                    <div>
                        <el-card class="department" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-grid"></i>
                            <router-link to="/admin/DepartmentList"><p style="color: #000">部门管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="user" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-user"></i>
                            <router-link to="/admin/UserList"><p style="color: #000">用户管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="menu" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-menu"></i>
                            <router-link to="/admin/MenusList"><p style="color: #000">菜单管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="authority" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-claim"></i>
                            <router-link to="/admin/AuthorityList"><p style="color: #000">权限管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="activity" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-shop"></i>
                            <router-link to="/admin/ActivityList"><p style="color: #000">市场活动管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="customer1" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-custom"></i>
                            <router-link to="/admin/CustomerList"><p style="color: #000">潜在客户管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="customer2" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-custom"></i>
                            <router-link to="/admin/Customer2List"><p style="color: #000">客户管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="department" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-marketing"></i>
                            <router-link to="/admin/DealsList"><p style="color: #000">交易管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="deal" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-check"></i>
                            <router-link to="/admin/ContacterList"><p style="color: #000">联系人管理</p></router-link>
                        </el-card>
                    </div>
                    <div>
                        <el-card class="sale" style="width: 150px; margin-left: 8px">
                            <i class="el-icon-s-order"></i>
                            <router-link to="/admin/SalesList"><p style="color: #000">售后服务管理</p></router-link>
                        </el-card>
                    </div>
                </div>
            </el-header>
            <el-main>
                <div style="width: 48%; margin-top: 10px; margin-right: 3px; float: left;">
                    <el-table :data="tableData" style="width: 100%;font-size:18px;">
                        <el-table-column prop="title" label="市场活动" width="300"> </el-table-column>
                        <el-table-column prop="times" label="日期"> </el-table-column>
                    </el-table>
                </div>
                <div style="width: 25.5%; margin-top: 10px; margin-right: 3px; float: right">
                    <el-col>
                        <el-card shadow="hover">
                            <div class="elcard-header" style="border-bottom: 5px solid #DDDDDD	;">
                                <span>本日交易量</span>
                            </div>
                            <p style="font-size:30px">10</p>
                        </el-card>
                    </el-col>
                </div>
                <div style="width: 25.5%; margin-top: 10px; margin-right: 3px; float: right;">
                    <el-col>
                        <el-card shadow="hover">
                          <div class="elcard-header" style="border-bottom: 5px solid #DDDDDD	;">
                                <span style="height:100px;">本周交易量</span>
                            </div> 
                          <p style="font-size:30px">300</p>
                          </el-card>
                    </el-col>
                </div>
                <div style="width: 25.5%; margin-top: 10px; margin-right: 3px; float: right;margin-top:50px;">
                    <el-col>
                        <el-card shadow="hover"> 
                          <div class="elcard-header" style="border-bottom: 5px solid #DDDDDD	;">
                                <span>本月交易量</span>
                            </div>
                          <p style="font-size:30px">1000</p>
                          </el-card>
                    </el-col>
                </div>
                <div style="width: 25.5%; margin-top: 10px; margin-right: 3px; float: right;margin-top:50px;">
                    <el-col>
                        <el-card shadow="hover"> 
                          <div class="elcard-header" style="border-bottom: 5px solid #DDDDDD	;">
                                <span>本年交易量</span>
                            </div>
                          <p style="font-size:30px">5000</p>
                          </el-card>
                    </el-col>
                </div>
                
            </el-main>
            <el-footer>
                <div class="container" style="height: 400px">
                    <div style="height: 400px">
                        <div ref="chart2" style="width: 50%; height: 400px; float: left"></div>
                        <div ref="chart3" style="width: 50%; height: 400px; float: right"></div>
                    </div>
                </div>
            </el-footer>
        </el-container>
    </div>
</template>
<script>
import * as echarts from 'echarts';
export default {
    data() {
        return {
            tableData: []
        };
    },
    created() {
        this.getData();
    },
    mounted() {
        this.getEchartData2();
        this.getEchartData3();
    },
    methods: {
        getEchartData2() {
            const chart2 = this.$refs.chart2;
            if (chart2) {
                var values = [];
                var lables = [];
                this.$axios.post('/api/statistic/getInfo').then((res) => {
                    for (var i = 0; i < res.data.length; i++) {
                        var test = { value: res.data[i].value, name: res.data[i].name };
                        values.push(test);
                        lables.push(res.data[i].name);
                    }
                    const myChart = this.$echarts.init(chart2);
                    const option = {
                        title: {
                            text: '漏斗图'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b} : {c}个'
                        },
                        toolbox: {
                            feature: {
                                dataView: { readOnly: false },
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        legend: {
                            data: lables
                        },
                        series: [
                            {
                                name: '漏斗图',
                                type: 'funnel',
                                left: '10%',
                                top: 60,
                                bottom: 60,
                                width: '80%',
                                min: 0,
                                max: 20,
                                minSize: '0%',
                                maxSize: '100%',
                                sort: 'descending',
                                gap: 2,
                                label: {
                                    show: true,
                                    position: 'inside'
                                },
                                labelLine: {
                                    length: 10,
                                    lineStyle: {
                                        width: 1,
                                        type: 'solid'
                                    }
                                },
                                itemStyle: {
                                    borderColor: '#fff',
                                    borderWidth: 1
                                },
                                emphasis: {
                                    label: {
                                        fontSize: 20
                                    }
                                },
                                data: values
                            }
                        ]
                    };
                    myChart.setOption(option);
                    window.addEventListener('resize', function () {
                        myChart.resize();
                    });
                    this.$on('hook:destroyed', () => {
                        window.removeEventListener('resize', function () {
                            myChart.resize();
                        });
                    });
                });
            }
        },
        //数据来源
        getData() {
            this.$axios.post('/api/activity/selectAct').then((res) => {
                console.log(res.data);
                this.tableData = res.data;
            });
        },
        getEchartData3() {
            const chart3 = this.$refs.chart3;
            const myChart = this.$echarts.init(chart3);
            if (chart3) {
                const option = {
                    title: {
                        text: '本月工作量完成图'
                    },
                    tooltip: {
                        formatter: '{a} <br/>{b} : {c}%'
                    },
                    series: [
                        {
                            name: '已完成',
                            type: 'gauge',
                            progress: {
                                show: true
                            },
                            detail: {
                                valueAnimation: true,
                                formatter: '{value}'
                            },
                            data: [
                                {
                                    value: 50,
                                    name: '本月完成工作量'
                                }
                            ]
                        }
                    ]
                };
                option && myChart.setOption(option);
            }
        }
    }
};
</script>

<style scoped>
.bm-view {
    width: 100%;
    height: 590px;
}
.idle-card2 {
    height: 290px;
    cursor: pointer;
}
.fenye {
    display: flex;
    justify-content: center;
    height: 20px;
    align-items: center;
}
</style>