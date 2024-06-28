<script setup lang="ts">
  import { computed, ref, reactive} from "vue"
  import TourenListeView from '@/views/TourenListeView.vue'
  import { useInfo } from '@/composables/useInfo'
  import { RouterView } from 'vue-router'
  import TourView from '@/views/TourView.vue'

  const { info, loescheInfo} = useInfo()

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
        <div v-if="info != ''" class="errorText"></div>
            <span  >{{info}}</span>
            <button v-on:click="loescheInfo" >x</button>
        </div>
        </header>
        <RouterView/>
        <TourView tourid="1"></TourView>
        <p>Hier sollte ihre Werbung stehen.</p>
        <footer >
            <a class="impressum" >Impressum</a>
            <a >Kontakt</a>
        </footer>
    </body>
</template>

<style scoped>
</style>
