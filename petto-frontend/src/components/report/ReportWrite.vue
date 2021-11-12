<template>
    <div>

        <br>
        <br>
        <br>

        <v-container style="width: 51%;">
            
            <form @submit.prevent="onSubmit">
                <span>
                    <v-select
                        v-model="keyword"
                        :items="items"
                        attach
                        chips
                        label="주제"
                        style="width: 120px; display: inline-block; float: left;"
                        class="normalText"
                    ></v-select>
                    
                    <input v-model="title" style="width: 78%; color: black; float: right; margin-top: 12px;" class="normalText"/>

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
                    auto-grow/>

                </span>

                <br>
                <br>

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
export default {
    name: 'ReportWrite',
    data() {
        return {
            keyword: '',
            items: [ '실종', '보호', '목격' ],
            title: '제목',
            content: ''
        }
    },
    methods: {
        goBack() {
            window.history.go(-1)
        },
        onSubmit() {
            const form = { keyword: this.keyword, title: this.title, content: this.content }

            this.$emit('submit', form)
        }
    }
}
</script>