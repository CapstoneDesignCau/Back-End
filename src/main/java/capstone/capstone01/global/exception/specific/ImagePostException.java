package capstone.capstone01.global.exception.specific;

import capstone.capstone01.global.apipayload.code.BaseErrorCode;
import capstone.capstone01.global.exception.GeneralException;

public class ImagePostException extends GeneralException {
    public ImagePostException(BaseErrorCode code) {
        super(code);
    }
}
