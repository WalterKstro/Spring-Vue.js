<template>
<div class="p-grid">
  <div class="p-col-2"></div>
  <div class="p-col-8">
    <Panel header="Reporte de Ventas Mensuales">
      <Chart type="bar" :data="basicData"/>
    </Panel>
  </div>
  <div class="p-col-2"></div>
</div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  created () {
    this.reduceData()
  },
  data () {
    return {
      enero: 0,
      febrero: 0,
      marzo: 0,
      abril: 0,
      mayo: 0,
      junio: 0,
      julio: 0,
      basicData: {
        labels: ['Enero', 'Febreo', 'Marzo', 'Abril', 'Mayo'],
        datasets: [
          {
            label: '',
            data: [],
            backgroundColor: [
              '#EC407A',
              '#AB47BC',
              '#42A5F5',
              '#7E57C2',
              '#66BB6A',
              '#FFCA28',
              '#26A69A'
            ]
          }
        ]
      }
    }
  },
  computed: {
    ...mapState(['allDataStore']),
    filterData () {
      return this.allDataStore.map(venta => {
        return {
          fecha: venta.fecha.split('-')[1],
          total: venta.total
        }
      })
    }
  },
  methods: {
    reduceData () {
      for (const venta of this.filterData) {
        switch (venta.fecha) {
          case '01':
            this.enero += venta.total
            break
          case '02':
            this.febrero += venta.total
            break
          case '03':
            this.marzo += venta.total
            break
          case '04':
            this.abril += venta.total
            break
          case '05':
            this.mayo += venta.total
            break
          case '06':
            this.junio += venta.total
            break
        }
      }
      this.basicData.datasets[0].data[0] = this.enero
      this.basicData.datasets[0].data[1] = this.febrero
      this.basicData.datasets[0].data[2] = this.marzo
      this.basicData.datasets[0].data[3] = this.abril
      this.basicData.datasets[0].data[4] = this.mayo
      this.basicData.datasets[0].data[5] = this.junio
    }
  }
}
</script>
<style></style>
