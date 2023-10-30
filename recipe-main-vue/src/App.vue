<template>
  <div class="container">
    <div class="common-layout">
      <el-container>
        <el-header class="max-content">
          <HeaderAndSideBar :avatar="avatar"></HeaderAndSideBar>
        </el-header>
        <el-main>
          <router-view @update-member="updateMember"></router-view>
        </el-main>
        <el-footer></el-footer>
      </el-container>
    </div>
  </div>
</template>

<script setup>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.js";
import "bootstrap-icons/font/bootstrap-icons.css";
import { ref } from "vue";

import HeaderAndSideBar from "./components/HeaderAndSideBar.vue";

const avatar = ref(null);

const updateMember = (params) => {
  avatar.value = params;
};
</script>

<style>
.el-container {
  height: 100%;
  margin: 0px;
  padding: 20px;
}

.el-header {
  border-radius: 10px 10px 0px 0px;
}

.el-footer {
  border-radius: 0px 0px 10px 10px;
}

.el-header,
.el-footer {
  /* background-color: #c8c0e9; */
  background-color: #fad689;
  color: #333;
  text-align: center;
  line-height: normal;
  --el-header-padding: 20px !important;
}

.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  line-height: inherit;
  min-height: 720px;
}

.el-menu {
  background-color: #d3dce6;
}

.el-row {
  margin-left: 0px;
  margin-right: 0px;
}

.el-col {
  padding: 5px;
  /* margin-left: 0px !important;
  margin-right: 0px !important;
  display: flex !important; */
  margin-left: 0px;
  margin-right: 0px;
  display: flex;
  flex-direction: column;
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  text-align: center;
}

.content-center {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  height: 200px; /* 如果您希望内容充满 el-tab-pane 的高度 */
}

.max-content {
  height: max-content !important;
}
</style>
