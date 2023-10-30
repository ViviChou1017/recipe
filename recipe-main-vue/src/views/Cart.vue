<template>
    <h1>購物車</h1>
    <el-steps
      :active="0"
      finish-status="success"
      simple
      style="margin-top: 20px; margin-bottom: 20px; background:white"
    >
      <el-step title="Step 1" />
      <el-step title="Step 2" />
      <el-step title="Step 3" />
    </el-steps>
  
    <el-card shadow="always" :body-style="{ padding: '20px' }">
      <el-col v-if="cartIsEmpty" style="text-align: center; display: block;"><h5>購物車是空的!!</h5></el-col>
      
      <el-row v-for="(
        {
          courseName,
          kitchenwareName,
          coursePrice,
          kitchenwarePrice,
          quantity,
          checked,
          cartProductId,
          kitchenwareImg,
          courseImg,
        },
        index
      ) in products"
      :key="index">
      <!-- 勾選框 -->
        <el-col :span="2" :offset="0">
          <!-- <el-checkbox label="" size="large" :id="index" v-model="products[index].checked" @click="changeChecked(`${index}`)"/> -->
        <input
          class="form-check-input"
          type="checkbox"
          name="all"
          :id="index"
          :checked="checked"
          @click="changeChecked(index)"
          aria-label="..."
        />
        </el-col>
        <el-col :span="6" :offset="0">
          <img
          v-if="courseImg !== null"
          :src="courseImg"
          style="width: 90%"
          alt=""
        />
        <img
          v-if="kitchenwareImg !== null"
          :src="kitchenwareImg"
          style="width: 90%"
          alt=""
        /></el-col>
        <el-col :span="12" :offset="0" style="display: block;">
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
            <el-col :span="12" :offset="8" style="text-align: left; display: inline;"><el-input-number
                v-if="kitchenwareName !== null"
                v-model="products[index].quantity"
                @change="quantityChange(index)"
              />
            </el-col>
            <!-- <el-col :span="16" :offset="0" style="text-align: left; display: inline;">{{ quantity }}</el-col> -->
          </el-row>
        </el-col>
        <el-col :span="4" :offset="0"><el-button
          @click=" open(cartProductId)"
          
          type="primary"
          :icon="Delete"
          round plain
          
        /></el-col>
        <!-- deleteByCartProductId(cartProductId); -->
      </el-row>

      <el-row v-if="!cartIsEmpty">
        <!-- 勾選框 -->
        <el-col :span="1" :offset="1"><el-checkbox  label="全選" size="large" @change="selectAll()" v-model="allIsChecked" /></el-col>
        <el-col :span="6" :offset="12" style="font-size: 20px">總金額: ${{ total }}</el-col>
        <el-col :span="4" :offset="0"><el-button
          type="primary"
          round
          plain
          :disabled="!productIsChecked"
          @click="uploadStorage()"
          >去買單</el-button
        ></el-col>
      </el-row>
    </el-card>

    <!-- old -->
  <!-- <div class="card p-3">
    <div v-if="cartIsEmpty">沒有資料</div>

    <div
      class="row"
      v-for="(
        {
          courseName,
          kitchenwareName,
          coursePrice,
          kitchenwarePrice,
          quantity,
          checked,
          cartProductId,
          kitchenwareImg,
          courseImg,
        },
        index
      ) in products"
      :key="index"
    >
      勾選框
      <div class="col-1 p-3">
        <input
          class="form-check-input"
          type="checkbox"
          name="all"
          :id="index"
          :checked="checked"
          @click="changeChecked(index)"
          aria-label="..."
        />
      </div>
      <div class="col-3 p-3">
        <img
          v-if="courseImg !== null"
          :src="courseImg"
          style="width: 90%"
          alt=""
        />
        <img
          v-if="kitchenwareImg !== null"
          :src="kitchenwareImg"
          style="width: 90%"
          alt=""
        />
      </div>
      <div class="col-6 p-3">
        <div v-if="courseName !== null">課程名稱: {{ courseName }}</div>
        <div v-if="coursePrice !== null">單價: ${{ coursePrice }}</div>
        <div v-if="coursePrice !== null">
          金額: ${{ coursePrice * quantity }}
        </div>
        <div v-if="kitchenwareName !== null">
          商品名稱: {{ kitchenwareName }}
        </div>
        <div v-if="kitchenwarePrice !== null">
          單價: ${{ kitchenwarePrice }}
        </div>
        <div v-if="kitchenwarePrice !== null">
          金額: ${{ kitchenwarePrice * quantity }}
        </div>
        <el-input-number
          v-if="kitchenwareName !== null"
          v-model="products[index].quantity"
          @change="quantityChange(index)"
        />
      </div>
      <div class="col-2 p-3">
        <el-button
          @click="deleteByCartProductId(cartProductId)"
          type="primary"
          :icon="Delete"
        />
      </div>
    </div>

    <div class="row" v-if="!cartIsEmpty">
      勾選框
      <div class="col-2 p-3">
        <input
          class="form-check-input"
          type="checkbox"
          name="checkAll"
          id="checkboxNoLabel"
          @change="selectAll()"
          aria-label="..."
          v-model="allIsChecked"
        />
        <label class="form-check-label" for="checkboxNoLabel"> 全選 </label>
      </div>
      <div class="col-5"></div>
      <div class="col-3">
        <div style="font-size: 20px">total: ${{ total }}</div>
      </div>
      <div class="col-1">
        <el-button
          type="primary"
          round
          plain
          :disabled="!productIsChecked"
          @click="uploadStorage()"
          >去買單</el-button
        >
      </div>
      <div class="col-1">
      </div>
    </div>


  </div> -->
