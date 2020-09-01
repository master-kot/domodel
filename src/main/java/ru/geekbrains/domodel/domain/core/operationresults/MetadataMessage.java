package ru.geekbrains.domodel.domain.core.operationresults;

public interface MetadataMessage extends HaveDataObject {
    String getMessage();
    Object getDataObject();
}
