<template>
  <el-tabs type="border-card" class="demo-tabs">
    <!-- <el-tab-pane>
        <template #label>
          <span class="custom-tabs-label">
            <el-icon><calendar /></el-icon>
            <span>Route</span>
          </span>
        </template>     
      </el-tab-pane> -->

    <el-tab-pane label="食譜搜尋">
      <template #label>
        <el-icon><calendar /></el-icon>
        <RouterLink to="/Recipe" class="nav-link">食譜</RouterLink>
      </template>
    </el-tab-pane>
    <el-tab-pane label="食材搜尋" class="navbar-content">
      <!-- <div class="navbar-content"> -->
      <Classification></Classification>
      <!-- </div> -->
    </el-tab-pane>
    <el-tab-pane label="料理法">料理法</el-tab-pane>
    <el-tab-pane label="線上課程">線上課程</el-tab-pane>
    <el-tab-pane label="再說">再說</el-tab-pane>
  </el-tabs>

  <!-- <script lang="ts" setup>
    import { Calendar } from "@element-plus/icons-vue";
  </script> -->

  <nav class="navbar navbar-expand bg-body-tertiary card" v-show="false">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Recipe</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <div class="row">
          <ul class="navbar-nav">
            <li class="nav-item">
              <RouterLink to="/" class="nav-link">首頁</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/cookingMethodSearch" class="nav-link"
                >料理法</RouterLink
              >
            </li>
            <!-- Cart先放這 -->
            <li class="nav-item">
              <RouterLink to="/cart" class="nav-link">Cart</RouterLink>
            </li>
            <!-- 周蓉先放這 -->
            <li class="nav-item">
              <RouterLink to="/course" class="nav-link">課程</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/kitchenware" class="nav-link">商城</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/Recipe" class="nav-link">食譜</RouterLink>
            </li>
            <!-- Kaki -->
            <li class="nav-item">
              <RouterLink to="/classification" class="nav-link"
                >食材搜尋</RouterLink
              >
            </li>
          </ul>
        </div>
      </div>

      <div class="row">
        <div class="col d-flex align-items-center">
          <slot name="bar"></slot>
        </div>
        <!-- 下拉會員 -->
        <div class="col" v-if="status.isLoggedIn">
          <div class="btn-group">
            <button type="button" class="btn btn-primary">
              {{ $cookies.get("cookie").account }}
            </button>
            <button
              type="button"
              class="btn btn-primary dropdown-toggle dropdown-toggle-split"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <span class="visually-hidden">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu">
              <li>
                <RouterLink to="/member-center" class="dropdown-item"
                  >會員中心</RouterLink
                >
              </li>

              <li><hr class="dropdown-divider" /></li>
              <li>
                <button class="dropdown-item" href="#" @click="signout">
                  登出
                </button>
              </li>
            </ul>
          </div>
        </div>
        <!-- 登入註冊 -->
        <div class="col" v-if="!status.isLoggedIn">
          <RouterLink to="/sign-up-first" class="btn btn-primary m-1"
            >註冊</RouterLink
          >

          <RouterLink to="/signin" class="btn btn-primary m-1">登入</RouterLink>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { reactive, ref } from "vue";
import router from "../router";
import SearchMemberBar from "./SearchMemberBar.vue";
import Status from "../models/Status";
import NavbarIngredient from "./Navbar-Ingredient.vue";
import Classification from "../views/Classification.vue";
// import ./views/Classification

const status = reactive(Status);
status.isLoggedIn = $cookies.isKey("cookie");
const signout = () => {
  if ($cookies.remove("cookie")) {
    status.isLoggedIn = $cookies.isKey("cookie");
    alert("登出成功，轉向首頁");
    router.push("/");
  } else {
    alert("登出失敗，轉向首頁");
    router.push("/");
  }
};
</script>

<style>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
.demo-tabs .custom-tabs-label .el-icon {
  vertical-align: middle;
}
.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
}

.navbar-content {
  display: flex;
  flex-direction: column;
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  height: 200px; /* 如果您希望内容充满 el-tab-pane 的高度 */
}
</style>
