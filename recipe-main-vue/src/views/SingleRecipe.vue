<script setup>
import axios from "axios";
import { useCookie } from "vue-cookie-next";
import { useRoute, useRouter } from "vue-router";
import { ref, reactive, onMounted, watch } from "vue";
import { Delete, Star, Edit, Finished } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import router from "../router";

const contextPath = import.meta.env.VITE_API_JAVAURL;

const { getCookie, setCookie } = useCookie();
const cookie = useCookie();
const signed = ref(cookie.getCookie("cookie")); //拿到cookie資訊(登入者的會員id)
const route = useRoute();
const id = ref(route.params.id); //接食譜首頁傳來的id
const Images = ref([]); //食譜圖片
const recipeName = ref(null); //食譜名稱
const introduction = ref(null); //食譜介紹
const step = ref(null); //步驟
const collectNumber = ref(null); //收藏編號
const isWhiteStar = ref(true); //收藏顯示白星星
const isBlackStar = ref(false); //收藏顯示黑星星
const ingredientId = ref([]); //中介食材id
const ingredientWeight = ref([]); //食材份量
const ingredientName = ref([]); //食材名稱
const rateNumber = ref([]); //評分id
const score = ref([]); //評分分數
const allScore = ref([]); //全部評分
const content = ref(null); //留言內容
const allMessage = ref([]); //全部留言
const prepTime = ref(null); //準備時間
const isShowDelete = ref(false); //留言刪除按扭
const memberAccounts = ref({}); //會員帳號
const uploaderId = ref(null); //食譜上傳者id
const isShowScoreAndMessage = ref(false);
const exists = ref(null);
const updateCount = ref(0);
const memberId = ref(null);
const uploader = ref(null); //上傳食譜的人名
const rank = ref(null); //食譜難易度
const comfirmCollect = ref(false); //確認有沒有找到收藏的資料
const sum = ref(null); //評分加總
const scoreAll = ref([]); //單一食譜的所有評分
const scoreAvg = ref(null); //評分平均

const requestBody = reactive({
  recipeId: null, //食譜id
  recipeName: null, //食譜name
  collectorNumber: null, //收藏者編號
  collectDate: null, //收藏時間
  collectNumber: null, //收藏編號
  classificationId: null, //中介分類id
  ingredientId: null, //中介食材id
  ratNumber: null, //評分id
  raterNumber: null, //評分者id
  recipeNumber: null, //食譜id -->收藏的食譜id
  score: null, //評分分數
  ratDate: new Date(), //評分日期
  id: null, //會員id
  //留言
  messageId: null, //留言編號
  contributorId: null, //食譜上傳者編號
  content: null, //留言內容
  newContent: null, //更新過後的留言內容
  newScore: null, //更改後的評分
});

// 立新
const memberNumber = () => {
  try {
    return $cookies.get("cookie").memberNumber;
  } catch (error) {
    return 0;
  }
};

const follow = async (trackedNumber) => {
  const API_URL = `${import.meta.env.VITE_API_JAVAURL}track`;
  let input = {
    trackerNumber: memberNumber(),
    trackedNumber: trackedNumber,
  };
  console.log(input);
  const response = await axios.post(API_URL, input);
  console.log(response.status);
  if (response.status == 200) {
    alert("追蹤成功");
  } else {
    alert("已追蹤");
  }
};

//綁定編輯按鈕來判斷要不要進行update
function updateMessageAndScore(value) {
  updateCount.value += 1; //點擊過編輯圖片就會改變數字

  //查留言ById
  axios
    .get(`${contextPath}recipeMessage/findMessageByMessageId/${value}`)
    .then(function (respones) {
      // console.log("查留言byId成功",respones.data.messageId)
      content.value = respones.data.content;
      memberId.value = respones.data.messageId;
      requestBody.newContent = content.value; //把改好的內容賦給新的內容接值變數
    })
    .catch(function (erroe) {
      console.log("查留言ById失敗", erroe);
    })
    .finally(function () {});
  //查評分ById
  axios
    .get(`${contextPath}recipe/findById/${value}`)
    .then(function (respones) {
      // console.log("查評分byId成功",respones.data.score)
      rateNumber.value = respones.data.ratNumber;
      score.value = respones.data.score;
    })
    .catch(function (erroe) {
      console.log("查評分ById失敗", erroe);
    })
    .finally(function () {});
}

