<template>
    <div align="center">

        <report-read v-bind:report="report"/>

    </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import ReportRead from '@/components/report/ReportRead.vue'

export default {
    components: {
        ReportRead
    },
    props: {
        reportNo: {
            type: Number,
            required: true
        }
    },

    methods: {
        ...mapActions(["fetchReport"])
    },

    computed: {
        ...mapState(['report'])
    },

    mounted() {
        this.$store.dispatch("fetchReport", this.reportNo)
    }
}
</script>
