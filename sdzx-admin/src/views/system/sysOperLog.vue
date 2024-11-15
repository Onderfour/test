<template>
    <div class="search-div">
        <!-- 搜索表单 -->
        <el-form label-width="70px" size="small">
            <el-row>
        <el-col :span="8">
          <el-form-item label="操作模块">
            <el-input
              v-model="queryDto.title"
              style="width: 100%"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="7">
          <el-form-item label="操作人">
            <el-input
              v-model="queryDto.operName"
              style="width: 100%"
              placeholder="用户名"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="8">
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

    <el-row style="display:flex">
        <el-button type="primary" size="small" @click="searchSysRole">
            搜索
        </el-button>
        <el-button size="small" @click="resetData">重置</el-button>
    </el-row>
</el-form>

 
<!-- 页面表单 -->
<el-dialog v-model="dialogVisible" title="日志详情" width="30%">
    <el-form label-width="120px">
        <el-form-item label="系统模块">
          {{sysRole.title}}
            <!-- <el-input v-model="sysRole.title"/> -->
        </el-form-item>
        <el-form-item label="操作人">
          {{sysRole.operName}}
            <!-- <el-input  v-model="sysRole.operName"/> -->
        </el-form-item>
    <el-form-item label="请求url">
      {{sysRole.operUrl}}
            <!-- <el-input  v-model="sysRole.operUrl"/> -->
        </el-form-item>
        <el-form-item label="操作ip">
          {{sysRole.operIp}}
            <!-- <el-input  v-model="sysRole.operIp"/> -->
        </el-form-item>
        <el-form-item label="返回参数">
          {{sysRole.operParam}}
            <!-- <el-input  v-model="sysRole.operParam"/> -->
        </el-form-item>
        <el-form-item label="操作信息">
          {{sysRole.jsonResult}}
            <!-- <el-input  v-model="sysRole.jsonResult"/> -->
        </el-form-item>
        
       
        <el-form-item label="错误日志">
          {{sysRole.errorMsg}}
            <!-- <el-input  v-model="sysRole.errorMsg"/> -->
        </el-form-item>
       
    </el-form>
</el-dialog>
        
        <!--- 角色表格数据 -->
        <el-table :data="list" style="width: 100% height: 451px;" >
            <el-table-column prop="title" label="系统模块" width="180"  />
            <el-table-column prop="requestMethod" label="请求方法" width="180" />
            <el-table-column prop="operIp" label="操作ip" width="180" />
            <el-table-column prop="status" label="操作状态" width="180" />
            <el-table-column prop="operName" label="操作人" width="180" />
            <el-table-column prop="createTime" label="创建时间" width="180" />

            <el-table-column label="操作" align="center" width="280" #default="scope">
            <el-button type="primary" size="small" @click="editShow(scope.row)">详情</el-button>
            </el-table-column>
        </el-table>

        <!-- 分配菜单的对话框 
// tree组件添加ref属性，后期方便进行tree组件对象的获取
-->
<el-dialog v-model="dialogMenuVisible" title="分配菜单" width="40%">
    <el-form label-width="80px">
        <el-tree
                 :data="sysMenuTreeList"
                 ref="tree"   
                 show-checkbox
                 default-expand-all
                 :check-on-click-node="true"
                 node-key="id"
                 :props="defaultProps"
        />
        <el-form-item>
            <el-button type="primary" @click="doAssign">提交</el-button>
            <el-button @click="dialogMenuVisible = false">取消</el-button>
        </el-form-item>
    </el-form>
</el-dialog>
        <!-- //分页条 -->
        <el-pagination
               v-model:current-page="pageParams.page"
               v-model:page-size="pageParams.limit"
               :page-sizes="[10, 20, 50, 100]"
               @size-change="fetchData"
               @current-change="fetchData"
               layout="total, sizes, prev, pager, next"
               :total="total"
/>
  </div>

</template>

<script setup>
import { ref , onMounted } from 'vue';
import {GetAllLogList,GetSysLogListByPage} from '@/api/sysRole';
// import { ElMessage, ElMessageBox } from 'element-plus'


        
  
    


// 树对象变量


// 默认选中的菜单数据集合



// 删除数据

// 控制对话是否展示的变量
const dialogVisible = ref(false)

//进入添加
const addShow = () => {
    sysRole.value={}
  	dialogVisible.value = true
}
// 修改按钮点击事件处理函数
const editShow = (row) => {
    sysRole.value = row
    dialogVisible.value = true
 }

// 分页条总记录数
let total = ref(0)

// 定义表格数据模型
let list = ref([])

//分页数据
const pageParamsForm = {
  page: 1, // 页码
  limit: 10, // 每页记录数
}
const pageParams = ref(pageParamsForm)     // 将pageParamsForm包装成支持响应式的对象

// 搜索表单数据
const queryDto = ref({"operName": ""})

// 页面加载完毕以后请求后端接口获取数据
onMounted(() => {
    fetchData() ;
})

// 搜索按钮点击事件处理函数
const searchSysRole = () => {
    //queryDto.value.roleName = ""
    fetchData() ;
}
// 重置数据
const resetData = () => {
  queryDto.value.operName = '';
  searchSysRole();
}

// 远程调用后端分页查询接口
const fetchData = async () => {
    const {data , code , message } = await GetSysLogListByPage(pageParams.value.page , pageParams.value.limit , queryDto.value) ;
    list.value = data.list ;
    total.value = data.total
}

//表单数据模型
const defaultForm = {
    id: "",
    title: "",
    requestMethod:"",
    operName: "",
    operUrl:"",
    operIp:"",
    operParam:"",
    jsonResult:"",
    status:"",
    errorMsg:""
}
const sysRole = ref(defaultForm)   // 使用ref包裹该对象，使用reactive不方便进行重置
 
// 添加角色
// const submit = async () => {
//     const { code } = await SaveSysRole(sysRole.value) ;
//     if(code === 200) {
//         dialogVisible.value = false
//         ElMessage.success('操作成功')
//         fetchData()
//     }
// }
// 添加角色


</script>

<style scoped>

.search-div {
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}

.tools-div {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ebeef5;
  border-radius: 3px;
  background-color: #fff;
}

</style>