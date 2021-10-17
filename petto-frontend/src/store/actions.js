import {

} from './mutation-types'

// import axios from 'axios'
// import router from '../router'
import cookies from 'vue-cookies'

export default {
    async TodayPopUp(){
        if(cookies.isKey("TodayPopUpClose") == false){
          cookies.set("TodayPopUpClose", "todayClose","1d")
          console.log(cookies.get("TodayPopUpClose"))
        }
      }
}
