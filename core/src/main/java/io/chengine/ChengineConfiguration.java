package io.chengine;

import io.chengine.provider.HandlerProvider;
import io.chengine.provider.TriggerProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ChengineConfiguration {

	private final String packageToScan;
	private final boolean createAfterRegister;
	private final List<HandlerProvider> handlerProviders;
	private final List<TriggerProvider> triggerProviders;

	private ChengineConfiguration(
		final String packageToScan,
		final boolean createAfterRegister,
		final List<HandlerProvider> handlerProviders,
		final List<TriggerProvider> triggerProviders) {

		this.packageToScan = packageToScan;
		this.createAfterRegister = createAfterRegister;
		this.handlerProviders = handlerProviders;
		this.triggerProviders = triggerProviders;
	}

	public static class Builder {

		private String packageToScan;
		private boolean createAfterRegister = true;
		private List<HandlerProvider> handlerProviders;
		private List<TriggerProvider> triggerProviders;

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
			createHandlerListIfNotExist();
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

		public Builder addTriggerProvider(TriggerProvider triggerProvider) {
			Objects.requireNonNull(triggerProvider, "triggerProvider can't be null");
			createTriggerListIfNotExist();
			triggerProviders.add(triggerProvider);
			return this;
		}

		public ChengineConfiguration build() {
			return new ChengineConfiguration(
				packageToScan,
				createAfterRegister,
				handlerProviders,
				triggerProviders
			);
		}

		private void createHandlerListIfNotExist() {
			if (handlerProviders == null) {
				handlerProviders = new ArrayList<>();
			}
		}

		private void createTriggerListIfNotExist() {
			if (triggerProviders == null) {
				triggerProviders = new ArrayList<>();
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

	public List<TriggerProvider> getTriggerProviders() {
		return triggerProviders;
	}
}
