<template>
    <section>
        <div style="width: 100%; padding-top:6em;">
            <h4><p class="normalText" style="text-align:center; margin:0%;">내가 찜한 동물</p></h4>
        </div>
        <div class="text-center">
            <v-row justify="center">
                <v-container class="justify center" style="margin-top:0%; margin-bottom:0%;">
                    <div class="container">
                        <div id="animal">
                            <div v-for="animal in calData" :key="animal.notice_no">
                                <v-card height="200" class="grow">
                                    <img :src="animal.image" style="cursor:pointer" width="230" height="230" @click="toDetailPage(animal.id)"/>
                                </v-card>
                            </div>
                        </div>
                    </div>
                </v-container>
                <v-pagination :length= "numofpage" v-model="curpagenum" color="#42b8d4" style="background-color: white; box-shadow: 0px 0px white;"> </v-pagination>
            </v-row>
        </div>
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
        <div class="text-center">    
            <v-row justify="center">
                <v-container class="justify center" style="margin-top:0%; margin-bottom:0%;">
                    <div class="container">
                        <div id="animal">
                            <div v-for="animal in calData" :key="animal.notice_no">
                                <v-card height="200" class="grow">
                                    <img :src="animal.image" style="cursor:pointer" width="230" height="230" @click="toDetailPage(animal.id)"/>
                                </v-card>   
                            </div>
                        </div>
                    </div>
                </v-container>
                <v-pagination :length= "numofpage" v-model="curpagenum" color="#42b8d4" style="background-color: white; box-shadow: 0px 0px white;"> </v-pagination>
            </v-row>
        </div>
    </section>
</template>  

<script>

import { mapActions, mapState } from 'vuex';
export default {
    name:'MyLikedAnimals',
    data () {
    return {
      curpagenum : 1,
      datapage : 12
    }
    },
//import axios from 'axios'
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyLikedAnimals',
    data () {
    return {
      curpagenum : 1,
      datapage : 12
    }
    },
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
    ...mapState(['myLikedAnimals']),
    startOffset() {
      return ((this.curpagenum - 1) * this.datapage);
    },
    endOffset() {
      return (this.startOffset + this.datapage);

    },
    numofpage() {
        return Math.ceil(this.myLikedAnimals.length / this.datapage);
    },
    calData() {
      return this.myLikedAnimals.slice(this.startOffset, this.endOffset);
    }
  },
        ...mapState(['myLikedAnimals'])
    },
    numofpage() {
        return Math.ceil(this.myLikedAnimals.length / this.datapage);
    },
    calData() {
      return this.myLikedAnimals.slice(this.startOffset, this.endOffset);
    }    
  },    
}
</script>

<style lang="scss" scoped>

#header ul {
  display: flex;
  justify-content: center;
  align-items: center;
}

.container{
  width: 100vw;
  margin-top: 5%;
  margin-bottom: 10%;
  position: relative;
}

#animal{
  width: 100%;
}

#animal div{
  display:inline-block;
  position: relative;
  margin: 0px 0px 13px 13px;
}

.grow {
	transition-property: transform;
	&:hover {
        transform: scale(1.1);
    }
}
</style>