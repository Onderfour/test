<template>
  <!---搜索表单-->
  <div class="search-div">
    <el-form label-width="70px" size="small">
      <el-row>
        <el-col :span="12">
          <el-form-item label="关键字">
            <el-input
              v-model="queryDto.keyword" 
              style="width: 100%"
              placeholder="订单号、姓名、手机号码"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间">
            <el-date-picker
              v-model="createTimes"
              type="daterange"
              range-separator="To"
              start-placeholder="开始时间"
              end-placeholder="结束时间"
              format="YYYY-MM-DD"
              value-format="YYYY-MM-DD"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="display: flex">
        <el-button type="primary" size="small" @click="searchSysUser">
          搜索
        </el-button>
        <el-button size="small" @click="resetData">重置</el-button>
      </el-row>
    </el-form>
  </div>

  <div class="tools-div">
    <el-button type="success" size="small" @click="addShow">添 加</el-button>
  </div>

  <el-dialog
    v-model="dialogVisible"
    :title="isEdit ? '修改' : '详情'"
    width="1200px"
  >
    <el-form label-width="200px" class="form-box">
      <div class="form-left">
        <el-form-item label="订单号">
          <span>{{ brand.orderNo }}</span>
        </el-form-item>
        <el-form-item label="订单总额">
          <span>{{ brand.totalAmount }}</span>
        </el-form-item>
        <el-form-item label="支付方式">
          <!-- <el-input :value="  brand.payType == 1 ? '微信' : '支付宝'"/> -->
          <span>{{ brand.payType == 1 ? '微信' : '支付宝' }}</span>
        </el-form-item>
        <el-form-item label="订单状态">
          <el-select
            v-model="brand.orderStatus"
            class="m-2"
            placeholder="请选择支付方式"
            size="large"
            style="width: 100%"
          >
            <el-option label="待付款" :value="0" />
            <el-option label="待发货" :value="1" />
            <el-option label="已发货" :value="2" />
            <el-option label="待用户收货，已完成" :value="3" />
          </el-select>
        </el-form-item>
        <el-form-item label="收货人姓名">
          <el-input v-model="brand.receiverName" />
        </el-form-item>
        <el-form-item label="收货人电话">
          <el-input v-model="brand.receiverPhone" />
        </el-form-item>
        <el-form-item label="详细地址">
          <el-input v-model="brand.receiverAddress" />
        </el-form-item>
      </div>
      <div class="form-right">
        <el-form-item label="品牌">
          <el-input v-model="brand.orderItemList[0].skuName" />
        </el-form-item>
        <el-form-item label="数量">
          <el-input v-model="brand.orderItemList[0].skuNum" />
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="brand.orderItemList[0].skuPrice" />
        </el-form-item>
        <el-form-item label="总价格">
          <el-input v-model="brand.totalAmount" />
        </el-form-item>
        <el-form-item label="图片">
          <img
            v-if="brand.orderItemList[0].thumbImg"
            :src="brand.orderItemList[0].thumbImg"
            class="avatasubmitr"
          />
        </el-form-item>
      </div>
    </el-form>
    <div class="handle" v-if="isEdit">
      <el-button type="primary" @click="saveOrUpdate">提交</el-button>
      <el-button @click="dialogVisible = false">取消</el-button>
    </div>
  </el-dialog>

  <el-table :data="list" style="width: 100%">
    <el-table-column prop="orderNo" label="订单号" />
    <el-table-column prop="totalAmount" label="订单总额" />
    <el-table-column prop="payType" label="支付方式" #default="scope">
      {{ scope.row.payType == 1 ? '微信' : '支付宝' }}
    </el-table-column>

    <el-table-column prop="orderStatus" label="订单状态" #default="scope">
      {{ mapName(scope.row.orderStatus) }}
    </el-table-column>
    <el-table-column prop="receiverName" label="收货人姓名" />
    <el-table-column prop="receiverPhone" label="收货人电话" />
    <el-table-column prop="receiverAddress" label="详细地址" />
    <el-table-column prop="createTime" label="创建时间" />

    <el-table-column label="操作" align="center" width="200" #default="scope">
      <el-button type="primary" size="small" @click="detailsShow(scope.row)">
        详情
      </el-button>
      <el-button type="danger" size="small" @click="update(scope.row)">
        修改
      </el-button>
    </el-table-column>
  </el-table>

  <el-pagination
    v-model:current-page="pageParams.page"
    v-model:page-size="pageParams.limit"
    :page-sizes="[10, 20, 50, 100]"
    layout="total, sizes, prev, pager, next"
    :total="total"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import {
  GetOrderPageList,
  FindAllOrder,
  FindAllOrderdetails,
  updateOrderInfo,
} from '@/api/orderInfo.js'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useApp } from '@/pinia/modules/app'


