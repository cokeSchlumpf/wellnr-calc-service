/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package test;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import model.CalculationResult;

public class TestApplication {

	@Test
	public void test() {
		Cache<Pair<Integer, Integer>, CalculationResult> cache;
		cache = //
				CacheBuilder.newBuilder() //
						.maximumSize(100) //
						.expireAfterWrite(10, TimeUnit.MINUTES) //
						.build();

		cache.put(Pair.of(Integer.valueOf(10), Integer.valueOf(312)), new CalculationResult(322));
		CalculationResult result = cache.getIfPresent(Pair.of(Integer.valueOf(12), Integer.valueOf(312)));

		System.out.println(result);
	}

}
