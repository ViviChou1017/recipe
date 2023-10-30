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
const courseName = ref(null); //課程名稱
const videoUrl = ref(null); //課程影片
const content = ref(null); //課程內容
const chef = ref(null);
const coursePrice = ref(null);

const requestBody = reactive({
  courseId: null,
});

requestBody.courseId = id.value;
axios
  .post(`${contextPath}pages/ajax/course/findById`, requestBody)
  .then(function (response) {
    console.log("findById成功", response);
    console.log("課程", response.data.CourseName);
    courseName.value = response.data.CourseName;
    coursePrice.value = response.data.coursePrice;
    //上面宣告許多變數，然後把變數的值用response.data設值給他們這樣就可以在底下的html顯示
    videoUrl.value = response.data.courseVideo;
    content.value = response.data.courseContent;
    chef.value = response.data.chef;
  })
  .catch(function (error) {
    console.log("findById失敗", error);
  })
  .finally(function () {});


  //Sharon
///加入購物車按鈕的 課程加入item
const createCourseItem = async (courseId) => {
  // if(!cookie.isCookieAvailable('cookie')){
  //       return
  //   }
  console.log(courseId);

  let itemBean = {
    courseId: courseId,
  };

  const URLAPI = `${contextPath}item/create`;
  let response = await axios.post(URLAPI, itemBean);

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

//加入購物車按鈕的 item加入cartProduct

const createCartProduct = async (courseId) => {
  console.log("items.value", items.value);

  items.value.forEach(async (element) => {
    if (element.courseId == courseId) {
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

  // console.log("cart.value"+cart.value)

  // router.push({path:"/paid"})
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
</script>

<template>
  <!-- <h3>id={{id}}</h3>完成後刪除，檢查作用 -->
  <div class="row" style="margin-top: 50px">
    <el-col :span="12">
      <el-card
        :body-style="{ padding: '0px 0px 0px 0px', width: '100%' }"
        :style="{ width: '100%' }"
      >
        <iframe class="video" :src="videoUrl"></iframe>
      </el-card>
    </el-col>

    <el-col :span="12">
      <el-card>
        <h1 style="font-weight: bolder">{{ courseName }}</h1>
        <p style="margin-top: 20px; font-size: 25px">{{ content }}</p>
        <p style="font-size: 20px; color: brown">
          價格:&nbsp;NT{{ coursePrice }}元
        </p>
        <br />
        <br />
        <el-row :gutter="20">
          <el-col
            :span="12"
            style="text-align: center; align-items: center; display: flex"
          >
            <el-text size="large"> 授課主廚&nbsp:&nbsp&nbsp{{ chef }} </el-text>
          </el-col>
          <el-col :span="12">
            <span><el-button round type="primary" @click="
                async () => {
                  await createCourseItem(requestBody.courseId);
                  await createCart();
                  await loadItems();
                  await loadCart();
                  await createCartProduct(requestBody.courseId);
                }
              ">加入購物車</el-button></span>
          </el-col>
        </el-row>

        <!--看要怎麼樣寫一個方法觸發@click事件連接購物車-->
      </el-card>
    </el-col>
  </div>
</template>
<style scoped>
.video {
  width: 100%;
  height: 340px;
}
</style>
