import Enums.PWINFO;
import Enums.PWOPER;
import Enums.PWRET;
import Estruturas.PW_GetData;
import com.sun.jna.ptr.ShortByReference;

import static Enums.PWRET.*;


public class ChamarFuncoes {

    static private LibIntegrada libIntegrada = new LibIntegrada();

    public static PWRET chamarPW_iInit(String caminho){
        short pw_iInit = libIntegrada.chamarPW_iInit (caminho);
        return convertPWRET(pw_iInit);
    }

    public static PWRET chamarPW_iNewTransac(PWOPER tipoOperacao){
        short pw_iNewTransac = libIntegrada.chamarPW_iNewTransac(tipoOperacao);
        return  convertPWRET(pw_iNewTransac);
    }

    public static PWRET chamarPW_iAddParam(PWINFO wParam, String pszValue){
        short pw_iAddParam = libIntegrada.chamarPW_iAddParam(wParam, pszValue);
        return convertPWRET(pw_iAddParam);
    }


    public static PWRET addMandatoryParams(){
        PWRET pwret = chamarPW_iAddParam(PWINFO.AUTDEV,"SETIS AUTOMACAO E SISTEMA LTDA");
        if(pwret != OK)
            return pwret;

        pwret = chamarPW_iAddParam(PWINFO.AUTVER,"1.1.0.0");
        if(pwret != OK)
            return pwret;

        pwret = chamarPW_iAddParam(PWINFO.AUTNAME,"PGWEBLIBTEST");
        if(pwret != OK)
            return pwret;

        pwret = chamarPW_iAddParam(PWINFO.AUTCAP,"15");
            return pwret;
    }

    public static PWRET chamarPW_iExecTransac(PW_GetData[] vstParam, ShortByReference iNUmParam) {
        short pw_iExecTransac = libIntegrada.chamarPW_iExecTransac(vstParam, iNUmParam);
        return convertPWRET(pw_iExecTransac);
    }

    public static PWRET chamarPW_iPPEventLoop (byte [] szDspMsg, int ulDisplaySize) {
        short pw_iPP_eventLoop = libIntegrada.chamarPW_iPP_EventLoop(szDspMsg, ulDisplaySize);
        return convertPWRET(pw_iPP_eventLoop);
    }

    public static PWRET chamarPW_iPPRemoveCard () {
        short pw_iPP_removeCard = libIntegrada.chamarPW_iPP_RemoveCard();
        return convertPWRET(pw_iPP_removeCard);
    }

    public static PWRET chamarPW_iGetResult (int iInfo, byte [] pszData, int ulDataSize) {
        short chamarPW_iGetResult = libIntegrada.chamarPW_iGetResult(iInfo, pszData, ulDataSize);
        return convertPWRET(chamarPW_iGetResult);
    }

    private static PWRET convertPWRET(short result){

        if (result == OK.getValor()) return OK;

        if (result == FROMHOSTPENDTRN.getValor()) return FROMHOSTPENDTRN;

        if (result == FROMHOSTPENDTRN.getValor()) return FROMHOSTPENDTRN;

        if (result == FROMHOSTPOSAUTHERR.getValor()) return FROMHOSTPOSAUTHERR;

        if (result == FROMHOSTUSRAUTHERR.getValor()) return FROMHOSTUSRAUTHERR;

        if (result == FROMHOST.getValor()) return FROMHOST;

        if (result == TLVERR.getValor()) return TLVERR;

        if (result == SRVINVPARAM.getValor()) return SRVINVPARAM;

        if (result == REQPARAM.getValor()) return REQPARAM;

        if (result == HOSTCONNUNK.getValor()) return HOSTCONNUNK;

        if (result == INTERNALERR.getValor()) return INTERNALERR;

        if (result == BLOCKED.getValor()) return BLOCKED;

        if (result == FROMHOSTTRNNFOUND.getValor()) return FROMHOSTTRNNFOUND;

        if (result == PARAMSFILEERR.getValor()) return PARAMSFILEERR;

        if (result == NOCARDENTMODE.getValor()) return NOCARDENTMODE;

        if (result == INVALIDVIRTMERCH.getValor()) return INVALIDVIRTMERCH;

        if (result == HOSTTIMEOUT.getValor()) return HOSTTIMEOUT;

        if (result == CONFIGREQUIRED.getValor()) return CONFIGREQUIRED;

        if (result == HOSTCONNERR.getValor()) return HOSTCONNERR;

        if (result == HOSTCONNLOST.getValor()) return HOSTCONNLOST;

        if (result == FILEERR.getValor()) return FILEERR;

        if (result == PINPADERR.getValor()) return PINPADERR;

        if (result == MAGSTRIPEERR.getValor()) return MAGSTRIPEERR;

        if (result == PPCRYPTERR.getValor()) return PPCRYPTERR;

        if (result == SSLCERTERR.getValor()) return SSLCERTERR;

        if (result == SSLNCONN.getValor()) return SSLNCONN;

        if (result == GPRSATTACHFAILED.getValor()) return GPRSATTACHFAILED;

        if (result == INVPARAM.getValor()) return INVPARAM;

        if (result == NOTINST.getValor()) return NOTINST;

        if (result == MOREDATA.getValor()) return MOREDATA;

        if (result == NODATA.getValor()) return NODATA;

        if (result == DISPLAY.getValor()) return DISPLAY;

        if (result == INVCALL.getValor()) return INVCALL;

        if (result == NOTHING.getValor()) return NOTHING;

        if (result == BUFOVFLW.getValor()) return BUFOVFLW;

        if (result == CANCEL.getValor()) return CANCEL;

        if (result == TIMEOUT.getValor()) return TIMEOUT;

        if (result == PPNOTFOUND.getValor()) return PPNOTFOUND;

        if (result == TRNNOTINIT.getValor()) return TRNNOTINIT;

        if (result == DLLNOTINIT.getValor()) return DLLNOTINIT;

        if (result == FALLBACK.getValor()) return FALLBACK;

        if (result == WRITERR.getValor()) return WRITERR;

        if (result == PPCOMERR.getValor()) return PPCOMERR;

        if (result == NOMANDATORY.getValor()) return NOMANDATORY;

        if (result == GPRSATTACHFAILED.getValor()) return GPRSATTACHFAILED;

        return null;
    }
}
