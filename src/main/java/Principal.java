import Enums.PWINFO;
import Enums.PWOPER;
import Enums.PWRET;

import static Enums.PWRET.OK;

import Estruturas.PW_GetData;
import com.sun.jna.ptr.ShortByReference;

import java.util.Scanner;



public class Principal {

    public static void main(String[] args) throws InterruptedException {

        PW_GetData vstParam = new PW_GetData();

        final PW_GetData [] vstParam2 = (PW_GetData []) vstParam.toArray(10);

        ShortByReference iNumParam = new ShortByReference((short)10);

        System.out.println("\n\n\n\niNumParam Antes da função: " + iNumParam.getValue());

        System.out.println("Init: "+ ChamarFuncoes.chamarPW_iInit("/home/thiago/Área de Trabalho/Automacao_PGWebLibC/src/main/java/").toString()); //Init

        System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.INSTALL).toString()); //New Transac

        System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams().toString()); //Adicionando parametros mandatórios

        PWRET pwret = null;

        do {
            Thread.sleep(500);
            pwret = ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam);  //Exec Transac
            System.out.println("\nExec Transac: " + pwret.toString());

            System.out.println("\n\n\n\niNumParam depois da função: " + iNumParam.getValue());


            System.out.println("\nbTipoDeDados Depois da função: " + vstParam2[0].bTipoDeDado);
            String szPrompt = new String(vstParam2[0].szPrompt);
            System.out.println("Digite a seguir : " + szPrompt);

            String szTexto1 = new String(vstParam2[0].stMenu.szTexto1);
            System.out.println(szTexto1);

            Scanner scan = new Scanner(System.in);
            String entrada = scan.nextLine();

            if(vstParam2[0].bTipoDeDado == 13) //Sai do loop de parametros para fazer iPPRemoveCard();
                break;

            System.out.println("Add Param: " + ChamarFuncoes.chamarPW_iAddParam(
                    converterPWINFO(vstParam2[0].wIdentificador),entrada)); //Adicionando parametros

            iNumParam.setValue((short)10); //Atualiza iNumParam

        } while (true);


        pwret = ChamarFuncoes.chamarPW_iPPRemoveCard();
        System.out.println("iPPRemoveCard: " + pwret.toString());


        byte [] szDspMsg = new byte [100];

        while(pwret != OK){
            Thread.sleep(100);

            pwret = ChamarFuncoes.chamarPW_iPPEventLoop(szDspMsg,100);
            System.out.println("EventLoop: " + pwret.toString());
        }

        byte [] pszData = new byte [1000];
        pwret = ChamarFuncoes.chamarPW_iGetResult(PWINFO.RESULTMSG.getValor(), pszData, 1000);
        System.out.println("iGetResult: " + pwret.toString());

        String retornoFinal = new String(pszData);
        System.out.println(retornoFinal);

    }

    private static PWINFO converterPWINFO(short valor){

        if( valor == PWINFO.AUTCAP.getValor()) return PWINFO.AUTCAP;

        if( valor == PWINFO.AUTHTECHUSER.getValor()) return PWINFO.AUTHTECHUSER;

        if( valor == PWINFO.POSID.getValor()) return PWINFO.POSID;

        if( valor == PWINFO.MERCHCNPJCPF.getValor()) return PWINFO.MERCHCNPJCPF;

        if( valor == PWINFO.DESTTCPIP.getValor()) return PWINFO.DESTTCPIP;

        if( valor == PWINFO.USINGPINPAD.getValor()) return PWINFO.USINGPINPAD;

        return null;
    }
}