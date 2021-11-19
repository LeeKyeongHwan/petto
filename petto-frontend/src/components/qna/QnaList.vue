<template>
  <div>
    <v-container>
      <table border="1">
        <tr>
          <th align="center" width="80">No</th>
          <th align="center" width="320">제목</th>
          <th align="center" width="100">작성자</th>
          <th align="center" width="240">등록일자</th>
        </tr>

        <tr v-if="!qnaboards || (Array.isArray(qnaboards) && qnaboards.length === 0)">
          <td colspan="4">
            질문이 없습니다!
          </td>
        </tr>

        <tr v-else v-for="qnaboard in calData" :key="qnaboard.qnadNo">
          <td align="center">{{ qnaboard.qnaNo }}</td>
          <td align="left">
            <router-link :to="{ name: 'QnaReadPage', params: { qnaNo: qnaboard.qnaNo.toString() } }">
              {{ qnaboard.title }}
            </router-link>
            </td>
          <td align="right">{{ qnaboard.writer }}</td>
          <td align="center">{{ qnaboard.regDate }}</td>
        </tr>
      </table>
    </v-container>
     <v-pagination :length= "numofpage" v-model="curpagenum" color="#42b8d4" style="background-color: white; box-shadow: 0px 0px white;"> </v-pagination>
      

  </div>
</template>

<script>
export default {
    name: 'QnAList',
    data () {
    return {
      curpagenum : 1,
      datapage : 10
    }
    },
    props: {
        qnaboards: {
            type: Array
        }
    },
    computed: {
    startOffset() {
      return ((this.curpagenum - 1) * this.datapage);
    },
    endOffset() {
      return (this.startOffset + this.datapage);
    },
    numofpage() {
        return Math.ceil(this.qnaboards.length / this.datapage);
    },
    calData() {
      return this.qnaboards.slice(this.startOffset, this.endOffset);
    } 
    }
}
</script>

<style scoped>