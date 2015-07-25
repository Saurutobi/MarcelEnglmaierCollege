/**
 * 
 */
package interpreter;

/**
 * @author Marcel Englmaier
 *
 */
public class DivideNode extends BinaryNode
{
	public DivideNode(ASTNode leftOperand, ASTNode rightOperand)
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
