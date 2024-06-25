<template>
  <h1 class="title">Das aktuelle Mitfahrangebot</h1>

  <TourenListe :touren="tourliste"></TourenListe>
</template>

<script setup lang="ts">
/*
 * TourenListe-Komponente bitte in src/components/tour selbst implementieren
 */
import TourenListe from '@/components/tour/TourenListe.vue'
import { ref } from 'vue'
import { useTourenStore } from '@/stores/tourenstore';
import type { ITourDTD } from '@/stores/ITourDTD';

const {tourdata,updateTourListe} = useTourenStore();
/** TODO:
 * Beachten Sie bitte, dass die Tourliste im Store erst gefüllt wird, wenn jemand updateTourListe() aufruft.
Seien Sie bitte dieser jemand und überlegen Sie sich, wie Sie dafür sorgen können, dass beim Laden der
TourenListeView anfangs einmal updateTourListe() aufgerufen wird. Sie sollten danach die im
Store hinterlegte Touren-Liste in Ihrer Webansicht sehen (sollte wie zuvor aussehen).
=> Ist hier glaube ich nicht so wie er eill :')'
 */
updateTourListe()
const tourliste = ref(tourdata.tourliste);

</script>

<style scoped></style>
