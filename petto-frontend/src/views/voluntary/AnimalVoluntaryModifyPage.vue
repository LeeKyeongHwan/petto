<template>
	<div class="back">
        <v-container class="justify center" style="width:40%; padding:3%;">
            <span><img src="https://i.dlpng.com/static/png/6345652_preview.png" width="300"/></span>
        <v-card height="450" style="margin-top:-5vh;">
            <v-container>
						<h5 style="text-align:center;">자원봉사</h5>
							<v-text-field label="자원봉사명" outlined type="volTitle" v-model="volTitle" ></v-text-field>

                             <label>모집마감일
                                <v-menu ref="menu"
                                    :close-on-content-click="false"
                                    :return-value.sync="date"
                                    transition="scale-transition"
                                    offset-y
                                    min-width="auto">
                                    <template v-slot:activator="{ on, attrs }">
                                    <v-text-field
                                        v-model="date"
                                        prepend-icon="mdi-calendar"
                                        readonly
                                        v-bind="attrs"
                                        v-on="on"
                                        outlined
                                        dense
                                        type="date"></v-text-field>
                                    </template>
                                    <v-date-picker
                                    v-model="date"
                                    no-title
                                    scrollable
                                    style="height:450px;">
                                    <v-spacer></v-spacer>
                                    <v-btn text color="orange" @click="menu = false">
                                        Cancel
                                    </v-btn>
                                    <v-btn text color="orange" @click="$refs.menu.save(date)">
                                        OK
                                    </v-btn>
                                    </v-date-picker>
                                </v-menu>
                            </label>

							<section>
                                <label>Title-Files
                                    <input type="file" id="files" ref="files" name="file" v-on:change="handleFileUpload()">
                                </label>
                            </section>

                            <section>
                                <label>content-Files
                                    <input type="file" id="files" ref="contentFiles" name="contentFiles" v-on:change="handleContentFileUpload()">
                                </label>
                            </section> 


							<center style="margin-top:10%;"> 
                                <v-btn @click="onSubmit()" color="blue"
                                class="ma-2 white--text">
                                수정등록
                                </v-btn>
                                <v-btn color="blue darken-4" outlined
                                class="ma-2 white--text" route :to="{ name: 'AnimalVoluntaryReadPage', params: { volunteerNo: voluntaryboard.volunteerNo.toString() } }">
                                취소
                                </v-btn> 
                            </center>
					</v-container>
            </v-card>
        </v-container>
	</div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import axios from 'axios'

export default {
    name: 'AnimalVoluntaryModifyPage',
    data() {
        return {
            volTitle:'',
            files: '',
            contentFiles: '',
            date: '',
            access:''
        }
    },
    props: {
        volunteerNo: {
            type: String,
            required: true
        }
    },
    computed: {
        ...mapState(['voluntaryboard'])
    },
    methods: {
        ...mapActions(['fetchVoluntaryBoard']),
        handleFileUpload () {
            this.files = this.$refs.files.files
        },
        handleContentFileUpload() {
            this.contentFiles = this.$refs.contentFiles.files
        },
        onSubmit () {
            let formData = new FormData()
            formData.append('volTitle', this.volTitle)
            formData.append('closingDate', this.date)

            if(this.files == ''){
                formData.append('fileList', this.voluntaryboard.fileName)
            } else {
                formData.append('fileList', this.files[0])
            }

            if(this.contentFiles == ''){
                formData.append('contentFileList', this.voluntaryboard.contentFileName)
            } else {
                formData.append('contentFileList', this.contentFiles[0])
            }

            axios.put(`http://localhost:8888/petto/voluntaryBoard/${this.volunteerNo}`,  formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
            })
            .then(res => {
                alert('수정하였습니다.')
                this.$router.push({
                    name: 'AnimalVoluntaryReadPage',
                    params: { volunteerNo: res.data.volunteerNo.toString() }
                })
            
            })
                .catch(err => {
                alert(err.response.data.message)
            })
        }
    },
    mounted() {
        if(this.$cookies.isKey('user') == true){
            this.access = this.$cookies.get('user').auth
            if(this.access != '관리자'){
                alert('권한이 필요한 서비스입니다')
                this.$router.push({
                name:'PettoHome',
                })
            }
        } else {
            alert('권한이 필요한 서비스입니다')
            this.$router.push({
            name:'PettoHome',
            })
        }
    },
    created () {
        this.fetchVoluntaryBoard(this.volunteerNo)
                .catch(err => {
                    alert(err.response.data.message)
                    this.$router.back()
        })
        this.volTitle = this.voluntaryboard.volTitle
    },
}
</script>



<style scoped>
@font-face {
    font-family: 'JSongMyung-Regular-KO';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2110@1.0/JSongMyung-Regular-KO.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}


h5{
	font-size: 40px;
	font-family: 'JSongMyung-Regular-KO';
}

.back{
    background-color:rgb(234, 224, 248);
}
</style>

