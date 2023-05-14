<template>
    <div class="q-pa-md row items-start q-gutter-md">
      <q-dialog v-model="exibir" >  
        <q-card class="my-card">
            <q-card-section>
                <div class="row">
                    
                    <div><q-input outlined v-model="text" :dense="dense" placeholder="Nome" hint="Nome completo do cliente" class="nomeCliente"/></div> <br/>
                    <div class="q-gutter-md">
                        <q-select outlined v-model="tipoProduto" option-label="nome" option-value="id" :dense="dense" :options="options" label="Tipo de produto" hint="Nacionalidade" style="width: 179px"/>
                    </div>
                </div>
            </q-card-section>
           
                <q-separator />

            <q-card-section id="inserindo">
                <q-btn color="white" text-color="black" label="Gravar" icon="save" @click="putProduct()"/>&nbsp;&nbsp;&nbsp;
                <q-btn color="white" text-color="black" label="Fechar" icon="save" @click="exibir=false" />
            </q-card-section>
        </q-card>
        
      </q-dialog>
      <q-dialog v-model="alert">
      <q-card id="dialogErro">
        <q-card-section>
          <div class="text-h6">Erro</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          {{ mensagemErro }}
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="OK" color="primary" v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
    </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'

export default {
    

    data:function(){
      return{  
          text: this.pTipoProduto.nome,
          id : this.pTipoProduto.id,
          tipoProduto: this.pTipoProduto.tipo,
          dense: ref(true),
          options: [{ id:1,nome:'Brasileiro'},{ id:2,nome:'Argentino'}],
          exibir: ref(false),
          mensagemErro:"",
          alert: ref(false)
      }
    },
    props:{
        pTipoProduto: Object
    },
    mounted(){
        this.exibir = true
    },
    methods:{
        putProduct(){

            this.alert = false;

            axios.put('http://localhost:8082/products/'+this.id, {
                name: this.text,
                productType:"http://localhost:8082/productTypes/"+this.tipoProduto.id
            })
            .then(response => {
                console.log(response.data);
                this.fecharFormulario();
            })
            .catch(error => {
                console.log(error);
                this.mensagemErro = error.response.data.message;
                this.alert = true;
            });       
        },
        fecharFormulario(){
            this.$emit('close-form',true);
            this.exibir = false;
        } 
    }
  }
</script>
<style scoped>

  .nomeCliente{
    position: relative;
    width: 490px;
}


.q-card{
    width: 525px;
    border: 1px solid;
    height: 230px;
}

#inserindo{
    position: relative;
    text-align: center;
}

#dialogErro{
    width: 250px;
    border: 1px solid;
    height: 170px;    
}



</style>