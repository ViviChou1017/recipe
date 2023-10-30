<template>
  <!-- <span class="demonstration">測試</span> -->
  <el-col :span="24" :style="{ width: '100%' }">
    <el-carousel trigger="click" style="width: 100%; height: 100%">
      <el-carousel-item v-for="(element, index) in advertisement" :key="index">
        <el-image
          :src="element.imageURL"
          fit="cover"
          style="width: 100%; height: 100%"
        />
      </el-carousel-item>
      <el-button size="large" type="info" :icon="Check" circle />
    </el-carousel>
  </el-col>
</template>

<script setup>
import { reactive, ref } from "vue";
import url from "../models/Url";
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from "@element-plus/icons-vue";
import imageA from "@/photos/advertisements/advertisement1.jpg";
import imageB from "@/photos/advertisements/advertisement2.jpg";

const advertisement = ref([
  {
    imageURL: imageA,
  },
  {
    imageURL: imageB,
  },
]);
</script>

<style scoped>
/* .scrollbar-demo-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    margin: 10px;
    text-align: center;
    border-radius: 4px;
    background: var(--el-color-primary-light-9);
    color: var(--el-color-primary);
  } */

.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
