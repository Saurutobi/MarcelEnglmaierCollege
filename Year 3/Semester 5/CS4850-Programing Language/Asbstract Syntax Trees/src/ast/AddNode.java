/**
 * 
 */
package ast;

/**
 * @author carr
 *
 */
public class AddNode extends BinaryNode {


	public AddNode(ASTNode leftOperand, ASTNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public void acceptDF(Visitor v) {
		leftOperand.acceptDF(v);
		v.visit(this);
		rightOperand.acceptDF(v);
	}

}
