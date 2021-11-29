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
        
          <v-btn text color="#42b8d4" @click="showFacilitiesDistribution" v-on="on" v-bind="attrs" :loading="loading" large>
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

      <!-- <div id="facilInfoBox"> -->  <div v-show="showBasicFacilInfo">

        <p class="normalText" style="color: black; display: inline-block;">전국의 전체 보호소 수</p>
          &nbsp;
        <p class="normalText" style="display: inline-block;">{{ facilityList.length }}개 기관</p>
        <p class="normalText" style="color: black; display: inline-block;">이 검색되었습니다.</p>

        <div id="facilInfo" style="display: none;"> <!-- <div v-show="showSpecificInfoFacil"> -->

          <p class="normalText" style="color: black; display: inline-block;">현재 선택된 보호소</p>

          <br/>
          
          <p class="normalText" style="color: black; display: inline-block;">기관명</p>
          &nbsp;
          <p class="normalText" style="display: inline-block;">{{ searchedFacil.facilityName }}</p>

          &emsp; &emsp; &emsp;

          <p class="normalText" style="color: black; display: inline-block;">주소</p>
          &nbsp;
          <p class="normalText" style="display: inline-block;">{{ searchedFacil.facilityAddr }}</p>

          &emsp; &emsp; &emsp;

          <p class="normalText" style="color: black; display: inline-block;">연락처</p>
          &nbsp;
          <p class="normalText" style="display: inline-block;">{{ searchedFacil.facilityTel }}</p>

          &emsp; &emsp; &emsp;

          <p class="normalText" style="color: black; display: inline-block;">보호 동물</p>
          &nbsp;
          <p class="normalText" style="display: inline-block;">{{ searchedFacil.saveTrgtAnimal }}</p>

        </div>

        <div id="facilsInArea" style="display: none">  <!-- <div v-show="showSpecificAreaFacil"> -->

          <p class="normalText" style="display: inline-block;">{{ chosenArea }}지역</p>
          <p class="normalText" style="color: black; display: inline-block;">의 보호소 수</p>
          &nbsp;
          <p class="normalText" style="display: inline-block;">{{ searchedFacilities.length }}개</p>

          <v-container>
            <v-simple-table dense>

              <tr v-for="(searchedFacility, index) in searchedFacilities" :key="index">

                <td style="font-size: 12px;"> 
                  <p>{{ searchedFacility.facilityName }}</p>
                </td>

                <td style="font-size: 12px;"> 
                  <p>주소 &nbsp; {{ searchedFacility.facilityAddr }}</p>
                </td>

                <td style="font-size: 12px;"> 
                  <p>연락처 &nbsp; {{ searchedFacility.facilityTel }}</p>
                </td>

                <td style="font-size: 12px;"> 
                  <p>구조 동물 &nbsp; {{ searchedFacility.saveTrgtAnimal }}</p>
                </td>

                <td style="font-size: 12px; float: right;"> 
                  <v-btn text outlined small @click="toFacilityDetailPage(searchedFacility.facilityNo)">
                    상세 정보
                  </v-btn>
                </td>

              </tr>

            </v-simple-table>
          </v-container>

        </div>
      </div>

      <div v-show="showBasicInfo">
        <p class="normalText" style="display: inline-block;">{{ chosenArea }}</p>
        <p class="normalText" style="color: black; display: inline-block;">지역의 전체 유기동물 수</p>
        &nbsp;
        <p class="normalText" style="display: inline-block;">{{ numOfDogs + numOfCats + numOfEtc }}마리</p>
        <br/>
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

      showBasicFacilInfo: true,

      searchedFacilities: [],
      searchedFacil: '',

      showBasicInfo: false,
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
      
      chosenArea: '서울',
      areas: [ '서울', '경기', '인천', '강원', '충청', '대전', '전라북도', '전라남도', '경상북도', '경상남도', '부산', '대구', '제주' ],

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

    if(this.$cookies.isKey("user")) {

      this.$store.state.session = this.$cookies.get("user");
      
      if(this.$store.state.session != null) {

          this.$store.dispatch('fetchAlarmList', this.session.id)
          this.$store.state.isLoggedIn = true;
      }
    }
  },

  computed: {
    ...mapState(['animals', 'facilityList', 'isLoggedIn', 'session'])
  },

  methods: {
    ...mapActions(['fetchAnimalLisByLocation', 'fetchFacilityList']),

    chooseArea() {
      const targetArea = this.chosenArea
      const map = this.map
      var geocoder = new kakao.maps.services.Geocoder();
      
      geocoder.addressSearch(targetArea, function(result, status) {
       if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          map.setCenter(coords);
        }
      })

      if(this.showBasicFacilInfo) { //지역별 *보호소* 정보

        const facilInfo = document.querySelector('#facilInfo') //this.showSpecificInfoFacil = false
        facilInfo.style.display = 'none'

        const facilsInArea = document.querySelector('#facilsInArea') //this.showSpecificAreaFacil = true
        facilsInArea.style.display = 'block'

        this.searchedFacilities = []

        for(var i=0; i<this.$store.state.facilityList.length; i++) {

          if(this.$store.state.facilityList[i].facilityAddr.includes(targetArea)) this.searchedFacilities.push(this.$store.state.facilityList[i])
        }
      }

      else if(!this.showBasicFacilInfo) { //지역별 *동물* 정보

        this.showNumOfAbandoned = false
        this.showWholeStat = false
        this.showBasicInfo = true
        this.showSpecificStat = true
        this.searchedAnimals = [] //초기화

        this.showAnimalsDistribution()
          .then(() => {

            for(var j=0; j<this.$store.state.animals.length; j++) {
              if(this.$store.state.animals[j].careaddr.includes(targetArea)) this.searchedAnimals.push(this.$store.state.animals[j])
            }
            this.chkNumOfEachKind(this.searchedAnimals)
          })
      }
    },

    toFacilityDetailPage(facilityNo) {
      let routeData = this.$router.resolve({
        name: 'FacilityReadPage',
        params: { 'facilityNo': facilityNo }
      });window.open(routeData.href, '_blank')
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

    async initMap() {

      this.showBasicFacilInfo = true

      if(this.$store.state.facilityList == '') await this.fetchFacilityList()

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
      var facility = this.$store.state.facilityList;

      var geocoder = new kakao.maps.services.Geocoder();

      for (var i = 0; i < facility.length; i++) {
        (function(i) {
         
          geocoder.addressSearch(facility[i].facilityAddr, function(result, status) {

            if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
              });

              var infowindow = new kakao.maps.InfoWindow({
                content:
                  '<div style="width: 150px; text-align: center; padding: 6px 0;">' +
                  '<p class="normalText" id="name">' + facility[i].facilityName + '</p>' + 
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
      this.map = map
    },

    async loadingAni(length) {
      this.loading = true
      await new Promise(resolve => setTimeout(resolve, length + 3000))
      this.loading = false
    },

    async showAnimalsDistribution() {

      this.showBasicFacilInfo = false
      this.showSpecificAreaFacil = false
      this.showSpecificInfoFacil = false

      this.showNumOfAbandoned = false
      this.showBasicInfo = true
      
      this.showSpecificStat = false
      this.showWholeStat = true
     
      //if(this.$store.state.animals == '') await this.fetchAnimalLisByLocation(this.chosenArea)
      await this.fetchAnimalLisByLocation(this.chosenArea)

      this.loading = true

      setTimeout(() => { 

        this.chkNumOfEachKind(this.$store.state.animals)
        
        const targetArea = this.chosenArea
        var geocoder = new kakao.maps.services.Geocoder();
        
        geocoder.addressSearch(targetArea, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            map.setCenter(coords);
          }
        })
        
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

        //var geocoder = new kakao.maps.services.Geocoder();

        for (var i=0; i<animals.length; i++) { 

          (function(i) {
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

                kakao.maps.event.addListener(
                  map,
                  "mouseout",
                  makeRemoveListener(infoBox)
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

              function makeRemoveListener() {
                return function() {

                  // for(var i=0; i<infowindowsList.length; i++) {
                  //   infowindowsList[i].close()
                  // }
                  infoBox.close()
                };
              }

            })
          })(i)
        }

        this.map = map
      }, 1000)  
    },

    showFacilitiesDistribution() {

      this.showBasicInfo = false
      this.showFacilityStatics = false
      this.showNumOfAbandoned = false
      this.showWholeStat = false
      this.showSpecificStat = false

      this.initMap()
    },

    searchByMap($event) {

      this.facilityName = document.getElementById("name").innerHTML

      if(this.showBasicFacilInfo) {
        
        const facilsInArea = document.querySelector('#facilsInArea') //this.showSpecificAreaFacil = true
        facilsInArea.style.display = 'none'

        const facilInfo = document.querySelector('#facilInfo') //this.showSpecificInfoFacil = true
        facilInfo.style.display = 'block'

        for(var j=0; j<this.$store.state.facilityList.length; j++) {

          if(this.$store.state.facilityList[j].facilityName == this.facilityName) {

            this.searchedFacil = this.$store.state.facilityList[j]
          }
        }

      }

      else if($event.target.innerText == this.facilityName) {

        this.showBasicInfo = false
        this.showNumOfAbandoned = true
        this.searchedAnimals = [] //null하면 안됨, 초기화

        this.numOfDogs = 0
        this.numOfCats = 0
        this.numOfEtc = 0

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