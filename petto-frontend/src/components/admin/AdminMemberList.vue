<template>
    <div align="center">
        <h3>회원 목록</h3>
        <table border="1" style="width:600px;">
            <tr>
                <th align="center" width="150">회원번호</th>
                <th align="center" width="200">권한</th>
                <th align="center" width="200">아이디</th>
                <th align="center" width="240">등록일자</th>
            </tr>
            <tr v-if="!adminMembers || (Array.isArray(adminMembers) && adminMembers.length === 0)">
                <td colspan="4">
                    현재 등록된 회원이 없습니다!
                </td>
            </tr>
            <tr v-else v-for="adminMember in adminMembers" :key="adminMember.memberNo">
                <td align="center">{{ adminMember.memberNo }}</td>
                <td align="left">
                    <router-link :to="{ name: 'AdminMemberReadPage',
                                    params: { memberNo: adminMember.memberNo.toString() } }">
                        {{ adminMember.auth }}
                    </router-link>
                </td>
                <td align="center">{{ adminMember.id }}</td>
                <td align="center">{{ adminMember.regDate }}</td>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    name: 'AdminMemberList',
    props: {
        adminMembers: {
            type: Array
        }
    }
}
</script>