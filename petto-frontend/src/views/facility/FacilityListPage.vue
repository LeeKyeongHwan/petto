<template>
    <div>
        <v-container class="justify center">
                <a href="/abandonedAnimal/list/page=0&place=none&kind=none" style="color:black;">
                <v-btn outlined style="margin:5%;" width="10vw">
                유기동물
                </v-btn>
                </a>
            <v-btn outlined route :to="{name: 'FacilityListPage'}" width="10vw">보호소 </v-btn>
        </v-container>
        <div align="center">
            <h1 style="margin-bottom:1%;">전국 보호소 리스트</h1>

            <v-container>
                <div style="float: right;">
                    <v-select
                        v-model="selectedAreas"
                        :items="areas"
                        label="지역 선택"
                        multiple
                        outlined
                        style="width: 250px; display: inline-block; margin-right: 40px; font-size: 12px;"
                        persistent-hint>
                    </v-select>

                    <v-select
                        v-model="selectedAnimals"
                        :items="animals"
                        label="보호 동물 선택"
                        multiple
                        outlined
                        style="width: 200px; display: inline-block; font-size: 12px;"
                        persistent-hint>
                    </v-select>
                </div>

                <v-data-table
                    style="margin-top: 100px;"
                    :headers="headers"
                    :items="filteredList()"
                    :items-per-page="10"
                    :search="searchKeyword"
                    loading-text="정보를 불러오는 중입니다."
                    class="elevation-1">

                    <template v-slot:item="{ item }"> 

                        <tr @click="toReadPage(item.facilityNo)">

                            <td>{{ item.facilityName }}</td>
                            <td>{{ item.facilityAddr }}</td>
                            <td>{{ item.facilityTel }}</td>
                            <td>{{ item.orgName }}</td>
                            <td>{{ item.saveTrgtAnimal }}</td>
                        </tr>
                    </template>

                </v-data-table>
                
                <label style="float: right; margin: 30px 0px 30px 0px;">
                    검색 키워드
                    <input v-model="searchKeyword" style="width: 200px;"/>
                </label>

            </v-container>

        </div>
    </div>
</template>

<script>

import { mapState } from 'vuex'
export default {
    name: 'FacilityListPage',
    props: {
        // selectedAreas: {
        //     type: Array,
        //     default: () => {
        //         return ''
        //     }
        // },
        // selectedAnimals: {
        //     type: Array,
        //     default: () => {
        //         return ''
        //     }
        // }
    },
    data() {
        return {
            headers:
                [
                    { text: '보호소명', value: 'facilityName' },
                    { text: '주소', value: 'facilityAddr' },
                    { text: '번호', value: 'facilityTel' },
                    { text: '지역', value: 'orgName' },
                    { text: '보호 동물', value: 'saveTrgtAnimal' }
                ],
            
            searchKeyword: '',

            areas: [ '서울', '경기', '인천', '강원', '충청', '대전', '전라북도', '전라남도', '경상북도', '경상남도', '부산', '대구', '제주' ],
            selectedAreas: [],

            animals: [ '개', '고양이', '기타' ],
            selectedAnimals: []

        }
    },
    methods: {
        filteredList() {

            if(this.selectedAreas == '' && this.selectedAnimals == '') return this.$store.state.facilityList

            var fl = []

            for(var i=0; i<this.$store.state.facilityList.length; i++) {

                if(this.selectedAreas.length > 0 && this.selectedAnimals.length > 0) {

                    var selectedAreasLen = this.selectedAreas.length
                    var selectedAnimalsLen = this.selectedAnimals.length

                    const length = selectedAreasLen > selectedAnimalsLen ? selectedAreasLen : selectedAnimalsLen
                
                    for(var j=0; j<length; j++) {

                        if(this.$store.state.facilityList[i].facilityAddr.includes(this.selectedAreas[j]) && 
                        this.$store.state.facilityList[i].saveTrgtAnimal.includes(this.selectedAnimals[j])) {

                            fl.push(this.$store.state.facilityList[i])
                        }
                    }
                }

                else if(this.selectedAreas.length > 0) {

                    for(var k=0; k<this.selectedAreas.length; k++) {

                        if(this.$store.state.facilityList[i].facilityAddr.includes(this.selectedAreas[k])) {

                            fl.push(this.$store.state.facilityList[i])
                        }
                    }
                }

                else if(this.selectedAnimals.length > 0) {

                    for(var l=0; l<this.selectedAnimals.length; l++) {

                        if(this.$store.state.facilityList[i].saveTrgtAnimal.includes(this.selectedAnimals[l])) {

                            fl.push(this.$store.state.facilityList[i])
                        }
                    }
                }
            }

            return fl.sort()
        },
        toReadPage(facilityNo) {
        
            let routeData = this.$router.resolve({
              name: 'FacilityReadPage',
              params: { "facilityNo": facilityNo }
            });window.open(routeData.href, '_blank')
        }
    },
    mounted() {
        this.$store.dispatch("fetchFacilityList")
    },
    computed: {
        ...mapState(['facilityList'])
    }
    
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@700&display=swap');
@font-face {
    font-family: 'GowunDodum-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h1 {
    font-family: 'GowunDodum-Regular';
    font-size: 30px;
    text-align: center;
    padding: 0% 0% 5% 0%;
}

tr:hover {
    font-family: 'Gowun Batang', serif;
    color: #42b8d4;
}

</style>