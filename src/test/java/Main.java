public class Main {

	public static void main(String[] args) {
		BloomFilter<Integer> bf = new BloomFilter<Integer>(1000000, 0.01);

		for (int i = 1; i < 1000000; i++) {
			bf.put(i);
		}

		int c = 0;
		for (int i = 1000001; i < 2000000; i++) {
			if (bf.contains(i)) {
				c++;
			}
		}
		System.out.println(c);

	}

}
