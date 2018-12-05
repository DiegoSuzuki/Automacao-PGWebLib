package Estruturas;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class PW_GetData extends Structure {

    public short wIdentificador;
    public byte bTipoDeDado;
    public byte [] szPrompt = new byte [84];
    public byte bNumOpcoesMenu;
    public PW_Menu stMenu;
    public byte [] szMascaraDeCaptura = new byte [41];
    public byte bTiposEntradaPermitidos;
    public byte bTamanhoMinimo;
    public byte bTamanhoMaximo;
    public int ulValorMinimo;
    public int ulValorMaximo;
    public byte bOcultarDadosDigitados;
    public byte bValidacaoDado;
    public byte bAceitaNulo;
    public byte [] szValorInicial = new byte [41];
    public byte bTeclasDeAtalho;
    public byte [] szMsgValidacao = new byte [84];
    public char [] szMsgConfirmacao = new char [84];
    public char [] szMsgDadoMaior = new char [84];
    public char [] szMsgDadoMenor = new char [84];
    public byte bCapturarDataVencCartao ;
    public int ulTipoEntradaCartao;
    public byte bItemInicial;
    public byte bNumeroCapturas;
    public char [] szMsgPrevia = new char [84];
    public byte bTipoEntradaCodigoBarras;
    public byte bOmiteMsgAlerta;
    public byte bIniciaPelaEsquerda;
    public byte bNotificarCancelamento;

    public static class ByReference extends PW_GetData implements Structure.ByReference { }

    public static class ByValue extends PW_GetData implements Structure.ByValue { }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(
                "wIdentificador",
                "bTipoDeDado",
                "szPrompt",
                "bNumOpcoesMenu",
                "stMenu",
                "szMascaraDeCaptura",
                "bTiposEntradaPermitidos",
                "bTamanhoMinimo",
                "bTamanhoMaximo",
                "ulValorMinimo",
                "ulValorMaximo",
                "bOcultarDadosDigitados",
                "bValidacaoDado",
                "bAceitaNulo",
                "szValorInicial",
                "bTeclasDeAtalho",
                "szMsgValidacao",
                "szMsgConfirmacao",
                "szMsgDadoMaior",
                "szMsgDadoMenor",
                "bCapturarDataVencCartao",
                "ulTipoEntradaCartao",
                "bItemInicial",
                "bNumeroCapturas",
                "szMsgPrevia",
                "bTipoEntradaCodigoBarras",
                "bOmiteMsgAlerta",
                "bIniciaPelaEsquerda",
                "bNotificarCancelamento"
         );
    }


}

