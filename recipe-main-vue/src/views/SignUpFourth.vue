<template>
  <h2>註冊頁面4</h2>

  <div></div>
  <div class="col-3">
    <button class="btn btn-primary" type="button" @click="sigup">
      完成註冊
    </button>
  </div>
</template>

<script setup>
import { inject } from "vue";
import axios from "axios";
import Member from "../models/Member";
import { ref, reactive } from "vue";
import router from "../router";

const $cookies = inject("$cookies");

const member = ref(Member);

const URL = import.meta.env.VITE_API_JAVAURL;
const sigup = async () => {
  console.log(member);
  const URLAPI = `${URL}member/signup`;
  const response = await axios.post(URLAPI, member.value);
  if (response.status === 200) {
    alert("已完成註冊，請於首頁登入");
  } else {
    alert("註冊失敗，請重新嘗試");
  }
  router.push("/");
};
</script>

<style scoped></style>
