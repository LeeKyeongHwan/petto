<template>
    <div align="center" style="padding-top: 60px;">

        <p class="mainText">해당 보호소 정보</p>

        <p class="normalText" style="color: black; margin-bottom: 40px;">{{ facilityInfo.facilityName }}</p>

        <span>

          <p class="normalText" style="display: inline; margin-right: 20px;">보호소 주소</p>
          <p class="normalText" style="color: black; display: inline;">{{ facilityInfo.facilityAddr }}</p>

          <br/>
          <div style="margin-top: 15px;"></div>

          <p class="normalText" style="display: inline; margin-right: 20px;">보호소 번호</p>
          <p class="normalText" style="color: black; display: inline;">{{ facilityInfo.facilityTel }}</p>

          <br/>
          <div style="margin-top: 15px;"></div>

          <p class="normalText" style="display: inline; margin-right: 20px;">지역</p>
          <p class="normalText" style="color: black; display: inline;">{{ facilityInfo.orgName }}</p>

          <br/>
          <div style="margin-top: 15px;"></div>

          <p class="normalText" style="display: inline; margin-right: 20px;">보호 동물</p>
          <p class="normalText" style="color: black; display: inline;">{{ facilityInfo.saveTrgtAnimal }}</p>

        </span>

        <div id="map" style="width: 30%; margin-top: 40px;"></div>

        <div style="margin-top: 10px;">
          <p v-show="mapSize=='small'" style="font-size: 12px;" @click="widenMapSize">지도 크게</p>
          <p v-show="mapSize=='big'" style="font-size: 12px;" @click="reduceMapSize">지도 작게</p>
        </div>

        <div style="margin-top: 50px;">

          <a class="normalText" style="color: black; margin-right: 20px;" target="_blank"
          :href="urlRequestForLostAnimals(facilityInfo.facilityName)">해당 보호소의 유기동물 검색</a>

           &nbsp;  &nbsp;  &nbsp;  &nbsp;

          <a class="normalText" style="color: black; margin-right: 20px;" target="_blank"
          :href="urlRequestForSpecificInfo(facilityInfo.facilityName, facilityInfo.orgName)">해당 보호소 상세정보</a>

        </div>

    </div>

</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: "KakaoMap",

  props: {
    facilityNo: {
      type: String,
      required: true
    }
  },
  
  data() {
    return {
      map: null,
      markers: [],
      mapSize: 'small'
    };
  },
  
  mounted() {
    this.fetchFacilityInfo(this.facilityNo)
    
    if (window.kakao && window.kakao.maps) {
      this.initMap();

    } else {

      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=07c4540ad9492806ee3339221fbf2965&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }

  },
  methods: {
    ...mapActions(['fetchFacilityInfo']),

    urlRequestForLostAnimals(facilityName) {
      return `https://search.naver.com/search.naver?query=${facilityName}+유기동물&ie=utf8&sm=whl_nht`
    },

    urlRequestForSpecificInfo(facilityName, orgName) {
      return `https://www.google.co.kr/search?q=${orgName} + ${facilityName}&source=hp&ei=XaxzYYrYGc_b-wTh4ojwCw&iflsig=
      ALs-wAMAAAAAYXO6bTmpCHtuq11Q0IyD06x9avaSRZxY&ved=0ahUKEwjK-tHG9N_zAhXP7Z4KHWExAr4Q4dUDCAc&uact=5&oq=
      24%EC%8B%9C%EC%95%84%EC%9D%B4%EB%8F%99%EB%AC%BC%EB%A9%94%EB%94%94%EC%BB%AC&gs_lcp=
      Cgdnd3Mtd2l6EAMyBQgAEIAEMgIIJlDVA1jVA2DbBWgAcAB4AIABfIgBfJIBAzAuMZgBAKABAqABAQ&sclient=gws-wiz`
    },

    widenMapSize() {
      this.mapSize = 'big'

      const container = document.getElementById("map");
      container.style.width = '100%';
      container.style.height = '500px';
      this.map.relayout();
    },

    reduceMapSize() {
      this.mapSize = 'small'

      const container = document.getElementById("map");
      container.style.width = '30%';
      container.style.height = '400px';
      this.map.relayout();
    },

    initMap() {
      var mapContainer = document.getElementById('map'), 

          mapOption = {
              center: new kakao.maps.LatLng(33.450701, 126.570667), 
              level: 3 
          };  
  
      var map = new kakao.maps.Map(mapContainer, mapOption); 

      var geocoder = new kakao.maps.services.Geocoder();

      const facilityAddr = this.facilityInfo.facilityAddr
      const facilName = this.facilityInfo.facilityName
      
      //여기서부터 d
      geocoder.addressSearch(facilityAddr, function(result, status) {

          if (status === kakao.maps.services.Status.OK) {

              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
              });

              var infowindow = new kakao.maps.InfoWindow({
                  content: 
                  '<div style="width: 150px; text-align: center; margin-bottom: -15px;">' + 
                  '<p class="normalText">' + facilName + '</p></div>'
              });
              infowindow.open(map, marker);

              map.setCenter(coords);
          } 
      });    
      this.map = map
    }
    
  },
  computed: {
    ...mapState(['facilityInfo'])
  }
}
</script>

<style scoped>
#map {
  width: 400px;
  height: 400px;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>