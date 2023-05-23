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
          <q-td key="id" :props="props">
            {{ props.row.id }}
          </q-td>
          <q-td key="name" :props="props">
            <q-badge color="green">
              {{ props.row.product.name }}
            </q-badge>
          </q-td>
          <q-td key="quantity" :props="props">
            <q-badge color="purple">
              {{ props.row.quantity }}
            </q-badge>
          </q-td>
        </q-tr>
        
      </template>
    </q-table>
  </div>
  <q-btn color="white" text-color="black" label="Atualizar" icon="edit" @click="getList()"/>
</template>

<script>
import { defineComponent } from 'vue';
import { useQuery, provideApolloClient } from '@vue/apollo-composable';
import { ApolloClient, InMemoryCache } from '@apollo/client/core';
import { gql } from 'graphql-tag';

const apolloClient = new ApolloClient({
  uri: 'http://localhost:8083/graphql', 
  cache: new InMemoryCache(),
});

const columns = [
  {
    name: 'id',
    required: true,
    label: 'Id',
    align: 'left',
    field: 'id',
    format: val => `${val}`,
    sortable: true
  },
  { name: 'name', align: 'left', label: 'Produto', field: 'name', sortable: true },
  { name: 'quantity', label: 'Quantidade', field: 'quantity', sortable: true }
];

export default defineComponent({
  data:function() {
    
    return {
      columns,
      rows:[{
        id:1,
        quantity:10,
        product:{
          name:"bola"
        }
      }]
    };
  },
  methods:{
    getList(){
      provideApolloClient(apolloClient);

      const { result } = useQuery(gql`
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
      `);


      this.rows = []

      for (let index = 0; index < result.value.stocks.length; index++) {
        
        this.rows.push(result.value.stocks [index])
        
      }

      console.log(this.rows)  
       


    },
    teste(){
      console.log("Teste")
    }
  }
});
</script>
