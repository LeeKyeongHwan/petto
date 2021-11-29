<template>

    <v-navigation-drawer
      absolute
      permanent
      left
      width="16vw"
      color="grey lighten-2"
    >
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img src="https://s3.ap-northeast-2.amazonaws.com/elasticbeanstalk-ap-northeast-2-176213403491/media/magazine_img/magazine_280/5-3-%EC%8D%B8%EB%84%A4%EC%9D%BC.jpg">
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>관리자</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          router :to="item.link"
        >
          <v-list-item-icon>
            <v-icon style="margin-left:2vw;">{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
</template>

<script>
  export default {
    data () {
      return {
        id: '',
        items: [
          { title: '회원목록', icon: 'mdi-account', link:'/admin' },
          { title: '문의목록', icon: 'question_answer', link: '/qnalist' },
        ],
      }
    }
  }
</script>