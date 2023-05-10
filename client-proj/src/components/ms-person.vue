
<template>

    <div class="q-pa-md row items-start q-gutter-md">
      <q-card class="my-card">
        <q-card-section>
            <div class="row">
                
                <div><q-input outlined v-model="text" :dense="dense" placeholder="Nome" hint="Nome completo do cliente" class="nomeCliente"/></div>
                <div><q-input outlined v-model="data" :dense="dense" placeholder="Nome" hint="Data de nascimento" mask="##/##/####" fill-mask class="nasc"/></div>
                <div><q-input outlined v-model="cpf" :dense="dense" placeholder="C.P.F" hint="C.P.F" mask="###.###.###-##" fill-mask class="cpf"/></div>
                
                <br /><br /><br />

                <div><q-input outlined v-model="nomeMae" :dense="dense" placeholder="Nome da mãe" hint="Nome completo da mãe" class="nomeMae"/></div>
                <div><q-input outlined v-model="nomePai" :dense="dense" placeholder="Nome do pai" hint="Nome completo do pai" class="pai"/></div>
                
                <div class="q-gutter-md">
                    <q-select outlined v-model="nacionalid" :dense="dense" :options="options" label="Nacionalidade" hint="Nacionalidade" style="width: 150px"/>
                </div>

            </div>
        </q-card-section>

        <q-separator />

        <q-card-section id="inserindo">
            <q-btn color="white" text-color="black" label="Gravar" icon="save"/>
        </q-card-section>

        <div id="semDados" v-if="selected[0]== null">Nehum registro cadastrado!</div>

        <q-card-section v-else>
            <div class="q-pa-md2">
                <q-table
                    flat bordered
                    title="Clientes"
                    :rows="rows"
                    :columns="columns"
                    row-key="name"
                    :selected-rows-label="getSelectedString"
                    selection="multiple"
                    v-model:selected="selected"
                    style="width: 643px;left: -30px;"
                />

                <div class="q-mt-md">
                    Selected: {{ JSON.stringify(selected) }}
                </div>

            </div>
        </q-card-section>

        <q-card-section v-if="selected[0]!= null">
            <div class="row2">
                &nbsp;&nbsp;&nbsp;
                <q-btn color="white" text-color="black" label="Excluir" icon="delete"/>&nbsp;&nbsp;&nbsp;
                <q-btn color="white" text-color="black" label="Alterar" icon="edit"/>
            </div>
        </q-card-section>

      </q-card>

    </div>
</template>

<script>
import { ref } from 'vue'
const columns = [
  {
    name: 'desc',
    required: true,
    label: 'Nome',
    align: 'left',
    field: row => row.name,
    format: val => `${val}`,
    sortable: true
  },
  { name: 'nascimento', label: 'Nascimento', field: 'fat', sortable: true },
  { name: 'cpf', label: 'C.P.F', field: 'carbs' },
  { name: 'mae', label: 'Nome da mãe', field: 'protein' },
  { name: 'pai', label: 'Nome do pai', field: 'sodium' },
  { name: 'nacionalidade', label: 'Nacionalidade', field: 'calcium', sortable: true },
  
]
export default {
    
  setup () {
    const selected = ref([])
    return {
      selected,
      columns,
      text: ref(''),
      data: ref(''),
      cpf: ref(''),
      nomeMae: ref(''),
      nomePai: ref(''),
      nacionalid: ref(''),
      dense: ref(true),
      options: [
        'Brasileiro'
      ]
    }
  }
}
</script>
<style scoped>

.nomeCliente{
    position: relative;
    width: 320px;
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
    width: 270px;
}

.nomePai{
    position: relative;
    width: 270px;
}

.my-card{
    width: 650px;
    height: 550px;
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






