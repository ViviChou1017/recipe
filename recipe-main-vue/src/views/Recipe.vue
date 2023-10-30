<script setup>
// 以下立新加入
import SearchBar from "../components/SearchBar.vue";
import Advertisement from "../components/Advertisement.vue";
import { Picture as IconPicture } from "@element-plus/icons-vue";
const currentPage = ref();

// 以上立新加入

import axios from "axios";
import { ref, reactive, onMounted } from "vue";

const contextPath = import.meta.env.VITE_API_JAVAURL;

const recipes = ref([]);
const images = ref([]); //食譜圖片
const page = ref(1); //分頁當前頁數
const totalPage = ref(null); //分頁總頁數
const category = ref([]); //食譜分類
const selectedCategory = ref(""); //接下拉是選單傳來的分類id
const selectedCategoryId = ref(null); //把selectedCategory賦值給這個然後再用這個值去執行doFindByRecipeCategory()
const selectType = ref(null); //控制分頁要顯示哪個狀態下的
const selectName = ref(null); //接使用者輸入的值

const requestBody = reactive({
  recipeId: null, //食譜id
  recipeName: null, //食譜name
  recipeCategory: null, //食譜分類編號
  page: page.value, //分頁頁碼
  size: 8, //一頁有幾筆資料
});

//食譜全部查詢

function doFindAll(value) {
  selectType.value = "all";
  requestBody.page = value;
  recipes.value = [];

  axios
    .post(`${contextPath}recipe/page`, requestBody)
    .then(function (respones) {
      // console.log("成功",respones.data.content)
      recipes.value = respones.data.content;

      // 遍历每个食谱，查找对应的图片
      for (var i = 0; i < recipes.value.length; i++) {
        const recipe = recipes.value[i];
        doFindPic(recipe.id, i); // 传递食谱的id和索引(i)
      }

      //------------------------------------------------------

      //查全部總比數
      axios
        .get(`${contextPath}recipe/countByAll`)
        .then(function (respones) {
          // console.log("查全部總比數成功",respones.data)
          totalPage.value =
            requestBody.size == 0
              ? 1
              : Math.ceil(respones.data / requestBody.size); //總比數 / 一頁幾筆資料 = 總頁數
        })
        .catch(function (error) {
          console.log("查全部總比數失敗", error);
        })
        .finally(function () {});
    })
    .catch(function (error) {
      console.log("失敗", error);
    })
    .finally(function () {});
}

//找食譜圖片
function doFindPic(id, index) {
  requestBody.recipeId = id;
  axios
    .post(`${contextPath}recipePic/findByRecipePic`, requestBody)
    .then(function (respones) {
      // console.log("找圖片成功",respones.data)

      // 将图片添加到对应索引的 images 陣列中，要有index才不會一直被覆蓋
      images.value[index] = respones.data[0].picBase64;
    })
    .catch(function (error) {
      console.log("找圖片失敗", error);
    })
    .finally(function () {});
}

//食譜名稱模糊搜尋
function doFindByName(value) {
  requestBody.recipeName = selectName.value;
  selectType.value = "byName";
  requestBody.page = value;
  recipes.value = [];
  // console.log('selectName',requestBody.recipeName)
  axios
    .post(`${contextPath}recipe/pagename`, requestBody)
    .then(function (respones) {
      // console.log("食譜名稱模糊搜尋成功",respones.data.content)
      recipes.value = respones.data.content;

      // 遍历每个食谱，查找对应的图片
      for (var i = 0; i < recipes.value.length; i++) {
        const recipe = recipes.value[i];
        doFindPic(recipe.id, i); // 传递食谱的id和索引(i)
      }

      axios
        .post(`${contextPath}recipe/findRecipeByName/count`, requestBody)
        .then(function (respones) {
          // console.log("名字查詢總比數成功",respones)
          totalPage.value =
            requestBody.size == 0
              ? 1
              : Math.ceil(respones.data / requestBody.size); //總比數 / 一頁幾筆資料 = 總頁數
        })
        .catch(function (error) {
          console.log("名字查詢總比數失敗", error);
        })
        .finally(function () {});
    })
    .catch(function (error) {
      console.log("食譜名稱模糊搜尋失敗", error);
    })
    .finally(function () {});
}

