package io.chengine;

import io.chengine.provider.HandlerProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ChengineConfiguration {

	private final String packageToScan;
	private final boolean createAfterRegister;
	private final List<HandlerProvider> handlerProviders;

	private ChengineConfiguration(
		final String packageToScan,
		final boolean createAfterRegister,
		final List<HandlerProvider> handlerProviders) {

		this.packageToScan = packageToScan;
		this.createAfterRegister = createAfterRegister;
		this.handlerProviders = handlerProviders;
	}

	public static class Builder {

		private String packageToScan;
		private boolean createAfterRegister = true;
		private List<HandlerProvider> handlerProviders;

		public Builder packageToScan(String packageToScan) {
			this.packageToScan = packageToScan;
			return this;
		}

		public Builder doNotCreateHandlersAfterRegistration() {
			this.createAfterRegister = false;
			return this;
		}

		public Builder addHandlerProvider(HandlerProvider handlerProvider) {
			Objects.requireNonNull(handlerProvider, "handlerProvider can't be null");
			createHandlerListIfNoExist();
			handlerProviders.add(handlerProvider);
			return this;
		}

		public Builder addHandlerProviders(Collection<HandlerProvider> handlerProviders) {
			Objects.requireNonNull(handlerProviders, "handlerProviders collection can't be null");
			handlerProviders.forEach(handlerProvider ->  {
					if (handlerProvider != null)
						addHandlerProvider(handlerProvider);
				});

			return this;
		}

		public ChengineConfiguration build() {
			return new ChengineConfiguration(
				packageToScan,
				createAfterRegister,
				handlerProviders
			);
		}

		private void createHandlerListIfNoExist() {
			if (handlerProviders == null) {
				handlerProviders = new ArrayList<>();
			}
		}
	}

	public String getPackageToScan() {
		return packageToScan;
	}

	public boolean isCreateAfterRegister() {
		return createAfterRegister;
	}

	public List<HandlerProvider> getHandlerProviders() {
		return handlerProviders;
	}
}
