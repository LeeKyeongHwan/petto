<template>
    <v-container>
         <h4 style="text-align:center; padding: 3%;"><p class="normalText">내가 작성한 게시물</p></h4>
         <v-simple-table>
             <thead>
                <tr>
                <th class="text-left" style="width: 100px; " >no</th>
                <th class="text-left" style="width: 150px;">카테고리</th>
                <th class="text-left" style="width: 300px;">제목</th>
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
                <td >{{ myBoardList.reportNo}}</td>
                <td>{{ myBoardList.category}}</td>
                <td>{{ myBoardList.title}}</td>
                </tr>
            </tbody>
         </v-simple-table>
        <!--<div style="width: 100%; padding-top:6em;">
            <h3 style="text-align:center; margin:0%;"> 내가 찜한  동물 </h3>
        </div>
            
        <v-row justify="center">
            <v-container class="justify center" style="margin-top:0%; margin-bottom:0%;">
                <div class="container">
                    <div id="animal">
                        <div v-for="animal in myLikedAnimals" :key="animal.notice_no">
                            <v-card height="200" class="grow">
                                <img :src="animal.image" width="230" height="230" @click="toDetailPage(animal.report_no)"/>
                            </v-card>            
                        </div>
                    </div>
                </div>
            </v-container>
        </v-row>-->
    </v-container>
</template>  

<script>
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyboardPage',
    methods:{
    ...mapActions(['fetchMyBoardList']),

    toDetailPage(report_no) {
    this.$router.push({
    name: 'AnimalDetailPage',
    params: { "report_no": report_no }
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