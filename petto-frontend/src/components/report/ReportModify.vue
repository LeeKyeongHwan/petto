<template>
    <div>

        <h1 style="margin-bottom: -80px;">공고 수정 양식</h1>
        <br>
        <br>
        <br>

        <v-container style="width: 51%; color: grey;" class="normalText">

            <div v-if="UploadedCnt > 0">           
                <p style="float: left;">해당 동물 사진</p>

                <br>
                <br>

                <span v-for="(num, idx) in UploadedCnt" :key="idx" style="position: relative;" :id="`imgBox${idx}`" class="imgBox">

                    <img :src="imgRequest(idx)" class="img" style="margin-top: 18px;" @click="removePic(idx)"/>
                    <p class="imgText d-none d-lg-block">클릭해서 삭제</p>

                </span>

                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
            </div>
            
            <form @submit.prevent="onSubmit">
                <div>
                    <v-select
                        v-model="keyword"
                        :items="items"
                        attach
                        chips
                        label="주제"
                        style="width: 120px; display: inline-block; float: left;"
                        class="normalText"
                    ></v-select>
                    
                    <input v-model="title" style="width: 70%; color: black; float: right; margin-top: 12px;" required class="normalText"/>

                    <br>
                    <br>
                    <br>
                    <br>
                    
                    <v-select
                        v-model="city"
                        :items="areas"
                        attach
                        chips
                        label="지역"
                        style="width: 100px; float: left;"
                        class="normalText"
                    ></v-select>

                    &ensp;

                    <input v-model="place" style="width: 70%; color: black; margin-top: 12px; float: right;" required class="normalText"/>

                    <br>
                    <br>
                    <br>
                    <br>

                    <label class="normalText" style="float: left; color: grey;">
                        해당일 &emsp;
                        <input type="date" v-model="date" style="width: 140px; color: black; margin-top: 12px;" required class="normalText"/>
                    </label>
                    
                    <br>
                    <br>
                    <br>
                    <br>

                    <v-select
                        v-model="breed"
                        :items="animals"
                        attach
                        chips
                        label="종"
                        style="width: 100px; float: left;"
                        class="normalText"
                    ></v-select>

                    <div v-if="breed == '기타'" style="float: left; margin-left: 40px;">
                        
                        <label class="normalText" style="float: left; color: grey;">
                            기타 종 명 &emsp;
                            <input v-model="etcAnimal" style="width: 200px; color: black; margin-top: 12px;" required class="normalText"/>
                        </label>
                    </div>

                    <br>
                    <br>
                    <br>
                    <br>

                    <label class="normalText" style="float: left; color: grey;">
                        특징 &emsp;
                        <input v-model="feature" style="width: 670px; color: black; margin-top: 12px;" required class="normalText"/>
                    </label>

                    <div v-if="keyword == '보호'">

                        <br>
                        <br>
                        <br>
                        <br>

                        <label class="normalText" style="float: left; color: grey;">
                            보호장소 &emsp;
                            <input v-model="keepingPlace" style="width: 650px; color: black; margin-top: 12px;" required class="normalText"/>
                        </label>
                    </div>

                    <br>
                    <br>
                    <br>
                    <br>

                    <v-textarea 
                    v-model="content"
                    clearable
                    clear-icon="mdi-close-circle"
                    class="normalText"
                    color="#42b8d4"
                    label="상세 내용"
                    style="width: 950px;"
                    height="10%;"
                    :rules="rules"
                    counter
                    auto-grow/>
                </div>

                <br>
                <br>

                <label class="normalText" style="float: left; color: grey;">
                    사진 &emsp;
                    <input multiple type="file" ref="files" class="normalText" style="color: grey;" v-on:change="handleFileUpload()"/>
                </label>

                <span style="float: right;">
                    <v-btn text class="normalText" type="submit">
                        수정 제출
                    </v-btn>

                    &emsp;

                    <v-btn text class="normalText" @click="goBack">
                        뒤로
                    </v-btn>
                </span>
            </form>

        </v-container>

        <br>
        <br>
        <br>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    props: {
        report: {
            type: Object, 
            required: true
        }
    },
    data() {
        return {
            items: [ '실종', '보호', '목격' ],
            areas: [ '서울', '경기', '인천', '강원', '충청', '대전', '전라북도', '전라남도', '경상북도', '경상남도', '부산', '대구', '제주' ],
            animals: [ '개', '고양이', '기타' ],

            title: this.report.title,
            keyword: this.report.category,
            date: this.report.whenHappened,

            city: this.report.whereHappened.split(' ')[0],
            place: this.report.whereHappened.replace(this.report.whereHappened.split(' ')[0], ''),

            breed: this.report.breed == '개' || this.report.breed == '고양이' ? this.report.breed : '기타',
            etcAnimal: this.report.breed,

            feature: this.report.feature,
            keepingPlace: this.report.keepingPlace,
            content: this.report.content,

            UploadedCnt: parseInt(this.report.imgUploadedCnt),
            pics: '',
            deletePicsArr: [],
            rules: [v => v.length <= 300 || '300자 이내 작성']
        }
    },
    methods: {

        imgRequest(idx) {
            try {                
                return require(`../../../../petto-backend/image/animalPics/${this.report.writer}-${this.report.imgUploadedTime}/${this.report.category}-${idx}.jpg`)
            } catch(e) {
                return require(`@/assets/logo.png`)
            }
        },

        removePic(idx) {

            const removedPic = document.querySelector(`#imgBox${idx}`)

            removedPic.className = 'removePic'

            this.deletePicsArr.push(idx)
        },

        handleFileUpload() {
            this.pics = this.$refs.files.files
        },

        onSubmit() {

            if(this.$cookies.get("user").id == this.report.writer) {

                let formData = new FormData()

                const prevFolderName = this.report.writer + '-' + this.report.imgUploadedTime
                const deletePicsArr = this.deletePicsArr

                let now = new Date()
                const dateId = now.toLocaleString().toString().replace(/ /gi, "").replace(/:/gi, "-")

                if(this.pics.length > 0) {

                    for(var i=0; i<this.pics.length; i++) {
                        formData.append("animalPics", this.pics[i])
                    }
                } 

                formData.append("prevFolderName", prevFolderName)
                formData.append("deletePicsArr", deletePicsArr)
                formData.append("category", this.keyword)
                formData.append("id", this.$store.state.session.id)
                formData.append("dateId", dateId)

                axios.put('http://localhost:8888/petto/report/modifyPics', formData)
                    .then((res) => {
                        alert(res.data) //서버에서 받은 숫자 cnt
                        alert(this.report.reportNo)

                        if(this.breed == '기타') this.breed = this.etcAnimal
                
                        const whereHappened = this.city + ' ' + this.place

                        const form = { reportNo: this.report.reportNo, category: this.keyword, title: this.title, writer: this.$store.state.session.id , whereHappened: whereHappened,
                        whenHappened: this.date, keepingPlace: this.keepingPlace, breed: this.breed, feature: this.feature, content: this.content, viewCnt: this.report.viewCnt }

                        form.imgUploadedTime = dateId
                        form.imgUploadedCnt = res.data

                        this.$emit('submit', form)

                    })
                    .catch(() => {
                        alert('잠시 후에 다시 시도해주세요.')
                    })

            } else alert('권한이 없습니다!')
        },

        goBack() {
            window.history.go(-1)
        }
    }
}
</script>

<style scoped>

@font-face {
    font-family: 'GowunDodum-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

h1 {
    font-family: 'GowunDodum-Regular';
    font-size: 25px;
    text-align: center;
    padding: 5% 0% 5% 0%;
}

img {
    width: 40%; 
    margin-right: 20px;
    border-radius: 8px;
    border: 3px solid #42b8d4;
}

.removePic {
    display: none;
}

p {
    position: absolute; 
    top: -170px; 
    left: 150px; 
    text-align: center;
}

.img:hover {
    filter: blur(1px) grayscale(100%); 
    opacity: 0.6;
}

.imgBox:hover .imgText {
    color: white;
    font-size: 20px;
    margin-left: -20px;
}
</style>

