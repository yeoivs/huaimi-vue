<template>
  <div class="layout-admin" :class="isCollapse ? 'layout-collapse' : ''">
    <el-container>
      <el-header class="layout-header">
        <Header/>
      </el-header>
      <el-container class="layout-body">
        <el-aside class="layout-aside">
          <Aside :menus="menuList" :isCollapse="isCollapse" @collapse="collapse"/>
        </el-aside>
        <el-main class="layout-main">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Header from './header'
import Aside from './aside'

import {getMenu} from "@/api/common";
import storage from "@/utils/storage";

export default {
  name: "Layout",
  data(){
    return {
      menuList: [],
      loading: null,
      isCollapse: false
    }
  },
  created() {
    this.initMenu()
    this.isCollapse = storage.get('collapse') === 'on'
  },
  methods: {
    initMenu(){
      this.startLoading()
      getMenu().then(res => {
        if(res['data']){
          this.loading.close()
          this.menuList = res['data']
        }
      })
    },
    startLoading(){
      this.loading = this.$loading({
        lock: true,
        text: '加载中,请勿进行任何操作...',
        background: 'rgba(1,1,1,.5)'
      })
    },
    collapse(){
      this.isCollapse = !this.isCollapse
      storage.set('collapse', this.isCollapse ? 'on' : 'off')
    }
  },
  mounted() {
    this.screenWidth = document.body.clientWidth;
    // this.screenHeight = document.body.clientHeight;
    window.onresize = () => {
      return (() => {
        this.screenWidth = document.body.clientWidth;
        // this.screenHeight = document.body.clientHeight;
        if(this.screenWidth <= 768) this.collapse()
      })();
    }
  },
  components: {
    Header,
    Aside
  }
}
</script>