function doFindAllMessage() {
  //用食譜id查
  requestBody.recipeId = id.value;
  axios
    .post(`${contextPath}recipeMessage/findMessageByRecipeId`, requestBody)
    .then(function (respones) {
      // console.log("查留言id成功",respones.data)
      allMessage.value = respones.data;
      // console.log("查留言時間成功",respones.data[0].messageDatetime)
      // console.log("查留言內容成功",respones.data[0].content)
      // console.log("查上傳者成功",respones.data[0].contributorId)
    })
    .catch(function (erroe) {
      console.log("查留言失敗", erroe);
    })
    .finally(function () {});
}

//新增留言與評分
function createOrUpdateMessageAndScore() {
  if (signed.value === null) {
    loginFirst();
  }

  if (updateCount.value % 2 == 0) {
    console.log("沒有按更新");

    //先判斷評分是否已經存在
    requestBody.recipeNumber = id.value; //食譜id
    requestBody.raterNumber = signed.value.memberNumber; //使用者(登入者)

    axios
      .post(`${contextPath}recipe/exists`, requestBody)
      .then(async function (respones) {
        //console.log("評分已存在",respones)
        exists.value = respones.data;

        if (exists.value == true) {
          scoreAlreadyHave();
          return;
        }

        if (exists.value != true) {
          requestBody.recipeId = id.value; //食譜id
          requestBody.id = signed.value.memberNumber; //使用者(登入者)
          requestBody.contributorId = uploaderId.value; //食譜上傳者id，在findByRecipeId()取得
          requestBody.content = content.value; //內容

          if (score.value.length == 0 || content.value === null) {
            isShowScoreAndMessage.value = true;
            return;
          }

          if (
            score.value.length != 0 &&
            content.value != null &&
            content.value != ""
          ) {
            isShowScoreAndMessage.value = false;

            //新增留言
            await axios
              .post(`${contextPath}recipeMessage/createMessage`, requestBody)
              .then(async function (response) {
                // console.log("留言新增成功",response.data.messageId)
                rateNumber.value = response.data.messageId;

                requestBody.ratNumber = rateNumber.value; //評分的id
                requestBody.recipeNumber = id.value; //食譜id
                requestBody.raterNumber = signed.value.memberNumber; //使用者(登入者)
                requestBody.score = score.value; //評分分數

                //新增評分
                await axios
                  .post(`${contextPath}recipe/createScore`, requestBody)
                  .then(async function (respones) {
                    //console.log("評分新增成功",respones)

                    await location.reload();
                  })
                  .catch(function (erroe) {
                    console.log("評分新增失敗", respones);
                  })
                  .finally(function () {});
              })
              .catch(function (error) {
                console.log("留言新增失敗", error);
              })
              .finally(function () {});

            createSuccess(); //新增成功提示
          }
        }
      })
      .catch(function (erroe) {
        console.log("評分存在失敗", erroe);
      })
      .finally(function () {});

    //如果有點擊編輯就會執行這下面的update
  } else {
    // console.log("我要更新")
    requestBody.newContent = content.value; //把改好的內容賦給新的內容接值變數
    //留言更新
    axios
      .put(
        `${contextPath}recipeMessage/updateMessage/${memberId.value}`,
        requestBody
      )
      .then(async function (respones) {
        // console.log("留言更新成功",respones)

        requestBody.newScore = score.value;
        //評分更新
        axios
          .put(
            `${contextPath}recipe/updateScore/${rateNumber.value}`,
            requestBody
          )
          .then(function (respones) {
            // console.log("評分更新成功",respones)
          })
          .catch(function (error) {
            console.log("評分更新失敗", error);
          })
          .finally(function () {});

        await location.reload(); //這個要寫在評分更新裡面
      })
      .catch(function (erroe) {
        console.log("留言更新失敗", erroe);
      })
      .finally(function () {})
      .catch(function (erroe) {
        console.log("查留言ById失敗", erroe);
      })
      .finally(function () {});
  }
}

