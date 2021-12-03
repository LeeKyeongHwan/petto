<template>
    <div align="center" style="padding-top: 60px;" >

        <p class="mainText">유기동물 정보</p>

        <br/>

        <div style="display: inline-block; margin-right: 50px;">
            <img :src="animalsInfo.image" width="350" height="300" class="aniPic"/>
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

        <div style="margin-right: 30px; margin-bottom: 10px;">

            <v-btn text class="normalText" style="color: black;" @click="toFacilityInfo(animalsInfo.carenm)">
                보호소 바로가기
            </v-btn>

            &emsp;
            &emsp;

            <p class="normalText" style="display: inline-block; font-size: 15px;">{{ animalsInfo.numberOfLiked }}</p>
            &nbsp;
            <p class="normalText" style="display: inline-block; font-size: 12px; color: black;">명이 이 동물을 찜했어요!</p>

            &emsp;
            &emsp;
            <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
            
                <font-awesome-icon v-show="chkLikedOrNot(animalsInfo.notice_no)" :icon="['fas','heart']" size="lg" :style="{ color: '#42b8d4' }" v-on="on" v-bind="attrs"
                @click="deleteLikedAnimal(animalsInfo.notice_no)"/>

                <font-awesome-icon v-show="!chkLikedOrNot(animalsInfo.notice_no)" :icon="['far','heart']" size="lg" :style="{ color: '#42b8d4' }" v-on="on" v-bind="attrs"
                @click="addLikedAnimal(animalsInfo.notice_no)"/>

                </template>

                <span v-show="chkLikedOrNot(animalsInfo.notice_no)">찜해제</span>

                <span v-show="!chkLikedOrNot(animalsInfo.notice_no)">찜하기</span>

            </v-tooltip>
            &emsp; &emsp;
            <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">
                    <img src="@/assets/img/kakao.png" width="35px; cursor: pointer;" @click="shareOnKaKao" v-on="on" v-bind="attrs" class="aniPic"/>
                </template>
                <span>카톡 글 공유하기</span>
            </v-tooltip>
        </div>
        <div id="latestSeenShower">
            <br>
            <p class="normalText" style="color: black;">최근에 본 유기동물</p>

            <v-btn text class="upBtn" :disabled="listNum === 0" @click="goListUp">
                <v-icon>
                expand_less
                </v-icon>
            </v-btn>
            <br>
            <div v-for="(latestSeenAni, index) in latestSeen" :key="index">
                <img :src="latestSeenAni.imgSrc" class="lateSeenThumbnail" @click="toDetailPage(latestSeenAni.noticeNo)"/>
                <v-btn text x-small color="grey" class="delBtn" @click="delLatestSeen(latestSeenAni.noticeNo)">
                    <v-icon>
                        cancel
                    </v-icon>
                </v-btn>
            </div>

            <span class="listCnt">{{ listNum + 1 }} / {{ listCount }}</span>
            &emsp; &ensp;
            <br>

            <v-btn text class="downBtn" :disabled="listNum >= listCount - 1"  @click="goListDown">
                <v-icon>
                expand_more
                </v-icon>
            </v-btn>

            <span style="display: inline-block; width: 60px;"/>
        </div>
    
    </div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'

