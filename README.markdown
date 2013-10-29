# Implementing STM

## Getting Started

1. fork this repository 
2. clone your fork
3. switch to branch 0.1

`git checkout tags/0.1`

STM related functionality located under `concurrency.stm` package.
`concurrency.test` is primarily for testing purposes.

You can use slides from the [Presentation](http://www.slideshare.net/mishadoff/implementing-stm-in-java) as a source.

## Tasks

### 1. Transaction return value

We use `void` as a transaction return type. 
User must be able to do something like this:

``` java
Long id = STM.<Long>transaction(...);
String s = STM.<String>transaction(...);
```

Note: You can change the API and add generic parameter to infer the actual return type.

### 2. Avoid calling `this.getTx()` for `get/set` ref methods.

Not sure if it is possible due to Java limitations but 
investigate this problem, at least simplify access to the transaction.

Note: One solution is to make `Transaction` protected inside `TransactionBlock` so, all callers, will obtain this reference by accessing `tx` variable. But I hope there is more elegant solution. Don't hesitate to change the internal API.

### 3. Fair `GlobalContext` 

`GlobalContext` just delegates `get` ref value to the actual ref value, and does not support mapping, like `Transaction`'s `inTxMap`. Do the same.

Note: Your implementation need to register ref values, perform safe update of the mapping and cleanup, if ref is not accessible.

### 4. Snapshot Isolation

In `Transaction.get()` we've cheated a bit. Our snapshot is *lazy*, it retrieves ref's value *on demand*. Implement the fair **Snapshot Isolation**

Note: This task is slightly related to the previous one, as you need all ref values to build the snapshot. Perform defensive copy of snapshot as you need identity, not refs.

### 5. Ref history

Current implementation contains one last version of ref value. Implement true *Multi-Versioning*. Suggest or implement a prototype, how multi-versioning can improve STM performance.

### 6. Exceptions

We do not support exceptions, would be good to have.
Basically, there are must be two types of exceptions. One should trigger transaction retry (it can be some internal reason to throw this exception), another one should be thrown up to the caller method.

### 7. Nested transactions

I hope nested transactions are not supported in this code. Add them.
Possible, we need to retry the whole outer transaction if collision detected inside nested transactions.

### 8. Instrumentation

Provide set of tools to test STM performance and calculate some metrics. Number of transactions per second, number of retries, longest transaction, etc. Select at least 10 metrics, which ones is up to you.

### 9. Improvement

Detect bottlenecks of current implementation and improve performance.

Hint: `STM.commitLock` can be improved, but not limited to.

### 10. Implementation from scratch [HARD]

Implement any STM algorithm.

___

Inspired by [STM in Scala](http://www.codecommit.com/blog/scala/software-transactional-memory-in-scala)
