<template>
  <div class="layout-top">
    <div class="head-left">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item v-for="(item, index) in $route.meta.nav" :key="index">
          <router-link v-if="item.url" :to="item.url">{{item.name}}</router-link>
          <a v-else>{{item.name}}</a>
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-menu class="nav-right" mode="horizontal">
      <el-menu-item>
        <el-badge :is-dot="false" class="circle-item">
          <i class="el-icon-bell"></i>
        </el-badge>
      </el-menu-item>
      <el-submenu :popper-append-to-body="false" index="1">
        <template slot="title"><el-avatar :size="30" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></template>
        <el-menu-item><router-link :to="{path: '/profile'}">个人资料</router-link></el-menu-item>
        <el-menu-item><router-link :to="{path: '/passwd'}">修改密码</router-link></el-menu-item>
        <el-menu-item><router-link :to="{path: '/'}">设置主题</router-link></el-menu-item>
        <el-menu-item @click="exit">退出系统</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
import {logout} from "@/api/login";
import storage from "@/utils/storage";

export default {
  props: {

  },
  methods: {
    exit(){
      this.$confirm('正在注销登录信息，请确认...', '系统提示').then(r => {
        logout().then(async res => {
          await this.$message.success('退出成功, 前往登录页面中')
          setTimeout(() => {
            storage.clear()
            location.reload()
          }, 1500)
        })
      })
    }
  }
}
</script>
