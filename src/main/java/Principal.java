import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import Estruturas.ShorT;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import java.io.UnsupportedEncodingException;

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        PW_GetData vstParam = new PW_GetData();

        final PW_GetData [] vstParam2 = (PW_GetData []) vstParam.toArray(10);

        final ShorT.ByReference iNumParam = new ShorT.ByReference((short)10);

        System.out.println("\n\n\n\niNumParam Antes da função: " + iNumParam.x);

        String pw_iInit = ChamarFuncoes.chamarPW_iInit(".");
        System.out.println("PW_iInit: " + pw_iInit);

        String pw_iNewTransac = ChamarFuncoes.chamarPW_iNewTransac(PWOPER.INSTALL);
        System.out.println("PW_iNewTransac: " + pw_iNewTransac);

        ChamarFuncoes.addMandatoryParams();

        Thread.sleep(600);

        String pw_iExecTransac = ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam);
        System.out.println("ExecTransac:" + pw_iExecTransac);



        System.out.println("\n\n\n\nwIdentificador Depois da função: " + vstParam2[0].wIdentificador);
        System.out.println("\n\n\n\nbTipoDeDados Depois da função: " + vstParam2[0].bTipoDeDado);
        String retorno1 = new String(vstParam2[0].szPrompt);
        System.out.println("\n\n\n\nszPrompt depois da função: " + retorno1);
        System.out.println("\n\n\n\niNumParam Depois da função: " + iNumParam.x);



        /*
        String pwA_iAddParam1 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.POSID, "51276");
        System.out.println("PW_iAddParam PDC: " + pwA_iAddParam1);

        String pwA_iAddParam2 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.DESTTCPIP, "app.tpgw.ntk.com.br:17502");
        System.out.println("PW_iAddParam IP/PORTA: " + pwA_iAddParam2);

        String pwA_iAddParam3 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.MERCHCNPJCPF, "05471416000101");
        System.out.println("PW_iAddParam CNPJ: " + pwA_iAddParam3);

        String pwA_iAddParam4 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.USINGPINPAD, "0");
        System.out.println("PW_iAddParam Using PIN Pad: " + pwA_iAddParam4);


        final PW_GetData.ByReference pwGetData =  new PW_GetData.ByReference();

        final PW_GetData.ByReference [] vstParam = (PW_GetData.ByReference[]) pwGetData.toArray(10);
        */


    }
}