
<template>
    <div class="q-pa-md row items-start q-gutter-md">
      <q-card class="my-card">
        <q-card-section>
            <div class="row">
                
                <div><q-input outlined v-model="text" :dense="dense" placeholder="Nome" hint="Nome completo do cliente" class="nomeCliente"/></div>
                <div><q-input outlined v-model="data" :dense="dense" placeholder="Nome" hint="Data de nascimento" mask="##/##/####" fill-mask class="nasc"/></div>
                <div><q-input outlined v-model="cpf" :dense="dense" placeholder="C.P.F" hint="C.P.F" mask="###.###.###-##" fill-mask class="cpf"/></div>
                <div class="q-gutter-md">
                    <q-select outlined v-model="nacionalid" option-label="nome" option-value="id" :dense="dense" :options="options" label="Nacionalidade" hint="Nacionalidade" style="width: 189px"/>
                </div>
                
                <br /><br /><br />

                <div><q-input outlined v-model="nomeMae" :dense="dense" placeholder="Nome da mãe" hint="Nome completo da mãe" class="nomeMae"/></div>
                <div><q-input outlined v-model="nomePai" :dense="dense" placeholder="Nome do pai" hint="Nome completo do pai" class="nomePai"/></div>
                
                

            </div>
        </q-card-section>
        <br/>
        <q-separator />

        <q-card-section id="inserindo">
            <q-btn color="white" text-color="black" label="Gravar" icon="save" @click="postPerson()"/>
        </q-card-section>

        <div id="semDados" v-if="rows.length == 0">Nehum registro cadastrado!</div>

        <div v-else>
            <q-card-section>
                <div class="q-pa-md2">
                    <q-table                      
                        flat bordered
                        title="Clientes"
                        :rows="rows"
                        :columns="columns"
                        row-key="id"
                        selection="multiple"
                        v-model:selected="selected"
                        style="width: 1015px;left: -30px;"
                        :rows-per-page-options="[2, 4, 6]"
                        :visible-columns="myColums"
                      
                    />
                </div>
            </q-card-section>
            <q-card-section v-if="rows.length > 0">
                <div class="row2">
                    &nbsp;&nbsp;&nbsp;
                    <q-btn color="white" text-color="black" label="Excluir" icon="delete" :disable="selected.length == 0" @click="deletePerson(true)"/>&nbsp;&nbsp;&nbsp;
                    <q-btn color="white" text-color="black" label="Alterar" icon="edit" :disable="selected.length > 1 || selected.length == 0" @blur="alterarPerson=false" @click="alterar()"/>
                </div>
            </q-card-section>
        </div>

      </q-card>
      <strong>Rest sem Hoteoas</strong> 
      <q-dialog v-model="confirm" persistent>
      <q-card>
        <q-card-section class="row items-center">          
          <span class="q-ml-sm">Tem certeza que deseja exluir o(s) registro(s)?</span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat label="Sim" color="primary" v-close-popup @click="deletePerson(false)"/>
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
      </q-card>
    </q-dialog>
    <ms-person-alter v-if="alterarPerson" :nomePerson="objetoPerson" v-on:close-form="getList()"/>

    </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'
import { Buffer } from 'buffer';
import msPersonAlter from './ms-person-alter.vue';

const columns = [
  { id: 'id', label: 'Id', field: 'id', sortable: true, align: 'left' },
  {
    name: 'desc',
    required: true,
    label: 'Nome',
    align: 'left',
    field: row => row.nome,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'nascimento', label: 'Nascimento', field: 'nascimento', sortable: true, align: 'left' },
  { name: 'cpf', label: 'C.P.F', field: 'cpf' , align: 'left'},
  { name: 'nomeMae', label: 'Nome da mãe', field: 'nomeMae', align: 'left' },
  { name: 'nomePai', label: 'Nome do pai', field: 'nomePai' , align: 'left'},
  { name: 'nationality', label: 'Nacionalidade', field: 'nationality', sortable: true , align: 'left'},
  
]

const selected = ref([])
const username = 'msperson';
const password = 'e1e96229eddcbe05f18939aa47c0fdcbec08863e';
const authHeader = 'Basic ' + Buffer.from(username + ':' + password).toString('base64');

export default {
  components: { msPersonAlter },
    

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
        nacionalid: ref(''),
        dense: ref(true),
        options: [{ id:1,nome:'Brasileiro'},{ id:2,nome:'Argentino'}],
        myColums :["nome","nascimento","cpf","nomeMae","nomePai"],
        excluirOk: ref(false),
        mensagemErro: ref(''),
        alert: ref(false),
        alterarPerson: ref(false),
        objetoPerson: null
       
    }
  },

  setup () {
    
    return {
      
        confirm: ref(false),
    }
  },
  methods: {
    
    getList() {

        this.rows = [];

        axios.get("http://localhost:8080/v1/persons", {
            headers: {
                Authorization: authHeader
            },
            params:{
                sort: "id,desc"
            }
        }).then((response) => {
            
            for (let index = 0; index < response.data.content.length; index++) {

                const dataOriginal = response.data.content[index].nascimento;
                const dataConvertida = dataOriginal.split("-").reverse().join("/");
                response.data.content[index].nascimento = dataConvertida;
                this.rows.push(response.data.content[index]);
                
            }

        })
    },
    postPerson(){

        const dataOriginal = this.data;
        const dataConvertida = dataOriginal.split("/").reverse().join("-");

        let cpf = this.cpf;
        let cpfNumerico = cpf.replace(/[^\d]/g, '');

        axios.post('http://localhost:8080/v1/persons', {
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
            this.getList();
        })
        .catch(error => {
            console.log(error);
            this.mensagemErro = error.response.data.detail;
            this.alert = true;
        });       
    },

    async deletePerson(varConfirm) {

        if(varConfirm){
            this.confirm = true;
        }else{
            this.excluirOk = true;
        }

    /* eslint-disable */

        if (this.excluirOk){

            this.alert = false

            for (let index = 0; index < selected.value.length; index++) {  

            await axios.delete(`http://localhost:8080/v1/persons/${selected.value[index].id}`, {
                headers: {
                    Authorization: authHeader
                }
                })
                .then(response => {
                    console.log(response.data);    
                    this.excluirOk =false;  
                    this.confirm = false;                                 
                    this.getList();
                    this.selected = []
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
        this.objetoPerson =this.selected[0];
        this.alterarPerson=true;
    }  
  },
  mounted(){

    this.getList();

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

.my-card{
    width: 1020px;
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






