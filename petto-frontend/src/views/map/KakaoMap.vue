<template>

  <div align="center" style="padding-top: 60px;">

    <div class="vld-parent">
      
      <p class="mainText">현재 유기동물 현황 지도</p>

      <span class="row" style="margin-left: 100px; float: left;">
        
        <v-select
            v-model="chosenArea"
            :items="areas"
            label="지역"
            dense
            class="normalText"
            style="width: 120px;"
          ></v-select>
        &emsp;
        <v-btn text class="normalText" @click="chooseArea" outlined>
          검색
        </v-btn>

      </span>

      <span style="float: right; margin-right: 100px;">
        <v-tooltip bottom>

          <template v-slot:activator="{ on, attrs }">
        
          <v-btn text color="#42b8d4" @click="showFacilitiesDistribution" v-on="on" v-bind="attrs" large>
            <v-icon>
              location_searching
            </v-icon>
          </v-btn>

          </template>

          <span>보호소 보기</span>

        </v-tooltip>

        &emsp;&emsp;&emsp;

        <v-tooltip bottom>

          <template v-slot:activator="{ on, attrs }">
        
          <v-btn text color="#42b8d4" @click="showAnimalsDistribution" v-on="on" v-bind="attrs" :loading="loading" large>
            <v-icon>
              pets
            </v-icon>
          </v-btn>

          </template>

          <span>유기동물 보기</span>

        </v-tooltip>
      </span>

      <br/>
      <br/>

      <div id="map" style="width: 100%; margin-top: 40px;" @click="searchByMap"></div>

      <br/>
      <br/>

      <div v-show="showBasicInfo">
        
        <div v-show="showWholeStat">
          <p class="normalText" style="color: black; display: inline-block;">전국의 전체 유기동물 수</p>
          &nbsp;
          <p class="normalText" style="display: inline-block;">{{ animals.length }}마리</p>
        </div>

        <br/>

        <div v-show="showSpecificStat">
          <p class="normalText" style="display: inline-block;">{{ chosenArea }}</p>
          <p class="normalText" style="color: black; display: inline-block;">지역의 전체 유기동물 수</p>
          &nbsp;
          <p class="normalText" style="display: inline-block;">{{ numOfDogs + numOfCats + numOfEtc }}마리</p>
        </div>

        <p class="normalText" style="color: black; display: inline-block;">유기된 개의 수</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfDogs }}마리</p>
        &emsp;
        <p class="normalText" style="color: black; display: inline-block;">유기된 고양이의 수</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfCats }}마리</p>
        &emsp;
        <p class="normalText" style="color: black; display: inline-block;">그 외 다른 동물들의 수</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfEtc }}마리</p>
        
      </div>

      <div v-show="showNumOfAbandoned">

        <p class="normalText" style="display: inline-block;">{{ facilityName }}</p>

        <p class="normalText" style="color: black; display: inline-block;">이 보호하는 동물 수</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfDogs + numOfCats + numOfEtc }}마리</p>

        <br/>

        <p class="normalText" style="color: black; display: inline-block;">강아지의 수</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfDogs }} 마리</p>
        &emsp;
        <p class="normalText" style="color: black; display: inline-block;">고양이의 수</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfCats }} 마리</p>
        &emsp;
        <p class="normalText" style="color: black; display: inline-block;">그 외</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfEtc }} 마리</p>

        <br/>
        <br/>

        <v-container>

          <span class="row" style="justify-content: right;" @click="filterByAnimal($event)">

            <v-btn text small class="normalText">
              개
            </v-btn>
            <v-btn text small class="normalText">
              고양이
            </v-btn>
            <v-btn text small class="normalText">
              기타
            </v-btn>
            <v-btn text small class="normalText">
              전체
            </v-btn>
          
          </span>

          <v-simple-table dense>

            <tr v-for="(filteredAni, idx) in filteredAnimals" :key="idx">

              <td style="font-size: 12px;"> 
                <p>공고번호 &nbsp; {{ filteredAni.notice_no }}</p>
              </td>

              <td style="font-size: 12px;"> 
                <p>종 &nbsp; {{ filteredAni.kind }}</p>
              </td>

              <td style="font-size: 12px;"> 
                <p>나이 &nbsp; {{ filteredAni.age }}</p>
              </td>

              <td style="font-size: 12px;"> 
                <p>성별 &nbsp; {{ filteredAni.gender }}</p>
              </td>

              <td style="font-size: 12px;"> 
                <p>실종지역 &nbsp; {{ filteredAni.happenplace }}</p>
              </td>

              <td style="font-size: 12px; float: right;"> 
                <v-btn text outlined small @click="toAnimalDetailPage(filteredAni.id)">
                  상세 정보
                </v-btn>
              </td>

            </tr>

          </v-simple-table>
        </v-container>

      </div>

    </div>
  </div>
  
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  name: "KakaoMap",
 
  data() {
    return {
      map: '',

      userLat: 0,
      userLon: 0,

      loading: false,

      showBasicInfo: true,
      showFacilityStatics: false,
      showNumOfAbandoned: false,

      showWholeStat: false,
      showSpecificStat: false,

      facilityName: '',
      searchedAnimals: [],
      filteredAnimals: [],

      numOfDogs: 0,
      numOfCats: 0,
      numOfEtc: 0,
      
      chosenArea: '',
      areas: [ '서울', '경기', '인천', '강원', '충청', '대전', '전라북도', '전라남도', '경상북도', '경상남도', '부산', '대구', '제주' ],

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
      markers: []
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=07c4540ad9492806ee3339221fbf2965&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },

  computed: {
    ...mapState(['animals'])
  },

  methods: {
    ...mapActions(['fetchAnimalList']),

    chooseArea() {
      var targetArea = this.chosenArea

      const map = this.map

      var geocoder = new kakao.maps.services.Geocoder();
      
      geocoder.addressSearch(targetArea, function(result, status) {

       if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          map.setCenter(coords);
        }
      })

      this.showNumOfAbandoned = false
      this.showWholeStat = false
      this.showBasicInfo = true
      this.showSpecificStat = true
      this.searchedAnimals = [] //초기화

      for(var i=0; i<this.$store.state.animals.length; i++) {

        if(this.$store.state.animals[i].careaddr.includes(targetArea)) this.searchedAnimals.push(this.$store.state.animals[i])
      }

      this.chkNumOfEachKind(this.searchedAnimals)
    },

    toAnimalDetailPage(id) {

      let routeData = this.$router.resolve({
        name: 'AnimalDetailPage',
        params: { 'id': id }
      });window.open(routeData.href, '_blank')

    },

    filterByAnimal($event) { //문제) splice를 쓰면 여러번 눌러야 완전히 필터링됨

      this.filteredAnimals = []
      
      if($event.target.innerText == '개') {
        
        for(var i=0; i<this.searchedAnimals.length; i++) {
          
          if(this.searchedAnimals[i].kind.includes('[개]')) this.filteredAnimals.push(this.searchedAnimals[i])
          
        }
      } else if($event.target.innerText == '고양이') {

          for(var j=0; j<this.searchedAnimals.length; j++) {
          
          if(this.searchedAnimals[j].kind.includes('[고양이]')) this.filteredAnimals.push(this.searchedAnimals[j])
          
        }
      } else if($event.target.innerText == '기타') {

          for(var k=0; k<this.searchedAnimals.length; k++) {
          
          if(!this.searchedAnimals[k].kind.includes('[개]') &&  !this.searchedAnimals[k].kind.includes('[고양이]')) {

            this.filteredAnimals.push(this.searchedAnimals[k])
          }
        }
      } else {
        
        this.filteredAnimals = this.searchedAnimals
      }
    },

    initMap() {

      //내 위치 찾기 시작
      if(navigator.geolocation) {

        navigator.geolocation.getCurrentPosition(function(position) {

          var lat = position.coords.latitude, 
              lon = position.coords.longitude;
          
          var locPosition = new kakao.maps.LatLng(lat, lon)
          map.setCenter(locPosition)  
          
        })
      }
      //내 위치 찾기 끝

      //지도 객체 생성 시작
      var mapContainer = document.getElementById("map"), 
        mapOption = {
          center: new kakao.maps.LatLng(37.564343, 126.947613), 
          level: 3,
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);

      var clusterer = new kakao.maps.MarkerClusterer({
        map: map, 
        averageCenter: true,
        minLevel: 5,
      });
      //지도 객체 생성 끝

      //보호소 정보 마커로 표시 시작
      var markers = [];
      var facility = this.facility;

      for (var i = 0; i < facility.length; i++) {
        (function(i) {
         
          var geocoder = new kakao.maps.services.Geocoder();

          geocoder.addressSearch(facility[i].addr, function(result, status) {

            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
              });


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

            }

            clusterer.addMarkers(markers);

            function makeOverListener(map, marker, infowindow) {
              return function() {
                infowindow.open(map, marker);
              };
            }

            function makeOutListener(infowindow) {
              return function() {
                infowindow.close();
              };
            }
          })
        })(i)
      }
      //보호소 정보 마커로 표시 끝
    },

    async loadingAni(length) {
      this.loading = true

      await new Promise(resolve => setTimeout(resolve, length + 3000))

      this.loading = false
    },

    showAnimalsDistribution() {
      
      this.showNumOfAbandoned = false
      this.showBasicInfo = true
      
      this.showSpecificStat = false
      this.showWholeStat = true
     
      if(this.$store.state.animals == '') this.fetchAnimalList()
      this.loading = true

      setTimeout(() => { //이걸 해야 받아온 후에 지도에 마커가 정상적으로 맵핑됨

        this.chkNumOfEachKind(this.$store.state.animals)

        if(navigator.geolocation) {

        navigator.geolocation.getCurrentPosition(function(position) {
            
          var lat = position.coords.latitude, 
              lon = position.coords.longitude;

          var locPosition = new kakao.maps.LatLng(lat, lon)
          map.setCenter(locPosition)  
         
        })
      }
        
        var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(0, 0),
          level: 3, 
        };

      var map = new kakao.maps.Map(mapContainer, mapOption);
      
      var clusterer = new kakao.maps.MarkerClusterer({
        map: map, 
        averageCenter: true, 
        minLevel: 3, 
      });

      var markers = [];
      var infowindowsList = [];
      var animals = this.$store.state.animals;

      this.loading = false
      this.loadingAni(animals.length) //로딩 애니메이션

      for (var i=0; i<animals.length; i++) {
        (function(i) {

          var geocoder = new kakao.maps.services.Geocoder();

          //여기서부터 data(), state, props 등 외부에서 만들어진 변수들이 접근 불가능(var, let, const 제외)!
          geocoder.addressSearch(animals[i].careaddr, function(result, status) {
            
            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
                clickable: true
              });

              var infowindow = new kakao.maps.InfoWindow({
                content:
                  '<div style="width:150px;text-align:center;padding:6px 0;">' +
                  animals[i].careaddr +
                  "</div>",
              });

              var infoBox = new kakao.maps.InfoWindow({
                  content: 
                  '<div style="width: 370px; height: 80px; text-align: center; padding: 6px 0;">' + 
                  '<p style="font-size: 14px;">이 기관이 보호하고 있는 동물을 확인하려면 클릭하세요.</p>' + 
                  '<p class="normalText" id="name">' + animals[i].carenm + "</p> </div>",
                  removable: true
              });

              markers.push(marker)
              infowindowsList.push(infoBox)

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

              //문제) marker 이외에 infowindow에는 마우스 이벤트가 작동 x --> 해결 infoBox를 배열로 담아 for문으로 전부 닫아줌
              kakao.maps.event.addListener(marker, 'click', function() { 

                  for(var i=0; i<infowindowsList.length; i++) {
                    infowindowsList[i].close()
                  }
                
                  infoBox.open(map, marker)
                  infowindow.close()
              });
            }

            clusterer.addMarkers(markers);

            function makeOverListener(map, marker, infowindow) {
              return function() {
                infowindow.open(map, marker);
              };
            }

            function makeOutListener(infowindow) {
              return function() {
                infowindow.close();
              };
            }

          })
        })(i)

      }
      this.map = map

      }, 1000)  
    },

    showFacilitiesDistribution() {
      this.initMap()
    },

    searchByMap($event) {

      const mode = document.getElementById("name").innerHTML

      if($event.target.innerText == mode) {

        this.showBasicInfo = false
        this.showNumOfAbandoned = true

        this.searchedAnimals = [] //null하면 안됨, 초기화
        this.numOfDogs = 0
        this.numOfCats = 0
        this.numOfEtc = 0
      
        this.facilityName = mode

        for(var i=0; i<this.$store.state.animals.length; i++) {

          if(this.$store.state.animals[i].carenm == this.facilityName) this.searchedAnimals.push(this.$store.state.animals[i])
        }

        this.filteredAnimals = this.searchedAnimals

        this.chkNumOfEachKind(this.searchedAnimals)
      }
    },

    chkNumOfEachKind(list) {

      this.numOfDogs = 0
      this.numOfCats = 0
      this.numOfEtc = 0

      for(var j=0; j<list.length; j++) {

        if(list[j].kind.includes('[개]')) {
          this.numOfDogs += 1

        } else if(list[j].kind.includes('[고양이]')) {
          this.numOfCats += 1

        } else this.numOfEtc += 1
      } 
    }

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Gowun+Batang:wght@700&display=swap');

#map {
  height: 500px;  
}


tr:hover {
  color: #42b8d4;
  font-family: 'Gowun Batang', serif;
}
</style>