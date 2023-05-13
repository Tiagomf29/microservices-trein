<template>
{{ nomePerson}}
    <div class="q-pa-md row items-start q-gutter-md">
      <q-dialog v-model="exibir" >  
        <q-card class="my-card">
            <q-card-section>
                <div class="row">
                    
                    <div><q-input outlined v-model="text" :dense="dense" placeholder="Nome" hint="Nome completo do cliente" class="nomeCliente"/></div> <br/>
                    <div><q-input outlined v-model="data" :dense="dense" placeholder="Nome" hint="Data de nascimento" mask="##/##/####" fill-mask class="nasc"/></div>&nbsp;&nbsp;&nbsp;
                    <div><q-input outlined v-model="cpf" :dense="dense" placeholder="C.P.F" hint="C.P.F" mask="###.###.###-##" fill-mask class="cpf"/></div>&nbsp;&nbsp;&nbsp;
                    <div class="q-gutter-md">
                        <q-select outlined v-model="nacionalid" option-label="nome" option-value="id" :dense="dense" :options="options" label="Nacionalidade" hint="Nacionalidade" style="width: 179px"/>
                    </div>
                    
                    <br /><br /><br />

                    <div><q-input outlined v-model="nomeMae" :dense="dense" placeholder="Nome da mãe" hint="Nome completo da mãe" class="nomeMae"/></div>
                    <div><q-input outlined v-model="nomePai" :dense="dense" placeholder="Nome do pai" hint="Nome completo do pai" class="nomePai"/></div>
                    
                </div>
            </q-card-section>
           
                <q-separator />

            <q-card-section id="inserindo">
                <q-btn color="white" text-color="black" label="Gravar" icon="save" @click="putPerson()"/>&nbsp;&nbsp;&nbsp;
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
import { Buffer } from 'buffer';

const username = 'msperson';
const password = 'e1e96229eddcbe05f18939aa47c0fdcbec08863e';
const authHeader = 'Basic ' + Buffer.from(username + ':' + password).toString('base64');

export default {
    

    data:function(){
      return{  
          text: this.nomePerson.nome,
          data: this.nomePerson.nascimento,
          cpf: this.nomePerson.cpf,
          nomeMae: this.nomePerson.nomeMae,
          nomePai: this.nomePerson.nomePai,
          nacionalid: this.nomePerson.nationality,
          dense: ref(true),
          options: [{ id:1,nome:'Brasileiro'},{ id:2,nome:'Argentino'}],
          exibir: ref(false),
          mensagemErro:"",
          alert: ref(false)
      }
    },
    props:{
        nomePerson: Object
    },
    mounted(){
        this.exibir = true
    },
    methods:{
        putPerson(){

            const dataOriginal = this.data;
            const dataConvertida = dataOriginal.split("/").reverse().join("-");

            let cpf = this.cpf;
            let cpfNumerico = cpf.replace(/[^\d]/g, '');
            this.alert = false;

            axios.put('http://localhost:8080/v1/persons/'+this.nomePerson.id, {
                nome: this.text,
                nascimento: dataConvertida,
                nomeMae: this.nomeMae,
                nomePai: this.nomePai,
                cpf: cpfNumerico,
                nationality:{
                    id: this.nacionalid.id
                }
            }, {
                headers: {
                    Authorization: authHeader
                }
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

.nasc{
    position: relative;
    width: 150px;
}

.cpf{
    position: relative;
    width: 140px;
}

.nomeMae{
    position: relative;
    width: 490px;
}

.nomePai{
    position: relative;
    width: 490px;
}

.q-card{
    width: 525px;
    border: 1px solid;
    height: 348px;
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