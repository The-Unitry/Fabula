package eu.theunitry.fabula.calculate;

/**
 * Created by Jeroen on 05-11-2015.
 */

public class UNCalculate{

    private int result;

    public int calculate(int g1, int g2, char operator)
    {
        switch (operator)
        {
            case '+':
                this.sum(g1, g2);
                break;
            case '-':
                this.subtract(g1, g2);
                break;
            case '*':
                this.multiply(g1, g2);
                break;
            case '/':
                this.divide(g1, g2);
                break;
        }
        return result;
    }

    private void sum(int g1, int g2)
    {

        result = g1 + g2;

    }

    public void subtract(int g1, int g2)
    {

        result = g1 - g2;

    }

    public void multiply(int g1, int g2)
    {

        result = g1 * g2;

    }

    public void divide(int g1, int g2)
    {

        result = g1 / g2;

    }

}