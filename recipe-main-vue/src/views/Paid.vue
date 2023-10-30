<template>

  <el-steps
    :active="3"
    finish-status="success"
    simple
    style="margin-top: 20px; margin-bottom: 20px; background: white"
  >
    <el-step title="Step 1" />
    <el-step title="Step 2" />
    <el-step title="Step 3" />
  </el-steps>

  <el-card shadow="always" :body-style="{ padding: '20px' }">
    
  <el-row>
    <el-col :sm="12" :lg="6" :span="5" :offset="9">
      <el-result
        icon="success"
        title="購買成功"
        sub-title="已成功購買，請至購買清單查詢"
        style="
          --el-result-icon-font-size: 150px;
          --el-result-title-font-size: 32px;
          --el-result-subtitle-font-size: 32px;
          --el-font-size-base: 14px;
        "
      >
        <template #extra>
          <el-button type="primary" @click="goToOrderList">購買清單</el-button>
          <el-button type="primary" @click="goToHome">返回首頁</el-button>
        </template>
      </el-result>
    </el-col>
  </el-row>
</el-card>
</template>

<script setup>
import { ref, reactive, computed } from "vue";
import axios from "axios";
import { useStorage } from "@vueuse/core";
import router from "../router";
import { useCookie } from "vue-cookie-next";

const URL = import.meta.env.VITE_API_JAVAURL;
const products = ref([]);

const cookie = useCookie();
const member = ref(cookie.getCookie("cookie"));

// const headers = {'Content-Type': 'application/json',};

const goToHome = () => {
  router.push({ path: "/" });
};

const createOrderList = async () => {
  const jsonProductsStorage = useStorage("productsStorage");
  // console.log(jsonProductsStorage.value);
  const test = ref(JSON.parse(jsonProductsStorage.value));
  // console.log(test.value);

  if (!cookie.isCookieAvailable("cookie")) {
    return;
  }

  let orderBean = {
    memberId: cookie.getCookie("cookie").memberNumber,
    note: jsonProductsStorage.value,
  };

  const URLAPI = `${URL}order/create`;
  let response = await axios.post(URLAPI, orderBean);

  console.log("response" + response);

  router.push({ path: "/order-list" });
};

const goToOrderList = () => {
  router.push({ path: "/order-list" });
};

const removeProducts = async (memberId) => {
  console.log(memberId);

  const URLAPI = `${URL}order/create/${memberId}`;
  let response = await axios.delete(URLAPI);
};
</script>

<style scoped></style>
