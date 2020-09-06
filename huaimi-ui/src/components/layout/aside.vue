<template>
  <div class="aside-nav">
    <div class="layout-logo">
      <a @click="www">
        <i class="el-icon-orange" style="display: none"></i><span>HUAIMI</span>
      </a>
    </div>
    <div class="layout-scroll">
      <div class="layout-side">
        <el-menu @select="handleSelect"
                 :default-active="currentPath"
                 :unique-opened="true"
                 :router="true" :collapse="isCollapse"
                 :collapse-transition="false">
          <template v-for="item in menus">
            <el-submenu v-if="item.children != null && item.children.length !== 0" :index="'' + item['id']">
              <template slot="title">
                <i :class="item['icon']"></i><span>{{item['name']}}</span>
              </template>
              <el-menu-item :index="'/' + children['path']" :key="children['id']"
                            v-for="children in item['children']">
                <i :class="children['icon']"></i><span>{{children['name']}}</span>
              </el-menu-item>
            </el-submenu>
            <el-menu-item v-else :index="'/' + item['path']" :key="item['id']">
              <i :class="item['icon']"></i><span>{{item['name']}}</span>
            </el-menu-item>
          </template>
        </el-menu>
      </div>
    </div>
  </div>
</template>

<script>
import storage from "@/utils/storage";

export default {
  props: {
    menus: {
      type: Array,
      default: []
    },
    isCollapse: {
      type: Boolean,
      default: false
    }
  },
  data(){
    return {
      currentPath: '',
    }
  },
  created() {
    const activeMenu = storage.get('activeMenu')
    this.currentPath =  activeMenu ? activeMenu : this.$route.path
  },
  methods: {
    handleSelect(key, path){
      this.currentPath =  key
    },
    www(){
      this.$emit('collapse');
    },
  }
}
</script>
