package capstone.capstone01.global.exception.specific;

import capstone.capstone01.global.apipayload.code.BaseErrorCode;
import capstone.capstone01.global.exception.GeneralException;

public class FileException extends GeneralException {
    public FileException(BaseErrorCode baseErrorCode){
        super(baseErrorCode);
    }
}
