<template>
    <div>
        <v-container id="nav">
                <admin-page-side-bar/>
            </v-container>
        <div align="center" style="margin:5% 0% 15% 0%;">
            <admin-member-read v-if="adminMember" :adminMember="adminMember"/>
            <p v-else>로딩중 ...... </p>

            <div>
                 <v-row justify="center" style="margin-top:2%;">
                    <v-dialog
                    v-model="dialog"
                    scrollable
                    max-width="300px"
                    >
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn
                        color="orange lighten-2"
                        dark
                        v-bind="attrs"
                        v-on="on"
                        style="margin:0.5%;"
                        >
                        권한
                        </v-btn>
                        <v-btn @click="onDelete" outlined style="margin:0.5%;">삭제</v-btn>
                        <v-btn route :to="{ name: 'AdminMemberListPage' }" color="grey lighten-4" style="margin:0.5%;">
                            목록
                        </v-btn>
                    </template>
                    <v-card>
                        <v-card-title>멤버 권한 수정</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text style="height: 120px;">
                        <v-radio-group v-model="radioGroup" column>
                                    <v-radio color="orange" v-for="kinds in kindsOfMember" :key="kinds" :label="`${kinds}`"></v-radio>
                                </v-radio-group>
                        </v-card-text>
                        <v-divider></v-divider>
                        <v-card-actions>
                        <v-btn
                            color="blue darken-1"
                            text
                            @click="dialog = false"
                        >
                            cancle
                        </v-btn>
                        <v-btn
                            color="blue darken-1"
                            text
                            @click="onEdit()"
                        >
                            ok
                        </v-btn>
                        </v-card-actions>
                    </v-card>
                    </v-dialog>
                </v-row>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import AdminMemberRead from '../../components/admin/AdminMemberRead.vue'
import AdminPageSideBar from '@/components/admin/AdminPageSideBar.vue'

export default {
    name: 'AdminMemberReadPage',
    props: {
        memberNo: {
            type: Number,
            required: true
        }
    },
    components: {
        AdminMemberRead,
        AdminPageSideBar
    },
    computed: {
        ...mapState(['adminMember'])
    },
    created () {
        this.fetchAdminMember(this.memberNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.push()
                })
    },
    methods: {
        ...mapActions(['fetchAdminMember']),
        onDelete () {
            const { memberNo } = this.adminMember
            axios.delete(`http://localhost:8888/petto/admin/${memberNo}`)
                .then(() => {
                    alert('회원을 삭제했습니다.')
                    this.$router.push({ name: 'AdminMemberListPage' })
                })
                .catch(res => {
                    alert(res.response.data.message)
                })
        },
        onEdit () {
            const { memberNo } = this.adminMember
            const { radioGroup, kindsOfMember } = this
            const auth = radioGroup == 0 ? '개인' : '관리자'
            console.log(kindsOfMember)
            axios.put(`http://localhost:8888/petto/admin/${memberNo}`, { auth })
                    .then(() => {
                        alert('수정하였습니다.')
                        this.$router.push({
                            name: 'AdminMemberListPage',
                        })
                    })
                    .catch(err => {
                        alert(err.response.data.message)
                    })
        }
    },
    data() {
        return {
            access: '',
            dialog: false,
            radioGroup: 1,
            kindsOfMember: [
                '개인',
                '관리자'
            ],
            auth:''
        }
    },
    mounted() {
        if(this.$cookies.isKey('user') == true){
            this.access = this.$cookies.get('user').auth
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
    }
}
</script>