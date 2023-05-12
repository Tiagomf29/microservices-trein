
<template>

    <div class="q-pa-md row items-start q-gutter-md">
      <q-card class="my-card">
        <q-card-section>
            <div class="row">
                
                <div><q-input outlined v-model="text" :dense="dense" placeholder="Nome" hint="Nome completo do cliente" class="nomeCliente"/></div>
                <div><q-input outlined v-model="data" :dense="dense" placeholder="Nome" hint="Data de nascimento" mask="##/##/####" fill-mask class="nasc"/></div>
                <div><q-input outlined v-model="cpf" :dense="dense" placeholder="C.P.F" hint="C.P.F" mask="###.###.###-##" fill-mask class="cpf"/></div>
                <div class="q-gutter-md">
                    <q-select outlined v-model="nacionalid" :dense="dense" :options="options" label="Nacionalidade" hint="Nacionalidade" style="width: 189px"/>
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
                        :rows-per-page-options="[2, 4, 0]"
                        :visible-columns="myColums"
                      
                    />
                </div>
            </q-card-section>
            <q-card-section v-if="rows.length > 0">
                <div class="row2">
                    &nbsp;&nbsp;&nbsp;
                    <q-btn color="white" text-color="black" label="Excluir" icon="delete" :disable="selected.length == 0" @click="deletePerson()"/>&nbsp;&nbsp;&nbsp;
                    <q-btn color="white" text-color="black" label="Alterar" icon="edit" :disable="selected.length > 1 || selected.length == 0"/>
                </div>
            </q-card-section>
        </div>

      </q-card>

    </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios'
import { Buffer } from 'buffer';

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
  { name: 'notionality', label: 'Nacionalidade', field: 'notionality', sortable: true , align: 'left'},
  
]

const selected = ref([])
const username = 'msperson';
const password = 'e1e96229eddcbe05f18939aa47c0fdcbec08863e';
const authHeader = 'Basic ' + Buffer.from(username + ':' + password).toString('base64');

export default {
    

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
        options: ['Brasileiro'],
        myColums :["nome","nascimento","cpf","nomeMae","nomePai"]
       
    }
  },

  setup () {
    
    return {
      
      
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
                id:1
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
        });       
    },

    async deletePerson() {
        
    /* eslint-disable */

        for (let index = 0; index < selected.value.length; index++) {  

           await axios.delete(`http://localhost:8080/v1/persons/${selected.value[index].id}`, {
            headers: {
                Authorization: authHeader
            }
            })
            .then(response => {
                console.log(response.data);                                       
            })
            .catch(error => {
                console.log(error);
            });
            
        }

        this.getList();
        
    },    
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






