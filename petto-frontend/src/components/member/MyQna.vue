<template>
    <v-container>
         <h4 style="text-align:center; padding: 3%;"><p class="normalText">1:1 문의</p></h4>
         <v-simple-table>
             <thead>
                <tr>
                <th class="text-left" style="width: 100px; " >no</th>
                <th class="text-left" style="width: 150px;">제목</th>
                <th class="text-left" style="width: 300px;">등록일자</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(myQnaList, idx) of myQnaList" :key="idx">
                <td >{{ myQnaList.qnaNo}}</td>
                <td @click="toDetailPage(myQnaList.qnaNo)" style="cursor: pointer;">{{ myQnaList.title}}</td>
                <td>{{ myQnaList.regDate}}</td>
                </tr>
            </tbody>
         </v-simple-table>
         <v-btn style="rihgt"><router-link :to="{ name: 'QnaWritePage' }">글쓰기</router-link></v-btn>
         
    </v-container>
</template>  

<script>
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyQna',
    methods:{
    ...mapActions(['fetchMyQnAList']),

    toDetailPage(qnaNo) {
    this.$router.push({
    name: 'QnaReadPage',
    params: { "qnaNo": qnaNo }
        })
        },
    },
    mounted(){
        this.fetchMyQnAList(this.$store.state.session.id)    
    },

    computed: {
        ...mapState(['myQnaList'])
    },


    
}
</script>