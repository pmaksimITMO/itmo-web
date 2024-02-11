<template>
  <div>
    <Post v-for="post in posts"
          :post="post"
          :comments-count="0"
          :key="post.id"/>
  </div>
</template>

<script>

import Post from "@/components/main/Post/Post.vue";

export default {
  name: "Index",
  props: ["posts"],
  components: {Post}
}
</script>

<style scoped>

</style>