<template>
    <div>
        <v-dialog v-model="dialog" persistent max-width="400">

        <template v-slot:activator="{ on }">

            <v-btn text v-on="on">
                <v-icon color="error">
                    add_alert
                </v-icon>
            </v-btn> 

        </template>

        <v-card style="position: fixed; top: 80px; right: 80px; width: 400px;">

            <v-card-title>

                <p class="normalText">계정 알림사항</p>

            </v-card-title>

            <v-card-text>
                <!-- <v-layout wrap> 이게 있으면 세로로 안나옴 -->

                <ul v-for="(alarm, idx) in session.updateAlarmList" :key="idx">
                    <li style="display: inline-block; width: 300px; font-size: 12px;" @click="toDetailPage(alarm.postNo)">

                        {{ alarm.commentator }}님이 {{ alarm.regDate }}시에 '{{ alarm.title }}' 글에 답변했어요! 
                        <br>
                    </li>
                    <v-btn text x-small style="float: right;" @click="deleteAlaram(alarm.alarmNo, idx)">
                        <v-icon color="#D5D5D5">
                            cancel
                        </v-icon>
                    </v-btn>
                </ul>
                        
                <!-- </v-layout> -->
            </v-card-text>
            
            <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn text @click="cancel" class="normalText">
                    닫기
                </v-btn>
            </v-card-actions>

        </v-card>
    </v-dialog>

    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'AlarmDialog',
    props: {
        session: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            dialog: false
        }
    },

    methods: {
        cancel() {
            this.dialog = false
        },

        toDetailPage(postNo) {

            let routeData = this.$router.resolve({
                name: 'ReportReadPage',
                params: { reportNo: postNo }
            })
            window.open(routeData.href, '_blank')
        },

        deleteAlaram(alarmNo, idx) {

            axios.delete(`http://localhost:8888/petto/member/delete_alarm/${alarmNo}`)
                .then(() => {

                    this.session.updateAlarmList.splice(idx, 1)
                })
                .catch(err => {
                    console.log(err.message)
                })
        }
    }
}
</script>