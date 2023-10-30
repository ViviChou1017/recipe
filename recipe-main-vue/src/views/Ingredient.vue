<template>
  <el-row :gutter="20">
    <el-col :span="12" style="padding: 0px">
      <el-col :span="18" :offset="3">
        <el-card :body-style="{ padding: '0px' }">
          <el-image :src="imagename" class="image" style="width: 100%" />

          <el-text
            ><h1>{{ receivedData }}</h1></el-text
          >
        </el-card>
      </el-col>
      <el-col :span="24" :offset="0">
        <el-carousel trigger="click" height="350px">
          <el-carousel-item
            v-for="(element, index) in advertisement"
            :key="index"
          >
            <el-image
              :src="element.imageURL"
              fit="cover"
              style="width: 100%; height: 100%"
            />
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-col>

    <el-col :span="12" style="padding: 0px">
      <el-col :span="24">
        <el-card>
          <el-divider content-position="left"><h2>小知識</h2> </el-divider>
          <span
            ><h6>{{ tipsname }}</h6></span
          >
          <el-divider>
            <el-icon><star-filled /></el-icon>
          </el-divider>
        </el-card>
      </el-col>

      <el-col :span="24">
        <el-card>
          <el-divider content-position="left"><h2>熱量/100g</h2></el-divider>
          <span
            ><h3>{{ caloriename }}卡路里</h3></span
          >
          <el-divider>
            <el-icon><star-filled /></el-icon>
          </el-divider>
        </el-card>
      </el-col>
      <el-col :span="24">
        <el-card>
          <el-divider content-position="left"><h2>相關食譜</h2></el-divider>
          <span></span>
          <el-select
            v-model="rId"
            class="m-2"
            placeholder="Select"
            size="large"
          >
            <el-option
              v-for="item in recipenBeans"
              :key="item.id"
              :label="item.recipeName"
              :value="item.id"
            />
          </el-select>
          <el-button @click="Recipe" size="large">搜尋食譜</el-button>
          <el-divider>
            <el-icon><star-filled /></el-icon>
          </el-divider>
        </el-card>
      </el-col>
    </el-col>
  </el-row>
  <!-- 以下就介面 -->
  <div v-if="false">
    <div>
      <div>食材名稱:{{ receivedData }}</div>

      <div>
        <!-- <router-link v-if="true" :to="{ name: 'ingredient', params: { id: selectedIngredient } }">
       abcd
      </router-link> -->

        <!-- <router-link v-if="true" :to="{ path: '/Recipe', params: { id:ingredientId } }">
      </router-link> -->
        <!-- <img :src="'data:image/png;base64,' + imagename"> -->
        <img :src="imagename" />
      </div>
      <div>小知識:{{ tipsname }}</div>
      <div>熱量每100g:{{ caloriename }}卡路里</div>
    </div>
    <!-- <button @click="RecipeName">AAAAA</button> -->
    <hr />
    <br />
    <br />
    <select v-model="rId">
      <option
        v-for="recipen in recipenBeans"
        :key="recipen.id"
        :value="recipen.id"
      >
        {{ recipen.recipeName }}
        <!-- {{ ingredient.ingredientId }} -->
      </option>
    </select>
    <button @click="Recipe">搜尋食譜</button>
  </div>
</template>

<script setup>
import Advertisement from "../components/Advertisement.vue";

const advertisement = ref([
  {
    imageURL:
      "https://imageproxy.icook.network/resize?background=255%2C255%2C255&nocrop=true&stripmeta=true&type=auto&url=http%3A%2F%2Ftokyo-kitchen.icook.tw.s3.amazonaws.com%2Fuploads%2Frecipe%2Fcover%2F101981%2Fb77a7b3ebbf4b094.jpg&width=443",
  },
  {
    imageURL:
      "https://www.swansoncooking.com.hk/wp-content/uploads/2021/10/1521105663.jpg",
  },
]);
import { ref, reactive } from "vue";
import axios from "axios";
import { useRoute, useRouter } from "vue-router";
import { onMounted } from "vue";
const URL = import.meta.env.VITE_API_JAVAURL;
const receivedData = ref([]);
const route = useRoute();
const router = useRouter();
// let ingredientId;
const imagename = ref(null);
const tipsname = ref(null);
const caloriename = ref(null);
const recipenId = ref(route.params.id);
const ingredientId = ref(route.params.id);
const rId = ref("");
const recipenBeans = ref(null);

// console.log(receivedData.value[0])
// console.log('hehe' + imagename);
// const body = reactive({

//   // classificationId:8,
//   // ingredientId: route.params.id
//   ingredientId:route.params.id

// })
//console.log(body.ingredientId )

//console.log(body.ingredientId )
// console.log(route.params.id)
// console.log('哈哈哈'+ingredientId.value)

// function Recipe() {

//   RecipeId()

// }

// 发送请求获取食材名称
const fetchIngredientName = async () => {
  try {
    const response = await axios.get(
      `${URL}recipe/getDisplayIngredient/${ingredientId.value}`
    );
    receivedData.value = response.data.ingredientName;
    imagename.value = response.data.ingredientImage;
    tipsname.value = response.data.tips;
    caloriename.value = response.data.calorie;
    //console.log(response.data);

    // var obj=Object.keys(response.data).map(function(_){
    //   return response.data[_];
    // })
    // receivedData.value= obj
    //console.log('Img'+obj);
    // console.log(caloriename.value)
    //console.log('哈哈'+receivedData.value[1])
  } catch (error) {
    //console.error(error);
  }
};

//獲取中間表的食譜id
const RecipeId = async () => {
  try {
    const response = await axios.get(
      `${URL}recipe/findFoodByIngredientById/${ingredientId.value}`
    );
    recipenId.value = response.data.recipeId;

    //console.log(response.data);
  } catch (error) {
    // console.error(error);
  }
};
//獲取食譜名稱
const RecipeName = async () => {
  try {
    const response = await axios.get(
      `${URL}recipe/getRecipe/${ingredientId.value}`
    );
    recipenBeans.value = response.data;
    //console.log('recipenname.value:', recipenBeans.value);
  } catch (error) {
    //console.error(error);
  }
};

//TODO

//跳轉到 SingleRecipe id
const Recipe = function () {
  //alert(rId.value);
  //console.log(router);
  if (rId.value == "") {
    alert("請選擇食譜 !!!!!");
  } else {
    router.push({
      path: "/SingleRecipe/" + rId.value,
    });
  }
};

// // 获取食譜id数据
// const RecipeId = async () => {
//   try {
//     const response = await axios.get(`${URL} recipe/findFoodByRecipeId${classificationId}`);
//     classification.value = response.data;

//     console.log('fetchClassification', response);
//   } catch (error) {
//     console.error(error);
//   }
// }

//console.log('hoho=' + JSON.stringify(receivedData))

// 在组件创建时调用获取食材名称的函数
onMounted(() => {
  // ingredientId = route.params.id; // 获取路由参数
  fetchIngredientName();
  RecipeName();
  // console.log(ingredientId)
});
</script>
<style>
.demo-image .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 20%;
  box-sizing: border-box;
  vertical-align: top;
}

.demo-image .block:last-child {
  border-right: none;
}

.demo-image .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
</style>
