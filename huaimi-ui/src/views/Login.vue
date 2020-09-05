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
          <el-button @click="submitForm" type="primary" :plain="true" size="medium">登录</el-button>
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
      }
    }
  },
  methods: {
    submitForm() {
      this.$refs['loginForm'].validate((valid) => {
        console.log(valid)
        if (valid) {
          login(JSON.stringify(this.loginForm)).then(res => {
            const token = res['data']['token']
            if(token){
              storage.setToken(token)
              this.$router.push('/')
            }
          }).catch(error => {
            this.$alert('登录失败')
          })
        }
        return false;
      })
    },
  }
}
</script>
