package ru.geekbrains.domodel.operationresults;

import java.io.Serializable;
import java.util.*;

public abstract class OperationResult implements Serializable {
    private final List<String> _logs = new ArrayList<>();

    private Metadata _metadata;
    private Exception _exception;

    public Metadata getMetadata() {
        return _metadata;
    }

    public void setMetadata(Metadata metadata) {
        _metadata = metadata;
    }

    public Exception getException() {
        return _exception;
    }

    public void setException(Exception exception) {
        _exception = exception;
    }

    public List<String> getLogs() {
        return _logs;
    }

    public static <TResult> OperationResultImpl<TResult> createResult(TResult result){
        OperationResultImpl<TResult> operation = new OperationResultImpl();
        operation.setResult(result);

        return operation;
    }

    public static <TResult> OperationResultImpl<TResult> createResult(){
        return OperationResult.createResult(null);
    }

    public void appendLog(String messageLog)
    {
        if (messageLog == null || messageLog.isEmpty())
            return;
        if (messageLog.length() > 500)
            this._logs.add(messageLog.substring(0, 500));
        this._logs.add(messageLog);
    }

    public void appendLog(List<String> messageLogs)
    {
        if (messageLogs == null)
            return;
        for (String messageLog : messageLogs)
        this.appendLog(messageLog);
    }

    ///////////////////////////////////////////////////////////////////////////////////

    public HaveDataObject addInfo(String message)
    {
        this.appendLog(message);
        _metadata = new Metadata(this, message);
        return _metadata;
    }

    public HaveDataObject addSuccess(String message)
    {
        this.appendLog(message);
        _metadata = new Metadata(this, message, MetadataType.Success);
        return _metadata;
    }

    public HaveDataObject addWarning(String message)
    {
        this.appendLog(message);
        _metadata = new Metadata(this, message, MetadataType.Warning);
        return _metadata;
    }

    public HaveDataObject addError(String message)
    {
        this.appendLog(message);
        _metadata = new Metadata(this, message, MetadataType.Error);
        return _metadata;
    }

    public HaveDataObject addError(Exception exception)
    {
        _exception = exception;
        if (exception != null)
            this.appendLog(exception.getMessage());
        _metadata = new Metadata(this, _logs.stream().findFirst().orElse(null), MetadataType.Error);
        return _metadata;
    }

    public HaveDataObject addError(String message, Exception exception)
    {
        _exception = exception;
        _metadata = new Metadata(this, message, MetadataType.Error);
        if (message != null && !message.isEmpty())
            this.appendLog(message);
        if (exception != null)
            this.appendLog(exception.getMessage());
        return _metadata;
    }

    public String getMetadataMessages()
    {
        StringBuilder sb = new StringBuilder();
        if (_metadata != null && _metadata.getMessage() != null)
            sb.append(_metadata.getMessage());
        if (!_logs.stream().anyMatch(log -> log != null && !log.isEmpty()))
            return sb.toString();
        _logs.forEach(log -> {
            sb.append("Log: " + log);
        });
        return sb.toString();
    }
}
