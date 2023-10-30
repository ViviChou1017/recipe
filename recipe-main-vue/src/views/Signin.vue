<template>
  <h2>Signin</h2>
  <div class="row">
    <div class="col-6">
      <input
        type="text"
        v-model="input.account"
        @input="checkAccountExist"
        placeholder="請輸入帳號"
        class="form-control mb-3"
      />
    </div>
    <div class="col-6"></div>
  </div>
  <div class="row">
    <div class="col-6">
      <input
        type="text"
        v-model="input.memberPassword"
        placeholder="請輸入密碼"
        class="form-control mb-3"
      />
    </div>
  </div>
  <div class="row" v-show="loginIsFailed">帳號或密碼錯誤</div>

  <div class="row">
    <div class="col-6">
      <button class="btn btn-primary" type="button" @click="login">登入</button>
    </div>
  </div>
</template>

<script setup>
import { inject } from "vue";
import { ref, reactive } from "vue";
import router from "../router";
import axios from "axios";
import SignIn from "../models/SignIn.js";
import Status from "../models/Status";

const status = reactive(Status);
const $cookies = inject("$cookies");
const signIn = ref(SignIn);
status.isLoggedIn = $cookies.isKey("cookie");

const loginIsFailed = ref(false); //帳號存在提示

const URL = import.meta.env.VITE_API_JAVAURL;

const input = reactive({
  account: "",
  memberPassword: "",
});

const login = async () => {
  const API_URL = `${import.meta.env.VITE_API_JAVAURL}members/login`;
  const response = await axios.post(API_URL, input);
  if (response.status === 200) {
    loginIsFailed.value = false;
    $cookies.set("cookie", response.data);
    status.isLoggedIn = true;
    alert("登入成功，導向首頁");
    console.log($cookies.get("cookie", response.data));

    router.push("/");
  } else {
    loginIsFailed.value = true;
  }
};
</script>

<style scoped></style>