//找食譜分類(顯示所有的分類)
function doRecipeCategoryFindAll() {
  axios
    .get(`${contextPath}recipeCategory/findAll`)
    .then(function (respones) {
      // console.log("食譜分類查詢成功",respones.data)
      category.value = respones.data;
    })
    .catch(function (error) {
      console.log("食譜分類查詢失敗", error);
    })
    .finally(function () {});
}

//選擇好分類進行查詢
function onCategoryChange(value) {
  selectType.value = "RecipeCategory";
  requestBody.page = value; //分頁功能使用

  selectedCategoryId.value = selectedCategory.value;
  if (selectedCategoryId.value !== "") {
    doFindByRecipeCategory(selectedCategoryId.value);
  }
}

//用分類找食譜
function doFindByRecipeCategory(value) {
  requestBody.recipeCategory = value;
  // requestBody.page = value
  recipes.value = [];

  //分類找食譜+分頁
  axios
    .post(`${contextPath}recipe/page/recipesCategory`, requestBody)
    .then(function (respones) {
      // console.log("分類找食譜+分頁成功",respones.data.content)
      recipes.value = respones.data.content;

      // 遍历每个食谱，查找对应的图片
      for (var i = 0; i < recipes.value.length; i++) {
        const recipe = recipes.value[i];
        doFindPic(recipe.id, i); // 传递食谱的id和索引(i)
      }
    })
    .catch(function (error) {
      console.log("分類找食譜+分頁失敗", error);
    })
    .finally(function () {});

  //"分類找食譜總比數
  axios
    .post(`${contextPath}recipe/recipeCategory`, requestBody)
    .then(function (respones) {
      // console.log("分類找食譜總比數成功",respones)
      totalPage.value =
        requestBody.size == 0 ? 1 : Math.ceil(respones.data / requestBody.size); //總比數 / 一頁幾筆資料 = 總頁數
    })
    .catch(function (error) {
      console.log("分類找食譜總比數失敗", error);
    })
    .finally(function () {});
}

//分頁
function changePage(value) {
  if (selectType.value === "all") {
    doFindAll(value);
  } else if (selectType.value === "RecipeCategory") {
    onCategoryChange(value);
  } else if (selectType.value === "byName") {
    doFindByName(value);
  }
}

onMounted(function () {
  doFindAll(1);
  doRecipeCategoryFindAll();
});
</script>

