<template>
  <el-row :gutter="0">
    <el-col :span="20" :offset="2">
      <el-card :style="coverPhoto">
        <el-col :span="14" :offset="5">
          <el-form
            label-position="top"
            ref="ruleFormRef"
            :model="ruleForm"
            :rules="rules"
            label-width="120px"
            class="demo-ruleForm"
            :size="formSize"
            status-icon
          >
            <!-- 帳號 -->
            <el-form-item label="帳號" prop="account">
              <el-input
                v-model="ruleForm.account"
                maxlength="16"
                show-word-limit
              />
            </el-form-item>
            <!-- 密碼 -->
            <el-form-item label="密碼" prop="memberPassword">
              <el-input
                v-model="ruleForm.memberPassword"
                type="password"
                autocomplete="off"
                show-password
              />
            </el-form-item>
            <!-- 密碼確認 -->
            <el-form-item label="密碼確認" prop="checkPass">
              <el-input
                v-model="ruleForm.checkPass"
                type="password"
                autocomplete="off"
                show-password
              />
            </el-form-item>
            <!-- 暱稱 -->
            <el-form-item label="暱稱" prop="memberName">
              <el-input
                v-model="ruleForm.memberName"
                maxlength="20"
                show-word-limit
              />
            </el-form-item>
            <!-- 出生日期 -->
            <el-form-item prop="birthday" label="出生日期" required>
              <el-date-picker
                value-format="YYYY-MM-DD"
                v-model="ruleForm.birthday"
                type="date"
                label="Pick a date"
                placeholder="Pick a birthday"
                style="width: 100%"
                :disabled-date="DateDisabler"
              />
            </el-form-item>
            <!-- 生理性別 -->
            <el-form-item label="生理性別" prop="gender">
              <el-radio-group v-model="ruleForm.gender">
                <el-radio label="男" />
                <el-radio label="女" />
              </el-radio-group>
            </el-form-item>
            <!-- 通訊地址 -->
            <el-form-item label="通訊地址">
              <el-col :span="10" :offset="0">
                <el-cascader
                  v-model="ruleForm.addressPrepend"
                  :options="citycountydata"
                  :props="props"
                  placeholder="請選擇"
                />
              </el-col>
              <el-col :span="10"
                ><el-input
                  v-model="zipCodewithName"
                  disabled
                  placeholder="Please input"
              /></el-col>

              <el-col :span="24">
                <el-input
                  v-model="ruleForm.addressAppend"
                  @change="addressMaker"
                  placeholder="請先選擇地區再輸入地址"
                  :disabled="ruleForm.addressPrepend.length == 0"
                >
                </el-input>
              </el-col>
            </el-form-item>

            <!-- email -->
            <el-form-item label="電子信箱" prop="email">
              <el-input v-model="ruleForm.email" />
            </el-form-item>

            <!-- 手機 -->
            <el-form-item label="手機" prop="cellphone">
              <el-input
                v-model="ruleForm.cellphone"
                maxlength="10"
                show-word-limit
              />
            </el-form-item>

            <!-- 表單按鍵 -->
            <el-form-item>
              <el-col :span="24" :offset="0" style="flex-direction: row">
                <el-button round @click="sharonClick">一鍵輸入</el-button>
                <el-button
                  round
                  type="primary"
                  @click="submitForm(ruleFormRef)"
                >
                  註冊
                </el-button>

                <el-button round @click="resetForm(ruleFormRef)"
                  >清空</el-button
                >
              </el-col>
            </el-form-item>
          </el-form>
        </el-col>
      </el-card>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import citycountydata from "../models/CityCountyData.json";

import { reactive, ref, computed } from "vue";
import type { FormInstance, FormRules } from "element-plus";
import { ElNotification } from "element-plus";
import url from "../models/Url";
import axios from "axios";
import router from "../router";
import imageD from "@/photos/signup.jpg";
const coverPhoto = ref({
  width: "75%",
  position: "relative",
  // backgroundImage: `url('/src/photos/advertisements/advertisement1.jpg')`,
  backgroundImage: `url(${imageD})`,
  backgroundSize: "cover",
  backgroundPosition: "center center",
  backgroundColor: "transparent",
  alignContent: "flex-start",
});

interface RuleForm {
  account: string;
  memberName: string;

  birthday: string;

  gender: string;

  memberPassword: string;
  checkPass: string;

  addressPrepend: string[];
  cellphone: string;
  email: string;
  address: string;
  addressAppend: string;
}

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();
const ruleForm = reactive<RuleForm>({
  account: "",
  memberName: "",

  birthday: "",

  gender: "",

  memberPassword: "",
  checkPass: "",

  addressPrepend: [],
  cellphone: "",
  email: "",
  address: "",
  addressAppend: "",
});

const sharon = {
  account: "sharon841015",
  address: "116臺北市文山區忠順街X段XX巷XX號X樓",
  addressAppend: "忠順街X段XX巷XX號X樓",
  addressPrepend: ["臺北市", "文山區"],
  birthday: "1995-12-01",
  cellphone: "0999999999",
  checkPass: "aaaaaaaaaaaa",
  email: "sharon@gmail.com",
  gender: "女",
  memberName: "趙可愛",
  memberPassword: "aaaaaaaaaaaa",
};

const sharonClick = () => {
  ruleForm.account = sharon.account;
  ruleForm.address = sharon.address;
  ruleForm.addressAppend = sharon.addressAppend;
  ruleForm.addressPrepend = sharon.addressPrepend;
  ruleForm.birthday = sharon.birthday;
  ruleForm.cellphone = sharon.cellphone;
  ruleForm.checkPass = sharon.checkPass;
  ruleForm.email = sharon.email;
  ruleForm.gender = sharon.gender;
  ruleForm.memberName = sharon.memberName;
  ruleForm.memberPassword = sharon.memberPassword;
};

