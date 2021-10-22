<template>
  <!--<div>
    <div align="center">
      <h2>Login</h2>
    </div>-->
  <member-login-form @submit="onSubmit" />
  <!--<v-spacer></v-spacer>
    <v-btn tile color="teal" @click="showSession">
      <v-icon left>
        ads_click
      </v-icon>
      세션 보기
    </v-btn>
    <v-spacer></v-spacer>
    <v-btn tile color="teal" @click="removeSession">
      <v-icon left>
        ads_click
      </v-icon>
      세션 끊기
    </v-btn>
    <v-spacer></v-spacer>
    <v-btn tile color="teal" @click="logout" v-if="isLogin">
      <v-icon left>
        ads_click
      </v-icon>
      로그 아웃
    </v-btn>
  </div>-->
</template>

<script>
import MemberLoginForm from "@/components/member/MemberLoginForm.vue";
export default {
  name: "MemberLoginPage",
  components: {
    MemberLoginForm,
  },
  data() {
    return {
      isLogin: false,
    };
  },
  mounted() {
    this.$store.state.session = this.$cookies.get("user");
    if (this.$store.state.session != null) {
      this.isLogin = true;
    }
  },
  computed: {
    ...mapState(["session"]),
  },
  methods: {
    ...mapActions(["fetchSession"]),
    onSubmit(payload) {
      if (this.$store.state.session == null) {
        const { id, password } = payload;
        axios
          .post("http://localhost:8888/petto/member/login", { id, password })
          .then(res => {
            if (res.data != "") {
              alert("로그인 성공! - " + JSON.stringify(res.data));
              this.isLogin = true;
              this.$store.state.session = res.data;
              this.$cookies.set("user", res.data, "1h");
              location.href = "/";
            } else {
              alert("로그인 실패! - " + res.data);
              this.isLogin = false;
            }
          })
          .catch(res => {
            alert(res.response.data.message);
          });
      } else {
        alert(
          "이미 로그인 되어 있습니다 - 계정: " + this.$store.state.session.id
        );
      }
    },
    logout() {
      this.$cookies.remove("user");
      this.isLogin = false;
      this.$store.state.session = null;
    },
  },
};
</script>