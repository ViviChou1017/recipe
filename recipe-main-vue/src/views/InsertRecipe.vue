<script setup>
    import axios from "axios"
    import { useCookie } from "vue-cookie-next";
    import { Check,Delete } from '@element-plus/icons-vue'
    import { ElMessage, ElMessageBox } from 'element-plus'
    import { useRouter } from 'vue-router';
    import { ref , reactive , watch , onMounted} from "vue"
    
    const contextPath = import.meta.env.VITE_API_JAVAURL;
    
    const router = useRouter();
    const cookie = useCookie();
    const signed = ref(cookie.getCookie("cookie"));
    const recipeName = ref(null);
    const memberNumber = ref(null);
    memberNumber.value = signed._rawValue.memberNumber  
    //取得cookie裡面的會員編號，可以拿來當uploaderId
    // console.log('cookie',signed._rawValue.memberNumber)
    const category = ref([]);
    const selectedCategory = ref(""); //接下拉式選單傳來的分類id
    const introduction = ref(null); //簡介
    const rank = ref(null); //難易度
    const prepTime = ref(""); //烹飪時間
    const step = ref([]); //接每個步驟的字串
    const stepByStep = ref([]); //放每個變成json格式後的步驟
    const stepElement = ref([{}]) //代表每一個步驟的html元件
    const isShowAlert = ref(false) //警告視窗
    const recipeId = ref(null) //食譜編號
    const imageUrl = ref('')  //element需要的
    const uploadRef = ref(null)  //上傳食譜的圖片
    const stepUpLoadRef = ref([]) //上傳步驟的圖片
    const imageUrls = ref([]); //步驟圖片尚未上傳前，做顯示用
    const classification = ref([]) //接食材分類
    const selectedClassification = ref([""]); //接下拉式選單傳來的食材分類id
    const allIngredients = ref([]);  // 所有的食材列表，每個元素都是 { classificationId: xxx, ingredients: [] }
    const selectIngredient = ref([""]); //接下拉是選單傳來的食材id
    const num = ref([1])  //食材份量
    const items = ref([]) //食材新增元件
    


    const requestBody = reactive({
        recipeName:null,
        uploaderId:null,
        uploadDate: new Date,
        category:null,
        vageCategory:null,
        prepTime:null,
        introduction:null, 
	      rank:null,
	      videoUrl:null,
	      step:null,
        // 1003中介table的requestBody
        foodMixId:{
            recipeId:null,
            ingredientId:null,
            cookeryId:1,
            classificationId:null
        },
        ingredientWeight:null
    })

    
    // function dotest(){
      
    //   console.log("沒有圖片",defaultImage)
    //   console.log("食材",selectIngredient._rawValue)
    //   console.log("食材份量",num._rawValue)

    //   // doFoodMIx()
    // }


//新增食譜
function doInsertRecipe(){ 

//檢查使用者資料是否都填妥
if(recipeName.value === null){
  noRecipeName()
  return 
}else if(!imageUrl.value || imageUrl.value.trim() === "") {
  noImageUrl()
  return 
}else if(introduction.value === null){
  noIntroduction()
  return
}else if(prepTime.value === ""){
  noPrepTime()
  return 
}else if(selectedCategory.value === ""){
  noCategory()
  return 
} else if (!selectedClassification.value.some(val => val !== "") || 
      !selectIngredient.value.some(val => val !== "")){
  noIngredient()
  return 
}else if(!step.value.some(val => val && val.trim() !== "")){
  noStep()
  return 
}else if(imageUrls.value.length == 0 || imageUrls.value.length < step.value.length){
  noImageUrls()
  return 
} 
  


  //新增食譜前，先把步驟塞入JSON物件陣列中
  for (let i = 0; i < step.value.length; i++) {
      if(!stepByStep.value[i]){
        stepByStep.value[i] = {}
      }
      stepByStep.value[i] = {
        introduction : step.value[i]
      }
    }
    requestBody.recipeName = recipeName.value
    requestBody.uploaderId = memberNumber.value
    requestBody.category = selectedCategory.value
    requestBody.introduction = introduction.value
    requestBody.rank = rank._rawValue
    requestBody.prepTime = prepTime.value
    requestBody.step = stepByStep.value //後端有寫把JSON轉字串

axios.post(`${contextPath}recipe/insertRecipe`,requestBody).then(function(respones){

console.log("新增食譜成功",respones)
recipeId.value = respones.data.id //食譜上傳成功才會產生食譜id

//上傳食譜圖片
const uploadComponent = uploadRef.value;
        uploadComponent.submit();


//上傳步驟圖片

for(let i = 0; i < stepUpLoadRef.value.length; i++) {


  stepUpLoadRef.value[i].submit();
  
}

//新增中介table
doFoodMIx() 

//發佈完成通知
finish()

//上傳成功跳轉頁面到食譜首頁
router.push("/Recipe");


}).catch(function(error){

console.log("新增食譜失敗",error)

}).finally(function(){

})
}
//上傳食譜方法到這邊



