import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import VueCookies from "vue-cookies";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import { VueCookieNext } from "vue-cookie-next";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import { globalCookiesConfig } from "vue3-cookies";

globalCookiesConfig({
  expireTimes: "30d",
  path: "/",
  domain: "",
  secure: true,
  sameSite: "None",
});

const app = createApp(App)
  .use(ElementPlus)
  .use(VueCookies, { expires: "7d" })
  .use(router)
  .use(VueCookieNext);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

app.mount("#app");
