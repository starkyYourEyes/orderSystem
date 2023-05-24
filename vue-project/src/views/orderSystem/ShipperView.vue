<template>
  <div>
    <el-container style="height: 700px; border: 1px solid #eee">
      <!-- <el-header style="font-size: 40px; background-color: rgb(238, 241, 246)"
        ><b>订 单 运 货 系 统</b></el-header> -->
      <el-container>
        <el-aside width="230px" style="border: 1px solid #eee">
          <el-menu :default-openeds="['1', '3']">
              <el-menu-item index="1-1">
                  <router-link to="/emp">
                      <b style="font-size: 24px">进销存运管理系统</b>
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
          <el-button type="primary" size="middle" @click="handleCreateShipper">新增运货商</el-button>
          <br><br>
          <!-- 表格 -->
          <el-table :data="tableData" border v-if="refreshTableDataVisible">
            <el-table-column
              prop="shipperId"
              label="运货商ID"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="companyName"
              label="公司名称"
              width="240"
            ></el-table-column>
            <el-table-column
              prop="phone"
              label="电话"
              width="200"
            ></el-table-column>
            <el-table-column label="操 作">
              <template slot-scope="scope">
                <el-button
                  type="danger"
                  size="mini"
                  @click="deleteOneShipper(scope.row.shipperId)">删 除</el-button>
                <el-button type="warning" size="mini" @click="updateShipper(scope.row.shipperId)">编 辑</el-button>
                <!-- 这个运货按钮中，首先要查看 等待 运输的订单有哪些，然后可以选择订单进行运输，记得修改order表中的deliver_date -->
                <el-button type="primary" size="mini" @click="shipGoods(scope.row.shipperId)">运 货</el-button>
                <el-button type="primary" size="mini" @click="goodsArrive(scope.row.shipperId)">到 货</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>

    <div class="insertShipper">
      <!-- 插入运货商的表单 -->
      <el-dialog :visible.sync="insertShipperDialogFormVisible">
        <el-form
          :model="shipper"
          ref="dataForm"
          label-position="center"
          label-width="120px"
          style="width: 500px; margin-left: 50px"
        >
          <el-form-item
            label="公司名称"
            prop="companyName"
            value="client.clientId"
            required="true"
          >
            <el-input v-model="shipper.companyName" />
          </el-form-item>
          <el-form-item
            label="电话"
            value="shipper.phone"
            prop="phone"
            required="true"
          >
            <el-input v-model="shipper.phone" />
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="insertShipperDialogFormVisible = false"
            >取消</el-button
          >
          <el-button type="primary" @click="createData()">确定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 编辑运货商的表单 -->
    <div class="updateShipper">
      <!-- 编辑运货商的表单 -->
      <el-dialog :visible.sync="updateShipperDialogFormVisible">
        <el-form
          :model="shipper"
          ref="dataForm"
          label-position="center"
          label-width="120px"
          style="width: 500px; margin-left: 50px"
        >
          <el-form-item
            label="公司名称"
            prop="companyName"
            value="shipper.companyName"
            required="true"
          >
            <el-input v-model="shipper.companyName" />
          </el-form-item>
          
          <el-form-item
            label="电话"
            value="shipper.phone"
            prop="phone"
            required="true"
          >
            <el-input v-model="shipper.phone" />
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="updateShipperDialogFormVisible = false"
            >取消</el-button
          >
          <el-button type="primary" @click="updateShipperConfirm">确定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- Order   v-if="refreshOrdersShipper" -->
    <div class="showOrders">
      <el-dialog :visible.sync="showOrdersDialogFormVisible" v-if="refreshOrdersShipper" @close="closeOrderShipper" width="80%">
            <!-- 表格,订单信息 -->
          <el-table :data="ordersData" border>

            <el-table-column label="操作" width="120">
                <template slot-scope="scope">
                    <el-button
                    type="primary"
                    size="mini"
                    @click="deliverOrder(scope.row.orderId)"
                    >运 送</el-button>
                </template>
            </el-table-column>

            <el-table-column
              prop="orderId"
              label="订单ID"
              width="90"
            ></el-table-column>
            <el-table-column
              prop="buyDate"
              label="订购日期"
              width="200"
            ></el-table-column>
            
            <el-table-column
              prop="ownerName"
              label="货主姓名"
              width="160"
            ></el-table-column>
            <el-table-column
              prop="ownerAddress"
              label="货主地址"
              width="240"
            ></el-table-column>
            <el-table-column
              prop="ownerCity"
              label="货主城市"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="ownerZone"
              label="货主地区"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="ownerPostcode"
              label="货主邮编"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="ownerCountry"
              label="货主国家"
              width="180"
            ></el-table-column>

        </el-table>
          <!-- Pagination 分页 -->
          <br><br>
          <el-pagination
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChangeOrder"
            @current-change="handleCurrentChangeOrder"
            :total="totalOrders"
          ></el-pagination>
          <span slot="footer" class="dialog-footer">
            <el-button @click="closeOrderShipper">取 消</el-button>
            <el-button type="primary" @click="closeOrderShipper">确 定</el-button>
        </span>
        </el-dialog>

    </div>

    <!-- ArriveOrder -->
    <div class="notArrivedOrdersData">
      <el-dialog :visible.sync="showArriveOrdersDialogFormVisible" v-if="refreshOrdersArrived" @close="closeShowArriveOrdersDialogFormVisible" width="80%">
            <!-- 表格,订单信息 -->
          <el-table :data="notArrivedOrdersData" border>

            <el-table-column label="操作" width="120">
                <template slot-scope="scope">
                    <el-button
                    type="primary"
                    size="mini"
                    @click="ordersArrived(scope.row.orderId)"
                    >运 送 完 成</el-button>
                </template>
            </el-table-column>

            <el-table-column
              prop="orderId"
              label="订单ID"
              width="90"
            ></el-table-column>
            <el-table-column
              prop="buyDate"
              label="订购日期"
              width="200"
            ></el-table-column>
            <el-table-column
              prop="deliverDate"
              label="发货日期"
              width="200"
            ></el-table-column>
            
            <el-table-column
              prop="ownerName"
              label="货主姓名"
              width="100"
            ></el-table-column>
            <el-table-column
              prop="ownerAddress"
              label="货主地址"
              width="120"
            ></el-table-column>
            <el-table-column
              prop="ownerCity"
              label="货主城市"
              width="140"
            ></el-table-column>
            <el-table-column
              prop="ownerZone"
              label="货主地区"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="ownerPostcode"
              label="货主邮编"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="ownerCountry"
              label="货主国家"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="shipperId"
              label="运货商Id"
              width="180"
            ></el-table-column>
           
          </el-table>
          <!-- Pagination 分页 -->
          <br><br>
          <!-- <el-pagination
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChangeOrder"
            @current-change="handleCurrentChangeOrder"
            :total="totalOrders"
          ></el-pagination> -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="showArriveOrdersDialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="showArriveOrdersDialogFormVisible = false">确 定</el-button>
        </span>
        </el-dialog>

    </div>
  </div>
