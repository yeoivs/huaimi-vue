<template>
  <div class="layout-aside">
    <el-aside>
      <div class="layout-logo">
        <a href="/"><span>HUAIMI</span></a>
      </div>
      <div class="layout-scroll">
          <div class="layout-navs">
            <el-menu :collapse="isCollapse" :unique-opened="true" :router="true" >
              <el-menu-item>
                <i class="el-icon-menu"></i><span slot="title">导航已</span>
              </el-menu-item>
              <el-submenu>
                <template slot="title">
                  <i class="el-icon-menu"></i><span slot="title">导航二</span>
                </template>
                <el-menu-item>
                  <span>11111111111111</span>
                </el-menu-item>
              </el-submenu>
            </el-menu>
          </div>
      </div>
    </el-aside>
  </div>
</template>

<script>
export default {
  props: {
    isCollapse: {
      type: Boolean,
      default: false
    }
  }
}
</script>
