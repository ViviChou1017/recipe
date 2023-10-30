<script setup>
// 以下立新加入
import SearchBar from "../components/SearchBar.vue";
import Advertisement from "../components/Advertisement.vue";
import { ShoppingTrolley } from "@element-plus/icons-vue";
const currentPage = ref();

// 以上立新加入

import axios from "axios";

import "v-calendar/dist/style.css";
import { ref, reactive, onMounted } from "vue";

// 以下Sharon加入
import { useCookie } from "vue-cookie-next";
// cookie裡面有東西可以用
const cookie = useCookie();
const member = ref(cookie.getCookie("cookie"));
// 以上Sharon加入

const contextPath = import.meta.env.VITE_API_JAVAURL;
const courses = ref([]); //顯示課程結果
const totalPages = ref(null); //總頁數
const pageSize = ref(8); //一頁有幾筆資料
const currentSearchType = ref(""); // 可以是 "name" 或 "chef" //給分頁功能使用，目前是依據什麼做查詢
const findByName = ref(null); //課程名稱
const findChef = ref("依廚師"); //廚師名稱
const sildValue = ref(null); //價錢數字條顯示的對應
const sildValueIsShow = ref(false); //價錢數字條是否顯示
const selectedDate = ref(new Date().toISOString()); //所選擇的日期(都抓當天日期)

const requestBody = reactive({
  courseName: null,
  chef: null,
  coursePrice: null,
  courseCreateDate: null,
  pageNumber: null,
  pageSize: pageSize.value,
});

function doFindAll(value) {
  if (requestBody.pageNumber === null) {
    requestBody.pageNumber = 1;
  } else {
    requestBody.pageNumber = value;
  }
  currentSearchType.value = "all";

  axios
    .post(`${contextPath}pages/ajax/course/finds`, requestBody)
    .then(function (respones) {
      console.log("FindAll成功", respones);
      courses.value = respones.data;
    })
    .catch(function (error) {
      console.log("FindAll失敗", error);
    })
    .finally(function () {});

  axios
    .get(`${contextPath}pages/ajax/course/finds/count`)
    .then(function (response) {
      console.log("FindAll總比數", response);
      totalPages.value = Math.ceil(response.data / pageSize.value); //總比數 / 一頁幾筆資料 = 總頁數
    })
    .catch(function (error) {
      console.log(error);
    })
    .finally(function () {
      console.log("doFindAlltest", courses.value);
    });
}

function dofindByName(value) {
  currentSearchType.value = "courseName";
  requestBody.courseName = findByName.value;
  requestBody.pageNumber = value;
  if (requestBody.courseName === "" || requestBody.courseName === null) {
    //顯示所有結果
    doFindAll(1);
  } else {
    //課程名稱資料搜尋結果
    axios
      .post(`${contextPath}pages/ajax/course/find/name`, requestBody)
      .then(function (respones) {
        courses.value = respones.data;
      })
      .catch(function (error) {
        console.log("findByName失敗", error);
      })
      .finally(function () {});

    //課程名字資料總比數
    axios
      .post(`${contextPath}pages/ajax/course/find/name/count`, requestBody)
      .then(function (respones) {
        totalPages.value = Math.ceil(respones.data / requestBody.pageSize); //總比數 / 一頁幾筆資料 = 總頁數
      })
      .catch(function (error) {
        console.log("findByName總比數失敗", error);
      })
      .finally(function () {});
  }
}

function doFindByChef(value) {
  //value是所選擇的頁數
  currentSearchType.value = "chefName";
  requestBody.chef = findChef.value;
  requestBody.pageNumber = value;
  if (requestBody.chef === "依廚師") {
    //顯示所有結果
    doFindAll(1);
  } else {
    //廚師查詢結果
    axios
      .post(`${contextPath}pages/ajax/course/filter/byChef`, requestBody)
      .then(function (response) {
        console.log("FindByChef成功", response);
        courses.value = response.data;
      })
      .catch(function (error) {
        console.log("FindByChef失敗", error);
      })
      .finally(function () {});

    //廚師查詢結果總比數
    axios
      .post(`${contextPath}pages/ajax/course/filter/byChef/count`, requestBody)
      .then(function (response) {
        console.log("FindByChef總比數", response);
        totalPages.value = Math.ceil(response.data / requestBody.pageSize); //總比數 / 一頁幾筆資料 = 總頁數
      })
      .catch(function (error) {
        console.log("FindByChef總比數失敗", error);
      })
      .finally(function () {});
  }
}

