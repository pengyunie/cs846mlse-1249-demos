package ca.uwaterloo.cs846;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class DAMethodVisitor extends MethodVisitor {
    private String currentMethodDescriptor;

    public DAMethodVisitor(MethodVisitor mv, String currentMethodDescriptor) {
        super(Opcodes.ASM9, mv);
        this.currentMethodDescriptor = currentMethodDescriptor;
    }

    @Override
    public void visitCode() {
        // add logCallee
        super.visitLdcInsn(this.currentMethodDescriptor);
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "ca/uwaterloo/cs846/DynamicAnalyzer", "logCallee",
                "(Ljava/lang/String;)V", false);

        // don't forget super.visit
        super.visitCode();
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        // add logCaller
        super.visitLdcInsn(this.currentMethodDescriptor);
        super.visitMethodInsn(Opcodes.INVOKESTATIC, "ca/uwaterloo/cs846/DynamicAnalyzer", "logCaller",
                "(Ljava/lang/String;)V", false);

        // don't forget super.visit
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }
}
