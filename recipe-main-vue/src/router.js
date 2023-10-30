import { createRouter, createWebHistory } from "vue-router";
import Home from "./views/Home.vue";
import CookingMethodSearch from "./views/CookingMethodSearch.vue";
import SignUpFirst from "./views/SignUpFirst.vue";
import SignUpSecond from "./views/SignUpSecond.vue";
import SignUpThird from "./views/SignUpThird.vue";
import SignUpFourth from "./views/SignUpFourth.vue";
import Signin from "./views/Signin.vue";
import MemberCenter from "./views/MemberCenter.vue";
import MemberSearch from "./views/MemberSearch.vue";
import MemberPage from "./views/MemberPage.vue";
import Cart from "./views/Cart.vue";
import Check from "./views/Check.vue";
import Paid from "./views/Paid.vue";
import SignUp from "./views/SignUp.vue";
import MemberCenterNew from "./views/MemberCenterNew.vue";

// 周蓉
import Course from "./views/course.vue";
import Kitchenware from "./views/kitchenware.vue";
import Recipe from "./views/Recipe.vue";
import Lesson from "./views/Lesson.vue";
import Item from "./views/Item.vue";
import SingleRecipe from "./views/SingleRecipe.vue";
//Kaki
import Classification from "./views/Classification.vue";
import Ingredient from "./views/Ingredient.vue";
import InsertRecipe from "./views/InsertRecipe.vue";

import OrderList from "./views/OrderList.vue";

const routes = [
  {
    //http://localhost/InsertRecipe
    path: "/InsertRecipe",
    component: InsertRecipe,
  },
  {
    //http://localhost/
    path: "/Home",
    component: Home,
  },
  {
    //http://localhost/about
    path: "/cookingMethodSearch",
    component: CookingMethodSearch,
  },
  {
    //http://localhost/signin
    path: "/signin",
    component: Signin,
  },
  {
    //http://localhost/signup
    path: "/sign-up-first",
    component: SignUpFirst,
  },
  {
    //http://localhost/sign-up-second
    path: "/sign-up-second",
    component: SignUpSecond,
  },
  {
    //http://localhost/sign-up-third
    path: "/sign-up-third",
    component: SignUpThird,
  },
  {
    //http://localhost/sign-up-fourth
    path: "/sign-up-fourth",
    component: SignUpFourth,
  },
  {
    //http://localhost/member-center
    path: "/member-center",
    component: MemberCenter,
  },
  {
    //http://localhost/member-search
    path: "/member-search",
    component: MemberSearch,
    props: (route) => ({ account: route.query.account }),
  },

  {
    //http://localhost/member-page
    //Number()避免資料類型驗證警告
    path: "/member-page",
    component: MemberPage,
    props: (route) => ({ memberNumber: Number(route.query.memberNumber) }),
  },
  {
    //http://localhost/cart
    path: "/cart",
    component: Cart,
  },
  {
    //http://localhost/check
    path: "/check",
    component: Check,
  },
  {
    //http://localhost/paid
    path: "/paid",
    component: Paid,
  },
  {
    //http://localhost/Item
    name: "Item",
    path: "/Item/:id", //帶參數的方式跳轉頁面
    component: Item,
  },
  {
    //http://localhost/Lesson
    name: "Lesson",
    path: "/Lesson/:id", //帶參數的方式跳轉頁面
    component: Lesson,
  },
  {
    //http://localhost/Recipe
    path: "/Recipe",
    component: Recipe,
    alias: "/",
  },
  {
    //http://localhost/kitchenwar
    path: "/kitchenware",
    component: Kitchenware,
  },
  {
    //http://localhost/course
    path: "/course",
    component: Course,
  },
  {
    //http://localhost/SingleRecipe
    name: "SingleRecipe",
    path: "/SingleRecipe/:id",
    component: SingleRecipe,
  },
  {
    path: "/classification",
    component: Classification,
  },
  {
    name: "ingredient",
    path: "/ingredient/:id",
    component: Ingredient,
  },

  {
    //http://localhost/order-list
    path: "/order-list",
    component: OrderList,
  },
  {
    //http://localhost/signin
    path: "/sign-up",
    component: SignUp,
  },
  {
    //http://localhost/member-center-new
    path: "/member-center-new",
    component: MemberCenterNew,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
