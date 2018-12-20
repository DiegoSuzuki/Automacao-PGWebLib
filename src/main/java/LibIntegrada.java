import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import Interfaces.InterfaceComPGWebLib;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.ptr.ShortByReference;

public class LibIntegrada {

    private InterfaceComPGWebLib pgWebLib;

    public LibIntegrada() {
        this.pgWebLib =  Native.loadLibrary (Platform.isLinux() ? "Essenciais/PGWebLib.so" : "PGwebLib",
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

    public short chamarPW_iExecTransac(PW_GetData[] vstParam, ShortByReference iNumParam) {

        return pgWebLib.PW_iExecTransac(vstParam, iNumParam);
    }

    public short chamarPW_iPP_EventLoop(byte [] szDspMsg, int ulDisplaySize ){
        return pgWebLib.PW_iPPEventLoop(szDspMsg, ulDisplaySize);
    }

    public short chamarPW_iPP_RemoveCard(){
        return pgWebLib.PW_iPPRemoveCard();
    }

    public short chamarPW_iGetResult(int iInfo, byte [] pszData, int ulDataSize){
        return pgWebLib.PW_iGetResult(iInfo, pszData, ulDataSize);
    }
}