</template>



<script>
import axios from "axios";

export default {
  inject: ['reload'],
  data() {
    return {
      refreshOrdersShipper: false, 
      refreshOrdersArrived: false, 
      refreshTableDataVisible: true,
    
      tableData: [],
      ordersData: [],
      notArrivedOrdersData: [],

      totalShippers: "",
      shipper: {
        shipperId: "",
        companyName: "",
        phone: "",
      },

      // 当前进行运货操作的shipper
      currentShipperId: "",

        // 查询订单
      totalOrders: "",
      pageSearchOrder: {
          pageSize: 10,
          start: 1,
      },

      order: {
          orderId: "",
          buyDate: "",
          deliverDate: "",
          arriveDate: "",
          confirmDate: "",
          ownerName: "",
          ownerAddress: "",
          ownerCity: "",
          ownerZone: "",
          ownerPostcode: "",
          ownerCountry: "",
          clientId: "",
          shipperId: "",
          totalCost: ""
      },

      // 控制表单的隐藏
      showOrdersDialogFormVisible: false,
      showArriveOrdersDialogFormVisible: false,
      insertShipperDialogFormVisible: false,
      updateShipperDialogFormVisible: false,

      searchForm: {
        name: "",
        gender: "",
        entrydate: [],
      },
    };
  },
  methods: {
    onSubmit: function () {
      // alert(JSON.stringify(val))
    },

    deleteOneShipper: function (id) {
      this.$confirm("此操作将永久删除该用户信息，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancleButtonText: "取消",
        type: "warning",
      }).then(() => {
        axios.delete("http://localhost:8080/shippers/" + id).then((result) => {

          if (result.data.msg == "success") {
            this.$notify({
              title: "成功",
              message: "删除成功",
              type: "success",
            });
            // 立即刷新页面
            // location.reload();
            axios.get("http://localhost:8080/shippers").then((result) => {
              this.tableData = result.data.data;
              this.refreshTableDataVisible = !this.refreshTableDataVisible;
              this.refreshTableDataVisible = !this.refreshTableDataVisible;
            });
            // //  延迟1s刷新页面
            // window.setTimeout(function () {
            //   window.location.reload();
            // }, 1000);
          } else {
            this.$notify.error({
              title: "失败",
              message: "操作失败，请联系管理员",
            });
          }
          
        });
      });
    },
    deliverOrder: function (ido){
        axios.put("http://localhost:8080/shippers/newOrder?ids=" + this.currentShipperId + "&ido=" + ido)
        .then((result) => {
            console.log(result.data.msg + this.currentShipperId + "成功接收订单" + ido);
            this.$notify({
              title: "成功",
              message: this.currentShipperId + "成功接收订单" + ido,
              type: "success"
            }),
            this.changeOnrefreshDeliverOrder();
        })
    },

    changeOnrefreshDeliverOrder:function(){
      // 请求数据
      axios.get("http://localhost:8080/shippers/shippableOrders").then((result) => {
            this.ordersData = result.data.data.rows;
            this.totalOrders = result.data.data.total;
        });
      this.refreshOrdersShipper = !this.refreshOrdersShipper;
      this.refreshOrdersShipper = !this.refreshOrdersShipper;
    },

    handleCreateShipper: function () {
      (this.shipper = {
        shipperId: "",
        companyName: "",
        phone: "",
      }),
        (this.insertShipperDialogFormVisible = true);
    },

    createData: function(){
        axios.post("http://localhost:8080/shippers", this.shipper).then((result) => {
            console.log("新增运货商成功" + result.data.msg);
            this.$notify({
              title: "成功",
              message: "新增运货商成功",
              type: "success",
            }),
            this.insertShipperDialogFormVisible = false;
            // window.setTimeout(function () {
            //     window.location.reload();
            //   }, 1000);
            axios.get("http://localhost:8080/shippers").then((result) => {
              this.tableData = result.data.data;
              this.refreshTableDataVisible = !this.refreshTableDataVisible;
              this.refreshTableDataVisible = !this.refreshTableDataVisible;
            });
        })
    },
    // 编辑运货商信息
    updateShipper: function(id){
        axios.get("http://localhost:8080/shippers/" + id).then((result) => {
            this.shipper = result.data.data;
        }),
        this.updateShipperDialogFormVisible = true;
    },
    updateShipperConfirm: function(){
        axios.put("http://localhost:8080/shippers", this.shipper).then((result) => {
        console.log(result.data.msg);
        this.updateShipperDialogFormVisible = false;
        this.$notify({
          title: "成功",
          message: "修改成功",
          type: "success",
        });
        axios.get("http://localhost:8080/shippers").then((result) => {
          this.tableData = result.data.data;
          this.refreshTableDataVisible = !this.refreshTableDataVisible;
          this.refreshTableDataVisible = !this.refreshTableDataVisible;
        });
        // //  延迟1s刷新页面
        // window.setTimeout(function () {
        //   window.location.reload();
        // }, 1000);
      });
    },

    shipGoods: function(id) {
        axios.get("http://localhost:8080/shippers/shippableOrders").then((result) => {
            this.ordersData = result.data.data.rows;
            this.totalOrders = result.data.data.total;
        }),
        this.showOrdersDialogFormVisible = true;

        this.currentShipperId = id;
        this.refreshOrdersShipper = true;
    },

    // 订单到货了
    goodsArrive: function(id) {
      
      this.currentShipperId = id;
      axios.get("http://localhost:8080/shippers/notArrivedOrders/" + id).then((result) => {
        this.notArrivedOrdersData = result.data.data;
      })

      this.showArriveOrdersDialogFormVisible = true;
      this.refreshOrdersArrived = true;
    },
    
    ordersArrived: function(id) {
      axios.put("http://localhost:8080/shippers/arrive/" + id).then((result) => {
        console.log(result.data.msg, id + "到了");
        this.$notify({
              title: "成功",
              message: "订单号" + id + " 到货了",
              type: "success"
            })
          this.refreshOnOrdersArrived();
      });
      
    },

    refreshOnOrdersArrived: function(){
      axios.get("http://localhost:8080/shippers/notArrivedOrders/" + this.currentShipperId).then((result) => {
        this.notArrivedOrdersData = result.data.data;
      });
       this.refreshOrdersArrived = !this.refreshOrdersArrived;
       this.refreshOrdersArrived = !this.refreshOrdersArrived;
    },
    closeShowArriveOrdersDialogFormVisible: function(){
      // this.refreshOrdersArrived = false;
      
      this.showArriveOrdersDialogFormVisible = false;
    },
    closeOrderShipper: function() {
        this.pageSearchOrder.start = 1;
        this.pageSearchOrder.pageSize = 10;
        // this.showOrdersDialogFormVisible = false;
        this.refreshOrdersShipper = false;
    },
    selectOrderByPage: function () {
        axios
        .get(
          "http://localhost:8080/shippers/shippableOrders?page=" +
            this.pageSearchOrder.start +
            "&pageSize=" +
            this.pageSearchOrder.pageSize
        )
        .then((result) => {
          this.totalOrders = result.data.data.total;
          this.ordersData = result.data.data.rows;
        });
    },

    // 记录订单页数变化
    handleSizeChangeOrder: function (val) {
        this.pageSearchOrder.pageSize = val;
        
        console.log("订单每页记录数变化" + val);
        this.selectOrderByPage();
    },

    handleCurrentChangeOrder: function(val) {
        this.pageSearchOrder.start = val;
        console.log("订单页码发生变化" + val);
        this.selectOrderByPage();
        
    },

  },
  
  mounted() {
    //发送异步请求,获取数据
    axios.get("http://localhost:8080/shippers").then((result) => {
      this.tableData = result.data.data;
    });

    axios.get("http://localhost:8080/shippers/shippableOrders").then((result) => {
        // alert(result.data.data.total)
        this.ordersData = result.data.data.rows;
        this.totalOrders = result.data.data.total;
    })


  },
};
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