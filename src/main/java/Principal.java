import Enums.PWINFO;
import Enums.PWOPER;
import Estruturas.PW_GetData;
import com.sun.jna.Pointer;


public class Principal {

    public static void main(String[] args) {

        String pw_iInit = ChamarFuncoes.chamarPW_iInit(".");
        System.out.println("PW_iInit: "+ pw_iInit);

        String pw_iNewTransac = ChamarFuncoes.chamarPW_iNewTransac(PWOPER.ADMIN);
        System.out.println("PW_iNewTransac: " + pw_iNewTransac);

        ChamarFuncoes.addMandatoryParams();

        String pwA_iAddParam5 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.POSID,"51276");
        System.out.println("PW_iAddParam PDC: " + pwA_iAddParam5);

        String pwA_iAddParam6 = ChamarFuncoes.chamarPW_iAddParam(PWINFO.DESTTCPIP,"app.tpgw.ntk.com.br:17505");
        System.out.println("PW_iAddParam IP/PORTA: " + pwA_iAddParam6);

        PW_GetData vstParam [] = new PW_GetData[10];

        for (int i = 0; i < vstParam.length; i++)
            vstParam [i] = new PW_GetData();

        Integer numParam = vstParam.length;
        Pointer iNumParam = new Pointer(numParam);

        String pw_iExectransac = ChamarFuncoes.chamarPW_iExecTransac(vstParam, iNumParam);
        System.out.println("PW_iExecTransac: " + pw_iExectransac);

    }

}