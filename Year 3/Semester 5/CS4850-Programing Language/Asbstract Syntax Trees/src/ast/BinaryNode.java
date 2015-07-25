/**
 * 
 */
package ast;

/**
 * @author carr
 *
 */
public abstract class BinaryNode implements ASTNode {

	/**
	 * @param leftOperand
	 * @param rightOperand
	 */
	public BinaryNode(ASTNode leftOperand, ASTNode rightOperand) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	protected ASTNode leftOperand, rightOperand;

	/**
	 * @return the leftOperand
	 */
	public ASTNode getLeftOperand() {
		return leftOperand;
	}

	/**
	 * @return the rightOperand
	 */
	public ASTNode getRightOperand() {
		return rightOperand;
	}
	
}
