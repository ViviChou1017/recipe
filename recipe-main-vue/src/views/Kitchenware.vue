<script setup>
// 以下立新加入
import SearchBar from "../components/SearchBar.vue";
import Advertisement from "../components/Advertisement.vue";
const currentPage = ref();
// 以上立新加入
import axios from "axios";
import { ref, reactive, onMounted } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";

// 以下Sharon加入
import { useCookie } from "vue-cookie-next";

// cookie裡面有東西可以用
const cookie = useCookie();
const member = ref(cookie.getCookie("cookie"));
// 以上Sharon加入

const contextPath = import.meta.env.VITE_API_JAVAURL;

const kitchenwares = ref([]); //顯示廚具結果
const totalCount = ref(null); //資料總比數
const totalPages = ref(null); //總頁數
const small = ref(0); //顯示品牌資料數
const port = ref(0); //顯示品牌資料數
const french = ref(0); //顯示品牌資料數
const wow = ref(0); //顯示品牌資料數

const brand = ref(null); //品牌的選擇
const material = ref(null); //材質的選擇
const origin = ref(null); //產地的選擇
const price = ref(null); //價格的選擇
const name = ref(null); //名字選擇的結果

const requestBody = reactive({
  kitchenwareId: null,
  kitchenwareName: null,
  kitchenwareCreateDate: null,
  kitchenwareContent: null,
  kitchenwarePrice: null,
  kitchenwareBrand: null,
  kitchenwareMaterial: null,
  kitchenwareOrigin: null,
  kitchenwareImgId: null,
  kitchenwareImg: null,
  sort: null,
  order: null,
  start: null,
  rows: 6,
});

//透過條件查詢結果(沒設條件就是查全部)
function doFind(page) {
  requestBody.kitchenwareBrand = brand.value;
  requestBody.kitchenwareMaterial = material.value;
  requestBody.kitchenwareOrigin = origin.value;
  requestBody.kitchenwarePrice = price.value;
  requestBody.kitchenwareName = name.value;
  //如果沒有更改頁數，就從第一筆資料開始顯示
  if (page != null) {
    requestBody.start = requestBody.rows * (page - 1);
  }

  axios
    .post(`${contextPath}pages/ajax/kitchenware/find`, requestBody)
    .then(function (response) {
      //   console.log("透過條件查詢結果成功", response.data);
      kitchenwares.value = response.data.list;
      totalCount.value = response.data.count;

      //怕用"/"的會有小數點，所以要用Math.ceil()取整數
      totalPages.value = Math.ceil(totalCount.value / requestBody.rows);

      //把廚具圖片跟廚具文字資料彙總
      const promises = kitchenwares.value.map(function (kitchenware) {
        //對dofind()查到的結果做一些事用map
        return dofindImg(kitchenware.kitchenwareId).then(function (imageData) {
          kitchenware.imageData = imageData; // 將圖片數據添加到kitchenware物件中
          //   console.log("有沒有", kitchenware.imageData[0].kitchenwareImg); //檢查kitchenware.imageData[0].kitchenwareImg是否存在，發現kitchenware.imageData是一個陣列
        });
      });
      return Promise.all(promises); // 等待所有的圖片請求完成
    })
    .catch(function (error) {
      console.log("透過條件查詢結果失敗", error);
    });
}

//找圖片
function dofindImg(kitchenwareId) {
  requestBody.kitchenwareId = kitchenwareId;

  return axios
    .post(`${contextPath}pages/ajax/kitchenimg/findByKitchwareId`, requestBody)
    .then(function (response) {
      // console.log("查詢照片成功",response.data);
      return response.data;
    })
    .catch(function (error) {
      console.log("查詢照片失敗", error);
    });
}

//清除選擇的選項，同時顯示所有結果
function doClear() {
  brand.value = null;
  material.value = null;
  name.value = null;
  price.value = null;
  origin.value = null;
  doFind();
}

//計算每個
function dofilter() {
  axios
    .get(`${contextPath}pages/ajax/kitchenware/findAll`)
    .then(function (response) {
      //   console.log("dofilter成功", response.data);
      for (var i = 0; i < response.data.length; i++) {
        if (response.data[i].kitchenwareBrand === "小禮堂") {
          small.value++;
        } else if (response.data[i].kitchenwareBrand === "CookPower鍋寶") {
          port.value++;
        } else if (response.data[i].kitchenwareBrand === "法國Staub") {
          french.value++;
        } else if (response.data[i].kitchenwareBrand === "渥思") {
          wow.value++;
        }
      }
    })
    .catch(function (error) {
      console.log("dofilter失敗", error);
    })
    .finally(function () {});
}

