<template>
	<div>
		<div style="margin-top:5%;">
            <p style="text-align:center;">| 자원봉사 |</p>
		</div>
		<v-divider></v-divider>

		<div class="container white z-depth-2">
			<div class="col s12">
				
				<form class="col s12">
					<div class="form-container">
						<h5 style="text-align:center;">자원봉사</h5>
							<v-text-field label="자원봉사명" outlined type="volTitle" v-model="volTitle" ></v-text-field>

                             <label>모집마감일
                                <v-menu ref="menu"
                                    :close-on-content-click="false"
                                    :return-value.sync="closingDate"
                                    transition="scale-transition"
                                    offset-y
                                    min-width="auto">
                                    <template v-slot:activator="{ on, attrs }">
                                    <v-text-field
                                        v-model="closingDate"
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

							<label>제목 이미지(800 * 200 사이즈 권장)
								<v-file-input ref="files" @change="handleFileUpload" dense outlined></v-file-input>
							</label>


							<label>내용 이미지
								<v-file-input ref="contentFiles" @change="handleContentFileUpload" dense outlined></v-file-input>
							</label>


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

					</div>
				</form>
			</div>
		</div>
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
            closingDate: ''
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
        handleFileUpload(file) {
            this.files = file
        },
        handleContentFileUpload(file) {
            this.contentFiles = file
        },
        onSubmit () {
            let formData = new FormData()
            formData.append('volTitle', this.volTitle)
            formData.append('closingDate', this.closingDate)

            if(this.files == ''){
                formData.append('fileList', this.voluntaryboard.fileName)
            } else {
                formData.append('fileList', this.files)
            }

            if(this.contentFiles == ''){
                formData.append('contentFileList', this.voluntaryboard.contentFileName)
            } else {
                formData.append('contentFileList', this.contentFiles)
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

.container{
	margin-top: 5%;
	width: 600px;
	height: 500px;
	margin-bottom: 10%;
	position: relative;
}

.form-container{
	padding: 40px;
	padding-top: 10px;
}
</style>