</template>

<script setup>
import { onMounted } from "vue";
import { ref, reactive, computed } from "vue";
import axios from "axios";
import { Delete, Edit, Search, Share, Upload, Picture, UploadFilled } from "@element-plus/icons-vue";
import { useCookie } from "vue-cookie-next";
import router from "../router";
import { useStorage } from "@vueuse/core";
import { ElMessage, ElMessageBox } from 'element-plus';

// cookie裡面有東西可以用
const cookie = useCookie();
const member = ref(cookie.getCookie("cookie"));
// cookie.setCookie(products.value)
console.log("cookie member.value.memberNumber" + member.value.memberNumber);

// const storage = useStorage();
// const member = storage("")

const products = ref([]);
const URL = import.meta.env.VITE_API_JAVAURL;

const test = useStorage("productsStorage");

const cartIsEmpty = ref(true); //勾選框
const allIsChecked = ref(false); //勾選框

//computed()數量有變動就會觸發 勾選框 -> total, element.checked, productIsChecked(button disabled)
const productIsChecked = ref(false);
const total = computed(() => {
  let sum = 0;

  let checkedProducts = products.value.filter((element) => {
    return element.checked;
  });
  console.log("checkedProducts", checkedProducts);
  if (checkedProducts.length == 0) {
    productIsChecked.value = false;
  } else {
    productIsChecked.value = true;
  }

  checkedProducts.forEach((element) => {
    if (element.courseId) {
      //condition只要不等於0就會動(JS only)
      sum += element.coursePrice * element.quantity;
    } else {
      sum += element.kitchenwarePrice * element.quantity;
    }
  });

  // products.value.forEach((element) => {
  //   if (element.courseId) {
  //     //condition只要不等於0就會動(JS only)
  //     sum += element.coursePrice * element.quantity;
  //   } else {
  //     sum += element.kitchenwarePrice * element.quantity;
  //   }
  // });

  return sum;
});

//跑出加入購物車的全部商品
const loadProducts = async (memberNumber) => {
  const URLAPI = `${URL}cartproductview/findbymembernumber/${memberNumber}`;
  let response = await axios.get(URLAPI);

  if (response.status == 200) {
    let orginalList = response.data;
    let test = [];
    let newList = orginalList.filter((element) => {
      if (test.includes(element.id.itemId)) {
        return;
      } else {
        test.push(element.id.itemId);
        return element;
      }
    });

    products.value = newList;
    products.value.forEach((element) => {
      //勾選框全選
      element.checked = false;
    });

    cartIsEmpty.value = false;
    console.log("test", products.value);
  } else {
    products.value = [];
    cartIsEmpty.value = true;
  }
};

//單一商品刪除
const deleteByCartProductId = async (cartProductId) => {
  console.log(cartProductId);

  const URLAPI = `${URL}cartproduct/remove/${cartProductId}`;
  const response = await axios.delete(URLAPI);
  console.log(cartProductId);
  if (response.status === 204) {
    loadProducts(member.value.memberNumber);
  }
  // if (window.confirm("真的要刪除嗎?")) {
  // }
};

//element-plus
const open = (cartProductId) => {
  ElMessageBox.confirm(
    '確定要刪除嗎??',
    '刪除商品提醒  : O',
    {
      confirmButtonText: '確定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      ElMessage({
        type: 'success',
        message: '刪除成功 : (',
      })
      deleteByCartProductId(cartProductId)
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消 : )',
      })
    })
}

