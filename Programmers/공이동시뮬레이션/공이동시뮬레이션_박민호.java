import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        int size = queries.length;
        int currentX = x; int currentY = y;
        Set<BallXY> set = new HashSet<>();
        set.add(new BallXY(x,y));
        for(int i=size-1;i>=0;i--) {
        	Object[] list = set.toArray();
        	set.clear();
        	for(int t=0;t<list.length;t++) {
        		currentX = ((BallXY)list[t]).x; currentY = ((BallXY)list[t]).y;
        		switch(queries[i][0]) {
    			case 0:
    				// y 감소
    				if(y==0) {
    					for(int j=0;j<=queries[i][1];j++) {
    						if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
    							break;
    						}
    						set.add(new BallXY(currentX, currentY++));
    					}
    				}
    				else {
    					currentY+=queries[i][1];
    					if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
							break;
						}
    					set.add(new BallXY(currentX, currentY));
    				}
    				break;
    			case 1:
    				// y 증가
    				if(y==m-1) {
    					for(int j=0;j<=queries[i][1];j++) {
    						if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
    							break;
    						}
    						set.add(new BallXY(currentX, currentY--));
    					}
    				}
    				else {
    					currentY -= queries[i][1];
    					if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
							break;
						}
    					set.add(new BallXY(currentX, currentY));
    				}
    				break;
    			case 2:
    				// x 감소
    				if(x==0) {
    					for(int j=0;j<=queries[i][1];j++) {
    						if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
    							break;
    						}
    						set.add(new BallXY(currentX++, currentY));
    					}
    				}
    				else {
    					currentX += queries[i][1];
    					if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
							break;
						}
    					set.add(new BallXY(currentX, currentY));
    				}
    				break;
    			case 3:
    				// x 증가
    				if(x==n-1) {
    					for(int j=0;j<=queries[i][1];j++) {
    						if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
    							break;
    						}
    						set.add(new BallXY(currentX--, currentY));
    					}
    				}
    				else {
    					currentX -= queries[i][1];
    					if(currentY<0 || currentY>=m || currentX<0 || currentX>=n) {
							break;
						}
    					set.add(new BallXY(currentX, currentY));
    				}
    				break;
        		}
        	}
        }
        return set.size();
    }
}
class BallXY{
	int x;
	int y;
	public BallXY(int x, int y){
		this.x = x; this.y = y;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BallXY) {
			return x==((BallXY)obj).x && y==((BallXY)obj).y;
		}
		return false;
	}
	@Override
	public int hashCode() {
		return Objects.hash(x,y);
	}
}