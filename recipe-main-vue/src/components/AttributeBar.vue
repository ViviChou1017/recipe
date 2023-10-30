<template>
  <div class="list-group-item list-group-item-action">
    <div class="row align-items-center">
      <div class="col-2">
        {{ props.title }}
      </div>
      <div class="col" v-if="!isEdit">
        {{ props.value !== null ? props.value : "未填寫" }}
      </div>
      <div class="col" v-if="isEdit">
        <!-- 字串輸入↓↓↓↓ -->
        <label for="validationServer01" class="form-label"></label>
        <input
          v-if="props.type == 'text'"
          type="text"
          :class="`form-control ${props.isValid ? 'is-valid' : 'is-invalid'}`"
          id="validationServer01"
          v-model="value"
          required
          @input="check"
        />
        <!-- 日期輸入↓↓↓↓ -->
        <input
          v-if="props.type == 'date'"
          type="date"
          :class="`form-control ${props.isValid ? 'is-valid' : 'is-invalid'}`"
          id="validationServer01"
          v-model="value"
          required
          @input="check"
        />
        <!-- 日期輸入↓↓↓↓ -->
        <!-- :value="value" -->
        <div class="valid-feedback">
          {{ props.validMessage !== null ? props.validMessage : "看起來不錯!" }}
        </div>
        <div id="invalidCheck3Feedback" class="invalid-feedback">
          {{
            props.invalidMessage !== null ? props.invalidMessage : "怪怪的喔!"
          }}
        </div>
      </div>
      <div class="col-2" v-if="!isEdit && editable"></div>
      <div class="col-2 float-end" v-if="!isEdit && editable">
        <div class="btn btn-primary col-12" @click.stop="edit">
          <i class="bi bi-pen-fill"></i>修改
        </div>
      </div>
      <div class="col-2" v-if="isEdit">
        <div
          :class="`btn btn-primary col-12 ${props.isValid ? '' : 'disabled'}`"
          @click.stop="update"
        >
          <i class="bi bi-floppy2-fill"> </i>
          儲存
        </div>
      </div>
      <div class="col-2" v-if="isEdit">
        <div class="btn btn-primary col-12" @click.stop="cancel">
          <i class="bi bi-x-square-fill"></i>
          取消
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const props = defineProps({
  title: String,
  value: String,
  isEdit: Boolean,
  isValid: Boolean,
  validMessage: String,
  invalidMessage: String,
  editable: Boolean,
  type: String,
});

const emits = defineEmits(["show", "update", "check"]);

const value = ref(props.value);

const edit = () => {
  emits("show", true);
};

const update = () => {
  if (props.isValid) {
    emits("update", value.value);
  }
};

const cancel = () => {
  value.value = props.value;
  check();
  emits("show", false);
};

const check = () => {
  emits("check", value.value);
};
</script>

<style scope></style>
