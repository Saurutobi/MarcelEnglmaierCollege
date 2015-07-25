/**
 * 
 */
package interpreter;

/**
 * @author carr
 *
 */
public abstract class BinaryNode implements ASTNode
{
	public BinaryNode(ASTNode leftOperand, ASTNode rightOperand)
	{
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	protected ASTNode leftOperand, rightOperand;

	public ASTNode getLeftOperand()
	{
		return leftOperand;
	}

	public ASTNode getRightOperand()
	{
		return rightOperand;
	}
	
}
