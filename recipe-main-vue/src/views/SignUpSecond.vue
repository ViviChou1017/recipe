<template>
  <h2>註冊畫面2</h2>

  <InputText>
    <!-- 有輸入title會被替換? -->
    <template v-slot:title>會員姓名</template>
    <template v-slot:input
      ><input
        type="text"
        id=""
        class="form-control is-valid"
        aria-describedby="passwordHelpInline"
        v-model="member.memberName"
      />
    </template>
    <template v-slot:message> </template>
  </InputText>

  <InputText>
    <template v-slot:title>地址</template>

    <template v-slot:input>
      <input
        type=""
        id=""
        class="form-control"
        aria-describedby="passwordHelpInline"
        v-model="member.address"
      />
    </template>
    <template v-slot:message></template>
  </InputText>

  <InputText>
    <template v-slot:title>性別</template>

    <template v-slot:input>
      <div class="form-check">
        <input
          type="radio"
          class="form-check-input is-valid"
          id="validationFormCheck2"
          name="radio-stacked"
          value="male"
          required
          v-model="member.gender"
        />
        <label class="form-check-label" for="validationFormCheck2">男</label>
      </div>
      <div class="form-check">
        <input
          type="radio"
          class="form-check-input"
          id="validationFormCheck3"
          name="radio-stacked"
          value="female"
          v-model="member.gender"
          required
        />
        <label class="form-check-label" for="validationFormCheck3">女</label>
        <div class="invalid-feedback">More example invalid feedback text</div>
      </div>
    </template>
    <template v-slot:message>{{ member.gender }}</template>
  </InputText>

  <InputText>
    <template v-slot:title>生日</template>

    <template v-slot:input>
      <!-- <label for="make" class="form-label"></label> -->
      <input type="date" class="form-control" id="" v-model="member.birthday" />
    </template>
    <template v-slot:message>{{ member.birthday }}</template>
  </InputText>

  <InputText>
    <template v-slot:title>Email</template>

    <template v-slot:input
      ><input
        type=""
        id=""
        class="form-control"
        aria-describedby="passwordHelpInline"
        v-model="member.email"
      />
    </template>
    <template v-slot:message></template>
  </InputText>

  <InputText>
    <template v-slot:title>手機</template>

    <template v-slot:input
      ><input
        type=""
        id=""
        class="form-control"
        aria-describedby="passwordHelpInline"
        v-model="member.cellphone"
      />
    </template>
    <template v-slot:message></template>
  </InputText>

  <!-- 按鍵列 -->
  <div class="row">
    <div class="col-3">
      <RouterLink to="/sign-up-first" class="nav-link">
        <button class="btn btn-primary" type="button" @click="">上一步</button>
      </RouterLink>
    </div>
    <div class="col-3">
      <RouterLink to="/sign-up-third" class="nav-link">
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
  const response = await axios.get(URLAPI, datas);
  accountIsExist.value = response.data.message;
};

const inputHandler = (value) => {
  datas.name = value;
  datas.start = 0;
  loadProducts();
};
</script>

<style scoped></style>
