<template>
    <v-container>
         <h4 style="text-align:center; padding: 3%;"><p class="normalText">내 게시물</p></h4>
                   
         <v-simple-table>
             <thead>
                <tr>
                <th class="text-left" style="width: 100px; " >no</th>
                <th class="text-center" style="width: 80px;">카테고리</th>
                <th class="text-center" style="width: 300px;">제목</th>
                <th class="text-center" style="width: 150px;">작성일시</th>
                <th class="text-center" style="width: 100px;">조회수</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(myBoardList, idx) of calData" :key="idx">
                <td class="text-left">{{ myBoardList.reportNo}}</td>
                <td class="text-center">{{ myBoardList.category}}</td>
                <td class="text-center"><router-link :to="{ name: 'ReportReadPage',
                                    params: { reportNo: myBoardList.reportNo.toString() } }"> 
                        {{ myBoardList.title}}
                    </router-link></td>
                <td class="text-center">{{ myBoardList.regDate}}</td>
                <td class="text-center">{{ myBoardList.viewCnt}}</td>
                </tr>
            </tbody>
         </v-simple-table>
        <p style="text-align:center; margin:20px;" v-if="myBoardList.length == 0">작성한 게시물이 없습니다. </p>       
        <v-pagination :length= "numofpage" v-model="curpagenum" color="#42b8d4" style="background-color: white; box-shadow: 0px 0px white;"> </v-pagination>
    </v-container>
</template>  

<script>
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyboardPage',
    data () {
    return {
      curpagenum : 1,
      datapage : 10
    }
    },
    methods:{
    ...mapActions(['fetchMyBoardList'])
    },
    mounted(){
        if(this.$cookies.isKey("user")) {
  
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.$store.state.session.id)
                this.fetchMyBoardList(this.session.id)

                this.$store.state.isLoggedIn = true;
            }
        }
    },
    computed: {
        ...mapState(['myBoardList', 'session']),
        startOffset() {
            return ((this.curpagenum - 1) * this.datapage);
        },
        endOffset() {
            return (this.startOffset + this.datapage);
        },
        numofpage() {
            return Math.ceil(this.myBoardList.length / this.datapage);
        },
        calData() {
            return this.myBoardList.slice(this.startOffset, this.endOffset);
        }  
    }
}
</script>