/**
 * 
 */
package interpreter;

/**
 * @author Marcel Englmaier
 *
 */
public class MultiplyNode extends BinaryNode
{
	public MultiplyNode(ASTNode leftOperand, ASTNode rightOperand)
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
