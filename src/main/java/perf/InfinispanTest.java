///*
// * Licensed to the Apache Software Foundation (ASF) under one or more
// * contributor license agreements.  See the NOTICE file distributed with
// * this work for additional information regarding copyright ownership.
// * The ASF licenses this file to You under the Apache License, Version 2.0
// * (the "License"); you may not use this file except in compliance with
// * the License.  You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//import javax.transaction.HeuristicMixedException;
//import javax.transaction.HeuristicRollbackException;
//import javax.transaction.NotSupportedException;
//import javax.transaction.RollbackException;
//import javax.transaction.SystemException;
//import javax.transaction.TransactionManager;
//import org.infinispan.Cache;
//import org.infinispan.configuration.cache.ConfigurationBuilder;
//import org.infinispan.manager.DefaultCacheManager;
//import org.infinispan.transaction.LockingMode;
//import org.infinispan.transaction.TransactionMode;
//
///**
// * TODO
// */
//public class InfinispanTest {
//    public static void main(String[] args) throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException, NotSupportedException {
//        // Define the default cache to be transactional
//        ConfigurationBuilder builder = new ConfigurationBuilder();
//        builder.transaction().transactionMode(TransactionMode.TRANSACTIONAL);
//        builder.transaction().lockingMode(LockingMode.OPTIMISTIC);
//        // Construct a local cache manager using the configuration we have defined
//        DefaultCacheManager cacheManager = new DefaultCacheManager(builder.build());
//       // Obtain the default cache
//       Cache<String, byte[]> cache = cacheManager.getCache();
//
//        TransactionManager transactionManager = cache.getAdvancedCache().getTransactionManager();
//
//        transactionManager.begin();
//
////        Map<String, byte[]> cache = new ConcurrentHashMap8<>();
//       // Store a value
//       cache.put("key", new byte[1024 * 1024]);
//
//        transactionManager.commit();
//
//        long total = 0;
//        long cnt = 0;
//
//        for (int i = 0; i < 500_000; i++) {
//            long s = System.nanoTime();
//
//            transactionManager.begin();
//
//            cache.get("key");
//            cache.put("key", new byte[10]);
//
//            transactionManager.commit();
//
////            if (l != 1024 * 1024)
////                throw new IllegalStateException();
//
//            long t = System.nanoTime() - s;
//
//            total += t;
//            cnt++;
//        }
//
//        System.out.println("Average: " + (total / cnt));
//
//       // Stop the cache manager and release all resources
//       cacheManager.stop();
//    }
//}
