package CodeU_Assignment6;

class NullRearrangementsException extends RearrangementExceptions{

    /**
     * serialVersionUID = 6995452535841683530L
     */
    private static final long serialVersionUID = 6995452535841683530L;
    public NullRearrangementsException() {}
    public NullRearrangementsException(String message)
    {
       super(message);
    }
}

class LengthMismatchException extends RearrangementExceptions{

    /**
     * serialVersionUID = -6715959889557026392L
     */
    private static final long serialVersionUID = -6715959889557026392L;
    public LengthMismatchException() {}
    public LengthMismatchException(String message)
    {
       super(message);
    }
}

class InvalidRearrangementException extends RearrangementExceptions{
    /**
     * serialVersionUID = 332283596105783306L
     */
    private static final long serialVersionUID = 332283596105783306L;
    public InvalidRearrangementException() {}
    public InvalidRearrangementException(String message)
    {
       super(message);
    }
}

public class RearrangementExceptions extends Exception{
    /**
     * serialVersionUID = -1991874018749639875L
     */
    private static final long serialVersionUID = -1991874018749639875L;
    public RearrangementExceptions() {}
    public RearrangementExceptions(String message)
    {
       super(message);
    }
}

