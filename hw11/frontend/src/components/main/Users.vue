<template>
  <div class="users datatable">
    <div class="caption">User</div>
    <table>
      <thead>
      <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
      </tr>
      </thead>
      <tbody>
      <template v-if="users.length === 0">
        <tr class="noData">
          <td colspan="3">
            No data
          </td>
        </tr>
      </template>
      <template v-else>
        <tr v-for="user in users" v-bind:key="user.id">
          <td class="id">{{ user.id }}</td>
          <td class="login">{{ user.login }}</td>
          <td class="name">{{ user.name }}</td>
        </tr>
      </template>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Users",
  data: function () {
    return {
      users: []
    }
  },
  beforeMount() {
    axios.get("/api/1/users").then(response => {
      this.users = response.data;
    });
  }
}
</script>

<style scoped>

</style>
