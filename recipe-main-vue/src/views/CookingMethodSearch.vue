<template>
  <el-row type="flex" :gutter="10" justify="center">
    <!-- 以下為新介面 -->
    <el-col :span="24" :offset="0">
      <Advertisement></Advertisement>
    </el-col>
    <el-col :span="24" :offset="0">
      <SearchBar :active-name="'cookingMethod'">
        <template v-slot:cookingMethod>
          <!-- cookingMethod 插槽的内容放这里 -->
          <el-form style="max-width: 100%" label-position="top" :inline="true">
            <el-form-item label="名稱搜尋">
              <el-input
                v-model="findByName"
                placeholder="請輸入料理法名稱"
                size="large"
                @input="findByMethodNameContaining(findByName)"
              />
            </el-form-item>
          </el-form>
        </template>
      </SearchBar>
    </el-col>
  </el-row>

  <el-row :gutter="0">
    <el-col :span="24" :offset="0"> </el-col>

    <el-col
      :span="6"
      v-for="cookingMethod in cookingMethods"
      :key="cookingMethod.id"
    >
      <el-card
        :body-style="{ padding: '0px 0px 3% 0px', width: '100%' }"
        :style="{ width: '100%' }"
      >
        <!-- <RouterLink :to="{ name: 'SingleRecipe', params: { id: recipe.id } }"> -->
        <el-image
          style="width: 100%; height: 200px"
          :src="cookingMethod.pic"
          fit="cover"
        >
          <template #error>
            <el-col :span="24" :offset="0" style="align-content: center">
            </el-col>
          </template>
        </el-image>
        <!-- </RouterLink> -->
        <el-row :gutter="20">
          <el-col :span="24">
            <!-- <RouterLink
              style="text-decoration: none"
              :to="{ name: 'SingleRecipe', params: { id: recipe.id } }"
            > -->
            <el-col :span="24" :offset="0">
              <el-text type="primary" size="large" tag="b">{{
                cookingMethod.methodName
              }}</el-text>
            </el-col>
            <!-- </RouterLink> -->
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import Advertisement from "../components/Advertisement.vue";
import SearchBar from "../components/SearchBar.vue";
import { reactive, computed, ref, onMounted } from "vue";
import axios from "axios";
import { ElNotification } from "element-plus";
import EleUploadImage from "vue-ele-upload-image";
import { h } from "vue";
import { useStorage } from "@vueuse/core";

// import type { TabsPaneContext } from "element-plus";
import url from "../models/Url";
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
  ShoppingCartFull,
  Notification,
  House,
  Camera,
  CircleClose,
} from "@element-plus/icons-vue";
import { VueCookieNext, useCookie } from "vue-cookie-next";
import router from "../router";

const findByName = ref("");

const cookingMethods = ref([]);

const findAllCookingMethods = async () => {
  try {
    const API_URL = `${url}cookingMethods`;
    // console.log(uploaderId);
    const response = await axios.get(API_URL);
    if (response.status === 200) {
      console.log("findAllCookingMethods", response.data);
      cookingMethods.value = response.data;
      // const list = [];
      // response.data.forEach((element) => {
      //   let recipe = {
      //     id: element[0],
      //     recipeName: element[1],
      //     uploadDate: element[2],
      //     picBase64: element[4],
      //   };
      //   list.push(recipe);
      // });

      // console.log(list);
    }
  } catch (error) {
    console.log(error);
  }
};

const findByMethodNameContaining = async (methodName) => {
  try {
    const API_URL = `${url}cookingMethods/${methodName}`;
    // console.log(uploaderId);
    const response = await axios.get(API_URL);
    if (response.status === 200) {
      console.log("findByMethodNameContaining", response.data);
      cookingMethods.value = response.data;
      // const list = [];
      // response.data.forEach((element) => {
      //   let recipe = {
      //     id: element[0],
      //     recipeName: element[1],
      //     uploadDate: element[2],
      //     picBase64: element[4],
      //   };
      //   list.push(recipe);
      // });

      // console.log(list);
    }
  } catch (error) {
    console.log(error);
  }
};

onMounted(findAllCookingMethods);
</script>

<style></style>
