import Enums.PWDAT;
import Enums.PWINFO;
import Enums.PWOPER;
import Enums.PWRET;

import static Enums.PWDAT.MENU;
import static Enums.PWRET.MOREDATA;
import static Enums.PWRET.NOTHING;
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
                case 0: //ADMINISTRATIVA

                    System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.ADMIN)); //New Transac
                    System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios

                    Thread.sleep(500);
                    System.out.println("\nExec Transac: " + ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam)); //Exec Transac);
                    System.out.println("\n\n\n\nvetores preenchidos: " + iNumParam.getValue());

                    System.out.println("\nbTipoDeDados: " + converterPWDAT(vstParam2[0].bTipoDeDado));
                    String szPrompt = formatarMensagem(vstParam2[0].szPrompt);
                    System.out.println("Digite a seguir : " + szPrompt);

                    String szTexto1 = formatarMensagem(vstParam2[0].stMenu.szTexto1);

                    if(converterPWDAT(vstParam2[0].bTipoDeDado) == MENU)
                        szTexto1 = tratarMsgADMIN(szTexto1);

                    int opcoesADM;
                    do { // fluxo da operação administrativa

                        System.out.println(szTexto1);

                        scan = new Scanner(System.in);
                        opcoesADM = scan.nextInt();

                        switch (opcoesADM) {
                            case 0: //INSTALACAO

                                //EVENTLOOP
                                byte[] szDspMsg = new byte[100];
                                do {
                                    Thread.sleep(100);
                                    pwret = ChamarFuncoes.chamarPW_iPPEventLoop(szDspMsg, 100);
                                    System.out.println("EventLoop: " + pwret.toString());
                                    String szDspMsgZ = formatarMensagem(szDspMsg);
                                    System.out.println(szDspMsgZ);

                                    if(pwret == OK) //Retorno OK saindo do loop
                                        break;

                                    System.out.println("Interromper captura?\n0 - SIM\n1 - NAO");
                                    scan = new Scanner(System.in);
                                    int interromper = scan.nextInt();
                                    if(interromper == 0)
                                        break;
                                } while (pwret == NOTHING);

                                System.out.println("\nExec Transac: " + ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam));//Exec Transac

                                // GETRESULT
                                byte[] pszData = new byte[1000];
                                pwret = ChamarFuncoes.chamarPW_iGetResult(PWINFO.RESULTMSG.getValor(), pszData, 1000);
                                System.out.println("iGetResult: " + pwret.toString());
                                String retornoFinal = formatarMensagem(pszData);
                                System.out.println(retornoFinal);

                                break;

                            case 1: //CONFIGURACAO

                                System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.INSTALL)); //New Transac
                                System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios

                                do {
                                    Thread.sleep(500);
                                    pwret = ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam);  //Exec Transac
                                    System.out.println("\nExec Transac: " + pwret);

                                    System.out.println("\nbTipoDeDados : " + converterPWDAT(vstParam2[0].bTipoDeDado));
                                    String szPromptZ = formatarMensagem(vstParam2[0].szPrompt);
                                    System.out.println("Digite a seguir : " + szPromptZ);

                                    //String szTexto1Z = formatarMensagem(vstParam2[0].stMenu.szTexto1);
                                    //System.out.println(szTexto1Z);


                                    if (vstParam2[0].bTipoDeDado == 13) { //Sai do loop de parametros para fazer iPPRemoveCard();
                                        System.out.println("Saindo do fluxo pelo RemoveCard");
                                        System.out.println("iPPRemoveCard: " + ChamarFuncoes.chamarPW_iPPRemoveCard());
                                        break;
                                    }

                                    scan = new Scanner(System.in);
                                    String entradaZ = scan.nextLine();


                                    System.out.println("Add Param: " + ChamarFuncoes.chamarPW_iAddParam(
                                            converterPWINFO(vstParam2[0].wIdentificador), entradaZ)); //Adicionando parametros

                                    iNumParam.setValue((short) 10); //Atualiza iNumParam

                                } while (pwret == MOREDATA);

                                break;

                            case 2: //MANUTENCAO
                                System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.MAINTENACE)); //New Transac
                                System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios

                                do {
                                    Thread.sleep(500);
                                    pwret = ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam); //Exec Transac

                                    if(pwret == OK)
                                        break;

                                    System.out.println("\nbTipoDeDados : " + converterPWDAT(vstParam2[0].bTipoDeDado));
                                    String szPromptZ = formatarMensagem(vstParam2[0].szPrompt);
                                    System.out.println("Digite a seguir : " + szPromptZ);

                                    String szTexto1Z = formatarMensagem(vstParam2[0].stMenu.szTexto1);

                                    if(converterPWDAT(vstParam2[0].bTipoDeDado) == MENU)
                                        szTexto1Z = tratarMsgADMIN(szTexto1Z);

                                    System.out.println(szTexto1Z);

                                    if(vstParam2[0].bTipoDeDado == 13) {
                                        System.out.println("Saindo do fluxo pelo RemoveCard");
                                        System.out.println("iPPRemoveCard: " + ChamarFuncoes.chamarPW_iPPRemoveCard());
                                        break;
                                    }

                                    scan = new Scanner(System.in);
                                    String entradaZ = scan.nextLine();

                                    if(Character.isDigit(entradaZ.charAt(0)))
                                        entradaZ = Integer.toString(Integer.parseInt(entradaZ) + 1);

                                    System.out.println("Add Param: " + ChamarFuncoes.chamarPW_iAddParam(
                                            converterPWINFO(vstParam2[0].wIdentificador), entradaZ)); //Adicionando parametros

                                } while(pwret == MOREDATA);

                                pszData = new byte[1000];
                                pwret = ChamarFuncoes.chamarPW_iGetResult(PWINFO.RESULTMSG.getValor(), pszData, 1000);
                                System.out.println("iGetResult: " + pwret.toString());
                                String retornoFinalMANUT = formatarMensagem(pszData);
                                System.out.println(retornoFinalMANUT);
                                break;

                            case 3: //VERSAO
                                System.out.println("New Transac: " + ChamarFuncoes.chamarPW_iNewTransac(PWOPER.VERSION)); //New Transac
                                System.out.println("Mandatory Params: " + ChamarFuncoes.addMandatoryParams()); //Adicionando parametros mandatórios
                                System.out.println("\nExec Transac: " + ChamarFuncoes.chamarPW_iExecTransac(vstParam2, iNumParam)); //Exec Transac);

                                pszData = new byte[1000];
                                pwret = ChamarFuncoes.chamarPW_iGetResult(PWINFO.RESULTMSG.getValor(), pszData, 1000);
                                System.out.println("iGetResult: " + pwret + "\n\n");

                                retornoFinal = formatarMensagem(pszData);
                                System.out.println(retornoFinal + "\n\n");
                                break;
                        }

                    } while(opcoesADM < 4);

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
        String retorno = "";

        for(int i = 0; i < array.length; i++){
            array[i] = i +" "+ array[i] + "\n";
        }
        for ( String linha : array) {
            retorno += linha;
        }

        return retorno;
    }
}