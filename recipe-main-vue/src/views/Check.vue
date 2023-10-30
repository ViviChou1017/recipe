<template>
  <div>
    <div>
      <h1>購物車</h1>
      <!-- 進度條 -->

      <el-steps
        :active="1"
        finish-status="success"
        simple
        style="margin-top: 20px; margin-bottom: 20px; background: white"
      >
        <el-step title="Step 1" />
        <el-step title="Step 2" />
        <el-step title="Step 3" />
      </el-steps>
    </div>

    <el-card shadow="always" :body-style="{ padding: '20px' }">
      <el-row
        v-for="(
          {
            courseName,
            kitchenwareName,
            coursePrice,
            kitchenwarePrice,
            quantity,
            checked,
            cartProductId,
            kitchenwareImg,
            courseImg,
          },
          index
        ) in products"
        :key="index"
      >
        <el-col :span="6" :offset="2">
          <img
            v-if="courseImg !== null"
            :src="courseImg"
            style="width: 90%"
            alt=""
          />
          <img
            v-if="kitchenwareImg !== null"
            :src="kitchenwareImg"
            style="width: 90%"
            alt=""
          />
        </el-col>
        <el-col :span="14" style="display: block">
          <el-row :gutter="10" v-if="courseName !== null">
            <el-col
              :span="4"
              :offset="4"
              style="text-align: center; display: block"
              >品項名稱：</el-col
            >
            <el-col :span="16" style="text-align: left; display: inline">{{
              courseName
            }}</el-col>
          </el-row>
          <el-row :gutter="10" v-if="courseName !== null">
            <el-col
              :span="4"
              :offset="4"
              v-if="coursePrice !== null"
              style="text-align: center; display: inline"
              >單價：</el-col
            >
            <el-col
              :span="16"
              :offset="0"
              style="text-align: left; display: inline"
              >${{ coursePrice }}</el-col
            >
          </el-row>
          <el-row :gutter="10" v-if="courseName !== null">
            <el-col
              :span="4"
              :offset="4"
              v-if="coursePrice !== null"
              style="text-align: center; display: inline"
              >金額：</el-col
            >
            <el-col
              :span="16"
              :offset="0"
              style="text-align: left; display: inline"
              >${{ coursePrice * quantity }}</el-col
            >
          </el-row>
          <el-row :gutter="10" v-if="kitchenwareName !== null">
            <el-col
              :span="4"
              :offset="4"
              style="text-align: center; display: inline"
              >品項名稱：</el-col
            >
            <el-col
              :span="16"
              :offset="0"
              style="text-align: left; display: inline"
              >{{ kitchenwareName }}</el-col
            >
          </el-row>
          <el-row :gutter="10" v-if="kitchenwareName !== null">
            <el-col
              :span="4"
              :offset="4"
              v-if="kitchenwarePrice !== null"
              style="text-align: center; display: inline"
              >單價：</el-col
            >
            <el-col
              :span="16"
              :offset="0"
              style="text-align: left; display: inline"
              >${{ kitchenwarePrice }}</el-col
            >
          </el-row>
          <el-row :gutter="10" v-if="kitchenwareName !== null">
            <el-col
              :span="4"
              :offset="4"
              v-if="kitchenwarePrice !== null"
              style="text-align: center; display: inline"
              >金額：</el-col
            >
            <el-col
              :span="16"
              :offset="0"
              style="text-align: left; display: inline"
              >${{ kitchenwarePrice * quantity }}</el-col
            >
          </el-row>
          <el-row :gutter="10">
            <el-col
              :span="4"
              :offset="4"
              style="text-align: center; display: inline"
              >數量：</el-col
            >
            <el-col
              :span="16"
              :offset="0"
              style="text-align: left; display: inline"
              >{{ quantity }}</el-col
            >
          </el-row>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6" :offset="18" style="font-size: 20px"
          >總金額: ${{ total }}</el-col
        >
      </el-row>
      <el-divider>
        <el-icon><star-filled /></el-icon>
      </el-divider>
      <el-row>
        <el-col :span="4" :offset="14">收件人:</el-col>
        <el-col :span="6" :offset="0" style="align-items: start">{{
          memberStorage.memberName
        }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="4" :offset="14">電話:</el-col>
        <el-col :span="6" :offset="0" style="align-items: start">{{
          memberStorage.cellphone
        }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="4" :offset="14">收件地:</el-col>
        <el-col :span="6" :offset="0" style="align-items: start">{{
          memberStorage.address
        }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="4" :offset="14">運費:</el-col>
        <el-col :span="6" :offset="0" style="align-items: start">$120</el-col>
      </el-row>
      <el-row>
        <el-col :span="4" :offset="14">訂單金額(含運費):</el-col>
        <el-col :span="6" :offset="0" style="align-items: start"
          >${{ total + 120 }}</el-col
        >
      </el-row>
      <el-row>
        <el-col :span="4" :offset="14">付款方式:</el-col>
        <el-col :span="6" :offset="0" style="align-items: start"
          >貨到付款</el-col
        >
      </el-row>
      <el-col :span="4" :offset="20"
        ><el-button
          type="primary"
          plain
          round
          @click="
            async () => {
              await createOrderList();
              await removePurchasedProducts();
              await removePurchasedItems();
            }
          "
          >確認</el-button
        >
      </el-col>
    </el-card>

    <!-- old -->
    <!-- <div class="card p-3">
      <div
        class="row"
        v-for="(
          {
            courseName,
            kitchenwareName,
            coursePrice,
            kitchenwarePrice,
            quantity,
            checked,
            cartProductId,
            kitchenwareImg,
            courseImg,
          },
          index
        ) in products"
        :key="index"
      >
        <div class="col-1 p-3"></div>
        <div class="col-3 p-3">
          <img
            v-if="courseImg !== null"
            :src="courseImg"
            style="width: 90%"
            alt=""
          />
          <img
            v-if="kitchenwareImg !== null"
            :src="kitchenwareImg"
            style="width: 90%"
            alt=""
          />
        </div>
        <div class="col-6 p-3">
          <div v-if="courseName !== null">品項名稱: {{ courseName }}</div>
          <div v-if="coursePrice !== null">單價: ${{ coursePrice }}</div>
          <div v-if="coursePrice !== null">
            金額: ${{ coursePrice * quantity }}
          </div>
          <div v-if="kitchenwareName !== null">
            品項名稱: {{ kitchenwareName }}
          </div>
          <div v-if="kitchenwarePrice !== null">
            單價: ${{ kitchenwarePrice }}
          </div>
          <div v-if="kitchenwarePrice !== null">
            金額: ${{ kitchenwarePrice * quantity }}
          </div>

          <div style="float: left">數量：{{ quantity }}</div>
        </div>
      </div>

      <div class="row">
        <div class="col-9 p-3"></div>
        <div class="col-3">
          <div @click="" style="font-size: 20px">total: ${{ total }}</div>
        </div>
      </div>
      虛線
      <hr
        style="
          border: 0;
          padding-top: 3px;
          background: repeating-linear-gradient(
            to right,
            black 0px,
            black 8px,
            transparent 0px,
            transparent 10px
          );
        "
      />
      <div class="row">
        <div class="col-6 p-3"></div>
        <div class="col-4 p-3">
          <div>收件人: {{ memberStorage.memberName }}</div>
          <div>電話: {{ memberStorage.cellphone }}</div>
          <div>收件地: {{ memberStorage.address }}</div>
          <div>運費: $120</div>
          <div>總金額: ${{ total }}</div>
          <br />
          <div>付款方式: 貨到付款</div>
        </div>
        <div class="col-2 p-3"></div>
      </div>

      <div class="row">
        <div class="col-10 p-3"></div>
        <div class="col-2">
          <input
            type="button"
            class="btn btn-outline-warning"
            value="確認"
            @click="
              async () => {
                await createOrderList();
                await removePurchasedProducts();
                await removePurchasedItems();
              }
            "
          />
        </div>
      </div>
    </div> -->
  </div>
</template>

<script setup>
import { useCookie } from "vue-cookie-next";
import { useStorage } from "@vueuse/core";
import { ref, computed } from "vue";
import router from "../router";
import axios from "axios";
import { StarFilled } from "@element-plus/icons-vue";

const URL = import.meta.env.VITE_API_JAVAURL;

const cookie = useCookie();
// const jsonProductsCookie = ref(cookie.getCookie("productsCookie"))
// const products = ref(JSON.parse(jsonProductsCookie.value))
const member = ref(cookie.getCookie("cookie"));

// const members = ref(JSON.parse(jsonCookie.value))
// console.log(jsonCookie.value);

// const storage = useStorage();
const jsonProductsStorage = useStorage("productsStorage");
const jsonMemberStorage = useStorage("member");
const products = ref(JSON.parse(jsonProductsStorage.value));
const memberStorage = ref(JSON.parse(jsonMemberStorage.value));
console.log("products", products.value);
console.log("memberStorage", memberStorage.value);
// console.log(Array.isArray(test.value));
// console.log(jsonProductsStorage.value[0]);
// console.log(typeof jsonProductsStorage.value);

// console.log("order.value[0].cartProductId: "+order.value[0].cartProductId);

const total = computed(() => {
  let sum = 0;
  products.value.forEach((element) => {
    if (element.courseId) {
      //condition只要不等於0就會動(JS only)
      sum += element.coursePrice * element.quantity;
    } else {
      sum += element.kitchenwarePrice * element.quantity;
    }
  });

  return sum;
});

const createOrderList = async () => {
  const jsonProductsStorage = useStorage("productsStorage");
  // console.log(jsonProductsStorage.value);
  const test = ref(JSON.parse(jsonProductsStorage.value));
  // console.log(test.value);

  if (!cookie.isCookieAvailable("cookie")) {
    return;
  }

  let orderBean = {
    memberId: cookie.getCookie("cookie").memberNumber,
    note: jsonProductsStorage.value,
  };

  const URLAPI = `${URL}order/create`;
  let response = await axios.post(URLAPI, orderBean);

  console.log("response" + response);

  router.push({ path: "/paid" });
};

const removePurchasedProducts = async () => {
  products.value.forEach(async (element) => {
    console.log("cartProductId", element.cartProductId);

    const URLAPI = `${URL}cartproduct/removeall/${element.cartProductId}`;
    let response = await axios.delete(URLAPI);
  });
};

const removePurchasedItems = () => {
  console.log("products.value", products.value);
  products.value.forEach(async (element) => {
    // console.log("kitchenwareId", element.kitchenwareId);
    // console.log("courseId", element.courseId);
    if (element.kitchenwareId == null) {
      element.kitchenwareId = 0;
      const URLAPI = `${URL}item/remove/${element.kitchenwareId}/${element.courseId}`;
      let response = await axios.delete(URLAPI);
    } else {
      element.courseId = 0;
      const URLAPI = `${URL}item/remove/${element.kitchenwareId}/${element.courseId}`;
      let response = await axios.delete(URLAPI);
    }
  });
};
</script>

<style scoped></style>