//請先登入
const loginFirst = () => {
  ElMessage("請先登入");
};

//新增成功提示
const createSuccess = () => {
  ElMessage("新增成功");
};

//取消按鈕
function CancelCreate() {
  score.value = null;
  content.value = null;
}

//刪除評分與留言(留言還沒做)
function doDeleteScoreAndMessage(value) {
  requestBody.ratNumber = value; //評分id

  //刪除評分
  axios
    .post(`${contextPath}recipe/deleteScore/ratNumber`, requestBody)
    .then(function (respones) {
      //    console.log("評分刪除成功",respones)
    })
    .catch(function (error) {
      console.log("評分刪除失敗", error);
    })
    .finally(function () {});

  //傳進來的value是要刪除的留言id

  //刪除留言
  requestBody.messageId = value; //留言id

  axios
    .post(`${contextPath}recipeMessage/deleteMessage`, requestBody)
    .then(async function (respones) {
      //  console.log("留言刪除成功",respones)
      await location.reload();
    })
    .catch(function (error) {
      console.log("留言刪除失敗", error);
    })
    .finally(function () {});

  deleteSuccess();
}

//刪除成功提示
const deleteSuccess = () => {
  ElMessage("刪除成功");
};

//已評論過通知
const scoreAlreadyHave = () => {
  ElMessage("您已評論過本篇食譜，謝謝");
};

function doScoreAvg() {
  requestBody.recipeNumber = id.value;
  axios
    .post(`${contextPath}recipe/findByRecipeNumber`, requestBody)
    .then(function (respones) {
      if (respones.data.length == 0) {
        scoreAvg.value = 0;
        return;
      }
      for (var i = 0; i < respones.data.length; i++) {
        sum.value += respones.data[i].score;
        scoreAll.value[i] = respones.data[i].score;
      }
      scoreAvg.value = (sum.value / scoreAll.value.length).toFixed(1);
    })
    .catch(function (erroe) {})
    .finally(function () {});
}

function doFindAllScore() {
  //用食譜id找評分
  if (signed.value != null) {
    //要登入才會顯示刪除按鈕
    isShowDelete.value = true;
  }

  requestBody.recipeNumber = id.value;

  axios
    .post(`${contextPath}recipe/findByRecipeNumber`, requestBody)
    .then((respones) => {
      // console.log("查評分成功",respones.data)
      allScore.value = respones.data;

      for (var i = 0; i < respones.data.length; i++) {
        requestBody.id = respones.data[i].raterNumber;

        //查詢會員帳號
        axios
          .post(`${contextPath}member/findById`, requestBody)
          .then(function (respones) {
            // console.log("會員查詢成功",respones.data)
            memberAccounts.value[respones.data.memberNumber] =
              respones.data.memberName;
          })
          .catch(function (error) {
            console.log("會員查詢失敗", error);
          })
          .finally(function () {});
      }
    })
    .catch(function (erroe) {
      console.log("查評分失敗", erroe);
    })
    .finally(function () {});
}

