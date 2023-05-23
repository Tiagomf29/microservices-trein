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
        <q-tr :props="props" @click="onRowClick(props.row)">
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

  <q-btn color="white" text-color="black" label="Pesquisar" icon="search" @click="getList" />
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
      text:''
    };
  },
  setup() {
    const rows = ref([]);

    const getList = async () => {
      try {
        const response = await axios.post('http://localhost:8083/graphql', {
          query: `
            query listStock {
              stocks {
                id
                product {
                  id
                  name
                }
                quantity
              }
            }
          `,
        });

        rows.value = response.data.data.stocks;
  
      } catch (error) {
        console.error(error);
      }
    };

    return {
      getList,
      rows,
      onRowClick: (row) => alert(`${row.product.name} clicked`),
    };
  },
  mounted(){
    this.getList()
  }
});
</script>
