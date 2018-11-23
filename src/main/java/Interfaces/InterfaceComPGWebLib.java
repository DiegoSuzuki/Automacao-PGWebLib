package Interfaces;
import Estruturas.PW_GetData;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface InterfaceComPGWebLib extends Library {

    InterfaceComPGWebLib INSTANCE = (InterfaceComPGWebLib) Native.loadLibrary
            (Platform.isLinux() ? "/home/thiago/Área de Trabalho/Automacao_PGWebLibC/src/main/java/libPGWebLib.so": "PGwebLib", InterfaceComPGWebLib.class); //lendo a lib pela Interface

    int PW_iInit(String caminho);

    int PW_iNewTransac(int bOper);

    int PW_iAddParam (int wParam, String pszValue);

    int PW_iAddParam (int wParam, int pszValue);

    int PW_iExecTransac (PW_GetData.ByReference vstParam[], Pointer iNumParam);

}
