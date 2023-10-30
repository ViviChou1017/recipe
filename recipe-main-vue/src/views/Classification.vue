<template>
  <!-- 以下新介面 -->
  <el-row type="flex" :gutter="10" justify="center">
    <!-- 以下為新介面 -->
    <el-col :span="24" :offset="0">
      <Advertisement></Advertisement>
    </el-col>
    <el-col :span="24" :offset="0">
      <SearchBar :active-name="'classification'">
        <template v-slot:classification>
          <!-- classification 插槽的内容放这里 -->
          <el-form style="max-width: 100%" label-position="top" :inline="true">
            <el-form-item label="分類篩選">
              <el-select
                v-model="selectedClassification"
                @change="fetchIngredients(selectedClassification)"
                placeholder="請選擇分類"
                size="large"
                :popper-append-to-body="true"
              >
                <el-option
                  v-for="(item, index) in classification"
                  :key="item + index"
                  :value="item.classificationId"
                  :label="item.classificationName"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="名稱搜尋">
              <el-select
                placeholder="請選擇食材"
                size="large"
                v-model="selectedIngredient"
                @change="chosenIngredients(selectedIngredient)"
              >
                <el-option
                  v-for="ingredient in ingredients"
                  :label="ingredient.ingredientName"
                  :key="ingredient.ingredientIdngredientId"
                  :value="ingredient.ingredientId"
                />
              </el-select>
            </el-form-item>
            <el-form-item label="&nbsp">
              <router-link
                v-if="selectedIngredient"
                :to="{ name: 'ingredient', params: { id: selectedIngredient } }"
              >
                <el-button round size="large" :disabled="!selectedIngredient"
                  >搜尋<el-icon><Search /></el-icon
                ></el-button>
              </router-link>
            </el-form-item>
          </el-form>
        </template>
      </SearchBar>
    </el-col>
  </el-row>

  <el-row :gutter="0">
    <el-col :span="24" :offset="0"> </el-col>

    <el-col
      :span="6"
      v-for="ingredient in Ingredients"
      :key="ingredient.ingredientId"
    >
      <el-card
        :body-style="{ padding: '0px 0px 3% 0px', width: '100%' }"
        :style="{ width: '100%' }"
      >
        <RouterLink
          :to="{ name: 'ingredient', params: { id: ingredient.ingredientId } }"
        >
          <el-image
            style="width: 100%; height: 200px"
            :src="ingredient.ingredientImage"
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
              style="text-decoration: none"
              :to="{
                name: 'ingredient',
                params: { id: ingredient.ingredientId },
              }"
            >
              <el-col :span="24" :offset="0">
                <el-text type="primary" size="large" tag="b">{{
                  ingredient.ingredientName
                }}</el-text>
              </el-col>
            </RouterLink>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
  </el-row>

  <!-- 以下舊介面 不顯示 -->
  <div v-if="false">
    <br />
    Type
    <select
      v-model="selectedClassification"
      @change="fetchIngredients(selectedClassification)"
    >
      <option
        v-for="(item, index) in classification"
        :key="item + index"
        :value="item.classificationId"
      >
        {{ item.classificationName }}
      </option>
    </select>

    SubType
    <select
      v-model="selectedIngredient"
      @change="chosenIngredients(selectedIngredient)"
    >
      <option
        v-for="ingredient in ingredients"
        :key="ingredient.ingredientIdngredientId"
        :value="ingredient.ingredientId"
      >
        {{ ingredient.ingredientName }}
        <!-- {{ ingredient.ingredientId }} -->
      </option>
    </select>
    <br />
    <br />

    <br />
    <!-- <button><router-link v-if="selectedIngredient" :to="{ name: 'ingredient', params: { id:selectedIngredient } }">
      </router-link>搜尋</button> -->
    <router-link
      v-if="selectedIngredient"
      :to="{ name: 'ingredient', params: { id: selectedIngredient } }"
    >
      <button>搜尋</button>
    </router-link>
  </div>
</template>

<script setup>
// 以下立新加入
import Advertisement from "../components/Advertisement.vue";
import SearchBar from "../components/SearchBar.vue";
import url from "../models/Url";
// 以上立新加入
import { ref, reactive } from "vue";
import axios from "axios";
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from "@element-plus/icons-vue";

const URL = import.meta.env.VITE_API_JAVAURL;

const classification = ref([]);
const ingredients = ref([]);

const selectedClassification = ref("");
const selectedIngredient = ref("");

const Ingredients = ref([]);

const findAllIngredients = async () => {
  try {
    const API_URL = `${url}allIngredient`;
    // console.log(uploaderId);
    const response = await axios.get(API_URL);
    if (response.status === 200) {
      console.log("findAllIngredients", response.data);
      Ingredients.value = response.data;
      const list = [];
      response.data.forEach((element) => {
        let recipe = {
          ingredientId: element[0],
          ingredientName: element[1],
          ingredientImage: element[2],
        };
        list.push(recipe);
      });
      Ingredients.value = list;
    }
  } catch (error) {
    console.log(error);
  }
};

// console.log("Ingredient" + selectedIngredient.value);

// const toIngredient = () => {
//       alert('AAAAA');
//       router.push('/ingredient');

//     }
// const ClassificationByIngredient = (ingredientId) => {
//   if (ingredientId == null || ingredientId == '') {
//     router.push({ path: "/Ingredient" });

//   }else{
//     router.push({ path: "/Ingredient",query:{ingredientId:ingredientId} });

//   }

// }

// 获取分类数据
const fetchClassification = async () => {
  try {
    const response = await axios.get(`${URL}recipe/findClassificationAll`);
    classification.value = response.data;

    // console.log("fetchClassification", response);
  } catch (error) {
    // console.error(error);
  }
};
// 获取食材数据

function chosenIngredients(input) {
  // console.log(input);
}
const fetchIngredients = async (classificationId) => {
  // console.log(classificationId);

  try {
    if (classificationId == null || classificationId == "") {
      return;
    }
    const response = await axios.get(
      `${URL}recipe/findByclassificationId/${classificationId}`
    );

    // response.data.forEach(element => {

    // });

    ingredients.value = response.data;
    // console.log(response.data);
  } catch (error) {
    console.error(error);
  }
};

// 在組件被掛載時獲取分類列表
import { onMounted } from "vue";
import router from "../router";
import { ElRow } from "element-plus";
onMounted(fetchClassification);
onMounted(findAllIngredients);
// onMounted(fetchIngredients);
</script>

<style scope>
/* .button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.button:hover {
  background-color: #0056b3;
} */

/* .container {
  height: 200px;
  text-align: center;
} */
/* align-items: center;
align-content: center;
text-align: center;
vertical-align: middle;
margin: auto; */

/* .el-col {
} */
</style>
