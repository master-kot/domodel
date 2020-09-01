package ru.geekbrains.domodel.domain.core.operationresults;

import java.io.Serializable;

public class Metadata implements MetadataMessage, HaveDataObject, Serializable {
    private  String _message;
    private Object _dataObject;

    private OperationResult _source;
    private MetadataType _type;

    public Metadata() {
        _type = MetadataType.Info;
    }

    public Metadata(OperationResult source, String message)
    {
        this();
        _message = message;
        _source = source;
    }

    public Metadata(OperationResult source, String message, MetadataType type) {
        _type = type;
       _source = source;
       _message= message;
    }

    @Override
    public void addData(Object data) {
        if (data instanceof Exception && _source.getMetadata() == null) {
            _source.setMetadata(new Metadata(this._source, ((Exception)data).getMessage()));
        } else{
            _source.getMetadata()._dataObject = data;
        }
    }

    @Override
    public String getMessage() {
        return _message;
    }

    @Override
    public Object getDataObject() {
        return _dataObject;
    }

    public MetadataType getMetadataType(){
        return _type;
    }
}