//找食材名稱與份量
async function doFindMixId() {
  //用rid取中介iid與食材重量
  // requestBody.recipeId = id.value
  await axios
    .get(`${contextPath}recipe/findFoodByRecipeId/${id.value}`)
    .then(function (respones) {
      for (var i = 0; i < respones.data.length; i++) {
        ingredientId.value[i] = respones.data[i].foodMixId.ingredientId;
        ingredientWeight.value[i] = respones.data[i].ingredientWeight;
        // ingredientId.value.push(respones.data[i].foodMixId.ingredientId); push() 方法將新的值添加到數组末尾
      }
    })
    .catch(function (error) {
      console.log("找食材id失敗", error);
      console.log("食材重量[]失敗", error);
    })
    .finally(function () {});
  //利用迴圈撈出每筆資料
  for (var i = 0; i < ingredientId._rawValue.length; i++) {
    //再用iid取食材名稱
    await axios
      .get(`${contextPath}recipe/findByIngredientId/${ingredientId.value[i]}`)
      .then(function (respones) {
        // console.log("食材名稱成功", respones)
        ingredientName.value[i] = respones.data;
      })
      .catch(function (error) {
        console.log("找食材名稱失敗", error);
      })
      .finally(function () {});
  }
  //console.log("test", ingredientName)
}

//收藏增刪 - 待確認cookie存取資訊
function dolike() {
  //利用點擊次數來判斷，奇數新增、偶數刪除

  requestBody.collectorNumber = signed.value.memberNumber; //使用者(登入者)
  requestBody.recipeNumber = id.value;

  axios
    .post(
      `${contextPath}collect/findByCollectorNumberAndRecipeNumber`,
      requestBody
    )
    .then(async function (respones) {
      // console.log("找收藏成功", respones)
      if (respones.data == "") {
        //資料庫沒資料，做新增
        requestBody.collectDate = new Date();
        doCreateCollect();
        comfirmCollect.value = true;

        //新增完成顯示黑色
        //  isWhiteStar.value = false
        //  isBlackStar.value = true
      } else {
        //資料庫有資料，做刪除
        requestBody.collectNumber = respones.data.collectNumber;
        doDeleteCollect();
        comfirmCollect.value = false;

        //刪除完成顯示白色
        // isWhiteStar.value = true
        // isBlackStar.value = false
      }
      if (comfirmCollect.value === true) {
        //顯示黑色
        isWhiteStar.value = false;
        isBlackStar.value = true;
      } else {
        //顯示白色
        isWhiteStar.value = true;
        isBlackStar.value = false;
      }

      setCookie(
        `comfirmCollect_${signed.value.memberNumber}_${id.value}`,
        comfirmCollect.value,
        30
      );
    })
    .catch(function (error) {
      console.log("找收藏失敗", error);
    })
    .finally(function () {});
}

//新增收藏
function doCreateCollect() {
  // requestBody.collectorNumber = signed.value.memberNumber //使用者(登入者)
  // requestBody.recipeNumber = id.value
  // requestBody.collectDate = new Date
  axios
    .post(`${contextPath}collect/createCollect`, requestBody)
    .then(function (respones) {
      // console.log("收藏成功", respones)
      collectNumber.value = respones.data.collectNumber;
      // console.log("編號", collectNumber)
    })
    .catch(function (error) {
      console.log("收藏失敗", error);
    })
    .finally(function () {});
}

//刪除收藏
function doDeleteCollect() {
  // requestBody.collectNumber = collectNumber.value
  // console.log("collectNumber", requestBody.collectNumber)
  axios
    .post(`${contextPath}collect/deleteCollect`, requestBody)
    .then(function (respones) {
      // console.log("收藏刪除成功", respones)
    })
    .catch(function (error) {
      console.log("收藏刪除失敗", error);
    })
    .finally(function () {});
}

//找食譜ID
function doFindRecipeId() {
  requestBody.recipeId = id.value;

  axios
    .post(`${contextPath}recipe/findByRecipeId`, requestBody)
    .then(async function (respones) {
      //console.log("這是什麼?",respones.data)
      // console.log(typeof respones.data.step)
      //console.log("步驟",respones.data.step)
      //console.log("JSON",JSON.parse(respones.data.step)) //JSON.parse(respones.data.step)把step字串轉成json格式
      uploaderId.value = respones.data.uploaderId;
      prepTime.value = respones.data.prepTime;
      step.value = JSON.parse(respones.data.step);
      //console.log("步驟細節",step.value[0].introduction)
      recipeName.value = respones.data.recipeName;
      introduction.value = respones.data.introduction; //食譜簡介

      rank.value = respones.data.rank;

      requestBody.id = uploaderId.value;
      //查上傳食譜的人名
      await axios
        .post(`${contextPath}member/findById`, requestBody)
        .then(function (respones) {
          // console.log("會員查詢成功",respones.data)
          uploader.value = respones.data.memberName;
          // console.log("上傳食譜的人",uploader.value)
        })
        .catch(function (error) {
          console.log("會員查詢失敗", error);
        })
        .finally(function () {});
    })
    .catch(function (error) {
      console.log("error", error);
    })
    .finally(function () {});
}

