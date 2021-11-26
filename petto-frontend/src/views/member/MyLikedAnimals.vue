<template>
    <section>
        <div class="text-center">   
          <h4 style="text-align:center; padding: 3%;"><p class="normalText">내가 찜한 동물</p></h4> 
          <h6><p v-if="myLikedAnimals.length == 0">찜한 동물이 없습니다.</p> </h6>
            <v-row justify="center">
                <v-container class="justify center" style="margin-top:0%; margin-bottom:0%;">
                    <div class="container">
                        <div id="animal">
                            <div v-for="(animal, index) in calData" :key="index">
                                <v-card height="200" class="grow">
                                    <img :src="animal.image" style="cursor:pointer" width="230" height="230" @click="toDetailPage(animal.id)"/>
                                </v-card>   
                            </div>
                        </div>
                    </div>
                </v-container>
                <v-pagination :length= "numofpage" v-model="curpagenum" color="#42b8d4" style="background-color: white; box-shadow: 0px 0px white;"></v-pagination>
            </v-row>
        </div>
    </section>
</template>  

<script>

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
        
        if(this.$cookies.isKey("user")) {
  
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)
                this.fetchMyLikedAnimalList(this.session.memberNo)

                this.$store.state.isLoggedIn = true;
            }
        }
    },
    
    computed: {
    ...mapState(['myLikedAnimals', 'isLoggedIn']),

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
  } 
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

#v-card:hover {
  filter: brightness(120%);
  -webkit-filter: brightness(120%);
  
}
</style>