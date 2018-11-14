import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import Interfaces.InterfaceComPGWebLib;
import com.sun.jna.Pointer;


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

    public int chamarPW_iExecTransac(PW_GetData vstParam[], Pointer iNumParam) {

        Pointer pointers[] = new Pointer[vstParam.length];

        for (int i = 0; i < pointers.length; i++)
            pointers[i] = vstParam[i].getPointer();

        return pgWebLib.PW_iExecTransac(pointers, iNumParam);
    }
}
