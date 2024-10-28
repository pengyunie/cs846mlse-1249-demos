package ca.uwaterloo.cs846;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CallGraphMethodVisitor extends MethodVisitor {

    private CallGraph cg;
    private String currentMethodDescriptor;

    public CallGraphMethodVisitor(CallGraph cg, String currentMethodDescriptor) {
        super(Opcodes.ASM9);
        this.cg = cg;
        this.currentMethodDescriptor = currentMethodDescriptor;
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        cg.addCall(currentMethodDescriptor, owner + "#" + name + descriptor);
    }
}
