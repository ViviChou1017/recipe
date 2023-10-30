<template>
  <!-- 以下為登入欄 -->
  <!-- inline="true" -->
  <el-form v-if="!isLoggedIn">
    <el-form-item>
      <el-col :span="24" :offset="0">
        <el-image :src="avatarFailed" style="width: 75%"></el-image>
      </el-col>
    </el-form-item>
    <el-form-item>
      <el-col :span="24" :offset="0"
        ><el-text class="mx-1" size="large">登入</el-text>
      </el-col>
    </el-form-item>
    <el-form-item
      label="帳號"
      prop="account"
      style="margin: 0px; line-height: 0px"
    >
      <el-input v-model="form.account" placeholder="請輸入帳號" />
      <el-text type="danger" size="small" style="line-height: 16px"
        >{{ form.account ? "&nbsp" : "&nbsp" }}
      </el-text>
    </el-form-item>
    <el-form-item
      label="密碼"
      prop="memberPassword"
      style="margin: 0px; line-height: 0px"
    >
      <el-input
        v-model="form.memberPassword"
        placeholder="請輸入密碼"
        show-password
      />
      <el-text type="danger" size="small" style="line-height: 16px"
        >{{ form.memberPassword ? "&nbsp" : "&nbsp" }}
      </el-text>
    </el-form-item>

    <el-form-item>
      <el-col
        :span="24"
        :offset="0"
        style="flex-direction: row"
        class="lineHeight"
      >
        記住密碼&nbsp;&nbsp;
        <el-switch inline-prompt active-text="是" inactive-text="否" />
      </el-col>
    </el-form-item>

    <el-form-item style="display: inline-block">
      <!-- <el-button @click="resetForm(ruleFormRef)">清除</el-button> -->
      <el-popover
        placement="bottom"
        :width="200"
        trigger="hover"
        content="未輸入帳號或密碼"
        :disabled="!disabled"
        :popper-style="`text-align: center;`"
      >
        <template #reference>
          <el-button type="primary" :disabled="disabled" @click="login">
            登入
          </el-button>
        </template>
        <el-text type="danger">未輸入帳號或密碼</el-text>
      </el-popover>

      <el-button type="primary" :disabled="false" @click="signUp">
        註冊
      </el-button>
    </el-form-item>
  </el-form>

  <!-- 以下為會員中心與登出欄 -->
  <el-row :gutter="20" v-if="isLoggedIn">
    <el-col :span="24" :offset="0">
      <el-button text size="large" @click="memberCenterNew">會員中心</el-button>
    </el-col>
    <el-col :span="24" :offset="0">
      <el-button text size="large" @click="logOut">登出</el-button>
    </el-col>
  </el-row>
</template>

<script setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from "@element-plus/icons-vue";
import { reactive, ref, computed } from "vue";
import { disabledTimeListsProps } from "element-plus/es/components/time-picker/src/props/shared";
import url from "../models/Url";
import axios from "axios";
import router from "../router";
import { UserFilled } from "@element-plus/icons-vue";
import { useCookies } from "vue3-cookies";
import { VueCookies } from "vue-cookies";
import { VueCookieNext, useCookie } from "vue-cookie-next";
import { ElMessage } from "element-plus";
import avatarFailed from "@/photos/avatar/failed2.png";
import {
  useLocalStorage,
  useMouse,
  usePreferredDark,
  useStorage,
  StorageSerializers,
} from "@vueuse/core";
import { ElNotification } from "element-plus";

import { inject } from "vue";

const ruleFormRef = ref();

const form = ref({
  account: "",
  memberPassword: "",
});

// const rules = reactive({
//   account: [
//     {
//       required: true,
//       min: 10,
//       max: 16,
//       message: "長度須為10-16個字元",
//       trigger: "blur",
//     },
//   ],
//   memberPassword: [
//     {
//       min: 12,
//       max: 16,
//       message: "長度須為12-16個字元",
//       trigger: "blur",
//       required: true,
//     },
//   ],
// });

const cookie = useCookie();

const isLoggedIn = ref(cookie.isCookieAvailable("cookie"));

const URL = url;

const emits = defineEmits(["logIn", "logOut", "signUp", "memberCenter"]);

const loginSuccess = (memberName) => {
  ElNotification({
    title: `歡迎回來，${memberName}`,
    message: "登入成功，即將導向首頁",
    type: "success",
  });
};

const login = async () => {
  const API_URL = `${url}members/login`;
  const response = await axios.post(API_URL, form.value);
  if (response.status === 200) {
    let member = {
      memberNumber: response.data.memberNumber,
      account: response.data.account,
      memberPassword: response.data.memberPassword,
      memberName: response.data.memberName,
    };
    cookie.setCookie("cookie", member);
    isLoggedIn.value = cookie.isCookieAvailable("cookie");
    console.log("response.data", response.data);
    //沒設變數可以存

    useStorage("member", `${JSON.stringify(response.data)}`);

    loginSuccess(response.data.memberName);
    // console.log(isLoggedIn.value, useLocalStorage("member"));
    emits("logIn", response.data);
    router.push("/Recipe");
  } else {
    isLoggedIn.value = false;
    loginFailed();
  }
};

const loginFailed = () => {
  ElNotification({
    title: `登入失敗`,
    message: "帳號或密碼錯誤，請重新輸入",
    type: "error",
  });
};

const logOut = () => {
  cookie.removeCookie("cookie");
  isLoggedIn.value = cookie.isCookieAvailable("cookie");
  let member = useStorage("member");
  member.value = null;
  emits("logOut");
};

const signUp = () => {
  emits("signUp");
  router.push({ path: "/sign-up" });
};

const memberCenterNew = () => {
  router.push({ path: "/member-center-new" });
  emits("memberCenter");
};

const disabled = computed(() => {
  return (
    form.value.account.length == 0 || form.value.memberPassword.length == 0
  );
  // form.value.account.length < 10 ||
  // form.value.account.length > 16 ||
  // form.value.account.memberPassword > 16 ||
  // form.value.memberPassword < 12
});
</script>

<style scoped>
.lineHeight {
  padding: 0px;
}
</style>
