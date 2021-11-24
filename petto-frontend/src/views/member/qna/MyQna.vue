<template>
    <v-container>
         <h4 style="text-align:center; padding: 3%;"><p class="normalText">1:1 문의</p></h4>
         <v-simple-table>
             <thead>
                <tr>
                <!-- <th class="text-left" style="width: 100px; " >no</th> -->
                <th class="text-left" style="width: 100px; " >답변상태</th>
                <th class="text-left" style="width: 150px;">제목</th>
                <th class="text-left" style="width: 300px;">등록일자</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="myQnaList in calData" :key="myQnaList.qnaNo">
                <!-- <td >{{ myQnaList.qnaNo}}</td> -->
                <td >{{ myQnaList.answerState}}</td>
                <!-- <td @click="toDetailPage(myQnaList.qnaNo)" style="cursor: pointer;">{{ myQnaList.title}}</td> -->
                   <td style="color:black;"><router-link :to="{ name: 'QnaReadPage',
                                    params: { qnaNo: myQnaList.qnaNo.toString() } }"> 
                        {{ myQnaList.title }}
                    </router-link></td>
                <td>{{ myQnaList.regDate}}</td>
                </tr>
            </tbody>
         </v-simple-table>
         <p style="text-align:center; margin:20px;" v-if="myQnaList.length == 0">문의 내역이 없습니다. </p>       
         <v-btn style="float: right;"><router-link :to="{ name: 'QnaWritePage' }">글쓰기</router-link></v-btn>
         <v-pagination :length= "numofpage" v-model="curpagenum" color="#42b8d4" style="background-color: white; box-shadow: 0px 0px white;"> </v-pagination>
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
    ...mapActions(['fetchMyQnAList']),
    },
    mounted(){
        this.fetchMyQnAList(this.$store.state.session.id)    
    },

    computed: {
        ...mapState(['myQnaList']),
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