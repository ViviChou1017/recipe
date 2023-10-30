<template lang="">
  <el-tabs
    v-model="activeName"
    type="border-card"
    class="demo-tabs"
    @tab-change="handleChange"
  >
    <el-tab-pane label="食譜搜尋" class="navbar-content" name="recipe">
      <template #label>
        <el-icon><calendar /></el-icon>
        食譜搜尋
        <!-- <RouterLink to="/Recipe" class="nav-link">測試食譜</RouterLink> -->
      </template>
      <slot name="recipe"></slot>
    </el-tab-pane>
    <el-tab-pane label="食材搜尋" class="navbar-content" name="classification">
      <template #label>
        <el-icon><calendar /></el-icon>
        食材搜尋
        <!-- <RouterLink to="/classification" class="nav-link">測試食材</RouterLink> -->
      </template>
      <slot name="classification"></slot>
    </el-tab-pane>
    <el-tab-pane label="料理法" class="navbar-content" name="cookingMethod"
      ><template #label>
        <el-icon><calendar /></el-icon>
        料理法
        <!-- <RouterLink to="/classification" class="nav-link">測試食材</RouterLink> -->
      </template>
      <slot name="cookingMethod"></slot
    ></el-tab-pane>
    <el-tab-pane label="線上課程" class="navbar-content" name="course">
      <slot name="course"></slot>
    </el-tab-pane>
    <el-tab-pane
      label="廚具搜尋"
      class="navbar-content"
      name="kitchenware"
    >
      <slot name="kitchenware"></slot>
    </el-tab-pane>
  </el-tabs>
</template>
<script lang="ts" setup>
//定義跳轉時初始標籤名稱
const props = defineProps({
  activeName: String,
});

import { ref } from "vue";
import type { TabsPaneContext } from "element-plus";
import router from "../router";

const activeName = ref(props.activeName);

const handleChange = (tab: TabsPaneContext, event: Event) => {
  // console.log(activeName.value);
  switch (activeName.value) {
    case "recipe":
      router.push({ path: "/Recipe" });
      break;

    case "classification":
      router.push({ path: "/classification" });
      break;

    case "course":
      router.push({ path: "/course" });
      break;

    case "kitchenware":
      router.push({ path: "/kitchenware" });
      break;

    case "cookingMethod":
      router.push({ path: "/cookingMethodSearch" });
      break;

    default:
      break;
  }
};
</script>
<style lang=""></style>
