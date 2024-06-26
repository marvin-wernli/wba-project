<template>
    <h1>Tour {{tourid}}</h1>
    <p>Und die restlichen Sachen</p>
</template>


<script setup lang="ts">
// TODO: Komplette Anzeige
import { defineProps } from 'vue';
import { useTourenStore } from '@/stores/tourenstore';

// TODO: tourid die mitgegeben werden kann. kein plan da.
const props = defineProps<{ tourid : string }>()
const {tourdata} = useTourenStore();
const {id,abfahrDateTime,preis,plaetze,buchungen,startOrtName,zielOrtName,anbieterName,distanz} = tourdata;




</script>