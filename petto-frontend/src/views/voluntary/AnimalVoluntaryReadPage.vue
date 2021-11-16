<template>
    <div id="voluntaryboard">
        <v-container class="justify center" style="margin-top:5%;">
            <p>| 자원봉사 |</p>
        </v-container>
        <v-divider></v-divider>

        <animal-voluntary-read v-if="voluntaryboard" :voluntaryboard="voluntaryboard"/>

        <p v-else>로딩중 ...... </p>

        <v-container class="justify center" style="margin-bottom:5%; margin-top:5%;" >
            <v-col cols="12" md="12"> 
            <v-btn color="blue" 
            class="ma-2 white--text" route :to="{ name: 'AnimalVoluntaryListPage' }">
            목록보기
            </v-btn> 
            <v-btn type="submit" color="blue darken-2"
            class="ma-2 white--text" route :to="{ name: 'AnimalVoluntaryModifyPage', params: { volunteerNo } }">
            게시글 수정
            </v-btn>
            <v-btn color="blue darken-2" outlined
            class="ma-2 white--text" @click="onDelete">
            게시글 삭제
            </v-btn>
            </v-col>   
        </v-container>

        <!-- 댓글기능 -->
        <v-divider></v-divider>

        <v-container class="justify center">
            <v-col cols="12" md="12">
            <v-textarea outlined type="comment" name="comment" v-model="comment"
             style="width:55em;float:left; margin-top:1em;"></v-textarea>
            <v-btn style="height:5em;" color="blue" class="ma-3 white--text" @click="onSubmit()">확인</v-btn>
            </v-col>
        </v-container>

        <v-divider></v-divider>
    </div>
</template>

<script>
import AnimalVoluntaryRead from '@/components/voluntary/AnimalVoluntaryRead.vue'
import { mapState, mapActions } from 'vuex'
import axios from 'axios'


export default {
    name: 'AnimalVoluntaryReadPage',
    props: {
        volunteerNo: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            comment: '',
            userInfo: this.$cookies.isKey("user")
        }
    } ,
    components: {
        AnimalVoluntaryRead
    },
    computed: {
        ...mapState(['voluntaryboard'])
    },
    created () {
        this.fetchVoluntaryBoard(this.volunteerNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.push()
                })
    },
    methods: {
        ...mapActions(['fetchVoluntaryBoard']),
        onSubmit() {
            if(this.userInfo == true){
                alert('댓글이 입력되었습니다.')
            } else {
                alert('로그인이 필요한 서비스입니다')
            }
        },
        onDelete () {
            const { volunteerNo } = this.voluntaryboard
            axios.delete(`http://localhost:8888/petto/voluntaryBoard/${volunteerNo}`)
                .then(() => {
                    alert('게시글을 삭제했습니다.')
                    this.$router.push({ name: 'AnimalVoluntaryListPage' })
                })
               .catch(res => {
                    alert(res.response.data.message)
                })
        }
    }
}
</script>



