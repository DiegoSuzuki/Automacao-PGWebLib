import Enums.PWDAT;
import Enums.PWINFO;
import Enums.PWOPER;
import Enums.PWRET;
import Estruturas.PW_GetData;
import Estruturas.PW_Menu;
import com.sun.jna.ptr.ShortByReference;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transacao {

    private PWOPER oper = null;
    private final ShortByReference iNumParam;
    private PW_GetData [] vstParam;
    private byte[] szDspMsg, pszData;

    public Transacao() {
        this.iNumParam = new ShortByReference((short)10);
        this.vstParam =  (PW_GetData[]) new PW_GetData().toArray(iNumParam.getValue());
        this.szDspMsg = new byte[100];
        this.pszData  = new byte[1000];
    }

    public PWRET newTransac(PWOPER oper){
        this.oper = oper;
        return ChamarFuncoes.chamarPW_iNewTransac(oper);
    }

    public PWRET addParam(PWINFO param, String data){
        return  ChamarFuncoes.chamarPW_iAddParam(param, data);
    }

    public PWRET mandatoryParams(){
        return ChamarFuncoes.addMandatoryParams();
    }

    public PWRET execTransac() throws InterruptedException {
        setINumParam((short)10);
        Thread.sleep(500);
        return ChamarFuncoes.chamarPW_iExecTransac(vstParam, iNumParam);
    }

    public PWRET ippDisplay( String pszMgs){
        return ChamarFuncoes.chamarPW_iPPDisplay(pszMgs);
    }

    public PWRET ippEventLoop() throws InterruptedException {
        Thread.sleep(100);
        return ChamarFuncoes.chamarPW_iPPEventLoop(szDspMsg, 100);
    }

    public PWRET removeCard(){
        return ChamarFuncoes.chamarPW_iPPRemoveCard();
    }
    public  PWRET getResult(PWINFO param){
        this.pszData = new byte[1000];
        return ChamarFuncoes.chamarPW_iGetResult(param, pszData, 1000);
    }

    //GETS
    public PW_GetData getVstParam(int index){
        return vstParam[index];
    }

    public PW_GetData[] getVstParamAll(){
        return vstParam;
    }

    public String getPszData(boolean formatada){
        if(formatada)
            return formatarMensagem(pszData);
        return new String(pszData);
    }

    public String getsZDspMsg(){
            return formatarMensagem(szDspMsg).replace(" ","\n");
    }

    public short getInumParam(){
        return iNumParam.getValue();
    }

    //SETS
    public void setINumParam(short value){
        this.iNumParam.setValue(value);
    }


    //GETS PARAMENTROS Do VETOR
    public PWINFO getWIdentificador(int index){
        return converterPWINFO(vstParam[index].wIdentificador);
    }

    public PWDAT getBtipoDeDado(int index){
        return converterPWDAT(vstParam[index].bTipoDeDado);
    }

    public String getSzPrompt(int index){
        return formatarMensagem(vstParam[index].szPrompt);
    }

    public int getBnumOpcoes(int index){
        return vstParam[index].bNumOpcoesMenu;
    }

    public String getSzMascaraDeCaptura(int index){
        return formatarMensagem(vstParam[index].szMascaraDeCaptura);
    }

    public int getBtipoEntradaPermitidos(int index){
        return vstParam[index].bTiposEntradaPermitidos;
    }

    public int getBtamanhoMinimo(int index) { return vstParam[index].bTamanhoMinimo; }

    public int getBtamanhoMaximo(int index) { return vstParam[index].bTamanhoMaximo; }

    public int getUlValorMinimo(int index) { return vstParam[index].ulValorMinimo; }

    public int getUlValorMaximo(int index) { return vstParam[index].ulValorMaximo; }

    public int getBocultarDadosDigitados(int index) { return vstParam[index].bOcultarDadosDigitados; }

    public int getBvalidacaoDado(int index) { return vstParam[index].bValidacaoDado; }

    public int getBaceitaNulo(int index) { return vstParam[index].bAceitaNulo; }

    public String getSzValorInicial(int index) { return formatarMensagem(vstParam[index].szValorInicial); }

    public int getBteclasDeAtalho(int index) { return vstParam[index].bTeclasDeAtalho; }

    public String getSzMsgValidacao(int index) { return formatarMensagem(vstParam[index].szMsgValidacao); }

    public String getSzMsgConfirmacao(int index) { return formatarMensagem(vstParam[index].szMsgConfirmacao); }

    public String getSzMsgDadoMaior(int index) { return formatarMensagem(vstParam[index].szMsgDadoMaior); }

    public String getSzMsgDadoMenor(int index) { return formatarMensagem(vstParam[index].szMsgDadoMenor); }

    public int getBcapturarDataVencCartao(int index) { return vstParam[index].bCapturarDataVencCartao; }

    public int getUlTipoEntradaCartao(int index) { return vstParam[index].ulTipoEntradaCartao; }

    public int getBitemInicial(int index) { return vstParam[index].bItemInicial; }

    public int getBnumeroCapturas(int index) { return vstParam[index].bNumeroCapturas; }

    public String getSzMsgPrevia(int index) { return formatarMensagem(vstParam[index].szMsgPrevia); }

    public int getBtipoEntradaCodigoBarras(int index) { return vstParam[index].bTipoEntradaCodigoBarras; }

    public int getBomiteMsgAlerta(int index) { return vstParam[index].bOmiteMsgAlerta; }

    public int getBiniciaPelaEsquerda(int index) { return vstParam[index].bIniciaPelaEsquerda; }

    public int getBnotificarCancelamento(int index) { return vstParam[index].bNotificarCancelamento; }


    //OBTENDO UM VETOR COM A QUANTIDADE DE MENUS PREENCHIDOS
    public String [] getMenu(int indexVstParam) throws IllegalAccessException {
        int bNumOpcoesMenu = vstParam[indexVstParam].bNumOpcoesMenu;
        String [] menu = new String[bNumOpcoesMenu*2]; //dobrando o vetor para obter Texto/Valor
        int metadeVetor = menu.length/2;
        Field fields [] = vstParam[indexVstParam].stMenu.getClass().getDeclaredFields();

        for (int i = 0; i < bNumOpcoesMenu; i++) {
            //preenchendo do inicio: szTexto
            menu[i] = new String((byte[]) fields[i].get(vstParam[indexVstParam].stMenu));
            //preenchendo a partir da metade: szValor
            menu[metadeVetor + i] = new String((byte[]) fields[i + 40].get(vstParam[indexVstParam].stMenu));
        }
        return menu;
    }


    @Override
    public String toString(){
        return "Operação: " + oper + "\nvetor GetData preenchido: " + iNumParam.getValue()
                + "\nEstrutura GetData: \n{\n" + getDataToString()+ "\n}";
    }

    private String getDataToString() {
        String getdata = "";
        for (int i = 0; i < iNumParam.getValue(); i++){
            String menus = ""; int cont = 0;
            try {
                for(String menu: getMenu(i)){ menus += (cont++) + "-" + menu + " "; }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            getdata += "wIdwntificador = " + getWIdentificador(i) + "\n"
                    + "bTipoDeDaDo  = " + getBtipoDeDado(i) + "\n"
                    + "szPrompt  = " +  getSzPrompt(i) + "\n"
                    + "bNumOpcoesMenu = " + getBnumOpcoes(i) + "\n"
                    + "stMenu = {" + menus + " }\n"
                    + "szMascaraDeCaptura = " + getSzMascaraDeCaptura(i) + "\n"
                    + "bTiposEntradaPermitidos = " + getBtipoEntradaPermitidos(i) + "\n"
                    + "bTamanhoMinimo = " + getBtamanhoMinimo(i) + "\n"
                    + "bTamanhoMaximo = " + getBtamanhoMaximo(i) + "\n"
                    + "ulValorMinimo = " + getUlValorMinimo(i) + "\n"
                    + "ulValorMaximo = " + getUlValorMaximo(i) + "\n"
                    + "bOcultarDadosDigitados = " + getBocultarDadosDigitados(i) + "\n"
                    + "bValidacaoDado = " + getBvalidacaoDado(i) + "\n"
                    + "bAceitaNulo = " + getBaceitaNulo(i) + "\n"
                    + "szValorInicial = " + getSzValorInicial(i) + "\n"
                    + "bTeclasDeAtalho = " + getBteclasDeAtalho(i) + "\n"
                    + "szMsgValidacao = " + getSzMsgValidacao(i) + "\n"
                    + "szMsgConfirmacao = " + getSzMsgConfirmacao(i) + "\n"
                    + "szMsgDadoMaior = " + getSzMsgDadoMaior(i) + "\n"
                    + "szMsgDadoMenor = " + getSzMsgDadoMenor(i) + "\n"
                    + "bCapturarDataVencCartao = " + getBcapturarDataVencCartao(i) + "\n"
                    + "ulTipoEntradaCartao = " + getUlTipoEntradaCartao(i) + "\n"
                    + "bItemInicial = " + getBitemInicial(i) + "\n"
                    + "bNumeroCapturas = " + getBnumeroCapturas(i) + "\n"
                    + "szMsgPrevia = " + getSzMsgPrevia(i) + "\n"
                    + "bTipoEntradaCodigoBarras = " + getBtipoEntradaCodigoBarras(i) + "\n"
                    + "bOmiteMsgAlerta = " + getBomiteMsgAlerta(i) + "\n"
                    + "bIniciaPelaEsquerda = " + getBiniciaPelaEsquerda(i) + "\n"
                    + "bNotificarCancelamento = " + getBnotificarCancelamento(i) + "\n";
        }
        return getdata;
    }

    //Formata bytes em String
    private String formatarMensagem(byte [] param) {
        String mensagem = new String (param); // transaformando array de bytes em string
        String mensagemFormatada = mensagem.replace('\0',' '); //substituindo byte vazio por espaços

        Pattern p = Pattern.compile("\\s+"); // expressão para mais de um espaço
        Matcher m = p.matcher(mensagemFormatada);

        return m.replaceAll(" ");
    }

    //Conversores
    private PWINFO converterPWINFO(short valor){
        for ( PWINFO pwinfo : PWINFO.values()){
            if(pwinfo.getValor() == valor)
                return pwinfo;
        }
        return null;
    }

    private PWDAT converterPWDAT(byte valor){
        for ( PWDAT pwdat : PWDAT.values()){
            if(pwdat.getValor() == valor)
                return pwdat;
        }
        return null;
    }

}
