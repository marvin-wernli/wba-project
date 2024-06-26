<template>
  <h1 class="title">Das aktuelle Mitfahrangebot</h1>

  <input type="text" v-model="suchfeld"/>
  <button v-on:click="reset">reset</button>
  <TourenListe :touren="tourliste" ></TourenListe>
</template>

<script setup lang="ts">
/*
 * TourenListe-Komponente bitte in src/components/tour selbst implementieren
 */
import TourenListe from '@/components/tour/TourenListe.vue'
import { computed, ref } from 'vue'
import { useTourenStore } from '@/stores/tourenstore';

const {tourdata,updateTourListe} = useTourenStore();
const suchfeld = ref("");

function reset() {
  suchfeld.value = ""
}

const tourliste = computed(() => {
    if (suchfeld.value == ""){
      return tourdata.tourliste;
    } else {
      return tourdata.tourliste.filter(e =>
        e.startOrtName.toLowerCase().includes(suchfeld.value.toLowerCase())
        || e.zielOrtName.toLowerCase().includes(suchfeld.value.toLowerCase())
      )
    }
});

</script>

<style scoped></style>
