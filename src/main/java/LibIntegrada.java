import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import Interfaces.InterfaceComPGWebLib;
import com.sun.jna.ptr.PointerByReference;



public class LibIntegrada {

    private InterfaceComPGWebLib pgWebLib;

    public LibIntegrada () {
        this.pgWebLib = InterfaceComPGWebLib.INSTANCE; // usando a interface com a lib
    }

    public int chamarPW_iInit(String caminho){
        return pgWebLib.PW_iInit(caminho);
    }

    public int chamarPW_iNewTransac(PWOPER tipoOperacao){ return pgWebLib.PW_iNewTransac(tipoOperacao.getValor()); }

    public int chamarPW_iAddParam (PWINFO wParam, String pszValue){
        return pgWebLib.PW_iAddParam(wParam.getValor(),pszValue);
    }

    public int chamarPW_iAddParam (PWINFO wParam, int pszValue){
        return pgWebLib.PW_iAddParam(wParam.getValor(),pszValue);
    }

    public int chamarPW_iExecTransac(PW_GetData.ByReference vstParam [], ShortByPointer iNumParam) {
        return pgWebLib.PW_iExecTransac(vstParam, iNumParam.getPointer());
    }
}
