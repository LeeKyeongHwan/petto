<template>
    <v-container>
         <h4 style="text-align:center; padding: 3%;"><p class="normalText">내가 작성한 게시물</p></h4>
         <h3>내가 쓴 게시물</h3>
         <v-simple-table>
             <thead>
                <tr>
                <th class="text-left" style="width: 100px; " >no</th>
                <th class="text-left" style="width: 150px;">카테고리</th>
                <th class="text-left" style="width: 300px;">제목</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(myBoardList, idx) of myBoardList" :key="idx">
                <td >{{ myBoardList.myBoardList}}</td>
                <td>{{ myBoardList.category}}</td>
                <td @click="toDetailPage(myBoardList.reportNo)" style="cursor: pointer;">{{ myBoardList.title}}</td>
                </tr>
            </tbody>
         </v-simple-table>
    </v-container>
</template>  

<script>
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyboardPage',
    methods:{
    ...mapActions(['fetchMyBoardList']),

    toDetailPage(reportNo) {
    this.$router.push({
    name: 'ReportReadPage',
    params: { "reportNo": reportNo }
        })
        },
    },
    mounted(){
        this.fetchMyBoardList(this.$store.state.session.id)    
    },

    computed: {
        ...mapState(['myBoardList'])
    },


    
}
</script>