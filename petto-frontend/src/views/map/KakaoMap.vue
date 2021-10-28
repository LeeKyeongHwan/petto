<template>
  <div>
    <div id="map"></div>
    <div class="button-group">
      <button @click="displayMarker([])">marker set (empty)</button>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,

      markers: [],
    };
  },
  computed: {
    ...mapState(["animals"]),
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
    this.fetchAnimalList();
  },
  methods: {
    ...mapActions(["fetchAnimalList"]),

    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.564343, 126.947613), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);
      // 마커 클러스터러를 생성합니다

      var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
      });

      var markers = [];

      console.log(this.animals);
      this.animals.length = 1000;
      var _self = this;

      for (var i = 0; i < this.animals.length; i++) {
        (function(i) {
          // 주소-좌표 변환 객체를 생성합니다
          var geocoder = new kakao.maps.services.Geocoder();

          // 주소로 좌표를 검색합니다
          geocoder.addressSearch(_self.animals[i].careaddr, function(
            result,
            status
          ) {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              /*var message =
                "latlng: new kakao.maps.LatLng(" + result[0].y + ", ";
              message += result[0].x + ")";*/

              //console.log(message);

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
              });

              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                content:
                  '<div style="width:150px;text-align:center;padding:6px 0;">' +
                  _self.animals[i].carenm +
                  "</div>",
              });

              markers.push(marker);
              kakao.maps.event.addListener(
                marker,
                "mouseover",
                makeOverListener(map, marker, infowindow)
              );
              kakao.maps.event.addListener(
                marker,
                "mouseout",
                makeOutListener(infowindow)
              );

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
            }
            clusterer.addMarkers(markers);
            // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
            function makeOverListener(map, marker, infowindow) {
              return function() {
                infowindow.open(map, marker);
              };
            }

            // 인포윈도우를 닫는 클로저를 만드는 함수입니다
            function makeOutListener(infowindow) {
              return function() {
                infowindow.close();
              };
            }
          });
        })(i);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 800px;
  height: 500px;
  margin: 50px;
}

.button-group {
  margin: 10 0 px;
}

button {
  margin: 0 3px;
}
</style>
