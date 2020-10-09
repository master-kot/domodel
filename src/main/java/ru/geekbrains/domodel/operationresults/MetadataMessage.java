package ru.geekbrains.domodel.operationresults;

public interface MetadataMessage extends HaveDataObject {
    String getMessage();
    Object getDataObject();
}
