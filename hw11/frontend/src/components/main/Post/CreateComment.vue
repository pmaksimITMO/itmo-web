<template>
  <div class="form-comment">
    <div class="header">Write Comment</div>
    <div class="body">
      <form @submit.prevent="onWriteComment">
        <div class="field">
          <div class="value">
            <textarea id="text" name="text" v-model="commentText"/>
          </div>
        </div>
        <div class="error">{{ error }}</div>
        <div class="button-field">
          <input type="submit" value="Write">
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "CreateComment",
  data: function () {
    return {
      commentText: "",
      error: ""
    }
  },
  props: ["postId"],
  methods: {
    onWriteComment: function () {
      this.error = "";
      this.$root.$emit("onWriteComment", this.postId, this.commentText);
    }
  },
  beforeMount() {
    this.$root.$on("onWriteCommentSuccess", () => this.commentText = "");
    this.$root.$on("onWriteCommentValidationError", error => this.error = error);
  }
}
</script>

<style scoped>

</style>