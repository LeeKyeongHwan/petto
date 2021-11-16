<template>
    <div align="center">

        <report-write @submit="onSubmit"/>

        <report-write/>

        <report-write @submit="onSubmit"/>

    </div>
</template>

<script>
import axios from 'axios'
import ReportWrite from '@/components/report/ReportWrite'

export default {
    components: {
        ReportWrite
    },
    methods: {
        onSubmit(payload) {

            axios.post('http://localhost:8888/petto/report/register', payload)
                .then(() => {
                    alert('등록되었습니다.')

                    //window.history.go(-1)
                    this.$router.push({ name: 'ReportBoardListPage', params: { keyword: '목격' } })
                })
                .catch(() => {
                    alert('잠시후에 다시 시도해주세요.')
                })
        }
    }
}
</script>