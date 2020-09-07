<template>
  <div class="layout-content">
    <div class="layout-search-inline">
      <el-form :inline="true" :model="condition"  class="demo-form-inline">
        <el-form-item label="用户名">
          <el-input v-model="condition.username" placeholder="用户名" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="condition.phone" placeholder="手机号" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="condition.email" placeholder="邮箱" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="condition.lockState" placeholder="状态" size="medium">
            <el-option label="正常" value="1"></el-option>
            <el-option label="禁用" value="0"></el-option>
            <el-option label="注销" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始日期">
          <el-date-picker
              v-model="condition.createTime"
              type="date"
              :picker-options="pickerOptions"
              placeholder="选择开始日期"  size="medium">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期">
          <el-date-picker
              v-model="condition.modifyTime"
              type="date"
              :picker-options="pickerOptions"
              placeholder="选择结束日期"  size="medium">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="medium">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="layout-groups">
      <el-button type="success" icon="el-icon-circle-plus-outline" size="medium">新增</el-button>
      <el-button type="danger" icon="el-icon-delete" size="medium">删除</el-button>
    </div>
    <el-table :data="tableData"
              ref="table"
              row-key="id"
              max-height="510px"
              stripe :highlight-current-row="true"
              style="width: 100%">
      <el-table-column type="selection" fixed="left"></el-table-column>
      <el-table-column prop="username" fixed="left" sortable label="用户名" width="180"></el-table-column>
      <el-table-column prop="email" sortable label="邮箱" width="180"></el-table-column>
      <el-table-column prop="phone" sortable label="手机" width="180"></el-table-column>
      <el-table-column prop="status" label="状态" sortable align="center" width="180">
        <template slot-scope="scope">
          <el-tag :type="typeStatus(scope.row['lockState'])"
                  v-text="formaterStatus(scope.row['lockState'])"
                  disable-transitions></el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime"
                       label="创建时间"
                       :formatter="formatDate"
                       sortable width="180">
      </el-table-column>
      <el-table-column prop="createdBy" label="创建者" width="180"> </el-table-column>
      <el-table-column prop="modifyTime"
                       label="更新时间"
                       :formatter="formatDate"
                       sortable width="180"> </el-table-column>
      <el-table-column prop="modifiedBy" label="更新者" width="180"> </el-table-column>
      <el-table-column fixed="right" label="操作" width="120" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small">编辑</el-button>
          <el-button type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="layout-page">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="nature.page"
          :page-sizes="nature.pages"
          :page-size="nature.size"
          layout="jumper,  prev, pager, next,sizes, total"
          :total="nature.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import user from "@/api/user";
import format from "@/utils/format";

export default {
  name: "user",
  data() {
    return {
      tableData: [],
      condition: {},
      nature: {
        page: 1,
        size: 30,
        total: 0,
        pages: [30, 100, 150, 200, 300, 400]
      },
      pickerOptions:{
        disabledDate(time) {
          return time.getTime() > Date.now();
        }
      }
    }
  },
  methods: {
    initTable(){
      let stringify = JSON.stringify(this.nature);
      user.list(stringify).then(res => {
        console.log(res['data'])
        const data = res['data'];
        this.nature['page'] = data['pageNum']
        this.nature['size'] = data['pageSize']
        this.nature['total'] = data['total']
        this.tableData = data['list']
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    formatDate(row, column){
      return format.formatDate(row, column)
    },
    formaterStatus(col){
      switch (col){
        case '0':
          return '禁用'
        case '1':
          return '正常'
        case '2':
          return '注销'
      }
    },
    typeStatus(val){
      switch (val){
        case '0':
          return 'danger'
        case '1':
          return 'primary'
        case '2':
          return 'info'
      }
    }
  },
  created() {
    this.initTable()
  },
}
</script>