function doFindByPrice(value) {
  //控制價錢調會不會出現
  if (sildValueIsShow.value === false) {
    sildValueIsShow.value = true;
  } else {
    sildValueIsShow.value = false;
  }

  currentSearchType.value = "price";
  requestBody.coursePrice = sildValue.value;
  requestBody.pageNumber = value;
  if (requestBody.coursePrice === 0) {
    doFindAll(1);
  }
  //讓第一次按出價目條的時候也可以顯示所有查詢結果，然後如果查了第一次以後，requestBody.coursePrice就不會是空的，所以再次按出價目條時，
  //剛剛搜尋的結果也不會消失，一樣會吃到剛剛的requestBody.coursePrice的紀錄，所以一樣會顯示在螢幕上。
  if (requestBody.coursePrice != null && requestBody.coursePrice != 0) {
    //價錢查詢結果
    axios
      .post(`${contextPath}pages/ajax/course/filter/byPrice`, requestBody)
      .then(function (response) {
        console.log("FindByPrice成功", response);
        courses.value = response.data;
      })
      .catch(function (error) {
        console.log("FindByPrice失敗", error);
      })
      .finally(function () {});

    //價錢查詢結果總比數
    axios
      .post(`${contextPath}pages/ajax/course/filter/byPrice/count`, requestBody)
      .then(function (response) {
        console.log("FindByPrice總比數成功", response);
        totalPages.value = Math.ceil(response.data / requestBody.pageSize); //總比數 / 一頁幾筆資料 = 總頁數
      })
      .catch(function (error) {
        console.log("FindByPrice總比數失敗", error.response.data);
      })
      .finally(function () {});
  }
}

function doFindByDate(value) {
  currentSearchType.value = "date";
  requestBody.courseCreateDate = selectedDate.value;
  requestBody.pageNumber = value;

  //日期查詢結果
  axios
    .post(`${contextPath}pages/ajax/course/filter/byDate`, requestBody)
    .then(function (respones) {
      console.log("FindByDate成功", respones);
      courses.value = respones.data;
      console.log("selectedDate", selectedDate);
    })
    .catch(function (error) {
      console.log("FindByDate失敗", error);
    })
    .finally(function () {});

  //日期查詢總比數
  axios
    .post(`${contextPath}pages/ajax/course/filter/byDate/count`, requestBody)
    .then(function (response) {
      console.log("FindByDate總比數成功", response);
      totalPages.value = Math.ceil(response.data / requestBody.pageSize); //總比數 / 一頁幾筆資料 = 總頁數
    })
    .catch(function (error) {
      console.log("FindByDate總比數失敗", error);
    })
    .finally(function () {});
}
const formatDate = (date) => {
  return date ? new Date(date).toLocaleDateString() : "";
};

//分頁共用方法
function changePage(value) {
  if (currentSearchType.value === "courseName") {
    dofindByName(value);
  } else if (currentSearchType.value === "chefName") {
    doFindByChef(value);
  } else if (currentSearchType.value === "all") {
    doFindAll(value);
  } else if (currentSearchType.value === "price") {
    doFindByPrice(value);
  } else if (currentSearchType.value === "date") {
    doFindByDate(value);
  }
}

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

onMounted(function () {
  doFindAll();
});
</script>

