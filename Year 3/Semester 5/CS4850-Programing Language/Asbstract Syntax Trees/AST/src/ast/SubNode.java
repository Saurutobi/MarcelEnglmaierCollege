/**
 * 
 */
package ast;

/**
 * @author carr
 *
 */
public class SubNode extends BinaryNode {
	
	/**
	 * @param leftOperand
	 * @param rightOperand
	 */
	public SubNode(ASTNode leftOperand, ASTNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	/* (non-Javadoc)
	 * @see ast.ASTNode#acceptDF(ast.Visitor)
	 */
	@Override
	public void acceptDF(Visitor v) {
		leftOperand.acceptDF(v);
		v.visit(this);
		rightOperand.acceptDF(v);
	}

}
