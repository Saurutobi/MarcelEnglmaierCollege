/**
 * 
 */
package ast;

import util.CalcException;

/**
 * @author carr
 *
 */
public class AddNode extends BinaryNode {


	public AddNode(ASTNode leftOperand, ASTNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public void acceptDF(Visitor v) throws CalcException {
		leftOperand.acceptDF(v);
		v.visit(this);
		rightOperand.acceptDF(v);
	}

	@Override
	public Object accept(Visitor v) throws CalcException {
		return v.visit(this);
	}

}
