<template>
  <div>
    <!-- Grid navigaitor -->
    <div class="p-grid">
      <div class="p-col-3"></div>
      <div class="p-col-6">
        <!-- Navigation top -->
        <Menubar :model="itemsNavigation">
           <template #end>
              <InputText v-model="findId" placeholder="Buscar" type="text" />
          </template>
        </Menubar>
      </div>
      <div class="p-col-3"></div>
    </div>
    <!-- Grid panel -->
    <div class="p-grid">
      <div class="p-col-3"></div>
      <div class="p-col-6">
        <!-- Panel general -->
        <Panel header="Administracion de Técnologia">
          <!-- Table of register -->
          <!--Agregated :selection.sync="selectedCar" @row-select="onRowSelect" -->
        <DataTable :value="filterById" :paginator="true" :rows="10" selectionMode="single" :selection.sync="selectedCar" @row-select="onRowSelect">
          <Column v-for="col of columns" :field="col.field" :header="col.header" :key="col.id"></Column>
        </DataTable>
        </Panel>
      </div>
      <div class="p-col-3"></div>
    </div>
    <!-- Show modal register venta-->
    <Dialog :style="{width: '50vh'}" header="Registrar nueva venta" :visible.sync="displayModal" :modal="true" class="p-grid p-dir-col">
      <div class="p-col">
        <InputNumber v-model="nuevaVenta.unidades" placeholder="Unidades" />
      </div>
      <div class="p-col">
        <InputNumber v-model="nuevaVenta.total" mode="currency" currency="GTQ" locale="es-GT" placeholder="Total" />
      </div>
      <div class="p-col">
        <Calendar v-model="nuevaVenta.fecha" dateFormat="yy/mm/dd" placeholder="Fecha"/>
      </div>
      <template #footer>
        <Button label="Guardar Venta" icon="pi pi-save" @click="agregarNuevaVentaStore" />
      </template>
    </Dialog><!--End modal register-->
    <!-- Show modal edit and remove-->
    <Dialog :style="{width: '70vh'}" header="Editar venta existente" :visible.sync="displayModalEdit" :modal="true" class="p-grid p-dir-col">
       <div class="p-col">
        <InputNumber v-model="car.id" placeholder="Id" :disabled="true"/>
      </div>
      <div class="p-col">
        <InputNumber v-model="car.unidades" placeholder="Unidades"/>
      </div>
      <div class="p-col">
        <InputNumber v-model="car.total" mode="currency" currency="GTQ" locale="es-GT" placeholder="Total" />
      </div>
      <div class="p-col">
        <Calendar v-model="car.fecha" dateFormat="yy/mm/dd" placeholder="Fecha"/>
      </div>
      <template #footer>
        <Button class="p-button-sm" label="Guardar Venta" icon="pi pi-save" @click="editarVentaStore" />
        <Button class="p-button-sm p-button-danger" label="Borrar" icon="pi pi-trash" @click="eliminarVentaStore" />
      </template>
    </Dialog><!--End modal edit and remove-->
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
import { endPoint } from '../services/endPoint'
export default {
  data () {
  // Agregated selectedCar: null, car: null,
    return {
      selectedCar: null,
      car: {},
      // Nombre de columnas de la tabla
      columns: [
        { field: 'id', header: 'Id' },
        { field: 'fecha', header: 'Fecha' },
        { field: 'unidades', header: 'Unidades' },
        { field: 'total', header: 'Total' }
      ],
      // Items de navegacion del panel
      itemsNavigation: [
        {
          label: 'Nuevo',
          icon: 'pi pi-check',
          command: () => {
            this.displayModal = true
          }
        }
      ],
      // Modals
      displayModal: false,
      displayModalEdit: false,
      // New sold
      nuevaVenta: {
        fecha: null,
        unidades: null,
        total: null
      },
      findId: ''
    }
  },
  created () {
    this.getAll()
  },
  methods: {
    ...mapActions({ actionAdd: 'saveAction' }),
    // Funcion que obtiene los datos de la API con axios
    getAll () {
      axios
        .get(`${endPoint.url}all`)
        .then(response => {
          this.$store.state.allDataStore = response.data
        })
    },
    // Funcion que agrega una venta
    agregarNuevaVentaStore () {
      axios.post(`${endPoint.url}save`, this.nuevaVenta)
        .then((response) => {
          this.getAll()
        })
      this.displayModal = false
    },
    // Funcion que edita una nueva venta
    editarVentaStore () {
      axios.post(`${endPoint.url}save`, this.car)
        .then((response) => {
          this.getAll()
        })
      this.displayModalEdit = false
    },
    // Funcion que elimina una venta
    eliminarVentaStore () {
      axios.get(`${endPoint.url}delete/${this.car.id}`)
        .then((response) => {
          this.getAll()
        })
      this.displayModalEdit = false
    },
    // Agregated methods
    onRowSelect (event) {
      this.car = { ...event.data }
      this.displayModalEdit = true
    }
  },
  computed: {
    // Se obtiene el store central
    ...mapState(['allDataStore']),
    // Computed properti filterById
    filterById () {
      return this.allDataStore.filter((venta) => {
        return String(venta.id).includes(this.findId)
      })
    }
  }
}
</script>

<style>
.p-dialog .p-dialog-content{
  overflow-y: visible !important;
}
</style>
