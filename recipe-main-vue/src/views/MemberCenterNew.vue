<template>
  <el-row :gutter="0">
    <el-col
      :span="24"
      :offset="0"
      :style="coverPhoto"
      style="justify-content: end; align-items: end"
    >
      ><el-button :icon="Camera" round> 編輯封面照 </el-button></el-col
    >

    <el-col
      :span="24"
      :offset="0"
      style="background-color: white; align-items: start"
    >
      <el-tabs
        v-model="activeName"
        class="demo-tabs"
        @tab-change="change"
        style="width: 100%"
        stretch
      >
        <el-tab-pane label="我的食譜" name="recipes"> </el-tab-pane>
        <el-tab-pane label="追蹤清單" name="track"></el-tab-pane>
        <el-tab-pane label="收藏清單" name="collect"></el-tab-pane>
        <el-tab-pane label="基本資料" name="profile"></el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
  <!-- 食譜內容 -->
  <el-row :gutter="0" v-if="activeName == 'recipes'">
    <!-- <el-col :span="4" v-for="recipe in recipes" :key="recipe.id">
      <el-card
        @click="console.log('test')"
        @mouseenter="console.log('1')"
        @mouseleave="console.log('2')"
        class="test"
        :body-style="{ padding: '0px' }"
      >
        <img
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          cover
          style="width: 100%"
        />
      </el-card>
    </el-col> -->

    <el-col :span="24" :offset="0">
      <el-button
        :icon="Search"
        round
        @click="router.push({ path: '/InsertRecipe' })"
        >上傳食譜</el-button
      >
    </el-col>

    <el-col :span="6" v-for="recipe in recipes" :key="recipe.id">
      <el-card
        :body-style="{ padding: '0px 0px 3% 0px', width: '100%' }"
        :style="{ width: '100%' }"
      >
        <RouterLink :to="{ name: 'SingleRecipe', params: { id: recipe.id } }">
          <el-image
            style="width: 100%; height: 200px"
            :src="recipe.picBase64"
            fit="cover"
          >
            <template #error>
              <el-col :span="24" :offset="0" style="align-content: center">
              </el-col>
            </template>
          </el-image>
        </RouterLink>
        <el-row :gutter="20">
          <el-col :span="24">
            <RouterLink
              style="text-decoration: none"
              :to="{ name: 'SingleRecipe', params: { id: recipe.id } }"
            >
              <el-col :span="24" :offset="0">
                <el-text type="primary" size="large" tag="b">{{
                  recipe.recipeName
                }}</el-text>
              </el-col>
            </RouterLink>
            <el-text type="info"
              >新增日期:{{ formatDate(recipe.uploadDate) }}</el-text
            >
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
  <!-- 追蹤內容 -->
  <el-row :gutter="0" v-if="activeName == 'track'">
    <el-col :span="4" v-for="(element, index) in tracks" :key="index">
      <el-image
        style="width: 100%"
        :src="element.avatar != null ? element.avatar : avatarFailed"
        fit="cover"
      >
        <template #error>
          <el-image
            style="width: 100%"
            :src="avatarFailed"
            fit="cover"
          ></el-image></template
      ></el-image>
      <el-text tag="b" size="large"
        >{{ element.memberName }}
        <el-text tag="i" type="info">{{ element.account }}</el-text></el-text
      >

      <el-button
        class="button"
        size="large"
        round
        :disabled="element.unfollow"
        @click="
          element.unfollow = true;
          deleteTrack(element.trackNumber, element.memberName);
        "
        ><el-icon><CircleClose /></el-icon>取消追蹤</el-button
      >
    </el-col>
  </el-row>
  <!-- 收藏內容 -->
  <el-row :gutter="0" v-if="activeName == 'collect'">
    <el-col
      :span="6"
      v-for="colletedRecipe in colletedRecipes"
      :key="colletedRecipe.collectNumber"
    >
      <el-card
        :body-style="{ padding: '0px 0px 3% 0px', width: '100%' }"
        :style="{ width: '100%' }"
      >
        <RouterLink
          :to="{
            name: 'SingleRecipe',
            params: { id: colletedRecipe.recipeNumber },
          }"
        >
          <el-image
            style="width: 100%; height: 200px"
            :src="colletedRecipe.picBase64"
            fit="cover"
          >
            <template #error>
              <el-col :span="24" :offset="0" style="align-content: center">
              </el-col>
            </template>
          </el-image>
        </RouterLink>
        <el-row :gutter="20">
          <el-col :span="24">
            <el-col :span="24" :offset="0">
              <RouterLink
                style="text-decoration: none"
                :to="{
                  name: 'SingleRecipe',
                  params: { id: colletedRecipe.recipeNumber },
                }"
              >
                <el-text type="primary" size="large" tag="b">{{
                  colletedRecipe.recipeName
                }}</el-text></RouterLink
              >
            </el-col>
            <el-col :span="24" :offset="0">
              <el-text type="info"
                >食譜作者:{{ colletedRecipe.memberName }}</el-text
              >
            </el-col>

            <el-col :span="24" :offset="0">
              <el-text type="info"
                >加入收藏:{{ formatDate(colletedRecipe.collectDate) }}</el-text
              >
            </el-col>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
  <el-row :gutter="0" v-if="activeName == 'profile'">
    <el-col :span="12" :offset="0">
      <el-popover
        placement="right"
        title="點擊大頭貼"
        :width="200"
        trigger="hover"
        content="可裁切圖片，編輯後上傳"
      >
        <template #reference>
          <el-image
            fit="cover"
            style="cursor: pointer; width: 50%"
            :src="
              memberLocalStorage.avatar != null
                ? memberLocalStorage.avatar
                : avatarFailed
            "
            @click="dialogVisible = true"
          >
            <template #error>
              <el-image
                :src="avatarFailed"
                style="width: 50%"
                @click="dialogVisible = true"
              />
            </template>
          </el-image>
        </template>
      </el-popover>

      <!-- <el-avatar
          v-if="!isLoggedIn"
          fit="cover"
          :src="avatarFailed"
          style="cursor: pointer"
          @click="dialogVisible = true"
          @error="errorHandler"
          :size="75"
        /> -->

      <AvatarCropper
        v-if="dialogVisible"
        :dialogVisible.sync="dialogVisible"
        @closeAvatarDialog="closeAvatarDialog"
        @test="
          (avatarBase64) => {
            updateAvatar(avatarBase64);
          }
        "
      ></AvatarCropper>
      <!-- <AdvancedCropper :image="'https://images.unsplash.com/photo-1485178575877-1a13bf489dfe?ixlib=rb-1.2.1&auto=format&fit=crop&w=991&q=80'"></AdvancedCropper> -->
    </el-col>
    <el-col :span="12">
      <el-card style="width: 100%">
        <el-collapse accordion style="width: 100%">
          <!-- 帳號 -->
          <el-collapse-item name="account">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>帳號</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  memberLocalStorage.account
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
          <!-- 暱稱 -->
          <el-collapse-item name="memberName">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>暱稱</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  memberLocalStorage.memberName
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
          <!-- 出生日期 -->
          <el-collapse-item name="birthday">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>出生日期</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  formatDate(memberLocalStorage.birthday)
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
          <!-- 生理性別 -->
          <el-collapse-item name="gender">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>生理性別</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  memberLocalStorage.gender
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
          <!-- 通訊地址 -->
          <el-collapse-item name="address">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>通訊地址</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  memberLocalStorage.address
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
          <!-- 電子信箱 -->
          <el-collapse-item name="email">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>電子信箱</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  memberLocalStorage.email
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
          <!-- 手機 -->
          <el-collapse-item name="cellphone">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>手機</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  memberLocalStorage.cellphone
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
          <!-- 簡介 -->
          <el-collapse-item name="introduction">
            <template #title>
              <el-col :span="6" :offset="0">
                <el-text size="large" tag="b" style="align-self: self-start">
                  <el-icon><User /></el-icon>簡介</el-text
                >
              </el-col>
              <el-col :span="16">
                <el-text size="large" tag="b" style="align-self: self-start">{{
                  memberLocalStorage.introduction
                }}</el-text>
              </el-col>
            </template>
          </el-collapse-item>
        </el-collapse>
      </el-card>
    </el-col>
  </el-row>
