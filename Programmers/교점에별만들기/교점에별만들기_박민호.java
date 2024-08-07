import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Solution {
    public String[] solution(int[][] line) {
        int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;
        int size = line.length;
        Set<XY> set = new HashSet<>();
        for(int i=0;i<size-1;i++){
            long x1 = Integer.valueOf(line[i][0]).longValue();
            long y1 = Integer.valueOf(line[i][1]).longValue();
            long c1 = Integer.valueOf(line[i][2]).longValue();
            for(int j=i+1;j<size;j++){
            	long x2 = Integer.valueOf(line[j][0]).longValue();
                long y2 = Integer.valueOf(line[j][1]).longValue();
                long c2 = Integer.valueOf(line[j][2]).longValue();
                long tempY = y2*x1-y1*x2;
                long tempC = c1*x2-c2*x1;
                if(tempY!=0) {
                    long y = tempC/tempY;
                    if(tempY!=0 && y==(Long.valueOf(tempC).floatValue() / Long.valueOf(tempY).floatValue())) {
                    	long x = Integer.MAX_VALUE;
                    	float fx = Float.MIN_VALUE;
                    	if(x1==0) {
                    		if(x2!=0) {
                    			x = -(y*y2+c2)/x2;
                        		fx = -(Long.valueOf(y*y2+c2).floatValue() / Long.valueOf(x2).floatValue());
                    		}
                    	}
                    	else {
                    		x = -(y*y1+c1)/x1;
                    		fx = -(Long.valueOf(y*y1+c1).floatValue() / Long.valueOf(x1).floatValue());
                    	}
                    	if(x==fx) {
                    		int intX = Long.valueOf(x).intValue();
                    		int intY = Long.valueOf(y).intValue();
                        	set.add(new XY(intX,intY));
                        	if(intX>maxX) maxX = intX;
                        	if(intX<minX) minX = intX;
                        	if(intY>maxY) maxY = intY;
                        	if(intY<minY) minY = intY;
                        }
                    }
                }
            }
        }
        size = maxY-minY+1;
        char[][] chars = new char[size][maxX-minX+1];
        String[] answer = new String[size];
        for(int i=0;i<size;i++) {
        	for(int j=0;j<=maxX-minX;j++) {
        		chars[i][j]='.';
        	}
        }
        for(XY xy : set){
            chars[xy.y-minY][xy.x-minX] = '*';
        }
        for(int i=0;i<size;i++) {
        	answer[i] = String.valueOf(chars[size-1-i]);
        }
        return answer;
    }
}
class XY{
	int x;
	int y;
	XY(int x, int y){
		this.x = x; this.y = y;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof XY) {
			return ((XY) o).x==this.x && ((XY)o).y==this.y;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return Objects.hash(x,y);
	}
}