const addressMaker = computed(() => {
  if (ruleForm.addressPrepend.length != 0 && ruleForm.addressAppend != "") {
    try {
      console.log(zipCodewithName.value);
      ruleForm.address = zipCodewithName.value + ruleForm.addressAppend;
      console.log(ruleForm.address);
    } catch (error) {
      console.log(error);
    }
  }
});

const props = {
  //   expandTrigger: "hover" as const,
  label: "value",
};

const validateBirthday = (rule: any, value: any, callback: any) => {
  console.log(value);
  if (value === "") {
    callback(new Error("請選擇日期"));
  } else {
    if (ruleForm.checkPass !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkPass", () => null);
    }
    callback();
  }
};

const validatePass = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("Please input the password"));
  } else {
    if (ruleForm.checkPass !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkPass", () => null);
    }
    callback();
  }
};
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("Please input the password again"));
  } else if (value !== ruleForm.memberPassword) {
    callback(new Error("Two inputs don't match!"));
  } else {
    callback();
  }
};

const validateAccount = async (rule: any, value: any, callback: any) => {
  if (!isAlphaNumeric(value)) {
    callback(new Error("帳號只能使用大小寫英文字母跟數字"));
  } else {
    try {
      let isExist = await checkAccountExist();
      if (isExist) {
        callback(new Error("帳號已存在"));
      }
    } catch (error) {
      callback(new Error("出錯"));
    }
    callback();
  }
};

const validateCellphone = (rule: any, value: any, callback: any) => {
  if (!isNumeric(value)) {
    callback(new Error("手機號碼僅能包含數字"));
  } else {
    callback();
  }
};

const rules = reactive<FormRules<typeof ruleForm>>({
  account: [
    {
      required: true,
      min: 10,
      max: 16,
      message: "長度須為10-16個字元",
      trigger: "blur",
    },
    { asyncValidator: validateAccount, trigger: "blur" },
  ],
  memberName: [
    { required: true, message: "Please input Activity name", trigger: "blur" },
    { min: 3, max: 5, message: "Length should be 3 to 5", trigger: "blur" },
  ],

  birthday: [
    {
      type: "date",
      // required: true,
      // message: "Please pick a date",
      trigger: "change",
      validator: validateBirthday,
    },
  ],

  gender: [
    {
      required: true,
      message: "Please select activity gender",
      trigger: "change",
    },
  ],

  memberPassword: [
    { min: 12, max: 16, message: "長度須為12-16個字元", trigger: "blur" },
    { validator: validatePass, trigger: "blur", required: true },
  ],
  checkPass: [
    { min: 12, max: 16, message: "長度須為12-16個字元", trigger: "blur" },
    { validator: validatePass2, trigger: "blur", required: true },
  ],
  cellphone: [
    { min: 10, max: 10, message: "長度須為10個字元", trigger: "blur" },
    { validator: validateCellphone, trigger: "blur", required: true },
  ],
  email: [
    {
      required: true,
      message: "請輸入電子信箱",
      trigger: "change",
    },
  ],
});

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log("submit!");
      signUp();
    } else {
      console.log("error submit!", fields);
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
};

const options = Array.from({ length: 10000 }).map((_, idx) => ({
  value: `${idx + 1}`,
  label: `${idx + 1}`,
}));

const formatDate = (date = new Date()) => {
  const year = date.toLocaleString("default", { year: "numeric" });
  const month = date.toLocaleString("default", {
    month: "2-digit",
  });
  const day = date.toLocaleString("default", { day: "2-digit" });

  return [year, month, day].join("-");
};

const DateDisabler = (date) => {
  return date > new Date();
};

interface CityData {
  value: string;
  CityEngName: string;
  children: {
    ZipCode: string;
    value: string;
    AreaEngName: string;
  }[];
}

const data: CityData[] = citycountydata;

const findZipCode = (
  cityData: CityData[],
  cityName: string,
  areaName: string
): string | undefined => {
  const city = cityData.find((data) => data.value === cityName);
  if (city) {
    const area = city.children.find((child) => child.value === areaName);
    if (area) {
      return area.ZipCode;
    }
  }
  return undefined;
};

const zipCodewithName = computed(() => {
  try {
    if (ruleForm.addressPrepend.length != 0)
      return (
        findZipCode(
          data,
          ruleForm.addressPrepend[0],
          ruleForm.addressPrepend[1]
        ) +
        ruleForm.addressPrepend[0] +
        ruleForm.addressPrepend[1]
      );
  } catch (error) {
    console.log();
  }
});

function isAlphaNumeric(input: string): boolean {
  // 使用正規表達式來檢查是否只包含英文和數字
  const alphaNumericRegex = /^[a-zA-Z0-9]+$/;
  return alphaNumericRegex.test(input);
}

function isNumeric(input: string): boolean {
  // 使用正規表達式來檢查是否只包含數字
  const alphaNumericRegex = /^[0-9]+$/;
  return alphaNumericRegex.test(input);
}

const checkAccountExist = async () => {
  const URLAPI = `${url}members/exists/${ruleForm.account}`;
  const response = await axios.get(URLAPI);
  return !response.data.success;
};

const signUp = async () => {
  try {
    const URLAPI = `${url}member/signup`;

    const response = await axios.post(URLAPI, ruleForm);
    if (response.status == 200) {
      ElNotification({
        title: `註冊成功`,
        message: "註冊成功，即將導向首頁",
        type: "success",
      });
      router.push("/");
    }
  } catch (error) {
    console.log(error);
    ElNotification({
      title: `註冊失敗`,
      message: "註冊失敗，請確認連線",
      type: "error",
    });
  }
};
</script>
