<template>
    <div id="voluntaryboard">   
        <v-container class="justify center" style="margin-top:5%;">
            <p>| 자원봉사 |</p>
        </v-container>
        <v-divider></v-divider>

        <v-container class="justify" style="text-align:right;">
            <v-btn color="orange" outlined route :to="{ name: 'AnimalVoluntaryRegister' }"
                style="margin:3% 0% 3%; 0%">봉사등록</v-btn>
        </v-container>

        <animal-voluntary-list :voluntaryboards="voluntaryboards"/>
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
            pageArray: []
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

