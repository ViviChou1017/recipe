<template>
  <el-row :gutter="20" class="row-bg">
    <el-col :span="2" :offset="0">
      <el-image :src="img" fit="cover" style="width: 100%" />
    </el-col>
    <el-col :span="8"></el-col>

    <el-col :span="6" style="display: flex">
      <SearchMemberBar></SearchMemberBar>
    </el-col>
    <el-col :span="6" style="display: flex">
      <el-row :gutter="0">
        <el-col :span="6" :offset="0">
          <el-button
            size="large"
            :icon="House"
            circle
            @click="router.push('/Recipe')"
          />
        </el-col>
        <el-col :span="6" :offset="0">
          <el-popover
            trigger="click"
            :width="300"
            popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
          >
            <template #reference>
              <el-badge :value="3" class="item">
                <el-button size="large" :icon="Message" circle />
              </el-badge>
            </template>
            <template #default>
              <div style="display: flex; flex-direction: column">
                <el-text type="primary" size="large"
                  ><el-icon><Setting /></el-icon>最新功能上線囉~
                  快來瞧瞧吧！</el-text
                >
                <el-divider>
                  <el-icon><star-filled /></el-icon>
                </el-divider>
                <el-text type="info"
                  ><el-icon><Bell /></el-icon
                  >10/31以前加入會員，即可領取500元商品折價券</el-text
                >
                <el-divider>
                  <el-icon><star-filled /></el-icon>
                </el-divider>
                <el-text type="info"
                  ><el-icon><Warning /></el-icon>10/29 凌晨01:00~上午08:00
                  為網站的固定維修時間，如有不便之處，敬請見諒！</el-text
                >
              </div>
            </template>
          </el-popover>
        </el-col>
        <el-col :span="6">
          <el-button
            size="large"
            :icon="ShoppingCartFull"
            circle
            @click="isLoggedIn ? router.push('/cart') : loginInFirst()"
          />
        </el-col>
        <el-col :span="6">
          <el-button
            size="large"
            :icon="Tickets"
            circle
            @click="isLoggedIn ? router.push('/order-list') : loginInFirst()"
          />
        </el-col>
      </el-row>
    </el-col>

    <el-col :span="2" style="display: flex">
      <el-avatar
        v-if="isLoggedIn"
        fit="cover"
        :src="member.avatar != null ? member.avatar : avatarFailed"
        @click="dialog = true"
        @error="errorHandler"
        style="cursor: pointer"
        :size="75"
      >
        <el-image :src="avatarFailed" />
      </el-avatar>
      <el-avatar
        v-if="!isLoggedIn"
        fit="cover"
        :src="avatarFailed"
        style="cursor: pointer"
        @click="dialog = true"
        @error="errorHandler"
        :size="75"
      />
    </el-col>
  </el-row>

  <!-- 以下側邊欄 -->
  <!-- :before-close="handleClose" -->
  <el-drawer
    ref="drawerRef"
    v-model="dialog"
    title=""
    direction="rtl"
    class="demo-drawer"
    size="20%"
  >
    <div class="demo-drawer__content">
      <MemberSider
        @log-in="update"
        @log-out="logOut"
        @sign-up="signUp"
        @member-center="memberCenter"
      ></MemberSider>

      <div class="demo-drawer__footer"></div>
    </div>
  </el-drawer>
</template>

<script setup>
import img from "@/photos/logo.png";
import avatarFailed from "@/photos/avatar/failed2.png";

import { ElNotification } from "element-plus";
import EleUploadImage from "vue-ele-upload-image";
import { reactive, ref, computed, onMounted, defineProps, watch } from "vue";
import { ElDrawer, ElMessageBox } from "element-plus";
import MemberSider from "./MemberSider.vue";
import { UserFilled } from "@element-plus/icons-vue";
import url from "../models/Url";
import { VueCookieNext, useCookie } from "vue-cookie-next";
import axios from "axios";
import router from "../router";
import {
  useLocalStorage,
  useMouse,
  usePreferredDark,
  useStorage,
} from "@vueuse/core";
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
  Tickets,
} from "@element-plus/icons-vue";
import SearchMemberBar from "@/components/SearchMemberBar.vue";

const props = defineProps({ avatar: String });

watch(
  () => props.avatar,
  (newVal, oldVal) => {
    // console.log("computed", props.avatar);

    // console.log("new", newVal);
    // console.log("old", oldVal);
    if (props.avatar != null) {
      member.value.avatar = props.avatar;
    }
  }
);

const logo = ref("src/photos/logo.png");

const cookie = useCookie();
// const isLoggedIn = ref(cookie.isCookieAvailable("cookie"));

//側邊欄開關
const dialog = ref(false);

const isLoggedIn = ref(cookie.isCookieAvailable("cookie"));

const member = cookie.isCookieAvailable("cookie")
  ? ref(JSON.parse(localStorage.getItem("member")))
  : ref(null);

// console.log(JSON.parse(member.value));
// console.log(member.value);

// const member = useLocalStorage("member");

// const avatarBase64 = ref(member.value ? JSON.parse(member.value).avatar : "");

const errorHandler = () => true;

const update = (param) => {
  try {
    // console.log("update");
    isLoggedIn.value = true;
    // avatarBase64.value = JSON.parse(member.value).avatar;
    // console.log(useLocalStorage("member"))
    // console.log(avatarBase64.value);
    member.value = param;
    // console.log(member.value);
    // avatarBase64.value = useStorage("member").value.avatar;
    // console.log(avatarBase64.value);
    // avatarBase64.value = JSON.parse(member.value).avatar;
    dialog.value = false;
  } catch (error) {
    console.log("update failed");
  }
};

const logOut = () => {
  try {
    // console.log("logOut");
    isLoggedIn.value = false;
    // dialog.value = false;
    router.push("/Recipe");
  } catch (error) {
    console.log("logOut failed");
  }
};

const signUp = () => {
  try {
    dialog.value = false;
  } catch (error) {
    console.log(error);
  }
};

const memberCenter = () => {
  try {
    dialog.value = false;
  } catch (error) {
    console.log(error);
  }
};

const loginInFirst = () => {
  // console.log("test");
  ElNotification({
    title: `請先登入`,
    message: "請先登入",
    type: "warning",
  });
  dialog.value = true;
};

const initial = () => {
  // avatarBase64.value = isLoggedIn.value ? JSON.parse(member.value).avatar : "";
};

onMounted(initial);
// update();

// onMounted(update);

// 以下帳號密碼輸入欄

// 以下為drawer
// const drawerRef = ref<InstanceType<typeof ElDrawer>>();
// const onClick = () => {
//   drawerRef.value!.close();
// };
</script>

<style scoped></style>
