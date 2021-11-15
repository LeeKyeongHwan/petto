<template>
    <div>
       <v-simple-table>
                <template v-slot:default>
                    <thead>
                        <tr>                         
                            
                            <th class="text-left">회원번호</th>
                            <th class="text-left">아이디</th>
                            <th class="text-left">이메일</th>
                            <th class="text-left">휴대폰번호</th>
                            <th class="text-left">이름</th>
                            <th class="text-left">생일</th>
                            <th class="text-left">키우는동물</th>
                            <th class="text-left">닉네임</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="member of members" :key="member.Id">
                            
                            <v-checkbox
                            v-model="selected"
                            label=""
                            :value="member.userId" 

                            ></v-checkbox>
                            <td style="color: gray">{{ member.memberNo }}</td>
                            <td>{{ member.Id }}</td>
                            <td>{{ member.password }}</td>
                            <td>{{ member.email }}</td>
                            <td>{{ member.phoneNumber }}</td>
                            <td>{{ member.name}}</td>
                            <td>{{ member.birthday }}</td>
                            <td>{{ member.petsRaised }}</td>
                            <td>{{ member.nickname }}</td>
                            

                        </tr>
                    </tbody>   

    </div>
</template>


<script>
import axios from 'axios'
import { mapState, mapActions } from 'vuex'
export default {
    
    name: 'MemberList',
    data() {
        return {
             headerTitle: [
                { text: '회원번호', value: 'memberNo', width: "50px" },
                { text: '아이디', value: 'Id', width: "100px" },
                { text: '이메일', value: 'email', width: "50px" },
                { text: '휴대폰번호', value: 'phoneNumber', width: "20px" },
                { text: '이름', value: 'name', width: "20px" },
                { text: '생일', value: 'birthday', width: "10px" },
                { text: '키우는동물', value: 'petsRaised', width: "500px" },
                { text: '닉네임', value: 'nickname', width: "30px" },
                
                
            ],
            selected: [],
            userId: null,
            dialog: false,
            
            
        }
    },
    props: {
        members: {
            type: Array,
            require: true
        }
    },
 
 
    methods: {
        ...mapActions(['fetchMember']),
        ...mapActions(['fetchSession']),
    cancel () {
      this.dialog = false
    },
      deleteUser () {
      if(this.selected.length == 1){
      axios.delete(`http://localhost:8888/member/remove/${this.selected}`).then(() => {
        this.dialog = false
        this.$store.commit('USER_LOGIN', false)
        
        this.fetchSession(this.$cookies.remove('session'))
        this.$store.commit('FETCH_USER_INFO', [])
        alert('탈퇴가 완료되었습니다.')
        this.$router.go();
      })
      }
      if(this.selected.length > 1){
        for(let i = 0; i < this.selected.length; i++){
      axios.delete(`http://localhost:8888/member/remove/${this.selected[i]}`).then(() => {
        this.dialog = false
        
        this.$store.commit('USER_LOGIN', false)
        
        this.fetchSession(this.$cookies.remove('session'))
        this.$store.commit('FETCH_USER_INFO', [])
        
        this.$router.go();
      })
      }
      alert('탈퇴가 완료되었습니다.')
      }
    }
       
    },
    computed: {
         ...mapState(['member']),
         ...mapState(['userInfo'])
    },
    mounted() {
    this.userId = this.userInfo.userId
    },
    
    
}
</script>