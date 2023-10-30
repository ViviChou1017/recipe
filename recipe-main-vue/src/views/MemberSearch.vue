<template>
  <!-- <el-row :gutter="20">
    <el-col :span="24" :offset="0">
      <el-image
        style="width: 100%; height: auto; border-radius: 10px; opacity: 50%"
        src="src/photos/member-search.jpg"
        fit="cover"
      />
    </el-col>
  </el-row> -->

  <el-row :gutter="0" :style="coverPhoto">
    <el-col
      :span="4"
      v-for="(
        { avatar, account, memberNumber, isFollowed, memberName }, index
      ) in memberSearched"
      :key="index"
    >
      <el-image
        style="width: 100%"
        :src="avatar != null ? avatar : avatarFailed"
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
        >{{ memberName }}
        <el-text tag="i" type="info">{{ account }}</el-text></el-text
      >

      <el-button
        class="button"
        size="large"
        round
        :disabled="isFollowed"
        @click="follow(memberNumber, memberName)"
      >
        <el-icon v-if="!isFollowed"><CirclePlus /></el-icon>
        <el-icon v-if="isFollowed"><CircleCheck /></el-icon>
        {{ isFollowed ? "已追蹤" : "追蹤" }}</el-button
      >
    </el-col>
  </el-row>

  <!-- <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <span>會員搜尋</span>
      </div>
    </template>

    <el-row :gutter="20">
      <el-col
        :span="24"
        :offset="0"
        v-for="(
          { avatar, account, memberNumber, isFollowed }, index
        ) in memberSearched"
        :key="index"
        @click="toMemberPage(memberNumber)"
      >
        <el-image
          style="width: 100%; height: 200px"
          :src="images[index]"
          fit="cover"
        />
      </el-col>
    </el-row>

    {{ "List item " + o }}

    <el-button class="button" text>Operation button</el-button>
  </el-card> -->

  <!-- 以上新介面 -->

  <div class="list-group" v-if="false">
    <div class="list-group-item active" aria-current="true">會員搜尋</div>
    <button
      class="list-group-item list-group-item-action"
      aria-current="true"
      type="button"
      v-for="(
        { avatar, account, memberNumber, isFollowed }, index
      ) in memberSearched"
      :key="index"
      @click="toMemberPage(memberNumber)"
    >
      <div class="row align-items-center">
        <div class="col-3">
          <img
            class="card img-fluid img-thumbnail mx-auto d-block"
            src="../photos/no_source_round.png"
            alt=""
            style="border-radius: 50%; width: 50%"
          />
        </div>
        <div class="col-7">
          {{ account }}
        </div>
        <div class="col-2 text-center">
          <button
            class="btn btn-primary"
            type="submit"
            @click="follow(memberNumber, account)"
            :disabled="isFollowed"
          >
            {{ isFollowed ? "已追蹤" : "追蹤" }}
          </button>
        </div>
      </div>
    </button>
    <div class="list-group-item" v-if="memberSearched.length == 0">
      {{ props.account == undefined ? "未輸入搜尋條件" : "查無資料" }}
    </div>
  </div>
</template>

<script setup>
import { ElNotification } from "element-plus";
import { ref, reactive, computed, watch } from "vue";
import { useRoute } from "vue-router";
import axios from "axios";
import { h } from "vue";
import Search from "../models/Search";
import MemberSearched from "../models/MemberSearched";
import router from "../router";
import avatarFailed from "@/photos/avatar/failed2.png";
import {
  Check,
  Delete,
  Edit,
  Message,
  Star,
  ShoppingCartFull,
  Notification,
  House,
  Camera,
  CircleClose,
  CircleCheck,
  CirclePlus,
} from "@element-plus/icons-vue";

const memberSearched = ref(MemberSearched);
const route = useRoute();

const props = defineProps({
  account: String,
});

//從cookie中獲取account
const getAccount = () => {
  try {
    return $cookies.get("cookie").account;
  } catch (error) {
    return "";
  }
};

//偵測路徑變化呼叫searchMembersByAccount
watch(
  () => route.query.account,
  async () => {
    console.log("watch", route.query.account);
    searchMembersByAccount(props.account, getAccount());
  }
);

//利用登入帳號跟搜尋欄的輸入值搜尋會員 避開搜尋登入帳號
const searchMembersByAccount = async (account, notAccount) => {
  const API_URL = `${
    import.meta.env.VITE_API_JAVAURL
  }members/like&&not/${account}/${notAccount}`;
  const response = await axios.get(API_URL);

  if (response.status === 200) {
    memberSearched.value = response.data;
    let list = await followList($cookies.get("cookie").memberNumber);
    memberSearched.value.forEach((element) => {
      if (list.includes(element.memberNumber)) {
        element.isFollowed = true;
      } else {
        element.isFollowed = false;
      }
    });
  } else {
    memberSearched.value = [];
  }
};

const test = () => {
  console.log(memberSearch);
};

//第一次進入頁面 查詢
if (getAccount() != "") {
  searchMembersByAccount(props.account, getAccount());
} else {
  ElNotification({
    title: `請先登入`,
    message: "部分功能僅提供會員使用",
    type: "warning",
  });
  router.push("/");
}

const follow = async (trackedNumber, memberName) => {
  const API_URL = `${import.meta.env.VITE_API_JAVAURL}track`;
  let input = {
    trackerNumber: memberNumber(),
    trackedNumber: trackedNumber,
  };
  console.log(input);
  const response = await axios.post(API_URL, input);
  console.log(response.status);
  if (response.status == 200) {
    ElNotification({
      title: "追蹤成功",
      message: h("i", { style: "color: grey" }, `已追蹤${memberName}`),
    });
  } else {
    alert("已追蹤");
  }
};

const memberNumber = () => {
  try {
    return $cookies.get("cookie").memberNumber;
  } catch (error) {
    return 0;
  }
};

const followList = async (trackerNumber) => {
  const API_URL = `${import.meta.env.VITE_API_JAVAURL}track/${trackerNumber}`;
  const response = await axios.get(API_URL);
  if (response.status === 200) {
    let list = [];
    response.data.forEach((element) => {
      list.push(element.trackedNumber);
    });
    return list;
  } else {
    return [];
  }
};

const toMemberPage = (memberNumber) => {
  console.log(typeof memberNumber);
  if (memberNumber == null) {
    router.push({ path: "/member-page" });
  } else {
    router.push({
      path: "/member-page",
      query: { memberNumber: memberNumber },
    });
  }
};
const coverPhoto = ref({
  height: "960px",
  position: "relative",
  // backgroundImage: `url('/src/photos/advertisements/advertisement1.jpg')`,
  backgroundImage: `url(src/photos/member-search-background1.jpg)`,
  backgroundSize: "cover",
  backgroundPosition: "center center",
  alignContent: "flex-start",
});
</script>

<style scoped>
/* .el-main {
  height: "250px";
  background-image: "url(src/photos/member-search-background.jpg)";
  background-size: "cover";
  background-position: "center center";
} */
</style>
