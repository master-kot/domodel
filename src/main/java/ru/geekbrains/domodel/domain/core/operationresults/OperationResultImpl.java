package ru.geekbrains.domodel.domain.core.operationresults;

import java.io.Serializable;

public class OperationResultImpl<T> extends OperationResult implements Serializable {
    private T _result;

    public T getResult(){
        return _result;
    }

    public void setResult(T result){
        this._result = result;
    }

    public Boolean Ok() {
        if (this.getMetadata() == null)
            return this.getException() == null && this.getResult() != null;
        if (this.getException() != null || this.getResult() == null)
            return false;
        Metadata metadata = this.getMetadata();
        return metadata == null || metadata.getMetadataType() != MetadataType.Error;
    }
}
