<template>
  <h2>註冊畫面</h2>

  <InputText>
    <!-- 有輸入title會被替換? -->
    <template v-slot:title>帳號</template>
    <template v-slot:input
      ><input
        type="text"
        v-model="member.account"
        @input="checkAccountExist"
        id="account"
        class="form-control"
        aria-describedby="passwordHelpInline"
        placeholder="請輸入帳號"
      />
    </template>
    <template v-slot:message>
      {{ accountIsExist }}
    </template>
  </InputText>
  <!-- 密碼輸入欄 -->

  <InputText>
    <!-- 有輸入title會被替換? -->
    <template v-slot:title>密碼</template>
    <template v-slot:input
      ><input
        v-bind:type="passwordIsReveal"
        id=""
        class="form-control"
        aria-describedby="passwordHelpInline"
        v-model="member.memberPassword"
        placeholder="請輸入密碼"
      />
    </template>
    <template v-slot:message>
      <div class="form-check">
        <input
          class="form-check-input"
          type="checkbox"
          value=""
          v-model="passwordIsRevealChecked"
          id="flexCheckDefault"
        />
        <label class="form-check-label" for="flexCheckDefault">
          顯示密碼
        </label>
      </div>
    </template>
  </InputText>

  <div class="row">
    <div class="col-6">
      <RouterLink to="/sign-up-second" class="nav-link">
        <button class="btn btn-primary" type="button" @click="">下一步</button>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, watch } from "vue";
import axios from "axios";
import Member from "../models/Member";
import InputText from "../components/InputText.vue";
const accountInput = ref(""); //帳號輸入文字
const member = ref(Member); //汙染
const accountIsExist = ref(""); //帳號存在提示
const passwordIsRevealChecked = ref(false); //密碼顯示溝選
const passwordIsReveal = ref("password"); //密碼顯示
const URL = import.meta.env.VITE_API_JAVAURL;
const test = ref("");

watch(passwordIsRevealChecked, (passwordIsRevealChecked) => {
  if (!passwordIsRevealChecked) {
    passwordIsReveal.value = "password";
  } else {
    passwordIsReveal.value = "";
  }

  // ...
});

const datas = reactive({
  account: "",
  passwordInput: "",
});

const checkAccountExist = async () => {
  if (member.value.account === "") {
    return;
  }
  const URLAPI = `${URL}members/exists/${member.value.account}`;
  const response = await axios.get(URLAPI);
  accountIsExist.value = response.data.message;
};

const inputHandler = (value) => {
  datas.name = value;
  datas.start = 0;
  loadProducts();
};
</script>

<style scoped></style>
