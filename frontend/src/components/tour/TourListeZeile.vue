<!-- Zeile von der Tabelle ausgeben -->

<template>
    <tr class="userLst">
        <td>
            <p>{{tour.abfahrDateTime}}</p>
        </td>
        <td>
            <p>{{tour.startOrtName}}</p>
        </td>
        <td>
            <p>{{tour.zielOrtName}}</p>
        </td>
        <td>
            <p>{{tour.distanz.toFixed(1)}}km</p>
        </td>
        <td>
            <p>{{tour.plaetze}}</p>
        </td>
        <td>
            <p>{{tour.plaetze-tour.buchungen}}</p>
        </td>
        <td>
            <p>{{tour.preis}}€</p>
        </td>
        <td>
            <button>Details</button>
        </td>
    </tr>
</template>

<script setup lang="ts">
    import { defineProps } from 'vue'
    import type { ITourDTD } from '@/views/TourenListeView.vue';
    const props = defineProps<{ tour: ITourDTD }>()
</script>