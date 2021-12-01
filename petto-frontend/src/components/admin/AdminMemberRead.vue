<template>
    <div style="width:100vw;">
        <div>
        <h3>회원정보 수정</h3>

            <tr>
                <td><label style="float:left;">회원번호</label></td>
                <td><p>{{adminMember.memberNo}}</p></td>
            </tr>
            <tr>
                <td><label style="float:left;">권한</label></td>
                <td><p>{{adminMember.auth}}</p></td>
            </tr>
            <tr>
                <td><label style="float:left;">아이디</label></td>
                <td><p>{{adminMember.id}}</p></td>
            </tr>
            <tr>
                <td><label style="float:left;">이메일</label></td>
                <td><p>{{adminMember.email}}</p></td>
            </tr>
            <tr>
                <td><label style="float:left;">전화번호</label></td>
                <td><p>{{adminMember.phoneNumber}}</p></td>
            </tr>
            <tr>
                <td><label style="float:left;">이름</label></td>
                <td><p>{{adminMember.name}}</p></td>
            </tr>
            <tr>
                <td><label style="float:left;">생일</label></td>
                <td><p>{{adminMember.birthday}}</p></td>
            </tr>
            <tr>
                <td><label style="float:left;">닉네임</label></td>
                <td><p>{{adminMember.nickname}}</p></td>
            </tr>
            <tr>
                <td><label>반려동물 유무</label></td>
                <td><p v-if="this.$store.state.adminMember.petsRaised == ''">없음</p>
                <p v-else>{{adminMember.petsRaised}}</p></td>
                
            </tr>
            <tr>
                <td><label style="float:left;">등록일자</label></td>
                <td><p>{{adminMember.regDate}}</p></td>
            </tr> 
        </div>
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

<style scoped>
#mem-box{
    width:500px;
}

@font-face {
    font-family: 'ChosunSm';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.1/ChosunSm.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
@font-face {
    font-family: 'MapoGoldenPier';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoGoldenPierA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
h3{
    font-family: 'ChosunSm';
    font-size: 30px;
}
p{
    font-family: 'MapoGoldenPier';
    font-size: 15px;
    margin: 0;
}

td{
    padding-top:2%;
}
</style>