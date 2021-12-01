<template>
    <section>
        <div style="width: 100%; padding-top:6em;">
            <h4><p class="normalText" style="text-align:center; margin:0%;">찜 리스트</p></h4>
        </div>
        <div class="text-center">
            <v-row justify="center">
                <v-container class="justify center" style="margin-top:0%; margin-bottom:0%;">
                    <div class="container">
                        <div id="animal">
                            <div v-for="(animal, index) in calData" :key="index">
                                <v-card height="300" width="370" class="grow">
                                    <img :src="animal.image" style="cursor:pointer" width="370" height="230" @click="toDetailPage(animal.id)"/>
                                        <v-card-title style="float: left; "> {{animal.carenm}}</v-card-title>


                                        <span style="float: right; margin-top:12px;">
                                            <v-tooltip bottom>

                                              <template v-slot:activator="{ on, attrs }">

                                              <v-btn text color="#FFB300" @click="toDetailPage(animal.id)" v-on="on" v-bind="attrs" >
                                                <v-icon>
                                                  assessment
                                                </v-icon>
                                              </v-btn>

                                              </template>

                                              <span>상세 페이지</span>

                                            </v-tooltip>

                                            <v-tooltip bottom>

                                              <template v-slot:activator="{ on, attrs }">

                                              <v-btn text color="#FFB300" @click="toFacilityInfo(animal.carenm)" v-on="on" v-bind="attrs"  >
                                                <v-icon>
                                                  place
                                                </v-icon>
                                              </v-btn>

                                              </template>

                                              <span>보호소 정보</span>

                                            </v-tooltip>
                                          </span>
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
import axios from 'axios'
import { mapActions, mapState } from 'vuex';
export default {
    name:'MyLikedAnimals',
    data () {
    return {
      curpagenum : 1,
      datapage : 12,
    }
    },
    methods:{
      ...mapActions(['fetchMyLikedAnimalList', 'fetchAlarmList']),

    toDetailPage(id) {
    this.$router.push({
    name: 'AnimalDetailPage',
    params: { "id": id }
        })
    },
    toFacilityDetailPage(carenm) {
      this.$router.push({
        name: 'FacilityReadPage',
        params: { 'facilityNo': carenm }
      });
    },
    toFacilityInfo(carenm) {
            axios.get(`http://localhost:8888/petto/facility/getFacilityNoAndAddr/${carenm}`)
                .then((res) => {

                    if(res.data != '') {

                        this.$router.push({
                            name: 'FacilityReadPage',
                            params: { "facilityNo": res.data[0], "facilityAddr": res.data[1] }
                        })

                    } else {
                        this.$router.push({
                            name: 'ExceptionPage',
                            params: { 'facilityName': carenm }
                        })
                    }
                })
                .catch(() => {
                    alert('잠시후에 다시 시도해주세요.')
                })
        },

    },
    mounted(){
      if(this.$cookies.isKey("user")) {

        this.$store.state.session = this.$cookies.get("user");

        if(this.$store.state.session != null) {
            this.$store.dispatch('fetchAlarmList', this.session.id)
            this.fetchMyLikedAnimalList(this.session.memberNo)
        }
      }
    },
    
     computed: {
    ...mapState(['myLikedAnimals', 'session']),

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

</style>