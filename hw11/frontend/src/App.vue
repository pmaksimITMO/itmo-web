<template>
  <div id="app">
    <Header :user="user"/>
    <Middle :posts="posts" :user="user"/>
    <Footer/>
  </div>
</template>

<script>
import Header from "./components/Header";
import Middle from "./components/Middle";
import Footer from "./components/Footer";
import axios from "axios"

export default {
  name: 'App',
  components: {
    Footer,
    Middle,
    Header
  },
  data: function () {
    return {
      user: null,
      posts: []
    }
  },
  beforeMount() {
    if (localStorage.getItem("jwt") && !this.user) {
      this.$root.$emit("onJwt", localStorage.getItem("jwt"));
    }

    axios.get("/api/1/posts").then(response => {
      this.posts = response.data;
    });
  },
  beforeCreate() {
    this.$root.$on("onRegister", (login, name, password) => {
      if (login.trim() === "") {
        this.$root.$emit("onRegisterValidationError", "Login is required");
        return;
      }
      if (login !== login.toLowerCase()) {
        this.$root.$emit("onRegisterValidationError", "Login: expected lowercase latin letters");
        return;
      }
      if (name.trim() === "") {
        this.$root.$emit("onRegisterValidationError", "Name is required");
        return;
      }
      if (password.trim() === "") {
        this.$root.$emit("onRegisterValidationError", "Password is required");
        return;
      }

      axios.post("/api/1/users", {
        login, password, name
      }).then(response => {
        localStorage.setItem("jwt", response.data);
        this.$root.$emit("onJwt", response.data);
      }).catch(error => {
        this.$root.$emit("onRegisterValidationError", error.response.data);
      });
    });

    this.$root.$on("onEnter", (login, password) => {
      if (password === "") {
        this.$root.$emit("onEnterValidationError", "Password is required");
        return;
      }

      axios.post("/api/1/jwt", {
        login, password
      }).then(response => {
        localStorage.setItem("jwt", response.data);
        this.$root.$emit("onJwt", response.data);
      }).catch(error => {
        this.$root.$emit("onEnterValidationError", error.response.data);
      });
    });

    this.$root.$on("onJwt", (jwt) => {
      localStorage.setItem("jwt", jwt);

      axios.get("/api/1/users/auth", {
        params: {
          jwt
        }
      }).then(response => {
        this.user = response.data;
        this.$root.$emit("onChangePage", "Index");
      }).catch(() => this.$root.$emit("onLogout"))
    });

    this.$root.$on("onLogout", () => {
      localStorage.removeItem("jwt");
      this.user = null;
    });

    this.$root.$on("onWritePost", (title, text) => {
      if (this.user) {
        title = title.trim();
        text = text.trim();
        if (!title || title.length < 5) {
          this.$root.$emit("onWritePostValidationError", "Title is too short");
        } else if (!text || text.length < 10) {
          this.$root.$emit("onWritePostValidationError", "Text is too short");
        } else {
          axios.post("/api/1/posts", {
            title, text,
            jwt: localStorage.getItem("jwt")
          }).then(() => {
            axios.get("/api/1/posts").then(response => {
              this.posts = response.data;
              this.$root.$emit("onChangePage", "Index");
            });
          }).catch(error => {
            this.$root.$emit("onWritePostValidationError", error.response.data);
          });
        }
      } else {
        this.$root.$emit("onWritePostValidationError", "No access");
      }
    });

    this.$root.$on("onWriteComment", (postId, text) => {
      if (text.trim() === "") {
        this.$root.$emit("onWriteCommentValidationError", "Text is required");
        return;
      }
      const url = "/api/1/posts/" + postId + "/comments";
      axios.post(url, {
        text,
        jwt: localStorage.getItem("jwt")
      }).then(() => axios.get(url).then(response => {
        this.$root.$emit("onWriteCommentSuccess", response.data, postId);
      })).catch(error => {
        this.$root
            .$emit("onWriteCommentValidationError", error.response.data);
      });
    });
  }
}
</script>

<style>
#app {

}
</style>
