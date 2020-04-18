package io.chengine;

public class ChengineConfiguration {

	private final String packageToScan;

	private ChengineConfiguration(String packageToScan) {
		this.packageToScan = packageToScan;
	}

	public static class Builder {

		private String packageToScan;

		public Builder packageToScan(String packageToScan) {
			this.packageToScan = packageToScan;
			return this;
		}

		public ChengineConfiguration build() {
			return new ChengineConfiguration(packageToScan);
		}
	}
}
