package com.qianzhui.enode.infrastructure.impl.inmemory;

import com.qianzhui.enode.common.io.AsyncTaskResult;
import com.qianzhui.enode.common.io.AsyncTaskStatus;
import com.qianzhui.enode.infrastructure.IMessageHandleRecordStore;
import com.qianzhui.enode.infrastructure.MessageHandleRecord;
import com.qianzhui.enode.infrastructure.ThreeMessageHandleRecord;
import com.qianzhui.enode.infrastructure.TwoMessageHandleRecord;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by junbo_xu on 2016/4/3.
 */
public class InMemoryMessageHandleRecordStore implements IMessageHandleRecordStore {
    private final CompletableFuture<AsyncTaskResult> _successTask = CompletableFuture.completedFuture(AsyncTaskResult.Success);
    private final ConcurrentMap<String, Integer> _dict = new ConcurrentHashMap<>();

    public CompletableFuture<AsyncTaskResult> addRecordAsync(MessageHandleRecord record) {
        _dict.put(record.getMessageId() + record.getHandlerTypeName(), 0);
        return _successTask;
    }

    public CompletableFuture<AsyncTaskResult> addRecordAsync(TwoMessageHandleRecord record) {
        _dict.put(record.getMessageId1() + record.getMessageId2() + record.getHandlerTypeName(), 0);
        return _successTask;
    }

    public CompletableFuture<AsyncTaskResult> addRecordAsync(ThreeMessageHandleRecord record) {
        _dict.put(record.getMessageId1() + record.getMessageId2() + record.getMessageId3() + record.getHandlerTypeName(), 0);
        return _successTask;
    }

    public CompletableFuture<AsyncTaskResult<Boolean>> isRecordExistAsync(String messageId, String handlerTypeName, String aggregateRootTypeName) {
        return CompletableFuture.completedFuture(new AsyncTaskResult<>(AsyncTaskStatus.Success, _dict.containsKey(messageId + handlerTypeName)));
    }

    public CompletableFuture<AsyncTaskResult<Boolean>> isRecordExistAsync(String messageId1, String messageId2, String handlerTypeName, String aggregateRootTypeName) {
        return CompletableFuture.completedFuture(new AsyncTaskResult<>(AsyncTaskStatus.Success, _dict.containsKey(messageId1 + messageId2 + handlerTypeName)));
    }

    public CompletableFuture<AsyncTaskResult<Boolean>> isRecordExistAsync(String messageId1, String messageId2, String messageId3, String handlerTypeName, String aggregateRootTypeName) {
        return CompletableFuture.completedFuture(new AsyncTaskResult<>(AsyncTaskStatus.Success, _dict.containsKey(messageId1 + messageId2 + messageId3 + handlerTypeName)));
    }
}
