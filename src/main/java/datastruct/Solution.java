package datastruct;



public class Solution {
	public String longestPalindrome(String s) {

		int c = 52;
		String[] container = new String[c+1];
		container[0] = "DUMMY";
		for(int i=0; i<52; i++){
			char x = (char)('a' +  i);
			try{
				if(s.indexOf(x) != s.lastIndexOf(x)){
					String temp = s.substring(s.indexOf(x), s.lastIndexOf(x));
					container[temp.length()] = temp;
				}
			}catch (Exception e){

			}
		}

 		for(int i = 1; i< c+1; i++){
 			if(container[i] != null)
 				return container[i];
		} 
	 	return "";
	}
}