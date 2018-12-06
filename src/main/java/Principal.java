import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import com.sun.jna.ptr.ShortByReference;

public class Principal {

    public static void main(String[] args) throws InterruptedException {

        PW_GetData vstParam = new PW_GetData();

        PW_GetData [] vstParam2 = (PW_GetData []) vstParam.toArray(10);

        ShortByReference iNumParam = new ShortByReference((short)10);

        System.out.println("\n\n\n\niNumParam Antes da função: " + iNumParam.getValue());

        String pw_iInit = ChamarFuncoes.chamarPW_iInit(".");
        System.out.println("PW_iInit: " + pw_iInit);

        String pw_iNewTransac = ChamarFuncoes.chamarPW_iNewTransac(PWOPER.INSTALL);
        System.out.println("PW_iNewTransac: " + pw_iNewTransac);

        ChamarFuncoes.addMandatoryParams();

        String pwA_iAddParam1 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.AUTHTECHUSER, "314159");
        System.out.println("PW_iAddParam Senha: " + pwA_iAddParam1);

        String pwA_iAddParam2 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.POSID, "51276");
        System.out.println("PW_iAddParam PDC: " + pwA_iAddParam2);


        String pwA_iAddParam3 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.MERCHCNPJCPF, "05471416000101");
        System.out.println("PW_iAddParam CNPJ: " + pwA_iAddParam3);

        String pwA_iAddParam4 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.DESTTCPIP, "app.tpgw.ntk.com.br:17502");
        System.out.println("PW_iAddParam IP/PORTA: " + pwA_iAddParam4);

        Thread.sleep(50);

        String pw_iExecTransac = ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam);
        System.out.println("ExecTransac:" + pw_iExecTransac);

        //String pw_iPPRemoveCard = ChamarFuncoes.chamarPW_iPPRemoveCard();
        //System.out.println(pw_iPPRemoveCard);


        //String pwA_iAddParam5 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.USINGPINPAD, "0");
        //System.out.println("PW_iAddParam Using PIN Pad: " + pwA_iAddParam5);

        //byte [] c = new byte [100];

        //String pw_iPPEventLoop = ChamarFuncoes.chamarPW_iPPEventLoop(c,100);
        //System.out.println("EventLoop: " + pw_iPPEventLoop);

        //while(pw_iPPEventLoop.equals("PWRET_NOTHING")){
          //  Thread.sleep(100);

            //pw_iPPEventLoop = ChamarFuncoes.chamarPW_iPPEventLoop(c,100);
            //System.out.println("EventLoop: " + pw_iPPEventLoop);
        //}



        System.out.println("\n\n\n\nwIdentificador Depois da função: " + vstParam2[0].wIdentificador);
        System.out.println("\n\n\n\nbTipoDeDados Depois da função: " + vstParam2[0].bTipoDeDado);
        String retorno1 = new String(vstParam2[0].szPrompt);
        System.out.println("\n\n\n\nszPrompt depois da função: " + retorno1);
        System.out.println("\n\n\n\niNumParam Depois da função: " + iNumParam.getValue());

    }
}