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

        <el-main v-if="refreshTotalContainer">
          <el-button type="primary" size="middle" @click="handleCreate"
            >新增客户</el-button>

          <el-button type="warning" size="middle" @click="handleSelectAllOrder"
            >查看所有订单</el-button>
          <div></div>
         <br>
        <el-form
          :model="client"
          ref="dataForm"
          inline
        >
          <div class="el-form-item">
            <label class="el-form-item__label">姓 名</label>
            <div class="el-form-item__content">
              <div class="el-input">
                <input type="text" autocomplete="off" placeholder="请输入联系人姓名" class="el-input__inner"  v-model="client.linkmanName">
              </div>
            </div>
          </div>

          <div class="el-form-item">
            <label class="el-form-item__label">地 址</label>
            <div class="el-form-item__content">
              <div class="el-input">
                <input type="text" autocomplete="off" placeholder="请输入地址" class="el-input__inner"  v-model="client.address">
              </div>
            </div>
          </div>

          <div class="el-form-item">
            <!---->
            <div class="el-form-item__content">

              <el-button type="primary" @click="selectClients">
                <span>查询</span>
              </el-button>

            </div>
          </div>
        </el-form>
         

          <!-- <hr><hr><hr> -->
          <!-- 表格,用户信息 -->
          <el-table :data="tableData" border v-if="refreshTableData">
            <el-table-column label="操作" width="360">
              <template slot-scope="scope">
                <el-button
                  type="danger"
                  size="mini"
                  @click="onSubmit(scope.row)"
                  >删 除</el-button>
                <el-button
                  type="primary"
                  size="mini"
                  @click="updateClient(scope.row.clientId)"
                  >编 辑</el-button>
                  
                <el-button 
                  type="success" 
                  size="middle"
                  @click="buybuybuy(scope.row.clientId)"
                  plain
                  >下 单</el-button>
                <el-button
                  type="primary"
                  size="mini"
                  @click="checkOrdersByClient(scope.row.clientId)"
                  >查看订单</el-button>
              </template>
            </el-table-column>

            <el-table-column
              prop="clientId"
              label="客户ID"
              width="90"
            ></el-table-column>
            <el-table-column
              prop="companyName"
              label="公司名字"
              width="160"
            ></el-table-column>
            <el-table-column
              prop="linkmanName"
              label="联系人姓名"
              width="140"
            ></el-table-column>
            <el-table-column
              prop="linkmanPos"
              label="联系人职务"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="address"
              label="地址"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="city"
              label="城市"
              width="100"
            ></el-table-column>
            <el-table-column
              prop="postcode"
              label="邮编"
              width="120"
            ></el-table-column>
            <el-table-column
              prop="country"
              label="国家"
              width="140"
            ></el-table-column>
            <el-table-column
              prop="phone"
              label="电话"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="fax"
              label="传真"
              width="180"
            ></el-table-column>

          </el-table>

          <br />
          <!-- 分页条 -->
          <!-- Pagination 分页 -->
          <el-pagination
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :total="total"
            v-if="refreshTotalContainer"

          ></el-pagination>
        </el-main>
      </el-container>
    </el-container>
      <!-- 插入客户的表单 -->
    <div class="insertClient">
      <el-dialog :visible.sync="insertClientDialogFormVisible">
        <el-form
          :model="client"
          ref="dataForm"
          label-position="center"
          label-width="120px"
          style="width: 500px; margin-left: 50px"
        >
          <el-form-item
            label="客户Id"
            prop="clientId"
            value="client.clientId"
            required="true"
          >
            <el-input v-model="client.clientId" />
          </el-form-item>

          <el-form-item
            label="公司名称"
            prop="companyName"
            value="client.postcode"
            required="true"
          >
            <el-input v-model="client.companyName" />
          </el-form-item>
          <el-form-item
            label="联系人姓名"
            prop="linkmanName"
            value="client.linkmanName"
            required="true"
          >
            <el-input v-model="client.linkmanName" />
          </el-form-item>
          <el-form-item
            label="联系人职务"
            prop="linkmanPos"
            value="client.linkmanPos"
            required="true"
          >
            <el-input v-model="client.linkmanPos" />
          </el-form-item>
          <el-form-item
            label="地址"
            prop="address"
            value="client.address"
            required="true"
          >
            <el-input v-model="client.address" />
          </el-form-item>
          <el-form-item
            label="城市"
            prop="city"
            value="client.city"
            required="true"
          >
            <el-input v-model="client.city" />
          </el-form-item>
          <el-form-item
            label="地区"
            prop="zone"
            value="client.zone"
            required="true"
          >
            <el-input v-model="client.zone" />
          </el-form-item>
          <el-form-item label="邮编" value="client.postcode" prop="postcode">
            <el-input v-model="client.postcode" />
          </el-form-item>
          <el-form-item label="国家" value="client.country" prop="country">
            <el-input v-model="client.country" />
          </el-form-item>
          <el-form-item
            label="电话"
            value="client.phone"
            prop="phone"
            required="true"
          >
            <el-input v-model="client.phone" />
          </el-form-item>
          <el-form-item label="传真" value="client.fax" prop="fax">
            <el-input v-model="client.fax" />
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="insertClientDialogFormVisible = false"
            >取消</el-button>
          <el-button type="primary" @click="createData()">确定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 更新客户的表单 -->
    <div class="updateClient">
      <!-- 更新客户的表单 -->
      <el-dialog :visible.sync="updateClientDialogFormVisible">
        <el-form
          :model="client"
          ref="dataForm"
          label-position="center"
          label-width="120px"
          style="width: 500px; margin-left: 50px"
        >
          <!-- <el-form-item label="客户Id" prop="clientId" >
            <el-input v-model="client.clientId" />
          </el-form-item> -->

          <el-form-item label="公司名称" prop="companyName" required="true">
            <el-input v-model="client.companyName" />
          </el-form-item>
          <el-form-item label="联系人姓名" prop="linkmanName" required="true">
            <el-input v-model="client.linkmanName" />
          </el-form-item>
          <el-form-item label="联系人职务" prop="linkmanPos" required="true">
            <el-input v-model="client.linkmanPos" />
          </el-form-item>
          <el-form-item label="地址" prop="address" required="true">
            <el-input v-model="client.address" />
          </el-form-item>
          <el-form-item label="城市" prop="city" required="true">
            <el-input v-model="client.city" />
          </el-form-item>
          <el-form-item label="地区" prop="zone" required="true">
            <el-input v-model="client.zone" />
          </el-form-item>
          <el-form-item label="邮编" prop="postcode">
            <el-input v-model="client.postcode" />
          </el-form-item>
          <el-form-item label="国家" prop="country">
            <el-input v-model="client.country" />
          </el-form-item>
          <el-form-item label="电话" prop="phone" required="true">
            <el-input v-model="client.phone" />
          </el-form-item>
          <el-form-item label="传真" prop="fax">
            <el-input v-model="client.fax" />
          </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="updateClientDialogFormVisible = false"
            >取消</el-button
          >
          <el-button type="primary" @click="updateClientConfirm()"
            >确定</el-button
          >
        </div>
      </el-dialog>
    </div>

    <!-- 展示订单对话框 -->
    <div class="ordersAll">
        <el-dialog :visible.sync="allOrdersDialogFormVisible" @close="allOrdersDialogFormClosed" width="80%">
            <!-- 表格,订单信息 -->
          <el-table :data="ordersData" border>
            <el-table-column
              prop="orderId"
              label="订单ID"
              width="90"
            ></el-table-column>
            
            <el-table-column
              prop="ownerName"
              label="货主姓名"
              width="160"
            ></el-table-column>
            <el-table-column
              prop="ownerAddress"
              label="货主地址"
              width="180"
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
            <el-table-column
              prop="shipperId"
              label="运货商Id"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="totalCost"
              label="总价"
              width="120"
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
              prop="arriveDate"
              label="到货日期"
              width="200"
            ></el-table-column>
            <el-table-column
              prop="confirmDate"
              label="货款确认日期"
              width="200"
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
            <el-button @click="allOrdersDialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="allOrdersDialogFormVisible = false">确 定</el-button>
          </span>
        </el-dialog>
    </div>

    <!-- 展示某一个客户的订单的对话框 -->
    <div class="ordersClients">
        <el-dialog :visible.sync="clientOrdersDialogFormVisible" v-if="refreshOnOrdersClients" @close="clientOrdersDialogFormClosed" width="80%">
            <!-- 表格,订单信息 -->
          <el-table :data="clientOrders" border>
            <el-table-column label="操作" width="340">
              <template slot-scope="scope">
                <el-button
                  type="danger"
                  size="mini"
                  v-if="scope.row.deliverDate == null || scope.row.deliverDate == undefined"
                  @click="deleteOrderByClientId(scope.row.orderId)"
                  >删 除 订 单</el-button>
                <el-button
                  type="warning"
                  size="mini"
                  v-if="scope.row.deliverDate == null || scope.row.deliverDate == undefined"
                  @click="updateOrderByClientId(scope.row.orderId)"
                  >编 辑 订 单</el-button>
                <el-button
                  type="success"
                  size="mini"
                  v-if="scope.row.arriveDate && (scope.row.confirmDate == null || scope.row.confirmDate == undefined)"
                  @click="confirmOrderArrive(scope.row.orderId)"
                  >确 定 收 货</el-button>

                  <el-button
                  type="info"
                  size="mini"
                  round
                  @click="showOrderDetails(scope.row.orderId)"
                  >查看订单明细</el-button>
              </template>
            </el-table-column>

            <el-table-column
              prop="orderId"
              label="订单ID"
              width="90"
            ></el-table-column>
            <el-table-column
              prop="ownerName"
              label="货主姓名"
              width="160"
            ></el-table-column>
            <el-table-column
              prop="ownerAddress"
              label="货主地址"
              width="180"
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
            <el-table-column
              prop="shipperId"
              label="运货商Id"
              width="180"
            ></el-table-column>
            <el-table-column
              prop="totalCost"
              label="总价"
              width="120"
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
              prop="arriveDate"
              label="到货日期"
              width="200"
            ></el-table-column>
            <el-table-column
              prop="confirmDate"
              label="货款确认日期"
              width="200"
            ></el-table-column>
          </el-table>
          <!-- Pagination 分页 -->
          <br><br>
          <el-pagination
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChangeOrderClient"
            @current-change="handleCurrentChangeOrderClient"
            :total="totalClientOrders"
          ></el-pagination>
          <span slot="footer" class="dialog-footer">
            <el-button @click="clientOrdersDialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="clientOrdersDialogFormVisible = false">确 定</el-button>
          </span>
        </el-dialog>
    </div>

    <!-- 下单对话框 -->
    <div class="ordersAll">
        <el-dialog :visible.sync="buybuybuyDialogFormVisible" v-if="refreshBuyBuyBuyDialog" @close="closeBuyBuyBuy" width="50%">
            <!-- 表单,订单信息 -->
            <p style="font-size: 24px">请选择商品先：</p>
          <template>

              <el-table
                ref="multipleTable"
                :data="productsData"
                tooltip-effect="dark"
                style="width: 100%"
                height="400"
                show-summary
                :summary-method="getSummaries"
                @selection-change="handleProductSelectionChange">
              <el-table-column
                type="selection"
                >
              </el-table-column>
              <el-table-column  
                v-if="false"
                prop="productId"
                label="产品Id"
                
                align="center"
              ></el-table-column>
              <el-table-column
                prop="productName"
                label="产品名"
                
                align="center"
              ></el-table-column>
              <el-table-column
                prop="unitNumber"
                label="单位数量"
                
                align="center"
              ></el-table-column>
              <el-table-column
                prop="unitPrice"
                label="单价"
                align="center"
              ></el-table-column>
              <el-table-column
                prop="inventory"
                label="剩余量"
                align="center"
              ></el-table-column>
              <el-table-column prop="mount" label="数量" align="center" style="width: 10%">
                <template slot-scope="scope">
                  <el-input size="small" v-model="scope.row.mount" @change="handleEdit(scope.$index, scope.row)"></el-input>
                </template>
              </el-table-column>
            </el-table>
          </template>
          
          <br>

          <p style="font-size: 24px">请填写收货信息：</p>
          <el-form
          :model="order"
          ref="dataForm"
          label-position="center"
          label-width="120px"
          style="width: 500px; margin-left: 50px"
        >

          <el-form-item label="姓名" prop="ownerName" required="true">
            <el-input v-model="order.ownerName" />
          </el-form-item>
          <el-form-item label="地址" prop="ownerAddress" required="true">
            <el-input v-model="order.ownerAddress" />
          </el-form-item>
          <el-form-item label="城市" prop="ownerCity" required="true">
            <el-input v-model="order.ownerCity" />
          </el-form-item>
          <el-form-item label="地区" prop="ownerZone" required="true">
            <el-input v-model="order.ownerZone" />
          </el-form-item>
          <el-form-item label="邮编" prop="ownerPostcode" required="true">
            <el-input v-model="order.ownerPostcode" />
          </el-form-item>
          <el-form-item label="国家" prop="ownerCountry" required="true">
            <el-input v-model="order.ownerCountry" />
          </el-form-item>
          
        </el-form>
         
          <span slot="footer" class="dialog-footer">
            <el-button @click="closeBuyBuyBuy">取 消</el-button>
            <el-button type="primary" @click="submitOrder">确 定</el-button>
          </span>
      </el-dialog>
    </div>

    <!-- 更新订单的对话框 -->
    <div class="updateOrder">
      <!-- 更新订单的表单 -->
      <el-dialog :visible.sync="updateOrderDialogFormVisible">
        <el-form
          :model="order"
          ref="dataForm"
          label-position="center"
          label-width="120px"
          style="width: 500px; margin-left: 50px"
        >
          <el-form-item label="名字" prop="ownerName" required="true">
            <el-input v-model="order.ownerName" />
          </el-form-item>
          <el-form-item label="地址" prop="ownerAddress" required="true">
            <el-input v-model="order.ownerAddress" />
          </el-form-item>
          <el-form-item label="城市" prop="ownerCity" required="true">
            <el-input v-model="order.ownerCity" />
          </el-form-item>
          <el-form-item label="地区" prop="ownerZone" required="true">
            <el-input v-model="order.ownerZone" />
          </el-form-item>
          <el-form-item label="邮编" prop="ownerPostcode" required="true">
            <el-input v-model="order.ownerPostcode" />
          </el-form-item>
          <el-form-item label="国家" prop="ownerCountry" required="true">
            <el-input v-model="order.ownerCountry" />
          </el-form-item>
          
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="updateOrderDialogFormVisible = false"
            >取消</el-button>
          <el-button type="primary" @click="updateOrderConfirm()"
            >确定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 展示订单明细 的 对话框 -->
    <div class="ordersAll">
        <el-dialog :visible.sync="showOrderDetailsDialogVisible" @close="allOrdersDialogFormClosed" width="80%">
            <!-- 表格,订单明细的信息 -->
          <el-table 
            :data="oderDetailsAndProducts" 
            border
            show-summary
            :summary-method="getSummariesOnOrderDetailsTotalCost"
          >
            <el-table-column
              prop="productId"
              label="产品名"
              :formatter="formatProductName"
              align="center"
            ></el-table-column>
            
            <el-table-column
              prop="quantity"
              label="数量"
              align="center"
            ></el-table-column>
            <el-table-column
              prop="cost"
              label="价格"
              align="center"
            ></el-table-column>
          </el-table>

          <span slot="footer" class="dialog-footer">
            <el-button @click="showOrderDetailsDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="showOrderDetailsDialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>
    </div>

  </div>
