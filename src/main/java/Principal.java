import Enums.PWDAT;
import Enums.PWINFO;
import Enums.PWOPER;
import Enums.PWRET;

import static Enums.PWRET.MOREDATA;
import static Enums.PWRET.OK;

import Estruturas.PW_GetData;
import com.sun.jna.ptr.ShortByReference;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Principal {

    public static void main(String[] args) throws InterruptedException {

        Scanner scan;
        PWRET pwret = null;
        PW_GetData vstParam = new PW_GetData();
        PW_GetData [] vstParam2 = (PW_GetData []) vstParam.toArray(10);
        ShortByReference iNumParam = new ShortByReference((short)10);
        int opcao;

        System.out.println("Init: "+ ChamarFuncoes.chamarPW_iInit(".")); //Init

        do {
            System.out.println("Escolher uma opção:\n" +
                "0 ADMINISTRATIVA\n" +
                "1 VENDA\n" +
                "2 SAIR");

            scan = new Scanner(System.in);
            opcao = scan.nextInt();


            switch (opcao) {
                case 0:
                    System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.ADMIN)); //New Transac
                    System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios

                    Thread.sleep(500);
                    System.out.println("\nExec Transac: " + ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam)); //Exec Transac);
                    System.out.println("\n\n\n\nvetores preenchidos: " + iNumParam.getValue());

                    System.out.println("\nbTipoDeDados: " + converterPWDAT(vstParam2[0].bTipoDeDado));
                    String szPrompt = formatarMensagem(vstParam2[0].szPrompt);
                    System.out.println("Digite a seguir : " + szPrompt);

                    String szTexto1 = formatarMensagem(vstParam2[0].stMenu.szTexto1);
                    System.out.println("Escolha uma opção: \n" + tratarMsgADMIN(szTexto1));

                    scan = new Scanner(System.in);
                    int opcoes = scan.nextInt();

                    switch (opcoes) {
                        case 0: //INSTALACAO
                            System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.INSTALL)); //New Transac
                            System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios

                            do {
                                Thread.sleep(500);
                                pwret = ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam);  //Exec Transac
                                System.out.println("\nExec Transac: " + pwret);

                                System.out.println("\nbTipoDeDados : " + converterPWDAT(vstParam2[0].bTipoDeDado));
                                String szPromptZ = formatarMensagem(vstParam2[0].szPrompt);
                                System.out.println("Digite a seguir : " + szPromptZ);

                                String szTexto1Z = formatarMensagem(vstParam2[0].stMenu.szTexto1);
                                System.out.println(szTexto1Z);

                                scan = new Scanner(System.in);
                                String entradaZ = scan.nextLine();

                                if (vstParam2[0].bTipoDeDado == 13) //Sai do loop de parametros para fazer iPPRemoveCard();
                                    break;

                                System.out.println("Add Param: " + ChamarFuncoes.chamarPW_iAddParam(
                                        converterPWINFO(vstParam2[0].wIdentificador), entradaZ)); //Adicionando parametros

                                iNumParam.setValue((short) 10); //Atualiza iNumParam

                            } while (pwret == MOREDATA);


                            pwret = ChamarFuncoes.chamarPW_iPPRemoveCard();
                            System.out.println("iPPRemoveCard: " + pwret.toString());

                            byte[] szDspMsg = new byte[100];

                            while (pwret != OK) {
                                Thread.sleep(100);

                                pwret = ChamarFuncoes.chamarPW_iPPEventLoop(szDspMsg, 100);
                                System.out.println("EventLoop: " + pwret.toString());
                            }

                            byte[] pszData = new byte[1000];
                            pwret = ChamarFuncoes.chamarPW_iGetResult(PWINFO.RESULTMSG.getValor(), pszData, 1000);
                            System.out.println("iGetResult: " + pwret.toString());

                            String retornoFinal = formatarMensagem(pszData);
                            System.out.println(retornoFinal);
                            break;

                        case 1 : //CONFIGURACAO
                            System.out.println("Implementar Config");
                            break;

                        case 2 : //MANUTENCAO
                            //System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.SALE)); //New Transac
                            //System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios

                            break;
                        case 3 : //VERSAO
                            System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.VERSION)); //New Transac
                            System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios
                            System.out.println("\nExec Transac: " + ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam)); //Exec Transac);

                            pszData = new byte[1000];
                            pwret = ChamarFuncoes.chamarPW_iGetResult(PWINFO.RESULTMSG.getValor(), pszData, 1000);
                            System.out.println("iGetResult: " + pwret +"\n\n");

                            retornoFinal = formatarMensagem(pszData);
                            System.out.println(retornoFinal+"\n\n");
                            break;
                    }

                    break;

                case 1:
                    System.out.println("implementar");
                    break;

                case 2:
                    System.out.println("Finalizado!");

                    break;

                default:
                    System.out.println("Opção invalida!");
                    break;
            }
        }while (opcao != 2);

    }



    //Conversores
    private static PWINFO converterPWINFO(short valor){
        for ( PWINFO pwinfo : PWINFO.values()){
            if(pwinfo.getValor() == valor)
                return pwinfo;
        }
        return null;
    }

    private static PWDAT converterPWDAT(byte valor){
        for ( PWDAT pwdat : PWDAT.values()){
            if(pwdat.getValor() == valor)
                return pwdat;
        }
        return null;
    }

    private static String formatarMensagem(byte [] param) {
        String mensagem = new String (param); // transaformando array de bytes em string
        String mensagemFormatada = mensagem.replace('\0',' '); //substituindo byte vazio por espaços

        Pattern p = Pattern.compile("\\s+"); // expressão para mais de um espaço
        Matcher m = p.matcher(mensagemFormatada);

        return m.replaceAll(" ");
    }

    private static String tratarMsgADMIN(String msgADMIN){
        String [] array = msgADMIN.split(" ");

        for(int i = 0; i < array.length; i++){
            array[i] = i +" "+ array[i];
        }
        return array[0] +"\n"+ array[1] +"\n"+ array[2] +"\n"+ array[3];
    }
}