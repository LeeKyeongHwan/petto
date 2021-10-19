import {
    FETCH_USER_INFO
} from './mutation-types'

export default {
    [FETCH_USER_INFO] (state, payload) {
        state.userInfo = payload 
    }
}