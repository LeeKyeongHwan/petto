<template>
  <div id="qnaboard" align="center">
    <h2>1:1 문의 (관리자)</h2>
    <qna-list :qnaboards="qnaboards"/>
  </div>
</template>

<script>
import { mapActions,mapState } from 'vuex'
import QnaList from '@/components/qna/QnaList.vue'
export default {
    name: 'QnAListPage',
    components:{
        QnaList

    },
    computed: {
        ...mapState(['qnaboards'])
    },
    mounted () {
        this.fetchQnAList()
    },
    methods: {
        ...mapActions(['fetchQnAList']),
    }
}
</script>