package Estruturas;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class PW_Menu extends Structure {

    public String szTexto1;
    public String szTexto2;
    public String szTexto3;
    public String szTexto4;
    public String szTexto5;
    public String szTexto6;
    public String szTexto7;
    public String szTexto8;
    public String szTexto9;
    public String szTexto10;
    public String szTexto11;
    public String szTexto12;
    public String szTexto13;
    public String szTexto14;
    public String szTexto15;
    public String szTexto16;
    public String szTexto17;
    public String szTexto18;
    public String szTexto19;
    public String szTexto20;
    public String szTexto21;
    public String szTexto22;
    public String szTexto23;
    public String szTexto24;
    public String szTexto25;
    public String szTexto26;
    public String szTexto27;
    public String szTexto28;
    public String szTexto29;
    public String szTexto30;
    public String szTexto31;
    public String szTexto32;
    public String szTexto33;
    public String szTexto34;
    public String szTexto35;
    public String szTexto36;
    public String szTexto37;
    public String szTexto38;
    public String szTexto39;
    public String szTexto40;
    public String szValor1;
    public String szValor2;
    public String szValor3;
    public String szValor4;
    public String szValor5;
    public String szValor6;
    public String szValor7;
    public String szValor8;
    public String szValor9;
    public String szValor10;
    public String szValor11;
    public String szValor12;
    public String szValor13;
    public String szValor14;
    public String szValor15;
    public String szValor16;
    public String szValor17;
    public String szValor18;
    public String szValor19;
    public String szValor20;
    public String szValor21;
    public String szValor22;
    public String szValor23;
    public String szValor24;
    public String szValor25;
    public String szValor26;
    public String szValor27;
    public String szValor28;
    public String szValor29;
    public String szValor30;
    public String szValor31;
    public String szValor32;
    public String szValor33;
    public String szValor34;
    public String szValor35;
    public String szValor36;
    public String szValor37;
    public String szValor38;
    public String szValor39;
    public String szValor40;

    public PW_Menu(){}

    //construtores do structure
    public PW_Menu(Pointer pointer) {
        super(pointer);
    }

    public static class ByReference extends PW_Menu implements Structure.ByReference {
        public ByReference() {
        }

        public ByReference(Pointer p) {
            super(p);
            read();
        }

    }

    public static class ByValue extends PW_Menu implements Structure.ByValue {
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
        return Arrays.asList("szTexto1", "szTexto2", "szTexto3", "szTexto4", "szTexto5", "szTexto6", "szTexto7", "szTexto8", "szTexto9", "szTexto10",
                "szTexto11", "szTexto12", "szTexto13", "szTexto14", "szTexto15", "szTexto16", "szTexto17", "szTexto18", "szTexto19", "szTexto20",
                "szTexto21", "szTexto22", "szTexto23", "szTexto24", "szTexto25", "szTexto26", "szTexto27", "szTexto28", "szTexto29", "szTexto30",
                "szTexto31", "szTexto32", "szTexto33", "szTexto34", "szTexto35", "szTexto36", "szTexto37", "szTexto38", "szTexto39", "szTexto40",
                "szValor1", "szValor2", "szValor3", "szValor4", "szValor5", "szValor6", "szValor7", "szValor8", "szValor9", "szValor10",
                "szValor11", "szValor12", "szValor13", "szValor14", "szValor15", "szValor16", "szValor17", "szValor18", "szValor19", "szValor20",
                "szValor21", "szValor22", "szValor23", "szValor24", "szValor25", "szValor26", "szValor27", "szValor28", "szValor29", "szValor30",
                "szValor31", "szValor32", "szValor33", "szValor34", "szValor35", "szValor36", "szValor37", "szValor38", "szValor39", "szValor40");
    }


}