<template>
  <!-- 以下新介面 -->
  <el-col :span="24" :offset="0">
    <Advertisement></Advertisement>
  </el-col>

  <el-col :span="24">
    <SearchBar :active-name="'course'" style="width: 100%">
      <template v-slot:course>
        <!-- recipe 插槽的内容放这里 -->
        <el-form style="max-width: 100%" label-position="top" :inline="true">
          <el-form-item label="依廚師篩選">
            <el-select
              v-model="findChef"
              placeholder="請選擇分類"
              size="large"
              @change="doFindByChef(1)"
            >
              <!-- <el-option
                v-for="rcategory in category"
                :key="rcategory.id"
                :value="rcategory.id"
                :label="rcategory.recipeCategory"
              /> -->
              <el-option :value="'依廚師'">依廚師</el-option>
              <el-option :value="'詹牡市'">詹牡市</el-option>
              <el-option :value="'米克斯'">米克斯</el-option>
              <el-option :value="'陳昆徨'">陳昆徨</el-option>
              <el-option :value="'邱天道'">邱天道</el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="名稱搜尋">
            <el-input
              v-model="findByName"
              placeholder="請輸入課程名稱"
              size="large"
              @input="dofindByName(1)"
            />
          </el-form-item>
        </el-form>
      </template>
    </SearchBar>
  </el-col>
  <el-col :span="24" style="align-items: center; flex-direction: row">
    <el-popover placement="bottom" :visible="sildValueIsShow" :width="160">
      <el-col :span="24" :offset="0">
        <el-text size="large">請選擇價格</el-text>
        <el-slider
          v-model="sildValue"
          :max="1000"
          :step="50"
          placement="bottom"
          :format-tooltip="
            (value) => {
              return value + '元';
            }
          "
        />
      </el-col>

      <template #reference>
        <el-button @click="doFindByPrice(1)">依價格</el-button>
      </template>
    </el-popover>
    <el-button type="button" style="margin-left: 20px" @click="doFindByDate(1)">
      依日期
    </el-button>
  </el-col>

  <el-row :gutter="20">
    <el-col :span="6" v-for="course in courses" :key="course.courseId">
      <el-card
        :body-style="{ padding: '0px 0px 3% 0px', width: '100%' }"
        :style="{ width: '100%' }"
      >
        <RouterLink :to="{ name: 'Lesson', params: { id: course.courseId } }">
          <el-image
            style="width: 100%; height: 200px"
            :src="course.courseImg"
            fit="cover"
          >
            <template #error>
              <el-col :span="24" :offset="0" style="align-content: center">
                <!-- <el-icon size="large"><icon-picture /></el-icon> -->
              </el-col>
            </template>
          </el-image>
        </RouterLink>
        <el-row :gutter="20">
          <el-col :span="24">
            <RouterLink
              :to="{ name: 'Lesson', params: { id: course.courseId } }"
            >
              <el-text type="primary" size="large" tag="b">{{
                course.courseName
              }}</el-text></RouterLink
            >
            <el-text tag="b">價格:{{ course.coursePrice }}</el-text>
            <el-text type="info"
              >新增日期:{{ formatDate(course.courseCreateDate) }}</el-text
            >
            <el-text type="info">授課廚師:{{ course.chef }}</el-text>

            <el-button
              type="primary"
              round
              :icon="ShoppingTrolley"
              @click="
                async () => {
                  await createCourseItem(course.courseId);
                  await createCart();
                  await loadItems();
                  await loadCart();
                  await createCartProduct(course.courseId);
                }
              "
              >加入購物車</el-button
            >

            <!-- 跟Lesson那頁一樣看到時候要怎麼連購物車 -->
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>
  <el-col :span="24" :offset="0">
    <el-pagination
      layout="prev, pager, next"
      :page-count="totalPages"
      :size="totalPages"
      @current-change="
        console.log(totalPages);
        console.log(currentPage);
        changePage(currentPage);
      "
      v-model:currentPage="currentPage"
    />
  </el-col>

  <!-- 以下為舊介面 -->
  <div v-if="false">
    <input
      type="search"
      placeholder="請輸入課程名稱"
      v-model="findByName"
      style="margin-top: 20px"
    />
    <button type="button" @click="dofindByName(1)">查詢</button>

    <select
      style="margin-left: 800px"
      v-model="findChef"
      @change="doFindByChef(1)"
    >
      <option>依廚師</option>
      <option>詹牡市</option>
      <option>米克斯</option>
      <option>陳昆徨</option>
      <option>邱天道</option>
    </select>

    <button type="button" style="margin-left: 20px" @click="doFindByDate(1)">
      依日期
    </button>

    <button type="button" style="margin-left: 20px" @click="doFindByPrice(1)">
      依價錢
    </button>
    <!-- 價錢的數字條 -->
    <div v-show="sildValueIsShow">
      <label style="margin-left: 1100px; margin-top: 5px">請選擇價錢: </label>
      <input
        type="range"
        min="1"
        max="1000"
        v-model="sildValue"
        style="margin-left: 1100px"
      />
      <span id="demo">{{ sildValue }}</span>
    </div>

    <!--  <table class="table">
      <thead>
      <tr>
        <th scope="col">編號</th>
        <th scope="col">課程名稱</th>
        <th scope="col">新增日期</th>-->
    <!-- <th scope="col">課程內容</th> -->
    <!--  <th scope="col">課程圖片</th>-->
    <!-- <th scope="col">課程影片</th> -->
    <!-- <th scope="col">課程價錢</th> -->
    <!-- <th scope="col">課程廚師</th> -->

    <!-- </tr> 
    </thead>
    <tbody>
      <tr v-for="course in courses" :key="course.courseId" >
        <th scope="row">{{ course.courseId }}</th>
        <th scope="row"><RouterLink :to="{name:'Lesson' , params: { id: course.courseId  }}">{{ course.courseName }}</RouterLink></th>
        <th scope="row">{{ formatDate(course.courseCreateDate) }}</th>-->
    <!-- <th scope="row">{{ course.courseContent }}</th> -->
    <!--<th scope="row"><img class="image" :src="course.courseImg"></th> 這裡面之後樣放img標籤-->
    <!-- <th scope="row"><iframe width="100px" :src=" course.courseVideo "></iframe></th>影片要用iframe顯示 -->
    <!-- <th scope="row">{{ course.coursePrice }}</th> -->
    <!-- <th scope="row">{{ course.chef }}</th> -->
    <!-- <th><button class="btn btn-primary" type="button">加入購物車</button></th> -->
    <!-- 跟Lesson那頁一樣看到時候要怎麼連購物車 -->
    <!-- </tr> 
      </tbody>
    </table>-->

    <div class="row row-cols-1 row-cols-md-3 g-4" style="margin-top: 20px">
      <div class="col" v-for="course in courses" :key="course.courseId">
        <div class="card h-100" style="width: 18rem">
          <img
            class="card-img-top"
            alt="..."
            :src="course.courseImg"
            style="height: 200px"
          /><!--這裡面之後樣放img標籤-->
          <div class="card-body">
            <h5 class="card-title">
              <RouterLink
                :to="{ name: 'Lesson', params: { id: course.courseId } }"
                >{{ course.courseName }}</RouterLink
              >
            </h5>
            <p class="card-text">
              新增日期:{{ formatDate(course.courseCreateDate) }}
            </p>
            <p class="card-text">價格:{{ course.coursePrice }}</p>
            <p class="card-text">授課廚師:{{ course.chef }}</p>
            <button class="btn btn-primary" type="button">加入購物車</button>
            <!-- 跟Lesson那頁一樣看到時候要怎麼連購物車 -->
          </div>
        </div>
      </div>
    </div>

    <nav aria-label="..." style="margin-top: 10px">
      <ul class="pagination">
        <li class="page-item" v-for="(page, index) in totalPages" :key="index">
          <a class="page-link" href="#" @click.prevent="changePage(page)"
            >{{ page }}
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<style scoped>
a {
  text-decoration: none;
}
/* 以下立新 */
.el-col {
  padding: 5px;
  margin-left: 0px !important;
  margin-right: 0px !important;
  display: flex !important;
  flex-direction: column;
  justify-content: center; /* 垂直居中 */
  align-items: center; /* 水平居中 */
  text-align: center;
}
</style>
