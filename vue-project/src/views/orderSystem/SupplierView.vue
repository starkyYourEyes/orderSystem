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
          <el-button type="primary" size="middle" @click="handleCreateSupplier"
            >新增供货商</el-button>

          <el-button type="success" size="middle" @click="checkAllProducts"
            >查看所有产品</el-button>

          <el-button type="success" size="middle" @click="checkAllTypes"
            >查看所有产品类别</el-button>

          <br /><br />
          <!-- 表格， 供货商信息 -->
          <el-table :data="tableData" border  v-if="refreshOnAddNewSupplier">
            <el-table-column label="操 作" width="280">
              <template slot-scope="scope">
                <el-button
                  type="danger"
                  size="mini"
                  @click="deleteOneSupplier(scope.row.supplierId)"
                  >删 除</el-button>

                <el-button
                  type="primary"
                  size="mini"
                  @click="updateSupplierInfo(scope.row.supplierId)"
                  >编 辑</el-button>

                <el-button
                  type="primary"
                  size="mini"
                  icon="el-icon-upload"
                  @click="manageProductsBySupplierId(scope.row.supplierId)"
                  >管 理 产 品</el-button>
              </template>
              <!-- <el-button type="danger" size="mini">修改</el-button> -->
            </el-table-column>

            <el-table-column
              prop="supplierId"
              label="供货商ID"
              width="80"
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
              prop="zone"
              label="地区"
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
            <el-table-column
              prop="homepage"
              label="主页"
              width="180"
            ></el-table-column>
          </el-table>

        </el-main>
        <!-- 编辑供货商的表单 -->
        <div class="updateSupplier">
          <!-- 编辑供货商的表单 -->
          <el-dialog :visible.sync="updateSupplierDialogFormVisible">
            <el-form
              :model="supplier"
              ref="dataForm"
              label-position="center"
              label-width="120px"
              style="width: 500px; margin-left: 50px"
            >
              <el-form-item
                label="公司名称"
                prop="companyName"
                value="supplier.companyName"
              >
                <el-input v-model="supplier.companyName" />
              </el-form-item>
              <el-form-item
                label="联系人姓名"
                value="supplier.linkmanName"
                prop="linkmanName"
              >
                <el-input v-model="supplier.linkmanName" />
              </el-form-item>
              <el-form-item
                label="联系人职务"
                value="supplier.linkmanPos"
                prop="linkmanPos"
              >
                <el-input v-model="supplier.linkmanPos" />
              </el-form-item>
              <el-form-item
                label="地址"
                value="supplier.address"
                prop="address"
              >
                <el-input v-model="supplier.address" />
              </el-form-item>
              <el-form-item label="城市" value="supplier.city" prop="city">
                <el-input v-model="supplier.city" />
              </el-form-item>
              <el-form-item label="地区" value="supplier.zone" prop="zone">
                <el-input v-model="supplier.zone" />
              </el-form-item>
              <el-form-item
                label="邮编"
                value="supplier.postcode"
                prop="postcode"
              >
                <el-input v-model="supplier.postcode" />
              </el-form-item>
              <el-form-item
                label="国家"
                value="supplier.country"
                prop="country"
              >
                <el-input v-model="supplier.country" />
              </el-form-item>
              <el-form-item label="电话" value="supplier.phone" prop="phone">
                <el-input v-model="supplier.phone" />
              </el-form-item>
              <el-form-item label="传真" value="supplier.fax" prop="fax">
                <el-input v-model="supplier.fax" />
              </el-form-item>
              <el-form-item
                label="主页"
                value="supplier.homepage"
                prop="homepage"
              >
                <el-input v-model="supplier.homepage" />
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="updateSupplierDialogFormVisible = false"
                >取 消</el-button
              >
              <el-button type="primary" @click="updateSupplierConfirm"
                >确 定</el-button
              >
            </div>
          </el-dialog>
        </div>

         <!-- 增加 供货商的表单 -->
        <div class="insertSupplier">
         
          <el-dialog :visible.sync="insertSupplierDialogFormVisible">
            <el-form
              :model="supplier"
              ref="dataForm"
              label-position="center"
              label-width="120px"
              style="width: 500px; margin-left: 50px"
            >
              <el-form-item
                label="公司名称"
                prop="companyName"
                value="supplier.companyName"
              >
                <el-input v-model="supplier.companyName" />
              </el-form-item>
              <el-form-item
                label="联系人姓名"
                value="supplier.linkmanName"
                prop="linkmanName"
              >
                <el-input v-model="supplier.linkmanName" />
              </el-form-item>
              <el-form-item
                label="联系人职务"
                value="supplier.linkmanPos"
                prop="linkmanPos"
              >
                <el-input v-model="supplier.linkmanPos" />
              </el-form-item>
              <el-form-item
                label="地址"
                value="supplier.address"
                prop="address"
              >
                <el-input v-model="supplier.address" />
              </el-form-item>
              <el-form-item label="城市" value="supplier.city" prop="city">
                <el-input v-model="supplier.city" />
              </el-form-item>
              <el-form-item label="地区" value="supplier.zone" prop="zone">
                <el-input v-model="supplier.zone" />
              </el-form-item>
              <el-form-item
                label="邮编"
                value="supplier.postcode"
                prop="postcode"
              >
                <el-input v-model="supplier.postcode" />
              </el-form-item>
              <el-form-item
                label="国家"
                value="supplier.country"
                prop="country"
              >
                <el-input v-model="supplier.country" />
              </el-form-item>
              <el-form-item label="电话" value="supplier.phone" prop="phone">
                <el-input v-model="supplier.phone" />
              </el-form-item>
              <el-form-item label="传真" value="supplier.fax" prop="fax">
                <el-input v-model="supplier.fax" />
              </el-form-item>
              <el-form-item
                label="主页"
                value="supplier.homepage"
                prop="homepage"
              >
                <el-input v-model="supplier.homepage" />
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="insertSupplierDialogFormVisible = false"
                >取 消</el-button
              >
              <el-button type="primary" @click="createData()">确 定</el-button>
            </div>
          </el-dialog>
        </div>

        <!-- 展示所有产品的对话框 -->
        <div class="productsAll">
          <el-dialog
            :visible.sync="checkAllProductsDialogFormVisible"
            @close="allProductsDialogFormClosed"
            v-if="refreshAllProductsDialogFormClosed"
            width="80%"
          >
            <!-- 表格,产品信息 -->
            <el-table :data="productsData" border>
              <el-table-column
                prop="productId"
                label="产品ID"
                width="90"
              ></el-table-column>
              <el-table-column
                prop="productName"
                label="产品名称"
                width="160"
              ></el-table-column>
              <el-table-column
                prop="unitNumber"
                label="单位数量"
                width="140"
              ></el-table-column>
              <el-table-column
                prop="unitPrice"
                label="单价"
                width="180"
              ></el-table-column>
              <el-table-column
                prop="inventory"
                label="库存"
                width="180"
              ></el-table-column>
              <el-table-column
                prop="suspend"
                label="状态"
                width="100"
                :formatter="formatBoolean"
              ></el-table-column>
              <el-table-column
                prop="supplierId"
                label="供货商Id"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="typeId"
                label="类别Id"
                width="140"
              ></el-table-column>
            </el-table>
            <!-- Pagination 分页 -->
            <br /><br />
            <el-pagination
              background
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChangeAllProducts"
              @current-change="handleCurrentChangeAllProducts"
              :total="totalProducts"
            ></el-pagination>

            <span slot="footer" class="dialog-footer">
              <el-button @click="allProductsDialogFormClosed"
                >取 消</el-button>
              <el-button
                type="primary"
                @click="allProductsDialogFormClosed"
                >确 定</el-button>
            </span>
          </el-dialog>
        </div>

        <!-- 展示所有类别的对话框 -->
        <div class="typesAll">
          <el-dialog
            :visible.sync="checkAllTypesDialogFormVisible"
            @close="allTypesDialogFormClosed"
            
            width="80%"
          >
            <!-- 表格,类别信息 -->
            <el-table :data="typesData" border v-if="refreshOnTypeManagementDialog">

              <el-table-column>
                <template slot-scope="scope">
                  <el-button
                    type="danger"
                    size="mini"
                    @click="deleteType(scope.row.typeId)"
                    >删 除</el-button>
                  <el-button
                    type="warning"
                    size="mini"
                    @click="updateTypeInfo(scope.row.typeId)"
                    >编 辑</el-button>
                </template>
              </el-table-column>

              <el-table-column
                prop="typeId"
                label="类别ID"
                width="90"
              ></el-table-column>

              <el-table-column
                prop="typeName"
                label="类别名"
                width="180"
              ></el-table-column>
              <el-table-column
                prop="instruction"
                label="说明"
                width="480"
              ></el-table-column>

              <el-table-column>
                <template slot-scope="scope">
                  <img style="max-width: 60%" :src="img1(scope.$index) ">
                </template>
              </el-table-column>

              <!-- <el-table-column>
              <img src="data:image/png;base64,image" onload="imageLoad(this)" />
              </el-table-column> -->

            </el-table>
            
            <br /><br />

            <span slot="footer" class="dialog-footer">
              <el-button @click="checkAllTypesDialogFormVisible = false"
                >取 消</el-button>
              <el-button
                type="primary"
                @click="checkAllTypesDialogFormVisible = false"
                >确 定</el-button>
            </span>
          </el-dialog>
        </div>

        <!-- 展示某一个供货商的 产品的 对话框 -->
        <div class="productsAll">
          <el-dialog
            :visible.sync="manageProductsDialogFormVisible"
            v-if="refreshOnManageProductsDialog"
            @close="allProductsDialogFormClosed"
            width="80%"
          >

          <el-button
          type="primary"
          size="middle"
          @click="addNewProductsBySupplier(currentSupplierId)"
          >上 架 新 品</el-button>

          <el-button
          type="primary"
          size="middle"
          @click="addNewTypes"
          >新 增 类 别</el-button>

          <br><br>

            <!-- 表格, 产品信息 -->
            <el-table :data="productsData" border>

              <el-table-column label="操作" width="240">
                <template slot-scope="scope">
                    <el-button
                    type="danger"
                    size="mini"
                    @click="deleteProductBySupplier(scope.row.productId)"
                    >删 除 产 品</el-button>
                    <el-button
                    type="warning"
                    size="mini"
                    @click="updateProductBySupplier(scope.row.productId)"
                    >修 改 产 品</el-button>
                </template>
            </el-table-column>

              <el-table-column
                prop="productId"
                label="产品ID"
                width="90"
              ></el-table-column>
              <el-table-column
                prop="productName"
                label="产品名称"
                width="160"
              ></el-table-column>
              <el-table-column
                prop="unitNumber"
                label="单位数量"
                width="140"
              ></el-table-column>
              <el-table-column
                prop="unitPrice"
                label="单价"
                width="180"
              ></el-table-column>
              <el-table-column
                prop="inventory"
                label="库存"
                width="180"
              ></el-table-column>
              <el-table-column
                prop="suspend"
                label="状态"
                width="100"
                :formatter="formatBoolean"
              ></el-table-column>
              <el-table-column
                prop="supplierId"
                label="供货商Id"
                width="120"
              ></el-table-column>
              <el-table-column
                prop="typeId"
                label="类别Id"
                width="140"
              ></el-table-column>
            </el-table>
            <!-- Pagination 分页 -->
            <br /><br />
            <el-pagination
              background
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChangeAllProducts"
              @current-change="handleCurrentChangeAllProducts"
              :total="totalProducts"
            ></el-pagination>

            <span slot="footer" class="dialog-footer">
              <el-button @click="manageProductsDialogFormVisible = false"
                >取 消</el-button>
              <el-button
                type="primary"
                @click="manageProductsDialogFormVisible = false"
                >确 定</el-button>
            </span>
          </el-dialog>
        </div>

        <!-- 编辑产品的表单 -->
        <div class="updateProductInfo">
          <!-- 编辑产品的表单 -->
          <el-dialog :visible.sync="updateProductInfoDialogFormVisible">
            <el-form
              :model="product"
              ref="dataForm"
              label-position="center"
              label-width="120px"
              style="width: 500px; margin-left: 50px"
            >
              <el-form-item
                label="产品名称"
                prop="productName"
                value="product.productName"
              >
                <el-input v-model="product.productName" />
              </el-form-item>

              <el-form-item
                label="单位数量"
                value="product.unitNumber"
                prop="unitNumber"
              >
                <el-input v-model="product.unitNumber" />
              </el-form-item>

              <el-form-item
                label="单价"
                value="product.unitPrice"
                prop="unitPrice"
              >
                <el-input v-model="product.unitPrice" />
              </el-form-item>

              <el-form-item
                label="库存量"
                value="product.inventory"
                prop="inventory"
              >
                <el-input v-model="product.inventory" />
              </el-form-item>

              <el-form-item
                label="状态"
                value="product.suspend"
                prop="suspend"
              >
                <el-input v-model="product.suspend" />
              </el-form-item>

              
              <el-form-item label="类别">
                <el-select v-model="product.typeId" style="width: 240px">
                  <el-option
                    v-for="item in typesData"
                    :key="item.typeId"
                    :label="item.typeName"
                    :value="item.typeId">
                  </el-option>
                </el-select>
              </el-form-item>
              
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="updateProductInfoDialogFormVisible = false"
                >取 消</el-button
              >
              <el-button type="primary" @click="updateProductInfoConfirm"
                >确 定</el-button
              >
            </div>
          </el-dialog>
        </div>

        <!-- 增加 产品 的表单 -->
        <div class="insertProduct">
          <el-dialog :visible.sync="insertProductDialogFormVisible">
            <el-form
              :model="product"
              ref="dataForm"
              label-position="center"
              label-width="120px"
              style="width: 500px; margin-left: 50px"
            >
              <el-form-item
                label="产品名"
                prop="productName"
                value="product.productName"
              >
                <el-input v-model="product.productName" />
              </el-form-item>
              <el-form-item
                label="单位数量"
                value="product.unitNumber"
                prop="unitNumber"
              >
                <el-input v-model="product.unitNumber" />
              </el-form-item>
              <el-form-item
                label="单价"
                value="product.unitPrice"
                prop="unitPrice"
              >
                <el-input v-model="product.unitPrice" />
              </el-form-item>

              <el-form-item
                label="库存量"
                value="product.inventory"
                prop="inventory"
              >
                <el-input v-model="product.inventory" />
              </el-form-item>
              
              <!-- <el-form-item
                label="类别Id"
                value="product.typeId"
                prop="typeId"
              >
                <el-input v-model="product.typeId" />
              </el-form-item> -->
              <el-form-item label="类别">
                <el-select v-model="product.typeId" style="width: 240px">
                  <el-option
                    v-for="item in typesData"
                    :key="item.typeId"
                    :label="item.typeName"
                    :value="item.typeId">
                  </el-option>
                </el-select>
              </el-form-item>
              
              
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="insertProductDialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="insertProductConfirm()">确 定</el-button>
            </div>
          </el-dialog>
        </div>


        <!-- 增加 类别 的表单 -->
        <div class="insertType">
          <el-dialog :visible.sync="insertTypeDialogFormVisible">
            <el-form
              :model="type"
              ref="dataForm"
              label-position="center"
              label-width="120px"
              style="width: 500px; margin-left: 50px"
            >
              <el-form-item
                label="类别名"
                prop="typeName"
                value="type.typeName"
              >
                <el-input v-model="type.typeName" />
              </el-form-item>
              <el-form-item
                label="说明"
                value="type.instruction"
                prop="instruction"
              >
                <el-input v-model="type.instruction" />
              </el-form-item>

              <!-- <el-form-item
                label="图片"
                value="type.image"
                prop="image"
              >
                <el-input v-model="type.image" />
              </el-form-item> -->

              <el-form-item label="图片" value="type.image">
                <td class="column"></td>
                  <td colspan="3">
                    <el-upload 
                      action="http://localhost:8080/types/imageUpload"
                      :show-file-list="false"
                        :auto-upload="true"
                        :multiple="false"
                        :on-change="uploadFile"
                        drag
                        accept="image/jpg,image/jpeg,image/png,image/bmp">
                        <i v-if="imageUrl" class="el-icon-circle-close deleteImg" @click.stop="handleRemove"></i>
                        <img v-if="imageUrl" style="max-width: 100%" :src="imageUrl" class="el-upload--picture-car" />
                        <div v-else>
                          <i class="el-icon-picture" style="margin-top: 40px; font-size: 40px; color: #999a9c"></i>
                            <br>
                            <div>上传图片</div>
                            <div>格式为png、jpeg或jpg</div>
                        </div>
                    </el-upload>
                  </td>
              </el-form-item>

            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="insertTypeDialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="insertTypeConfirm()">确 定</el-button>
            </div>
          </el-dialog>
        </div>

            <!-- 编辑 类别的 的表单 -->
        <div class="updateShipper">
          <!-- 编辑类别的表单 -->
          <el-dialog :visible.sync="updateTypeInfoDialogFormVisible" @close="closeUpdateTypesInfoDialog">
            <el-form
              :model="type"
              ref="dataForm"
              label-position="center"
              label-width="120px"
              style="width: 500px; margin-left: 50px"
            >
              <el-form-item
                label="类别名称"
                prop="typeName"
                value="type.typeName"
                required="true"
              >
                <el-input v-model="type.typeName" />
              </el-form-item>

              <el-form-item
                label="描述"
                value="type.instruction"
                prop="instruction"
                required="true"
              >
                <el-input v-model="type.instruction" />
              </el-form-item>

              <el-form-item label="图片" value="type.image">
                <td class="column"></td>
                  <td colspan="3">
                    <el-upload 
                      action="http://localhost:8080/types/imageUpload"
                      :show-file-list="false"
                        :auto-upload="true"
                        :multiple="false"
                        :on-change="uploadFile"
                        drag
                        accept="image/jpg,image/jpeg,image/png,image/bmp">
                        <i v-if="imageUrl" class="el-icon-circle-close deleteImg" @click.stop="handleRemove"></i>
                        <img v-if="imageUrl" style="max-width: 100%" :src="imageUrl" class="el-upload--picture-car" />
                        <div v-else>
                          <i class="el-icon-picture" style="margin-top: 40px; font-size: 40px; color: #999a9c"></i>
                            <br>
                            <div>上传图片</div>
                            <div>格式为png、jpeg或jpg</div>
                        </div>
                    </el-upload>
                  </td>
              </el-form-item>

            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="updateTypeInfoDialogFormVisible = false">取消</el-button>
              <el-button type="primary" @click="updateTypeInfoConfirm">确定</el-button>
            </div>
          </el-dialog>
        </div>

        <el-dialog
          title="提示"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose">
          <span>修改成功，图片在刷新后更新展示</span>
          <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </span>
        </el-dialog>
        
      </el-container>
    </el-container>
  </div>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {

      dialogVisible: false,
      // 新增产品的 下拉菜单选择类别信息
      optionsOfTypes: [],

      tableData: [],
      productsData: [],
      typesData: [],


      refreshOnAddNewSupplier: true,
      refreshOnManageProductsDialog: false,
      refreshAllProductsDialogFormClosed: true,
      refreshOnTypeManagementDialog: true,
      totalProducts: 10,
      pageSearchAllProducts: {
        start: 1,
        pageSize: 10,
      },

      // 记录当前supplierId
      currentSupplierId: "",

      updateSupplierDialogFormVisible: false,
      insertSupplierDialogFormVisible: false,
      checkAllProductsDialogFormVisible: false,

      checkAllTypesDialogFormVisible: false,
      manageProductsDialogFormVisible: false,
      insertProductDialogFormVisible: false,
      insertTypeDialogFormVisible: false,
      updateProductInfoDialogFormVisible: false,

      updateTypeInfoDialogFormVisible: false,
      supplier: {
        supplierId: "",
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
        homepage: "",
      },
      product: {
        productId: "",
        productName: "",
        unitNumber: "",
        unitPrice: "",
        inventory: "",
        suspend: "",
        supplierId: "",
        typeId: "",
      },
      type: {
        typeId: "",
        typeName: "",
        instruction: "",
        image: "",
      },

      formData: "",
      imageUrl: "",

      searchForm: {
        name: "",
        gender: "",
        entrydate: [],
      },
    };
  },
  methods: {

    // 插入图片，返回图片的url
    img1: function(index){
      var url = "http://localhost:8080/types/image/" + index;
      return url;
    },
    onSubmit: function () {
      // alert(JSON.stringify(val))
    },

    deleteOneSupplier: function (id) {
      // alert("sdawdawda"),
      this.$confirm("此操作将永久删除该用户信息，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancleButtonText: "取消",
        type: "warning",
      }).then(() => {
        axios.delete("http://localhost:8080/suppliers/" + id).then((result) => {
          // alert(result.message.data),
          // alert(result.data.msg == 'success');

          if (result.data.msg == "success") {
            this.$notify({
              title: "成功",
              message: "删除成功",
              type: "success",
            });
            axios.get("http://localhost:8080/suppliers").then((result) => {
              this.tableData = result.data.data;
            });
            this.refreshOnAddNewSupplier = !this.refreshOnAddNewSupplier;
            this.refreshOnAddNewSupplier = !this.refreshOnAddNewSupplier;
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

    // 编辑供货商信息
    updateSupplierInfo: function (id) {
      axios.get("http://localhost:8080/suppliers/" + id).then((result) => {
        this.supplier = result.data.data; // 回显
      }),
        (this.updateSupplierDialogFormVisible = true);
    },
    updateSupplierConfirm: function () {
      axios.put("http://localhost:8080/suppliers", this.supplier).then((result) => {
          console.log(result.data.msg);
          this.updateSupplierDialogFormVisible = false;
          this.$notify({
            title: "成功",
            message: "修改成功",
            type: "success",
          });
          axios.get("http://localhost:8080/suppliers").then((result) => {
            this.tableData = result.data.data;
          });
          this.refreshOnAddNewSupplier = !this.refreshOnAddNewSupplier;
          this.refreshOnAddNewSupplier = !this.refreshOnAddNewSupplier;
          // //  延迟1s刷新页面
          // window.setTimeout(function () {
          //   window.location.reload();
          // }, 1000);
        });
    },

    // 新增供货商
    handleCreateSupplier: function () {
      (this.supplier = {
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
        homepage: "",
      }),
      (this.insertSupplierDialogFormVisible = true);
    },
    createData: function () {
      axios.post("http://localhost:8080/suppliers", this.supplier).then((result) => {
          axios.get("http://localhost:8080/suppliers").then((result) => {
            this.tableData = result.data.data;
          });
          this.refreshOnAddNewSupplier = !this.refreshOnAddNewSupplier;
          this.refreshOnAddNewSupplier = !this.refreshOnAddNewSupplier;

          console.log("新增供货商成功" + result.data.msg);
          this.$notify({
            title: "成功",
            message: "新增供货商成功",
            type: "success",
          }),
          (this.insertSupplierDialogFormVisible = false)
            // window.setTimeout(function () {
            //   window.location.reload();
            // }, 1000);
        });
    },

    handleSizeChange: function (val) {
      alert("每页记录数变化" + val);
    },
    handleCurrentChange: function (val) {
      alert("页码发生变化" + val);
    },
    // 查看所有产品
    checkAllProducts: function () {
      axios.get("http://localhost:8080/suppliers/products?page=" +
        this.pageSearchAllProducts.start +"&pageSize=" +this.pageSearchAllProducts.pageSize
        ).then((result) => {
        this.productsData = result.data.data.rows;
        this.totalProducts = result.data.data.total;
        this.refreshAllProductsDialogFormClosed = true;
        this.checkAllProductsDialogFormVisible = true;
      });
      
    },
    // 关闭查看所有的产品的对话框的函数
    allProductsDialogFormClosed: function(){
      this.checkAllProductsDialogFormVisible = false;
      this.refreshOnManageProductsDialog = false;
      location.reload();
      this.pageSearchAllProducts.start = 1;
      this.pageSearchAllProducts.pageSize = 10;
      this.refreshAllProductsDialogFormClosed = false;
    },

    selectAllProductsByPage: function(){
      axios
      .get(
        "http://localhost:8080/suppliers/products?id=" + this.currentSupplierId + "&page=" +
          this.pageSearchAllProducts.start +
          "&pageSize=" +
          this.pageSearchAllProducts.pageSize
      )
      .then((result) => {
        this.productsData = result.data.data.rows;
        this.totalProducts = result.data.data.total;
      });
    },
    // 记录产品页数变化
    handleSizeChangeAllProducts: function (val) {
        this.pageSearchAllProducts.pageSize = val;
        
        console.log("订单每页记录数变化" + val);
        this.selectAllProductsByPage();
    },

    handleCurrentChangeAllProducts: function(val) {
        this.pageSearchAllProducts.start = val;
        console.log("订单页码发生变化" + val);
        this.selectAllProductsByPage();
        
    },

    // 查看所有的产品的类别的对话框
    checkAllTypes: function(){
      axios.get("http://localhost:8080/types").then((result) => {
        this.typesData = result.data.data;
      });
      this.checkAllTypesDialogFormVisible = true;
      this.refreshOnTypeManagementDialog = true;
    },
    allTypesDialogFormClosed: function(){
      this.checkAllTypesDialogFormVisible = false;
    },

    // 管理商品的对话框
    manageProductsBySupplierId: function (id) {
      this.currentSupplierId = id;
      this.manageProductsDialogFormVisible = true;
      this.refreshOnManageProductsDialog = true;
      axios.get("http://localhost:8080/suppliers/products?id=" + this.currentSupplierId + "&page=" +
        this.pageSearchAllProducts.start +
        "&pageSize=" +
        this.pageSearchAllProducts.pageSize
      ).then((result) => {
        this.productsData = result.data.data.rows;
        this.totalProducts = result.data.data.total;
      });

    },

    // 删除产品
    deleteProductBySupplier: function(id){
      
      this.$confirm("此操作将永久删除该产品信息，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancleButtonText: "取消",
        type: "warning",
      }).then(() => {

        axios.delete("http://localhost:8080/products/" + id).then((result) => {
          console.log(result.data.msg, '成功删除产品id' + id);
          axios.get("http://localhost:8080/suppliers/products?id=" + this.currentSupplierId + "&page=" +
            this.pageSearchAllProducts.start +
            "&pageSize=" +
            this.pageSearchAllProducts.pageSize
          ).then((result) => {
            this.productsData = result.data.data.rows;
            this.totalProducts = result.data.data.total;
          });

          this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;
          this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;

        });

        this.$notify({
          title: "成功",
          message: "删除成功",
          type: "success",
        });
        // window.setTimeout(function () {
        //   window.location.reload();
        // }, 1000);
      });
    },
    // 更新产品信息
    updateProductBySupplier: function(id){
      axios.get("http://localhost:8080/products/" + id).then((result) => {
        this.product = result.data.data;
      }),
      this.updateProductInfoDialogFormVisible = true;
    },  
    updateProductInfoConfirm: function(){
      axios
        .put("http://localhost:8080/products", this.product)
        .then((result) => {
          console.log(result.data.msg);

          if(result.data.msg == "success"){
            this.$notify({
              title: "成功",
              message: "修改成功",
              type: "success",
            });
            axios.get("http://localhost:8080/suppliers/products?id=" + this.currentSupplierId + "&page=" +
              this.pageSearchAllProducts.start +
              "&pageSize=" +
              this.pageSearchAllProducts.pageSize
            ).then((result) => {
              this.productsData = result.data.data.rows;
              this.totalProducts = result.data.data.total;
            });

            this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;
            this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;

            // //  延迟1s刷新页面
            // window.setTimeout(function () {
            //   window.location.reload();
            // }, 1000);
            this.updateProductInfoDialogFormVisible = false;
          } else {
            this.$notify.error({
              title: "失败",
              message: "请检查商品信息输入是否有误后重新提交",
            });
          }
        });
    },

    // 新增产品
    addNewProductsBySupplier: function(id){
      this.product.supplierId = id;
      this.insertProductDialogFormVisible = true;
    },
    insertProductConfirm: function(){
      axios.post("http://localhost:8080/products", this.product).then((result) => {
        console.log(result.data.msg, this.product.supplierId + "新增产品成功");
        if(result.data.msg == "success"){
          this.$notify({
            title: "成功",
            message: "商品'" + this.product.productName + "'成功上架",
            type: "success",
          });
          this.insertProductDialogFormVisible = false;
          axios.get("http://localhost:8080/suppliers/products?id=" + this.currentSupplierId + "&page=" +
            this.pageSearchAllProducts.start +
            "&pageSize=" +
            this.pageSearchAllProducts.pageSize
          ).then((result) => {
            this.productsData = result.data.data.rows;
            this.totalProducts = result.data.data.total;
          });

          this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;
          this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;

          this.product = {
            productId: "",
            productName: "",
            unitNumber: "",
            unitPrice: "",
            inventory: "",
            suspend: "",
            supplierId: "",
            typeId: "",
          }
          // window.setTimeout(function () {
          //   window.location.reload();
          // }, 1000);
        } else {
          this.$notify.error({
            title: "失败",
            message: "请检查商品信息输入是否有误后重新提交",
          });
        }
      })
    },

    // 新增产品类别
    addNewTypes: function(){
      this.insertTypeDialogFormVisible = true;
    },
    insertTypeConfirm: function(){
      axios.post("http://localhost:8080/types", this.type).then((result) => {
        console.log(result.data.msg, "新增产品类别成功");
        if(result.data.msg == "success"){
          this.$notify({
            title: "成功",
            message: "类别'" + this.type.typeName + "'成功添加",
            type: "success",
          });

          // 更新类别数据
          axios.get("http://localhost:8080/types").then((result) => {
            this.typesData = result.data.data;
          });

          this.type = {
            typeId: "",
            typeName: "",
            instruction: "",
            image: "",
          }
          this.imageUrl = "";

          this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;
          this.refreshOnManageProductsDialog = !this.refreshOnManageProductsDialog;
          
          this.insertTypeDialogFormVisible = false;
          // window.setTimeout(function () {
          //   window.location.reload();
          // }, 1000);
        }
      })
    },
    // 上传图片
    uploadFile(item) {
      this.formData = item.raw;
      this.imageUrl = URL.createObjectURL(item.raw);
    },
    handleRemove: function(){
      this.imageUrl = '';
    },
    
    // 获取图片真实高度


    // 显示boolean值
    // 布尔值格式化：cellValue为后台返回的值
    formatBoolean: function (row, column, cellValue) {
        var ret = ''  //你想在页面展示的值
        if (cellValue) {
            ret = "停售"  //根据自己的需求设定
        } else {
            ret = "在售"
        }
        return ret;
    },

    // 删除类别
    deleteType: function(id){
      this.$confirm("此操作将永久删除该类别，是否继续？", "提示", {
        confirmButtonText: "确定",
        cancleButtonText: "取消",
        type: "warning",
      }).then(() => {
        axios.delete("http://localhost:8080/types/" + id).then((result) => {
          console.log(result.data.msg, '成功删除产品id' + id);
          axios.get("http://localhost:8080/types").then((result) => {
            this.typesData = result.data.data;
            this.refreshOnTypeManagementDialog = !this.refreshOnTypeManagementDialog;
            this.refreshOnTypeManagementDialog = !this.refreshOnTypeManagementDialog;
          });
        });
        this.$notify({
          title: "成功",
          message: "删除成功",
          type: "success",
        });
        // window.setTimeout(function () {
        //   window.location.reload();
        // }, 1000);
      });
    },
    // 更新类别信息
    updateTypeInfo: function(id){
      axios.get("http://localhost:8080/types/" + id).then((result) => {
        this.type = result.data.data;
      })
      this.updateTypeInfoDialogFormVisible = true;
    },
    updateTypeInfoConfirm: function() {
      axios.put("http://localhost:8080/types", this.type).then((result) => {
          console.log(result.data.msg);
          
          if(result.data.msg == "success"){
            this.dialogVisible = true;
            // this.$notify({
            //   title: "成功",
            //   message: "修改成功",
            //   type: "success",
            // });

            axios.get("http://localhost:8080/types").then((result) => {
              this.typesData = result.data.data;
              this.refreshOnTypeManagementDialog = !this.refreshOnTypeManagementDialog;
              this.refreshOnTypeManagementDialog = !this.refreshOnTypeManagementDialog;
              
            });
            
            this.updateTypeInfoDialogFormVisible = false;
          } else {
            this.$notify.error({
              title: "失败",
              message: "请检查信息输入是否有误后重新提交",
            });
          }
          

        });
    },
    closeUpdateTypesInfoDialog: function(){ 
      this.imageUrl = "";
    },

    handleClose(done) {
      this.$confirm('关闭')
        .then(_ => {
          this.updateTypeInfoDialogFormVisible = false;
          // 立即刷新页面
          
          console.log(_);
          done();
        })
        .catch(_ => {
          location.reload();
          console.log(_);
        });
        
    },
  },
  mounted() {
    //发送异步请求,获取数据
    axios.get("http://localhost:8080/suppliers").then((result) => {
      this.tableData = result.data.data;
    });

    axios.get("http://localhost:8080/suppliers/products?id=" + this.currentSupplierId + "&page=" +
      this.pageSearchAllProducts.start +
      "&pageSize=" +
      this.pageSearchAllProducts.pageSize
    ).then((result) => {
      this.productsData = result.data.data.rows;
      this.totalProducts = result.data.data.total;
    });
    
    axios.get("http://localhost:8080/types").then((result) => {
      this.typesData = result.data.data;
    });

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
/* .el-menu-item {
    background-color: #333  !important;
} */

</style>