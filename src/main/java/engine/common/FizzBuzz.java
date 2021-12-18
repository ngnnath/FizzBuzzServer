package engine.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FizzBuzz {


    public static List<String> fizzBuzz(int int1, int int2, int limit, String str1, String str2) {
        // verify limit

        if (limit < 1 && limit > Integer.MAX_VALUE) {
            //LOG Erreur de  limit entré
            return Collections.emptyList();
        }
        // Evite de tomber dans le cas ArithmeticException
        if (int1 < 1 || int2 < 1) {
            //LOG Erreur valeur en dessous de 1
            return Collections.emptyList();
        }
        // int 1 et int 2 doivent être superieur à la valeur limite.
        if (int1 > limit || int2 > limit) {
            //LOG erreur de multiple
            return Collections.emptyList();
        }

        List<String> listResult = new ArrayList<>();
        for (int i = 1; i <= limit; i++) {
            if (i % int1 == 0 && i % int2 == 0) {
                listResult.add(str1 + str2);
            } else if (i % int1 == 0) {
                listResult.add(str1);
            } else if (i % int2 == 0) {
                listResult.add(str2);
            } else {
                listResult.add(String.valueOf(i));
            }
        }
        return listResult;
    }

}
