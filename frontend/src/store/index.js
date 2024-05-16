import { createStore } from "vuex";

export default createStore({
  state: {
    user: null,
    ifLogin: false
  },
  mutations: {
    setUser(state, user) {
      state.user = user
      localStorage.setItem('token', user.token)
      state.ifLogin = true
    },
    userLogout(state) {
      state.user = null
      state.ifLogin = false
      localStorage.removeItem('token')
      location.reload()
    }
  },
  actions: {
  },
  modules: {},
});