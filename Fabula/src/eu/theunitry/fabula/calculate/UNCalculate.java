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

        return this.result;
    }

    private void sum(int g1, int g2)
    {
        this.result = g1 + g2;
    }

    private void subtract(int g1, int g2)
    {
        this.result = g1 - g2;
    }

    private void multiply(int g1, int g2)
    {
        this.result = g1 * g2;
    }

    private void divide(int g1, int g2)
    {
        this.result = g1 / g2;
    }

}