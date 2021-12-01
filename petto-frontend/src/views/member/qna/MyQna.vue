<template>
    <v-container style="width: 50%;">
         <h4 style="text-align:center; padding: 3%;"><p class="normalText">1:1 문의</p></h4>
         <v-simple-table>
             <thead>
                <tr>
                <th class="text-center" style="width: 100px; " >답변상태</th>
                <th class="text-center" style="width: 400px;">제목</th>
                <th class="text-left" style="width: 150px;">작성자</th>
                <th class="text-left" style="width: 200px;">등록일자</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="myQnaList in calData" :key="myQnaList.qnaNo">
                    <td>
                  <v-card-text class="text-center">
                        <v-chip color="orange lighten-4">{{ myQnaList.answerState}}</v-chip>
                  </v-card-text></td>
                   <td  class="text-center" style="color:black;"><router-link :to="{ name: 'QnaReadPage',
                                    params: { qnaNo: myQnaList.qnaNo.toString() } }"> 
                        {{ myQnaList.title }}
                    </router-link></td>
                <td class="text-left">{{ myQnaList.writer}}</td>
                <td>{{ myQnaList.regDate}}</td>
                </tr>
            </tbody>
         </v-simple-table>
         <p style="text-align:center; margin:20px;" v-if="myQnaList.length == 0">문의 내역이 없습니다. </p>       
         <v-btn style="float: right;"><v-icon color="blue">create</v-icon><router-link :to="{ name: 'QnaWritePage' }">&nbsp; 문의하기</router-link></v-btn>
         <v-pagination  :length= "numofpage" v-model="curpagenum" color="#42b8d4" style="background-color: white; box-shadow: 0px 0px white; margin-bottom:130px;"> </v-pagination>
    </v-container>
</template>  

<script>
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyQna',
    data () {
    return {
      curpagenum : 1,
      datapage : 10
    }
    },
    methods:{

    ...mapActions(['fetchMyQnAList', 'fetchAlarmList']),

    toDetailPage(qnaNo) {
            this.$router.push({
                name: 'QnaReadPage',
                params: { "qnaNo": qnaNo }
            })
        },
    },
    mounted(){
        if(this.$cookies.isKey("user")) {

            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.$store.state.session.id)
                this.fetchMyQnAList(this.$store.state.session.id)
                this.$store.state.isLoggedIn = true;
            }
        }
    },

    computed: {
        ...mapState(['myQnaList', 'session']),
        
        startOffset() {
            return ((this.curpagenum - 1) * this.datapage);
        },
        endOffset() {
            return (this.startOffset + this.datapage);
        },
        numofpage() {
            return Math.ceil(this.myQnaList.length / this.datapage);
        },
        calData() {
            return this.myQnaList.slice(this.startOffset, this.endOffset);
        }    
    },


    
}
</script>