//找照片
function doFindPic() {
  requestBody.recipeId = id.value;
  axios
    .post(`${contextPath}recipePic/findByRecipePic`, requestBody)
    .then(function (respones) {
      // console.log("找圖片成功", respones.data)
      // firstImage.value = respones.data[0].picBase64
      Images.value = respones.data;
    })
    .catch(function (error) {
      console.log("找圖片失敗", error);
    })
    .finally(function () {});
}

//追蹤食譜作者的方法
function doTrack(value) {
  follow(value);
}


function changePage(){
  router.push("/kitchenware");

}
onMounted(async function () {
  if (signed.value != null) {
    //判斷使用者登入與否
    comfirmCollect.value = getCookie(
      `comfirmCollect_${signed.value.memberNumber}_${id.value}`
    );
    if (
      getCookie(`comfirmCollect_${signed.value.memberNumber}_${id.value}`) ===
      "true"
    ) {
      //顯示黑色
      isWhiteStar.value = false;
      isBlackStar.value = true;
    } else {
      //顯示白色
      isWhiteStar.value = true;
      isBlackStar.value = false;
    }
  }

  doFindRecipeId();
  doFindMixId();
  doFindAllScore();
  doFindAllMessage();
  doFindPic();
  doScoreAvg();
});
</script>