</template>
<script setup>
import { reactive } from "vue";
import avatarFailed from "@/photos/avatar/failed2.png";
import Example from "@/components/rotate-image-example.vue";
import AvatarCropper from "@/components/avatarCropper.vue";
const dialogVisible = ref(false);
// 关闭弹框所触发的事件
const closeAvatarDialog = (data) => {
  dialogVisible.value = false;
};
import AdvancedCropper from "../components/AdvancedCropper.vue";
import "vue-cropper/dist/index.css";
import { VueCropper } from "vue-cropper";
import { ElNotification } from "element-plus";
import EleUploadImage from "vue-ele-upload-image";
import { computed, ref, onMounted } from "vue";
import { h } from "vue";
import { useStorage } from "@vueuse/core";
import imageC from "@/photos/profile1.jpg";
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
import axios from "axios";
import { VueCookieNext, useCookie } from "vue-cookie-next";
import router from "../router";

const cookie = useCookie();

const member = ref(
  cookie.isCookieAvailable("cookie") ? cookie.getCookie("cookie") : null
);

const emits = defineEmits(["updateMember"]);

const activeName = ref("recipes");

// 追蹤清單陣列
const tracks = ref([]);

// 食譜清單陣列
const recipes = ref([]);

// 收藏食譜清單陣列
const colletedRecipes = ref([]);

