<template>
  <div v-if="!signed">未登入，請先登入</div>

  <div class="list-group" v-if="signed">
    <button
      type="button"
      class="list-group-item list-group-item-action active"
      aria-current="true"
    >
      會員資料
    </button>
    <!-- 大頭貼 -->
    <button type="button" class="list-group-item list-group-item-action">
      <div class="row">
        <div class="col-2">大頭貼</div>
        <div class="col-3">
          <img
            :src="
              signIn.avatar == null
                ? '/src/photos/no_source.png'
                : signIn.avatar
            "
            class="img-fluid img-thumbnail"
            alt="no_source"
          />
        </div>
        <div class="col-2">預覽畫面</div>
        <div class="col-3">
          <el-upload
            class="avatar-uploader"
            action=""
            :show-file-list="false"
            :auto-upload="false"
            :on-change="getImage"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </div>
      </div>

      <!-- v-show="isEdit.avatar" 不能放在自定義組件?-->
      <!-- <ImageUploader
        @update-img="
          (base64) => {
            signIn.avatar = base64;
          }
        "
      ></ImageUploader> -->
      <div class="row">
        <button class="btn btn-primary col">返回</button>
        <button class="btn btn-primary col">更新</button>
      </div>
    </button>

    <AttributeBar
      v-for="{ index } in showViews"
      :type="views[index].type"
      :editable="views[index].editable"
      :is-valid="views[index].isValid"
      :is-edit="views[index].isEdit"
      :title="views[index].title"
      :value="views[index].value"
      :validMessage="views[index].validMessage"
      :invalidMessage="views[index].invalidMessage"
      @show="
        (param) => {
          views[index].isEdit = param;
        }
      "
      @update="
        (param) => {
          views[index].value = param;
          update();
          updateCookie();

          //為完成
        }
      "
      @check="
        (params) => {
          views[index].isValid = views[index].check(params, index)
            ? true
            : false;
        }
      "
    ></AttributeBar>
  </div>
</template>

<script setup lang="ts">
import router from "../router";
import { ref, reactive, computed } from "vue";
import { useCookie } from "vue-cookie-next";

import SignIn from "../models/SignIn";
import ImageUploader from "../components/ImageUploader.vue";
import AttributeBar from "../components/AttributeBar.vue";
import { ElMessage } from "element-plus";
import { Plus } from "@element-plus/icons-vue";

import type { UploadProps } from "element-plus";

const cookie = useCookie();
const signed = ref(cookie.getCookie("cookie"));
const signIn = ref();
if (signed.value) {
  signIn.value = cookie.getCookie("cookie");
}

const views = ref([
  {
    editable: false,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 0,
    attribute: "account",
    title: "帳號",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過20個字元或未輸入字串",
    check: (param, index) => {
      return param.length < 20 && param.length > 0 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 1,
    attribute: "memberPassword",
    title: "密碼",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過20個字元或未輸入字串",
    check: (param, index) => {
      return param.length < 20 && param.length > 0 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 2,
    attribute: "memberName",
    title: "暱稱",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過20個字元或未輸入字串",
    check: (param, index) => {
      return param.length <= 20 && param.length > 0 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "date",
    isValid: true,
    isEdit: false,
    index: 3,
    attribute: "birthday",
    title: "生日",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過20個字元或未輸入字串",
    check: (param, index) => {
      console.log("check未完成");
      return param.length < 20 && param.length > 0 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 4,
    attribute: "introduction",
    title: "介紹",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過50個字元或未輸入字串",
    check: (param, index) => {
      console.log("check未完成");
      return param.length <= 50 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 5,
    attribute: "gender",
    title: "性別",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過50個字元或未輸入字串",
    check: (param, index) => {
      console.log("check未完成");
      return param.length <= 50 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 6,
    attribute: "email",
    title: "電子信箱",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過50個字元或未輸入字串",
    check: (param, index) => {
      console.log("check未完成");
      return param.length <= 50 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 7,
    attribute: "address",
    title: "地址",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過50個字元或未輸入字串",
    check: (param, index) => {
      console.log("check未完成");
      return param.length <= 50 ? true : false;
    },
  },
  {
    editable: true,
    isHidden: false,
    type: "text",
    isValid: true,
    isEdit: false,
    index: 8,
    attribute: "cellphone",
    title: "手機",
    value: "",
    validMessage: "格式符合",
    invalidMessage: "格式不符，超過50個字元或未輸入字串",
    check: (param, index) => {
      console.log("check未完成");
      return param.length <= 50 ? true : false;
    },
  },
]);

const showViews = computed(() => {
  return views.value.filter((item) => !item.isHidden);
});

const updateCookie = () => {
  cookie.setCookie("cookie", signIn.value);
  console.log("update", cookie.getCookie("cookie"));
};

const initial = () => {
  views.value.forEach((element) => {
    try {
      switch (element.attribute) {
        case "account":
          element.value = signIn.value.account;
          break;
        case "memberPassword":
          element.value = signIn.value.memberPassword;
          break;
        case "memberName":
          element.value = signIn.value.memberName;
          break;
        case "account":
          element.value = signIn.value.account;
          break;
        case "birthday":
          element.value = signIn.value.birthday;
          break;
        case "introduction":
          element.value = signIn.value.introduction;
          break;
        case "gender":
          element.value = signIn.value.gender;
          break;
        case "email":
          element.value = signIn.value.email;
          break;
        case "address":
          element.value = signIn.value.address;
          break;
        case "cellphone":
          element.value = signIn.value.address;
          break;
      }
    } catch (error) {}
  });
  console.log("initial:", views.value);
};

const update = () => {
  views.value.forEach((element) => {
    try {
      switch (element.attribute) {
        case "account":
          signIn.value.account = element.value;
          break;
        case "memberPassword":
          signIn.value.memberPassword = element.value;
          break;
        case "memberName":
          signIn.value.memberName = element.value;
          break;
        case "account":
          signIn.value.account = element.value;
          break;
        case "birthday":
          signIn.value.birthday = element.value;
          break;
        case "introduction":
          signIn.value.introduction = element.value;
          break;
        case "gender":
          signIn.value.gender = element.value;
          break;
        case "email":
          signIn.value.email = element.value;
          break;
        case "address":
          signIn.value.address = element.value;
          break;
        case "cellphone":
          signIn.value.address = element.value;
          break;
      }
    } catch (error) {}
  });
  console.log("update:", views.value);
};

initial();

const imageUrl = ref("");

const getBase64 = (file) => {
  return new Promise(function (resolve, reject) {
    let reader = new FileReader();
    let imgResult = "";
    reader.readAsDataURL(file);
    reader.onload = function () {
      imgResult = reader.result as string; //強轉?
    };
    reader.onerror = function (error) {
      reject(error);
    };
    reader.onloadend = function () {
      resolve(imgResult);
    };
  });
};

const getImage = async (file) => {
  console.log(file);
  if (beforeAvatarUpload(file.raw)) {
    imageUrl.value = (await getBase64(file.raw)) as string;
  }
};

const beforeAvatarUpload = (rawFile) => {
  if (rawFile.type !== "image/jpeg" && rawFile.type !== "image/png") {
    ElMessage.error("Avatar picture must be JPG format!");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("Avatar picture size can not exceed 2MB!");
    return false;
  }
  return true;
};
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
