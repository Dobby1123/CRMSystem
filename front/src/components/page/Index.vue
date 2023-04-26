<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-arrow-right"></i> 首页
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
      <div class="container" style="height: 600px;">
        <div ref="chart2" style="width:100%;height:400px;float:left;margin-top: 50px;"></div>
      </div>
    </div>

</template>
<script>
export default {
  data() {
    return {

    };
  },
  created() {

  },
  mounted(){
    this.getEchartData2();
  },
  methods: {
    getEchartData2() {
      const chart2 = this.$refs.chart2
      if (chart2) {
        var values =[];
        var lables =[];
        this.$axios.post('/api/statistic/getInfo').then(res => {
          for( var i =0;i<res.data.length;i++){
            var test={"value":res.data[i].value,"name":res.data[i].name}
            values.push(test);
            lables.push(res.data[i].name)
          }
          const myChart = this.$echarts.init(chart2)
          const option = {
            title: {
              text: '漏斗图',
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
                name:'漏斗图',
                type:'funnel',
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
          myChart.setOption(option)
          window.addEventListener("resize", function() {
            myChart.resize()
          })
          this.$on('hook:destroyed',()=>{
            window.removeEventListener("resize", function() {
              myChart.resize();
            });
          })
        });

      }
    },

  },
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