<template>
    <div>

        <h1 v-if="!report.expired" style="margin-bottom: -80px;" class="head">{{ report.category }} &ensp; 공고</h1>
        <h1 v-else style="margin-bottom: -80px;" class="head">만료된 공고</h1>
        <br>
        <v-container style="width: 51%; color: grey;" class="normalText">

            <p class="normalText" style="float: right; color: grey;">작성자 {{ report.writer }} &emsp; 조회수 {{ report.viewCnt + 1 }}</p>
            <br>
            <div v-if="report.imgUploadedCnt > 0">           
                <p style="float: left;">해당 동물 사진</p>

                <br>
                <br>
                <img v-for="(num, idx) in UploadedCnt" :key="idx" :src="imgRequest(idx)" class="img" style="margin-top: 18px;"/>

                <!-- <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br>
                <br> -->
            </div>

            <div style="margin-top:10%;">
                <!-- <input
                    readonly
                    style="width: 120px; display: inline-block; float: left;"
                    class="normalText"
                    :value="report.category"
                /> -->
                <span>
                <p style="width: 120px; display: inline-block; float: left;" class="normalText">{{ report.category }}</p>
                <!-- <input :value="report.title" style="width: 70%; color: black; float: right;" 
                readonly class="normalText"/> -->
                <p style="width: 70%; color: black; text-align:left;" class="normalText">{{ report.title }}</p>
                </span>

                <!-- <br>
                <br>
                <br>
                <br> -->
                
                <!-- <input
                    readonly
                    style="width: 100px; float: left;"
                    class="normalText"
                    :value="cityName"
                /> -->

                <!-- &ensp; -->
                <span>
                    <p style="width: 120px;display: inline-block; float: left;" class="normalText">{{cityName}}</p>
                    <!-- <input :value="report.whereHappened" style="width: 70%; color: black; float: right;" 
                readonly class="normalText"/> -->
                <p style="width: 70%; color: black; text-align:left;" class="normalText">{{ report.whereHappened }}</p>
                </span>
                

                <!-- <br>
                <br>
                <br>
                <br> -->
                <span>
                    <p style="width: 120px;display: inline-block; float: left;" class="normalText">해당일</p>
                    <p style="width: 70%; color: black; text-align:left;" class="normalText">{{ report.whenHappened }}</p>
                </span>

                <!-- <label class="normalText" style="float: left; color: grey;">
                    해당일 &emsp;
                    <input type="date" :value="report.whenHappened" style="width: 140px; color: black; margin-top: 12px;" 
                    readonly class="normalText"/>
                </label> -->
                
                <!-- <br>
                <br>
                <br>
                <br> -->

                <span>
                    <p style="width: 120px;display: inline-block; float: left;" class="normalText">종 명</p>
                    <p style="width: 70%; color: black; text-align:left;" class="normalText">{{ report.breed }}</p>
                </span>

                <!-- <div>
                    <label class="normalText" style="float: left; color: grey;">
                        종 명 &emsp;
                        <input :value="report.breed" style="width: 200px; color: black; margin-top: 12px;" readonly class="normalText"/>
                    </label>
                </div> -->

                <!-- <br>
                <br>
                <br>
                <br> -->

                <span>
                    <p style="width: 120px;display: inline-block; float: left;" class="normalText">특징</p>
                    <p style="width: 70%; color: black; text-align:left;" class="normalText">{{ report.feature }}</p>
                </span>

                <!-- <label class="normalText" style="float: left; color: grey;">
                    특징 &emsp;
                    <input :value="report.feature" style="width: 670px; color: black; margin-top: 12px;" readonly class="normalText"/>
                </label> -->

                <div v-if="report.category == '보호'">

                    <!-- <br>
                    <br>
                    <br>
                    <br> -->
                    <span>
                    <p style="width: 120px;display: inline-block; float: left;" class="normalText">보호장소</p>
                    <p style="width: 70%; color: black; text-align:left;" class="normalText">{{ report.keepingPlace }}</p>
                </span>

                    <!-- <label class="normalText" style="float: left; color: grey;">
                        보호장소 &emsp;
                        <input :value="report.keepingPlace" style="width: 650px; color: black; margin-top: 12px;" readonly class="normalText"/>
                    </label> -->
                </div>

                <!-- <br>
                <br>
                <br>
                <br> -->
                <span>
                    <p style="width: 120px;display: inline-block; float: left;" class="normalText">상세내용</p>
                    <p style="width: 70%; color: black; text-align:left;" class="normalText">{{ report.content }}</p>
                </span>

                <!-- <v-textarea 
                readonly
                :value="report.content"
                clear-icon="mdi-close-circle"
                class="normalText"
                color="#42b8d4"
                label="상세 내용"
                style="width: 950px;"
                height="10%;"
                counter
                auto-grow/> -->

            </div>
            <br>
            <br>
            
            <span>
                
                <div style="float: left;">
                    <p class="normalText" style="color: grey; font-size: 12px; margin-top: 2px; display: inline-block;">
                        혹시 이 동물이 안전하게 집으로 돌아갔다면 이 만료 버튼을 눌러주세요 :)
                    </p>
                    <v-btn v-if="!report.expired" text class="normalText" @click="makeExpired($event)" color="red" id="expireBtn">
                        만료
                    </v-btn>
                    <v-btn v-else text class="normalText" disabled>
                        만료된 공고입니다.
                    </v-btn>
                </div>
                <br><br><br>
                <v-tooltip bottom>
                    <template v-slot:activator="{ on, attrs }">
                        <img src="@/assets/img/kakao.png" @click="shareOnKakao" style="float: right; width: 40px; cursor: pointer;" v-on="on" v-bind="attrs"/>
                    </template>
                    <span>카톡 글 공유하기</span>
                </v-tooltip>
                <br><br><br>
                <div style="float: right;">
                    
                    <v-btn v-if="!report.expired" text class="normalText" @click="modifyReport">
                        수정
                    </v-btn>

                    <v-btn v-else text class="normalText" disabled>
                        수정
                    </v-btn>
                    &emsp;
                    <v-btn text class="normalText" @click="deleteReport($event)" id="deleteBtn">
                        삭제
                    </v-btn>
                </div>

            </span>
        </v-container>
    </div>
