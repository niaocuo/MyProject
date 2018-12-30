<template>
  <transition-group
    tag="ul"
    :class="[
      'el-upload-list',
      'el-upload-list--picture-card',
      { 'is-disabled': disabled }
    ]"
    name="el-list"
  >
    <li
      v-for="(file, index) in files"
      :class="['el-upload-list__item', 'is-success' , focusing ? 'focusing' : '']"
      :key="index"
      tabindex="0"
      @keydown.delete="!disabled && $emit('remove', file)"
      @focus="focusing = true"
      @blur="focusing = false"
      @click="focusing = false"
    >
      <img
        class="el-upload-list__item-thumbnail"
        :src="root+file.url" alt=""
      >
      <label class="el-upload-list__item-status-label">
        <i :class="{
          'el-icon-upload-success': true,
          'el-icon-circle-check': false,
          'el-icon-check': true
        }"></i>
      </label>
      <span class="el-upload-list__item-actions" >
        <span
          class="el-upload-list__item-preview"
          v-if="handlePreview "
          @click="handlePreview(file)"
        >
          <i class="el-icon-zoom-in"></i>
        </span>
        <span
          v-if="!disabled"
          class="el-upload-list__item-delete"
          @click="$emit('remove', file)"
        >
          <i class="el-icon-delete"></i>
        </span>
      </span>

    </li>
  </transition-group>

</template>
<script>
  export default {

    data() {
      return {
        focusing: false,
        root:api
      };
    },
    props: {
      files: {
        type: Array,
        default() {
          return [];
        }
      },
      disabled: {
        type: Boolean,
        default: false
      },
      handlePreview: Function,

    },
    methods: {

    }
  };
</script>
