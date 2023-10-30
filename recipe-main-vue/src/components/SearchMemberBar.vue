<template>
  <el-input
    v-model="search.searchMemberByAccount"
    placeholder="請輸入搜尋條件"
    size="large"
  >
    <template #append>
      <el-button
        :icon="SearchIcon"
        @click="searchMembersByAccout(search.searchMemberByAccount)"
      />
    </template>
  </el-input>
</template>

<script setup>
import {
  Check,
  Delete,
  Edit,
  Message,
  Search as SearchIcon,
  Star,
  ShoppingCartFull,
  Notification,
  House,
} from "@element-plus/icons-vue";
import Status from "../models/Status";
import { inject } from "vue";
import { ref, reactive } from "vue";
import router from "../router";
import axios from "axios";
import SignIn from "../models/SignIn.js";
import Search from "../models/Search";
import MemberSearched from "../models/MemberSearched";
import { ElNotification } from "element-plus";

const memberSearch = ref(MemberSearched);
const $cookies = inject("$cookies");

const status = reactive(Status);
const search = reactive(Search);

const searchMembersByAccout = (account) => {
  if (!$cookies.isKey("cookie")) {
    ElNotification({
      title: `請先登入`,
      message: "部分功能僅提供會員使用",
      type: "warning",
    });
    return;
  }

  if (account == "") {
    ElNotification({
      title: `請輸入搜尋名稱`,
      message: "請輸入搜尋名稱，再點擊搜尋",
      type: "warning",
    });

    // router.push({ path: "/member-search" });
  } else {
    router.push({ path: "/member-search", query: { account: account } });
  }
};
</script>

<style scoped></style>
