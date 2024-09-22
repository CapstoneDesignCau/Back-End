package capstone.capstone01.global.exception.specificException;

import capstone.capstone01.global.apipayload.code.BaseErrorCode;
import capstone.capstone01.global.exception.GeneralException;

public class UserException extends GeneralException {
    public UserException(BaseErrorCode baseErrorCode){
        super(baseErrorCode);
    }
}