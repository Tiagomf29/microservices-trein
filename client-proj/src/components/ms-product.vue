
<template>
    <div class="q-pa-md row items-start q-gutter-md">
      <q-card class="my-card">
        <q-card-section>
            <div class="row">
                <div><q-input outlined v-model="text" :dense="dense" placeholder="Nome" hint="Nome completo do cliente" class="nomeProduto"/></div>
                <div class="q-gutter-md">
                    <q-select outlined v-model="tipoProduto" option-label="nome" option-value="id" :dense="dense" :options="options" label="Tipo" hint="Tipo do produto" style="width: 189px"/>
                </div>
            </div>
        </q-card-section>

        <q-card-section id="inserindo">
            <q-btn color="white" text-color="black" label="Gravar" icon="save" @click="postProduct()"/>
        </q-card-section>

        <div id="semDados" v-if="rows.length == 0">Nehum registro cadastrado!</div>

        <div v-else>
            <q-card-section>
                <div class="q-pa-md2">
                    <q-table                      
                        flat bordered
                        title="Produtos"
                        :rows="rows"
                        :columns="columns"
                        row-key="id"
                        selection="multiple"
                        v-model:selected="selected"
                        style="width: 726px;left: -30px;"
                        :rows-per-page-options="[3, 6, 0]"
                        :visible-columns="myColums"
                      
                    />
                </div>
            </q-card-section>
            <q-card-section v-if="rows.length > 0">
                <div class="row2">
                    &nbsp;&nbsp;&nbsp;
                    <q-btn color="white" text-color="black" label="Excluir" icon="delete" :disable="selected.length == 0" @click="deleteProduct(true)"/>&nbsp;&nbsp;&nbsp;
                    <q-btn color="white" text-color="black" label="Alterar" icon="edit" :disable="selected.length > 1 || selected.length == 0" @blur="alterarProduct=false" @click="alterar()"/>
                </div>
                <strong>Rest com Hoteoas</strong> 
            </q-card-section>
        </div>

      </q-card>

      <q-dialog v-model="confirm" persistent>
      <q-card>
        <q-card-section class="row items-center">          
          <span class="q-ml-sm">Tem certeza que deseja exluir o(s) registro(s)?</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="Sim" color="primary" v-close-popup @click="deleteProduct(false)"/>
          <q-btn flat label="Cancelar" color="primary" v-close-popup @click="excluirOk=false"/>
        </q-card-actions>
      </q-card>
    </q-dialog>

    <q-dialog v-model="alert">
      <q-card>
        <q-card-section>
          <div class="text-h6">Erro</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{ mensagemErro }}
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="OK" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>,
    </q-dialog>
    <ms-product-alter  v-if="alterarProduct" :pTipoProduto="pTipoProduto" v-on:close-form="getList()"/>

    </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'
import MsProductAlter from './ms-product-alter.vue';

const columns = [

  {
    name: 'desc',
    required: true,
    label: 'Código',
    align: 'left',
    field: row => row.id,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'nome', label: 'Nome', field: 'nome', sortable: true, align: 'left' },
  { name: 'tipo', label: 'Tipo do produto', field: 'tipo', sortable: true, align: 'left' },
]

const selected = ref([])

export default {
  components: { MsProductAlter },
    

  data:function(){
    return{
        columns,
        selected,
        rows:[],
        text: ref(''),
        data: ref(''),
        cpf: ref(''),
        nomeMae: ref(''),
        nomePai: ref(''),
        tipoProduto: ref(''),
        dense: ref(true),
        options: [{ id:1,nome:'Esportivo'},{ id:2,nome:'Escritório'}],
        myColums :["tipo","nome"],
        excluirOk: ref(false),
        mensagemErro: ref(''),
        alert: ref(false),
        alterarProduct: ref(false),
        pTipoProduto: null
       
    }
  },

  setup () {
    
    return {
      
        confirm: ref(false),
    }
  },
  methods: {
    
    async getList() {
    this.rows = [];
    const response = await axios.get("http://localhost:8082/products",{
        params:{
            sort:"id,desc"
        }
    });
    const productPromises = response.data._embedded.products.map(async (product) => {
        const urlProdut = product._links.productType.href;
        const response2 = await axios.get(urlProdut);
        const productWithType = {
        id: product.id,    
        nome: product.name,
        tipo: response2.data.name,
        };
        return productWithType;
    });
    const products = await Promise.all(productPromises);
    this.rows = products;
    console.log(this.rows);
    },

    postProduct(){

        axios.post('http://localhost:8082/products', {
            name: this.text,
            productType:"http://localhost:8082/productTypes/"+this.tipoProduto.id
        })
        .then(response => {
            console.log(response.data);
            this.getList();
        })
        .catch(error => {
            console.log(error);
            this.mensagemErro = error.response.data.detail;
            this.alert = true;
        });       
    },

    async deleteProduct(varConfirm) {

        if(varConfirm){
            this.confirm = true;
        }else{
            this.excluirOk = true;
        }

    /* eslint-disable */

        if (this.excluirOk){

            this.alert = false

            for (let index = 0; index < selected.value.length; index++) {  

                await axios.delete(`http://localhost:8082/products/${selected.value[index].id}`)
                    .then(response => {
                        console.log(response.data);    
                        this.excluirOk =false;  
                        this.confirm = false;                                 
                        this.getList();
                    })
                    .catch(error => {
                        console.log(error);
                        this.excluirOk =false;
                        this.confirm =false;
                    });
                
            }
        }
        
    } ,
    alterar(){
        this.pTipoProduto =this.selected[0];
        this.alterarProduct=true;
    }  
  },
  mounted(){

    this.getList();

  }
}
</script>
<style scoped>

.nomeProduto{
    position: relative;
    width: 490px;
}
.my-card{
    width: 730px;
    height: 600px;
}
.q-input{
    padding: 5px;
}

.q-select{
    position: relative;
    top: 4px;
    left: 5px;
}

#botoes{
    position: relative;
    text-align: center;
}

#inserindo{
    position: relative;
    text-align: center;
}

.q-card {
    border: 1px solid;
}

.row2{
    position: relative;
    text-align: center;
    top: -10px;
}

.q-pa-md2{
    position: relative;
    top: -20px;
    left: 15px;
}



#semDados{
    display: flex;
    justify-content: center;
    align-items: center;
    width:600px;
    height:180px;
    margin:auto;
    font-family:Arial;
    font-weight: bold;
    font-size: 25px;
}

</style>






