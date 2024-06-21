<script setup lang="ts">
  import { computed, ref, reactive} from "vue"
  import TourenListeView from '@/views/TourenListeView.vue'

  const info = ref("Dies ist eine Nachricht")

  function error(){
    info.value = ""
  }
  function hallo(){
    info.value = "Dies ist eine Nachricht"
  }
</script>

<template> 
    <body>
        <header >
            <div class="header">
            <a  class="hUsr" >Benutzer</a>
            <a  class="hUsr" >Ort</a>
            <a  class="hUsr" >Touren</a>
            <a >DE</a>
            <a >NL</a>
            <a >EN</a>
        </div>
        <div v-if="info != ''" class="errorText">
            <span  >{{info}}</span>
            <button v-on:click="error" >x</button>
        </div>
        <TourenListeView/>
        <button v-on:click="hallo"> Fehlernachricht erzeugen </button>
        </header>
        <footer >
            <a class="impressum" >Impressum</a>
            <a >Kontakt</a>
        </footer>
    </body>
</template>

<style scoped>
</style>
