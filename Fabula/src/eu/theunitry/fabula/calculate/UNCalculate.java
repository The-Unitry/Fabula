package eu.theunitry.fabula.calculate;

public class UNCalculate
{
    private int result;

    public static int calculate(int g1, int g2, char operator)
    {
        switch (operator)
        {
            case '+':
                return g1 + g2;
            case '-':
                return g1 - g2;
            case '*':
                return g1 * g2;
            case '/':
                return g1 / g2;
            default:
                return 0;
        }
    }
}
