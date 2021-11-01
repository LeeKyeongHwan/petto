<template>
    <div align="center" style="padding-top: 60px;">

        <p class="mainText">전국 보호소 리스트</p>

        <v-container style="margin-top: 60px;">
            
            <div style="float: right;">
                <v-select
                    v-model="selectedAreas"
                    :items="areas"
                    label="지역 선택"
                    multiple
                    style="width: 250px; display: inline-block; margin-right: 40px; font-size: 12px;"
                    persistent-hint>
                </v-select>

                <v-select
                    v-model="selectedAnimals"
                    :items="animals"
                    label="보호 동물 선택"
                    multiple
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

                        <tr @click="toReadPage(item.facilityNo, item.facilityAddr, item.facilityName)">

                            <td>{{ item.facilityName }}</td>
                            <td>{{ item.facilityAddr }}</td>
                            <td>{{ item.facilityTel }}</td>
                            <td>{{ item.orgName }}</td>
                            <td>{{ item.saveTrgtAnimal }}</td>
                        </tr>
                    </template>

            </v-data-table>
            
            <label style="float: right; margin-top: 30px;">
                검색 키워드
                <input v-model="searchKeyword" style="width: 200px;"/>
            </label>

        </v-container>

    </div>
</template>

<script>

import { mapState } from 'vuex'
export default {
    name: 'FacilityListPage',
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

            areas: [ '서울', '경기', '인천', '강원', '충북', '충남', '전북', '전남', '경북', '경남', '부산', '대구', '제주' ],
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

            return fl
        },
        toReadPage(facilityNo, facilityAddr, facilityName) {

            this.$router.push({
                name: 'FacilityReadPage',
                params: { facilityNo: facilityNo, facilityAddr: facilityAddr, facilityName: facilityName }
            })
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

tr:hover {
    font-family: 'Gowun Batang', serif;
    color: #42b8d4;
}

</style>