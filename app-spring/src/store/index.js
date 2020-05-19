import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    allDataStore: []
  },
  mutations: {
    saveMutation (state, venta) {
      state.allDataStore.push(venta)
    }
  },
  actions: {
    saveAction ({ commit }, venta) {
      commit('saveMutation', venta)
    }
  },
  modules: {
  }
})
