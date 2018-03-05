/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.easyandroid.easyclean.httpextend.easycache;

import java.util.Collections;
import java.util.Map;

/**
 * An interface for a cache keyed by a String with a byte array as data.
 */
public interface Cache {
	/**
	 * Retrieves an entry from the cache.
	 * 
	 * @param key
	 *            Cache key
	 * @return An {@link Entry} or null in the event of a cache miss
	 */
	public Entry get(String key);

	/**
	 * Adds or replaces an entry to the cache.
	 * 
	 * @param key
	 *            Cache key
	 * @param entry
	 *            Data to store and metadata for cache coherency, TTL, etc.
	 */
	public void put(String key, Entry entry);

	/**
	 * Performs any potentially long-running actions needed to initialize the cache; will be called from a worker thread.
	 */
	public void initialize();

	/**
	 * Invalidates an entry in the cache.
	 * 
	 * @param key
	 *            Cache key
	 * @param fullExpire
	 *            True to fully expire the entry, false to soft expire
	 */
	public void invalidate(String key, boolean fullExpire);

	/**
	 * Removes an entry from the cache.
	 * 
	 * @param key
	 *            Cache key
	 */
	public void remove(String key);

	/**
	 * Empties the cache.
	 */
	public void clear();

	public long getCacheSize();

	public static final String CACHE_DURATION = "Cache_Duration";

	/**
	 * Data and metadata for an entry returned by the cache.
	 */
	public static class Entry {
		 
		/** The data returned from cache. */
		public byte[] data;

		/** 验证文件一致性用的ETag for cache coherency. */
		public String mimeType;// 验证文件一致性用的

		/** 服务器日期Date of this response as reported by the server. */
		public long serverDate;// 服务器日期

		/** 要保存到哪个时间TTL for this record. */
		public long ttl;// 要保存到哪个时间

		/** Soft TTL for this record. */
		public long softTtl;
		/**
		 * Immutable response headers as received from server; must be non-null.
		 */
		public Map<String, String> responseHeaders = Collections.emptyMap();

		/** 是否过期的True if the entry is expired. */
		public boolean isExpired() { // 是否过期的
			return this.ttl < System.currentTimeMillis();
		}

		/** True if a refresh is needed from the original data source. */
		public boolean refreshNeeded() {
			return this.softTtl < System.currentTimeMillis();
		}
	}

}
