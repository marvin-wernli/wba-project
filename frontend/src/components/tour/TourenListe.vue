<!-- Tabelle Ausgeben -->

<!-- for ... mach TourListZeil.vue mit daten-->

<template>
    <div class="border">
        <table>
            <tr class="userLst">
                <td>
                    <p>Abfahrt</p>
                </td>
                <td>
                    <p>von</p>
                </td>
                <td>
                    <p>nach</p>
                </td>
                <td>
                    <p>Entfernung</p>
                </td>
                <td>
                    <p>Plätze</p>
                </td>
                <td>
                    <p>frei</p>
                </td>
                <td>
                    <p>Preis</p>
                </td>
                <td>
                    <button>Details</button>
                </td>
            </tr>
            <tbody>
            <TourListeZeile v-for="tour in touren" :key="tour.id" :tour="tour"/>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">

    import TourListeZeile from '@/components/tour/TourListeZeile.vue'
    import type { ITourDTD } from '@/views/TourenListeView.vue';
    import { defineProps } from 'vue'
    const props = defineProps<{ touren: ITourDTD[] }>()

</script>