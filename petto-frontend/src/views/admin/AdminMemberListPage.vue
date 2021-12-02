<template>
<div>
    <v-container id="nav">
            <admin-page-side-bar/>
        </v-container>
    <div align="center" style="margin:5% 0% 5% 15%;">
        <h3>회원 목록</h3>
         <v-card-text style="text-align:right; padding-right:10em;">
            <v-select
                v-model="selectAuth"
                :items="auths"
                label="전체"
                attach
                outlined
                rounded  
                style="width: 150px; display: inline-block;"
                persistent-hint>
            </v-select>
        </v-card-text>
        <v-data-table
                style="width:1000px;"
                :headers="headers"
                :items="memberList()"
                :items-per-page="10"
                loading-text="정보를 불러오는 중입니다."
                class="elevation-1"
               >

                <template v-slot:item="{ item }">
     
                        <tr @click="toReadPage(item.memberNo)" style="overflow:hidden;">
                            <v-chip :color="getColor(item.auth)" class="ma-2 auth-view"> 
                            <td><p>{{ item.auth }}</p></td></v-chip>

                            <td><p>{{ item.id }}</p></td>
                            <td><p>{{ item.regDate }}</p></td>
                        </tr>
                </template>

            </v-data-table>

    </div>
</div>
</template>

<script>
import { mapState } from 'vuex'
import AdminPageSideBar from '../../components/admin/AdminPageSideBar.vue'
export default {
    name: 'AdminMemberListPage',
    components: {
        AdminPageSideBar
    },
    computed: {
        ...mapState(['adminMembers'])
    },
    data() {
        return {
            headers:
                [   
                    { text: '권한', value: 'auth' },
                    { text: '아이디', value: 'id' },
                    { text: '날짜', value: 'regDate' },
                ],
            access:'',
            auths: ['전체','관리자','개인'],
            selectAuth: [],
        }
    },
    mounted () {
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
        this.$store.dispatch("fetchAdminMemberList") 
    },
    methods: {
        getColor (auth) {
            if (auth =='관리자') return 'orange lighten-3'
            else if (auth == '개인') return 'grey lighten-2'
        },
        memberList() {
            if(this.selectAuth == '전체'){
                return this.$store.state.adminMembers
            } else {
                var authList = []
                for(var i=0; i<this.$store.state.adminMembers.length; i++){
                    if(this.$store.state.adminMembers[i].auth.includes(this.selectAuth)){
                        authList.push(this.$store.state.adminMembers[i])
                    }
                }
                return authList
            }
        },
        toReadPage(memberNo) {
            this.$router.push({
                name:'AdminMemberReadPage',
                params: { memberNo: memberNo }
            })
        }
    },
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

#nav{
    overflow: hidden;
}

.auth-view{
    /* color: white; */
    margin-top:3%;
}
</style>