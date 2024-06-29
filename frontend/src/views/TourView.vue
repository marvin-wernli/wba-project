<template>
    <div v-if="tour">
        <p v-html="infoText"></p>
    </div>
    <div v-else>
        <p>Wähle eine Tour für mehr Details</p>
    </div>
</template>


<script setup lang="ts">
import { computed, defineProps, toRef, toRefs } from 'vue';
import { useTourenStore } from '@/stores/tourenstore';

const props = defineProps <{tourid: string}>();
const { tourid } = toRefs(props);
const touridInt = parseInt(tourid.value);

const {tourdata} = useTourenStore();
const tour = computed(() => tourdata.tourliste.find(tour => tour.id === touridInt ));

const infoText = computed(() => {
    if (!tour.value) return '';
    return `<h1>Tour ${tour.value.id}: ${tour.value.startOrtName} - ${tour.value.zielOrtName}</h1> 
            ${tour.value.info}  <br><br>
            Abfahrt am <b>${tour.value.abfahrDateTime}</b>,  <br>
            Preis <b>${tour.value.preis} EUR</b> für ${tour.value.distanz} km. <br>
            Anbieter ist <b>${tour.value.anbieterName}</b>  <br>
            Es sind von ${tour.value.plaetze} Plätzen bisher ${tour.value.buchungen} 
            gebucht <b>(${tour.value.plaetze - tour.value.buchungen} freie Plätze)</b>  <br>`
    ;
});

</script>