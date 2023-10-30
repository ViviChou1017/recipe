<template>
  <h1>購買清單</h1>
      <el-space fill wrap style="padding-top: 1%; width: 100% ">
        <!-- orders的值 -->
        <el-card v-for="({memberId, id, note}) in orders" :key="id" class="box-card">
          <template #header>
            <div class="card-header">
              <span>訂單編號: {{ id }}</span>
            </div>
          </template>
          <!-- products的值 -->
          <div v-for="({courseName,
              kitchenwareName,
              coursePrice,
              kitchenwarePrice,
              quantity,
              checked,
              cartProductId,
              kitchenwareImg,
              courseImg,}, index) in JSON.parse(note)" :key="index" class="text item">
              <el-divider v-if="index != 0">
                <el-icon><star-filled /></el-icon>
              </el-divider>
              <!-- <hr
                v-if="index != 0"
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
              /> -->
              <el-row>
                <el-col :span="6" :offset="2">
                  <img
                    v-if="courseImg !== null"
                    :src="courseImg"
                    style="width: 90%;"
                    alt=""
                  />
                  <img
                    v-if="kitchenwareImg !== null"
                    :src="kitchenwareImg"
                    style="width: 90%"
                    alt=""
                  />
                </el-col>
                <el-col :span="14" style="display: block;">
                  <el-row :gutter="10" v-if="courseName !== null">
                    <el-col :span="4" :offset="4" style="text-align: center; display: block;">品項名稱：</el-col>
                    <el-col :span="16"  style="text-align: left; display: inline;">{{ courseName }}</el-col>
                  </el-row>
                  <el-row :gutter="10" v-if="courseName !== null">
                    <el-col :span="4" :offset="4" v-if="coursePrice !== null" style="text-align: center; display: inline;">單價：</el-col>
                    <el-col :span="16" :offset="0" style="text-align: left; display: inline;">${{ coursePrice }}</el-col>
                  </el-row>
                  <el-row :gutter="10" v-if="courseName !== null">
                    <el-col :span="4" :offset="4" v-if="coursePrice !== null" style="text-align: center; display: inline;">金額：</el-col>
                    <el-col :span="16" :offset="0" style="text-align: left; display: inline;">${{ coursePrice * quantity }}</el-col>
                  </el-row>
                  <el-row :gutter="10" v-if="kitchenwareName !== null">
                    <el-col :span="4" :offset="4" style="text-align: center; display: inline;">品項名稱：</el-col>
                    <el-col :span="16" :offset="0" style="text-align: left; display: inline;">{{ kitchenwareName }}</el-col>
                  </el-row>
                  <el-row :gutter="10" v-if="kitchenwareName !== null">
                    <el-col :span="4" :offset="4" v-if="kitchenwarePrice !== null" style="text-align: center; display: inline;">單價：</el-col>
                    <el-col :span="16" :offset="0" style="text-align: left; display: inline;">${{ kitchenwarePrice }}</el-col>
                  </el-row>
                  <el-row :gutter="10" v-if="kitchenwareName !== null">
                    <el-col :span="4" :offset="4" v-if="kitchenwarePrice !== null" style="text-align: center; display: inline;">金額：</el-col>
                    <el-col :span="16" :offset="0" style="text-align: left; display: inline;">${{ kitchenwarePrice * quantity }}</el-col>
                  </el-row>
                  <el-row :gutter="10">
                    <el-col :span="4" :offset="4" style="text-align: center; display: inline;">數量：</el-col>
                    <el-col :span="16" :offset="0" style="text-align: left; display: inline;">{{ quantity }}</el-col>
                  </el-row>

                  
                  
                  <!-- <div v-if="courseName !== null">品項名稱: <span>{{ courseName }}</span></div>
                  <div v-if="coursePrice !== null">單價: $<span>{{ coursePrice }}</span></div>
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

                  <div style="float: left">數量：{{ quantity }}</div> -->
                </el-col>
              </el-row>
          </div>
        </el-card>
      </el-space>

      <!-- <div class="row" v-for="({id,note}) in orders">
        {{ id }}

        <div class="col" v-for="({cartProductId}) in JSON.parse(note)">
          {{ cartProductId }}
        </div>
      </div> -->

</template>

<script setup>
import { useStorage } from "@vueuse/core";
import { ref, reactive, computed, onMounted } from "vue";
import { useCookie } from "vue-cookie-next";
import axios from "axios";
import { StarFilled } from "@element-plus/icons-vue";

const URL = import.meta.env.VITE_API_JAVAURL;
const orders = ref([]);
const products = ref([]);

//cookie
const cookie = useCookie();
// const jsonProductsCookie = ref(cookie.getCookie("productsCookie"));
// const products = ref(JSON.parse(jsonProductsCookie.value));
const member = ref(cookie.getCookie("cookie"));

//local storage
// const jsonProductsStorage = useStorage("productsStorage");
// const test = ref(JSON.parse(jsonProductsStorage.value));

const loadOrderList = async () => {
  const URLAPI = `${URL}order/findallbymemberid/${member.value.memberNumber}`;
  let response = await axios.get(URLAPI);

  if (response.status == 200) {
    orders.value = response.data;
    console.log(orders.value)
    
    // response.data.forEach(element => {

    //   products.value.push(JSON.parse(element.note));
    // });
  }
};

//載入時就跑
onMounted(() => {
  loadOrderList(member.value.memberNumber);
});
</script>

<style scoped></style>
