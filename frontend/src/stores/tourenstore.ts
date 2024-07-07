import { reactive, readonly, computed } from "vue";
import { defineStore } from "pinia";
import type { ITourDTD } from "./ITourDTD";
import { useInfo } from "@/composables/useInfo";
import { Client } from "@stomp/stompjs";
import type { IFrontendNachrichtEvent } from "@/services/IFrontendNachrichtEvent";

export const useTourenStore = defineStore("tourenstore", () => {
    const tourdata = reactive({ok:false,tourliste:Array<ITourDTD>()});
    const DEST = "/topic/tour";
    const stompClient = new Client({brokerURL: `ws://${window.location.host}/stompbroker`})
    stompClient.onWebSocketError = (event) =>{
        useInfo().setzeInfo(`WebSocketError: ${event.body}`)
    }
    stompClient.onStompError = (frame) => {
        useInfo().setzeInfo(`StompError: ${frame}`)
    }

    stompClient.onConnect = (frame) => {
        console.log("StompClient status: " + frame)
        console.log("subscribe incoming")
        stompClient.subscribe(DEST, (message) => {
            console.log("subscribe")
            const jsMessage: IFrontendNachrichtEvent = JSON.parse(message.body)
            console.log(jsMessage)
            if(jsMessage.typ == "TOUR"){
                console.log(JSON.stringify(jsMessage))
                updateTourListe();
            }
        })
    }

    async function updateTourListe() {
        try {
            const response = await fetch('/api/tour', {method: 'GET'})
            if(!response.ok) throw new Error(response.statusText)
            console.log("resonse status: ok")
            
            startTourenLiveUpdate()
            const jsondata = await response.json()
            tourdata.tourliste = jsondata
            tourdata.ok = true
        } catch (reason) {
            tourdata.ok = false
            useInfo().setzeInfo(`Fehler: ${reason}`)
        }
    }

    function startTourenLiveUpdate() {
        if(!stompClient.active){
            stompClient.activate();
            console.log("stompClient aktiviert")
        } else {
            console.log("stompClient ist bereits aktiv")
        }
    }

    updateTourListe()
    
    return {
        tourdata,
        updateTourListe
    }
} )