package edu.columbia.cs.psl.phosphor.instrumenter;

import edu.columbia.cs.psl.phosphor.runtime.ProfileCount;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Created by jianyu on 5/18/17.
 */
public class ProfileMV extends MethodVisitor {
    public ProfileMV(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (!owner.startsWith("java") && !owner.startsWith("edu/columbia/cs/psl/phosphor/runtime/ProfileCount")
                && !name.equals("<init>")
                && !owner.startsWith("edu/columbia/cs/psl/phosphor")
                && !owner.startsWith("sun/")) {
            super.visitLdcInsn(owner + ":" + name);
            super.visitMethodInsn(Opcodes.INVOKESTATIC,
                    Type.getInternalName(ProfileCount.class),
                    "invoke_insn",
                    "(Ljava/lang/String;)V", false);
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}
