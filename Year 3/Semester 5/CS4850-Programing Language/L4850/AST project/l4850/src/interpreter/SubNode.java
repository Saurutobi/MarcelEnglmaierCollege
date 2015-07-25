/**
 * 
 */
package interpreter;

/**
 * @author carr
 *
 */
public class SubNode extends BinaryNode
{
	public SubNode(ASTNode leftOperand, ASTNode rightOperand)
	{
		super(leftOperand, rightOperand);
	}
	
	@Override
	public void acceptDF(Visitor v)
	{
		leftOperand.acceptDF(v);
		v.visit(this);
		rightOperand.acceptDF(v);
	}

}
