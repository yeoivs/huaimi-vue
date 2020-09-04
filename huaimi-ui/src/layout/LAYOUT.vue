<template>
  <div class="hm-layout">
    <el-container class="layout-father">
      <LayoutHeader @collapse="collapseSide"></LayoutHeader>
      <el-container class="layout-body">
        <LayoutAside :isCollapse="isCollapse"></LayoutAside>
        <el-main class="layout-main">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import LayoutHeader from "@/layout/LayoutHeader";
import LayoutAside from "@/layout/LayoutAside";
export default {
  data(){
    return {
      isCollapse: false
    }
  },
  methods: {
    collapseSide(){
      this.isCollapse = !this.isCollapse;
    }
  },
  components: {
    LayoutHeader,
    LayoutAside
  }
}
</script>

<style lang="sass">
@import "../assets/styles/common.scss"
</style>
