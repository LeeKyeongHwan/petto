<template>
    <div>

        <br>
        <br>
        <br>

        <v-container style="width: 51%;">
            
            <form @submit.prevent="onSubmit">
              
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
                    label="작성 내용"
                    style="width: 950px;"
                    height="10%;"
                    auto-grow
                    :rules="rules"/>
                <br>
                <br>

                <label class="normalText" style="float: left; color: grey;">
                    사진 &emsp;
                    <input multiple type="file" ref="files" class="normalText" style="color: grey;" v-on:change="handleFileUpload()"/>
                </label>

                <span style="float: right;">
                    <v-btn text class="normalText" type="submit">
                        확인
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
    name: 'ReportWrite',
    data() {
        return {
            keyword: '',
            items: [ '실종', '보호', '목격' ],
            title: '제목',
            content: '',
            city: '',
            areas: [ '서울', '경기', '인천', '강원', '충청', '대전', '전라북도', '전라남도', '경상북도', '경상남도', '부산', '대구', '제주' ],
            place: '구체적 장소(발견 장소)',
            animals: [ '개', '고양이', '기타' ],
            date: '',
            breed: '',
            etcAnimal: '',
            feature: '',
            keepingPlace: '',
            rules: [v => v.length <= 300 || '300자 이내 작성'],
            pics: ''
        }
    },
    methods: {
        goBack() {
            window.history.go(-1)
        },
        // onSubmit() {
        //     const form = { keyword: this.keyword, title: this.title, content: this.content }

        //     this.$emit('submit', form)
        // },
        handleFileUpload() {
            this.pics = this.$refs.files.files
        },
        onSubmit() {

            if(this.$store.state.session) {

                if(this.breed == '기타') this.breed = this.etcAnimal
                
                const whereHappened = this.city + ' ' + this.place

                const form = { category: this.keyword, title: this.title, writer: this.$store.state.session.id , whereHappened: whereHappened,
                whenHappened: this.date, keepingPlace: this.keepingPlace, breed: this.breed, feature: this.feature, content: this.content, viewCnt: 0 }

                if(this.pics.length > 0) {

                    let formData = new FormData()

                    for(var i=0; i<this.pics.length; i++) {
                        formData.append("animalPics", this.pics[i])
                    }

                    formData.append("category", this.keyword)
                    formData.append("id", this.$store.state.session.id)

                    let now = new Date()
                    const dateId = now.toLocaleString().toString().replace(/ /gi, "").replace(/:/gi, "-")

                    formData.append("dateId", dateId)

                    axios.post('http://localhost:8888/petto/report/sendAnimalPics', formData)
                        .then((res) => {
                            if(res.data) {

                                form.imgUploadedTime = dateId
                                form.imgUploadedCnt = this.pics.length

                                this.$emit('submit', form)
                            }
                            else alert('잠시 후에 시도해주세요!')
                        })
                        .catch(() => {
                            alert('잠시 후에 시도해주세요!')
                        })

                } else {
                    this.$emit('submit', form)
                }

            } else alert('로그인이 필요해요.')
        }
    }
}
</script>