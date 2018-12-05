package Estruturas;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

public class ShorT extends Structure {

    public short x=0;

    public ShorT(short x){
        this.x = x;
    }

    public static class ByReference extends ShorT implements Structure.ByReference {
        public ByReference(short x) {
            super(x);
        }
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList(
                "x");
    }
}
