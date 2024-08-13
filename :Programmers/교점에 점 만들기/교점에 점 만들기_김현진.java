import java.util.*;

//좌표 클래스
class Coordinate {
	long x;
	long y;

	Coordinate(long x, long y) {
		this.x = x;
		this.y = y;
	}

	void setX(long x) {
		this.x = x;
	}

	void setY(long y) {
		this.y = y;
	}
}

class Solution {
	public String[] solution(int[][] line) {
		// 교점을 저장할 좌표 리스트
		List<Coordinate> cList = new ArrayList<>();
		// 교점 저장하기
		for (int i = 0; i < line.length - 1; i++) {
			for (int j = i + 1; j < line.length; j++) {

				long a = line[i][0], b = line[i][1], e = line[i][2],
						c = line[j][0], d = line[j][1], f = line[j][2];

				if ((a * d - b * c) != 0) {
					double x = (double) (b * f - e * d) / (a * d - b * c);
					double y = (double) (e * c - a * f) / (a * d - b * c);
					if (x % 1 == 0 && y % 1 == 0) { // 정수인 경우에만 저장
						cList.add(new Coordinate((long) x, (long) y));
					}
				}
			}
		}
		// 교점의 최소, 최대값 찾기
		Coordinate min = new Coordinate(Long.MAX_VALUE, Long.MAX_VALUE);
		Coordinate max = new Coordinate(Long.MIN_VALUE, Long.MIN_VALUE);

		for (Coordinate c : cList) {
			long cx = c.x, cy = c.y;

			if (cx < min.x) {
				min.setX(cx);
			}

			if (cy < min.y) {
				min.setY(cy);
			}

			if (cx > max.x) {
				max.setX(cx);
			}

			if (cy > max.y) {
				max.setY(cy);
			}
		}

		// 격자판 크기 산출 후 격자판 배열 선언
		int width = (int) (max.x - min.x + 1);
		int height = (int) (max.y - min.y + 1);

		char[][] arr = new char[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				arr[i][j] = '.';
			}
		}
		// 교점 찍기
		for (Coordinate c : cList) {
			arr[(int) (max.y - c.y)][(int) (c.x - min.x)] = '*';
		}
		// 답변배열 생성 후 격자판 문자열로 변환하여 저장
		String[] answer = new String[height];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = new String(arr[i]);
		}

		return answer;
	}
}