//加入購物車按鈕的 廚具加入item
const createKitchenwareItem = async (kitchenwareId) => {
  //   if(!cookie.isCookieAvailable('cookie')){
  //         return
  //     }
  console.log(kitchenwareId);
  let itemBean = {
    kitchenwareId: kitchenwareId,
  };
  console.log(itemBean);

  const URLAPI = `${contextPath}item/create`;
  let response = await axios.post(URLAPI, itemBean);
  // if(response.data == null){
  // }
  
  console.log("未加入",response.data)
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
          // ElMessage({
          //   message: '加入購物車',
          //   type: 'success',
          // })
        }
      });
    }
  });
};

onMounted(function () {
  doFind();
  dofilter();
});

const test = async () => {
  await createKitchenwareItem(kitchenware.kitchenwareId);
  await createCart();
  // await loadItems();
  // await loadCart();
  // await createCartProduct(kitchenware.kitchenwareId);
};
</script>
<template>
  <el-row type="flex" :gutter="10" justify="center">
    <!-- 以下為新介面 -->
    <el-col :span="24" :offset="0">
      <Advertisement></Advertisement>
    </el-col>

    <el-col :span="24">
      <SearchBar :active-name="'kitchenware'" style="width: 100%">
        <template v-slot:kitchenware>
          <el-form style="max-width: 100%" label-position="top" :inline="true">
            <el-form-item label="名稱搜尋">
              <el-input
                type="text"
                placeholder="請輸入廚具名稱"
                v-model="name"
                size="large"
              />
            </el-form-item>
          </el-form>
        </template>
      </SearchBar>
    </el-col>

    <!-- <el-col :span="6" v-for="(recipe, index) in recipes" :key="recipe.id">
      <el-card
        :body-style="{ padding: '0px 0px 3% 0px', width: '100%' }"
        :style="{ width: '100%' }"
      >
        <RouterLink :to="{ name: 'SingleRecipe', params: { id: recipe.id } }">
          <el-image
            style="width: 100%; height: 200px"
            :src="images[index]"
            fit="cover"
          >
            <template #error>
              <el-col :span="24" :offset="0" style="align-content: center">
              </el-col>
            </template>
          </el-image>
        </RouterLink>
        <el-row :gutter="20">
          <el-col :span="24">
            <RouterLink
              :to="{ name: 'SingleRecipe', params: { id: recipe.id } }"
              >{{ recipe.recipeName }}</RouterLink
            >
          </el-col>
        </el-row>
      </el-card>
    </el-col>

    <el-col :span="24" :offset="0">
      <el-pagination
        layout="prev, pager, next"
        :page-count="totalPage"
        :size="totalPage"
        @current-change="
          console.log(totalPage);
          console.log(currentPage);
          changePage(currentPage);
        "
        v-model:currentPage="currentPage"
      />
    </el-col> -->
  </el-row>

  <el-row :gutter="20"> </el-row>

  <!-- 以上為新介面 -->

  <div class="row">
    <div class="col-2" style="margin-right: 8px; text-align: start">
      <h5 style="margin-top: 10px">
        <i class="bi bi-funnel-fill"></i>條件篩選
      </h5>

      <div v-if="false">
        <label class="brand">廚具名稱</label>
        <el-input
          type="text"
          placeholder="請輸入廚具名稱"
          v-model="name"
          style="margin-top: 5px"
        />
        <hr class="hori-line" />
      </div>
      <div>
        <label class="brand">品牌</label>
        <br />
        <label
          ><input
            type="radio"
            name="brand"
            value="小禮堂"
            v-model="brand"
          />&nbsp 小禮堂<span>({{ small }})</span></label
        >
        <br />
        <label
          ><input
            type="radio"
            name="brand"
            value="CookPower鍋寶"
            v-model="brand"
          />&nbsp CookPower鍋寶<span>({{ port }})</span></label
        >
        <br />
        <label
          ><input
            type="radio"
            name="brand"
            value="法國Staub"
            v-model="brand"
          />&nbsp 法國Staub<span>({{ french }})</span></label
        >
        <br />
        <label
          ><input type="radio" name="brand" value="渥思" v-model="brand" />&nbsp
          渥思<span>({{ wow }})</span></label
        >
        <hr class="hori-line" />
      </div>
      <div>
        <label class="brand">材質</label>
        <br />
        <label
          ><input
            type="radio"
            name="material"
            value="不鏽鋼"
            v-model="material" />&nbsp 不鏽鋼<span></span
        ></label>
        <br />
        <label
          ><input
            type="radio"
            name="material"
            value="陶瓷"
            v-model="material"
          />&nbsp 陶瓷</label
        >
        <br />
        <label
          ><input
            type="radio"
            name="material"
            value="塑料/樹脂"
            v-model="material"
          />&nbsp 塑料/樹脂</label
        >
        <br />
        <label
          ><input
            type="radio"
            name="material"
            value="鑄鐵"
            v-model="material"
          />&nbsp 鑄鐵</label
        >
        <hr class="hori-line" />
      </div>
      <div>
        <label class="brand">產地</label>
        <br />
        <label
          ><input
            type="radio"
            name="origin"
            value="中國"
            v-model="origin" />&nbsp 中國<span></span
        ></label>
        <br />
        <label
          ><input
            type="radio"
            name="origin"
            value="日本"
            v-model="origin"
          />&nbsp 日本</label
        >
        <br />
        <label
          ><input
            type="radio"
            name="origin"
            value="台灣"
            v-model="origin"
          />&nbsp 台灣</label
        >
        <br />
        <label
          ><input
            type="radio"
            name="origin"
            value="法國"
            v-model="origin"
          />&nbsp 法國</label
        >
        <hr class="hori-line" />
      </div>
      <div>
        <label class="brand">價格(以內)</label>
        <br />
        <input type="range" min="1" max="20000" v-model="price" />
        <br />
        <span>{{ price }}</span>
      </div>

      <el-button type="primary" @click="doFind()">查詢</el-button>
      <el-button type="primary" @click="doClear()" style="margin-left: 15px">
        刪除全部
      </el-button>
    </div>

    <!-- 商品顯示 -->
    <div class="col-9">
      <div class="row row-cols-1 row-cols-md-3 g-4" style="margin-top: 20px">
        <div
          class="col"
          v-for="(kitchenware, index) in kitchenwares"
          :key="kitchenware.kitchenwareId"
        >
          <div class="card-group">
            <el-card class="card h-100 bg-light mb-3" style="width: 18rem">
              <!-- <img class="card-img-top" v-for="img in kitchenware.imageData"  alt="..." :src="img.kitchenwareImg" style=" height:200px">因為kitchenware.imageDatag是陣列，所以也要再跑一次迴圈 -->
              <!-- 圖片顯示+輪播效果   -->
              <div
                :id="'carousel-' + index"
                class="carousel slide"
                data-bs-ride="carousel"
              >
                <div class="carousel-inner">
                  <div
                    class="carousel-item"
                    :class="{ active: imgIndex === 0 }"
                    v-for="(img, imgIndex) in kitchenware.imageData"
                    data-bs-interval="1000"
                  >
                    <img
                      :src="img.kitchenwareImg"
                      class="d-block w-100"
                      alt="..."
                      style="height: 150px"
                    />
                  </div>
                </div>
                <button
                  class="carousel-control-prev"
                  type="button"
                  :data-bs-target="'#carousel-' + index"
                  data-bs-slide="prev"
                >
                  <span
                    class="carousel-control-prev-icon"
                    aria-hidden="true"
                  ></span>
                  <span class="visually-hidden">Previous</span>
                </button>
                <button
                  class="carousel-control-next"
                  type="button"
                  :data-bs-target="'#carousel-' + index"
                  data-bs-slide="next"
                >
                  <span
                    class="carousel-control-next-icon"
                    aria-hidden="true"
                  ></span>
                  <span class="visually-hidden">Next</span>
                </button>
              </div>

              <div class="card-body">
                <h5 class="card-title">
                  <RouterLink
                    :to="{
                      name: 'Item',
                      params: { id: kitchenware.kitchenwareId },
                    }"
                    >{{ kitchenware.kitchenwareName }}</RouterLink
                  >
                </h5>
                <p class="card-text">
                  新增日期:{{ kitchenware.kitchenwareCreateDate }}
                </p>
                <p class="card-text">價格:{{ kitchenware.kitchenwarePrice }}</p>
                <p class="card-text">{{ kitchenware.kitchenwareContent }}</p>
                <el-button
                  round
                  class="btn btn-primary"
                  type="primary"
                  @click="
                    async () => {
                      await createKitchenwareItem(kitchenware.kitchenwareId);
                      await createCart();
                      await loadItems();
                      await loadCart();
                      await createCartProduct(kitchenware.kitchenwareId);
                    }
                  "
                >
                  加入購物車
                </el-button>
                <!-- 跟Lesson那頁一樣看到時候要怎麼連購物車 -->
                <!-- <p>{{kitchenware.kitchenwareId}}</p>用以確認正確性，之後要刪除 -->
              </div>
            </el-card>
          </div>
        </div>
      </div>

      <!-- 分頁 -->
      <nav aria-label="..." style="margin-top: 15px">
        <ul class="pagination">
          <li
            class="page-item"
            v-for="(page, index) in totalPages"
            :key="index"
          >
            <a class="page-link" href="#" @click.prevent="doFind(page)">{{
              page
            }}</a>
          </li>
        </ul>
      </nav>
    </div>

    <!-- 跟Lesson那頁一樣看到時候要怎麼連購物車 -->
    <!-- <p>{{kitchenware.kitchenwareId}}</p>用以確認正確性，之後要刪除 -->
  </div>
</template>

<style scoped>
span {
  margin-left: 5px;
}
.brand {
  color: blue;
}
a {
  font-size: 20px;
  text-decoration: none;
}
a:hover {
  font-size: 40px;
  color: green;
}

p {
  margin-top: 5px;
}
</style>
