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
              placeholder="用户名、姓名、手机号码"
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
  <!-- 添加按钮 -->
  <div class="tools-div">
      <el-button type="success" size="small" @click="addShow">添 加</el-button>
  </div>

  <!-- <el-dialog v-model="dialogVisible" title="添加或修改" width="30%">
  <el-form label-width="120px">
      <el-form-item label="用户名">
          <el-input v-model="userInfo.username"/>
      </el-form-item>
      <el-form-item label="头像">
          <el-upload
                     class="avatar-uploader"
                     action="http://localhost:8501/admin/system/fileUpload"
                     :show-file-list="false"
                     :on-success="handleAvatarSuccess"
                     :headers="headers"
                     >
              <img v-if="userInfo.avatar" :src="userInfo.avatar" class="avatar" />
              <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
      </el-form-item>
      <el-form-item>
          <el-button type="primary" @click="saveOrUpdate">提交</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
      </el-form-item>
  </el-form>
</el-dialog> -->
<el-dialog v-model="dialogVisible" title="添加或修改" width="40%">
    <el-form label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="userInfo.username" />
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="userInfo.nickName" />
      </el-form-item>
      <el-form-item label="头像">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8501/admin/system/fileUpload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :headers="headers"
        >
          <img v-if="userInfo.avatar" :src="userInfo.avatar" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="userInfo.sex" />
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="userInfo.phone" />
      </el-form-item>  
      <el-form-item label="状态">
        <el-input v-model="userInfo.status" />
      </el-form-item>
    
      <el-form-item>
        <el-button type="primary" @click="submit">提交</el-button>
        <el-button @click="dialogVisible = false">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>

  <el-table :data="list" style="width: 100%">
    <el-table-column prop="username" label="用户名" />
    <el-table-column prop="nickName" label="昵称" />
      <el-table-column prop="avatar" label="头像" #default="scope">
          <img :src="scope.row.avatar" width="50" />
      </el-table-column>
      <el-table-column prop="sex" label="性别" #default="scope">
        {{ scope.row.sex == 1 ? '女' : '男' }}
      </el-table-column>
      <el-table-column prop="phone" label="电话号码" />
      <el-table-column prop="updateTime" label="最后一次登录时间" />
      <el-table-column prop="status" label="状态" #default="scope">
      {{ scope.row.status == 1 ? '正常' : '停用' }}
    </el-table-column>
      <el-table-column prop="createTime" label="创建时间" />

      <el-table-column label="操作" align="center" width="200" #default="scope">
         
          <el-button type="primary" size="small" @click="editShow(scope.row)">
      修改
  </el-button>
  <el-button type="danger" size="small" @click="remove(scope.row.id)">
      删除
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

import { ref , onMounted } from 'vue'
import { GetUserInfoPageList ,SaveUserInfo,UpdateUserInfoById,DeleteUserInfoById,FindAllUserInfo} from '@/api/userInfo.js'
import { ElMessage, ElMessageBox } from 'element-plus'
import { useApp } from '@/pinia/modules/app'

const queryDto = ref({
  keyword: '',
  createTimeBegin: '',
  createTimeEnd: '',
})
// 搜素按钮点击事件处理函数
const searchSysUser = () => {
  fetchData()
}
// 重置按钮点击事件处理函数
const resetData = () => {
  queryDto.value = {}
  createTimes.value = []
  searchSysUser();
}
const createTimes = ref([])
//删除
const remove = async id => {
ElMessageBox.confirm('此操作将永久删除该记录, 是否继续?', 'Warning', {
  confirmButtonText: '确定',
  cancelButtonText: '取消',
  type: 'warning',
})
  .then(async () => {
    await DeleteUserInfoById(id)
    ElMessage.success('删除成功')
    fetchData()
  })
}
//进入修改
const editShow = row => {

dialogVisible.value = true
// userInfo.value = row
  userInfo.value = {...row}

}
///////////////////add
const headers = {
// 从pinia中获取token，在进行文件上传的时候将token设置到请求头中
token: useApp().authorization.token     
}

// 定义提交表单数据模型
const defaultForm = {
  id: '',
  username: '',
  nickName: "",
  avatar: '',
  sex:"",
  phone:"",

}
const userInfo = ref(defaultForm)


const dialogVisible = ref(false) 

// 显示添加品牌表单
const addShow = () => {
  userInfo.value = {}
  dialogVisible.value = true 
}

//上传
const handleAvatarSuccess = (response) => {
  userInfo.value.avatar = response.data
}

const submit = async () => {
    if(!userInfo.value.id) {
        const {code , message , data} = await SaveUserInfo(userInfo.value) 
        if(code === 200) {
            dialogVisible.value = false
            ElMessage.success('操作成功')
            fetchData()
        }else {
            ElMessage.error(message)
        }
    }else {
        const {code , message , data} = await UpdateUserInfoById(userInfo.value) 
        if(code === 200) {
            dialogVisible.value = false
            ElMessage.success('操作成功')
            fetchData()
        }else {
            ElMessage.error(message)
        }   
    }    
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

// 钩子函数
onMounted(()=> {
  fetchData()
})

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
 const {code , message , data} = await GetUserInfoPageList(pageParams.value.page , pageParams.value.limit, queryDto.value) 
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
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }


</style>