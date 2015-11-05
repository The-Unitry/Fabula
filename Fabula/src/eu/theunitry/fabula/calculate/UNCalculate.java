package eu.theunitry.fabula.calculate;

/**
 * Created by Jeroen on 5-11-2015.
 */

public class UNCalculate{

    private int g1, g2, result;

    public int sum(){

        result = g1+g2;
        return result;

    }

    public int substract(){

        result = g1 - g2;
        return result;

    }

    public int multiply(){

        result = g1*g2;
        return result;

    }

    public int divide(){

        result = g1/g2;
        return result;

    }

}

