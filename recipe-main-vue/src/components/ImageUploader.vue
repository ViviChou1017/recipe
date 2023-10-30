<template>
  <label for="formFile" class="form-label">更新大頭貼test</label>
  <input class="form-control" type="file" id="formFile" @change="changeFile" />
</template>

<script setup>
import { ref, reactive } from "vue";

const base64 = ref();
const emit = defineEmits(["updateImg"]);

const changeFile = async (event) => {
  const file = event.target.files[0];
  base64.value = await convertBase64(file);
  update();
};

const convertBase64 = (file) => {
  return new Promise((resolve, reject) => {
    const fileReader = new FileReader();
    fileReader.readAsDataURL(file);

    fileReader.onload = () => {
      resolve(fileReader.result);
    };

    fileReader.onerror = (error) => {
      reject(error);
    };
  });
};

const form = reactive({
  title: " ",
  body: " ",
  image: base64,
});

const update = () => {
  emit("updateImg", base64.value);
};
</script>

<style scope></style>
