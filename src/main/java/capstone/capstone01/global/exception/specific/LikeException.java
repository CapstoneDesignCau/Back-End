package capstone.capstone01.global.exception.specific;

import capstone.capstone01.global.apipayload.code.BaseErrorCode;
import capstone.capstone01.global.exception.GeneralException;

public class LikeException extends GeneralException {
    public LikeException(BaseErrorCode baseErrorCode){
            super(baseErrorCode);
    }
}
