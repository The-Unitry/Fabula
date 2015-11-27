package eu.theunitry.fabula.UNGameEngine.calculate;

/**
 * UNCalculate is used for basic operations. Later we will add support
 * for more advanced calculations.
 */
public class UNCalculate
{
    /**
     * Calculate
     * @param g1
     * @param g2
     * @param operator
     * @return
     */
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
