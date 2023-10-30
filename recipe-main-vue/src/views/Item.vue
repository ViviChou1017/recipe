<script setup>
import axios from "axios";
import { useRoute } from "vue-router";
import { ref, reactive } from "vue";

// 以下Sharon加入
import { useCookie } from "vue-cookie-next";

// cookie裡面有東西可以用
const cookie = useCookie();
const member = ref(cookie.getCookie("cookie"));
// 以上Sharon加入

const contextPath = import.meta.env.VITE_API_JAVAURL;
const route = useRoute();
const id = ref(route.params.id); //取得前一頁的id，應該可以拿id來查詢資料，然後更新頁面內容
const imgs = ref(null);
const kitchenwareName = ref(null);
const content = ref(null);
const price = ref(null);
const brand = ref(null);
const material = ref(null);
const origin = ref(null);

const requestBody = reactive({
  kitchenwareId: null,
});

//文字資料
requestBody.kitchenwareId = id.value;
axios
  .post(`${contextPath}pages/ajax/kitchenware/findById`, requestBody)
  .then(function (response) {
    console.log("廚具findById成功", response.data);
    kitchenwareName.value = response.data["kitchenware_name "];
    content.value = response.data["kitchenware_content "];
    price.value = response.data["kitchenware_price "];
    brand.value = response.data["kitchenware_brand "];
    material.value = response.data["kitchenware_material "];
    origin.value = response.data["kitchenware_origin "];
  })
  .catch(function (error) {
    console.log("廚具findById失敗", error);
  })
  .finally(function () {});

//圖片資料
axios
  .post(`${contextPath}pages/ajax/kitchenimg/findByKitchwareId`, requestBody)
  .then(function (response) {
    console.log("圖片查詢成功", response.data);
    imgs.value = response.data;
  })
  .catch(function (error) {
    console.log("圖片查尋失敗", error);
  })
  .finally(function () {});

  //Sharon
  //加入購物車按鈕的 廚具加入item
const createKitchenwareItem = async (kitchenwareId) => {
  //   if(!cookie.isCookieAvailable('cookie')){
  //         return
  //     }
  console.log(kitchenwareId);
  let itemBean = {
    kitchenwareId: kitchenwareId,
  };

  const URLAPI = `${contextPath}item/create`;
  let response = await axios.post(URLAPI, itemBean);

  console.log("response" + response);

  // router.push({path:"/paid"})
};

//加入購物車按鈕的 創建購物車
const createCart = async () => {
  if (!cookie.isCookieAvailable("cookie")) {
    return;
  }

  let cartBean = {
    userAccountId: cookie.getCookie("cookie").memberNumber,
  };

  const URLAPI = `${contextPath}cart/create`;
  let response = await axios.post(URLAPI, cartBean);

  console.log("response" + response);

  // router.push({path:"/paid"})
};

//跑出加入item的全部商品
const items = ref([]);
const loadItems = async () => {
  const URLAPI = `${contextPath}item/findall`;
  let response = await axios.get(URLAPI);

  if (response.status == 200) {
    items.value = response.data;
    console.log(items.value);
  }
};

//找購物車
const cart = ref([]);
const loadCart = async () => {
  const URLAPI = `${contextPath}cart/findall`;
  let response = await axios.get(URLAPI);

  if (response.status == 200) {
    cart.value = response.data;
    console.log("cart.value", cart.value);
  }
};

//加入購物車按鈕的 item加入cartProduct

const createCartProduct = async (kitchenwareId) => {
  //   console.log("items.value",items.value);

  items.value.forEach(async (element) => {
    if (element.kitchenwareId == kitchenwareId) {
      cart.value.forEach(async (cart) => {
        if (cart.userAccountId == cookie.getCookie("cookie").memberNumber) {
          let cartProductBean = {
            cartId: cart.id,
            productId: element.itemId,
            quantity: 1,
          };
          const URLAPI = `${contextPath}cartproduct/create`;
          let response = await axios.post(URLAPI, cartProductBean);
          console.log("cartProductBean success");
        }
      });
    }
  });
};
</script>

<template>
  <div class="row" style="margin-top: 50px">
    <div class="col">
      <el-card>
        <div
          id="carouselExampleIndicators"
          class="carousel slide"
          data-bs-ride="carousel"
        >
          <div class="carousel-inner">
            <div
              class="carousel-item active"
              v-for="img in imgs"
              data-bs-interval="1000"
            >
              <img
                :src="img.kitchenwareImg"
                class="d-block w-100"
                alt="..."
                style="height: 300px"
              />
            </div>
          </div>
          <button
            class="carousel-control-prev"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </el-card>
    </div>

    <div class="col" style="margin-left: 20px">
      <el-card>
        <h1 style="font-weight: bolder">{{ kitchenwareName }}</h1>
        <p style="margin-top: 20px; font-size: 25px">{{ content }}</p>
        <p style="color: brown">價格:&nbsp;{{ price }}</p>
        <p style="color: blue">品牌:&nbsp{{ brand }}</p>
        <p style="color: purple">材質:&nbsp{{ material }}</p>
        <p style="color: green">產地:&nbsp{{ origin }}</p>
        <span
          ><el-button type="primary" size="large" round @click="
                    async () => {
                      await createKitchenwareItem(requestBody.kitchenwareId);
                      await createCart();
                      await loadItems();
                      await loadCart();
                      await createCartProduct(requestBody.kitchenwareId);
                    }
                  "
            >加入購物車</el-button
          ></span
        >
        <!--看要怎麼樣寫一個方法觸發@click事件連接購物車-->
      </el-card>
    </div>
  </div>
</template>

<style scoped>
p {
  font-size: 20px;
}
</style>
