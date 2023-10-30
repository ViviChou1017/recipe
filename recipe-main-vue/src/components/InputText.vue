<template>
  <div class="row g-3 align-items-center p-3">
    <div class="col-1">
      <label for="inputPassword6" class="col-form-label">
        <slot name="title">Title</slot></label
      >
    </div>
    <div class="col-6">
      <slot name="input"> </slot>
    </div>
    <div class="col-auto">
      <slot name="message"><span id="passwordHelpInline" class="form-text">
  
      </span> </slot>
      
    </div>
  </div>
</template>

<script setup></script>

<style></style>
