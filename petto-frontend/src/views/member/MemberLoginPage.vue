<template>
  <member-login-form @submit="onSubmit" />
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
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
              //alert(JSON.stringify(this.$store.state.session))
              location.href = "/";

               this.fetchUserInfo(id)
                        if (res.data.auth == '관리자'){
                           this.$router.push({name: 'AuthPage'})
                       }else{
                        this.$router.push({name: 'Home'})
                       }
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
