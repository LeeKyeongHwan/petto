<template>
    <div align="center">
        <report-modify v-if="report" v-bind:report="report" @submit="onSubmit"/>
        <p v-else-if="!report">정보를 불러오는 중입니다.</p>
    </div>
</template>

<script>
import axios from 'axios'

import { mapActions, mapState } from 'vuex'
import ReportModify from '@/components/report/ReportModify.vue'

export default {
    components: {
        ReportModify
    },
    props: {
        id: {
            type: String,
            default: ''
        },
        reportNo: {
            type: String,
            required: true
        }
    },
    methods: {
        ...mapActions(['fetchReport']),

        onSubmit(payload) {
            //JSON.stringify(payload)

            axios.put('http://localhost:8888/petto/report/modify', payload)
                .then(() => {
                    alert('수정되었습니다.')

                    //window.history.go(-1)
                    //this.$router.push({ name: 'ReportReadPage', params: { reportNo: this.report } })
                    window.close()
                })
                .catch(() => {
                    alert('잠시후에 다시 시도해주세요.')
                })
        }
    },
    computed: {
        ...mapState(['report'])
    },
    mounted() {
        if(this.$cookies.get('user').id == this.id) {
            this.$store.dispatch("fetchReport", this.reportNo)
        }
        else {
            alert('권한이 없습니다!')
            window.close()
        }

        if(this.$cookies.isKey("user")) {
  
            this.$store.state.session = this.$cookies.get("user");
            
            if(this.$store.state.session != null) {
                this.$store.dispatch('fetchAlarmList', this.session.id)

                this.$store.state.isLoggedIn = true;
            }
        }
    }
}
</script>