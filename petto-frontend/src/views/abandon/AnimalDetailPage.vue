<template>
    <div align="center" style="padding-top: 60px;">

        <p class="mainText">유기동물 정보</p>

        <br/>

        <div style="display: inline-block; margin-right: 50px;">
            <img :src="animalsInfo.image" width="350" height="300"/>
        </div>

        <div style="display: inline-block;">
            
            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">공고 번호</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.notice_no }}</p>

            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">종</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.kind }}</p>
            
            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">색</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.color }}</p>

            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">나이</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.age }}</p>

            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">성별</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.gender }}</p>

            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">특징</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.feature }}</p>

            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">발견 일자</p> <!-- 형식 수정 필요 -->
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.happened }}</p>

            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">발견 장소</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.happenplace }}</p>

            <br/>
            <br/>
                   
            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">보호소명</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.carenm }}</p>
         
            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">보호소 전화번호</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.caretel }}</p>

            <br/>
            <br/>

            <p class="normalText" style="display: inline-block; margin-right: 20px; float: left;">보호소 주소</p>
            <p class="normalText" style="display: inline-block; color: black; margin-bottom: 20px; float: left;">{{ animalsInfo.careaddr }}</p>

        </div>

        <br/>
        <br/>

        <p class="normalText" style="color: black; margin-top: 20px;" @click="toFacilityInfo(animalsInfo.carenm)">보호소 바로가기</p>
    
    </div>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'

export default {
    name: 'AnimalDetailPage',
    props: {
        id: {
            type: String,
            required: true
        }
    },

    methods: {
        ...mapActions(['fetchAnimalInfo']),

        toFacilityInfo(carenm) {
            
            axios.get(`http://localhost:8888/petto/facility/getFacilityNoAndAddr/${carenm}`)
                .then((res) => {

                    this.$router.push({
                        name: 'FacilityReadPage',
                        params: { "facilityNo": res.data[0], "facilityAddr": res.data[1] }
                        
                    })
                })
                .catch(() => {
                    alert('잠시후에 다시 시도해주세요.')
                })
        }
    },
    mounted() {
        this.fetchAnimalInfo(this.id)
    },
    computed: {
        ...mapState(['animalsInfo'])
    }

}

</script>