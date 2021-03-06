package leetcode.L0ToL35;

/**
 * @author zhailzh
 * 
 * @Date 20151224:56:04
 * 
 Reverse digits of an integer.
  Example1: x = 123, return 321 
  Example2: x= -123, return -321
  Example3: x=120 ,return 21
  
input:-2147483648
Output:
-126087180
Expected:
0

-2147483648 的绝对值是 2147483648，
也就是说Integer.MIN_VALUE的绝对值是一个负数
2147483647 的绝对值是 -2147483647
 *       
 */
public class L07ReverseInteger {

/**
 * 基础知识，Integer.MIN_VALUE
 * */
	public int reverse_NOUSE_Long(int x) {
    int prevRev = 0 , rev= 0;
    while( x != 0){
        rev= rev*10 + x % 10;
        if((rev - x % 10) / 10 != prevRev){
            return 0;
        }
        prevRev = rev;
        x= x/10;
    }
    return rev;
}
	
public static 	int reverse(int x) {
		int y = x>0?x:-x;
		if(Integer.MIN_VALUE == y){
			return 0;
		}
		int value = 0;
		while (y != 0) {
			int tmp = y%10;
			value = value * 10 + tmp;
			if(value > Integer.MAX_VALUE){
				return 0;
			}
			y /= 10;
		}
		return x>0?(int) value:-(int) value;
	}

	public static void main(String[] args) {
		System.out.println(" abs: "+ Math.abs(Integer.MIN_VALUE) + " "+ Math.abs(Integer.MAX_VALUE) );
		System.out.println(L07ReverseInteger.reverse(-2147483648));
		System.out.println(L07ReverseInteger.reverse(1534236469));

		

	}

}
