import Enums.PWINFO;
import Enums.PWOPER;
import Enums.PWRET;
import Estruturas.PW_GetData;
import Estruturas.ShorT;
import com.sun.jna.ptr.ShortByReference;


public class ChamarFuncoes {

    static private LibIntegrada libIntegrada = new LibIntegrada();

    public static String chamarPW_iInit(String caminho){
        short pw_iInit = libIntegrada.chamarPW_iInit (caminho);
        return convertStringError(pw_iInit);
    }

    public static String chamarPW_iNewTransac(PWOPER tipoOperacao){
        short pw_iNewTransac = libIntegrada.chamarPW_iNewTransac(tipoOperacao);
        return  convertStringError(pw_iNewTransac);
    }

    public static String chamarPW_iAddParam(PWINFO wParam, String pszValue){
        short pw_iAddParam = libIntegrada.chamarPW_iAddParam(wParam, pszValue);
        return convertStringError(pw_iAddParam);
    }


    public static void addMandatoryParams(){
        chamarPW_iAddParam(PWINFO.AUTDEV,"SETIS AUTOMACAO E SISTEMA LTDA");
        chamarPW_iAddParam(PWINFO.AUTVER,"1.1.0.0");
        chamarPW_iAddParam(PWINFO.AUTNAME,"PGWEBLIBTEST");
        chamarPW_iAddParam(PWINFO.AUTCAP,"15");
    }

    public static String chamarPW_iExecTransac(PW_GetData [] vstParam, ShortByReference iNUmParam) {
        short pw_iExecTransac = libIntegrada.chamarPW_iExecTransac(vstParam, iNUmParam);
        return convertStringError(pw_iExecTransac);
    }

    public static String chamarPW_iPPEventLoop (byte [] szDspMsg, int ulDisplaySize) {
        short pw_iPP_eventLoop = libIntegrada.chamarPW_iPP_EventLoop(szDspMsg, ulDisplaySize);
        return convertStringError(pw_iPP_eventLoop);
    }

    public static String chamarPW_iPPRemoveCard () {
        short pw_iPP_removeCard = libIntegrada.chamarPW_iPP_RemoveCard();
        return convertStringError(pw_iPP_removeCard);
    }

    private static String convertStringError(short result){

        if (result == PWRET.OK.getValor()) return PWRET.OK.toString();

        if (result == PWRET.FROMHOSTPENDTRN.getValor()) return PWRET.FROMHOSTPENDTRN.toString();

        if (result == PWRET.FROMHOSTPENDTRN.getValor()) return PWRET.FROMHOSTPENDTRN.toString();

        if (result == PWRET.FROMHOSTPOSAUTHERR.getValor()) return PWRET.FROMHOSTPOSAUTHERR.toString();

        if (result == PWRET.FROMHOSTUSRAUTHERR.getValor()) return PWRET.FROMHOSTUSRAUTHERR.toString();

        if (result == PWRET.FROMHOST.getValor()) return PWRET.FROMHOST.toString();

        if (result == PWRET.TLVERR.getValor()) return PWRET.TLVERR.toString();

        if (result == PWRET.SRVINVPARAM.getValor()) return PWRET.SRVINVPARAM.toString();

        if (result == PWRET.REQPARAM.getValor()) return PWRET.REQPARAM.toString();

        if (result == PWRET.HOSTCONNUNK.getValor()) return PWRET.HOSTCONNUNK.toString();

        if (result == PWRET.INTERNALERR.getValor()) return PWRET.INTERNALERR.toString();

        if (result == PWRET.BLOCKED.getValor()) return PWRET.BLOCKED.toString();

        if (result == PWRET.FROMHOSTTRNNFOUND.getValor()) return PWRET.FROMHOSTTRNNFOUND.toString();

        if (result == PWRET.PARAMSFILEERR.getValor()) return PWRET.PARAMSFILEERR.toString();

        if (result == PWRET.NOCARDENTMODE.getValor()) return PWRET.NOCARDENTMODE.toString();

        if (result == PWRET.INVALIDVIRTMERCH.getValor()) return PWRET.INVALIDVIRTMERCH.toString();

        if (result == PWRET.HOSTTIMEOUT.getValor()) return PWRET.HOSTTIMEOUT.toString();

        if (result == PWRET.CONFIGREQUIRED.getValor()) return PWRET.CONFIGREQUIRED.toString();

        if (result == PWRET.HOSTCONNERR.getValor()) return PWRET.HOSTCONNERR.toString();

        if (result == PWRET.HOSTCONNLOST.getValor()) return PWRET.HOSTCONNLOST.toString();

        if (result == PWRET.FILEERR.getValor()) return PWRET.FILEERR.toString();

        if (result == PWRET.PINPADERR.getValor()) return PWRET.PINPADERR.toString();

        if (result == PWRET.MAGSTRIPEERR.getValor()) return PWRET.MAGSTRIPEERR.toString();

        if (result == PWRET.PPCRYPTERR.getValor()) return PWRET.PPCRYPTERR.toString();

        if (result == PWRET.SSLCERTERR.getValor()) return PWRET.SSLCERTERR.toString();

        if (result == PWRET.SSLNCONN.getValor()) return PWRET.SSLNCONN.toString();

        if (result == PWRET.GPRSATTACHFAILED.getValor()) return PWRET.GPRSATTACHFAILED.toString();

        if (result == PWRET.INVPARAM.getValor()) return PWRET.INVPARAM.toString();

        if (result == PWRET.NOTINST.getValor()) return PWRET.NOTINST.toString();

        if (result == PWRET.MOREDATA.getValor()) return PWRET.MOREDATA.toString();

        if (result == PWRET.NODATA.getValor()) return PWRET.NODATA.toString();

        if (result == PWRET.DISPLAY.getValor()) return PWRET.DISPLAY.toString();

        if (result == PWRET.INVCALL.getValor()) return PWRET.INVCALL.toString();

        if (result == PWRET.NOTHING.getValor()) return PWRET.NOTHING.toString();

        if (result == PWRET.BUFOVFLW.getValor()) return PWRET.BUFOVFLW.toString();

        if (result == PWRET.CANCEL.getValor()) return PWRET.CANCEL.toString();

        if (result == PWRET.TIMEOUT.getValor()) return PWRET.TIMEOUT.toString();

        if (result == PWRET.PPNOTFOUND.getValor()) return PWRET.PPNOTFOUND.toString();

        if (result == PWRET.TRNNOTINIT.getValor()) return PWRET.TRNNOTINIT.toString();

        if (result == PWRET.DLLNOTINIT.getValor()) return PWRET.DLLNOTINIT.toString();

        if (result == PWRET.FALLBACK.getValor()) return PWRET.FALLBACK.toString();

        if (result == PWRET.WRITERR.getValor()) return PWRET.WRITERR.toString();

        if (result == PWRET.PPCOMERR.getValor()) return PWRET.PPCOMERR.toString();

        if (result == PWRET.NOMANDATORY.getValor()) return PWRET.NOMANDATORY.toString();

        if (result == PWRET.GPRSATTACHFAILED.getValor()) return PWRET.GPRSATTACHFAILED.toString();

        return "Erro não encontrado!";
    }
}
