<template>
    <div align="center" style="padding-top: 60px;">
        <v-container class="justyfy center">
            <v-data-table
                :headers="headers"
                :items="abandonedAnimalList()"
                :items-per-page="10"
                loading-text="정보를 불러오는 중입니다."
                class="elevation-1">

                <template v-slot:item="{ item }"> 
                        <tr>
                            <td>{{ item.happened }}</td>
                            <td>{{ item.image }}</td>
                            <td>{{ item.state }}</td>
                            <td>{{ item.kind }}</td>
                            <td>{{ item.happenplace }}</td>
                        </tr>
                    </template>
            </v-data-table>
        </v-container>
    </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
    data() {
        return {
            headers:
                [
                { text: '등록일', value: 'happened' },
                { text: '사진', value: 'image' },
                { text: '상태', value: 'state' },
                { text: '종류', value: 'kind' },
                { text: '구조장소', value: 'happenplace' },
                ]
            }
    },
    methods: {
        abandonedAnimalList() {
            return this.$store.state.animals
        }
    },
    mounted() {
        this.$store.dispatch("fetchAnimalList")
    },
    computed: {
        ...mapState(['animals'])
    }
    
}
</script>
