package edu.hw11.task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public class FibonacciClassGenerator {
    private static final String CLASS_NAME = "FibonacciCalculator";
    private static final String METHOD_NAME = "fib";

    private FibonacciClassGenerator() {}

    public static Class<?> createFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(METHOD_NAME, long.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(
                new FibonacciMethodGenerator()
            ))
            .make()
            .load(FibonacciClassGenerator.class.getClassLoader())
            .getLoaded();
    }

    public static class FibonacciMethodGenerator implements ByteCodeAppender {
        private static final int STACK_SIZE = 5;
        private static final String FUNCTION_SIGNATURE = "(I)J";

        @Override
        public @NotNull Size apply(
            MethodVisitor methodVisitor,
            @NotNull Implementation.Context context,
            @NotNull MethodDescription instrumentedMethod
        ) {
            Label argumentIsBiggerThanTwo = new Label();

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, argumentIsBiggerThanTwo);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            methodVisitor.visitLabel(argumentIsBiggerThanTwo);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ISUB);

            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                CLASS_NAME,
                METHOD_NAME,
                FUNCTION_SIGNATURE,
                false
            );

            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.ISUB);

            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                CLASS_NAME,
                METHOD_NAME,
                FUNCTION_SIGNATURE,
                false
            );
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            return new ByteCodeAppender.Size(STACK_SIZE, 0);
        }
    }
}
