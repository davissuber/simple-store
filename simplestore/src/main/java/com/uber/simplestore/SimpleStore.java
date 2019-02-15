package com.uber.simplestore;

import com.google.common.util.concurrent.ListenableFuture;
import java.io.Closeable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/** Fast, reliable storage. */
public interface SimpleStore extends Closeable {

  /**
   * Retrieve a byte[]-backed String.
   *
   * @param key to fetch from
   */
  @CheckReturnValue
  ListenableFuture<String> getString(String key);

  /**
   * Stores a String as a byte[].
   *
   * @param key to store to
   * @param value to write
   */
  @CheckReturnValue
  ListenableFuture<String> putString(String key, @Nullable String value);

  /**
   * Retrieve a byte[] from disk.
   *
   * @param key to store to
   */
  @CheckReturnValue
  ListenableFuture<byte[]> get(String key);

  /**
   * Stores a byte[] on disk.
   *
   * @param key to store to
   * @param value to store
   */
  @CheckReturnValue
  ListenableFuture<byte[]> put(String key, @Nullable byte[] value);

  /**
   * Removes a key from memory & disk.
   *
   * @param key to remove
   * @return when complete
   */
  @CheckReturnValue
  ListenableFuture<Void> remove(String key);

  /**
   * Determine if a key exists in storage.
   *
   * @param key to check
   * @return if key is set
   */
  @CheckReturnValue
  ListenableFuture<Boolean> contains(String key);

  /** Delete all keys in this direct scope. */
  @CheckReturnValue
  ListenableFuture<Void> deleteAll();

  /** Fails all outstanding operations then releases the memory cache. */
  @Override
  void close();
}