</template>




<script>
import axios from "axios";
// import func from 'vue-editor-bridge';

export default {
  data() {
    return {
      refreshBuyBuyBuyDialog: false,
      refreshTotalContainer: true,
      // 当前下单的用户的Id
      currentClientId: "",
      // 当前查看订单的用户的Id
      currentClientOrdersId: "",
        // 查询客户
      total: "",
      pageSearch: {
        pageSize: 10,
        start: 1,
      },

      refreshOnOrdersClients: false,
      refreshTableData: true,
        // 查询订单
      totalOrders: "",
      pageSearchOrder: {
          pageSize: 10,
          start: 1,
      },

      totalClientOrders: 10,
      pageSearchOrderClient: {
          pageSize: 10,
          start: 1,
      },

      // 客户发送过来的订单
      orderClient: {
        order: {},  // 订单对象
        idp: "",
        productNumberList: []
      },

      ProductNumber:{
        productId: "",
        quantity: ""
      },

      tableData: [],
      ordersData: [],
      productsData: [],
      oderDetailsAndProducts: [],

      selectNum: "",
      selectedProducts: [],
      clientOrders: [],
      //  客户类
      client: {
        clientId: "",
        companyName: "",
        linkmanName: "",
        linkmanPos: "",
        address: "",
        city: "",
        zone: "",
        postcode: "",
        country: "",
        phone: "",
        fax: "",
      },
      allProductsData: [],
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
      // 设置表单可见
      insertClientDialogFormVisible: false,
      updateClientDialogFormVisible: false,
      allOrdersDialogFormVisible: false,
      buybuybuyDialogFormVisible: false,
      clientOrdersDialogFormVisible: false,
      updateOrderDialogFormVisible: false,

      showOrderDetailsDialogVisible: false,

      searchForm: {
        name: "",
        gender: "",
        entrydate: [],
      },
    };
  },
  methods: {
    selectClients: function(){
      // alert("???");
      axios.get("http://localhost:8080/clients?name=" + this.client.linkmanName
       + "&address=" + this.client.address
      ).then((result) => {
        this.total = result.data.data.total;
        this.tableData = result.data.data.rows;

        this.refreshTotalContainer = !this.refreshTotalContainer;
        this.refreshTotalContainer = !this.refreshTotalContainer;
      });
      this.refreshTotalContainer = !this.refreshTotalContainer;
      this.refreshTotalContainer = !this.refreshTotalContainer;
    },
    // getProductNameByProductId: function(id) {
    //   for (var i = 0; i < this.productsData.length; ++ i){
    //     if (this.productsData[i].productId == id){
    //       return this.productsData[i].productName;
    //     }
    //   };
    //   return this.productsData[0].productName;
    // },
    //     open1() {
    //     this.$notify({
    //       title: '成功',
    //       message: '这是一条成功的提示消息',
    //       type: 'success'
    //     });
    //   },
    formatProductName: function (row, column, cellValue) {
        // alert(this.productsData.length);

        for (var i = 0; i < this.allProductsData.length; ++ i){
          // alert(cellValue);
          if (this.allProductsData[i].productId == cellValue){
            // alert(cellValue);
            return this.allProductsData[i].productName;
          }
        }
        return ' ';
    },

    onSubmit: function (e) {
      // 删除用户
      this.$confirm("此操作将永久删除该用户信息，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancleButtonText: "取消",
        type: "warning",
      }).then(() => {
        axios
          .delete("http://localhost:8080/clients/" + e.clientId)
          .then((result) => {
            // alert(result.message.data),
            // alert(result.data.msg == 'success');

            if (result.data.msg == "success") {
              this.$notify({
                title: "成功",
                message: "删除成功",
                type: "success",
              });
              axios.get("http://localhost:8080/clients").then((result) => {
                this.total = result.data.data.total;
                this.tableData = result.data.data.rows;
                // alert(this.tableData[1]);
                // alert(JSon.stringify(this.tableData));
              })
              this.refreshTableData = !this.refreshTableData;
              this.refreshTableData = !this.refreshTableData;
              this.insertClientDialogFormVisible = false;
              // 立即刷新页面
              // location.reload();

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
    // 修改客户信息
    async updateClient(id) {
      this.updateClientDialogFormVisible = true;
      axios.get("http://localhost:8080/clients/" + id).then((result) => {
        this.client = result.data.data;
      });
    },

    demoButtonAlert: function () {
      alert("按钮了  ");
    },

    updateClientConfirm: function () {
      axios.put("http://localhost:8080/clients", this.client).then((result) => {
        console.log(result.data.msg);
        this.updateClientDialogFormVisible = false;
        this.$notify({
          title: "成功",
          message: "修改成功",
          type: "success",
        });
        axios.get("http://localhost:8080/clients").then((result) => {
          this.total = result.data.data.total;
          this.tableData = result.data.data.rows;
          // alert(this.tableData[1]);
          // alert(JSon.stringify(this.tableData));
        })
        this.refreshTableData = !this.refreshTableData;
        this.refreshTableData = !this.refreshTableData;
        this.insertClientDialogFormVisible = false;
        // //  延迟1s刷新页面
        // window.setTimeout(function () {
        //   window.location.reload();
        // }, 1000);
      });
    },

    handleCreate: function () {
      (this.client = {
        clientId: "",
        companyName: "",
        linkmanName: "",
        linkmanPos: "",
        address: "",
        city: "",
        zone: "",
        postcode: "",
        country: "",
        phone: "",
        fax: "",
      }),
      (this.insertClientDialogFormVisible = true);
    },
    
    //添加客户的函数
    async createData() {
      //   alert(JSON.stringify(params));
      this.dialogFormVisible = false;
      axios.post("http://localhost:8080/clients", this.client).then((result) => {
          if (result.data.msg == "success") {
            this.$notify({
              title: "成功",
              message: "添加成功",
              type: "success",
            }),
            axios.get("http://localhost:8080/clients").then((result) => {
              this.total = result.data.data.total;
              this.tableData = result.data.data.rows;
              // alert(this.tableData[1]);
              // alert(JSon.stringify(this.tableData));
            })
            this.refreshTableData = !this.refreshTableData;
            this.refreshTableData = !this.refreshTableData;
            this.insertClientDialogFormVisible = false;
          } else {
            this.$notify.error({
              title: "错误",
              message: result.data.msg,
            });
          }
        });
    },

    selectByPage: function () {
      // axios
      //   .get(
      //     "http://localhost:8080/clients?page=" +
      //       this.pageSearch.start +
      //       "&pageSize=" +
      //       this.pageSearch.pageSize
      //   )
      //   .then((result) => {
      //     this.total = result.data.data.total;
      //     this.tableData = result.data.data.rows;
      //   });
      // alert("???");
      axios.get("http://localhost:8080/clients?name=" 
      + this.client.linkmanName
      + "&address=" + this.client.address 
      + "&page=" + this.pageSearch.start
      + "&pageSize=" + this.pageSearch.pageSize
      ).then((result) => {
        this.total = result.data.data.total;
        this.tableData = result.data.data.rows;
        
        this.refreshTotalContainer = !this.refreshTotalContainer;
        this.refreshTotalContainer = !this.refreshTotalContainer;
      });
      this.refreshTotalContainer = !this.refreshTotalContainer;
      this.refreshTotalContainer = !this.refreshTotalContainer;

    },
    selectOrderByPage: function () {
        axios
        .get(
          "http://localhost:8080/orders?page=" +
            this.pageSearchOrder.start +
            "&pageSize=" +
            this.pageSearchOrder.pageSize
        )
        .then((result) => {
          this.totalOrders = result.data.data.total;
          this.ordersData = result.data.data.rows;
        });
    },
    handleSizeChange: function (val) {
      this.pageSearch.pageSize = val;
      // alert("每页记录数变化" + val);
      console.log("每页记录数变化" + val);
      // alert(this.start + " " + this.pageSize);
      // location.reload();
      this.selectByPage();
    },
    handleCurrentChange: function (val) {
      this.pageSearch.start = val;
      // alert("页码发生变化" + val);
      console.log("页码发生变化" + val);
      // alert(this.pageSearch.start + " " + this.pageSearch.pageSize);
      // location.reload();
      this.selectByPage();
      // alert(this.tableData);
    },

    // 记录订单页数变化
    handleSizeChangeOrder: function (val) {
        this.pageSearchOrder.pageSize = val;
      // alert("每页记录数变化" + val);
      console.log("订单每页记录数变化" + val);
      // alert(this.start + " " + this.pageSize);
      // location.reload();
      this.selectOrderByPage();
    },

    handleCurrentChangeOrder: function(val) {
        this.pageSearchOrder.start = val;
        // alert("页码发生变化" + val);
        console.log("订单页码发生变化" + val);
        // alert(this.pageSearch.start + " " + this.pageSearch.pageSize);
        // location.reload();
        this.selectOrderByPage();
        // alert(this.tableData);
    },

    // 查看所有订单
    handleSelectAllOrder: function(){
        
        axios.get("http://localhost:8080/orders").then((result) => {
            this.ordersData = result.data.data;
        });
        this.allOrdersDialogFormVisible = true;

    },

    // 客户下单
    buybuybuy: function(id){
      // 刷新商品列表
      axios.get("http://localhost:8080/products").then((result) => {
        this.productsData = result.data.data;
      })
      this.refreshBuyBuyBuyDialog = true;
      this.currentClientId = id;
      this.buybuybuyDialogFormVisible = true;
      console.log(id + "下蛋了");
    },
    submitOrder: function() {
      // 清空数据
      this.ProductNumber = {
        productId: "",
        quantity: ""
      },
      this.orderClient = {
        order: {},  // 订单对象
        idp: "",
        productNumberList: []
      },
      // 表单提交的order信息
      this.orderClient.order = this.order;
      // 下蛋的客户的Id
      this.orderClient.idp = this.currentClientId;
      // 产品： 数量
      for(var i = 0; i < this.selectedProducts.length; i ++){
        this.orderClient.productNumberList.push({productId: this.selectedProducts[i].productId, quantity: this.selectedProducts[i].mount});
      }
      // await
      axios.post("http://localhost:8080/clients/newOrder", this.orderClient).then((result) =>{
          console.log(result.data.data);
          if(result.data.msg == 'success'){
            this.$notify({
              title: "成功",
              message: "下单成功",
              type: "success",
            })
            
          } else {
            this.$notify.error({
              title: "下单失败",
              message: "还没有选择商品 或 选择的某商品数大于剩余~"
            })
          }
      }),
      this.buybuybuyDialogFormVisible = false;
      this.refreshBuyBuyBuyDialog = false;
      location.reload();
      this.order = {
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
      }
    },
    closeBuyBuyBuy: function(){
      this.buybuybuyDialogFormVisible = false;
      this.refreshBuyBuyBuyDialog = false;
      this.order = {
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
      }
    },
    // 合计
    getSummaries:function(param){
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index == 0) {
          sums[index] = '总价';
          return;
        }

        if (column.property === 'mount'){
          const values = data.map(item => Number(this.selectedProducts.find(m => m.productName == item.productName) ? (item.unitPrice * item.mount).toFixed(2) : 0));

          sums[index] = values.reduce((prev, curr) => {

          const value = Number(curr);
          if (!isNaN(value)) {
          return prev + curr;
          } else {
          return prev;
          }
          }, 0);
          sums[index]
        }
      })
      return sums;
    },
    getSummariesOnOrderDetailsTotalCost: function(param){
      const { columns, data } = param;
      const sums = [];
      columns.forEach((column, index) => {
        if (index == 0) {
          sums[index] = '总价';
          return;
        }
        if (column.property === 'cost'){
          const values = data.map(item => Number(item.cost));

          sums[index] = values.reduce((prev, curr) => {

          const value = Number(curr);
          if (!isNaN(value)) {
          return prev + curr;
          } else {
          return prev;
          }
          }, 0);
          sums[index]
        }
      })
      return sums;
    },
    // 记录多选的变化
    handleProductSelectionChange: function(selection){
      this.selectedProducts = selection;
      for (var i = 0; i < selection.length; ++ i){
        console.log(selection[i].productName);
        console.log(selection[i].mount);
      }
      this.selectNum = selection.length;
    },
     //可获取到编辑的数据
    handleEdit(index, row) {
      console.log(row.mount + row.productName);
    },

    // 查看某一个客户的订单
    checkOrdersByClient: function(id){
      this.currentClientOrdersId = id;
      axios
        .get(
          "http://localhost:8080/clients/orders?id=" + this.currentClientOrdersId + "&page=" +
            this.pageSearchOrderClient.start +
            "&pageSize=" +
            this.pageSearchOrderClient.pageSize
        )
        .then((result) => {
          this.clientOrders = result.data.data.rows;
          this.totalClientOrders = result.data.data.total;
        });

      this.clientOrdersDialogFormVisible = true;
      this.refreshOnOrdersClients = true;
    },

    selectOrderClientByPage: function(){
      axios
        .get(
          "http://localhost:8080/clients/orders?id=" + this.currentClientOrdersId + "&page=" +
            this.pageSearchOrderClient.start +
            "&pageSize=" +
            this.pageSearchOrderClient.pageSize
        )
        .then((result) => {
          this.clientOrders = result.data.data.rows;
          this.totalClientOrders = result.data.data.total;
        });
    },
        // 记录订单页数变化
    handleSizeChangeOrderClient: function (val) {
        this.pageSearchOrderClient.pageSize = val;
      // alert("每页记录数变化" + val);
      console.log("订单每页记录数变化" + val);
      // alert(this.start + " " + this.pageSize);
      // location.reload();
      this.selectOrderClientByPage();
    },

    handleCurrentChangeOrderClient: function(val) {
        this.pageSearchOrderClient.start = val;
        // alert("页码发生变化" + val);
        console.log("订单页码发生变化" + val);
        // alert(this.pageSearch.start + " " + this.pageSearch.pageSize);
        // location.reload();
        this.selectOrderClientByPage();
        // alert(this.tableData);
    },
    // 确定收货了
    confirmOrderArrive: function(id){
        axios.put("http://localhost:8080/clients/confirm/" + id).then((result) => {
          console.log(result.data.msg, id + "确定货款信息");
          this.$notify({
            title:"成功",
            message:"确认货款成功",
            type:'success'
          });
          axios.get("http://localhost:8080/clients/orders?id=" + this.currentClientOrdersId 
          + "&page=" + this.pageSearchOrderClient.start + "&pageSize=" + this.pageSearchOrderClient.pageSize
          ).then((result) => {
            this.clientOrders = result.data.data.rows;
            this.totalClientOrders = result.data.data.total;
          });
        });
        this.refreshOnOrdersClients = !this.refreshOnOrdersClients;
        this.refreshOnOrdersClients = !this.refreshOnOrdersClients;
        
    },

    // 关闭表单还原分页的页码和每一页的个数
    clientOrdersDialogFormClosed: function(){
      this.pageSearchOrderClient.start = 1;
      this.pageSearchOrderClient.pageSize = 10;
      this.pageSearchOrder.start = 1;
      this.pageSearchOrder.pageSize = 10;
      this.pageSearch.start = 1;
      this.pageSearch.pageSize = 10;
      this.refreshOnOrdersClients = false;
    },
    allOrdersDialogFormClosed:function(){
      this.clientOrdersDialogFormClosed;
    },
    deleteOrderByClientId: function(id){
      axios.delete("http://localhost:8080/clients/deleteOrder/" + id).then((result) => {

        console.log("用户删除订单" + result.data.msg + ", " + id);
        this.$notify({
          title: "成功",
          message: "删除成功",
          type: "success",
        });
        axios.get("http://localhost:8080/clients/orders?id=" + this.currentClientOrdersId 
          + "&page=" + this.pageSearchOrderClient.start + "&pageSize=" + this.pageSearchOrderClient.pageSize
          ).then((result) => {
            this.clientOrders = result.data.data.rows;
            this.totalClientOrders = result.data.data.total;
        });
      }),
      this.refreshOnOrdersClients = !this.refreshOnOrdersClients;
      this.refreshOnOrdersClients = !this.refreshOnOrdersClients;
      // //  延迟1s刷新页面
      // window.setTimeout(function () {
      //   window.location.reload();
      // }, 1000);
      
    },

    updateOrderByClientId: function(id){
      axios.get("http://localhost:8080/orders/" + id).then((result) => {
        this.order = result.data.data;
      })
      this.updateOrderDialogFormVisible = true;
    },
    updateOrderConfirm: function(){
      axios.put("http://localhost:8080/orders", this.order).then((result) => {
        this.$notify({
          title: "成功",
          message: "修改订单成功",
          type: "success",
        });
        console.log(result.data.msg, '成功修改订单');
        axios.get("http://localhost:8080/clients/orders?id=" + this.currentClientOrdersId 
          + "&page=" + this.pageSearchOrderClient.start + "&pageSize=" + this.pageSearchOrderClient.pageSize
          ).then((result) => {
            this.clientOrders = result.data.data.rows;
            this.totalClientOrders = result.data.data.total;
        });
      }),
      this.updateOrderDialogFormVisible = false;
      this.refreshOnOrdersClients = !this.refreshOnOrdersClients;
      this.refreshOnOrdersClients = !this.refreshOnOrdersClients;
      // window.setTimeout(function () {
      //   window.location.reload();
      // }, 1000);
    },
    // 显示订单明细
    showOrderDetails: function(id){
      axios.get("http://localhost:8080/clients/orderDetails/" + id).then((result) => {
        this.oderDetailsAndProducts = result.data.data;
      });
      this.showOrderDetailsDialogVisible = true;

    },
  },
  mounted() {
    //发送异步请求,获取数据
    axios.get("http://localhost:8080/clients").then((result) => {
      this.total = result.data.data.total;
      this.tableData = result.data.data.rows;
      // alert(this.tableData[1]);
      // alert(JSon.stringify(this.tableData));
    }),
    // 把数据预先加载到 vue 的数据模型中
    axios.get("http://localhost:8080/orders").then((result) => {
      this.totalOrders = result.data.data.total;
      this.ordersData = result.data.data.rows;
    }),
    // 获取产品的信息
    axios.get("http://localhost:8080/products").then((result) => {
      this.productsData = result.data.data;
      // alert(this.productsData.length);
    });
    // 获取产品的信息
    axios.get("http://localhost:8080/products/all").then((result) => {
      this.allProductsData = result.data.data;
      // alert(this.productsData.length);
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

.body{
  margin: 0px;
}
.col, .col-1, .col-10, .col-11, .col-12, .col-2, .col-3, .col-4, .col-5, .col-6, .col-7, .col-8, .col-9, .col-auto, .col-lg, .col-lg-1, .col-lg-10, .col-lg-11, .col-lg-12, .col-lg-2, .col-lg-3, .col-lg-4, .col-lg-5, .col-lg-6, .col-lg-7, .col-lg-8, .col-lg-9, .col-lg-auto, .col-md, .col-md-1, .col-md-10, .col-md-11, .col-md-12, .col-md-2, .col-md-3, .col-md-4, .col-md-5, .col-md-6, .col-md-7, .col-md-8, .col-md-9, .col-md-auto, .col-sm, .col-sm-1, .col-sm-10, .col-sm-11, .col-sm-12, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-auto, .col-xl, .col-xl-1, .col-xl-10, .col-xl-11, .col-xl-12, .col-xl-2, .col-xl-3, .col-xl-4, .col-xl-5, .col-xl-6, .col-xl-7, .col-xl-8, .col-xl-9, .col-xl-auto {
    position: relative;
    width: 100%;
    padding-right: 0;
     padding-left: 0;
}
</style>