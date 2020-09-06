<template>
  <div class="login-layout">
    <div class="main">
      <el-form :model="loginForm" :rules="formRules"
               :hide-required-asterisk="true"
               ref="loginForm" size="medium"
               label-width="50px">
        <el-form-item label="账号" prop="username">
          <el-input type="text" v-model="loginForm.username" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="submitForm"
                     :loading="loading"
                     :plain="true"
                     type="primary" size="medium">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {login} from "@/api/login";
import storage from "@/utils/storage";

export default {
  data(){
    return {
      loginForm: {},
      formRules: {
        username: [{required: true, message: '账号不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      loading: false,
    }
  },
  methods: {
    submitForm() {
      this.$refs['loginForm'].validate((valid) => {
        if (valid) {
          this.loading = true
          login(JSON.stringify(this.loginForm)).then(res => {
            const token = res['data']['token']
            if(token){
              storage.setToken(token)
              this.$router.push('/index')
            }
            this.loading = false
          }).catch(error => {
            this.loading = false
            this.$message(error.data['msg'] ? error.data['msg'] : '登录失败')
          })
        }
        return false;
      })
    },
  }
}
</script>
