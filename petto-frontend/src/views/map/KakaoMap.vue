<template>
  <div>
    <div id="map"></div>
    <div class="button-group">
      <button @click="displayMarker([])">marker set (empty)</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      facility: [
        {
          title: "원주유기동물보호관리센터",
          addr: "강원도 원주시 호저면 칠봉로 110-6",
        },
        {
          title: "화천군농업기술센터",
          addr: "강원도 화천군 상서면 영서로 6387-9  화천군농업기술센터",
        },
        {
          title: "홍익동물종합병원",
          addr: "서울특별시 마포구 독막로 45 (합정동)",
        },
        {
          title: "화원연합동물병원",
          addr: "대구광역시 달성군 화원읍 사문진로 447",
        },
        {
          title: "최종주동물병원",
          addr: "충청북도 영동군 영동읍 계산로 54",
        },
        {
          title: "현풍동물병원",
          addr: "대구광역시 달성군 유가읍 테크노공원로 51 (유가읍)",
        },
        {
          title: "21세기동물병원",
          addr: "서울특별시 용산구 보광동 259-1",
        },
        {
          title: "C.T종합동물병원",
          addr:
            "서울특별시 마포구 만리재로 74 (신공덕동, 신공덕2차삼성래미안) 삼성 래미안상가 117호",
        },
        {
          title: "강동구청 반려동물팀",
          addr: "서울특별시 강동구 성내로 25 (성내동, 강동구청) 강동구청",
        },
        {
          title: "강릉시동물사랑센터",
          addr: "강원도 강릉시 성산면 내맬길 172 ",
        },
        {
          title: "강현림동물병원",
          addr: "서울특별시 양천구 등촌로 160 (목동) 1층",
        },
        {
          title: "경동동물병원",
          addr: "인천광역시 중구 개항로 68 (경동)",
        },
      ],
      markers: [],
    };
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
  },
  methods: {
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
        minLevel: 5, // 클러스터 할 최소 지도 레벨
      });

      var markers = [];
      var facility = this.facility;

      for (var i = 0; i < facility.length; i++) {
        (function(i) {
          // 주소-좌표 변환 객체를 생성합니다
          var geocoder = new kakao.maps.services.Geocoder();

          // 주소로 좌표를 검색합니다
          geocoder.addressSearch(facility[i].addr, function(result, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              var message =
                "latlng: new kakao.maps.LatLng(" + result[0].y + ", ";
              message += result[0].x + ")";

              console.log(message);

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
              });

              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                content:
                  '<div style="width:150px;text-align:center;padding:6px 0;">' +
                  facility[i].title +
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
