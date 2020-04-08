package io.chengine;

import io.chengine.adapter.Adapter;
import lombok.Builder;

import java.util.List;

@Builder
public class Chengine {


	private final List<Adapter> adapters;



}
