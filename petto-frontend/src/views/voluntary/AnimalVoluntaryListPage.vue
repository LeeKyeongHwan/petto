<template>
    <div id="voluntaryboard">   
        <v-container class="justify center" style="margin-top:5%;">
            <p>| 자원봉사 |</p>
        </v-container>
        <v-divider></v-divider>

        <animal-voluntary-list :voluntaryboards="voluntaryboards"/>

        <center>
            <v-btn color="blue" outlined route :to="{ name: 'AnimalVoluntaryRegister' }"
                style="margin-bottom:3%">글쓰기</v-btn>
        </center>
    </div>  
</template>

<script>
import { mapActions,mapState } from 'vuex'
import AnimalVoluntaryList from '@/components/voluntary/AnimalVoluntaryList.vue'

export default {
    name: 'BoardListPage',
    components: {
        AnimalVoluntaryList
    },
    data() {
        return {
            volTitle: '',
        }
    },
    computed: {
        ...mapState(['voluntaryboards'])
    },
    mounted () {
        this.fetchVoluntaryBoardList()
    },
    methods: {
        ...mapActions(['fetchVoluntaryBoardList']),

    }

}
</script>