export default {
    name: 'AnimalDetailPage',
    props: {
        id: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            tmpLatestSeen: [],
            listNum: 0,
            LATEST_SEEN_SIZE: 3,
            
            latestSeenDeleteCnt: 0,
            DUPLI_PREVENTION_CHECK: 0,
        }
    },

    methods: {
        ...mapActions(['fetchAnimalInfo', 'fetchLikedAnimalList']),

        shareOnKaKao() {
            const likeCnt = this.animalsInfo.numberOfLiked
            const sharedCnt = this.animalsInfo.sharedCnt

            Kakao.Link.sendDefault({
                objectType: 'feed',
                content: {
                    title: 'Petto) 이 유기동물의 가족이 되어주세요.',
                        description: '이 동물의 가족이 될 사람은 누굴까요? 많은 유기동물들이 여러분들의 관심을 기다립니다.',
                        imageUrl: this.animalsInfo.image,
                    link: {
                        mobileWebUrl: `http://localhost:8080/animalDetail/read/${this.animalsInfo.id}`,
                        webUrl: `http://localhost:8080/animalDetail/read/${this.animalsInfo.id}`
                    },
                },
                social: {
                    likeCount: likeCnt,
                    sharedCount: sharedCnt,
                },
                buttons: [
                    {
                        title: '웹으로 보기',
                        link: {
                            mobileWebUrl: `http://localhost:8080/animalDetail/read/${this.animalsInfo.id}`,
                            webUrl: `http://localhost:8080/animalDetail/read/${this.animalsInfo.id}`
                        },
                    },
                ],
                // 카카오톡 미설치 시 카카오톡 설치 경로이동
                installTalk: true,
            })
            
            axios.post(`http://localhost:8888/petto/animals/plusSharedCnt/${this.animalsInfo.notice_no}`)
            this.$store.state.animalsInfo.sharedCnt ++
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

        addLikedAnimal(notice_no) {

            if(this.$store.state.session) {
            
                const memberNo = this.$store.state.session.memberNo
                const noticeNo = notice_no

                axios.post('http://localhost:8888/petto/member/addLikedAnimal', { memberNo, noticeNo })
                    .then(() => {

                        this.$store.state.likedAnimalList.push({ 'memberNo': memberNo, 'noticeNo': noticeNo })

                        //const targetIndex = this.$store.state.animals.findIndex(v => v.notice_no === notice_no)
                        //this.$store.state.animals[targetIndex].numberOfLiked ++

                        this.$store.state.animalsInfo.numberOfLiked ++
                    })
                    
                    .catch(() => {
                        alert('잠시후에 다시 시도해주세요.')
                    })

            } else alert('로그인이 필요한 서비스입니다.')
        },

        chkLikedOrNot(notice_no) {

            if(this.$store.state.session) {
                for(var i=0; i<this.$store.state.likedAnimalList.length; i++) {

                    if(notice_no == this.$store.state.likedAnimalList[i].noticeNo) {
                        return true
                    }
                }
                return false
            } else return false
        },

        deleteLikedAnimal(notice_no) {

            if(this.$store.state.session) {
        
                const memberNo = this.$store.state.session.memberNo
                const noticeNo = notice_no
                
                axios.put('http://localhost:8888/petto/member/deleteLikedAnimal', { memberNo, noticeNo }, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(() => {
                        const targetIndex = this.$store.state.likedAnimalList.findIndex(v => v.noticeNo === notice_no) //*** likedAnimalList에는 noticeNo ***
                        this.$store.state.likedAnimalList.splice(targetIndex, 1)

                        //const targetIndex2 = this.$store.state.animals.findIndex(v => v.notice_no === notice_no)
                        //this.$store.state.animals[targetIndex2].numberOfLiked -- 

                        this.$store.state.animalsInfo.numberOfLiked --
                    })
                    .catch(() => {
                        alert('잠시후에 다시 시도해주세요.')
                    })

            } else alert('로그인이 필요한 서비스입니다.')
        },

        goListUp() {
            this.listNum -= 1
        },

        goListDown() {
            this.listNum += 1
        },

        toDetailPage(noticeNo) {
            // let routeData = this.$router.resolve({
            // name: 'AnimalDetailPage',
            // params: { id: noticeNo }
            // });window.open(routeData.href, '_blank')

            this.$router.push({
                name: 'AnimalDetailPage',
                params: { id: noticeNo }
            })
        },

        delLatestSeen(noticeNo) {

            this.latestSeenDeleteCnt++

            this.tmpLatestSeen = JSON.parse(this.$cookies.get('latestSeen'))

            const targetIndex = this.tmpLatestSeen.findIndex(v => v.noticeNo === noticeNo)
            this.tmpLatestSeen.splice(targetIndex, 1)

            this.$cookies.set('latestSeen', JSON.stringify(this.tmpLatestSeen), '12h')
        }
    },

    computed: {
        ...mapState(['animalsInfo', 'session', 'likedAnimalList', 'animals', 'isLoggedIn']),

        latestSeen() {
            console.log(this.latestSeenDeleteCnt)

            if(this.$cookies.get("latestSeen")) {

                const start = this.listNum * this.LATEST_SEEN_SIZE
                const end = start + this.LATEST_SEEN_SIZE;

                const seenAnimalsArr = JSON.parse(this.$cookies.get("latestSeen"))

                return seenAnimalsArr.reverse().slice(start, end)

            } else return null
        },

        listCount() {
            console.log(this.latestSeenDeleteCnt)
            
            let listLeng = JSON.parse(this.$cookies.get('latestSeen')).length

            let page = Math.floor(listLeng / this.LATEST_SEEN_SIZE)

            if(listLeng % this.LATEST_SEEN_SIZE > 0) page += 1;

            return page;
        }
    },

    mounted() {
        this.fetchAnimalInfo(this.id)

        if(this.$cookies.isKey("user")) {
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)
                this.fetchLikedAnimalList(this.session.memberNo)

                this.$store.state.isLoggedIn = true;
            }
        }
    },
    updated() {
        //this.$cookies.remove("latestSeen");    

        if(this.$store.state.animalsInfo) {

            if(this.DUPLI_PREVENTION_CHECK == 0) {

                var tmpObj = { noticeNo: this.$store.state.animalsInfo.notice_no, imgSrc: this.$store.state.animalsInfo.image }

                if(!this.$cookies.get('latestSeen')) {

                    this.tmpLatestSeen.push(tmpObj)
                    this.$cookies.set('latestSeen', JSON.stringify(this.tmpLatestSeen), '12h')

                } else {
                    this.tmpLatestSeen = JSON.parse(this.$cookies.get('latestSeen'))

                    if(this.tmpLatestSeen.length == 21) {
                        this.tmpLatestSeen.splice(0, 1)
                        this.$cookies.set('latestSeen', JSON.stringify(this.tmpLatestSeen), '12h')
                        return false
                    }

                    for(var i=0; i<this.tmpLatestSeen.length; i++) {
                        if(this.tmpLatestSeen[i].noticeNo == this.$store.state.animalsInfo.notice_no) {
                            this.tmpLatestSeen.splice(i, 1)
                            this.tmpLatestSeen.push(tmpObj)
                            this.$cookies.set('latestSeen', JSON.stringify(this.tmpLatestSeen), '12h')
                            return false
                        }
                    }
                    this.tmpLatestSeen.push(tmpObj)
                    this.$cookies.set('latestSeen', JSON.stringify(this.tmpLatestSeen), '12h')
                }
                this.latestSeenDeleteCnt ++
                this.DUPLI_PREVENTION_CHECK = 1
            } 
        }
    }
}

</script>

<style>

.aniPic {
    margin-right: 20px;
    border-radius: 8px;
    border: 3px solid #42b8d4;
}

</style>