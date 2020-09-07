<template>
  <div class="layout-content">
    <div class="layout-search-inline">
      <el-form :model="search" ref="searchForm" :inline="true">
        <el-form-item label="标识">
          <el-input type="text" v-model="search.perms" placeholder="权限标识" size="small"/>
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="search.type" placeholder="权限类型" clearable size="small">
            <el-option :label="item.value" :value="item.key"
                       v-for="(item, index) in api.missType" :key="index" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="search.status" placeholder="权限状态" clearable size="small">
            <el-option :label="item.value" :value="item.key"
                       v-for="(item, index) in api.status" :key="index" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-search" @click="searchData" size="small">查询</el-button>
          <el-button icon="el-icon-refresh-left" @click="search = {}" size="small">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="layout-groups">
      <el-button type="primary" @click="dialogBox"
                 icon="el-icon-circle-plus-outline"
                 size="small">添加权限</el-button>
    </div>
    <el-table :data="tableData" row-key="id" ref="table"
              max-height="510px"
              :tree-props="{ children: 'children' }"
              stripe :highlight-current-row="true"
              style="width: 100%">
      <el-table-column prop="name" fixed sortable label="权限名称" width="180"></el-table-column>
      <el-table-column prop="perms" label="权限标识" width="180"></el-table-column>
      <el-table-column prop="type" label="类型" align="center">
        <template slot-scope="scope">
          <el-tag :type="item.type"
                  v-if="scope.row['type'] === item.key"
                  v-for="item in api.missType" :key="scope.row['id'] + item.key"
                  disable-transitions size="small">{{item.value}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="icon" label="图标">
        <template slot-scope="scope">
          <i :class="scope.row['icon']"></i>
        </template>
      </el-table-column>
      <el-table-column prop="path" label="路径" width="180"></el-table-column>
      <el-table-column prop="method" label="方法" width="100" align="center">
        <template slot-scope="scope">
          <el-tag :type="item.type"
                  v-if="scope.row['method'] === item.key"
                  v-for="item in api.method" :key="scope.row['id'] + item.key"
                  disable-transitions size="small">{{ item.value }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="排序" sortable align="center"></el-table-column>
      <el-table-column prop="status" label="状态" sortable align="center" width="180">
        <template slot-scope="scope">
          <el-tag :type="item.type"
                  v-if="item.key === scope.row['status']"
                  v-for="item in api.status" :key="scope.row['id'] + item.key"
                  disable-transitions size="small">{{item.value}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="180"></el-table-column>
      <el-table-column prop="createTime"
                       label="创建时间"
                       :formatter="formatDate"
                       sortable width="180">
      </el-table-column>
      <el-table-column prop="createdBy" label="创建者" width="140"></el-table-column>
      <el-table-column prop="modifyTime"
                       label="更新时间"
                       :formatter="formatDate"
                       sortable width="180"></el-table-column>
      <el-table-column prop="modifiedBy" label="更新者" width="140"></el-table-column>
      <el-table-column fixed="right" label="操作" width="120" align="center">
        <template slot-scope="scope">
          <el-button type="text" @click="editData(scope.row)" size="small">编辑</el-button>
          <el-button type="text" @click="delData(scope.row)" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="dialogTitle"
               :visible.sync="dialogVisible"
               :lock-scroll="false" width="500px" top="20px"
               :append-to-body="false" :close-on-click-modal="false">
      <el-form :model="form" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="权限类型" prop="type">
          <el-select v-model="form.type" placeholder="类型" size="medium">
            <el-option :label="item.value" :value="item.key" v-for="item in api.missType" :key="item.key"/>
          </el-select>
        </el-form-item>
        <el-form-item label="父级菜单" prop="parentId">
          <el-cascader :options="parentOptions" v-model="form.parentId"
                       :show-all-levels="false" clearable
                       :props="{checkStrictly: true, emitPath: false, value: 'id', label: 'name'}"
                       placeholder="父级菜单" size="medium" :append-to-body="false">
          </el-cascader>
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
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" :min="0" label="排序" size="medium"></el-input-number>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <template>
            <el-popover placement="bottom-start"
                trigger="hover" :append-to-body="false">
              <div class="icon-box">
                <a v-for="item in api.icons" @click="selectIcon(item)"> <i :class="item"></i> </a>
              </div>
              <el-button slot="reference" class="icon-select" size="small">
                <div v-if="form.icon">
                  <i :class="form.icon"></i>
                  <a class="el-icon-close" @click="clearIcon"></a>
                </div>
                <div v-else>选择图标</div>
              </el-button>
            </el-popover>
          </template>
        </el-form-item>
        <el-form-item label="请求方式" prop="method">
          <el-select v-model="form.method" placeholder="请求方式" size="medium">
            <el-option :label="item.value" :value="item.key" v-for="item in api.method" :key="item.key"/>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-switch v-model="form.status"
                     active-value="1" inactive-value="0">
          </el-switch>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark"
                    type="textarea" :rows="3"
                    maxlength="50" show-word-limit
                    placeholder="备注">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveData" size="medium">保存</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import permission from "@/api/permission";
import format from "@/utils/format";
import api from '@/api/data/common.json'

export default {
  data() {
    return {
      api: api,
      tableData: [],
      dialogTitle: '',
      dialogVisible: false,
      parentOptions: [],
      form: {},
      rules: {},
      search: {},
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
    dialogBox(){
      this.form = {type: '0', sort: 0, parentId: 0, status: '1'}
      this.pushData()
      this.dialogTitle = '添加权限'
      this.dialogVisible = true
    },
    editData(row){
      this.pushData()
      this.form = row
      this.dialogTitle = '编辑权限'
      this.dialogVisible = true
    },
    pushData(){
      this.parentOptions = []
      permission.list().then(res => {
        format.toTreeData(res['data']).forEach(value => {
          this.parentOptions.push(value)
        })
      })
    },
    saveData(){
      this.$refs['ruleForm'].validate(valid => {
        if(valid){
          this.dialogVisible = false
          let id = this.form['id'];
          if(id && id > 0){
            permission.edit(JSON.stringify(this.form)).then( res => {
              this.$notify.success('编辑权限信息成功')
            }).catch(error => {
              this.$notify.error('编辑失败')
            })
          }else{
            permission.add(JSON.stringify(this.form)).then(async res => {
              await this.$notify.success('添加成功，重新加载中...')
              this.initTable()
            }).catch(error => {
              this.$notify.error('添加失败')
            })
          }
        }
        return false
      })
    },
    delData(row){
      this.$confirm('正在删除数据，请确认。。。', '系统提示').then(r => {
        permission.del(row['id']).then(async res => {
          await this.$notify.success('删除成功，重新加载中...')
          this.initTable()
        }).catch(error => {
          this.$notify.error('删除失败')
        })
      })
    },
    selectIcon(icon){
      this.form.icon = icon
      this.$forceUpdate()
    },
    clearIcon(){
      this.form.icon = ''
      this.$forceUpdate()
    },
    searchData(){
      const search = this.search
      permission.search(JSON.stringify(search)).then(res => {
        this.tableData = format.toTreeData(res['data'])
      })
    }
  }
}
</script>
