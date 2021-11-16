<template>
    <div>
        <table style="width:500px;">
            <tr>
                <td>회원번호</td>
                <td><input type="text" :value="adminMember.memberNo" readonly></td>
            </tr>
            <tr>
                <td>권한</td>
                <td><input type="text" :value="adminMember.auth" readonly></td>
            </tr>
            <tr>
                <td>아이디</td>
                <td><input type="text" :value="adminMember.id" readonly></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><input type="text" :value="adminMember.email" readonly></td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" :value="adminMember.phoneNumber" readonly></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" :value="adminMember.name" readonly></td>
            </tr>
            <tr>
                <td>생일</td>
                <td><input type="text" :value="adminMember.birthday" readonly></td>
            </tr>
            <tr>
                <td>닉네임</td>
                <td><input type="text" :value="adminMember.nickname" readonly></td>
            </tr>
            <tr>
                <td>반려동물 유무</td>
                <td><input type="text" :value="adminMember.petsRaised" readonly></td>
            </tr>
            <tr>
                <td>등록일자</td>
                <td><input type="text" :value="adminMember.regDate" readonly></td>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    name: 'AdminMemberRead',
    props: {
        adminMember: {
            type: Object,
            required: true
        }
    }    
}
</script>