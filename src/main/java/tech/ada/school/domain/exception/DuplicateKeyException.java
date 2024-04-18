package tech.ada.school.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DuplicateKeyException extends  Exception{
    private final Class clazz;
    private final String key;

}