const coverPhoto = ref({
  height: "250px",
  position: "relative",
  // backgroundImage: `url('/src/photos/advertisements/advertisement1.jpg')`,
  backgroundImage: `url(${imageC})`,
  backgroundSize: "cover",
  backgroundPosition: "center center",
});

// const isLoggedIn = ref(cookie.isCookieAvailable("cookie"));

const memberLocalStorage = cookie.isCookieAvailable("cookie")
  ? ref(JSON.parse(localStorage.getItem("member")))
  : ref(null);

// const handleChange = (tab: TabsPaneContext, event: Event) => {
//   console.log(tab, event);
// };

const updateTracks = async (memberNumber) => {
  try {
    const API_URL = `${url}track-with-name/${memberNumber}`;
    // console.log(memberNumber);
    const response = await axios.get(API_URL);
    if (response.status === 200) {
      console.log("updateTracks", response.data);
      const list = [];
      response.data.forEach((element) => {
        let object = {
          trackNumber: element[0],
          memberNumber: element[1],
          account: element[2],
          memberName: element[3],
          avatar: element[4],
          unfollow: false,
        };
        list.push(object);
        // console.log(list);
      });

      return list;
    }
  } catch (error) {
    console.log(error);
  }

  return [];
};

//更新食譜清單
const updateRecipes = async (uploaderId) => {
  try {
    const API_URL = `${url}member/recipe/${uploaderId}`;
    // console.log(uploaderId);
    const response = await axios.get(API_URL);
    if (response.status === 200) {
      console.log("updateRecipes", response.data);
      const list = [];
      response.data.forEach((element) => {
        let recipe = {
          id: element[0],
          recipeName: element[1],
          uploadDate: element[2],
          picBase64: element[4],
        };
        list.push(recipe);
      });

      // console.log(list);
      return list;
    }
  } catch (error) {
    console.log(error);
  }

  return [];
};

