<template>
    <div align="center" style="margin-top: 100px;">

        <p class="normalText" style="display: inline-block;">찾으시는 보호소 정보가 없습니다 :(</p>

        <br/>

        <p class="normalText" style="color: black; display: inline-block;">데이터 제공처의 등록 여부에 따라 데이터가 확인되지 않을 수 있습니다.</p>

        <br/>
        <br/>

        <p class="normalText" style="color: black; display: inline-block;">추가 정보를 확인하려면 버튼을 클릭하세요.</p>

        <br/>

        <v-btn text class="normalText">
            <a class="normalText" style="color: black;" target="_blank" :href="urlRequestForSpecificInfo()">추가 정보</a>
        </v-btn>

        &emsp;

        <v-btn text class="normalText" @click="toPreviousPage">
            뒤로
        </v-btn>

    </div>
</template>

<script>
export default {
    name: 'ExceptionPage',
    props: {
        facilityName: {
            type: String,
            required: true
        }
    },

    methods: {
        urlRequestForSpecificInfo() {
            return `https://www.google.co.kr/search?q=${this.facilityName}&source=hp&ei=XaxzYYrYGc_b-wTh4ojwCw&iflsig=
            ALs-wAMAAAAAYXO6bTmpCHtuq11Q0IyD06x9avaSRZxY&ved=0ahUKEwjK-tHG9N_zAhXP7Z4KHWExAr4Q4dUDCAc&uact=5&oq=
            24%EC%8B%9C%EC%95%84%EC%9D%B4%EB%8F%99%EB%AC%BC%EB%A9%94%EB%94%94%EC%BB%AC&gs_lcp=
            Cgdnd3Mtd2l6EAMyBQgAEIAEMgIIJlDVA1jVA2DbBWgAcAB4AIABfIgBfJIBAzAuMZgBAKABAqABAQ&sclient=gws-wiz`
        },

        toPreviousPage() {
            history.go(-1); 
        }
    }
}
</script>