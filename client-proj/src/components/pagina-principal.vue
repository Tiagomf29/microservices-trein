<template>
  <div>
    <q-splitter
      v-model="splitterModel"
      style="height: 700px"
    >

      <template v-slot:before>
        <div class="q-pa-md">
          <q-tree
            :nodes="simple"
            node-key="label"
            selected-color="primary"
            v-model:selected="selected"
            default-expand-all
          />
        </div>
      </template>

      <template v-slot:after >
       
        <q-tab-panels
          v-model="selected"
          animated
          transition-prev="jump-up"
          transition-next="jump-up"

        >
          <q-tab-panel name="Clientes">
            <ms-person />            
          </q-tab-panel>

          <q-tab-panel name="Produtos">
            <ms-product />            
          </q-tab-panel>

          <q-tab-panel name="Compras">
            
          </q-tab-panel>

          <q-tab-panel name="Vendas">
            
          </q-tab-panel>
        
          <q-tab-panel name="Estoque">
             
          </q-tab-panel>

        </q-tab-panels>
        
      </template>
    </q-splitter>
  </div>
</template>
  
  <script>
  import { ref } from 'vue'
import msPerson from './ms-person.vue'
import MsProduct from './ms-product.vue'
  
  export default {
  components: { msPerson, MsProduct },
    setup (){
    return {
      splitterModel: ref(-200),
      selected: ref('Food'),

      simple: [
        {
          label: 'Sistema de compras e vendas de produtos',
          children: [
            {
              label: 'Clientes',
              icon: 'person_add'
            },
            {
              label: 'Produtos',
              icon: 'production_quantity_limits'
            },
            {
              label: 'Compras',
              icon: 'shop_2'
            },
            {
              label: 'Vendas',
              icon: 'point_of_sale'
            },
            {
              label: 'Estoque',
              icon: 'inventory_2'
            }
          ]
        }
      ]
    }
  
    }
  }
  </script>