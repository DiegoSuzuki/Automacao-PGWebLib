package Interfaces;
import Estruturas.PW_GetData;
import Estruturas.ShorT;
import com.sun.jna.Library;

public interface InterfaceComPGWebLib extends Library {

    short PW_iInit(String caminho);

    short PW_iNewTransac(int bOper);

    short PW_iAddParam (int wParam, String pszValue);

    short PW_iExecTransac (PW_GetData [] vstParam, ShorT.ByReference iNumParam);

    short PW_iPPEventLoop (char [] szDspMdg, int ulDisplaySize);

}
