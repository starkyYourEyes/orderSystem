<template>
    <div>
        <el-container  style="height: 700px; border: 1px solid #eee">
            <!-- <el-header style="font-size:40px; background-color: rgb(238, 241, 246)"
            ><b>订 单 运 货 系 统 -- 任务二 十三组</b></el-header> -->
            <el-container>
                <el-aside width="230px" style="border: 1px solid #eee">
                    <el-menu :default-openeds="['1', '3']">
                        
                        <el-menu-item index="1-1">
                  <router-link to="/emp">
                      <b style="font-size: 24px">进销存运管理系统
</b>
                  </router-link>
              </el-menu-item>
                        <el-menu-item index="1-2">
                            <i class="el-icon-location"></i>
                            <router-link to="/emp">首 页</router-link>
                        </el-menu-item>
                        <el-menu-item index="1-3">
                            <router-link to="/client">客 户 管 理</router-link>
                        </el-menu-item>
                        <el-menu-item index="1-4">
                            <router-link to="/shipper">运 货 商 管 理</router-link>
                        </el-menu-item>
                        <el-menu-item index="1-5">
                            <router-link to="/supplier">供 货 商 管 理</router-link>
                        </el-menu-item>

                    </el-menu>
                </el-aside>

                <el-main>
                    
                <img src="../../assets/NAUpuko.jpg" width="100%">
                    

                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>

export default {
    data() {
        return {
            
        }
    },
    methods: {
        
    },
    mounted () {
        //发送异步请求,获取数据
        
    }
}
</script>

<style scoped>
.router-link-active {
  text-decoration: none;
  color: peru;
}
a{
  text-decoration: none;
  color: black;
  font-size: 18px;
}



</style>