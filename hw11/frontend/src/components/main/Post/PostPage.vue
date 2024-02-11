<template>
  <div>
    <Post :post="post"/>
    <CreateComment :postId="post.id" v-if="user"/>
    <Comment v-for="comment in actualComments()"
             :key="comment.id"
             :comment="comment"/>
  </div>
</template>

<script>
import Comment from "@/components/main/Post/Comment.vue";
import Post from "@/components/main/Post/Post.vue";
import CreateComment from "@/components/main/Post/CreateComment.vue";
import axios from "axios";

export default {
  name: "PostPage",
  components: {CreateComment, Post, Comment},
  data: function () {
    return {
      comments: null
    }
  },
  props: ["post", "user"],
  beforeMount() {
    this.$root.$on("onWriteCommentSuccess", (comments, id) => {
      if (this.post.id === id) {
        // alert(this.post.id + " " + this.comments.length + " " + comments.length);
        this.comments = comments;
      }
    });
    axios.get("/api/1/posts/" + this.post.id + "/comments")
        .then(response => this.comments = response.data);
  },
  methods: {
    actualComments: function () {
      return this.comments;
    }
  }
}

</script>

<style scoped>

</style>