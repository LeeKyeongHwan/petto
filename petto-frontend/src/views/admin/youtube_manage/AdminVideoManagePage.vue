<template>
    <div align="center">
        <v-container>
            <p class="mainText">유튜브 영상 게시 리스트</p>
            <br><br>
            <v-simple-table class="normalText">
                <tr v-for="(vid, idx) in vidList" :key="idx">
                    <td style="font-size: 12px;" @click="toUrlPage(vid.url)">{{ vid.title }}</td>
                    <td>
                        <v-btn v-if="vid.allowedOrNot" text class="normalText" small style="float: right;"
                        color="#42b8d4"
                        outlined
                        @click="changeAllowedStatus(vid.videoNo, idx, 0)">
                            게시됨
                        </v-btn>
                        <!-- -->
                        <v-btn v-else-if="!vid.allowedOrNot" text class="normalText" small style="float: right;" 
                        color="red"
                        outlined
                        @click="changeAllowedStatus(vid.videoNo, idx, 1)">
                            게시불가
                        </v-btn>
                    </td>
                </tr>
            </v-simple-table>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex';
export default {
    data() {
        return {
            access: '',
            vidList: []
        }
    },
    mounted() {
        if(this.$cookies.isKey('user')) {
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
        this.getVidList()
    },
    computed: {
        ...mapState(['session'])
    },

    methods: {
        getVidList() {
            axios.get('http://localhost:8888/petto/admin/videos')
                .then((res) => {
                    //alert(JSON.stringify(res.data))
                    this.vidList = res.data
                })
        },

        toUrlPage(url) {
            window.open(url, 'newWindow');
        },

        changeAllowedStatus(videoNo, idx) {
            axios.patch(`http://localhost:8888/petto/admin/change_stat_vid/${videoNo}`)
                .then(() => {
                    if(this.vidList[idx].allowedOrNot) this.vidList[idx].allowedOrNot = false
                    else this.vidList[idx].allowedOrNot = true
                })
                .catch((err) => {
                    console.log(err.response.message)
                })
        }
    }
}
</script>

<style>

</style>