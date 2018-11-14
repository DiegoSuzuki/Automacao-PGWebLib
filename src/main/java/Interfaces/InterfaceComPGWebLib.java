package Interfaces;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

public interface InterfaceComPGWebLib extends Library {

    InterfaceComPGWebLib INSTANCE = (InterfaceComPGWebLib) Native.loadLibrary
            (Platform.isLinux() ? "PGWebLib": "PGwebLib", InterfaceComPGWebLib.class); //lendo a lib pela Interface

    int PW_iInit(String caminho);

    int PW_iNewTransac(int bOper);

    int PW_iAddParam (int wParam, String pszValue);

    int PW_iExecTransac(Pointer vstParam [], Pointer iNumParam);

}
