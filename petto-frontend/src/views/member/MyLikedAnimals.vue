<template>
    <section>
        <div style="width: 100%; padding-top:6em;">
            <h3 style="text-align:center; margin:0%;"> 내가 찜한  동물 </h3>
        </div>
            
        <v-row justify="center">
            <v-container class="justify center" style="margin-top:0%; margin-bottom:0%;">
                <div class="container">
                    <div id="animal">
                        <div v-for="animal in myLikedAnimals" :key="animal.notice_no">
                            <v-card height="200" class="grow">
                                <img :src="animal.image" width="230" height="230" @click="toDetailPage(animal.id)"/>
                            </v-card>            
                        </div>
                    </div>
                </div>
            </v-container>
        </v-row>
    </section>
</template>  

<script>
//import axios from 'axios'
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyLikedAnimals',
    methods:{
    ...mapActions(['fetchMyLikedAnimalList']),

    toDetailPage(id) {
    this.$router.push({
    name: 'AnimalDetailPage',
    params: { "id": id }
        })
        },
    },
    mounted(){
        this.fetchMyLikedAnimalList(this.$store.state.session.memberNo) 
        
        if(this.$cookies.get("user").id) {
            this.$store.state.session = this.$cookies.get("user")
        }
    },
    
    computed: {
        ...mapState(['myLikedAnimals'])
    },


    
}
</script>