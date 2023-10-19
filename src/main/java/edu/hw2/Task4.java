package edu.hw2;

public class Task4 {
    private Task4() {}

    public record CallingInfo(String className, String methodName) {
        public static CallingInfo callingInfo() {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            return new CallingInfo(stackTrace[1].getClassName(), stackTrace[1].getMethodName());
        }
    }
}
