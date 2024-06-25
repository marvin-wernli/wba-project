// Folie 214
import { ref,readonly,reactive} from 'vue'

const info = ref<string>('');

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