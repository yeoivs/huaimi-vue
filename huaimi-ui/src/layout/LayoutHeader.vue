<template>
  <div class="layout-header">
    <el-header>

    </el-header>
  </div>
</template>

<script>
export default {
  methods: {
    collapse(){
      this.$emit('collapse')
    }
  }
}
</script>