<script setup lang="ts">
  import { useInfo } from '@/composables/useInfo'
  import { RouterLink, RouterView } from 'vue-router'

  const { info, loescheInfo} = useInfo();

</script>

<template> 
    <body>
        <header >
            <div class="header">
            <a  class="hUsr" >Benutzer</a>
            <a  class="hUsr" >Ort</a>
            <RouterLink to="/touren" class="hUsr">Touren</RouterLink>
            <a >DE</a>
            <a >NL</a>
            <a >EN</a>
        </div>
        <div v-if="info != ''" class="errorText">
            <span  >{{info}}</span>
            <button v-on:click="loescheInfo" >x</button>
        </div>
        </header>
        <RouterView/>
        <footer >
            <a class="impressum" >Impressum</a>
            <a >Kontakt</a>
        </footer>
    </body>
</template>

<style scoped>
</style>
