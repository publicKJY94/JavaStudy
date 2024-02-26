package day004;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class BinarySearch {

	public static void main(String[] args) {

//		int[] ar = IntStream.range(1, 100).toArray();
//		System.out.println(Arrays.toString(ar));
//		System.out.println(Arrays.binarySearch(ar, -1));
		Random rand = new Random();
		int[] ar = new int[50];
		int startIndex = 0; // 검색하려는 범위의 첫 인덱스 번호
		int endIndex = ar.length; // 검색하려는 범위의 마지막 인덱스 번호
		int searchKey = 30; // 찾으려는 값
		// 1~100까지의 수 중에서 랜덤으로 50개 넣기
		int i = 0;
		while (i < ar.length) {
			ar[i] = rand.nextInt(100) + 1;
			i++;
		}
		Arrays.sort(ar);
		System.out.println(Arrays.toString(ar));

		while (startIndex <= endIndex) {
			int midIndex = (startIndex + endIndex) / 2; // 범위의 중간 인덱스
			if (ar[midIndex] == searchKey) { // 찾으려는 값이 중간값인 경우
				System.out.printf("해당 값은 %d번 인덱스에 있습니다", midIndex);
				break;
			} else if (ar[midIndex] < searchKey) {
				startIndex = midIndex;
				// 현재 중간인덱스의 결과가 찾으려는 값보다 작으면,
				// 중간인덱스를 시작점으로 변경한다
			} else {
				endIndex = midIndex;
				// 현재 중간인덱스의 결과가 찾으려는 값보다 크면,
				// 중간인덱스를 종료점으로 변경한다
			}
			System.out.println(ar[midIndex]); // 중간값 출력
		}
		
//		int[] datas = {1, 2, 4, 7, 8, 10, 11, 12, 18, 20};
//		int findNum = 18;
//		
//		int start = 0;
//		int end = datas.length - 1;
//		int findIdx, mid;
//		
//		while (true) {
//			mid = (start+end)/2;
//			System.out.println(mid);
//			if (datas[mid] == findNum) {
//				findIdx = mid;
//				break ;
//			} else if (datas[mid] < findNum) {
//				start = mid + 1;
//			} else {
//				end = mid - 1;
//			}

	}

	private static int binarySearch0(int[] a, int fromIndex, int toIndex, int key) {
		int low = fromIndex; // 검색시작 인덱스
		int high = toIndex - 1; // 검색하려는 인덱스

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = a[mid];

			if (midVal < key)
				low = mid + 1;
			else if (midVal > key)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found.
	}
}
//	
//	public static int binarySearch(Object[] a, Object key) {
//		return binarySearch0(a, 0, a.length, key);
//	}
/**
 * Searches a range of the specified array of ints for the specified value using
 * the binary search algorithm.
 * 
 * 범위가 지정된 정수 배열의에서 지정된 값을 찾기 위해 사용하는 이진탐색 알고리즘
 * 
 * The range must be sorted
 * 
 * 범위는 무조건 정렬되어 있어야 한다
 * 
 * prior to making this call. If it is not sorted, the results are undefined.
 * 
 * 만약 정렬하기 전에 사용했을 때, 결과값을 못 찾는다
 * 
 * If the range contains multiple elements with the specified value, there is no
 * guarantee which one will be found.
 * 
 * 만약 범위내에 지정된 값이 다수 포함되어 있는경우, 어느것이 발견될지 보장할 수 없습니다.
 *
 * @param a         the array to be searched
 * 
 *                  찾을 값이 있는 배열
 * 
 * @param fromIndex the index of the first element (inclusive) to be searched
 * 
 *                  배열 내 찾으려는 범위의 첫번째 요소(시작점) -> 검색 대상에 포함된다
 * 
 * @param toIndex   the index of the last element (exclusive) to be searched
 * 
 *                  배열 내 찾으려는 범위의 마지막 요소(끝점) -> 검색 대상에 포함되지 않는다
 * 
 * @param key       the value to be searched for
 * 
 *                  찾으려는 값
 * 
 * @return index of the search key, if it is contained in the array within the
 *         specified range;
 * 
 * 
 * 
 *         otherwise, <code>(-(<i>insertion point</i>) - 1)</code>. The
 *         <i>insertion point</i> is defined as the point at which the key would
 *         be inserted into the array: the index of the first element in the
 *         range greater than the key, or {@code toIndex} if all elements in the
 *         range are less than the specified key. Note that this guarantees that
 *         the return value will be &gt;= 0 if and only if the key is found.
 * @throws IllegalArgumentException       if {@code fromIndex > toIndex}
 * @throws ArrayIndexOutOfBoundsException if
 *                                        {@code fromIndex < 0 or toIndex > a.length}
 * @since 1.6
 */