//1003中介table
function doFoodMIx(){

  requestBody.foodMixId.recipeId = recipeId.value
  
  for(var i=0;i<selectedClassification._rawValue.length;i++){
    requestBody.foodMixId.classificationId = selectedClassification._rawValue[i]
    requestBody.foodMixId.ingredientId = selectIngredient._rawValue[i]
  requestBody.ingredientWeight = num._rawValue[i]


  
  axios.post(`${contextPath}recipe/addFoodMix`,requestBody).then(function(respones){
    console.log("中介table成功",respones)
  }).catch(function(error){
    console.log("中介table失敗",error)
  }).finally(function(){
  
  })

}

}

    

//食譜上傳成功通知
const finish = () => {
  ElMessageBox.alert('食譜發布成功，感謝您的分享', '成功', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}



//食譜上傳失敗訊息區
const noRecipeName = () => {
  ElMessageBox.alert('食譜發布失敗，請填寫食譜名稱', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}


const noImageUrl = () => {
  ElMessageBox.alert('食譜發布失敗，請新增食譜圖片', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}

const noIntroduction = () => {
  ElMessageBox.alert('食譜發布失敗，請填寫食譜簡介', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}

const noPrepTime = () => {
  ElMessageBox.alert('食譜發布失敗，請填寫烹調時間', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}

const noIngredient = () => {
  ElMessageBox.alert('食譜發布失敗，請新增至少一種食材', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}

const noStep = () => {
  ElMessageBox.alert('食譜發布失敗，請輸入至少一個步驟', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}


const noCategory = () => {
  ElMessageBox.alert('食譜發布失敗，請選擇食譜分類', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}


const noImageUrls = () => {
  ElMessageBox.alert('食譜發布失敗，請選擇步驟圖片', '發布失敗', {
    // if you want to disable its autofocus
    // autofocus: false,
    confirmButtonText: '確定',
    callback: (action) => {
      ElMessage({
        type: 'info',
        message: `action: ${action}`,
      })
    },
  })
}









//食材分類
function onChangeClassification(index){
  
  axios.get(`${contextPath}recipe/findClassificationAll`).then(function(response){
    console.log("食材分類成功",response)
    classification.value = response.data
    // if(selectedClassification.value!= null){
    doFindIngredient(index)
  // }
  }).catch(function(error){
    console.log("食材分類失敗",error)
  }).finally(function(){

  })
}


//拿到食材分類id後(selectedClassification)，找出相關食材分類的食材
function doFindIngredient(index){
if(selectedClassification.value[index]!=null && selectedClassification.value[index]!=""){
axios.get(`${contextPath}recipe/findByclassificationId/${selectedClassification.value[index]}`).then(function(response){
console.log("找食材成功",response)

const existingData = allIngredients.value.find(ingredient => ingredient.classificationId === selectedClassification.value[index]);
      if (existingData) {
        existingData.ingredients = response.data;
      } else {
        allIngredients.value.push({
          classificationId: selectedClassification.value[index],
          ingredients: response.data
        });
      }

}).catch(function(error){
console.log("找食材失敗",error)
}).finally(function(){

})
}
}


watch(selectedClassification, (newValue, oldValue) => {
    if(newValue !== oldValue && newValue != null && newValue !== "") {
        doFindIngredient();
    }
});






//上傳圖片前，要有食譜id才能上傳圖片
function beforeAvatarUpload(file) {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('recipeId', recipeId.value);
    
    axios.post(`${contextPath}recipePic/insertRecipePic`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then(response => {
      handleAvatarSuccess(response);
    })
    .catch(error => {
      console.error("Upload failed:", error);
    });
    // Prevent the default upload behavior of the el-upload component
    return false;
  }

//照片上傳成功
 function handleAvatarSuccess(response) {
    // Handle successful upload logic here, for example:
    imageUrl.value = response.data.path;
  }

//食譜圖片，尚未上傳前圖片顯示方法
  function handleFileChange(file) {
    const reader = new FileReader();

    reader.onload = (event) => {
        imageUrl.value = event.target.result;
    };

    reader.readAsDataURL(file.raw);
}

//步驟圖片尚未上傳前，圖片顯示方法
function handleFileChange2(file, index) {
    const reader = new FileReader();

    reader.onload = (event) => {
        // 使用Vue 3的reactivity系統設置array的特定索引值
        imageUrls.value[index] = event.target.result;
    };

    reader.readAsDataURL(file.raw);
}



//食譜分類
function onCategoryChange(){
axios.get(`${contextPath}recipeCategory/findAll`).then(function(respones){
console.log("食譜分類成功",respones)
category.value = respones.data
}).catch(function(error){
console.log("食譜分類失敗",error)
}).finally(function(){

})
}

//增加步驟
function addStepElement(){
  
  //增加步驟元件
  stepElement.value.push({});
  stepUpLoadRef.value.push(null);
}

//刪除步驟
function deleteStepElement(index){
  if(index!=0){
    //刪除步驟元件
    stepElement.value.splice(index,1);
  }else{
    isShowAlert.value = true
  }
}


function addItem(index) {
  console.log("點擊了一次")
  console.log("index",index)
  items.value.push({});
}


function deleteItem(index){
  if(index!=0){
    //刪除步驟元件
    items.value.splice(index,1);
  }else{
    isShowAlert.value = true
  }
}

//清空所有資料
function deleteAll(){
  recipeName.value = null;
    imageUrl.value = '';
    introduction.value = null;
    prepTime.value = '';
    selectedCategory.value = "";
    selectedClassification.value = [""];
    selectIngredient.value = [""];
    num.value = [1];
    step.value = [];
    imageUrls.value = [];
    rank.value = null;
    items.value = [{}];  // 重置食材列表，始終保留一個空白項目
    stepElement.value = [{}];  // 重置步驟列表，始終保留一個空白項目
}

const deleteComfirm = () => {
  ElMessageBox.confirm(
    '確定要放棄食譜編輯?',
    '警告',
    {
      confirmButtonText: '確定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      ElMessage({
        type: 'success',
        message: 'Delete completed',
      })
      deleteAll()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: 'Delete canceled',
      })
    })
}






onMounted(function(){
    onCategoryChange()
    onChangeClassification()
    addItem()
})

</script>

<template>
<!-- <h1>上傳食譜(待刪除)</h1> -->

<h3>食譜分類</h3>
<select @change="onCategoryChange()" v-model="selectedCategory" style="margin-right: 5px; height: 30px;">
      <option value="" disabled>分類</option>
      <option v-for="rcategory in category" :key="rcategory.id" :value="rcategory.id">{{ rcategory.recipeCategory }} </option>
</select>

<hr>

<h3>食譜名稱</h3>
      <el-input
    v-model="recipeName"
    maxlength="50"
    placeholder="請輸入食譜名稱"
    show-word-limit
    type="text"
    style="height: 50px;"
  />

  

<div>
  <h3>食譜圖片</h3>
  <el-upload
    ref="uploadRef"
    class="avatar-uploader"
    action="http://localhost:8080/recipePic/insertRecipePic"
    :show-file-list="false"
    :auto-upload="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    @change="handleFileChange"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus/></el-icon>
  </el-upload>
</div>


<h3>簡介</h3>
<el-input
    v-model="introduction"
    :rows="8"
    type="textarea"
    placeholder="輸入食譜描述"
  />
<br>
<br>



<!-- 難易度 -->
<h3>難易度</h3>
  <el-rate
    v-model="rank"
    :texts="['超簡單', '簡單', '中等', '困難', '超困難']"
    show-text
  />
  
  <hr>

<h3>烹調時間<span style="font-size: 10px;" >(分鐘)</span></h3>
<select style="width: 200px;" v-model="prepTime">
  <option value="">未設定</option>
  <option value="5">5</option>
  <option value="10">10</option>
  <option value="15">15</option>
  <option value="20">20</option>
  <option value="25">25</option>
  <option value="30">30</option>
  <option value="45">45</option>
  <option value="60">60</option>
  <option value="90">90</option>
  <option value="180">180</option>
</select>
<br>

<hr>

<h3>食材</h3>
<!-- <el-button type="success" @click.prevent="addItem()" :icon="Check" circle  style="margin-left: 10px;"/>   -->



<div v-for="(item, index) in items" :key="index" >
<select @change="onChangeClassification(index)" v-model="selectedClassification[index]" style="margin-right: 5px; height: 30px;">
      <option value="" disabled>分類</option>
      <option v-for="c in classification" :key="c.classificationId" :value="c.classificationId">{{ c.classificationName}} </option>
</select>
<select @change="doFindIngredient(index)" v-model="selectIngredient[index]" style="margin-right: 5px; height: 30px;">
  <option value="" disabled >食材</option>
  <option v-for="i in (allIngredients.find(ingredient => ingredient.classificationId === selectedClassification[index]) || {}).ingredients || []" :key="i.ingredientId" :value="i.ingredientId">{{ i.ingredientName }}</option>
</select>
<!-- 食材份量 -->
  <el-input-number v-model="num[index]" :min="1" :max="1000"/>
     <!-- 加入、刪除按鈕 -->
   <el-button type="success" @click="addItem(index)" :icon="Check" circle  style="margin-left: 10px;"/>  
<el-button type="danger"  @click="deleteItem(index)" :icon="Delete" circle />
  </div>  









  <hr>

<h3>步驟</h3>
<div class="common-layout" style="border-radius:5px;border:1px solid black;margin-bottom: 20px;">
    <el-container v-for="(stepElementObj, index) in stepElement" :key="index" style="min-height: none;">
      <el-aside width="200px" height="fit-content" >  
        <el-upload
        :ref="el => { if(el) stepUpLoadRef[index] = el }"
    class="avatar-uploader"
    :auto-upload="false"
    action="http://localhost:8080/recipePic/insertRecipePic"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    @change="file => handleFileChange2(file, index)"
    style=";margin-top: 15px"
    
  >
    <img v-if="imageUrls[index]" :src="imageUrls[index]" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload></el-aside>
      <el-main style="min-height: max-content;">
        <span style="font-size: 45px;">{{index+1}}</span>
        <a href="#" @click.prevent="addStepElement(index)" style="margin-left: 750px; font-size: 30px; text-decoration: none ;color:black;"><el-icon><Plus /></el-icon></a>
        <a  href="#" @click.prevent="deleteStepElement(index)" style="font-size: 30px; margin-left: 20px; text-decoration: none ;color:black;"><el-icon><Delete /></el-icon></a>
        <el-input
    v-model="step[index]"
    maxlength="300"
    placeholder="請輸入步驟(最多300字)"
    show-word-limit
    type="text"
    style="height: 100px;"
  />
      </el-main>
    </el-container>
  </div>

  <!-- 警告 -->
  <el-alert
    title="至少寫一個步驟/食材"
    type="warning"
    show-icon
    v-show="isShowAlert"
  />

<!-- <button type="button" @click="dotest()">測試按鈕</button> -->


<el-button type="primary" @click="doInsertRecipe()" size="large" >發佈</el-button>
<el-button type="danger" size="large" @click="deleteComfirm()">取消</el-button>
</template>
    
<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
  height:fit-content;
}
h3{
  margin-top: 20px;
  font-weight: bolder;
}
</style>