<template>
    <h1>Tour {{tourid}}</h1>
    <div v-if="tour">
        <p>{{tour.info}}</p>
        <p>Abfahrt am {{tour.abfahrDateTime }}</p>
    </div>
</template>


<script setup lang="ts">
// TODO: Komplette Anzeige
import { computed, defineProps } from 'vue';
import { useTourenStore } from '@/stores/tourenstore';

// TODO: tourid die mitgegeben werden kann. kein plan da.
const props = defineProps <{tourid: string}>();
var touridInt = Number(props.tourid.valueOf);
const {tourdata} = useTourenStore();
const tour = computed(() => tourdata.tourliste.find(tour => tour.id === touridInt ));



</script>