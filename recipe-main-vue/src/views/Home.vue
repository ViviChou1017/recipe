<template>
  <!-- <div class="block text-center" m="t-4">
  
    <el-carousel trigger="click">
      <el-carousel-item v-for="(element, index) in advertisement" :key="index">
        <el-image :src="element.imageURL" fit="cover" />
      </el-carousel-item>
    </el-carousel>
    
  </div> -->

  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>會員搜尋</span>
      </div>
    </template>
    <div>
      <el-row :gutter="20">
        <el-col :span="6" :offset="0">
          <el-image
            style="width: 200px; height: 200px"
            src="src\photos\avatar\asian-girl.png"
            fit="cover"
          />
          <el-text tag="b" size="large">userA</el-text>
          <el-button class="button" size="large" round disabled
            ><el-icon><Star /></el-icon>已追蹤</el-button
          >
        </el-col>
        <el-col :span="6" :offset="0">
          <el-image
            style="width: 200px; height: 200px"
            src="src\photos\avatar\blond-girl.png"
            fit="cover"
          />
          <el-text tag="b" size="large">userB</el-text>
          <el-button class="button" size="large" round disabled
            ><el-icon><Star /></el-icon>已追蹤</el-button
          >
        </el-col>
        <el-col :span="6" :offset="0">
          <el-image
            style="width: 200px; height: 200px"
            src="src\photos\avatar\tchykovsky.png"
            fit="cover"
          />
          <el-text tag="b" size="large">userC</el-text>
          <el-button class="button" size="large" round type="warning"
            ><el-icon><Star /></el-icon>追蹤</el-button
          >
        </el-col>
        <el-col :span="6" :offset="0">
          <el-image
            style="width: 200px; height: 200px"
            src="src\photos\avatar\user-type-B-02.png"
            fit="cover"
          />
          <el-text tag="b" size="large">userD</el-text>
          <el-button class="button" size="large" round type="warning"
            ><el-icon><Star /></el-icon>追蹤</el-button
          >
        </el-col>
      </el-row>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from "@element-plus/icons-vue";

const advertisement = ref([
  {
    imageURL: "src/photos/advertisements/OJTdiscount.png",
  },
  {
    imageURL: "src/photos/advertisements/rainbowFun_winter.png",
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
