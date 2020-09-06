<template>
  <div class="layout-content">
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
      <el-table-column prop="name" fixed="left" sortable label="角色名称" width="180"></el-table-column>
      <el-table-column prop="perms" label="角色标识" width="180"></el-table-column>
      <el-table-column prop="status" label="状态" sortable align="center" width="180">
        <template slot-scope="scope">
          <el-tag :type="scope.row['status'] === '0' ? 'danger' : 'primary'"
                  v-text="formaterStatus(scope.row['status'])"
                  disable-transitions></el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="180"></el-table-column>
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
  </div>
</template>

<script>
import format from "@/utils/format";
import role from "@/api/role";

export default {
  name: "role",
  data(){
    return {
      tableData: []
    }
  },
  methods: {
    initTable(){
      role.list().then(res => {
        console.log(res)
        this.tableData = res['data']
      })
    },
    formatDate(row, column){
      return format.formatDate(row, column)
    },
    formaterStatus(col){
      if(col === '0'){
        return '禁用'
      }
      return '正常'
    },
  },
  created() {
    this.initTable()
  },
}
</script>
