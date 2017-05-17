package edu.columbia.cs.psl.phosphor.instrumenter;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import edu.columbia.cs.psl.phosphor.Configuration;
import edu.columbia.cs.psl.phosphor.TaintUtils;
import edu.columbia.cs.psl.phosphor.runtime.HardcodedBypassStore;

public class TaintTagFieldCastMV extends MethodVisitor implements Opcodes {

	public TaintTagFieldCastMV(MethodVisitor mv) {
		super(Opcodes.ASM5, mv);
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {

//		if ((opcode == Opcodes.GETFIELD || opcode == Opcodes.GETFIELD) && name.equals("lineNumberPHOSPHOR_TAG") && owner.startsWith("java/lang/StackTrace")){
//			super.visitFieldInsn(opcode, owner, name, "Ljava/lang/Object;");
//			super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(HardcodedBypassStore.class), "cast", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
//			super.visitTypeInsn(CHECKCAST, Type.getType(desc).getInternalName());
//		} else

		if ((opcode == Opcodes.GETFIELD || opcode == Opcodes.GETSTATIC) && !TaintAdapter.canRawTaintAccess(owner) && name.endsWith(TaintUtils.TAINT_FIELD) && (desc.equals(Configuration.TAINT_TAG_DESC) || desc.startsWith("Ledu/columbia/cs/psl/phosphor/struct/Lazy"))) {
			String castTo = Configuration.TAINT_TAG_INTERNAL_NAME;
			if (!desc.equals(Configuration.TAINT_TAG_DESC))
				castTo = TaintUtils.getShadowTaintType(desc);

			super.visitFieldInsn(opcode, owner, name, "I");
			super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(HardcodedBypassStore.class), "get", "(I)Ljava/lang/Object;", false);
//				super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(HardcodedBypassStore.class), "cast", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
			super.visitTypeInsn(CHECKCAST, Type.getType(desc).getInternalName());
//			} else {
//				super.visitFieldInsn(opcode, owner, name, "[I");
//				super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(HardcodedBypassStore.class), "get", "([I)[Ljava/lang/Object;", false);
//				super.visitTypeInsn(CHECKCAST, Configuration.TAINT_TAG_ARRAY_INTERNAL_NAME);
//			}
		} else if ((opcode == Opcodes.PUTFIELD || opcode == Opcodes.PUTSTATIC) && !TaintAdapter.canRawTaintAccess(owner) && name.endsWith(TaintUtils.TAINT_FIELD)
				&& (desc.equals(Configuration.TAINT_TAG_DESC) || desc.startsWith("Ledu/columbia/cs/psl/phosphor/struct/Lazy"))) {
//			if (desc.equals(Configuration.TAINT_TAG_DESC)) {

			if (opcode == Opcodes.PUTFIELD) {
				super.visitInsn(Opcodes.SWAP);
				super.visitInsn(Opcodes.DUP_X1);
				super.visitFieldInsn(Opcodes.GETFIELD, owner, name, "I");
			}
			else {
				super.visitFieldInsn(Opcodes.GETSTATIC, owner, name, "I");
			}
			super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(HardcodedBypassStore.class), "add", "(Ljava/lang/Object;I)I", false);
			super.visitFieldInsn(opcode, owner, name, "I");
//			} else {
//				super.visitMethodInsn(Opcodes.INVOKESTATIC, Type.getInternalName(HardcodedBypassStore.class), "add", "([Ljava/lang/Object;)[I", false);
//				super.visitFieldInsn(opcode, owner, name, "[I");
//			}
		} else
			super.visitFieldInsn(opcode, owner, name, desc);
	}
}