const mapName = status => {
  return status == 0
    ? '待付款'
    : status == 1
    ? '待发货'
    : status == 2
    ? '已发货'
    : status == 3
    ? '待用户收货，已完成'
    : '已取消'
}

///////////////////add
const headers = {
  // 从pinia中获取token，在进行文件上传的时候将token设置到请求头中
  token: useApp().authorization.token,
}
// 定义提交表单数据模型
const defaultForm = {
  id: '',
  orderNo: '',
  totalAmount: '',
  payType: '',
  orderStatus: '',
  receiverName: '',
  receiverPhone: '',
  receiverAddress: '',
  orderItemList: '',
}
const brand = ref(defaultForm)
const dialogVisible = ref(false)

// 详情
const detailsShow = async (payload = {}) => {
  isEdit.value = false
  console.log(payload)
  console.log(brand.value)
  dialogVisible.value = true

  const res = await FindAllOrderdetails(payload.orderNo, payload.id)
  console.log(res.data)
  for (const key in brand.value) {
    brand.value[key] = res.data[key]
  }
  ElMessage.success('操作成功')
  fetchData()
}

// 显示添加品牌表单
const addShow = () => {
  brand.value = {}
  dialogVisible.value = true
}

//上传
const handleAvatarSuccess = response => {
  brand.value.logo = response.data
}

// // 保存数据
const saveOrUpdate = async () => {

  await updateOrderInfo(brand.value)
  ElMessage.success('操作成功')
  dialogVisible.value = false
  fetchData()
}

// // 修改
const isEdit = ref(false)
const update = async (payload={}) => {
  isEdit.value = true
  const res = await FindAllOrderdetails(payload.orderNo, payload.id)
  for (const key in brand.value) {
    brand.value[key] = res.data[key]
  }
  dialogVisible.value = true
  ElMessage.success('操作成功')
  // fetchData()
}




// 定义表格数据模型
const list = ref([])

// 分页条数据模型
const total = ref(0)

//分页条数据模型
const pageParamsForm = {
  page: 1, // 页码
  limit: 10, // 每页记录数
}
const pageParams = ref(pageParamsForm)
const createTimes = ref([])
// 钩子函数
onMounted(() => {
  fetchData()
})
const queryDto = ref({
  orderNo: '',
  keyword: '',
  receiverName: '',
  receiverPhone: '',
  createTimeBegin: '',
  createTimeEnd: '',
})
const searchSysUser = () => {
  fetchData()
}
const resetData = () => {
  queryDto.value = {}
  createTimes.value = []
  searchSysUser()
}
//页面变化
const handleSizeChange = size => {
  pageParams.value.limit = size
  fetchData()
}
const handleCurrentChange = number => {
  pageParams.value.page = number
  fetchData()
}

// 分页查询
const fetchData = async () => {
  if (createTimes.value.length == 2) {
    queryDto.value.createTimeBegin = createTimes.value[0]
    queryDto.value.createTimeEnd = createTimes.value[1]
  }
  const { code, message, data } = await GetOrderPageList(
    pageParams.value.page,
    pageParams.value.limit,
    queryDto.value
  )
  list.value = data.list
  total.value = data.total
}
</script>

<style scoped>
.tools-div {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}
.form-box {
  display: flex;
}
.form-left {
  width: 50%;
}
.form-right {
  width: 50%;
}
.handle {
  margin: 20px;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.avatasubmitr {
  display: inline-block;
  max-width: 300px;
}
</style>