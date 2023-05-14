<template>
  
<div>
  <pagina-principal />
</div>
</template>

<script>
import { ref } from 'vue'
import PaginaPrincipal from './components/pagina-principal.vue'


export default {
  
  components: {
    PaginaPrincipal
   
  },

  setup () {
    return {
      leftDrawerOpen: ref(false)
    }
  }
}
</script>
