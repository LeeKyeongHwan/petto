<template>
    <div>
        <v-container id="nav">
            <admin-page-side-bar/>
        </v-container>
    <div align="center" style="margin:5% 0% 5% 15%;">
        <h3>문의글 목록</h3>

         <v-card-text style="text-align:right; padding-right:10em;">
            <v-select
            outlined
            v-model="selectLatest"
            :items="latest"
            label="최신순"
            attach    
            style="width: 100px; display: inline-block; margin-right: 40px;"
            persistent-hint>
            </v-select>
            <v-select
                outlined
                v-model="selectAnswerState"
                :items="answers"
                label="전체"
                attach
                style="width: 100px; display: inline-block; margin-right: 40px;"
                persistent-hint>
            </v-select>
        </v-card-text>

        <v-data-table
                style="width:1000px;"
                :headers="headers"
                :items="list()"
                :items-per-page="10"
                loading-text="정보를 불러오는 중입니다."
                class="elevation-1"
               >

                <template v-slot:item="{ item }"> 
                        <tr @click="toReadPage(item.qnaNo)" style="overflow:hidden;">
                            <v-chip :color="getColor(item.answerState)" class="ma-2 answer-view"> 
                            <td><p>{{ item.answerState }}</p></td></v-chip>
                            <td><p>{{ item.title }}</p></td>
                            <td><p>{{ item.writer }}</p></td>
                            <td><p>{{ item.regDate }}</p></td>
                        </tr>
                </template>

            </v-data-table>
    </div>
    </div>
</template>

<script>
import { mapState } from 'vuex'
import AdminPageSideBar from '@/components/admin/AdminPageSideBar.vue'
export default {
    name: 'QnaListPage',
    components:{
        AdminPageSideBar
    },
    data() {
        return {
            headers:
                [   
                    { text: '문의상태', align: 'center', value: 'answer' },
                    { text: '제목', value: 'title' },
                    { text: '작성자', value: 'writer' },
                    { text: '날짜', value: 'regDate' },
                ],
            latest: ['최신순','오래된순'],
            answers: ['전체','답변대기','답변완료'],
            selectLatest: [],
            selectAnswerState: [],
            access:''

        }
    },
    methods: {
        getColor (answer) {
            if (answer =='답변완료') return 'green'
            else if (answer == '답변대기') return 'orange'
        },
        list() {
            if(this.selectLatest == '' && this.selectAnswerState == '') {
                return this.$store.state.adminQnAList
            }
            if(this.selectLatest == '최신순' && ((this.selectAnswerState == '전체') || (this.selectAnswerState == ''))) {
                return this.$store.state.adminQnAList
            }
            if(this.selectLatest == '오래된순' && ((this.selectAnswerState == '전체') || (this.selectAnswerState == ''))) {
                return this.$store.state.adminQnAList.slice().reverse()
            }

            var boards = []
            for(var i=0; i<this.$store.state.adminQnAList.length; i++){
                if((this.selectAnswerState == '답변대기')){
                    if(this.$store.state.adminQnAList[i].answerState.includes('답변대기')){
                        boards.push(this.$store.state.adminQnAList[i])
                    }  
                } else if((this.selectAnswerState == '답변완료')){
                    if(this.$store.state.adminQnAList[i].answerState.includes('답변완료')){
                        boards.push(this.$store.state.adminQnAList[i])
                    }  
                } else {
                    return this.$store.state.adminQnAList
                }
            }
            if(this.selectLatest == '최신순' || this.selectLatest == ''){
                return boards.sort()
            } else {
                return boards.sort().slice().reverse()
            }
        },
        toReadPage(qnaNo) {
            console.log(qnaNo)
            this.$router.push({
                name:'AdminQnaReadPage',
                params: { qnaNo: qnaNo }
            })
        }
    },
    mounted() {
        if(this.$cookies.isKey('user') == true){
            this.access = this.$cookies.get('user').auth
            this.$store.state.session = this.$cookies.get("user");
            if(this.access != '관리자'){
                alert('권한이 필요한 서비스입니다')
                this.$router.push({
                name:'PettoHome',
                })
            }
        } else {
            alert('권한이 필요한 서비스입니다')
            this.$router.push({
            name:'PettoHome',
            })
        }
        this.$store.dispatch("fetchAdminQnAList")
    },
    computed: {
        ...mapState(['adminQnAList'])
    }
    
}
</script>

<style scoped>
@font-face {
    font-family: 'ChosunSm';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.1/ChosunSm.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'MapoGoldenPier';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoGoldenPierA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h3{
    font-family: 'ChosunSm';
}
p{
    font-family: 'MapoGoldenPier';
    font-size: 15px;
    margin: 0;
}

.answer-view{
    color: white;
    margin-top:3%;
}

#nav{
    overflow: hidden;
}
</style>
    