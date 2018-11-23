package Estruturas;

import com.sun.jna.Structure;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import java.util.Arrays;
import java.util.List;

public class PW_GetData extends Structure {

    public short wIdentificador;
    public byte bTipoDeDado;
    public String szPrompt;
    public ByteByReference bNumOpcoesMenu;
    public PW_Menu stMenu;
    public StringByReference  szMascaraDeCaptura;
    public ByteByReference bTiposEntradaPermitidos;
    public ByteByReference bTamanhoMinimo;
    public ByteByReference bTamanhoMaximo;
    public Integer ulValorMinimo;
    public IntByReference ulValorMaximo;
    public ByteByReference bOcultarDadosDigitados;
    public ByteByReference bValidacaoDado;
    public ByteByReference bAceitaNulo;
    public StringByReference szValorInicial;
    public ByteByReference bTeclasDeAtalho;
    public StringByReference szMsgValidacao;
    public StringByReference szMsgConfirmacao;
    public StringByReference szMsgDadoMaior;
    public StringByReference szMsgDadoMenor;
    public ByteByReference bCapturarDataVencCartao ;
    public IntByReference ulTipoEntradaCartao;
    public ByteByReference bItemInicial;
    public ByteByReference bNumeroCapturas;
    public ByteByReference szMsgPrevia;
    public ByteByReference bTipoEntradaCodigoBarras;
    public ByteByReference bOmiteMsgAlerta;
    public ByteByReference bIniciaPelaEsquerda;
    public ByteByReference bNotificarCancelamento;

    public PW_GetData(){ }

    public static class ByReference extends PW_GetData implements Structure.ByReference { }

    public static class ByValue extends PW_GetData implements Structure.ByValue { }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList(
         "bAceitaNulo",
         "bCapturarDataVencCartao",
         "bIniciaPelaEsquerda",
         "bItemInicial",
         "bNotificarCancelamento",
         "bNumOpcoesMenu",
         "bNumeroCapturas",
         "bOcultarDadosDigitados",
         "bOmiteMsgAlerta",
         "bTamanhoMaximo",
         "bTamanhoMinimo",
         "bTeclasDeAtalho",
         "bTipoDeDado",
         "bTipoEntradaCodigoBarras",
         "bTiposEntradaPermitidos",
         "bValidacaoDado",
         "stMenu",
         "szMascaraDeCaptura",
         "szMsgConfirmacao",
         "szMsgDadoMaior",
         "szMsgDadoMenor",
         "szMsgPrevia",
         "szMsgValidacao",
         "szPrompt",
         "szValorInicial",
         "ulTipoEntradaCartao",
         "ulValorMaximo",
         "ulValorMinimo",
        "wIdentificador");
    }


}

