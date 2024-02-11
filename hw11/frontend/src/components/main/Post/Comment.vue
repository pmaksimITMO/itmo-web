<template>
  <div class="comment">
    <div class="header">
      {{ comment.user.name }}
    </div>
    <div class="text">
      <p v-for="line in comment.text.split('\n')" :key="line">
        {{ (line + " ").replaceAll(' ', "&nbsp;") }}
      </p>
    </div>
    <div class="footer">
      Time {{ comment.creationTime.split(".")[0].split("T").join(" ") }}
    </div>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props: ["comment"],
}
</script>

<style scoped>
  .comment {
    border: 1px solid dimgray;
    border-radius: 6px;
    margin: 0.5rem;
    padding: 0.5rem;
  }
  .header {
    font-weight: bold;
  }
</style>