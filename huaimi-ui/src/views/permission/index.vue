<template>
  <div class="layout-content">
    <div class="layout-groups">
      <el-button @click="dialogVisible=true"
                 type="success"
                 icon="el-icon-circle-plus-outline"
                 size="medium">新增
      </el-button>
      <el-button type="danger" icon="el-icon-delete" size="medium">删除</el-button>
    </div>
    <el-table :data="tableData" row-key="id" ref="table"
              max-height="510px"
              :tree-props="{ children: 'children' }"
              stripe :highlight-current-row="true"
              @select="handleSelect"
              @select-all="handleSelectAll"
              @selection-change="handleSelectionChange"
              style="width: 100%">
      <el-table-column type="selection" fixed="left"></el-table-column>
      <el-table-column prop="name" fixed="left" sortable label="权限名称" width="180"></el-table-column>
      <el-table-column prop="perms" label="权限标识" width="180"></el-table-column>
      <el-table-column prop="type" label="类型" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row['type'] === '2' ? 'warning' : 'success'"
                  v-text="handlerType(scope.row['type'])"
                  disable-transitions></el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="path" label="路径" width="180"></el-table-column>
      <el-table-column prop="icon" label="图标">
        <template slot-scope="scope">
          <i :class="scope.row['icon']"></i>
        </template>
      </el-table-column>
      <el-table-column prop="method" label="方法" width="100" align="center">
        <template slot-scope="scope">
          <el-tag :type="formaterMethod(scope.row['method'])" v-if="scope.row['method'] != null"
                  disable-transitions>{{ scope.row['method'] }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="排序" sortable align="center"></el-table-column>
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
      <el-table-column prop="createdBy" label="创建者" width="180"></el-table-column>
      <el-table-column prop="modifyTime"
                       label="更新时间"
                       :formatter="formatDate"
                       sortable width="180"></el-table-column>
      <el-table-column prop="modifiedBy" label="更新者" width="180"></el-table-column>
      <el-table-column fixed="right" label="操作" width="120" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small">编辑</el-button>
          <el-button type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="dialogTitle"
               :visible.sync="dialogVisible"
               :lock-scroll="false" width="500px" top="20px"
               :append-to-body="false">
      <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="权限类型" prop="type">
          <el-select v-model="form.type" placeholder="类型" size="medium">
            <el-option label="目录" value="0"/>
            <el-option label="菜单" value="1"/>
            <el-option label="按钮" value="2"/>
          </el-select>
        </el-form-item>
        <el-form-item label="权限名称" prop="name">
          <el-input type="text" v-model="form.name" placeholder="权限名称" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="权限标识" prop="perms">
          <el-input type="text" v-model="form.perms" placeholder="权限标识" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="访问路径" prop="path">
          <el-input type="text" v-model="form.path" placeholder="访问路径" size="medium"></el-input>
        </el-form-item>
        <el-form-item label="图标" prop="icon">

        </el-form-item>
        <el-form-item label="访问方式" prop="method">
          <el-select v-model="form.method" placeholder="访问方式" size="medium">
            <el-option label="POST" value="POST"/>
            <el-option label="GET" value="GET"/>
            <el-option label="PUT" value="PUT"/>
            <el-option label="DELETE" value="DELETE"/>
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" :min="0" label="排序" size="medium"></el-input-number>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark"
                    type="textarea" :rows="3"
                    placeholder="备注">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="medium">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import permission from "@/api/permission";
import format from "@/utils/format";

export default {
  data() {
    return {
      tableData: [],
      dialogTitle: '新增权限',
      dialogVisible: false,
      form: {sort: 0},
      rules: {}
    }
  },
  created() {
    this.initTable()
  },
  methods: {
    initTable() {
      permission.list().then(res => {
        this.tableData = format.toTreeData(res['data'])
      })
    },
    formatDate(row, column) {
      return format.formatDate(row, column)
    },
    handlerType(col) {
      switch (col) {
        case '0':
          return '目录'
        case '1':
          return '菜单'
        default:
          return '按钮'
      }
    },
    formaterStatus(col) {
      if (col === '0') {
        return '禁用'
      }
      return '正常'
    },
    formaterMethod(col) {
      let a = col.toUpperCase()
      switch (a) {
        case 'POST':
          return 'success'
        case 'DELETE':
          return 'danger'
        case 'PUT':
          return 'warning'
        case 'GET':
          return 'primary'
      }
    },
    handleSelect(selection, row) {
      console.log('select => ', selection, row)
    },
    handleSelectAll(selection) {
      console.log('select all => ', selection)
    },
    handleSelectionChange(selection) {
      console.log('select change => ', selection)
    }
  }
}
</script>
