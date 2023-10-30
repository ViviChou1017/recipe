<template>
  <el-dialog
    title="上傳大頭貼"
    v-model="dialogVisible"
    :show-close="false"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    destroy-on-close
    width="600px"
  >
    <div class="avatar-container">
      <!-- {{dialogVisible}} -->
      <!-- 待上传图片 -->
      <div v-show="!options.img">
        <el-upload
          class="upload"
          ref="elUpload"
          action="#"
          :on-change="upload"
          accept="image/png, image/jpeg, image/jpg"
          :show-file-list="false"
          :auto-upload="false"
        >
          <el-button slot="trigger" type="primary" ref="uploadBtn">
            請選擇圖片
          </el-button>
        </el-upload>
        <div>只支援jpg、png格式，檔案大小不超過5MB</div>
      </div>
      <!-- <img :src="options.img" alt=""> -->
      <!-- 已上传图片 -->
      <div v-show="options.img" class="avatar-crop">
        <vueCropper
          class="crop-box"
          ref="cropper"
          :img="options.img"
          :autoCrop="options.autoCrop"
          :fixedBox="options.fixedBox"
          :canMoveBox="options.canMoveBox"
          :autoCropWidth="options.autoCropWidth"
          :autoCropHeight="options.autoCropHeight"
          :centerBox="options.centerBox"
          :fixed="options.fixed"
          :fixedNumber="options.fixedNumber"
          :canMove="options.canMove"
          :canScale="options.canScale"
        ></vueCropper>
      </div>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <div class="reupload" @click="reupload">
          <span v-show="options.img">重新選擇</span>
        </div>
        <div>
          <el-button round @click="closeDialog">返 回</el-button>
          <el-button type="primary" round @click="getCrop">確 定</el-button>
        </div>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { ref, reactive, defineComponent } from "vue";
import { VueCropper } from "vue-cropper";
import "vue-cropper/dist/index.css";
import type { UploadProps, UploadUserFile } from "element-plus";
import { ElMessage } from "element-plus";
export default defineComponent({
  props: {
    dialogVisible: {
      type: Boolean,
    },
  },
  components: {
    VueCropper,
  },
  setup(props, { emit }) {
    // console.log(11111);
    const cropper = ref();
    const uploadBtn = ref();
    const elUpload = ref();
    const options = reactive({
      img: "", // 原图文件

      autoCrop: true, // 默认生成截图框
      fixedBox: false, // 固定截图框大小
      canMoveBox: true, // 截图框可以拖动
      autoCropWidth: 200, // 截图框宽度
      autoCropHeight: 200, // 截图框高度
      fixed: true, // 截图框宽高固定比例
      fixedNumber: [1, 1], // 截图框的宽高比例
      centerBox: true, // 截图框被限制在图片里面
      canMove: false, // 上传图片不允许拖动
      canScale: false, // 上传图片不允许滚轮缩放
    });
    // vueCropper组件 裁剪配置信息

    // 读取原图
    const upload: UploadProps["onChange"] = (file, uploadFiles) => {
      //console.log(file);

      const isIMAGE =
        file.raw.type === "image/jpeg" || file.raw.type === "image/png";
      const isLt5M = file.raw.size / 1024 / 1024 < 5;
      if (!isIMAGE) {
        ElMessage.warning("请选择 jpg、png 格式的图片");
        return false;
      }
      if (!isLt5M) {
        ElMessage.warning("图片大小不能超过 5MB");
        return false;
      }
      let reader = new FileReader();

      reader.readAsDataURL(file.raw);
      //console.log(reader);

      reader.onload = (e) => {
        options.img = e.target.result; // base64
      };
      elUpload.value.clearFiles(); //这里处理重新上传时，upload组件change事件错误问题
    };
    // 获取截图信息
    const getCrop = () => {
      // 获取截图的 base64 数据
      cropper.value.getCropData((data) => {
        emit("test", data);
        emit("closeAvatarDialog");
      });
      // 获取截图的 blob 数据
      //console.log(cropper.value);
      //   cropper.value.getCropBlob((data) => {
      // console.log(111);
      // let formData = new FormData();
      //第三个参数是规定以什么为后缀，接口是根据后缀来返回地址格式的
      // formData.append("file", data, "chris.jpg");
      //上传接口
      // uploadApi(formData).then((res) => {});
      //console.log(data);

      // emit('closeAvatarDialog')
      // closeDialog();
      //   });
    };
    // 重新上传
    const reupload = () => {
      // console.log(uploadBtn.value.ref);

      uploadBtn.value.ref.click();
    };
    // 关闭弹框
    const closeDialog = () => {
      emit("closeAvatarDialog");
      options.img = "";
    };
    return {
      options,
      cropper,
      uploadBtn,
      elUpload,
      upload,
      getCrop,
      reupload,
      closeDialog,
    };
  },
});
</script>

<style lang="scss" scoped>
.dialog-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 14px;
  .reupload {
    color: #409eff;
    cursor: pointer;
  }
}
.avatar-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 560px;
  height: 350px;
  background-color: #f0f2f5;
  margin-right: 10px;
  border-radius: 4px;
  .upload {
    text-align: center;
    margin-bottom: 24px;
  }
  .avatar-crop {
    width: 560px;
    height: 350px;
    position: relative;
    .crop-box {
      width: 100%;
      height: 100%;
      border-radius: 4px;
      overflow: hidden;
    }
  }
}
</style>
