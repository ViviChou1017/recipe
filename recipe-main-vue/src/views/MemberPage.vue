<template>
  <div>
    {{ props.memberNumber }}
  </div>
</template>

<script setup>
import { ref, reactive, computed, watch } from "vue";
const props = defineProps({ memberNumber: Number });
</script>

<style scoped></style>
