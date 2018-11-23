import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class ShortByPointer extends Structure {

    public short tamanho;

    public ShortByPointer(short valor){
        this.tamanho = valor;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("tamanho");
    }

}
