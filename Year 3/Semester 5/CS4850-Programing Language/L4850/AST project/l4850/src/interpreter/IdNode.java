/**
 * 
 */
package interpreter;

/**
 * @author carr
 *
 */
public class IdNode implements ASTNode
{
	private String name;

	public IdNode(String name)
	{
		super();
		this.name = name;
	}

	@Override
	public void acceptDF(Visitor v)
	{
		v.visit(this);
	}

	public String getName()
	{
		return name;
	}

}
