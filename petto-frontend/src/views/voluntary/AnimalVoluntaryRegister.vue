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
							<v-text-field label="자원봉사명" outlined type="title" v-model="volTitle"></v-text-field>

                            <label>모집마감일
                                <v-menu ref="menu"
                                    :close-on-content-click="false"
                                    :return-value.sync="date"
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
                                    v-model="closingDate"
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

						<center>
							<v-btn @click="submitFiles()" :disabled="!contentFiles || !files" outlined style="margin-top:10%;">작성하기</v-btn>
						</center>
					</div>
				</form>
			</div>
		</div>
	</div>
</template>

<script>
import axios from 'axios'
export default {
    data () {
        return {
            volTitle: '',
            files: '',
            contentFiles: '',
            closingDate:''
        }
    },
    methods: {
        handleFileUpload(file) {
            this.files = file
        },
        handleContentFileUpload(file) {
            this.contentFiles = file
        },
        submitFiles () {
         let formData = new FormData()

        formData.append('volTitle', this.volTitle)
        formData.append('closingDate', this.closingDate)
        formData.append('fileList', this.files)
        formData.append('contentFileList', this.contentFiles)

        axios.post('http://localhost:8888/petto/voluntaryBoard/file', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            },
        },)
        .then (() => {
            alert('게시글이 등록되었습니다.')
            this.$router.push({
                name: 'AnimalVoluntaryListPage',
            })
        
        })
        .catch (res => {
            this.response = res.message
        }) 
    },
	}
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