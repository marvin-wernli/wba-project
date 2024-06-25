// Folie 214
import { ref,readonly,reactive} from 'vue'

const info = ref<string>('Oh nein! Spinne auf dem Bildschirm!');

export function useInfo() {

    function loescheInfo(): void {
        info.value = ''
    } 

    function setzeInfo(msg: string):void {
        info.value = msg
    }

    return {
        info:reactive(readonly(info)),
        loescheInfo,
        setzeInfo
    }

}