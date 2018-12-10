package Interfaces;
import Estruturas.PW_GetData;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ShortByReference;

public interface InterfaceComPGWebLib extends Library {

    short PW_iInit (String path);

    short PW_iNewTransac (int bOper);

    short PW_iAddParam (int wParam, String pszValue);

    short PW_iExecTransac (PW_GetData [] vstParam, ShortByReference iNumParam );

    short PW_iGetResult (int iInfo, byte [] pszData, int ulDataSize);

    short PW_iPPEventLoop (byte [] szDspMdg, int ulDisplaySize);

    short PW_iPPRemoveCard ();
}
