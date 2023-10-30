<template>
  <div class="row">
    <div class="col-6">
      <RouterLink to=sharon class="nav-link">
        <button class="btn btn-primary" type="button" @click="">測試</button>
      </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
const sharon = ref(0);

</script>

<style lang="scss" scoped></style>