//刪除勾選的商品
const removeCheckedBoxes = () => {
  // console.log(products.value)

  products.value.forEach(async (product) => {
    // console.log(product.checked)

    if (product.checked == true) {
      // console.log("success")
      const URLAPI = `${URL}cartproduct/remove/${product.cartProductId}`;
      const response = await axios.delete(URLAPI);
      if (response.status === 204) {
        loadProducts(member.value.memberNumber);
      }
    }
  });
};

//勾選框全選
const selectAll = () => {
  // allIsChecked.value=!allIsChecked;
  console.log(allIsChecked);
  products.value.forEach((element) => {
    element.checked = allIsChecked.value;
    // console.log(element.checked)
  });
};

//換checked的值

const changeChecked = (index) => {
  products.value[index].checked = !products.value[index].checked;

  // console.log(products.value[index].checked);
};

//增刪商品數量
const quantityChange = async (index) => {
  console.log(products.value[index]);

  const URLAPI = `${URL}cartproduct/modify/${products.value[index].cartProductId}`;
  let response = await axios.put(URLAPI, products.value[index]);

  console.log("response" + response);

  // console.log("products.value[index].quantity"+products.value[index].quantity)
};

//上傳cookie
const uploadCookie = () => {
  let jsonCookie = JSON.stringify(products.value);
  console.log("jsonCookie " + jsonCookie);
  cookie.setCookie("productsCookie", jsonCookie);
  router.push({ path: "/check" });
};

// 上傳localStorage
const uploadStorage = () => {
  // let jsonStorage = JSON.stringify(products.value);

  // console.log("jsonStorage "+jsonStorage);
  // localStorage.setItem('productsStorage', products.value)
  let a = products.value.filter((element) => {
    return element.checked;
  });
  console.log(a);
  // test.value = a;
  test.value = JSON.stringify(a);

  // console.log('test.value',test.value);
  // console.log('JSON.parse(useStorage("productsStorage").value',JSON.parse(useStorage("productsStorage").value));
  // console.log('useStorage("productsStorage")[0]',useStorage("productsStorage")[0]);

  router.push({ path: "/check" });
};

//載入時就跑
onMounted(() => {
  loadProducts(member.value.memberNumber);
});

//加入購物車按鈕的 創建購物車
const createCart = async () => {
  if (!cookie.isCookieAvailable("cookie")) {
    return;
  }

  let cartBean = {
    userAccountId: cookie.getCookie("cookie").memberNumber,
  };

  const URLAPI = `${URL}cart/create`;
  let response = await axios.post(URLAPI, cartBean);

  console.log("response" + response);

  // router.push({path:"/paid"})
};

///加入購物車按鈕的 廚具加入item
const createKitchenwareItem = async () => {
  if (!cookie.isCookieAvailable("cookie")) {
    return;
  }

  let itemBean = {
    kitchenwareId: requestBody.kitchenwareId,
  };

  const URLAPI = `${URL}item/create`;
  let response = await axios.post(URLAPI, itemBean);

  console.log("response" + response);

  // router.push({path:"/paid"})
};

///加入購物車按鈕的 課程加入item
const createCourseItem = async () => {
  if (!cookie.isCookieAvailable("cookie")) {
    return;
  }

  let itemBean = {
    courseId: requestBody.courseId,
  };

  const URLAPI = `${URL}item/create`;
  let response = await axios.post(URLAPI, itemBean);

  console.log("response" + response);

  // router.push({path:"/paid"})
};

///加入購物車按鈕的 item加入cartProduct
// const createCartProduct = async () => {
//   if(!cookie.isCookieAvailable('cookie')){
//         return
//     }

//     let cartProductBean = {
//         "productId": ,
//         "quantity":
//     }

//     const URLAPI = `${URL}cartproduct/create`;
//     let response = await axios.post(URLAPI, cartProductBean);

//     console.log("response"+response)

//     // router.push({path:"/paid"})

// };

// checkbox.checked = test.value;
//   function toggle(source) {
//   checkboxes = document.getElementsByName('foo');
//   for(var checkbox in checkboxes)
//     checkbox.checked = source.checked;
// }

// function check_all(obj,cName)
// {
//     var checkboxs = document.getElementsByName(cName);
//     for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
// }

// const change = () =>{
//    cartIsEmpty.value = !cartIsEmpty.value;
//   }

//   const test = () =>{
//     console.log(cartIsEmpty);
//   }
</script>

<style scoped></style>