<template>
  <!-- 以下新介面 -->
  <el-row :gutter="20">
    <el-col
      :span="12"
      :offset="0"
      style="
         {
          padding: 0px;
          margin-left: inherit;
          margin-right: inherit;
          display: inherit;
        }
      "
    >
      <!-- <el-image
        style="width: 100%; height: 100%"
        :src="Images[0].picBase64"
        fit="cover"
      /> -->
    </el-col>
    <el-col :span="12"> </el-col>
  </el-row>

  <!-- 以上新介面 -->
  <h1>{{ recipeName }}</h1>
  <!-- <div>{{ introduction }}</div> -->
  <!-- 首圖成品、介紹等 -->
  <el-row :gutter="24">
    <el-col :span="23">
      <div class="card mb-3 left" style="max-width: 75%; height: auto">
        <div
          class="row g-0"
          v-for="(img, index) in Images"
          :key="img.id"
          style="background-color: aquamarine"
        >
          <img
            :src="img.picBase64"
            alt="..."
            style="max-width: 290px; height: 220px"
            v-if="index === 0"
            class="pic"
          />

          <span>
            <span
              style="
                position: absolute;
                top: 10px;
                left: 300px;
                white-space: nowrap;
                font-size: 30px;
              "
            >
              {{ recipeName }}
            </span>
            <span
              style="
                font-size: medium;
                position: absolute;
                top: 10px;
                right: 145px;
                white-space: nowrap;
              "
              >By &nbsp{{ uploader }}</span
            >

            <el-button
              type="success"
              style="
                height: 28px;
                border: none;
                border-radius: 10px;
                background-color: #00cd66;
                position: absolute;
                top: 10px;
                right: 75px;
              "
              @click="doTrack(uploaderId)"
              >追蹤</el-button
            >

            <button
              @click="dolike()"
              style="
                border: none;
                border-radius: 10px;
                padding-top: 3px;
                background-color: hwb(60 0% 0% / 0.71);
                position: absolute;
                top: 10px;
                right: 5px;
              "
            >
              <span>
                <el-icon v-show="isWhiteStar">
                  <Star />
                </el-icon>
                <el-icon v-show="isBlackStar"> <StarFilled /> </el-icon
                >&nbsp;收藏</span
              >
            </button>
          </span>
          <p
            class="card-title"
            style="top: 20px; margin-top: 20px; width: auto"
          >
            <el-rate
              v-model="scoreAvg"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{value} 顆星"
            />
          </p>
          <p
            class="card-title"
            style="top: 50px; margin-top: 20px; width: auto"
          >
            食譜難易度&nbsp:&nbsp{{ rank }}&nbsp顆星
          </p>
          <p
            class="card-title"
            style="top: 80px; margin-top: 20px; width: auto"
          >
            烹飪時間&nbsp:&nbsp{{ prepTime }} &nbsp(分鐘)
          </p>
          <p
            class="card-title"
            style="top: 110px; margin-top: 20px; width: auto"
          >
            簡介&nbsp:&nbsp{{ introduction }}
          </p>
        </div>
      </div>

      <!-- 所需食材 -->
      <div class="card mb-3" style="max-width: 75%">
        <div class="row g-0">
          <h3
            style="
              border-bottom: 1px solid black;
              margin-bottom: 20px;
              margin-top: 5px;
              margin-left: 10px;
              padding-bottom: 10px;
              width: 95%;
            "
          >
            所需食材
          </h3>
          <!-- <span v-for="(name,index) in ingredientName" :key="index">
        <p>{{ name.ingredientName }}</p></span>
        <span v-for="(weight,index) in ingredientWeight" :key="index">
        <p>{{ weight }}</p></span> -->

          <div
            class="col-4"
            style="line-height: 10px"
            v-for="(name, index) in ingredientName"
            :key="index"
          >
            <p
              style="
                justify-content: space-between;
                margin-bottom: 15px;
                margin-left: 30px;
              "
            >
              {{ name.ingredientName }}&nbsp;&nbsp;&nbsp;&nbsp;{{
                ingredientWeight[index]
              }}&nbsp;&nbsp;&nbsp;克
            </p>
          </div>
        </div>
      </div>

      <!-- 放步驟的地方；有第二張以上的照片就會自動帶入 -->
      <div
        class="card mb-3"
        style="max-width: 75%"
        v-for="(img, index) in Images.slice(1)"
        :key="img.id"
      >
        <!--Images.slice(1)讓圖片從第2張圖開始跑，所以第二張圖變成index[0]的位子，所以index從頭到尾都是0開始-->
        <div class="row g-0">
          <div class="col-md-4">
            <img
              :src="img.picBase64"
              alt=""
              style="max-width: 268px; height: 188px"
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5 class="card-title">
                <!-- 有幾張照片就要有幾個步驟，步驟不能比照片少，不然會報錯，因為照片的index會找不到資料 -->
                <p>步驟{{ index + 1 }}</p>
                {{ step[index].introduction }}
                <!-- step.value[0].introduction -->
              </h5>
            </div>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="1" style="margin-left: -280px">
      <div>
        <img
          style="width: 350px; height: 350px"
          src="https://img95.699pic.com/photo/40164/5434.gif_wh860.gif"
        />
        <img
          style="width: 350px; height: 350px"
          src="https://img95.699pic.com/photo/40173/9661.gif_wh300.gif"
        />
        <img
          style="width: 350px; height: 350px"
          src="https://cdn.store-assets.com/s/836670/f/10947278.jpg"
          @click="changePage()"
        />
      </div>
    </el-col>
  </el-row>

  <!-- 評分欄 -->
  <el-col :span="24" :offset="0">
    <el-card class="el-card-d" shadow="always" style="width: 72%">
      <div class="infinite-list-wrapper" style="overflow: auto; width: 100%">
        <el-timeline infinite-scroll-disabled="disabled">
          <h3>評論</h3>
          <p></p>

          <div v-for="(scoree, index) in allScore" :key="scoree.ratNumber">
            <el-timeline-item
              ><!--留言到時在這跑迴圈-->
              <el-card class="el-card-m" style="height: auto">
                <!-- 評分 -->
                <!-- 顯示留言 -->
                <div>
                  <span class="demonstration"></span>
                  <el-rate v-model="scoree.score" />
                  <span
                    >by&nbsp;&nbsp;{{
                      memberAccounts[scoree.raterNumber]
                    }}&nbsp;&nbsp;&nbsp;{{ scoree.ratDate }}
                    <a href="#targetElementId"
                      ><el-button
                        v-if="
                          isShowDelete &&
                          scoree.raterNumber === signed.memberNumber
                        "
                        @click="updateMessageAndScore(scoree.ratNumber)"
                        type="primary"
                        :icon="Edit"
                        circle
                        class="top-right-button"
                    /></a>
                    <el-button
                      v-if="
                        isShowDelete &&
                        scoree.raterNumber === signed.memberNumber
                      "
                      @click="doDeleteScoreAndMessage(scoree.ratNumber)"
                      type="danger"
                      :icon="Delete"
                      circle
                      class="top-right-button2"
                    /><!--刪除按鈕-->
                  </span>

                  <div
                    v-for="(message, index) in allMessage"
                    :key="message.messageId"
                  >
                    <p v-if="scoree.raterNumber === message.id">
                      {{ message.content }}
                    </p>
                    <!--當評論者跟評分者同一人時顯示評論內容-->
                  </div>
                </div>
              </el-card>
            </el-timeline-item>
          </div>
        </el-timeline>
      </div>
      <!-- 留言處 -->
      <div
        style="
          border: 1px solid rgb(193, 187, 187);
          border-radius: 10px;
          padding: 10px;
        "
      >
        <el-col :span="24" :offset="0">
          <div class="el-card-messages">
            <!-- 評分 -->
            <div id="targetElementId">
              <span class="demonstration"
                >&nbsp;&nbsp;&nbsp;評審請給分&nbsp;</span
              >
              <el-rate v-model="score" />
            </div>
            <el-input
              type="textarea"
              :rows="5"
              placeholder="請輸入留言"
              maxlength="200"
              v-model="content"
              style="padding: 10px; padding-top: "
            ></el-input>
            <el-col :span="24" :offset="0">
              <el-button
                style="text-align: center"
                type="info"
                round
                class="submit-message"
                @click="createOrUpdateMessageAndScore()"
                >送&nbsp;出</el-button
              >&nbsp;&nbsp;&nbsp;
              <el-button
                type="info"
                round
                class="submit-message"
                @click="CancelCreate()"
                >取&nbsp;消</el-button
              >
            </el-col>
          </div>
        </el-col>
      </div>
    </el-card>
  </el-col>

  <!-- 評分跟留言都要輸入 -->
  <el-alert
    title="評分跟評論都要輸入唷!!!"
    type="warning"
    v-show="isShowScoreAndMessage"
  />
</template>

<style scoped>
.card-title {
  position: absolute;
  top: 10px;
  left: 300px;
}
.top-right-button {
  position: absolute;
  top: 10px;
  right: 50px;
}
.top-right-button2 {
  position: absolute;
  top: 10px;
  right: 10px;
}
.infinite-list-wrapper {
  width: 100%;
  height: auto;
}

.submit-message {
  width: 25%;
  background: rgb(235, 245, 247);
  color: cadetblue;
  /* letter-spacing: 30px; */
  position: relative; /* 設置按鈕絕對位置 */
  left: 22%;
}
/* 重新改 */
.el-col {
  /* margin-left: auto;
  align-items: inherit;
  padding: inherit;
  margin-right: inherit;
  display: inherit; */
  padding: 0px;
  margin-left: inherit;
  margin-right: inherit;
  display: inherit;
  text-align: left;
}

@media screen and (max-width: 3000px) and (min-width: 767px) {
  .el-card-d {
    float: left;
    margin-top: 20px;
    /* margin-left: 10%; */
    width: 80%;
    height: 90%;
  }
}
</style>