</template>

<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
    name: 'ReportRead',
    props: {
        report: {
            type: Object,
            required: true
        }
    },

    computed: {
        ...mapState(['replyList']),

        UploadedCnt() {
            return parseInt(this.report.imgUploadedCnt)
        },
        cityName() {
            return this.report.whereHappened.split(' ')[0]
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

        shareOnKakao() {
            let desc;
            if(this.report.category == '보호') desc = '이 동물을 보호하고 있어요. 주인을 찾아주세요!'
            else if(this.report.category == '목격') desc = '이 동물을 본적이 있대요! 빨리 주인의 품으로 돌아갈 수 있게 도와주세요!'
            else desc = '이 동물을 보신적이 있나요? 가족이 애타게 기다려요!'

            Kakao.Link.sendDefault({
                objectType: 'feed',
                content: {
                    title: 'Petto) 게시글 공유알람을 확인하세요!',
                        description: desc,
                        imageUrl: '',
                    link: {
                        mobileWebUrl: `http://localhost:8080/reportBoard/read/${this.report.reportNo}`,
                        webUrl: `http://localhost:8080/reportBoard/read/${this.report.reportNo}`,
                    },
                },
                social: {
                    commentCount: this.replyList.length,
                    viewCount: this.report.viewCnt
                },
                buttons: [
                    {
                        title: '웹으로 보기',
                        link: {
                            mobileWebUrl: `http://localhost:8080/reportBoard/read/${this.report.reportNo}`,
                            webUrl: `http://localhost:8080/reportBoard/read/${this.report.reportNo}`,
                        },
                    },
                ],
            // 카카오톡 미설치 시 카카오톡 설치 경로이동
            installTalk: true,
            })
        },

        makeExpired(event) {

            if(this.$cookies.get("user").id == this.report.writer) {

                if(event.target.innerText == '정말 이 공고를 만료로 전환할까요?') {

                    axios.put(`http://localhost:8888/petto/report/makeReportExpired/${this.report.reportNo}`)
                        .then(() => {

                            const headTitle = document.querySelector('h1[class=head]')
                            headTitle.innerText = '만료된 공고'

                            // const btn = document.querySelector('button[id=expireBtn]')
                            // btn.setAttribute('disabled', 'x')
                            this.report.expired = true

                            alert('전환되었습니다 :)')
                        })
                        .catch(() => {
                            alert('잠시 후에 다시 시도해주세요.')
                        })

                } else {

                    const btn = document.getElementById('expireBtn')
                    btn.innerText = '정말 이 공고를 만료로 전환할까요?'

                    setTimeout(() => {
                        btn.innerText = '만료'
                    }, 10000)
                }

            } else alert('권한이 없습니다.')
        },

        deleteReport(event) {

            if(this.$cookies.get("user").id == this.report.writer) {

                if(event.target.innerText == '삭제') {
                    const btn = document.getElementById('deleteBtn')
                    btn.innerText = '정말 이 공고를 삭제하시겠어요?'

                    setTimeout(() => {
                        btn.innerText = '삭제'
                    }, 10000)

                    return false
                }

                const reportNo = this.report.reportNo
                const writer = this.report.writer
                const imgUploadedTime = this.report.imgUploadedTime

                let formData = new FormData()

                formData.append('reportNo', reportNo)
                formData.append('id', writer)
                formData.append('imgUploadedTime', imgUploadedTime)

                axios.put('http://localhost:8888/petto/report/deleteReport', formData)
                    .then(() => {

                        alert('공고가 삭제되었습니다 :)')
                        window.history.go(-1)
                    })
                    .catch(() => {

                        alert('잠시 후에 다시 시도해주세요.')
                    })
            } else alert('권한이 없습니다!')
        },

        modifyReport() {

            if(this.$cookies.get("user").id == this.report.writer) {

                this.$router.push({
                    name: 'ReportModifyPage',
                    params: { reportNo: this.report.reportNo.toString() }
                })
                
            } else alert('권한이 없습니다.')
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

img {
    width: 40%; 
    margin-right: 20px;
    border-radius: 8px;
    border: 3px solid #42b8d4;
}

h1 {
    font-family: 'GowunDodum-Regular';
    font-size: 25px;
    text-align: center;
    padding: 5% 0% 5% 0%;
}

</style>