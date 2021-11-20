<!--<template>
  <div id="qnaboard" align="center">
    <h2>1:1 문의 (관리자)</h2>
    <qna-list :qnaboards="qnaboards"/>
  </div>
</template>

<script>
import { mapActions,mapState } from 'vuex'
import QnaList from '@/components/admin/qna/QnaList.vue'
export default {
    name: 'QnAListPage',
    components:{
        QnaList

    },
    computed: {
        ...mapState(['qnaboards'])
    },
    mounted () {
        this.fetchQnAList()
    },
    methods: {
        ...mapActions(['fetchQnAList']),
    }
}
</script>-->

<template>
    <div align="center" style="margin:50px">
        <h3>문의글 목록</h3>
        <!--  -->

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
                        <tr @click="toReadPage(item.qnaNo)">
                            <td>{{ item.answerState }}</td>
                            <td>{{ item.title }}</td>
                            <td>{{ item.writer }}</td>
                            <td>{{ item.regDate }}</td>
                        </tr>
                </template>

            </v-data-table>

    </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
    name: 'QnaListPage',
    data() {
        return {
            headers:
                [   
                    { text: '문의상태', value: 'answer' },
                    { text: '제목', value: 'title' },
                    { text: '작성자', value: 'writer' },
                    { text: '날짜', value: 'regDate' },
                ],
            latest: ['최신순','오래된순'],
            answers: ['전체','답변대기','답변완료'],
            selectLatest: [],
            selectAnswerState: [],

        }
    },
    methods: {
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
        if( this.$cookies.get("user").auth != '관리자'){
            alert('권한이 필요한 서비스입니다.')
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