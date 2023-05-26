<template>

  <div class="q-pa-md">
    <q-table
      flat bordered
      title="Estoque"
      :rows="rows"
      :columns="columns"
      row-key="id"
      :rows-per-page-options="[10, 20, 0]"
    >
      <template v-slot:body="props">
        <q-tr :props="props" @click="rowClick(props.row)">
          <q-td key="id" :props="props">{{ props.row.id }}</q-td>
          <q-td key="name" :props="props">
            <q-badge color="green">{{ props.row.product.name }}</q-badge>
          </q-td>
          <q-td key="quantity" :props="props">
            <q-badge color="purple">{{ props.row.quantity }}</q-badge>
          </q-td>
        </q-tr>
      </template>
    </q-table>
  </div>
  <q-input v-model="text" label="Pequisa por nome do produto" stack-label  />

  <q-btn color="white" text-color="black" label="Pesquisar" icon="search" @click="getList(text)" />

  <q-dialog v-model="alert">
    <q-card class="my-card" >
        <q-card-section>
         {{ nomeProduto }}
          <q-input v-model="qtde" :maxlength="10" label="Quantidade" stack-label  /> <br /><br />
          <q-btn color="white" text-color="black" label="Atualizar" icon="update"  @click="atualizar()"/>
        </q-card-section>
      </q-card>
  </q-dialog>


</template>

<script>
import { defineComponent, ref } from 'vue';
import axios from 'axios';

const columns = [
  {
    name: 'id',
    required: true,
    label: 'Código',
    align: 'left',
    field: 'id',
    format: (val) => `${val}`,
    sortable: true,
  },
  { name: 'name', align: 'left', label: 'Produto', field: 'name', sortable: true },
  { name: 'quantity', label: 'Quantidade', field: 'quantity', sortable: true },
];

export default defineComponent({
  data() {
    return {
      columns,
      text:"",
      alert:false,
      nomeProduto:"",
      qtde:"",
      vId:0,
  
    };
  },
  setup() {
    const rows = ref([]);

    const getList = async (vlr) => {

      try {
        const response = await axios.post('http://localhost:8083/graphql', {
          query: `
            query ListStockByNameProduct($name: String!) {
              stockByProductName (name: $name){
                id
                product {
                  id
                  name
                }
                quantity
              }
            }
          `,
          variables:{
            name: vlr
          }
        });

        rows.value = response.data.data.stockByProductName;
        
  
      } catch (error) {
        console.error(error);
      }
    };

    const updateStock = async (varId, varProductId, varQtde, vText) => {
      const url = 'http://localhost:8083/graphql';
 
      const mutation = `
        mutation {
          updateStock(
            id: ${varId}
            product: {
              id: ${varProductId}
            }
            quantity: ${varQtde}
          ) {
            id
          }
        }
      `;

      try {
        const response = await axios.post(url, {
          query: mutation
        });


        if(response.data){
          getList(vText)
        }

        console.log(response.data);
        
      } catch (error) {
        console.error(error);
      }
    };

    return {
      getList,
      updateStock,
      rows,
      onRowClick: (row) => alert(`${row.product.name} clicked`),
    };
  },
  mounted(){
    this.getList(this.text)
  },
  methods:{
    rowClick(row){

      this.alert = false;
      this.productid = row.product.id;
      this.nomeProduto = row.product.name;
      this.qtde = row.quantity; 
      this.vId = row.id;
      this.alert  = true;

    },
    atualizar(){
      this.updateStock(this.vId, this.productid, this.qtde, this.text);
      this.alert = false;
    }
  }
});
</script>
