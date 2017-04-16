package edu.columbia.cs.psl.phosphor.instrumenter;

import edu.columbia.cs.psl.phosphor.Configuration;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Created by jianyu on 4/16/17.
 */
public class ArrayObjectMV extends MethodVisitor {
    ArrayObjectMV(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
        if (!Configuration.IMPLICIT_TRACKING &&
                owner.equals("java/io/ObjectInputStream") &&
                (name.equals("resolveObjectWrapper") ||
                        name.equals("resolveObjectWrapper$$PHOSPHORTAGGED"))) {
            desc = "(Ljava/io/ObjectInputStream;Ljava/lang/Object;)V";
            System.out.println("instrumenting Inputstream.resolve");
            super.visitMethodInsn(Opcodes.INVOKESTATIC,
                    Type.getInternalName(StreamObjectHelper.class),
                    name, desc, itf);
            return;
        }
        super.visitMethodInsn(opcode, owner, name, desc, itf);
    }
}
