<!-- Tabelle Ausgeben -->

<!-- for ... mach TourListZeil.vue mit daten-->

<template>
    <div class="border">
        <h1>Das aktuelle Mitfahrangebot...</h1>
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
            <TourListZeile v-bind="tourliste" />
        </table>
    </div>
</template>

<script setup lang="ts">

const touren = defineProps<{
    abfahrDateTime: string
    preis: number
    plaetze: number
    startOrtName: string
    zielOrtName: string
    anbieterName: string
    distanz: number
    info: string
}

</script>