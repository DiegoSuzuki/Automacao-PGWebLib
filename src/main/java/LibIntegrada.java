import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import Estruturas.ShorT;
import Interfaces.InterfaceComPGWebLib;
import com.sun.jna.Native;
import com.sun.jna.Platform;

public class LibIntegrada {

    private InterfaceComPGWebLib pgWebLib;

    public LibIntegrada() {
        this.pgWebLib =  Native.loadLibrary (Platform.isLinux() ? "PGWebLib.so" : "PGwebLib",
                InterfaceComPGWebLib.class); //lendo a lib pela Interface
    }

    public short chamarPW_iInit(String caminho){
        return pgWebLib.PW_iInit(caminho);
    }

    public short chamarPW_iNewTransac(PWOPER tipoOperacao){
        return pgWebLib.PW_iNewTransac(tipoOperacao.getValor());
    }

    public short chamarPW_iAddParam (PWINFO wParam, String pszValue){
        return pgWebLib.PW_iAddParam(wParam.getValor(),pszValue);
    }

    public short chamarPW_iExecTransac(PW_GetData [] vstParam, ShorT.ByReference iNumParam) {
        return pgWebLib.PW_iExecTransac(vstParam, iNumParam);
    }

    public short chamarPW_iPP_EventLoop(char[] szDspMsg, int ulDisplaySize ){
        return pgWebLib.PW_iPPEventLoop(szDspMsg, ulDisplaySize);
    }
}
