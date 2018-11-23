import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import Estruturas.PW_Menu;
import com.sun.jna.StringArray;
import com.sun.jna.ptr.PointerByReference;

import javax.swing.text.AttributeSet;
import java.util.Vector;

public class Principal {

    public static void main(String[] args) {

        String pw_iInit = ChamarFuncoes.chamarPW_iInit(".");
        System.out.println("PW_iInit: "+ pw_iInit);

        String pw_iNewTransac = ChamarFuncoes.chamarPW_iNewTransac(PWOPER.INSTALL);
        System.out.println("PW_iNewTransac: " + pw_iNewTransac);

        ChamarFuncoes.addMandatoryParams();

        String pwA_iAddParam5 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.POSID,"54403");
        System.out.println("PW_iAddParam PDC: " + pwA_iAddParam5);

        String pwA_iAddParam6 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.DESTTCPIP,"app.tpgw.ntk.com.br:17502");
        System.out.println("PW_iAddParam IP/PORTA: " + pwA_iAddParam6);

        String pwA_iAddParam7 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.AUTHTECHUSER,"314159");
        System.out.println("PW_iAddParam Senha: " + pwA_iAddParam7);

        String pwA_iAddParam8 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.USINGPINPAD,"1");
        System.out.println("PW_iAddParam Usind PIN Pad: " + pwA_iAddParam8);

        PW_GetData.ByReference vstParam [] = (PW_GetData.ByReference[]) new PW_GetData.ByReference().toArray(1);

        System.out.println("\nAntes da função: "+ (vstParam[0].szPrompt));
        System.out.println("Antes da função: "+ (vstParam[0].bTipoDeDado) + "\n");



        ShortByPointer iNumParam = new ShortByPointer((short)10);


        String pw_iExecTransac = ChamarFuncoes.chamarPW_iExecTransac(vstParam, iNumParam);
        System.out.println("PW_iExecTransac: " + pw_iExecTransac);

        System.out.println("\nDepois da função: " + (vstParam[0]));
        //System.out.println("\nDepois da função: " + (vstParam[1]));

    }

}