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
};
</script>