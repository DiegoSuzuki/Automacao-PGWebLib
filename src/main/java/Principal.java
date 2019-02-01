import Enums.PWDAT;
import Enums.PWINFO;
import Enums.PWOPER;
import Enums.PWRET;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws InterruptedException, IllegalAccessException {
        Scanner scan;
        int opcao, optTransac;

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
                    Transacao transacAdm = new Transacao();
                    System.out.println("New Transac " + transacAdm.newTransac(PWOPER.ADMIN));
                    System.out.println("Mandatory Params " + transacAdm.mandatoryParams());

                    System.out.println("iGetResult(STATUS): " + transacAdm.getResult(PWINFO.STATUS) +
                                        "\npszData(STATUS): " + transacAdm.getPszData(true));

                    PWRET pwRet = transacAdm.execTransac();
                    System.out.println("Exec TransacADM " + pwRet);
                    mostrarDadosDaTransacao(transacAdm);

                    int j = transacAdm.getInumParam() - 1;
                    do { // fluxo da operação administrativa
                        System.out.println("bTipoDeDados : " + transacAdm.getBtipoDeDado(j));
                        System.out.println("bTipoEntradaPermitidos " + transacAdm.getBtipoEntradaPermitidos(j));
                        System.out.println("bTamanhoMinimo " + transacAdm.getBtamanhoMinimo(j));
                        System.out.println("bTamanhoMaximo " + transacAdm.getBtamanhoMaximo(j));
                        System.out.println("szMascaraDeCaptura " + transacAdm.getSzMascaraDeCaptura(j));
                        switch (transacAdm.getBtipoDeDado(j)) {

                            case MENU:
                                System.out.println("\nDIGITE UMA OPERAÇÃO:" + transacAdm.getSzPrompt(j));
                                String vetorDeMenus [] = transacAdm.getMenu(j);
                                int metadeVetor = vetorDeMenus.length/2;
                                for (int i = 0; i < metadeVetor; i++) {
                                    System.out.println(i + " " + vetorDeMenus[i] + "(" +vetorDeMenus[metadeVetor+i]+")");
                                }
                                scan = new Scanner(System.in);
                                optTransac = scan.nextInt();

                                switch (transacAdm.getWIdentificador(j)){
                                    case LOCALINFO1:
                                        System.out.println("Add Param " + transacAdm.addParam( //adicioando opcao usuario
                                        transacAdm.getWIdentificador(j), vetorDeMenus[optTransac+metadeVetor]));
                                        break;
                                    case APAGADADOS:
                                        System.out.println("Add Param " + transacAdm.addParam( //adicioando opcao usuario
                                                transacAdm.getWIdentificador(j), Integer.toString(optTransac + 1)));
                                        break;
                                    case VIRTMERCH:
                                        System.out.println("Add Param " + transacAdm.addParam( //adicioando opcao usuario
                                                transacAdm.getWIdentificador(j), vetorDeMenus[optTransac+metadeVetor]));
                                        break;
                                    default:
                                        System.out.println("Add Param " + transacAdm.addParam( //adicioando opcao usuario
                                                transacAdm.getWIdentificador(j), Integer.toString(optTransac)));
                                }
                                break;

                            case USERAUTH:
                                System.out.println("DIGITE A SENHA:");
                                scan = new Scanner(System.in);
                                String senha = scan.nextLine();
                                System.out.println("Add Param: " + transacAdm.addParam(transacAdm.getWIdentificador(j), senha));
                                break;

                            case TYPED:
                                System.out.println("DIGITE O DADO SOLICITADO: " + transacAdm.getSzPrompt(j));
                                scan = new Scanner(System.in);
                                String dado = scan.nextLine();
                                System.out.println("Add Param: " + transacAdm.addParam(transacAdm.getWIdentificador(j), dado));
                                break;

                            case PPREMCRD:
                                System.out.println("Saindo do fluxo pelo RemoveCard");
                                System.out.println("iPPRemoveCard: " +transacAdm.removeCard());
                                PWRET eventLoop = transacAdm.ippEventLoop();

                                while (eventLoop != PWRET.OK) {
                                    System.out.println("EventLoop: " + eventLoop);
                                    System.out.println(transacAdm.getsZDspMsg()); //mensagem do eventLoop
                                    eventLoop = transacAdm.ippEventLoop();
                                }
                                break;
                        }

                        j++;
                        if( j == transacAdm.getInumParam() ){

                            System.out.println("iGetResult(STATUS): " + transacAdm.getResult(PWINFO.STATUS) +
                                    "\npszData(STATUS): " + transacAdm.getPszData(true));

                            pwRet = transacAdm.execTransac();
                            System.out.println("Exec TransacADM " + pwRet);
                            mostrarDadosDaTransacao(transacAdm);
                            j = 0;
                        }

                    }while (pwRet == PWRET.MOREDATA);
                    transacAdm.ippDisplay(transacAdm.getsZDspMsg());
                 break;

                case 1: //VENDA
                    Transacao transacVenda = new Transacao();
                    System.out.println("New Transac Venda: " + transacVenda.newTransac(PWOPER.SALE));
                    System.out.println("Mandatory Params " + transacVenda.mandatoryParams());

                    System.out.println("iGetResult(STATUS): " + transacVenda.getResult(PWINFO.STATUS) +
                            "\npszData(STATUS): " + transacVenda.getPszData(true));

                    pwRet = transacVenda.execTransac();
                    System.out.println("Exec TransacADM " + pwRet);
                    mostrarDadosDaTransacao(transacVenda);

                    j = transacVenda.getInumParam() - 1;
                    do { // fluxo da operação de venda
                        System.out.println("bTipoDeDados : " + transacVenda.getBtipoDeDado(j));
                        System.out.println("bTipoEntradaPermitidos " + transacVenda.getBtipoEntradaPermitidos(j));
                        System.out.println("bTamanhoMinimo " + transacVenda.getBtamanhoMinimo(j));
                        System.out.println("bTamanhoMaximo " + transacVenda.getBtamanhoMaximo(j));
                        System.out.println("szMascaraDeCaptura " + transacVenda.getSzMascaraDeCaptura(j));

                        switch (transacVenda.getBtipoDeDado(j)) {
                            case MENU:
                                System.out.println("\nDIGITE UMA OPERAÇÃO:" + transacVenda.getSzPrompt(j));
                                String vetorDeMenus [] = transacVenda.getMenu(j);
                                int metadeVetor = vetorDeMenus.length/2;
                                for (int i = 0; i < metadeVetor; i++) {
                                    System.out.println(i + " " + vetorDeMenus[i] + "(" +vetorDeMenus[metadeVetor+i]+")");
                                }
                                scan = new Scanner(System.in);
                                optTransac = scan.nextInt();

                                switch (transacVenda.getWIdentificador(j)){
                                    case LOCALINFO1:
                                        System.out.println("Add Param " + transacVenda.addParam( //adicioando opcao usuario
                                                transacVenda.getWIdentificador(j), vetorDeMenus[optTransac+metadeVetor]));
                                        break;
                                    case APAGADADOS:
                                        System.out.println("Add Param " + transacVenda.addParam( //adicioando opcao usuario
                                                transacVenda.getWIdentificador(j), Integer.toString(optTransac + 1)));
                                        break;
                                    case VIRTMERCH:
                                        System.out.println("Add Param " + transacVenda.addParam( //adicioando opcao usuario
                                                transacVenda.getWIdentificador(j), vetorDeMenus[optTransac+metadeVetor]));
                                        break;
                                    default:
                                        System.out.println("Add Param " + transacVenda.addParam( //adicioando opcao usuario
                                                transacVenda.getWIdentificador(j), Integer.toString(optTransac)));
                                }
                                break;

                            case USERAUTH:
                                System.out.println("DIGITE A SENHA:");
                                scan = new Scanner(System.in);
                                String senha = scan.nextLine();
                                System.out.println("Add Param: " + transacVenda.addParam(transacVenda.getWIdentificador(j), senha));
                                break;

                            case TYPED:
                                System.out.println("DIGITE O DADO SOLICITADO: " + transacVenda.getSzPrompt(j));
                                scan = new Scanner(System.in);
                                String dado = scan.nextLine();
                                System.out.println("Add Param: " + transacVenda.addParam(transacVenda.getWIdentificador(j), dado));
                                break;

                            case PPREMCRD:
                                System.out.println("Saindo do fluxo pelo RemoveCard");
                                System.out.println("iPPRemoveCard: " +transacVenda.removeCard());
                                PWRET eventLoop = transacVenda.ippEventLoop();

                                while (eventLoop != PWRET.OK) {
                                    Thread.sleep(100);
                                    System.out.println("EventLoop: " + eventLoop);
                                    System.out.println(transacVenda.getsZDspMsg()); //mensagem do eventLoop
                                    eventLoop = transacVenda.ippEventLoop();
                                }
                                break;
                        }

                        j++;
                        if( j == transacVenda.getInumParam() ){

                            System.out.println("iGetResult(STATUS): " + transacVenda.getResult(PWINFO.STATUS) +
                                    "\npszData(STATUS): " + transacVenda.getPszData(true));

                            pwRet = transacVenda.execTransac();
                            System.out.println("Exec TransacADM " + pwRet);
                            mostrarDadosDaTransacao(transacVenda);
                            j = 0;
                        }

                    }while (pwRet == PWRET.MOREDATA);

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

    private static void  mostrarDadosDaTransacao(Transacao transac){
        if(transac.getInumParam() != 10) {
            System.out.println("Dados da Transação\n=========================================================\n"
                    + transac + " \n=========================================================\n");
        }
        System.out.println("Mensagens da Transação\n=====================================" + //captura Mensagem
                "\niGetResult(CNCDSPMSG): " + transac.getResult(PWINFO.CNCDSPMSG) +
                "\npszData(CNCDSPMSG): " + transac.getPszData(true) +
                "\niGetResult(RESULTMSG): " + transac.getResult(PWINFO.RESULTMSG) +
                "\npszData(RESULTMSG): " + transac.getPszData(true) +
                "\niGetResult(RCPTFULL): " + transac.getResult(PWINFO.RCPTFULL) +
                "\npszData(RCPTFULL): " + transac.getPszData(false) +
                "\n=====================================");
    }

}