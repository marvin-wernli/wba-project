import { reactive, readonly, computed } from "vue";
import { defineStore } from "pinia";
import type { ITourDTD } from "./ITourDTD";
import { useInfo } from "@/composables/useInfo";

export const useTourenStore = defineStore("tourenstore", () => {
    const tourdata = reactive({ok:false,tourliste:Array<ITourDTD>()});

    async function updateTourListe() {
        try {
            const response = await fetch('/api/tour', {method: 'GET'})
            if(!response.ok) throw new Error(response.statusText)

            const jsondata = await response.json()
            tourdata.tourliste = jsondata
            tourdata.ok = true
        } catch (reason) {
            tourdata.ok = false
            useInfo().setzeInfo(`Fehler: ${reason}`)
        }
    }

    updateTourListe()

    return {
        tourdata,
        updateTourListe
    }
    
} )