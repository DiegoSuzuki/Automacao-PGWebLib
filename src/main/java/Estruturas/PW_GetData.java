package Estruturas;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class PW_GetData extends Structure {

    public byte bTipoDeDado;
    public short wIdentificador;
    public String szPrompt;
    public byte bNumOpcoesMenu;
    public PW_Menu stMenu = new PW_Menu();
    public String szMascaraDeCaptura;
    public byte bTiposEntradaPermitidos;
    public byte bTamanhoMinimo;
    public byte bTamanhoMaximo;
    public int ulValorMinimo;
    public int ulValorMaximo;
    public byte bOcultarDadosDigitados;
    public byte bValidacaoDado;
    public byte bAceitaNulo;
    public String szValorInicial;
    public byte bTeclasDeAtalho;
    public String szMsgValidacao;
    public String szMsgConfirmacao;
    public String szMsgDadoMaior;
    public String szMsgDadoMenor;
    public byte bCapturarDataVencCartao ;
    public int ulTipoEntradaCartao;
    public byte bItemInicial;
    public byte bNumeroCapturas;
    public String szMsgPrevia;
    public byte bTipoEntradaCodigoBarras;
    public byte bOmiteMsgAlerta;
    public byte bIniciaPelaEsquerda;
    public byte bNotificarCancelamento;

    public PW_GetData(){
        super();
    }

    //construtores do structure
    public PW_GetData(Pointer pointer) {
        super(pointer);
    }


    public static class ByReference extends PW_GetData implements Structure.ByReference {
        public ByReference() {
        }

        public ByReference(Pointer p) {
            super(p);
            read();
        }

    }

    public static class ByValue extends PW_GetData implements Structure.ByValue {
        public ByValue() {
        }

        public ByValue(Pointer p) {
            super(p);
            read();
        }

    }
    //fim dos construtores

    @Override
    protected List<String> getFieldOrder() {
        return  Arrays.asList(
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
                "ulValorMinimo",
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
                "bTamanhoMinimo",
                "wIdentificador"

                );
    }


}