//更新食譜清單
const updateCollectedRecipes = async (collectNumber) => {
  try {
    const API_URL = `${url}member/recipes/collect/${collectNumber}`;
    // console.log(uploaderId);
    const response = await axios.get(API_URL);
    if (response.status === 200) {
      console.log("updateRecipes", response.data);
      const list = [];
      response.data.forEach((element) => {
        let collectedRecipe = {
          collectNumber: element[0],
          recipeNumber: element[1],
          uploaderId: element[2],
          memberName: element[3],
          recipeName: element[4],
          collectDate: element[5],
          picBase64: element[6],
        };
        list.push(collectedRecipe);
      });

      console.log(list);
      return list;
    }
  } catch (error) {
    console.log(error);
  }

  return [];
};

const change = async () => {
  try {
    switch (activeName.value) {
      case "track":
        // console.log("track");
        if (cookie.isCookieAvailable("cookie") == false) {
          return;
        }
        tracks.value = await updateTracks(
          cookie.getCookie("cookie").memberNumber
        );
        break;

      case "recipes":
        // console.log("recipes");
        if (cookie.isCookieAvailable("cookie") == false) {
          return;
        }
        recipes.value = await updateRecipes(
          cookie.getCookie("cookie").memberNumber
        );
        break;

      case "collect":
        // console.log("recipes");
        if (cookie.isCookieAvailable("cookie") == false) {
          return;
        }
        colletedRecipes.value = await updateCollectedRecipes(
          cookie.getCookie("cookie").memberNumber
        );
        break;

      default:
        break;
    }
  } catch (error) {
    console.log(error);
  }
};

const updateAvatar = (avatarBase64) => {
  try {
    if (avatarBase64 != null && memberLocalStorage.value != null) {
      // memberLocalStorage.value.avatar = avatarBase64;
      let modify = memberLocalStorage.value;
      modify.avatar = avatarBase64;
      updateMember(modify);
    }
  } catch (error) {
    console.log(error);
  }
};

const updateMember = async (memberBean) => {
  try {
    // console.log("memberBean", memberBean);
    const API_URL = `${url}member/modify`;
    const response = await axios.put(API_URL, memberBean);
    if (response.status === 200) {
      ElNotification({
        title: "會員資料已更新",
        message: h("i", { style: "color: grey" }, `會員資料已順利更新`),
      });
      memberLocalStorage.value = memberBean;
      localStorage.setItem("member", JSON.stringify(memberBean));
      // console.log("更新", memberLocalStorage.value);
      emits("updateMember", memberLocalStorage.value.avatar);
    }
  } catch (error) {
    console.log(error);
  }
};

const deleteTrack = async (trackNumber, memberName) => {
  try {
    const API_URL = `${url}track/delete/${trackNumber}`;
    const response = await axios.delete(API_URL);
    if (response.status === 204) {
      ElNotification({
        title: "取消追蹤成功",
        message: h("i", { style: "color: grey" }, `取消追蹤${memberName}`),
      });
    }
  } catch (error) {
    console.log(error);
  }
};

const handleResponse = (response, file, fileList) => {
  return file.url;
};

const formatDate = (date) => {
  return date ? new Date(date).toLocaleDateString() : "";
};
// const test = useStorage("test", [{ hello: "hi", greeting: "Hello" }]);

const initial = () => {
  change();
  // avatarBase64.value = isLoggedIn.value ? JSON.parse(member.value).avatar : "";
};

onMounted(initial);

// const test = useStorage("test");
// console.log(test.value);
// test.value = null;
// test.value = 5;
// test.value = 10;
// console.log(test.value[0].hello);

// useStorage("test", 120);
// console.log(useStorage("test").value);
// console.log(test.value);
// const updateMember;
</script>
<style scoped>
/* .demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
} */

.style-col {
  /* padding: 5px; */

  flex-direction: column;
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  text-align: center;
  margin-left: 0px !important;
  margin-right: 0px !important;
  display: flex !important;
}

.test {
  cursor: pointer;
}
</style>
