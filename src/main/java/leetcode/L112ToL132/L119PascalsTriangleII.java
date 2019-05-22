package leetcode.L112ToL132;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guizhai
 *
 */
public class L119PascalsTriangleII {

	/**
	 * @param args
	 */
	
	public List<Integer> getRow(int rowIndex) {
		ArrayList<Integer> row = new ArrayList<Integer>();
		for(int i=0;i<rowIndex;i++)
		{
			row.add(0, 1);
			for(int j=1;j<row.size()-1;j++)
				row.set(j, row.get(j)+row.get(j+1));
		}
		return row;
  }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
