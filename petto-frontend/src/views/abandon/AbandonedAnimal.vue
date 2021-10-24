<template>
  <div>
    <h1>유기동물 리스트</h1>
    <paginated-list :animals="animals" />
  </div>
</template>

<script>
import PaginatedList from '@/components/abandon/PaginatedList'
import {mapActions, mapState} from 'vuex'

export default {
  name: 'AbandoneAnimal',
  components: {
    PaginatedList
  },
  data () {
    return {
      pageArray: []
    }
  },
  computed: {
      ...mapState(['animals'])
  },
  mounted () {
      this.fetchAnimalList()
  },
  methods: {
      ...mapActions(['fetchAnimalList'])
  }
}
</script>

<style scoped>
@font-face {
    font-family: 'GowunDodum-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/GowunDodum-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}

h1 {
    font-family: 'GowunDodum-Regular';
    font-size: 30px;
    text-align: center;
    padding: 5% 0% 5% 0%;
}
</style>