<template>
  <el-row type="flex" :gutter="10" justify="center">
    <!-- 以下為新介面 -->
    <el-col :span="24" :offset="0">
      <Advertisement></Advertisement>
    </el-col>

    <el-col :span="24">
      <SearchBar :active-name="'recipe'" style="width: 100%">
        <template v-slot:recipe>
          <!-- recipe 插槽的内容放这里 -->
          <el-form style="max-width: 100%" label-position="top" :inline="true">
            <el-form-item label="分類篩選">
              <el-select
                v-model="selectedCategory"
                placeholder="請選擇分類"
                size="large"
                @change="onCategoryChange(1)"
              >
                <el-option
                  v-for="rcategory in category"
                  :key="rcategory.id"
                  :value="rcategory.id"
                  :label="rcategory.recipeCategory"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="名稱搜尋">
              <el-input
                v-model="selectName"
                placeholder="輸入食譜名稱"
                size="large"
                @input="doFindByName(1)"
              />
            </el-form-item>
          </el-form>
        </template>
      </SearchBar>
    </el-col>

    <el-col :span="6" v-for="(recipe, index) in recipes" :key="recipe.id">
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
                <!-- <el-icon size="large"><icon-picture /></el-icon> -->
              </el-col>
            </template>
          </el-image>
        </RouterLink>
        <el-row :gutter="20">
          <el-col :span="24">
            <RouterLink
              :to="{ name: 'SingleRecipe', params: { id: recipe.id } }"
            >
              <el-text type="primary" size="large" tag="b">{{
                recipe.recipeName
              }}</el-text></RouterLink
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
          // console.log(totalPage);
          // console.log(currentPage);
          changePage(currentPage)
        "
        v-model:currentPage="currentPage"
        style="align-self: center;"
      />
    </el-col>
  </el-row>

  <el-row :gutter="20"> </el-row>

  <!-- 以上為新介面 -->
  <div v-if="false">
    <h2>食譜</h2>
    <nav class="navbar navbar-expand-lg navbar-light">
      <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
          <select
            @change="onCategoryChange(1)"
            v-model="selectedCategory"
            style="margin-right: 5px; height: 30px"
          >
            <option value="" disabled>分類</option>
            <option
              v-for="rcategory in category"
              :key="rcategory.id"
              :value="rcategory.id"
            >
              {{ rcategory.recipeCategory }}
            </option>
          </select>
          <input type="text" placeholder="搜尋食譜名稱" v-model="selectName" />
          <!--名字搜尋@click綁在a上，v-model綁在input上-->
          <a href="#" @click="doFindByName(1)"
            ><i
              class="bi bi-search-heart"
              style="font-size: 25px; margin-left: 5px"
            ></i
          ></a>
          <span>
            <button
              type="button"
              class="btn btn-light"
              style="margin-left: 610px"
            >
              <RouterLink to="/InsertRecipe" class="nav-link"
                >上傳食譜</RouterLink
              >
            </button>
          </span>
          <button
            type="button"
            class="btn btn-info"
            style="margin-left: 10px"
            @click="doFindAll(1)"
          >
            所有食譜
          </button>
        </div>
      </div>
    </nav>
    <el-row :gutter="20">
      <el-col :span="24" :offset="0">
        <div class="card-group group">
          <div
            class="card card-item"
            style="width: 18rem; margin-left: 10px"
            v-for="(recipe, index) in recipes"
            :key="recipe.id"
          >
            <RouterLink
              :to="{ name: 'SingleRecipe', params: { id: recipe.id } }"
              ><img
                :src="images[index]"
                class="card-img-top"
                style="height: 200px"
            /></RouterLink>
            <!--圖片顯示-->

            <div class="card-body">
              <h5 class="card-title">
                <RouterLink
                  :to="{ name: 'SingleRecipe', params: { id: recipe.id } }"
                  >{{ recipe.recipeName }}</RouterLink
                >
              </h5>
              <p class="card-text"></p>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="24" :offset="0">
        <nav aria-label="...">
          <ul class="pagination">
            <li
              class="page-item"
              v-for="(page, index) in totalPage"
              :key="index"
            >
              <a class="page-link" href="#" @click="changePage(page)"
                >{{ page }}
              </a>
            </li>
          </ul>
        </nav>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
h5 {
  text-align: center;
}
.group {
  display: flex; /* 使用 flex 布局 */
  flex-wrap: wrap; /* 弯曲以适应屏幕宽度 */
  justify-content: space-between; /* 在一行上平均分布卡片 */
  margin-top: 15px;
}

.card-item {
  flex-basis: calc(25% - 10px); /* 计算每个卡片的宽度，保留 10px 的间距 */
  margin-bottom: 10px; /* 适当的间距 */
  flex-grow: 0; /* 防止卡片放大 */
  flex-shrink: 0; /* 防止卡片缩小 */
}

/* 自定义样式以使不满四个卡片的行靠右排列 */
.group::after {
  content: "";
  flex-grow: 1;
}
a {
  text-decoration: none;
}
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
